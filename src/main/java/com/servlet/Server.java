package com.servlet;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Server {
  public static void main(String[] args) {
    // 启动Tomcat
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8080);
    tomcat.getConnector();
    // 创建WebApp
    Context context = tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());
    
    WebResourceRoot webroot = new StandardRoot(context);
    webroot.addPreResources(new DirResourceSet(webroot, "/WEB-INF/classes", new File("target/classes").getAbsolutePath(), "/"));
    
    context.setResources(webroot);
    
    try {
      tomcat.start();
      tomcat.getServer().await();
    } catch (Exception exp) {
      exp.printStackTrace();
    }
    
  }
}
