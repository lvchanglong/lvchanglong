import lvchanglong.YongHu
import lvchanglong.Lan
import lvchanglong.Discipline

class BootStrap {
    def init = { servletContext ->
		YongHu.init() //用户初始化
		Lan.init() //语种初始化
        Discipline.init() //学科初始化
    }
    def destroy = {
    }
}
