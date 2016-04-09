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
	 * @param termFrom 术语From
	 * @param termTo 术语To
	 * @return
	 */
	static def link(Term termFrom, Term termTo) {
		if(termFrom.id && termTo.id) {
			def entryFrom = termFrom.entry
			def entryTo = termTo.entry
			if(entryFrom && entryTo) {
				//已存在
			} else if(entryFrom) {//entryFrom存在, entryTo不在
				HashSet setIds = JsonHelper.decode(entryFrom.ids)
				setIds.add(termTo.id.toString())
				String strIds = setIds.encodeAsJSON()
				entryFrom.ids = strIds

				entryFrom.addToTerms(termTo)
				entryFrom.save(flush: true) //更新ids
				return entryFrom
			} else if(entryTo) {//entryTo存在, entryFrom不在
				HashSet setIds = JsonHelper.decode(entryTo.ids)
				setIds.add(termFrom.id.toString())
				String strIds = setIds.encodeAsJSON()
				entryTo.ids = strIds

				entryTo.addToTerms(termFrom)
				entryTo.save(flush: true) //更新ids
				return entryTo
			} else { //新建
				String strIds = [termFrom.id.toString(), termTo.id.toString()].encodeAsJSON()
				def entry = new Entry([ids:strIds])

				entry.addToTerms(termFrom)
				entry.addToTerms(termTo)

				entry.save(flush: true)
				return entry
			}
		}
	}

	HashSet getIdsAsHS() {
		return JsonHelper.decode(this.ids)
	}
	
}
