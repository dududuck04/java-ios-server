package com.cbm.saekalpi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(
//		exclude = {
//		SecurityAutoConfiguration.class, // spring Secuirty 자동 비밀번호 설정 제외
//		ManagementWebSecurityAutoConfiguration.class } // Actuator 보안 설정 제외
)
public class SaekalpiApplication {
	public static void main(String[] args) {
		SpringApplication.run(SaekalpiApplication.class, args);
	}

}

