package lvchanglong

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File;
import java.util.Calendar;

import javax.imageio.ImageIO

/**
 * 帮助工具
 * @author 吕常龙
 *
 */
class Helper {

	/**
	 * 成功状态
	 * 200 (OK/正常) OK
	 * 201 (Created/已创建) CREATED
	 * 204 (No Content/无内容) NO_CONTENT
	 * 失败状态
	 * 400 (Bad Request/错误请求) BAD_REQUEST
	 * 401 (Unauthorized/未授权) UNAUTHORIZED
	 * 404 (Not Found/未找到) NOT_FOUND
	 * 406 (Not Acceptable/无法访问) NOT_ACCEPTABLE
	 * 409 (Conflict/冲突) CONFLICT
	 * 500 (Internal Server Error/内部服务器错误) INTERNAL_SERVER_ERROR
	 */
	
	/**
	 * 通过年获得生肖
	 * @param nian 年
	 */
	static HashMap getShengXiao(Integer nian) {
		switch(nian % 12) {
			case 0:
				return new HashMap("zhongWen":"猴", "pinYin":"hou")
			case 1:
				return new HashMap("zhongWen":"鸡", "pinYin":"ji")
			case 2:
				return new HashMap("zhongWen":"狗", "pinYin":"gou")
			case 3:
				return new HashMap("zhongWen":"猪", "pinYin":"zhu")
			case 4:
				return new HashMap("zhongWen":"鼠", "pinYin":"shu")
			case 5:
				return new HashMap("zhongWen":"牛", "pinYin":"niu")
			case 6:
				return new HashMap("zhongWen":"虎", "pinYin":"hu")
			case 7:
				return new HashMap("zhongWen":"兔", "pinYin":"tu")
			case 8:
				return new HashMap("zhongWen":"龙", "pinYin":"long")
			case 9:
				return new HashMap("zhongWen":"蛇", "pinYin":"she")
			case 10:
				return new HashMap("zhongWen":"马", "pinYin":"ma")
			case 11:
				return new HashMap("zhongWen":"羊", "pinYin":"yang")
		}
	}
	
	/**
	 * 通过文件名获得文件类型
	 * @param fileName 文件名
	 */
	static String getFileType(String fileName) {
        def splits = fileName.split(/\./)
		return splits[splits.length - 1]
	}
	
	/**
	 * 强制获得文件夹
	 * @param path 文件夹路径
	 */
	static File getFolder(String path) {
		File folder = new File(path)
		if(false == folder.exists()) {
			folder.mkdirs()
		}
		return folder
	}
	
	/**
	 * 强制获得文件
	 * @param path 文件路径
	 */
	static File getFile(String path) {
		File file = new File(path)
		this.getFolder(file.getParent())//确保文件夹存在
		return file
	}
	
	/**
	 * 年
	 */
	static def getNian() {
		Calendar calendar = Calendar.getInstance()
		return calendar.get(Calendar.YEAR)
	}
	
	/**
	 * 月
	 */
	static def getYue() {
		Calendar calendar = Calendar.getInstance()
		return calendar.get(Calendar.MONTH)
	}
	
	/**
	 * 日
	 */
	static def getRi() {
		Calendar calendar = Calendar.getInstance()
		return calendar.get(Calendar.DAY_OF_MONTH)
	}
	
	/**
	 * 星期
	 */
	static def getXingQi() {
		Calendar calendar = Calendar.getInstance()
		switch(calendar.get(Calendar.DAY_OF_WEEK)) {
			case 1://星期天
				return "星期日"//日
			case 2://星期一
				return "星期一"//月
			case 3://星期二
				return "星期二"//火
			case 4://星期三
				return "星期三"//水
			case 5://星期四
				return "星期四"//木
			case 6://星期五
				return "星期五"//金
			case 7://星期六
				return "星期六"//土
		}
	}
	
	/**
	 * 图片压缩
	 * @param fileSrc 原始图片
	 * @param fileTar 目标图片
	 * @param widthTar 目标宽度（"0":自适应）
	 * @param heightTar 目标高度（"0":自适应）
	 */
	static def yaSuo(File fileSrc, File fileTar, Integer widthTar, Integer heightTar) {
		if(fileSrc.exists()) {
			BufferedImage biSrc = ImageIO.read(fileSrc)
			Integer widthSrc = biSrc.getWidth()//原图宽度
			Integer heightSrc = biSrc.getHeight()//原图高度
			
			if(widthTar == 0) {//宽度自适应(高度固定)
				def rate = heightSrc / heightTar
				widthTar = widthSrc / rate
			} else if (heightTar == 0) {//高度自适应(宽度自适)
				def rate = widthSrc / widthTar
				heightTar = heightSrc / rate
			}
			
			Image scaledImage = biSrc.getScaledInstance(widthTar, heightTar, Image.SCALE_SMOOTH)//原图压缩
			BufferedImage biTar = new BufferedImage(widthTar, heightTar, BufferedImage.TYPE_INT_RGB)
			biTar.getGraphics().drawImage(scaledImage, 0, 0, null)

			ImageIO.write(biTar, this.getFileType(fileTar.getName()), fileTar)
		}
	}
	
	/**
	 * 图片压缩(自身减肥)
	 * @param file
	 * @param widthTar 目标宽度（0/自适应）
	 * @param heightTar 目标高度（0/自适应）
	 */
	static def yaSuo(File file, Integer widthTar, Integer heightTar) {
		this.yaSuo(file, file, widthTar, heightTar)
	}

	/**
	 * 随机获取
	 * @param array
	 * @return
     */
	static def random(def array) {
		if(array) {
			def sz = array.size()
			if(sz == 1) {
				return array[0]
			}
			Random random = new Random(System.currentTimeMillis())
			int bound = array.size()-1//设定范围
			int idx = random.nextInt(bound)
			return array[idx]
		}
		return null
	}
	
	static main(args) {
		println "随机获取：" + this.random(new ArrayList())
//		println "随机获取：" + this.random(["H", "E", "L", "L", "O"])
//		println "生肖属相：" + this.getShengXiao(1988)
//		println "文件类型：" + this.getFileType("fileName.txt")
//		println "宽度800，高度自适：" + this.yaSuo(new File("C:\\Users\\lvchanglong\\Desktop\\素材\\左手.jpg"), 800, 0)
//		println "文件名：" + this.getFile("C:\\Users\\admin\\Desktop\\新建文本文档.txt").getName()
//		println "星期几：" + this.getXingQi()
		
	}

}
