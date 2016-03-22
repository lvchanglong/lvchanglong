package lvchanglong

/**
 * 条目(概念分组)
 * @author lvchanglong
 *
 */
class Entry {

	String code //编码序列(JSON)
	String idx //索引序列(JSON)
	
	static constraints = {
		code(unique: true, nullable: false, blank: false)
		idx(unique: true, nullable: false, blank: false)
	}
	
	static mapping = {
		table 'ENTRY'
		
		code column: 'CODE'
		idx column: 'IDX'
		
		id column:'ID'
		version false
	}
	
	String toString() {
		return this.code
	}
	
	/**
	 * 建立关联
	 * @param a 术语A
	 * @param z 术语Z
	 * @return
	 */
	static def link(Term a, Term z) {
		def dc = Entry.where {
			(code ==~ "%#${a.name}#%") || (code ==~ "%#${z.name}#%")
		}
		def entry = dc.find()
		if(entry) {
			HashSet setCode = JsonHelper.decode(entry.code)
			setCode.add("#" + a.name + "#")
			setCode.add("#" + z.name + "#")
			String strCode = JsonHelper.encode(setCode)
			
			HashSet<String> setIdx = JsonHelper.decode(entry.idx)
			setIdx.add(a.id.toString())
			setIdx.add(z.id.toString())
			String strIdx = JsonHelper.encode(setIdx)
			
			entry.code = strCode
			entry.idx = strIdx
			entry.save(flush: true)
			return entry
		} else {
			String strCode = JsonHelper.encode(new HashSet(["#${a.name}#", "#${z.name}#"]))
			
			String strIdx = JsonHelper.encode(new HashSet(["${a.id}", "${z.id}"]))
			
			entry = new Entry(['code':strCode, 'idx':strIdx])
			entry.save(flush: true)
			return entry
		}
	}
	
	/**
	 * 条目检索
	 * @param q 询问
	 * @return
	 */
	static def search(String q) {
		def dc = Entry.where {
			(code ==~ "%#" + q + "#%")
		}
		return dc.find()
	}
	
}
