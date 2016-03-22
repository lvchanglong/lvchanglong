package lvchanglong

/**
 * 语种
 * @author lvchanglong
 *
 */
class Lan {

	static hasMany = [terms: Term]
	
	String name //语种名称
	Boolean ysc = false //已删除
	
	static constraints = {
		name(unique: true, nullable: false, blank: false)
		ysc(nullable: false, blank: false)
	}
	
	static mapping = {
		table 'LAN'
		
		name column: 'NAME'
		ysc column: 'YSC'
		
		id column:'ID'
		version false
	}
	
	String toString() {
		return this.name
	}
	
	/**
	 * 语种初始化
	 */
	static def init() {
		def n = Lan.count()
		if(0 == n) {
			LanData.quanBu.each {nm->
				def lan = new Lan(['name':nm.trim()])
				lan.save(flush: true)
			}
		}
	}
	
	/**
	 * 语种检索
	 * @param q 询问
	 * @return
	 */
	static def search(String q) {
		def dc = Lan.where {
			(ysc == false) && (name ==~ "%" + q + "%")
		}
		return dc.list([max: 10])
	}
	
}
