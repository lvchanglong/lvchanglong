package lvchanglong


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(AutoInterceptor)
class AutoInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test auto interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"auto")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
