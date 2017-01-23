package lvchanglong

/**
 * 元素
 */
class Element {
    static belongsTo = [user: User]

    String biaoTi //标题
    String neiRong //内容
    String leiBie = "视频" //类别
    Integer lieShu = 8 //列数（最大12）

    Date dateCreated
    Date lastUpdated

    static constraints = {
        biaoTi(nullable: false, blank: false)
        neiRong(nullable: false, blank: false)
        leiBie(nullable: true, blank: true, inList: ["视频", "链接", "文本"])
        lieShu(nullable: true, blank: true, inList: [8, 12, 4])
    }

    static mapping = {
        table 'element'

        id column:'id'
        user column: 'user_id'
        biaoTi column: 'biao_ti'
        neiRong column: 'nei_rong', sqlType:"text"
        leiBie column: 'lei_bie'
        lieShu column: 'lie_shu'

        dateCreated column: 'chuang_jian_shi_jian'
        lastUpdated column: 'geng_xin_shi_jian'

        sort id: "desc"
        version false
    }

    String toString() {
        return this.biaoTi
    }

    def getColspan() {
        return this.lieShu?:4
    }

    boolean isVideo() {
        return this.leiBie.equals("视频")
    }

    boolean isLink() {
        return this.leiBie.equals("链接")
    }

    boolean isText() {
        return this.leiBie.equals("文本")
    }
}
