package lvchanglong

import org.apache.solr.client.solrj.response.QueryResponse
import org.apache.solr.common.SolrDocument
import org.apache.solr.common.SolrDocumentList
import org.apache.solr.common.SolrInputDocument
import org.apache.solr.common.params.ModifiableSolrParams

/**
 * 术语
 * @author lvchanglong
 *
 */
class Term {

	static hasOne = [termInfo: TermInfo] //术语详情(地域area，定义dy，来源ly)
	static belongsTo = [lan: Lan, discipline: DisciplineP, yongHu: YongHu, entry: Entry] //语种，学科，用户，条目
	
	String name //名称->导入

	//导入：名称(name)、定义(dy)、语种(lan)、学科(discipline)、用户(yongHu)、条目(entry)、来源(ly)
	//特有：地域(ly)
	static constraints = {
		name(nullable: false, blank: false, unique: true)

		termInfo(nullable: true, blank: true)

		lan(nullable: false, blank: false)
		discipline(nullable: true, blank: true)
		yongHu(nullable: false, blank: false)
		entry(nullable: true, blank: true)
	}
	
	static mapping = {
		table 'TERM'

		name column: 'NAME', sqlType: 'varchar(130)'

		lan column: 'LAN_ID'
		discipline column: 'DISCIPLINE_ID'
		yongHu column: 'YONG_HU_ID'
		entry column: 'ENTRY_ID'

		id column:'ID'
		version false
	}
	
	String toString() {
		return this.name
	}
	
	/**
	 * 模糊检索(注：使用LIKE操作时，如果条件以通配符开始（ '%abc...'），MySQL无法使用索引)
	 * @param q 询问
	 * @return
	 */
	static def search(String q) {
		def dc = Term.where {
			name ==~ q.trim() + "%"
		}
		return dc.list([max: 10])
	}

	/**
	 * 精确检索
	 * @param q
	 * @return
	 */
	static def find(String q) {
		def dc = Term.where {
			name == q.trim()
		}
		return dc.find()
	}

	/**
	 * 模糊检索solr
	 * @param q
	 * @return
     */
	static SolrDocumentList searchSolr(String q) {
		def solr = SolrHelper.getSolrClient()
		ModifiableSolrParams params = new ModifiableSolrParams()
		params.add("rows", "10")
		params.add("q", "name:${q.trim()}*")
		return SolrHelper.search(solr, params)
	}

	/**
	 * 精确检索solr
	 * @param q
	 * @return
     */
	static SolrDocument findSolr(String q) {
		def solr = SolrHelper.getSolrClient()
		ModifiableSolrParams params = new ModifiableSolrParams()
		params.add("rows", "1")
		params.add("q", "name:\"${q.trim()}\"")
		QueryResponse response = solr.query(params)
		SolrDocumentList docs = response.getResults()
		if(docs.getNumFound() > 0) {
			return docs.get(0)
		}
		return null
	}

	def afterInsert() {
		def solr = SolrHelper.getSolrClient()
		SolrInputDocument document = new SolrInputDocument()
		document.addField("id", this.id)
		document.addField("name", this.name)
		SolrHelper.add(solr, document)
		solr.close()
	}

	def beforeUpdate() {
		if(this.isDirty('name')) {
			def solr = SolrHelper.getSolrClient()
			SolrInputDocument document = new SolrInputDocument()
			document.setField("id", this.id)
			document.setField("name", this.name)
			SolrHelper.add(solr, document)
			solr.close()
		}
	}

	def beforeDelete() {
		def solr = SolrHelper.getSolrClient()
		SolrHelper.delete(solr, this.id)
		solr.close()
	}

}