package lvchanglong

/**
 * 学科[中华人民共和国学科分类与代码国家标准（GB/T13745-2009）]
 * 第1学科
 * @author lvchanglnog
 */
class DisciplineP {
	
	static hasMany = [terms: Term, children: DisciplineC]
	
	String name //学科名称
	
	static constraints = {
		name(unique: true, nullable: false, blank: false)
	}
	
	static mapping = {
		table 'DISCIPLINE_P'
		
		name column: 'NAME', sqlType: 'varchar(30)'
		
		id column:'ID'
		terms sort: "id", order: "desc"
		children sort: "id", order: "desc"
		version false
	}
	
	String toString() {
		return this.name
	}
	
	/**
	 * 学科初始化
	 */
	static def init() {
		def instance = DisciplineP.first()
		if(!instance) {
			DisciplineData.quanBu.each {pName, nmList->
				def parent = new DisciplineP(['name':pName.trim()])
				nmList.each {cName->
					def child = new DisciplineC(['name':cName.trim()])
					parent.addToChildren(child)
				}
				parent.save(flush: true)
			}
			def elseParent = new DisciplineP(['name':'未知'])
			elseParent.save(flush:true)
		}
	}
	
	/**
	 * 学科检索
	 * @param q 询问
	 * @return
	 */
	static def search(String q) {
		def list = this.searchP(q)
		if(!list) {
			list = this.searchC(q)
		}
		return list
	}

	/**
	 * 内部检索
	 * @param q
     */
	static def searchP(String q) {
		def dc = DisciplineP.where {
			name ==~ "%${q.trim()}%"
		}
		return dc.list([max: 10])
	}

	/**
	 * 延伸检索
	 * @param q
     */
	static def searchC(String q) {
		def dc = DisciplineP.where {
			children {
				name ==~ "%${q.trim()}%"
			}
		}
		return dc.list([max: 10])
	}
	
}
