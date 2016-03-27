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
			def termList = Term.search(term)
			if(termList) {
				render termList.name as JSON
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
			render status:NOT_FOUND, text:"未知条目"
			return
		}
		render status:BAD_REQUEST, text:"非法请求"
	}

	/**
	 * 语种检索
	 * @param term 术语
	 */
	def searchLan(String term) {
		if(term) {
			def lanList = Lan.search(term)
			if(lanList) {
				ArrayList array = new ArrayList()
				lanList.each {lan->
					array.add(new HashMap(['label':lan.name+"#"+lan.id, 'value':lan.name]))
				}
				render array as JSON
				return
			}
			render status:NOT_FOUND, text:"未知语种"
			return
		}
		render status:BAD_REQUEST, text:"非法请求"
	}

	/**
	 * 学科检索
	 * @param term 术语
	 */
	def searchDiscipline(String term) {
		if(term) {
			def disList = Discipline.search(term)
			if(disList) {
				ArrayList array = new ArrayList()
				disList.each {dis->
					def strName = dis.name
					if(dis.parent) {
						strName = dis.parent.name + ">" + dis.name
					}
					array.add(new HashMap(['label':strName+"#"+dis.id, 'value':dis.name]))
				}
				render array as JSON
				return
			}
			render status:NOT_FOUND, text:"未知学科"
			return
		}
		render status:BAD_REQUEST, text:"非法请求"
	}

}
