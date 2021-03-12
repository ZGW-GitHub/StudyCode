package b.factory.simple.factory.parse;

/**
 * @author 愆凡
 * @date 2020/9/7 10:34 上午
 */
public interface IConfigParse {

	/**
	 * 根据文件格式解析配置文件
	 *
	 * @param type type
	 * @return str
	 */
	String doParse(String type);

}
