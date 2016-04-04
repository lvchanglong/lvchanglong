package lvchanglong

/**
 * 条目(概念分组，后台辅助，作为术语相关信息)
 * @author lvchanglong
 *
 */
class Entry {

	static hasMany = [terms: Term]

	String ids //索引集(JSON)

	static constraints = {
		ids(unique: true, nullable: false, blank: false)
	}
	
	static mapping = {
		table 'ENTRY'

		ids column: 'IDS'
		
		id column:'ID'
		version false
	}
	
	String toString() {
		return this.ids
	}

	/**
	 * 建立关联
	 * @param a 术语A
	 * @param z 术语Z
	 * @return
	 */
	static def link(Term a, Term z) {
		if(a.entry || z.entry) { //更新
			def entry = null
			def aEntry = a.entry
			def zEntry = z.entry
			if(aEntry && zEntry) { //关联已存在

			} else if(aEntry) { //A关联存在
				entry = aEntry
				entry.addToTerms(z)
			} else { //Z关联存在
				entry = zEntry
				entry.addToTerms(a)
			}
			HashSet setIds = JsonHelper.decode(entry.ids)
			setIds.add(a.id.toString())
			setIds.add(z.id.toString())
			String strIds = setIds.encodeAsJSON()
			entry.ids = strIds
			entry.save(flush: true)
			return entry
		} else { //新建
			String strIds = [a.id.toString(), z.id.toString()].encodeAsJSON()
			def entry = new Entry([ids:strIds])
			entry.addToTerms(a)
			entry.addToTerms(z)
			entry.save(flush: true)
			return entry
		}
	}

	HashSet getIdsAsHS() {
		return JsonHelper.decode(this.ids)
	}
	
}
