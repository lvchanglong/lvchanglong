package lvchanglong

/**
 * 用户
 */
class User {
    static hasMany = [elements: Element]

    String zhangHao //账号
    String xingMing //姓名
    byte[] touXiang //头像
    String quanXian = "普通用户" //权限
    Date dateCreated //创建时间
    Date lastUpdated //更新时间

    static constraints = {
        zhangHao(nullable: false, blank: false, unique: true)
        xingMing(nullable: false, blank: false)
        touXiang(nullable: true, blank: true)
        quanXian(nullable: false, blank: false, inList: ["管理员", "普通用户"])
    }

    static mapping = {
        table 'user'

        id column:'id'
        zhangHao column: 'zhang_hao'
        xingMing column: 'xing_ming'
        touXiang column: 'tou_xiang', sqlType: "MediumBlob"
        quanXian column: 'quan_xian'
        dateCreated column: 'chuang_jian_shi_jian'
        lastUpdated column: 'geng_xin_shi_jian'

        elements sort: "id", order: "desc"

        sort id: "desc"
        version false
    }

    String toString() {
        return this.xingMing
    }

    def beforeInsert() {

    }

    /**
     * 用户初始化
     */
    static boolean init() {
        def instance = User.first()
        if(!instance) {
            Map map = [
                zhangHao: "54Mt",
                xingMing: "吕常龙",
                quanXian: "管理员"
            ]
            instance = new User(map)
            if (instance.hasErrors()) {
                return false
            } else {
                instance.save flush:true
                return true
            }
        }
    }

    /**
     * 是管理员么？
     * @return
     */
    boolean isAdmin() {
        return this.quanXian.equals("管理员")
    }
}
