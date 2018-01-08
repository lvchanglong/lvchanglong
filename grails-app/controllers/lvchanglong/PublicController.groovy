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
	 * 用户列表
	 */
	def users(String q, Integer max) {
		params.max = Math.min(max ?: 9, 100)
		def trimText = q?q.trim():""
		def criteria = User.where {
			(zhangHao ==~ "%" + trimText + "%") || (xingMing ==~ "%" + trimText + "%")
		}
		[instanceList:criteria.list(params), instanceCount:criteria.count()]
	}

	/**
	 * 反馈列表
	 * @param max
	 * @return
     */
	def feedbacks(Integer max) {
		params.max = Math.min(max ?: 9, 100)
		[instanceList: Feedback.list(params), instanceCount: Feedback.count()]
	}

	/**
	 * 发布反馈
	 */
	@Transactional
	def saveFeedback(Feedback instance, String xxx, String neiRong) {
		if(!neiRong) {
			render status: NOT_ACCEPTABLE
			return
		}
		if(neiRong.toLowerCase().contains("http") || neiRong.toLowerCase().contains("href")) {
			render status: NOT_ACCEPTABLE
			return
		}
		if(!request.isRequestedSessionIdFromCookie()) {
			render status: NOT_ACCEPTABLE
			return
		}
		if(!xxx) {
			render status: NOT_ACCEPTABLE
			return
		}
		if(!xxx.equals(Feedback.count().encodeAsMD5())) {
			render status: NOT_ACCEPTABLE
			return
		}
		if (instance == null) {
			render status: NOT_FOUND
			return
		}
		instance.validate()
		if (instance.hasErrors()) {
			render status: NOT_ACCEPTABLE
			return
		}
		instance.save flush:true
		respond instance, [status: CREATED, formats:['json', 'xml']]
	}

	/**
	 * 删除反馈
	 */
	@Transactional
	def deleteFeedback(Feedback instance) {
		if (instance == null) {
			render status: NOT_FOUND
			return
		}
		instance.delete flush:true
		render status: NO_CONTENT
	}

	/**
	 * 登录系统
	 */
	def login(String zhangHao) {
		if (zhangHao) {
			def instance = User.findByZhangHao(zhangHao)
			if (instance) {
				session.uid = instance.id
				session.setMaxInactiveInterval(43200) //失效时间12小时
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
	 * 退出系统
	 */
	def logout() {
		session.invalidate()
		render status: OK, text: '操作成功'
	}

	/**
	 * 用户注册
	 */
	@Transactional
	def register(String zhangHao) {
		if (zhangHao) {
			def instance = User.findByZhangHao(zhangHao)
			if (instance) {//冲突Z
				render status: CONFLICT, text: '用户已存在'
				return
			}
			instance = new User(params)//注册用户
			if (!instance.hasErrors()) {
				instance.save flush: true
				render status: OK, text: '注册成功'
				return
			}
		}
		render status: BAD_REQUEST, text: '参数异常'
	}

	/**
	 * 头像加载(服务)
	 */
	def loadTouXiang(User user) {
		try {
			def url = new URL(createLink(uri:'/', absolute:true) + assetPath(src:'SuCai/%E8%AE%B0%E8%80%85.png').replaceFirst("/", ""))
			byte[] bytes = url.getBytes()
			if (user) {
				if (user.touXiang) {
					bytes = user.touXiang
				}
			}
			def out = response.getOutputStream()
			out << bytes
			out.flush()
			out.close()
		} catch(Exception e) {

		}
	}

	/**
	 * 用前必读
	 */
	def information() {

	}

	/**
	 * 网站首页
	 */
	def index(String content, Integer max) {
		if(content) {
			params.max = Math.min(max ?: 20, 40)
			def trimContent = content.trim()
			def criteria = Element.where {
				(biaoTi ==~ "%" + trimContent + "%") || (neiRong ==~ "%" + trimContent + "%")
			}
			return [instanceList:criteria.list(params), instanceCount:criteria.count()]
		} else {
			def elements = Element.list()
			HashMap<String, ArrayList> hm = elements.groupBy {element->
				element.leiBie
			}
			def videoList = hm.get("视频")
			def linkList = hm.get("链接")
			def textList = hm.get("文本")

			def video = Helper.random(videoList)
			def link = Helper.random(linkList)
			def texts = new ArrayList()

			def minimum = Math.min(textList.size(), 9)
			if(minimum >= 1) {
				for(i in 1..minimum) {
					def text = Helper.random(textList)
					texts.add(text)
					textList.remove(text)
				}
			}
			return [video:video, link:link, texts:texts, instanceList:elements]
		}
	}

	/**
	 * 元素详情
	 * @param id
     */
	def element(String id) {
		if(id) {
			def instance = Element.get(id)
			if(instance) {
				return ["instance":instance]
			}
		}
	}

	/**
	 *视频
	 */
	def videos(String q, Integer max) {
		params.max = Math.min(max ?: 1, 9)
		def trimText = q?q.trim():""
		def criteria = Element.where {
			(leiBie == "视频") && ((biaoTi ==~ "%" + trimText + "%") || (neiRong ==~ "%" + trimText + "%"))
		}
		[instanceList:criteria.list(params), instanceCount:criteria.count()]
	}

	/**
	 *链接
	 */
	def links(String q, Integer max) {
		params.max = Math.min(max ?: 1, 9)
		def trimText = q?q.trim():""
		def criteria = Element.where {
			(leiBie == "链接") && ((biaoTi ==~ "%" + trimText + "%") || (neiRong ==~ "%" + trimText + "%"))
		}
		[instanceList:criteria.list(params), instanceCount:criteria.count()]
	}

	/**
	 *文本
	 */
	def texts(String q, Integer max) {
		params.max = Math.min(max ?: 1, 9)
		def trimText = q?q.trim():""
		def criteria = Element.where {
			(leiBie == "文本") && ((biaoTi ==~ "%" + trimText + "%") || (neiRong ==~ "%" + trimText + "%"))
		}
		[instanceList:criteria.list(params), instanceCount:criteria.count()]
	}

}
