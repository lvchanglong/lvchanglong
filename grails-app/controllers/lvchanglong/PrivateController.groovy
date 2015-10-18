package lvchanglong

import static org.springframework.http.HttpStatus.*

/**
 * 私有方法，需登录，管理员
 */
class PrivateController {

	static defaultAction = "index"
	
	def test() {
//		def array = new ArrayList([null, null, 'test', null, 'hello', 'world'])
//		println array
//
//		println "n:" + array.findAll {elem->
//			elem != null
//		}

//		println KongJian.first().children()
	}
	
	def ajaxTest() {
		render status: BAD_REQUEST, text:"请求不合法！！！！！"
	}
		
    def index() {
		render "私有的"
	}
	
	/**
	 * 修复图片路径
	 */
	def fixImageUrl() {
		/*ShiTi.list().each {st->
			st.neiRong = st.neiRong.replaceAll("(kindeditor/attached/image|ueditor/jsp/upload/file|ueditor/jsp/upload/image)", "storage")
			st.save(flush:true)
		}*/
		render true
	}
	
}
