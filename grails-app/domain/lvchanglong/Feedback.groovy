package lvchanglong

/**
 * 反馈
 */
class Feedback {
    String neiRong //内容
    Date dateCreated //反馈时间

    static constraints = {
        neiRong(nullable: false, blank: false)
    }

    static mapping = {
        table 'feedback'

        id column:'id'
        neiRong column: 'nei_rong', sqlType:"text"
        dateCreated column: 'chuang_jian_shi_jian'

        sort id: "desc"
        version false
    }

    String toString() {
        return this.neiRong
    }
}
