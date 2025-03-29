package com.springboot.practice;

import com.springboot.practice.data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class concatExample {
    // concat() : 문자열을 차례대로 이어 붙여 하나의 문자열로 생성.
    // Reactor의 concat() : 하나의 Sequence로 이어 붙임.
    // 붙인 Sequecne에서 시간 순서대로 데이터를 차례대로 emit.
    public static void main(String[] args) {
        Flux
                .concat(Flux.just("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                        Flux.just("Saturday", "Sunday"))
                .subscribe(System.out::println);

        Flux
                .concat(Flux.fromIterable(SampleData.salesOfCafeA),
                        Flux.fromIterable(SampleData.salesOfCafeB),
                        Flux.fromIterable(SampleData.salesOfCafeC))
                .reduce((a, b) -> a + b)
                .subscribe(data -> log.info("# total sales: {}", data));
    }

}
