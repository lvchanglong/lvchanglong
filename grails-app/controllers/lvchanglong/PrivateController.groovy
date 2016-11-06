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

	/**
	 * 酱油
	 */
    def index() {
		render "私有的"
	}

	//---------------------------------------------------------------------------------------------------

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

	/**
	 * 用户列表（页面）
	 */
	def users(String q) {
		params.max = 9

		if(q) {
			def trimText = q.trim()
			def criteria = YongHu.where {
				(xingMing ==~ "%" + trimText + "%") || (xingMing ==~ "%" + trimText + "%")
			}
			return [yongHuList:criteria.list(params), yongHuCount:criteria.count()]
		}

		[yongHuList:YongHu.list(params), yongHuCount:YongHu.count()]
	}

	/**
	 * 删除用户
	 */
	@Transactional
	def deleteYongHu(YongHu yongHu) {
		if (yongHu == null) {
			render status: NOT_FOUND
			return
		}

		yongHu.delete flush:true
		render status: NO_CONTENT
	}

	/**
	 * solr索引重置
     */
	def resetSolr() {
		def solr = SolrHelper.getSolrClient()
		SolrHelper.deleteByQuery(solr, "*:*")
		render status: OK, text: "重置索引"
	}

	def fix() {
		ShiTi.findAll().each {st->
			st.leiBie = "默认"
			st.save(flush: true)
		}
		render status: OK, text: "修复成功"
	}

}
