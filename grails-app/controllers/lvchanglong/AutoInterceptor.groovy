package lvchanglong


class AutoInterceptor {
	
	AutoInterceptor() {
		matchAll()
	}
	
    boolean before() { 
		if(!session.uname) { //姓名
			session.uname = ChineseName.getInst()
		}
		if(!session.uinfo) { //简介
			session.uinfo = JianJie.getInst()
		}
		return true
	}

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
