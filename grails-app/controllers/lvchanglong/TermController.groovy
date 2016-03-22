package lvchanglong

import static org.springframework.http.HttpStatus.*

class TermController {

	static defaultAction = "index"
	
	def index() {
		render Term.list()
	}
	
	def test() {
		render "好好好" + "晕晕晕111"
	}
	
	def conn1() {
		def lan = Lan.get(103)
		lan.addToTerms(new Term(["name":"汞"]))
		lan.addToTerms(new Term(["name":"水银"]))
		render lan.save(flush: true)
	}
	
	def conn2() {
		def lanC = Lan.get(103)
		lanC.addToTerms(new Term(["name":"监狱"]))
		lanC.save(flush: true)
		
		def lanE = Lan.get(100)
		lanE.addToTerms(new Term(["name":"prison"]))
		lanE.addToTerms(new Term(["name":"jail"]))
		lanE.save(flush: true)
		
		render "conn2"
	}
	
}
