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
	def users(String q) {
		params.max = 9
		if(null == q) {
			q = ""
		}
		def criteria = User.where {
			(zhangHao ==~ "%" + q.trim() + "%") || (xingMing ==~ "%" + q.trim() + "%")
		}
		[instanceList:criteria.list(params), instanceCount:criteria.count()]
	}

	/**
	 * 反馈列表
	 * @param max
	 * @return
     */
	def feedbacks(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[instanceList: Feedback.list(params), instanceCount: Feedback.count()]
	}

	/**
	 * 发布反馈
	 */
	@Transactional
	def saveFeedback(Feedback instance, String xxx) {
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
	def info() {

	}

	/**
	 * 网站首页
	 */
	def index(String text) {
		params.max = 9
		def trimText = text?text.trim():""
		def criteria = Element.where {
			(biaoTi ==~ "%" + trimText + "%") || (neiRong ==~ "%" + trimText + "%")
		}
		[instanceList:criteria.list(params), instanceCount:criteria.count()]
	}

}
