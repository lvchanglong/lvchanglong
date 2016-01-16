package lvchanglong

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON
import grails.gorm.DetachedCriteria

/**
 * 公有方法，所有人
 */
class PublicController {

	static defaultAction = "index"
	
	/**
	 * 管理员初始化
	 */
	def initAdmin() {
		YongHu.sheZhiGuanLiYuan()
	}
	
	/**
	 * 反馈(页面)
	 */
	def fanKui(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond FanKui.list(params), model:[fanKuiCount: FanKui.count()]
	}
	
	/**
	 * 用前必读(页面)
	 */
	def yongQianBiDu() {
		
	}
	
	/**
	 * 实体详情(页面)
	 * @param shiTi
	 */
	def showShiTi(ShiTi shiTi) {
		if (shiTi == null) {
			render status: NOT_FOUND
			return
		}
		respond shiTi
	}
	
	/**
	 * 网站首页(页面)
	 */
    def index(String text) {
		params.max = 9
		
		if(text) {
			def trimText = text.trim()
			def criteria = ShiTi.where {
				(biaoTi ==~ "%" + trimText + "%") || (neiRong ==~ "%" + trimText + "%")
			}
			return [shiTiList:criteria.list(params), shiTiCount:criteria.count()]
		}
		
		[shiTiList:ShiTi.list(params), shiTiCount:ShiTi.count()]
	}
	
	//---------------------------------------------------------------------------------------------------
	
	/**
	 * 用户登录(服务)
	 * @param xingMing 姓名
	 * @param miMa 密码
	 */
	def yongHuDengLu(String xingMing, String miMa) {
		if (xingMing) {
			def yonghu = YongHu.findInstance(xingMing, miMa)
			if (yonghu) {
				session.uid = yonghu.id
				session.uname = yonghu.xingMing //姓名
				session.uinfo = yonghu.jianJie //简介
				
				session.setMaxInactiveInterval(10800) //失效时间3小时
				render status: OK, text: '操作成功，初始化...'
				return
			} else {
				render status: UNAUTHORIZED, text: '认证失败'
				return
			}
		}
		render status: BAD_REQUEST, text: '参数异常'
	}
	
	/**
	 * 用户注销(服务)
	 */
	def yongHuZhuXiao() {
		session.invalidate()
		render status: OK, text: '操作成功'
	}
	
	/**
	 * 用户注册(服务)
	 */
	@Transactional
	def yongHuZhuCe(String xingMing, String miMa, String queRenMiMa, String jianJie) {
		if (xingMing && miMa) {
			if (miMa == queRenMiMa) {//确认密码一致性
				def yongHu = YongHu.findInstance(xingMing, miMa)
				if (yongHu) {//冲突
					render status: CONFLICT, text: '已存在'
					return
				}
				def yonghu = new YongHu([xingMing: xingMing, miMa: miMa, jianJie: jianJie])//注册用户
				if (!yonghu.hasErrors()) {
					yonghu.save flush: true
					render status: OK, text: '注册成功'
					return
				}
			}
			render status: NOT_ACCEPTABLE, text: '密码不一致'
			return
		}
		render status: BAD_REQUEST, text: '参数异常'
	}
	
	/**
	 * 生肖查询(服务)
	 * @param nian 年份
	 */
	def shengXiaoChaXun(Integer nian) {
		if (nian && nian >= 0) {
			render Helper.getShengXiao(nian) as JSON
			return
		}
		render status: NOT_ACCEPTABLE, text: '数据不合法'
	}
	
	/**
	 * ip详情(服务)
	 * @param ip地址
	 */
	def ipXiangQing(String ip) {
		try {
			def url = new URL("http://wap.ip138.com/ip138.asp?ip=" + ip)
			def text = url.getText()
			render text.find(/(?<=<b>).*?(?=<\/b>)/)
		} catch(Exception e) {
			
		}
	}
	
	/**
	 * 下载(服务)
	 * @param filePath 文件路径  grails-app/assets/androids/lvchanglong.apk
	 */
	def xiaZai(String filePath) {
		try {
			File file = new File(filePath)
			def fileName = file.getName()
			def fileType = Helper.getFileType(fileName)
			response.contentType = grailsApplication.config.grails.mime.types[fileType]
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"))
			def out = response.getOutputStream()
			out << file.getBytes()
			out.flush()
			out.close()
		} catch(Exception e) {
			
		}
	}
	
	/**
	 * 近期公告，HTML5 EventSource，服务器实时推送(服务)
	 */
	def jinQiGongGao() {
		def array = ["I want to play a game with you", "我就是吕常龙", "我是这的站长", "我要不断的成长"]
		
		Integer i = Math.floor(Math.random() * array.size())
		
		String wt = array[i];
		
		response.setHeader("Content-Type", "text/event-stream")
		response.setHeader("Cache-Control", "no-cache")
		response.setCharacterEncoding ("UTF-8")
		
		def printWriter = response.getWriter()

		printWriter.println("data:" + wt)
		
		printWriter.println()
		printWriter.flush()
	}
	
	/**
	 * 头像加载(服务)
	 */
	def loadTouXiang(YongHu yongHu) {
		try {
			def url = new URL(createLink(uri:'/', absolute:true) + assetPath(src:'SuCai/%E8%AE%B0%E8%80%85.png').replaceFirst("/", ""))
			byte[] byteList = url.getBytes()
			if (yongHu) {
				if (yongHu.touXiang) {
					byteList = yongHu.touXiang
				}
			}
			def out = response.getOutputStream()
			out << byteList
			out.flush()
			out.close()
		} catch(Exception e) {
		
		}
	}
	
}
