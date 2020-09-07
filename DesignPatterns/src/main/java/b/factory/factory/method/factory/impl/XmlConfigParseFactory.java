package b.factory.factory.method.factory.impl;

import b.factory.factory.method.factory.IConfigParseFactory;
import b.factory.factory.method.parse.IConfigParse;
import b.factory.factory.method.parse.impl.XmlConfigParse;

/**
 * @author 愆凡
 * @date 2020/9/7 11:19 上午
 */
public class XmlConfigParseFactory implements IConfigParseFactory {

	@Override
	public IConfigParse createParse() {
		return new XmlConfigParse();
	}

}
