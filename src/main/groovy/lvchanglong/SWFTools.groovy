package lvchanglong

/**
 * Created by lvchanglong on 2017/2/15.
 */
class SWFTools {
    String PDF2SWF_EXE = "E:/Program Files (x86)/SWFTools/pdf2swf.exe"

    /**
     * 转化为pdf
     * @param inputFile
     * @return
     */
    File convert2swf(File inputFile = new File("F:\\2pdf", "中期检查.pdf")) {
        if(inputFile.exists()) {
            File outputFile = new File(inputFile.getPath() + ".swf")
            try {
                this.convert(inputFile, outputFile)
            } catch (Exception e) {
                e.printStackTrace()
                return null
            }
            return outputFile
        }
        return null
    }

    /**
     * 文件格式转化
     * @param inputFile
     * @param outputFile
     * @return
     */
    void convert(File inputFile = new File("F:\\2pdf", "中期检查.pdf"), File outputFile = new File("F:\\2pdf", "中期检查.swf")) {
        String command = PDF2SWF_EXE + " ${inputFile.getPath()} -o ${outputFile.getPath()}"
        Runtime.getRuntime().exec(command)//创建进程
    }

}
