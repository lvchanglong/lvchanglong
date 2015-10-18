package lvchanglong


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ProtectedInterceptor)
class ProtectedInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test protected interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"protected")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
