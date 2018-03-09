package com.xonro.vflow.wxpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xonro.vflow"})
public class WxpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxpayApplication.class, args);
	}
}
