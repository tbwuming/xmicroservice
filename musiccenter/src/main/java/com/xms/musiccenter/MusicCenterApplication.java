package com.xms.musiccenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xms.musiccenter.mapper")
public class MusicCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicCenterApplication.class, args);
	}
}
