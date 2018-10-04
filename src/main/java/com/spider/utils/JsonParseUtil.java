package com.spider.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

/**
 * <p>
 * Title: JsonParseUtil
 * </p>
 * <p>
 * Description: json格式数据解析器
 * </p>
 * 
 * @author liyongqiang
 * @datetime 2018年10月4日 下午4:58:59
 */
public class JsonParseUtil {

	/**
	 * 根据json数组转换成list集合数据
	 * 
	 * @return
	 */
	public static List<String> getStringList(String jsonString) {
		List<String> list = new ArrayList<String>();
		String json = "[{\"Key\":\"程序合法\",\"Value\":\"360613\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":360613},{\"Key\":\"驳回\",\"Value\":\"147996\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":147996},{\"Key\":\"具体行政行为\",\"Value\":\"120101\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":120101},{\"Key\":\"第三人\",\"Value\":\"104213\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":104213},{\"Key\":\"不履行\",\"Value\":\"95921\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":95921},{\"Key\":\"催告\",\"Value\":\"58438\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":58438},{\"Key\":\"违法行为\",\"Value\":\"56763\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":56763},{\"Key\":\"法定期限\",\"Value\":\"52195\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":52195},{\"Key\":\"不予受理\",\"Value\":\"52070\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":52070},{\"Key\":\"工伤\",\"Value\":\"51955\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":51955},{\"Key\":\"利害关系\",\"Value\":\"47332\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":47332},{\"Key\":\"管辖\",\"Value\":\"47318\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":47318},{\"Key\":\"房屋征收\",\"Value\":\"40810\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":40810},{\"Key\":\"拆迁\",\"Value\":\"32836\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":32836},{\"Key\":\"所有权\",\"Value\":\"30230\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":30230},{\"Key\":\"变更\",\"Value\":\"30099\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":30099},{\"Key\":\"本案争议\",\"Value\":\"29912\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":29912},{\"Key\":\"不动产\",\"Value\":\"27003\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":27003},{\"Key\":\"合同\",\"Value\":\"25060\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":25060},{\"Key\":\"房屋拆迁\",\"Value\":\"24948\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":24948},{\"Key\":\"授权\",\"Value\":\"24701\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":24701},{\"Key\":\"履行法定职责\",\"Value\":\"21346\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":21346},{\"Key\":\"鉴定\",\"Value\":\"20995\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":20995},{\"Key\":\"公共利益\",\"Value\":\"20681\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":20681},{\"Key\":\"行政赔偿\",\"Value\":\"20677\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":20677},{\"Key\":\"土地使用权\",\"Value\":\"19519\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":19519},{\"Key\":\"建设用地\",\"Value\":\"18962\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":18962},{\"Key\":\"交通事故\",\"Value\":\"17655\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":17655},{\"Key\":\"房屋所有权\",\"Value\":\"17215\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":17215},{\"Key\":\"建设工程\",\"Value\":\"16361\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":16361},{\"Key\":\"处分\",\"Value\":\"15999\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":15999},{\"Key\":\"撤回起诉\",\"Value\":\"15081\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":15081},{\"Key\":\"行政拘留\",\"Value\":\"14794\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":14794},{\"Key\":\"土地征收\",\"Value\":\"13243\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":13243},{\"Key\":\"类似商品\",\"Value\":\"12906\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":12906},{\"Key\":\"没收\",\"Value\":\"12647\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":12647},{\"Key\":\"财产权\",\"Value\":\"12214\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":12214},{\"Key\":\"宅基地\",\"Value\":\"11847\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":11847},{\"Key\":\"给付\",\"Value\":\"11258\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":11258},{\"Key\":\"国有土地使用权\",\"Value\":\"10854\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":10854},{\"Key\":\"土地登记\",\"Value\":\"10841\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":10841},{\"Key\":\"行政强制执行\",\"Value\":\"10618\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":10618},{\"Key\":\"混淆\",\"Value\":\"9617\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":9617},{\"Key\":\"变更登记\",\"Value\":\"9405\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":9405},{\"Key\":\"动产\",\"Value\":\"9376\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":9376},{\"Key\":\"近似商标\",\"Value\":\"9131\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":9131},{\"Key\":\"驳回起诉\",\"Value\":\"9090\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":9090},{\"Key\":\"利害关系人\",\"Value\":\"8886\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":8886},{\"Key\":\"不作为\",\"Value\":\"8611\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":8611},{\"Key\":\"投资\",\"Value\":\"8190\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":8190},{\"Key\":\"确权\",\"Value\":\"8052\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":8052},{\"Key\":\"法定代表人\",\"Value\":\"7616\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":7616},{\"Key\":\"传唤\",\"Value\":\"7407\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":7407},{\"Key\":\"拆迁安置\",\"Value\":\"7112\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":7112},{\"Key\":\"交付\",\"Value\":\"6824\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":6824},{\"Key\":\"土地承包经营权\",\"Value\":\"6667\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":6667},{\"Key\":\"保险费\",\"Value\":\"6597\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":6597},{\"Key\":\"诉讼标的\",\"Value\":\"6458\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":6458},{\"Key\":\"房屋权属\",\"Value\":\"6274\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":6274},{\"Key\":\"租赁\",\"Value\":\"6086\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":6086},{\"Key\":\"强制性规定\",\"Value\":\"5789\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":5789},{\"Key\":\"滞纳金\",\"Value\":\"5767\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":5767},{\"Key\":\"公共秩序\",\"Value\":\"5765\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":5765},{\"Key\":\"劳动合同\",\"Value\":\"5751\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":5751},{\"Key\":\"意思表示真实\",\"Value\":\"5723\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":5723},{\"Key\":\"公证\",\"Value\":\"5719\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":5719},{\"Key\":\"房屋产权\",\"Value\":\"5563\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":5563},{\"Key\":\"查封\",\"Value\":\"5114\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":5114},{\"Key\":\"合法财产\",\"Value\":\"5083\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":5083},{\"Key\":\"承包经营\",\"Value\":\"5017\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":5017},{\"Key\":\"抵押\",\"Value\":\"5002\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":5002},{\"Key\":\"共有\",\"Value\":\"4996\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4996},{\"Key\":\"注册商标\",\"Value\":\"4828\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4828},{\"Key\":\"伪造\",\"Value\":\"4825\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4825},{\"Key\":\"扣押\",\"Value\":\"4820\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4820},{\"Key\":\"保证\",\"Value\":\"4775\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4775},{\"Key\":\"代理人\",\"Value\":\"4724\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4724},{\"Key\":\"委托代理人\",\"Value\":\"4722\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4722},{\"Key\":\"赔偿损失\",\"Value\":\"4659\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4659},{\"Key\":\"返还\",\"Value\":\"4598\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4598},{\"Key\":\"书面形式\",\"Value\":\"4408\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4408},{\"Key\":\"强制措施\",\"Value\":\"4307\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4307},{\"Key\":\"超越职权\",\"Value\":\"4306\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4306},{\"Key\":\"股份\",\"Value\":\"4255\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4255},{\"Key\":\"自然资源\",\"Value\":\"4228\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4228},{\"Key\":\"违法所得\",\"Value\":\"4212\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4212},{\"Key\":\"承诺\",\"Value\":\"4118\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4118},{\"Key\":\"不可抗力\",\"Value\":\"4040\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":4040},{\"Key\":\"分公司\",\"Value\":\"3991\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":3991},{\"Key\":\"债权\",\"Value\":\"3808\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":3808},{\"Key\":\"继承\",\"Value\":\"3786\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":3786},{\"Key\":\"国家赔偿\",\"Value\":\"3779\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":3779},{\"Key\":\"土地权属争议\",\"Value\":\"3776\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":3776},{\"Key\":\"基金\",\"Value\":\"3728\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":3728},{\"Key\":\"承包合同\",\"Value\":\"3677\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":3677},{\"Key\":\"股权\",\"Value\":\"3637\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":3637},{\"Key\":\"公积金\",\"Value\":\"3628\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":3628},{\"Key\":\"恢复原状\",\"Value\":\"3601\",\"Child\":[],\"parent\":\"\",\"id\":\"\",\"Field\":\"关键词\",\"SortKey\":100,\"IntValue\":3601}]";
		JSONArray parseArray = JSONArray.parseArray(jsonString);
		for (Object object : parseArray) {
			String str = object.toString();
			String keyWord = str.substring(str.indexOf(",\"Key\":\"") + 8, str.length() - 2);
			list.add("\"" + keyWord + "\"");
		}
		return list;
	}

}