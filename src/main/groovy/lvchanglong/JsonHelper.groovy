package lvchanglong

import grails.converters.JSON
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

/**
 * JSON帮助
 * 来源：google, baidu
 */
class JsonHelper {
	
	/**
	 * JSON解析
	 * @param strJson
	 * @return
	 */
	static def decode(String strJson) {
		return new JsonSlurper().parseText(strJson)
	}

	/**
	 * JSON编码
	 * @param obj
	 * @return
	 */
	static def encode(def obj) {
		return new JsonBuilder(obj)
	}
	
	static main(args) {
		Set set = new HashSet(['#吕#','#常#','#龙#'])
		set.add("#吕#")
//		set.add(2)
//		set.add(3)
		println this.encode(set)
		
		this.decode("[5,6]").each {inst->
			println "#${inst}#"
		}
	}

}
