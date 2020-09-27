package com.code.thread.nn.threadpool.basic.ss.completable.future;

public class CompletableFuture笔记 {

	/*
	 * 构造
	 *
	 *
	 * 组合
	 *      返回值：CompletableFuture<Void>
	 *          thenAcceptBoth（ CompletionStage , v,s -> ）  接受两者【Async】
	 *          acceptEither（ CompletionStage , v -> ）      接受任何一个【Async】
	 *          runAfterBoth（ CompletionStage ,  -> ）       在两者都完成后运行【Async】
	 *          runAfterEither（ CompletionStage ,  -> ）     在其中一个完成后运行【Async】
	 *
	 *      返回值：CompletableFuture<?>
	 *          thenCombine（ CompletionStage , v,s -> R ）   然后结合【Async】
	 *          thenCompose（ v -> R ）                       撰写【Async】
	 *
	 *
	 *
	 * 中转
	 *      返回值：CompletableFuture<?>
	 *          supplyAsync（ -> R ）                 供应
	 *          whenComplete（ v,T -> ）              当完成后【Async】
	 *          thenApply（ v -> R ）                 然后应用【Async】
	 *          handle（ s,T -> R ）                  处理【Async】
	 *          toCompletableFuture（ -> R ）         return this
	 *
	 *      返回值：CompletableFuture<Void>
	 *          thenAccept（ s -> ）                  然后接受【Async】
	 *          thenRun（ -> ）                       然后运行【Async】
	 *
	 *
	 *
	 *
	 * 终结
	 *      T getNow（ T ）
	 *          如果未完成返回T
	 *      Boolean complete（ T ）
	 *          返回true，表示任务没完成，操作设置成功。所谓操作->{当后续调用 future.get() 时，若任务仍未完成则非阻塞地返回 T }。
	 *          注意：如果任务还没有开始，就调用了 complete ，任务会被 取消。
	 *      join
	 *          与 get 类似，区别：使用 join 异常被吞了，不用关心异常了。
	 *      completeExceptionally（ Throwable ）
	 *          设置之后，当任务还没完成，调用 future.get() 会抛出 Throwable
	 *      obtrudeException（ Throwable ）
	 *          设置之后，调用  future.get() 会抛出 Throwable
	 *      obtrudeValue（ T ）
	 *          设置之后，不管任务是否已经完成，调用 future.get() 返回 T
	 *      exceptionally
	 *          如果产生异常就执行该语句，否则跳过该语句。
	 *
	 *
	 *
	 */

}
