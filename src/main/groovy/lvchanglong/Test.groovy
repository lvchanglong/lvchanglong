package lvchanglong

import org.apache.poi.ss.usermodel.Cell

class Test {

	static main(args) {
        def xls = new File("E:/", "123456.xls")
        def xlsx = new File("E:/", "123456.xlsx")
        def wb = ExcelHelper.open(xlsx)
        def sheet = wb.getSheetAt(0)
        for(int i=sheet.getFirstRowNum(); i<=sheet.getLastRowNum(); i++) {
            def row = sheet.getRow(i)
			def cell = row.getCell(0)
			switch (cell.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					println cell.getBooleanCellValue()
					break
				case Cell.CELL_TYPE_NUMERIC:
					println cell.getNumericCellValue()
					break
				case Cell.CELL_TYPE_STRING:
					println cell.getStringCellValue()
					break
				case Cell.CELL_TYPE_BLANK:
					break
				case Cell.CELL_TYPE_ERROR:
					break
				case Cell.CELL_TYPE_FORMULA:// CELL_TYPE_FORMULA will never happen
					break
			}
        }
//        xlsx.delete()
	}
	
	def a() {
		def tarFile = new File("E:/", "tar.txt")
		tarFile.withWriter("UTF-8") {writer->
			def file = new File("E:/", "新建文本文档.txt")
			file.readLines("UTF-8").each {str->
				if(str) {
					if(!str.startsWith("　")) {
						str = str.find(/(?<=　).*?(?=$)/)
						println str
						writer.write(str + "\n")
					}
				}
			}
		}
	}

}
