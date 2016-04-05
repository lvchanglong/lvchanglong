package lvchanglong

/**
 * 术语详情
 * @author lvchanglong
 */
class TermInfo {

    static belongsTo = [term: Term]

    String area = '无关'//地域
    String dy = '无'//定义
    String ly = '无'//来源

    static constraints = {
        area(nullable: true, blank: true)
        dy(nullable: true, blank: true)
        ly(nullable: true, blank: true)
        term(nullable: false, blank: false, unique: true)
    }

    static mapping = {
        table 'TERM_INFO'

        area column: 'AREA', sqlType: 'varchar(100)'
        dy column: 'DY', sqlType: 'varchar(255)'
        ly column: 'LY', sqlType: 'varchar(100)'
        term column: 'TERM_ID'

        id column:'ID'
        version false
    }

    String toString() {
        return "来源：${this.ly}<br>地域：${this.area}<br>定义：${this.dy}"
    }

}
