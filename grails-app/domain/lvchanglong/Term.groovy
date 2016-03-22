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
	
	static constraints = {
		name(nullable: false, blank: false)
		area(nullable: true, blank: true)
		dy(nullable: true, blank: true)
		
		lan(nullable: false, blank: false)
		discipline(nullable: true, blank: true)
	}
	
	static mapping = {
		table 'TERM'
		
		name column: 'NAME'
		area column: 'AREA'
		dy column: 'DY'
		
		lan column: 'LAN_ID'
		discipline column: 'DISCIPLINE_ID'
		
		id column:'ID'
		version false
	}
	
	String toString() {
		return this.name
	}
	
}
