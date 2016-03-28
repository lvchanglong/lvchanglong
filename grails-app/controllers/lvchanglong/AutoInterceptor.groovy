package lvchanglong


class AutoInterceptor {
	
	AutoInterceptor() {
		matchAll()
	}
	
    boolean before() {
		return true
	}

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
