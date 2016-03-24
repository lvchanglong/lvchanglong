package lvchanglong

import grails.converters.JSON

import static org.springframework.http.HttpStatus.*

class TermController {

	static defaultAction = "index"

	/**
	 * 检索入口
	 */
	def index() {

	}

	/**
	 * 术语检索
	 * @param term 术语
     */
	def searchTerm(String term) {
		if(term) {
			def tList = Term.search(term)
			if(tList) {
				render tList.name as JSON
				return
			}
			render status:NOT_FOUND, text:"未知术语"
			return
		}
		render status:BAD_REQUEST, text:"非法请求"
	}

	/**
	 * 条目检索
	 * @param term 术语
	 * @return
     */
	def searchEntry(String term) {
		if(term) {
			Entry entry = Entry.find(term)
			if(entry) {
				def terms = Term.getAll(entry.getIdsAsHS())
				render(template: '/term/entry', model: ['termInstanceList': terms])
				return
			}
			render status:NOT_FOUND, text:"未知术语"
			return
		}
		render status:BAD_REQUEST, text:"非法请求"
	}

}
