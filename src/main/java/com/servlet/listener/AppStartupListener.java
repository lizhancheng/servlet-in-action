package com.servlet.listener;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppStartupListener implements ServletContextListener {
	// 模拟应用配置
    private static final String APP_NAME = "MyWebApp";
    private static final String VERSION = "1.0.0";

    // 定时任务执行器（用于演示后台任务）
    private ScheduledExecutorService scheduler;
    
    // 应用启动时的计数器
    private int startupCount = 0;
    
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	// 记录应用启动信息
    	String startupTime = LocalDateTime.now().toString();
    	startupCount ++;
    	
    	System.out.println("========================================");
    	System.out.println(APP_NAME + " v" + VERSION + " 正在启动...");
    	System.out.println("启动时间：" + startupTime);
    	System.out.println("启动次数：" + startupCount);
    	System.out.println("========================================");
    	
    	// 2. 初始化应用配置（模拟从数据库或配置文件加载）
    	initializeAppConfig(servletContextEvent);
    	
    	// 3. 启动后台任务（如定时清理、监控等）
    	startBackgrounTasks(servletContextEvent);
    	
    	servletContextEvent.getServletContext().setAttribute("appName", APP_NAME);
    	servletContextEvent.getServletContext().setAttribute("version", VERSION);
    	servletContextEvent.getServletContext().setAttribute("startupTime", startupTime);
    	
    	System.out.println("应用启动完成！");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	System.out.println("========================================");
    	System.out.println(APP_NAME + "正在关闭...");
    	System.out.println("运行时长：模拟计算中...");
    	System.out.println("========================================");
    	
    	// 停止所有后台任务
    	stopBackgroundTasks();
    	
    	// 清理资源（模拟）
    	cleanupResources();
    	
    	// 移除应用属性
    	servletContextEvent.getServletContext().removeAttribute("appName");
    	servletContextEvent.getServletContext().removeAttribute("version");
    	servletContextEvent.getServletContext().removeAttribute("startupTime");
    	
    	System.out.println("应用已安全关闭！");
    }
    
    /**
     * 模拟初始化应用配置
     */
    private void initializeAppConfig(ServletContextEvent servletContextEvent) {
    	// 这里可以是读取配置文件、数据库连接池初始化等操作
    	System.out.println("初始化应用配置...");
    	
    	// 模拟设置一些配置参数
    	servletContextEvent.getServletContext().setAttribute("maxUsers", 1000);
    	servletContextEvent.getServletContext().setAttribute("sessionTimeout", 1800);
    	
    	System.out.println(" - 最大用户数：1000");
    	System.out.println(" - 会话超时：1800秒");
    	System.out.println("配置初始化完成！");
    }
    
    /**
     * 启动后台任务
     */
    private void startBackgrounTasks(ServletContextEvent servletContextEvent) {
    	System.out.println("启动后台任务...");
    	
    	// 创建定时任务线程池
    	scheduler = Executors.newScheduledThreadPool(1);
    	
    	// 延迟1分钟开始，之后每分钟执行一次
    	scheduler.scheduleAtFixedRate(() -> {
    		System.out.println("[后台任务] 应用运行中... 当前时间：" + LocalDateTime.now().toString().substring(11, 19));
    	}, 1, 1, TimeUnit.MINUTES);
    	
    	System.out.println("后台任务已启动！");
    }
    
    /**
     * 停止后台任务
     */
    private void stopBackgroundTasks() {
    	System.out.println("停止后台任务...");
    	
    	if (scheduler != null && !scheduler.isShutdown()) {
    		scheduler.shutdown();
    		try {
    			if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
    				scheduler.shutdownNow();
    			}
    		} catch (InterruptedException e) {
    			scheduler.shutdownNow();
    			Thread.currentThread().interrupt();
			}
    		System.out.println("后台任务已停止！");
    	}
    }
    
    private void cleanupResources() {
    	System.out.println("清理应用资源...");
    	// 这里可以关闭数据库连接、释放文件句柄等
    	System.out.println("资源清理完成！");
    }
}
