package lvchanglong

/**
 * 中文名
 *
 */
class ChineseName {

	/**
	 * 百家姓
	 */
//	static def XingList = [
//		"赵","钱","孙","李","周","吴","郑","王","冯","陈","褚","卫","蒋","沈","韩","杨","朱","秦","尤","许",
//		"何","吕","施","张","孔","曹","严","华","金","魏","陶","姜","戚","谢","邹","喻","柏","水","窦","章","云","苏","潘","葛","奚","范","彭","郎",
//		"鲁","韦","昌","马","苗","凤","花","方","俞","任","袁","柳","酆","鲍","史","唐","费","廉","岑","薛","雷","贺","倪","汤","滕","殷",
//		"罗","毕","郝","邬","安","常","乐","于","时","傅","皮","卞","齐","康","伍","余","元","卜","顾","孟","平","黄","和",
//		"穆","萧","尹","姚","邵","湛","汪","祁","毛","禹","狄","米","贝","明","臧","计","伏","成","戴","谈","宋","茅","庞","熊","纪","舒",
//		"屈","项","祝","董","梁","杜","阮","蓝","闵","席","季","麻","强","贾","路","娄","危","江","童","颜","郭","梅","盛","林","刁","钟",
//		"徐","邱","骆","高","夏","蔡","田","樊","胡","凌","霍","虞","万","支","柯","昝","管","卢","莫","经","房","裘","缪","干","解","应",
//		"宗","丁","宣","贲","邓","郁","单","杭","洪","包","诸","左","石","崔","吉","钮","龚","程","嵇","邢","滑","裴","陆","荣","翁","荀",
//		"羊","于","惠","甄","曲","家","封","芮","羿","储","靳","汲","邴","糜","松","井","段","富","巫","乌","焦","巴","弓","牧","隗","山",
//		"谷","车","侯","宓","蓬","全","郗","班","仰","秋","仲","伊","宫","宁","仇","栾","暴","甘","钭","厉","戎","祖","武","符","刘","景",
//		"詹","束","龙","叶","幸","司","韶","郜","黎","蓟","溥","印","宿","白","怀","蒲","邰","从","鄂","索","咸","籍","赖","卓","蔺","屠",
//		"蒙","池","乔","阴","郁","胥","能","苍","双","闻","莘","党","翟","谭","贡","劳","逄","姬","申","扶","堵","冉","宰","郦","雍","却",
//		"璩","桑","桂","濮","牛","寿","通","边","扈","燕","冀","浦","尚","农","温","别","庄","晏","柴","瞿","阎","充","慕","连","茹","习",
//		"宦","艾","鱼","容","向","古","易","慎","戈","廖","庾","终","暨","居","衡","步","都","耿","满","弘","匡","国","文","寇","广","禄",
//		"阙","东","欧","殳","沃","利","蔚","越","夔","隆","师","巩","厍","聂","晁","勾","敖","融","冷","訾","辛","阚","那","简","饶","空",
//		"曾","毋","沙","乜","养","鞠","须","丰","巢","关","蒯","相","查","后","荆","红","游","郏","竺","权","逯","盖","益","桓","公","仉",
//		"督","岳","帅","缑","亢","况","郈","有","琴","归","海","晋","楚","闫","法","汝","鄢","涂","钦","商","牟","佘","佴","伯","赏","墨",
//		"哈","谯","篁","年","爱","阳","佟","言","福","南","火","铁","迟","漆","官","冼","真","展","繁","檀","祭","密","敬","揭","舜","楼",
//		"疏","冒","浑","挚","胶","随","高","皋","原","种","练","弥","仓","眭","蹇","覃","阿","门","恽","来","綦","召","仪","风","介","巨",
//		"木","京","狐","郇","虎","枚","抗","达","杞","苌","折","麦","庆","过","竹","端","鲜","皇","亓","老","是","秘","畅","邝","还","宾",
//		"闾","辜","纵","侴","万俟","司马","上官","欧阳","夏侯","诸葛","闻人","东方","赫连","皇甫","羊舌","尉迟","公羊","澹台","公冶","宗正",
//		"濮阳","淳于","单于","太叔","申屠","公孙","仲孙","轩辕","令狐","钟离","宇文","长孙","慕容","鲜于","闾丘","司徒","司空","兀官","司寇",
//		"南门","呼延","子车","颛孙","端木","巫马","公西","漆雕","车正","壤驷","公良","拓跋","夹谷","宰父","谷梁","段干","百里","东郭","微生",
//		"梁丘","左丘","东门","西门","南宫","第五","公仪","公乘","太史","仲长","叔孙","屈突","尔朱","东乡","相里","胡母","司城","张廖","雍门",
//		"毋丘","贺兰","綦毋","屋庐","独孤","南郭","北宫","王孙"
//	]
	
	static def XingList = [
		"赵","钱","孙","李","周","吴","郑","王","冯","陈","卫","蒋","沈","韩","杨","朱","尤","许",
		"何","吕","施","张","孔","曹","严","华","金","魏","陶","姜","云","苏","潘","葛","范","彭",
		"鲁","韦","马","苗","花","方","俞","任","袁","柳","史","唐","费","廉","薛","雷","贺","殷",
		"罗","毕","常","于","时","齐","伍","余","顾","孟","平","黄",
		"萧","姚","明","宋","庞","熊","纪",
		"项","祝","董","梁","杜","蓝","席","季","贾","江","颜","郭","盛","林",
		"徐","邱","高","夏","蔡","田","胡","凌","莫",
		"邓","单","左","崔","程","陆","荣","于","车","秋","宁","刘",
		"龙","叶","白","姬","申","柴","习","墨","南","火","铁","迟","高","原","风",
		"司马","上官","欧阳","夏侯","诸葛","东方","皇甫","尉迟","澹台","公冶","宗正",
		"轩辕","令狐","钟离","宇文","慕容","司徒","司空","南门","百里","东郭",
		"西门","南宫","第五","太史","司城","独孤","南郭","北宫"
	]
	
	static def MingList = [
		"异","治","瑾","然","才","纪","瑾","元逊","子瑜","季文","君理","子范","泰","幼平","瑜","公瑾","承","子布",
		"达","子纲","温","尚","白","忠","盛","文则","景","范","永元","子义","昭","越","英","休","子烈","秀",
		"希","松","公礼","谦","子智","子远","幼台 ","静","子孝","和","元宗","皓","奋","封","伯符","策","文台","坚",
		"子明","蒙","范","霸","玄","士仁","逊","伯言","公纪","机","宏","肃",
		"子敬","统","永先","义公","子默","子直","宁",
		"兴霸","京","云","群","仲直","瑛","伯岐","仪","文休","慈","子远","平","文仪","公佑",
		"士元","良","义","雅","奉孝","武","恢","敬哀","来敏",
		"伯约","彦英","彦英","正","微","芝","和","琼","方","季然","士仁","孝仁",
		"仓慈","灵","风","建平","元常","毅","会","伯然","华","艾","涣","义山","修","文若","适",
		"仪","奕","宣","公明","妙才","元让","文休","思","元","怡","芝",
		"嘉","子异","会","通","公至","柔","文惠 ","志","玉","宇",
		"熙","协","符仲","子桓","仁","子孝","礼","洪",
		"芳","冲","兰卿","纯","子修","初","仓舒","秀","真"
	]
	
	/**
	 * 生成姓氏
	 */
	static String getXing() {
		Random random = new Random(System.currentTimeMillis())
		int bound = XingList.size()-1//设定范围
		int idx = random.nextInt(bound)
		return XingList[idx]
	}
	
	/**
	 * 生成名字
	 */
	static String getMing() {
		Random random = new Random(System.currentTimeMillis())
		int bound = MingList.size()-1//设定范围
		int idx = random.nextInt(bound)
		return MingList[idx]
	}
	
	/**
	 * 生成姓名
	 */
	static String getInst() {
		return ChineseName.getXing() + ChineseName.getMing()
	}
	
	static main(args) {
//		println ChineseName.getXing()
//		println ChineseName.getMing()
		println ChineseName.getInst()
	}

}