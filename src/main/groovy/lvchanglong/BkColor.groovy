package lvchanglong

/**
 * 背景色
 *
 */
class BkColor {

	/**
	 * 备选
	 * "#4ECDC4","#2D2D2D","#4378D4","#8D1189","#9AC600","#191919","#9D261D","#1F1F1F","#3770bf","#10131D",
	 * "#B0D62C","#E4C622","#F9AF00","#F7A901","#F0DB4F","#2A5DB6",
	 */
	static def InstList = [
		"#359F6F",
		"#3EA578",
		"#695A45",
		"#6D5E49",
		"#1EABBF",
		"#26B1C4",
		"#568ed6",
		"#9F3694",
		"#489D76",
		"#2C825B",
		"#563d7c",
		"#6f5499",
		"#206F96",
		"#20242b",
		"#798088",
		"#2c313a",
		"#FF9F41",
		"#FF9BAB",
		"#5399D9",
		"#9790F3",
		"#62B3FF",
		"#73A8DE",
		"#B385BD",
		"#666666",
		"#515666",
		"#E17B31",
		"#31C1D8",
		"#6D3353",
		"#605E5E",
		"#1FA67A",
		"#1B5E9B",
		"#1E2B33",
		"#2489C5",
		"#E48632",
		"#2B9646",
		"#1ABC9C",
		"#46BFBD",
		"#353131",
		"#454853"
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
