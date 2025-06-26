package org.example.xiaomibms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class XiaomiBmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaomiBmsApplication.class, args);
    }

}
