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
	 * 术语添加（页面）
	 */
	def termCreate(String discipline, String source, String target, String ly) {

	}

	/**
	 * 开始添加术语
	 * @param did discipline.id
	 * @param sid source.id
	 * @param tid target.id
	 */
	def termCreating(String did, String sid, String tid, String ly) {
		if(did && sid && tid) {

		}
	}

	/**
	 * 酱油
	 */
    def index() {
		render "受保护的"
	}

	//---------------------------------------------------------------------------------------------------

	/**
	 * 基础信息（页面）
	 */
	def termCommon() {

	}

	/**
	 * 术语导入（页面）
	 * @param discipline 经济学#48
	 * @param source 中文#103
	 * @param target 英语#100
	 */
	def termImport(String discipline, String source, String target, String ly) {
		if(discipline && source && target) {
			def did = discipline.split("#")[1]
			def sid = source.split("#")[1]
			def tid = target.split("#")[1]

			def disciplineInstance = DisciplineP.get(did.trim())
			def sourceInstance = Lan.get(sid.trim())
			def targetInstance = Lan.get(tid.trim())
			return [disciplineInstance:disciplineInstance, sourceInstance:sourceInstance, targetInstance:targetInstance, ly:ly]
		}
		render status: BAD_REQUEST
	}

	/**
	 * 开始导入术语
	 * @param did discipline.id
	 * @param sid source.id
	 * @param tid target.id
	 */
	def termImporting(String did, String sid, String tid, String ly) {
		withForm {
			if(did && sid && tid) {
				def discipline = DisciplineP.get(did.trim()) //学科
				def sourceLan = Lan.get(sid.trim()) //源语言
				def targetLan = Lan.get(tid.trim()) //目标语言
				def yongHu = YongHu.get(session.uid) //当前用户

				String realPath = servletContext.getRealPath("/")
				def dirPath = realPath + "userData/${yongHu.id}"
				def dir = Helper.getFolder(dirPath)
				dir.listFiles().each {file->
					String fileType = Helper.getFileType(file.getName())

					switch(fileType.toLowerCase()){
						case "xls":
						case "xlsx":
							def wb = ExcelHelper.open(file)
							def sheet = wb.getSheetAt(0)
							def firstRowNum = sheet.getFirstRowNum()
							def lastRowNum = sheet.getLastRowNum()

							Sql sql = new Sql(dataSource)
							for(int i=firstRowNum; i<=lastRowNum; i++) {
								def row = sheet.getRow(i)
								def cellFrom = row.getCell(0)
								if(cellFrom.getCellType() != Cell.CELL_TYPE_STRING) {
									cellFrom.setCellType(Cell.CELL_TYPE_STRING)
								}
								String strTermFrom = cellFrom.getStringCellValue()

								def cellTo = row.getCell(1)
								if(cellTo.getCellType() != Cell.CELL_TYPE_STRING) {
									cellTo.setCellType(Cell.CELL_TYPE_STRING)
								}
								String strTermTo = cellTo.getStringCellValue()

								if(strTermFrom && strTermTo){
									String strTrimFrom = strTermFrom.trim()
									String strTrimTo = strTermTo.trim()

									def termFrom = null
									SolrDocument solrDocumentFrom = Term.findSolr(strTrimFrom)
									if(solrDocumentFrom) {
										termFrom = Term.get(solrDocumentFrom.id)
									} else {
										termFrom = new Term(["name":strTrimFrom, "yongHu":yongHu, "discipline":discipline, "lan":sourceLan, "termInfo":new TermInfo(["ly":ly])])
										termFrom.save(flush: true)
										/*String termFromId = Term.sqlInsert(sql, strTrimFrom, sourceLan.id, discipline.id, yongHu.id)
										TermInfo.sqlInsert(sql, termFromId, ly)
										termFrom = Term.get(termFromId)*/
									}

									def termTo = null
									SolrDocument solrDocumentTo = Term.findSolr(strTrimTo)
									if(solrDocumentTo) {
										termTo = Term.get(solrDocumentTo.id)
									} else {
										termTo = new Term(["name":strTrimTo, "yongHu":yongHu, "discipline":discipline, "lan":targetLan, "termInfo":new TermInfo(["ly":ly])])
										termTo.save(flush: true)
										/*String termToId = Term.sqlInsert(sql, strTrimTo, targetLan.id, discipline.id, yongHu.id)
										TermInfo.sqlInsert(sql, termToId, ly)
										termTo = Term.get(termToId)*/
									}

									Entry.link(termFrom, termTo) //建立关联
								}
							} //for
							sql.close()
					}

					file.delete()
				}
				render status: OK, text:"导入成功"
				return
			}
			render status: BAD_REQUEST, text:"无效请求"
			return
		}.invalidToken {
			render status: BAD_REQUEST, text:"导入进行中，请不要多次提交"
			return
		}
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
	 * 文件预览
	 * @param fileName 文件名
	 */
	def previewFile(String fileName) {
		String realPath = servletContext.getRealPath("/")
		String filePath = realPath + "userData/${session.uid}/${fileName.trim()}"
		File file = Helper.getFile(filePath)
		String fileType = Helper.getFileType(fileName)

		switch(fileType.toLowerCase()){
			case "xls":
			case "xlsx":
				def wb = ExcelHelper.open(file)
				def sheet = wb.getSheetAt(0)
				def firstRowNum = sheet.getFirstRowNum()
				def lastRowNum = Math.min(sheet.getLastRowNum(), 3)

				ArrayList arrayHM = new ArrayList()
				for(int i=firstRowNum; i<=lastRowNum; i++) {
					def row = sheet.getRow(i)
					def termFrom = row.getCell(0).getStringCellValue()
					def termTo = row.getCell(1).getStringCellValue()
					arrayHM.add(new HashMap(["from":termFrom.trim(), "to":termTo.trim()]))
				}
				render(template:"/protected/previewFile", model:['arrayHM': arrayHM])
				return
			default:
				render status: BAD_REQUEST
		}
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
