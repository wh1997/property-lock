package com.tianjian.property;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableCaching     //开启Ehcache缓存支持
@SpringBootApplication
@ServletComponentScan
@EnableDiscoveryClient      //eureka客户端
public class LockApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder lockApplication){
//        // -----开始手动设置配置文件加载路径
//        // 原始加载路径
//        String contextPath = "spring.config.additional-location:classpath:/,classpath:/config/,file:./,file:./config/";
//        // 自定义路径,tomcat目录下的resource目录
//        contextPath += "," + System.getProperty("catalina.home") + "/resources/";
//        System.out.println("项目路径：" + contextPath);
//        // 设置文件加载路径及文件默认名称
//        lockApplication.properties("spring.config.name:application", contextPath);
//        // -----手动设置配置文件结束，本地运行时请注释

    return lockApplication.sources(LockApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(LockApplication.class, args);
  }
}

