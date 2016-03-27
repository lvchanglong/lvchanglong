package lvchanglong

/**
 * 术语
 * @author lvchanglong
 *
 */
class Term {

	static belongsTo = [lan: Lan, discipline: Discipline, yongHu: YongHu] //语种，学科，用户
	
	String name //名称->导入
	String area = '无关'//地域
	String dy = '无定义'//定义->导入
	String ly //来源

	//共有：语种(lan)、学科(discipline)、用户(yongHu)、来源(ly)
	//导入：名称(name)、定义(dy)
	//特有：地域(ly)
	
	static constraints = {
		name(nullable: false, blank: false, unique: true)
		area(nullable: true, blank: true)
		dy(nullable: true, blank: true)
		ly(nullable: true, blank: true)
		
		lan(nullable: false, blank: false)
		discipline(nullable: true, blank: true)
		yongHu(nullable: false, blank: false)
	}
	
	static mapping = {
		table 'TERM'

		name column: 'NAME'
		area column: 'AREA'
		dy column: 'DY'
		ly column: 'LY'

		lan column: 'LAN_ID'
		discipline column: 'DISCIPLINE_ID'
		yongHu column: 'YONG_HU_ID'

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
