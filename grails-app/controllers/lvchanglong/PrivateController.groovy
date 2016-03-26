package lvchanglong

import grails.transaction.Transactional
import static org.springframework.http.HttpStatus.*

/**
 * 私有方法，需登录，管理员
 */
class PrivateController {

	static defaultAction = "index"
	
	def test() {

	}
	
	def ajaxTest() {
		render status: BAD_REQUEST, text:"请求不合法！！！！！"
	}

	def conn() {
		def lan = Lan.get(103)
		lan.addToTerms(new Term(["name":"汞"]))
		lan.addToTerms(new Term(["name":"水银"]))
		lan.save(flush: true)

		def lanC = Lan.get(103)
		lanC.addToTerms(new Term(["name":"监狱"]))
		lanC.save(flush: true)

		def lanE = Lan.get(100)
		lanE.addToTerms(new Term(["name":"prison"]))
		lanE.addToTerms(new Term(["name":"jail"]))
		lanE.save(flush: true)

		Entry.link(Term.findByName('水银'), Term.findByName('汞'))
		Entry.link(Term.findByName('监狱'), Term.findByName('jail'))
		Entry.link(Term.findByName('jail'), Term.findByName('prison'))

		render "conn"
	}
		
    def index() {
		render "私有的"
	}

	/**
	 * 删除反馈
     */
	@Transactional
	def deleteFanKui(FanKui fanKui) {
		if (fanKui == null) {
			render status: NOT_FOUND
			return
		}

		fanKui.delete flush:true
		render status: NO_CONTENT
	}
}
