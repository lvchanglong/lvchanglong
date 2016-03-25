package lvchanglong

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
 * Excel帮助
 * Created by 吕常龙 on 2016/3/25.
 */
class ExcelHelper {
    static Workbook open(File file) {
        def fileName = file.getName()
        def fileType = Helper.getFileType(fileName)
        switch (fileType) {
            case "xls":
                return new HSSFWorkbook(file.newInputStream())
            case "xlsx":
                return new XSSFWorkbook(file.newInputStream())
            default:
                return null
        }
    }
}
