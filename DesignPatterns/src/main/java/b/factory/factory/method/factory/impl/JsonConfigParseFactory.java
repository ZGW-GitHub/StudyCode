package b.factory.factory.method.factory.impl;

import b.factory.factory.method.factory.IConfigParseFactory;
import b.factory.factory.method.parse.IConfigParse;
import b.factory.factory.method.parse.impl.JsonConfigParse;

/**
 * @author 愆凡
 * @date 2020/9/7 11:19 上午
 */
public class JsonConfigParseFactory implements IConfigParseFactory {

	@Override
	public IConfigParse createParse() {
		return new JsonConfigParse();
	}

}
