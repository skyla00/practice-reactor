package com.springboot.practice;

import com.springboot.practice.data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FromIterableExample {
    public static void main(String[] args) {
        Flux
                // List, Map, Set의 컬랙션을 fromIterable() 의 파라미터로 전달
                .fromIterable(SampleData.coffeeList)
                .subscribe(coffee -> log.info("{} :{}",
                        coffee.getKoreanName(), coffee.getPrice()));
    }
}
