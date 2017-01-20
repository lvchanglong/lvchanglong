package lvchanglong
import static org.springframework.http.HttpStatus.*

class PrivateInterceptor {

    boolean before() { 
		if(!session.uid) {
			redirect(url:"/")
			return
		}
		if(!User.get(session.uid).isAdmin()) {
			render status: UNAUTHORIZED, text:"未授权，非管理员"
			return
		}
		return true
	}

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
