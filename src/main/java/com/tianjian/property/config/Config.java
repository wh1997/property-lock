package com.tianjian.property.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

/**
 * @Author LiaoQuanfeng
 * Date on 2021\6\28 0028  15:10
 * @description
 */
@Component
public class Config {
  /**
   * 自动注入RestTemplate类
   * @return
   */
  @Bean
  @LoadBalanced
  RestOperations restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

}
