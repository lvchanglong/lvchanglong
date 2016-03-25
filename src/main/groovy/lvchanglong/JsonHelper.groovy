package lvchanglong

import grails.converters.JSON
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

/**
 * JSON帮助
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
     * 函数缺点：中文被UTF-8编码，导致肉眼无法识别
     * 解决方案：使用Grails的encodeAsJSON()代替
	 * @param obj
	 * @return
	 */
	static def encode(def obj) {
		return new JsonBuilder(obj)
	}
	
	static main(args) {
		Set set = new HashSet(['#吕#','#常#','#龙#'])
		set.add("#吕#")
        println set.inspect()
        println Eval.me(set.inspect())

        def jb = new JsonBuilder(set)
        println jb.toPrettyString() //清晰的json结构展示

		println this.encode(set)
		
		this.decode('["#吕#","#常#","#龙#"]').each {inst->
			println inst
		}
	}

}
