package lvchanglong

class Test {

	static main(args) {
        def xls = new File("E:/", "123456.xls")
        def xlsx = new File("E:/", "123456.xlsx")
        def wb = ExcelHelper.open(xlsx)
        def sheet = wb.getSheetAt(0)
        for(int i=sheet.getFirstRowNum(); i<=sheet.getLastRowNum(); i++) {
            def row = sheet.getRow(i)
            def termFrom = row.getCell(0).getStringCellValue()
            def termTo = row.getCell(1).getStringCellValue()
            println "From:" + termFrom + ",To:" + termTo
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
