package b.factory.factory.method;

import b.factory.factory.method.factory.IConfigParseFactory;
import b.factory.factory.method.factory.impl.JsonConfigParseFactory;
import b.factory.factory.method.factory.impl.XmlConfigParseFactory;
import b.factory.factory.method.factory.impl.YmlConfigParseFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 这里有相当于一个简单工厂，通过该简单工厂获取 ConfigParseFactory
 *
 * @author 愆凡
 * @date 2020/9/7 11:27 上午
 */
public class ConfigParseFactoryMap {

	private static final Map<String, IConfigParseFactory> PARSE_FACTORY_MAP = new HashMap<>();

	static {
		PARSE_FACTORY_MAP.put("json", new JsonConfigParseFactory());
		PARSE_FACTORY_MAP.put("xml", new XmlConfigParseFactory());
		PARSE_FACTORY_MAP.put("yml", new YmlConfigParseFactory());
	}

	// 当 ConfigParseFactory 能复用时使用该方法获取 ConfigParseFactory
	public static IConfigParseFactory getParseFactoryByReuse(String type) {
		return PARSE_FACTORY_MAP.get(type);
	}

	// 当 ConfigParseFactory 不能复用时使用该方法获取 ConfigParseFactory
	public static IConfigParseFactory getParseFactory(String type) {
		IConfigParseFactory parseFactory = null;

		if ("json".equalsIgnoreCase(type)) {
			parseFactory = new JsonConfigParseFactory();
		} else if ("xml".equalsIgnoreCase(type)) {
			parseFactory = new XmlConfigParseFactory();
		} else if ("yml".equalsIgnoreCase(type)) {
			parseFactory = new YmlConfigParseFactory();
		}

		return parseFactory;
	}

}
