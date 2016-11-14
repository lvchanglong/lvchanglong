package lvchanglong

import java.util.Date;

/**
 * 实体
 * @author lvchanglong
 *
 */
class ShiTi {
	
	static belongsTo = [yongHu: YongHu]

	String biaoTi //标题
	String neiRong //内容
	String leiBie = "默认"//类别
	
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		yongHu(nullable: true, blank: true)
		biaoTi(nullable: false, blank: false)
		neiRong(nullable: false, blank: false)
		leiBie(nullable: false, blank: false, inList: ["默认", "观察", "思考"])
	}
	
	static mapping = {
		table 'shi_ti'
		
		yongHu column: 'YONG_HU_ID'
		
		biaoTi column: 'BIAO_TI'
		neiRong column: 'NEI_RONG', sqlType:"text"
		leiBie column: 'LEI_BIE'

		id column:'ID'
		version column:'BAN_BEN'
		dateCreated column: 'CHUANG_JIAN_SHI_JIAN'
		lastUpdated column: 'GENG_XIN_SHI_JIAN'
		
		sort id: "desc"
	}
	
	String toString() {
		return this.neiRong
	}
	
	String getYongHuAsString() {
		return this.yongHu?:"匿名"
	}
	
}
