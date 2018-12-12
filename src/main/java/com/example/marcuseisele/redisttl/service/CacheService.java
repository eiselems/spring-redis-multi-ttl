package com.example.marcuseisele.redisttl.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Cacheable(value = "Cache1", key = "'5seconds_'.concat(#value)")
    public String cacheProtected5Seconds(String value) {
        System.out.println("Got called with value: " + value);
        return value;
    }

    @Cacheable(value = "Cache2", key = "'10seconds_'.concat(#value)")
    public String cacheProtected10Seconds(String value) {
        System.out.println("10s protected called: " + value);
        return value;
    }

    @Cacheable(value = "SomeOtherCacheWithDefaultConfig", key = "'default_'.concat(#value)")
    public String cacheProtectedDefault(String value) {
        System.out.println("Default protected called: " + value);
        return value;
    }
}
