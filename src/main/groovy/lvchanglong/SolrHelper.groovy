package lvchanglong

import org.apache.solr.client.solrj.SolrClient
import org.apache.solr.client.solrj.impl.HttpSolrClient
import org.apache.solr.client.solrj.response.QueryResponse
import org.apache.solr.client.solrj.response.UpdateResponse
import org.apache.solr.common.SolrDocument
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
     * 获得
     * @param solr
     * @param id
     * @return
     */
    static SolrDocument get(SolrClient solr, def id) {
        return solr.getById(id.toString())
    }

    /**
     * 检索
     * ModifiableSolrParams params = new ModifiableSolrParams()
     * params.add("rows", "1")
     * params.add("q", "username:*常*")
     * @param solr
     * @param params
     */
    static SolrDocumentList search(SolrClient solr, ModifiableSolrParams params) {
        QueryResponse response = solr.query(params)
        return response.getResults()
    }

    /**
     * 添加
     * @param solr
     * @param document
     * SolrInputDocument document = new SolrInputDocument()
     * document.addField("id", "lvchanglong")
     * document.addField("username", "吕常龙")
     * document.addField("password", "123456")
     */
    static UpdateResponse add(SolrClient solr, SolrInputDocument document) {
        solr.add(document)
        return solr.commit()
    }

    /**
     * 删除
     * @param solr
     * @param id
     */
    static UpdateResponse delete(SolrClient solr, def id) {
        solr.deleteById(id.toString())
        return solr.commit()
    }

    /**
     * 查询删除
     * @param solr
     * @param query "name:*常*"
     */
    static UpdateResponse deleteByQuery(SolrClient solr, String query) {
        solr.deleteByQuery(query)
        return solr.commit()
    }

    static main(args) {
        SolrClient solr = this.getSolrClient()

        /*ModifiableSolrParams params = new ModifiableSolrParams()
        params.add("rows", "1")
        params.add("q", "username:*常*")
        SolrDocumentList docs = this.search(solr, params)
        docs.each { doc->
            println doc.getFieldValue("username")
            println doc.password

            doc.setField("password", "123456")
        }*/

        /*SolrInputDocument document = new SolrInputDocument()
        document.addField("id", "0") //增
        document.addField("name", "吕布")
        solr.add(document)
        solr.commit()*/

        /*solr.deleteById('0') //删
        solr.commit()*/

        ModifiableSolrParams params = new ModifiableSolrParams()
        params.add("rows", "2")
        params.add("q", "name:common bond") //params.add("q", "name:\"common bond\"")
        SolrDocumentList docs = this.search(solr, params)
        docs.each { doc->
            println doc.name
        }
    }

}
