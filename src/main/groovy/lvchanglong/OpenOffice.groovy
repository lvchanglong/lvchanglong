package lvchanglong

import com.artofsolving.jodconverter.DocumentConverter
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter

/**
 * Created by lvchanglong on 2017/2/14.
 */
class OpenOffice {

    /**
     * 转化为pdf
     * @param inputFile
     * @return
     */
    File convert2pdf(File inputFile = new File("F:\\2pdf", "中期检查.doc")) {
        if(inputFile.exists()) {
            File outputFile = new File(inputFile.getPath() + ".pdf")
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
    void convert(File inputFile = new File("F:\\2pdf", "中期检查.doc"), File outputFile = new File("F:\\2pdf", "中期检查.pdf")) {
        // connect to an OpenOffice.org instance running on port 8100
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100)
        connection.connect()

        // convert
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection)
        converter.convert(inputFile, outputFile)

        // close the connection
        connection.disconnect()
    }

    //---------------------------------------------------------------------------------------------------------------

    static String OPEN_OFFICE_HOME = "C:/Program Files (x86)/OpenOffice 4/program"
    static Process process = null

    /**
     * 启动服务
     */
    static void start() {
        String command = OPEN_OFFICE_HOME + '/soffice.exe -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard'
        try {
            process = Runtime.getRuntime().exec(command)//创建进程
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    /**
     * 关闭服务
     */
    static void stop() {
        if(process) {
            process.destroy()//销毁进程(关闭服务)
        }
    }

}
