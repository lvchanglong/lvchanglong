package lvchanglong

import grails.converters.JSON
import groovy.sql.Sql
import org.apache.poi.ss.usermodel.Cell
import org.apache.solr.common.SolrDocument

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional;

/**
 * 受保护方法，需登录
 */
class ProtectedController {

	static defaultAction = "index"

	/**
	 * 酱油
	 */
    def index() {
		render "受保护的"
	}

	/**
	 * 头像上传
	 */
	@Transactional
	def uploadTouXiang(String fileName) {
		withForm {
			def user = User.get(session.uid)
			if (user) {
				String realPath = servletContext.getRealPath("/")
				def imagePath = realPath + "userData/${user.id}/${fileName.trim()}"
				File file = Helper.getFile(imagePath)

				BufferedInputStream fileIn = new BufferedInputStream(request.getInputStream())
				byte[] buf = new byte[1024]
				BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(file))
				while (true) {
					int bytesIn = fileIn.read(buf, 0, 1024)
					if (bytesIn == -1) {
						break
					} else {
						fileOut.write(buf, 0, bytesIn)
					}
				}
				fileOut.flush()
				fileOut.close()

//				Helper.yaSuo(file, 180, 180)//图片压缩处理

				user.touXiang = file.getBytes()
				user.save(flush: true)//更新路径
				file.delete()//删除文件
				file.getParentFile().delete()//删除文件夹
				render status: OK
			} else {
				render status: NOT_FOUND, text: '用户不存在'
			}
		}.invalidToken {
			// bad request
		}
	}

	/**
	 * 修改用户信息
	 */
	@Transactional
	def updateUser() {
		def user = User.get(session.uid)
		if (user) {
			user.properties = params
			if(!user.save(flush: true)) {
				render status: NOT_ACCEPTABLE
				return
			}
			render status: OK, text: "修改成功"
			return
		}
		render status: BAD_REQUEST, text: "请求不合法 "
	}

	/**
	 * 发布元素
	 */
	@Transactional
	def saveElement(Element instance) {
		if (instance == null) {
			render status: NOT_FOUND
			return
		}
		instance.validate()
		if (instance.hasErrors()) {
			render status: NOT_ACCEPTABLE
			return
		}
		instance.save flush:true
		respond instance, [status: CREATED, formats:['json', 'xml']]
	}

	/**
	 * 修改元素
	 */
	@Transactional
	def updateElement(Element instance) {
		if (instance == null) {
			render status: NOT_FOUND
			return
		}
		instance.validate()
		if (instance.hasErrors()) {
			render status: NOT_ACCEPTABLE
			return
		}
		instance.save flush:true
		respond instance, [status: OK, formats:['json', 'xml']]
	}

	/**
	 * 删除元素
	 */
	@Transactional
	def deleteElement(Element instance) {
		if (instance == null) {
			render status: NOT_FOUND
			return
		}
		instance.delete flush:true
		render status: NO_CONTENT
	}

	/**
	 * 元素列表
	 */
	def elements() {
		params.max = 9
		[instanceList:Element.list(params), instanceCount:Element.count()]
	}

	//---------------------------------------------------------------------------------------------------

	/**
	 * 文件上传
	 */
	def uploadFile() {
		def f = request.getFile('file')
		if (f.empty) {
			render status: BAD_REQUEST
			return
		}
		String realPath = servletContext.getRealPath("/")
		def fileName = f.getOriginalFilename()
		def filePath = realPath + "userData/${session.uid}/${fileName}"
		def serverFile = Helper.getFile(filePath)
		f.transferTo(serverFile)
		render new HashMap(['file': serverFile.getName()]) as JSON
//		response.sendError(200, 'Done')
	}

	/**
	 * 文件列表
	 */
	def listFiles() {
		String realPath = servletContext.getRealPath("/")
		def dirPath = realPath + "userData/${session.uid}"
		def dir = Helper.getFolder(dirPath)
		render(template:"/protected/fileList", model:[files: dir.list()])
	}

	/**
	 * 删除文件
	 * @param fileName 文件名
	 */
	def deleteFile(String fileName) {
		String realPath = servletContext.getRealPath("/")
		def filePath = realPath + "userData/${session.uid}/${fileName.trim()}"
		def file = Helper.getFile(filePath)
		file.delete()
		def dir = file.getParentFile()
		render(template:"/protected/fileList", model:[files: dir.list()])
	}

}
