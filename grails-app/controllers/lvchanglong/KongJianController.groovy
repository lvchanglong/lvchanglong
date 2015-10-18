package lvchanglong

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class KongJianController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond KongJian.list(params), [status: OK]
    }

    @Transactional
    def save(KongJian kongJian) {
        if (kongJian == null) {
            render status: NOT_FOUND
            return
        }

        kongJian.validate()
        if (kongJian.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        kongJian.save flush:true
        respond kongJian, [status: CREATED]
    }

    @Transactional
    def update(KongJian kongJian) {
        if (kongJian == null) {
            render status: NOT_FOUND
            return
        }

        kongJian.validate()
        if (kongJian.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        kongJian.save flush:true
        respond kongJian, [status: OK]
    }

    @Transactional
    def delete(KongJian kongJian) {

        if (kongJian == null) {
            render status: NOT_FOUND
            return
        }

        kongJian.delete flush:true
        render status: NO_CONTENT
    }
}
