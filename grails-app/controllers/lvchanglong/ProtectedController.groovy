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

	def dataSource

	static defaultAction = "index"

	/**
	 * 酱油
	 */
    def index() {
		render "受保护的"
	}

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

	/**
	 * 实体列表(页面)
	 */
	def listShiTi() {
		try {
			def dangQianYongHu = YongHu.get(session.uid)

			def criteria = ShiTi.where {
				if(dangQianYongHu) {
					yongHu {
						id == dangQianYongHu.id
					}
				}
			}
			params.max = 1
			params.sort = 'id'
			params.order = 'desc'

			return [shiTiList:criteria.list(params), shiTiCount:criteria.count(), dangQianYongHu:dangQianYongHu]
		} catch(Exception e) {

		}
	}

	/**
	 * 发布实体
	 */
	@Transactional
	def saveShiTi(ShiTi shiTi) {
		if (shiTi == null) {
			render status: NOT_FOUND
			return
		}

		shiTi.validate()
		if (shiTi.hasErrors()) {
			render status: NOT_ACCEPTABLE
			return
		}

		shiTi.save flush:true
		respond shiTi, [status: CREATED, formats:['json', 'xml']]
	}

	/**
	 * 实体修改(页面)
	 * @param shiTi
	 */
	def editShiTi(ShiTi shiTi) {
		def dangQianYongHu = YongHu.get(session.uid)

		if (shiTi == null) {
			render status: NOT_FOUND
			return
		}
		[shiTi:shiTi, dangQianYongHu:dangQianYongHu]
	}

	/**
	 * 修改实体
	 */
	@Transactional
	def updateShiTi(ShiTi shiTi) {
		if (shiTi == null) {
			render status: NOT_FOUND
			return
		}

		shiTi.validate()
		if (shiTi.hasErrors()) {
			render status: NOT_ACCEPTABLE
			return
		}

		shiTi.save flush:true
		respond shiTi, [status: OK, formats:['json', 'xml']]
	}

	/**
	 * 删除实体
	 */
	@Transactional
	def deleteShiTi(ShiTi shiTi) {
		if (shiTi == null) {
			render status: NOT_FOUND
			return
		}

		shiTi.delete flush:true
		render status: NO_CONTENT
	}

	/**
	 * 修改密码(服务)
	 */
	@Transactional
	def miMaXiuGai(String yuanMiMa, String xinMiMa, String queRenMiMa) {
		def dangQianYongHu = YongHu.get(session.uid)
		if (dangQianYongHu && yuanMiMa && xinMiMa && queRenMiMa) {
			if (xinMiMa == queRenMiMa) {//确认密码一致性
				def md5 = (dangQianYongHu.xingMing + yuanMiMa).encodeAsMD5()
				if (dangQianYongHu.miMa == md5) {//原始密码验证
					dangQianYongHu.miMa = (dangQianYongHu.xingMing + xinMiMa).encodeAsMD5() //更新密码
					dangQianYongHu.save(flush: true)
					render status: OK, text: "修改成功"
					return
				}
				render status: UNAUTHORIZED, text: "原密码有误"
				return
			}
			render status: NOT_ACCEPTABLE, text: "新密码不一致"
			return
		}
		render status: BAD_REQUEST, text: "请求不合法 "
	}
	
	/**
	 * 头像上传(服务)
	 */
	@Transactional
	def touXiangShangChuan(String fileName) {
		withForm {
			def dangQianYongHu = YongHu.get(session.uid)
			if (dangQianYongHu) {
				String realPath = servletContext.getRealPath("/")
				def imagePath = realPath + "userData/${dangQianYongHu.id}/${fileName.trim()}"
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
				
				dangQianYongHu.touXiang = file.getBytes()
				dangQianYongHu.save(flush: true)//更新路径
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
	
}
