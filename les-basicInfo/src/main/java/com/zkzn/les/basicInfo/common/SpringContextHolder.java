package com.zkzn.les.basicInfo.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringContextHolder implements ApplicationContextAware{
	
	private static ConfigurableApplicationContext applicationContext;

	/**.
	 * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		SpringContextHolder.applicationContext =(ConfigurableApplicationContext)applicationContext;
	}
	/**.
	 * 
	 * 功能描述：验证ApplicationContext是否为空
	 * 作者：wangzhou
	 * 时间:2018年6月19日
	 */
	private static void checkApplicationContext() {
		if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtil");
        }
	}
	/**.
	 * 
	 * 功能描述：取得存储在静态变量中的ApplicationContext.
	 * 作者：wangzhou
	 * 时间:2018年6月19日
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}
	/**.
	 * 
	 * 功能描述：从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型
	 * 作者：wangzhou
	 * 时间:2018年6月19日
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}
	/**.
	 * 
	 * 功能描述：是否包含某个bean
	 * 作者：wangzhou
	 * 时间:2018年6月19日
	 * @param beanName
	 * @return
	 */
	public static boolean containsBean(String beanName){
		return applicationContext.containsBean(beanName);
	}
	/**.
	 * 
	 * 功能描述：从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 * 作者：wangzhou
	 * 时间:2018年6月19日
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		return (T) applicationContext.getBeansOfType(clazz);
	}
	
	
	
}
