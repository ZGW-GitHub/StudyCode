package b.factory.simple.factory;

import b.factory.simple.factory.parse.IConfigParse;

/**
 * @author 愆凡
 * @date 2020/9/7 10:30 上午
 */
public class ConfigSource {

	public String load(String filePath) {
		String fileType = getFileType(filePath);
		IConfigParse configParse = ConfigParseFactory.createParse(fileType);

		return configParse.doParse(filePath);
	}

	// 根据文件路径获取文件类型
	public String getFileType(String filePath) {
		return "json";
	}

}
