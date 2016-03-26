package lvchanglong


class AutoInterceptor {
	
	AutoInterceptor() {
		matchAll()
	}
	
    boolean before() { 
		if(!session.uname) { //姓名
			session.uname = ChineseName.getInst()
		}
		return true
	}

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
