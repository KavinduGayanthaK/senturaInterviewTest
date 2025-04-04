package lk.ijse.senturaInterviewTest.senturaInterviewTest;

import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SenturaInterviewTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SenturaInterviewTestApplication.class, args);
	}

	@Bean
	OkHttpClient okHttpClient(){
		return new OkHttpClient();
	}
}
