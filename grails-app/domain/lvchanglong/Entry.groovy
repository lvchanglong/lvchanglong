package lvchanglong

/**
 * 条目(概念分组)
 * @author lvchanglong
 *
 */
class Entry {

	String nms //术语集(JSON)
	String ids //索引集(JSON)
	
	static constraints = {
		nms(unique: true, nullable: false, blank: false)
		ids(unique: true, nullable: false, blank: false)
	}
	
	static mapping = {
		table 'ENTRY'
		
		nms column: 'NMS'
		ids column: 'IDS'
		
		id column:'ID'
		version false
	}
	
	String toString() {
		return this.nms
	}
	
	/**
	 * 建立关联
	 * @param a 术语A
	 * @param z 术语Z
	 * @return
	 */
	static def link(Term a, Term z) {
		def dc = Entry.where {
			(nms ==~ "%\"${a.name}\"%") || (nms ==~ "%\"${z.name}\"%")
		}
		def entry = dc.find()
		if(entry) {
			HashSet setNms = JsonHelper.decode(entry.nms)
			setNms.add(a.name)
			setNms.add(z.name)
			String strNms = setNms.encodeAsJSON()
			
			HashSet setIds = JsonHelper.decode(entry.ids)
			setIds.add(a.id.toString())
			setIds.add(z.id.toString())
			String strIds = setIds.encodeAsJSON()
			
			entry.nms = strNms
			entry.ids = strIds
			entry.save(flush: true)
			return entry
		} else {
			String strNms = [a.name, z.name].encodeAsJSON()
			String strIds = [a.id.toString(), z.id.toString()].encodeAsJSON()
			
			entry = new Entry([nms:strNms, ids:strIds])
			entry.save(flush: true)
			return entry
		}
	}
	
	/**
	 * 条目检索
	 * @param q 询问
	 * @return
	 */
	static def find(String q) {
		def dc = Entry.where {
			(nms ==~ "%\"" + q + "\"%")
		}
		return dc.find()
	}
	
	HashSet getIdsAsHS() {
		return JsonHelper.decode(this.ids)
	}
	
	HashSet getNmsAsHS() {
		return JsonHelper.decode(this.nms)
	}
	
}
