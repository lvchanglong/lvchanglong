package lvchanglong

import org.apache.solr.client.solrj.SolrClient
import org.apache.solr.client.solrj.impl.HttpSolrClient
import org.apache.solr.client.solrj.response.QueryResponse
import org.apache.solr.common.SolrDocumentList
import org.apache.solr.common.SolrInputDocument
import org.apache.solr.common.params.ModifiableSolrParams

/**
 * Created by 吕常龙 on 16-4-7.
 */
class SolrHelper {

    /**
     * solr create -c lvchanglong -p 8983
     * @param urlString
     * @return
     */
    static SolrClient getSolrClient(String urlString = "http://localhost:8983/solr/lvchanglong") {
        return new HttpSolrClient(urlString)
    }

    /**
     * 添加
     * @param solr
     * @param document
     * SolrInputDocument document = new SolrInputDocument()
     * document.addField("username", "吕布")
     * document.addField("password", "123456")
     * document.addField("quanxian", "管理员")
     */
    static void add(SolrClient solr, SolrInputDocument document) {
        solr.add(document)
        solr.commit()
    }

    /**
     * 删除
     * @param solr
     * @param solrId 699eadec-7398-4ed1-a4ff-379981e9e2aa
     */
    static void delete(SolrClient solr, String solrId) {
        solr.deleteById(solrId)
        solr.commit()
    }

    /**
     * 查询删除
     * @param solr
     * @param query
     */
    static void deleteByQuery(SolrClient solr, String query) {
        solr.deleteByQuery(query)
        solr.commit()
    }

    /**
     * 修改
     * @param solr
     * @param solrId
     * @param document
     */
    static void update(SolrClient solr, String solrId, SolrInputDocument document) {
        solr.deleteById(solrId)
        solr.add(document)
        solr.commit()
    }

    /**
     * 查询修改
     * @param solr
     * @param query
     * @param document
     */
    static void updateByQuery(SolrClient solr, String query, SolrInputDocument document) {
        solr.deleteByQuery(query)
        solr.add(document)
        solr.commit()
    }

    /**
     * 检索
     * ModifiableSolrParams params = new ModifiableSolrParams()
     * params.add("q", "username:*常*")
     * @param solr
     * @param params
     */
    static SolrDocumentList search(SolrClient solr, ModifiableSolrParams params) {
        QueryResponse response = solr.query(params)
        return response.getResults()
    }

    static main(args) {
        SolrClient solr = this.getSolrClient()
        ModifiableSolrParams params = new ModifiableSolrParams()
        params.add("q", "username:*常*")
        SolrDocumentList docs = this.search(solr, params)
        docs.each { doc->
            println doc.getFieldValue("username")
            println doc.getFieldValue("password")
            println doc.getFieldValue("quanxian")
        }
    }

}
