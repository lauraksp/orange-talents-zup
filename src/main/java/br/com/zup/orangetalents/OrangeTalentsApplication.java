package br.com.zup.orangetalents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrangeTalentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrangeTalentsApplication.class, args);
	}

}
