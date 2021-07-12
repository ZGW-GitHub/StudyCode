package com.code.plus.tools.btrace;

import org.openjdk.btrace.core.BTraceUtils;
import org.openjdk.btrace.core.annotations.*;

/**
 * 运行 BTrace 脚本 : ./bin/btrace pid /xxx/BtraceDemo.java
 * 
 * @author 愆凡
 * @date 2021/7/12 11:59
 */
@BTrace
public class BtraceDemo {
	
	@OnMethod(clazz = "com.code.plus.tools.btrace.RunnerDemo", 
			method = "sleep", 
			location = @Location(Kind.RETURN))
	public static void test(@Return long result, @Duration long time, long arg1) {
		BTraceUtils.print("arg1 : " + arg1);
		BTraceUtils.print("result : " + result);
		BTraceUtils.print("time : " + time);
	}
	
}
