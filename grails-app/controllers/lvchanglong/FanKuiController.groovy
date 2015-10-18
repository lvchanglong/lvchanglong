package lvchanglong

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FanKuiController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond FanKui.list(params), [status: OK]
    }

    @Transactional
    def save(FanKui fanKui) {
        if (fanKui == null) {
            render status: NOT_FOUND
            return
        }

        fanKui.validate()
        if (fanKui.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        fanKui.save flush:true
        respond fanKui, [status: CREATED]
    }

    @Transactional
    def update(FanKui fanKui) {
        if (fanKui == null) {
            render status: NOT_FOUND
            return
        }

        fanKui.validate()
        if (fanKui.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        fanKui.save flush:true
        respond fanKui, [status: OK]
    }

    @Transactional
    def delete(FanKui fanKui) {

        if (fanKui == null) {
            render status: NOT_FOUND
            return
        }

        fanKui.delete flush:true
        render status: NO_CONTENT
    }
}
