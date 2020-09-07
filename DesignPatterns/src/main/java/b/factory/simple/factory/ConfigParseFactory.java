package b.factory.simple.factory;

/**
 * @author 愆凡
 * @date 2020/9/7 10:32 上午
 */
public class ConfigParseFactory {

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

}
