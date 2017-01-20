package lvchanglong

import grails.transaction.Transactional
import static org.springframework.http.HttpStatus.*

/**
 * 私有方法，需登录，管理员
 */
class PrivateController {

	static defaultAction = "index"

	/**
	 * 酱油
	 */
    def index() {
		render "私有的"
	}

}