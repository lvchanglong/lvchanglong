package lvchanglong

/**
 * 用户
 * @author lvchanglong
 *
 */
class YongHu {

	static hasMany = [shiTis: ShiTi]
	
	String xingMing //姓名
	String miMa //密码
	
	String jianJie = "暂无" //简介
	
	byte[] touXiang //头像
	
	String quanXian = "普通用户" //权限
	
	Date dateCreated //创建时间
	Date lastUpdated //更新时间

	static transients = ["sessionID"] //属性集合（不会出现在数据库中）
	
	String sessionID //session-ID
	
	static constraints = {
		xingMing(nullable: false, blank: false)
		miMa(nullable: false, blank: false, unique: true)
		jianJie(nullable: false, blank: false)
		touXiang(nullable: true, blank: true)
		quanXian(nullable: false, blank: false, inList: ["管理员", "普通用户"])
	}
	
	static mapping = {
		table 'YONG_HU'
		
		miMa column: 'MI_MA'
		xingMing column: 'XING_MING'
		jianJie column: 'JIAN_JIE'
		touXiang column: 'TOU_XIANG', sqlType: "MediumBlob"
		quanXian column: 'QUAN_XIAN'

		id column:'ID'
		version column:'BAN_BEN'
		dateCreated column: 'CHUANG_JIAN_SHI_JIAN'
		lastUpdated column: 'GENG_XIN_SHI_JIAN'
		
		shiLis sort: "id", order: "desc"
	}
	
	String toString() {
		return this.xingMing
	}
	
	def beforeInsert() {
		def md5 = (this.xingMing + this.miMa).encodeAsMD5()
		this.miMa = md5
	}
	
	def beforeUpdate() {
		
	}
	
	/**
	 * 是管理员么？
	 * @return
	 */
	boolean isAdmin() {
		return (this.quanXian == "管理员")
	}

	/**
	 * 是否是我的实体
	 * @param stid
	 * @return
     */
	boolean isMyShiTi(def stid) {
		if(this.isAdmin()) {
			return true
		}
		def shiTi = ShiTi.where {
			id == stid
			yongHu {
				id == this.id
			}
		}
		return shiTi.count()
	}
	
	/**
	 * 查询单个实例
	 * @param strXingMing 姓名
	 * @param strMiMa 密码
	 */
	static def findInstance(String strXingMing, String strMiMa) {
		def md5 = (strXingMing + strMiMa).encodeAsMD5()
		return YongHu.findByMiMa(md5)
	}
	
	/**
	 * 用户初始化
	 */
	static boolean init() {
		def strXingMing = "吕常龙"
		def strMiMa = "54Mt"
		
		def yonghu = YongHu.findInstance(strXingMing, strMiMa)
		if (yonghu) {
			return true
		}
		
		Map map = [
			xingMing: strXingMing,
			miMa: strMiMa,
			jianJie:"我是这里的站长...",
			quanXian: "管理员"
		]
		yonghu = new YongHu(map)
		if (yonghu.hasErrors()) {
			return false
		} else {
			yonghu.save flush:true
			return true
		}
	}

}
