package lvchanglong

import lvchanglong.User

class BootStrap {

    def init = { servletContext ->
		User.init()
    }
    def destroy = {
    }
}
