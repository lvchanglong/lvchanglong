package lvchanglong

/**
 * 术语
 * @author lvchanglong
 *
 */
class Term {

	static belongsTo = [lan: Lan, discipline: Discipline] //语种，学科
	
	String name //术语名称
	String area //地域
	String dy //定义
	String ly //来源
	
	static constraints = {
		name(nullable: false, blank: false)
		area(nullable: true, blank: true)
		dy(nullable: true, blank: true)
		ly(nullable: true, blank: true)
		
		lan(nullable: false, blank: false)
		discipline(nullable: true, blank: true)
	}
	
	static mapping = {
		table 'TERM'
		
		name column: 'NAME'
		area column: 'AREA'
		dy column: 'DY'
		ly column: 'LY'
		
		lan column: 'LAN_ID'
		discipline column: 'DISCIPLINE_ID'
		
		id column:'ID'
		version false
	}
	
	String toString() {
		return this.name
	}
	
	/**
	 * 术语检索
	 * @param q 询问
	 * @return
	 */
	static def search(String q) {
		def dc = Term.where {
			name ==~ "%" + q + "%"
		}
		return dc.list([max: 10])
	}
	
}
