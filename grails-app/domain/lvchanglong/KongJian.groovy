package lvchanglong

import java.util.Date;

/**
 * 空间
 * @author lvchanglong
 *
 */
class KongJian {

    static belongsTo = [yongHu: YongHu]
	
	String biaoTi //标题
	String miaoShu //描述
	String fuJian //附件
	String neiRong //内容（实体ID列表）
	
	static constraints = {
		yongHu(nullable: false, blank: false)
		biaoTi(nullable: false, blank: false)
		miaoShu(nullable: false, blank: false)
		fuJian(nullable: false, blank: false)
		neiRong(nullable: true, blank: true)
	}
	
	static mapping = {
		table 'KONG_JIAN'
		
		yongHu column: 'YONG_HU_ID'
		
		biaoTi column: 'BIAO_TI'
		miaoShu column: 'MIAO_SHU'
		fuJian column: 'FU_JIAN', sqlType:"text"
		neiRong column: 'NEI_RONG', sqlType:"text"

		id column:'ID'
		version column:'BAN_BEN'
		
		sort id: "desc"
	}
	
	String toString() {
		return this.biaoTi
	}
	
	String getYongHuAsString() {
		return this.yongHu?:"匿名"
	}
	
	/**
	 * 实体列表
	 */
	List<ShiTi> children() {
		if (this.neiRong) {
			String[] splits = this.neiRong.trim().split("(,|，)")
			def arrayList = ShiTi.getAll(splits)
			return arrayList.findAll {elem->
				elem != null
			}
		}
		return null
	}
	
}
