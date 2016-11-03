package lvchanglong

import lvchanglong.YongHu

class BootStrap {

    def init = { servletContext ->
		YongHu.init() //用户初始化
    }
    def destroy = {
    }
}
