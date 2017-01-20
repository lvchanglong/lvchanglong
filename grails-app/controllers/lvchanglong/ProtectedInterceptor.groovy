package lvchanglong


class ProtectedInterceptor {
    boolean before() { 
		if(!session.uid) {
			redirect(url:"/")
			return
		}
		return true
	}

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
