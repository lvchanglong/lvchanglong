package lvchanglong

/**
 * 背景色
 *
 */
class BkColor {

	static def InstList = [
		"#359F6F",
		"#3EA578",
		"#F7A901",
		"#F9AF00",
		"#695A45",
		"#6D5E49",
		"#1EABBF",
		"#26B1C4",
		"#568ed6",
		"#8D1189",
		"#9F3694",
		"#1F1F1F",
		"#2D2D2D",
		"#489D76",
		"#2C825B",
		"#2A5DB6",
		"#4378D4",
		"#3770bf",
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
		"#B0D62C",
		"#62B3FF",
		"#73A8DE",
		"#B385BD",
		"#9AC600",
		"#666666",
		"#515666",
		"#E17B31",
		"#31C1D8",
		"#E4C622",
		"#191919",
		"#6D3353",
		"#605E5E",
		"#1FA67A",
		"#1B5E9B",
		"#1E2B33",
		"#2489C5",
		"#4ECDC4",
		"#E48632",
		"#2B9646",
		"#9D261D",
		"#1ABC9C",
		"#46BFBD",
		"#F0DB4F",
		"#353131",
		"#10131D",
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
