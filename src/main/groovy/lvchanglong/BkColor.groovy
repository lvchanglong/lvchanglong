package lvchanglong

/**
 * 背景色
 *
 */
class BkColor {

	static def InstList = [
		"#1ABC9C",
		"#16A085",
		"#2ECC71",
		"#27AE60",
		"#3498DB",
		"#2980B9",
		"#9B59B6",
		"#8E44AD",
		"#34495E",
		"#2C3E50",
		"#F39C12",
		"#E67E22",
		"#D35400",
		"#E74C3C",
		"#C0392B",
		"#95A5A6",
		"#7F8C8D"
	]
	
	/**
	 * 生成颜色
	 */
	static String getInst() {
		Random random = new Random()
		int bound = InstList.size()-1//设定范围
		int idx = random.nextInt(bound)
		return InstList[idx]
	}
	
	static main(args) {
		println BkColor.getInst()
	}

}
