import lvchanglong.YongHu
import lvchanglong.Lan
import lvchanglong.DisciplineP

class BootStrap {
    def init = { servletContext ->
		YongHu.init() //用户初始化
		Lan.init() //语种初始化
        DisciplineP.init() //学科初始化
    }
    def destroy = {
    }
}
