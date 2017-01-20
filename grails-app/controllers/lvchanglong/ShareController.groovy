package lvchanglong

import grails.converters.JSON
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE

class ShareController {

    /**
     * 生肖查询
     * @param nian 年份
     */
    def shengXiaoChaXun(Integer nian) {
        if (nian && nian >= 0) {
            render Helper.getShengXiao(nian) as JSON
            return
        }
        render status: NOT_ACCEPTABLE, text: '数据不合法'
    }

    /**
     * ip详情
     * @param ip地址
     */
    def ipXiangQing(String ip) {
        try {
            def url = new URL("http://wap.ip138.com/ip138.asp?ip=" + ip.trim())
            def text = url.getText()
            render text.find(/(?<=<b>).*?(?=<\/b>)/)
        } catch(Exception e) {

        }
    }

    /**
     * 下载
     * <g:link controller="public" action="xiaZai" params="[filePath:'grails-app/assets/androids/lvchanglong.apk']">下安卓版</g:link>
     */
    def xiaZai(String filePath) {
        try {
            File file = new File(filePath.trim())
            def fileName = file.getName()
            def fileType = Helper.getFileType(fileName)
            response.contentType = grailsApplication.config.grails.mime.types[fileType]
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"))
            def out = response.getOutputStream()
            out << file.getBytes()
            out.flush()
            out.close()
        } catch(Exception e) {

        }
    }

    /**
     * 近期公告，HTML5 EventSource，服务器实时推送
     */
    def jinQiGongGao() {
        def array = ["I want to play a game with you", "我就是吕常龙", "我是这的站长", "我要不断的成长"]

        Integer i = Math.floor(Math.random() * array.size())

        String wt = array[i];

        response.setHeader("Content-Type", "text/event-stream")
        response.setHeader("Cache-Control", "no-cache")
        response.setCharacterEncoding ("UTF-8")

        def printWriter = response.getWriter()

        printWriter.println("data:" + wt)

        printWriter.println()
        printWriter.flush()
    }

}
