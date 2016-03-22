package lvchanglong

/**
 * 学科[中华人民共和国学科分类与代码国家标准（GB/T13745-2009）]
 * @author lvchanglnog
 * 
 */
class Discipline {
	
	static hasMany = [terms: Term]
	
	String name //学科名称
	Discipline parent //一级学科
	Boolean ysc = false //已删除
	
	static constraints = {
		name(unique: true, nullable: false, blank: false)
		parent(nullable: true, blank: true)
		ysc(nullable: false, blank: false)
	}
	
	static mapping = {
		table 'DISCIPLINE'
		
		name column: 'NAME'
		parent column: 'PARENT_ID'
		ysc column: 'YSC'
		
		id column:'ID'
		version false
	}
	
	String toString() {
		return this.name
	}
	
	/**
	 * 学科初始化
	 */
	static def init() {
		def n = Discipline.count()
		if(0 == n) {
			DisciplineData.quanBu.each {name, nmList->
				def p = new Discipline(['name':name.trim()])
				p.save(flush:true)
				nmList.each {nm->
					def discipline = new Discipline(['name':nm.trim()])
					discipline.parent = p
					discipline.save(flush: true)
				}
			}
		}
	}
	
	/**
	 * 学科检索
	 * @param q 询问
	 * @return
	 */
	static def search(String q) {
		def dc = Discipline.where {
			(ysc == false) && (name ==~ "%" + q + "%")
		}
		return dc.list([max: 10])
	}
	
}
