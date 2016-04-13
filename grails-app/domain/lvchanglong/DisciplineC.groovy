package lvchanglong

/**
 * 学科[中华人民共和国学科分类与代码国家标准（GB/T13745-2009）]
 * 第2学科(后台辅助，作为第1学科关键词)
 * @author lvchanglnog
 * 
 */
class DisciplineC {
	
	static belongsTo = [parent: DisciplineP]
	
	String name //学科名称
	
	static constraints = {
		name(unique: true, nullable: false, blank: false)
	}
	
	static mapping = {
		table 'discipline_c'
		
		name column: 'NAME', sqlType: 'varchar(30)'

		parent column: 'PARENT_ID'
		
		id column:'ID'
		version false
	}
	
	String toString() {
		return this.name
	}
	
}