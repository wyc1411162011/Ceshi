package com.yonyou.ceshi;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CallableDemo implements Callable<String> {
	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName()+"-启动了...");
		Thread.sleep(3000);
		return "Lisa";
	}

	public static void main(String[] args) throws Exception {
		Callable<Integer> call = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("正在计算结果...");
				Thread.sleep(3000);
				return 1;
			}
		};

		FutureTask<Integer> task = new FutureTask<>(call);

		Thread thread = new Thread(task);
		thread.start();

		// do something
		System.out.println(" 干点别的...");

		Integer result = task.get();

		System.out.println("拿到的结果为：" + result);
	}
}