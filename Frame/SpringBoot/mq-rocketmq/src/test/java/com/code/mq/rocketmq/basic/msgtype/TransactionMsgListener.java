package com.code.mq.rocketmq.basic.msgtype;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * 监听事务消息，执行本地事务
 * 
 * @author 愆凡
 * @date 2021/3/20 14:04
 */
@RocketMQTransactionListener
public class TransactionMsgListener implements RocketMQLocalTransactionListener {
	/**
	 * 监听到事务消息后会调用该方法执行 Producer 的本地事务
	 *
	 * @param msg 监听到的事务消息
	 * @param arg 事务消息携带的额外参数
	 * @return 本地事务的执行结果：rollback 、commit 、unknown
	 */
	@Override
	public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
		System.err.println("执行本地事务，接收到的消息：" + msg + " arg：" + arg + "");

		return RocketMQLocalTransactionState.UNKNOWN;
	}

	/**
	 * 回查本地事务，实现方式：<br/>
	 *  {@link #executeLocalTransaction(Message, Object)} 方法在执行本地事务前可以记录 msg 的事务编号、事务状态(unknown) 存储到数据库<br/>
	 * 	当本地事务执行成功，将数据库中的事务状态更新为：commit<br/>
	 * 	当本地事务执行失败，将数据库中的事务状态更新为：rollback
	 *
	 * @param msg 事务消息
	 * @return 回查结果：rollback 、commit 、unknown
	 */
	@Override
	public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
		System.err.println("回查消息：" + msg);

		// 
		return RocketMQLocalTransactionState.COMMIT;
	}
}
