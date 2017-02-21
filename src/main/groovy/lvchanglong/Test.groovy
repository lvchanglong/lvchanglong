package lvchanglong

class Test {

	static def convert() {
		File inputFile = new File("F:\\2pdf", "中期检查.doc")

		def openOffice = new OpenOffice()
		def pdf = openOffice.convert2pdf(inputFile)
		if(pdf) {
			def pdf2swf = new SWFTools()
			def swf = pdf2swf.convert2swf(pdf)
			if(swf) {
				println "转换成功"
				println swf.getAbsolutePath()
			} else {
				println "swf转换失败"
			}
		} else {
			println "pdf转换失败"
		}

	}

	static main(args) {

//		OpenOffice.start()

		Test.convert()

//		def sb = new StringBuilder()
//		sb.append('<div id="替换成优酷视频ID" style="width:480px;height:400px"></div>')
//		sb.append('<script type="text/javascript">')
//		sb.append('player = new YKU.Player("替换成优酷视频ID",{')
//		sb.append('styleid: "0",')
//		sb.append('client_id: "b2a05d6e715a8025",')
//		sb.append('vid: "替换成优酷视频ID",')
//		sb.append('newPlayer: true,')
//		sb.append('show_related: false,')
//		sb.append('autoplay: false')
//		sb.append('});')
//		sb.append('</script>')
//		println sb.toString()

//        def xls = new File("E:/", "123456.xls")
//        def xlsx = new File("E:/", "123456.xlsx")
//        def wb = ExcelHelper.open(xlsx)
//        def sheet = wb.getSheetAt(0)
//        for(int i=sheet.getFirstRowNum(); i<=sheet.getLastRowNum(); i++) {
//            def row = sheet.getRow(i)
//			def cell = row.getCell(0)
//			switch (cell.getCellType()) {
//				case Cell.CELL_TYPE_BOOLEAN:
//					println cell.getBooleanCellValue()
//					break
//				case Cell.CELL_TYPE_NUMERIC:
//					println cell.getNumericCellValue()
//					break
//				case Cell.CELL_TYPE_STRING:
//					println cell.getStringCellValue()
//					break
//				case Cell.CELL_TYPE_BLANK:
//					break
//				case Cell.CELL_TYPE_ERROR:
//					break
//				case Cell.CELL_TYPE_FORMULA:// CELL_TYPE_FORMULA will never happen
//					break
//			}
//        }
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
