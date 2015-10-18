package lvchanglong


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PrivateInterceptor)
class PrivateInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test private interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"private")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
