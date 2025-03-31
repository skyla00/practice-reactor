package com.springboot.practice;

import com.springboot.practice.data.Coffee;
import com.springboot.practice.data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class DoOnNextExample {
    public static void main(String[] args) {
        // 데이터 emit 시 트리거. 부수효과를 추가할 수 있는 Operator
        // 리턴 값 없음.
        Flux
                .fromIterable(SampleData.coffeeList)
                // 데이터 유효성 검증.
                .doOnNext(coffee -> validateCoffee(coffee))
                .subscribe(data -> log.info("{} : {}", data.getKoreanName(), data.getPrice()));
    }

    private static void validateCoffee(Coffee coffee) {
        if (coffee == null) {
            throw new RuntimeException("Not found coffee");
        }
        // TODO 유효성 검증에 필요한 로직을 필요한 만큼 추가할 수 있습니다.
    }
}
