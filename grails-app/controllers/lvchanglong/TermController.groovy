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
	 * @param term autocomplete值
     */
	def searchTerm(String term) {
		if(term) {
			def termList = Term.search(term)
			if(termList) {
				ArrayList array = new ArrayList()
				termList.each {tm->
					def strId = tm.id
					def strName = tm.name
					array.add(new HashMap(['label':strName+"#"+strId, 'value':strName]))
				}
				render array as JSON
				return
			}
			render status:NOT_FOUND, text:"未知术语"
			return
		}
		render status:BAD_REQUEST, text:"非法请求"
	}

	/**
	 * 语种检索
	 * @param term autocomplete值
	 */
	def searchLan(String term) {
		if(term) {
			def lanList = Lan.search(term)
			if(lanList) {
				ArrayList array = new ArrayList()
				lanList.each {lan->
					def strId = lan.id
					def strName = lan.name
					array.add(new HashMap(['label':strName+"#"+strId, 'value':strName]))
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
	 * @param term autocomplete值
	 */
	def searchDiscipline(String term) {
		if(term) {
			def disList = DisciplineP.search(term)
			if(disList) {
				ArrayList array = new ArrayList()
				disList.each {dis->
					def strId = dis.id
					def strName = dis.name
					array.add(new HashMap(['label':strName+"#"+strId, 'value':strName]))
				}
				render array as JSON
				return
			}
			render status:NOT_FOUND, text:"未知学科"
			return
		}
		render status:BAD_REQUEST, text:"非法请求"
	}

	/**
	 * 条目检索
	 * @param term 术语 耐久比#3144
	 * @return
	 */
	def getEntry(String term) {
		if(term) {
			def tid = term.split("#")[1]
			def termInstance = Term.get(tid)
			Entry entry = termInstance.entry
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

	def test() {
		println Term.findSolr("name:高*")
	}

}
