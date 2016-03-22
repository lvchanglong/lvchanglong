package lvchanglong

class Test {

	static main(args) {
		def tarFile = new File("E:/", "tar.txt")
		tarFile.withWriter("UTF-8") {writer->
			def file = new File("E:/", "新建文本文档.txt")
			file.readLines("UTF-8").each {str->
				if(str) {
					if(str.indexOf("编辑源代码") == -1) {
						writer.write(str)
					} else {
					str = str.replaceAll("\\[编辑 \\| 编辑源代码\\]", "")
						writer.write("\n" + "##" + str + "\n")
					} 
				}
			}
		}
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
	
	def b() {
		def tarFile = new File("E:/", "tar.txt")
		tarFile.withWriter("UTF-8") {writer->
			def file = new File("E:/", "新建文本文档.txt")
			file.readLines("UTF-8").each {str->
				if(str) {
					if(str.indexOf("编辑源代码") == -1) {
						writer.write("'"+str.trim()+"',")
					} else {
						writer.write("\n"+str+"\n")
					}
				}
			}
		}
	}
	
	def c() {
		def tarFile = new File("E:/", "tar.txt")
		tarFile.withWriter("UTF-8") {writer->
			def file = new File("E:/", "新建文本文档.txt")
			file.readLines("UTF-8").each {str->
				if(str) {
					if(str.indexOf("编辑源代码") == -1) {
						str = str.substring(0, str.length()-1)
						writer.write(str)
					} else {
						writer.write("\n" + str + "\n")
					}
				}
			}
		}
	}
	
	def d() {
		def tarFile = new File("E:/", "tar.txt")
		tarFile.withWriter("UTF-8") {writer->
			def file = new File("E:/", "新建文本文档.txt")
			file.readLines("UTF-8").each {str->
				if(str) {
					if(str.indexOf("编辑源代码") == -1) {
						writer.write(str)
					} else {
					str = str.replaceAll("\\[编辑 \\| 编辑源代码\\]", "")
						writer.write("\n" + "##" + str + "\n")
					}
				}
			}
		}
	}

}
