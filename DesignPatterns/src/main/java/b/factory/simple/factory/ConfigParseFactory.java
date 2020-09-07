package b.factory.simple.factory;

import b.factory.simple.factory.parse.IConfigParse;
import b.factory.simple.factory.parse.impl.JsonConfigParse;
import b.factory.simple.factory.parse.impl.XmlConfigParse;
import b.factory.simple.factory.parse.impl.YmlConfigParse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 愆凡
 * @date 2020/9/7 10:32 上午
 */
public class ConfigParseFactory {

	private static final Map<String, IConfigParse> configParseMap = new HashMap<>();

	static {
		configParseMap.put("json", new JsonConfigParse());
		configParseMap.put("xml", new XmlConfigParse());
		configParseMap.put("yml", new YmlConfigParse());
	}

	// 当 ConfigParse 不能复用时使用该方法获取 ConfigParse
	public static IConfigParse createParse(String type) {
		IConfigParse configParse = null;

		if ("json".equalsIgnoreCase(type)) {
			configParse = new JsonConfigParse();
		} else if ("xml".equalsIgnoreCase(type)) {
			configParse = new XmlConfigParse();
		} else if ("yml".equalsIgnoreCase(type)) {
			configParse = new YmlConfigParse();
		}

		return configParse;
	}

	// 当 ConfigParse 能复用时使用该方法获取 ConfigParse
	public static IConfigParse createParseByReuse(String type) {
		return configParseMap.get(type);
	}

}
