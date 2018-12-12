package com.example.marcuseisele.redisttl;

import com.example.marcuseisele.redisttl.service.CacheService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableScheduling
public class RedisttlApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisttlApplication.class, args);
    }


    @Component
    @AllArgsConstructor
    public class ScheduledTask {

        private CacheService cacheService;

        @Scheduled(fixedRate = 1000)
        public void eachFiveSeconds() {
            System.out.println("Running 1 second scheduler");
            cacheService.cacheProtected5Seconds("This is it 5!");
            cacheService.cacheProtected10Seconds("This is it 10!");
            cacheService.cacheProtectedDefault("This is it default!");
        }
    }

}

