package com.servlet;

import java.io.File;
import java.util.Map;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Server {
	public static void main(String[] args) {
		// 设置日志
		setupLogging();
		// 启动Tomcat
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		tomcat.getConnector();
		// 创建WebApp
		Context context = tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());

		WebResourceRoot webroot = new StandardRoot(context);
		webroot.addPreResources(
				new DirResourceSet(webroot, "/WEB-INF/classes", new File("target/classes").getAbsolutePath(), "/"));

		context.setResources(webroot);

		try {
			tomcat.start();
			tomcat.getServer().await();
		} catch (Exception exp) {
			exp.printStackTrace();
		}

	}

	private static void setupLogging() {
		File logDir = new File("logs");
		if (!logDir.exists()) {
			logDir.mkdirs();
		}

		Map.of("org.slf4j.simpleLogger.logFile", "logs/myapp.log", "org.slf4j.simpleLogger.defaultLogLevel", "info",
				"org.slf4j.simpleLogger.showDateTime", "true", "org.slf4j.simpleLogger.dateTimeFormat",
				"yyyy-MM-dd HH:mm:ss", "org.slf4j.simpleLogger.showThreadName", "false",
				"org.slf4j.simpleLogger.showLogName", "true", "org.slf4j.simpleLogger.showShortLogName", "true")
				.forEach(System::setProperty);
	}
}
