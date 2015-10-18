package lvchanglong

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ShiTiController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ShiTi.list(params), [status: OK]
    }

    @Transactional
    def save(ShiTi shiTi) {
        if (shiTi == null) {
            render status: NOT_FOUND
            return
        }

        shiTi.validate()
        if (shiTi.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        shiTi.save flush:true
        respond shiTi, [status: CREATED]
    }

    @Transactional
    def update(ShiTi shiTi) {
        if (shiTi == null) {
            render status: NOT_FOUND
            return
        }

        shiTi.validate()
        if (shiTi.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        shiTi.save flush:true
        respond shiTi, [status: OK]
    }

    @Transactional
    def delete(ShiTi shiTi) {

        if (shiTi == null) {
            render status: NOT_FOUND
            return
        }

        shiTi.delete flush:true
        render status: NO_CONTENT
    }
}
