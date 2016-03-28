package lvchanglong

import java.util.Date

/**
 * 反馈（免登录）
 * @author lvchanglong
 */
class FanKui {

	String xingMing //姓名
	String neiRong //内容
	
	Date dateCreated
	
	static constraints = {
		xingMing(nullable: false, blank: false)
		neiRong(nullable: false, blank: false)
	}
	
	static mapping = {
		table 'FAN_KUI'
		
		xingMing column: 'XING_MING'
		neiRong column: 'NEI_RONG', sqlType:"text"

		id column:'ID'
		dateCreated column: 'CHUANG_JIAN_SHI_JIAN'
		
		sort id: "desc"
		version false
	}
	
	String toString() {
		return this.neiRong
	}
	
}
