package com.lp.techDemo;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.lp.techDemo.metaq.entity.MetaqDemoEntity;
import com.lp.techDemo.util.ToStringUtil;

public class DemoTest {

	@Test
	public void test() {
		int[] array = new int[]{430049,
				560113,
				840015,
				974565,
				1196092,
				1521598,
				1878782,
				2371855,
				3186150,
				4324529,
				5622884,
				7750188,
				8914282,
				9211501,
				12480134,
				14024338,
				16294490,
				18614116,
				21375682};
		long[] ta = new long[array.length];
		long current = 0l;
		for (int i = 0; i < array.length; i++){
			current += array[i];
			ta[i] = current;
		}
		System.err.println(Arrays.toString(ta));
	}

	@Test
	public void testSplit(){
//		String s = "10.151.10.135 - - [01/Sep/2015:00:05:39 +0800] \"POST /getAccInfo HTTP/1.1\" 200 368 \"-\" \"Jakarta Commons-HttpClient/3.1\" 0.008 10.151.10.93:8080 200 0.008";
//		String[] ss = s.split(" ");
//		for (String sss : ss){
//			System.err.println(sss);
//		}
//		
//		String str = "0.002";
//		System.err.println(Long.valueOf(str.replace(".", "")));
		
		BigDecimal a = new BigDecimal(4000), b=new BigDecimal(2100), 
				c=new BigDecimal(2601),d=new BigDecimal(2565),e=new BigDecimal(1000);
		int a1 = a.divide(e, RoundingMode.FLOOR).intValue(), b1=b.divide(e, RoundingMode.FLOOR).intValue(),
				c1=c.divide(e, RoundingMode.FLOOR).intValue(),d1=d.divide(e, RoundingMode.FLOOR).intValue();
		System.err.println(a1);
		System.err.println(b1);
		System.err.println(c1);
		System.err.println(d1);
		
		BigDecimal db;
		
		List<Long[]> ls = new ArrayList<Long[]>();
		Long[] l1 = new Long[4];
		l1[0] = 3l;
		Long[] l2 = new Long[5];
		l2[3] = 4l;
		ls.add(l1);
		ls.add(l2);
	}

	@Test
	public void compareWithZero(){
		BigDecimal bd = new BigDecimal(0.0000000000000000000000000001);
		System.err.println(bd.doubleValue());
	}
	
	@Test
	public void cachedTheadPoolMonitor(){
		ExecutorService es = Executors.newCachedThreadPool(new ThreadFactoryMonitor());
		for (int i = 0; i < 10; i++) {
			es.execute(new Runnable() {
				@Override
				public void run() {
					System.err.println(Thread.currentThread().getName()+"do=================================");
				}
				
			});
		}
//		for (int i = 0; i < 10; i++) {
//		es.submit(new Callable<String>() {
//			@Override
//			public String call() {
//				System.err.println(Thread.currentThread().getName()+"do=================================");
//				return "hello";
//			}
//			
//		});
//	}
	}
	@Test
	public void threadPoolMonitor() throws IOException, InterruptedException, ExecutionException{

		final ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 15, 60L, 
				TimeUnit.SECONDS, 
				new LinkedBlockingQueue<Runnable>(30), 
				new ThreadFactoryMonitor(),
				new BlockingPolicy());
		List<FutureTask<Integer>> futures = new ArrayList<>();
		new Thread(new Runnable(){
			
			@Override
			public void run() {
				while (true){
					System.err.println("thread pool number-------------------------------->" + tpe.getActiveCount());
					try {
						TimeUnit.SECONDS.sleep(1l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
			
		}).start();
		for (int i = 0; i < 50; i++) {
			Task task = new Task();
	        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
	        futures.add(futureTask);
			tpe.execute(futureTask);
		}
		
		for (int i = 0; i < futures.size(); i++) {
			FutureTask<Integer> ft = futures.get(i);
			Integer result = ft.get();
			System.err.println("result=========================="+result);
		}
		System.err.println("getActiveCount=========================="+tpe.getActiveCount());
		System.in.read();
	}
	
	private AtomicInteger ai = new AtomicInteger();
	class ThreadFactoryMonitor implements ThreadFactory {
		   public Thread newThread(Runnable r) {
		     return new Thread(r, "ThreadFactoryMonitor-"+ai.incrementAndGet());
		   }
	}
	
	class Task implements Callable<Integer>{
	    @Override
	    public Integer call() throws Exception {
	        System.out.println("子线程在进行计算");
	        TimeUnit.SECONDS.sleep(3);
	        int sum = 0;
	        Random r = new Random();
	        for(int i = 0 ; i < r.nextInt(100) ; i++)
	            sum += i;
	        return sum;
	    }
	}
	
	class BlockingPolicy implements RejectedExecutionHandler{

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			BlockingQueue<Runnable> queue = executor.getQueue();
			try {
				queue.put(r);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Test
	public void toStringTest(){
		String str = ToStringUtil.generateToString(MetaqDemoEntity.class);
		assertNotNull(str);
		System.err.println(str);
	}
}
