package lvchanglong


class PrivateInterceptor {

    boolean before() { 
		if(!session.uid) {
			redirect(url:"/")
			return
		}
		if(!YongHu.get(session.uid).shiFouGuanLiYuan()) {
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
