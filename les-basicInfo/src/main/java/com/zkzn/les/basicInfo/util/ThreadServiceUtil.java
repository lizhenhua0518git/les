package com.zkzn.les.basicInfo.util;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadServiceUtil {

	
	private  ExecutorService executorService = null;
	
	
	public ExecutorService getExecutorService() {
		return executorService;
	}
	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}
	/**.
	 * 
	 * 功能描述：创建一个固定连接的的线程池
	 * 作者：wangzhou
	 * 时间：2018年10月28日
	 * @return
	 */
	public  ExecutorService createService(){
		executorService = Executors.newFixedThreadPool(10);
		return executorService;
	}
	
	/**.
	 * 
	 * 功能描述：创建单列线程池
	 * 作者：wangzhou
	 * 时间：2019年3月11日
	 * @return
	 */
	public ExecutorService createSingleService(){
		executorService = Executors.newSingleThreadExecutor();
		return executorService;
	}
	/**.
	 * 
	 * 功能描述：向线程中提交一个任务
	 * 作者：wangzhou
	 * 时间：2018年10月28日
	 * @param command
	 */
	public  void addThreadSchdule(Runnable command){
		
		executorService.execute(command);
	}
	/**.
	 * 
	 * 功能描述：连接池是否关闭
	 * 作者：wangzhou
	 * 时间：2018年10月28日
	 * @return
	 */
	public boolean isTerminated() {
	    return executorService.isTerminated();
	}
	
	/**.
	 * 
	 * 功能描述：向线程中提交很多任务
	 * 作者：wangzhou
	 * 时间：2018年10月28日
	 * @param commands
	 */
	public void addThreadList(final List<Runnable> commands) {
	    for (Runnable command : commands) {
	    	executorService.execute(command);
	    }
	}
	/**.
	 * 
	 * 功能描述：关闭连接池
	 * 作者：wangzhou
	 * 时间：2018年10月28日
	 */
	public void shutDown() {
		executorService.shutdown();
	}
}
