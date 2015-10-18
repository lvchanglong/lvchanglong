package lvchanglong

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class YongHuController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond YongHu.list(params), [status: OK]
    }

    @Transactional
    def save(YongHu yongHu) {
        if (yongHu == null) {
            render status: NOT_FOUND
            return
        }

        yongHu.validate()
        if (yongHu.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        yongHu.save flush:true
        respond yongHu, [status: CREATED]
    }

    @Transactional
    def update(YongHu yongHu) {
        if (yongHu == null) {
            render status: NOT_FOUND
            return
        }

        yongHu.validate()
        if (yongHu.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        yongHu.save flush:true
        respond yongHu, [status: OK]
    }

    @Transactional
    def delete(YongHu yongHu) {

        if (yongHu == null) {
            render status: NOT_FOUND
            return
        }

        yongHu.delete flush:true
        render status: NO_CONTENT
    }
}
