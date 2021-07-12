package b.factory.factory.method;

import b.factory.factory.method.factory.IConfigParseFactory;
import b.factory.factory.method.parse.IConfigParse;

/**
 * @author 愆凡
 * @date 2020/9/7 10:30 上午
 */
public class ConfigSource {

	public String load(String filePath) {
		String fileType = getFileType(filePath);

		// 获取工厂
		IConfigParseFactory parseFactory = ConfigParseFactoryMap.getParseFactoryByReuse(fileType);
		// 根据工厂获取 parse
		IConfigParse parse = parseFactory.createParse();

		return parse.doParse(filePath);
	}

	// 根据文件路径获取文件类型
	public String getFileType(String filePath) {
		return "json";
	}

}
