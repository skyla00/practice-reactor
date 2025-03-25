package com.springboot.practice;

import reactor.core.publisher.Flux;

import java.util.stream.Stream;

public class FromStreamExample {
    public static void main(String[] args) {
        // Java 의 Stream 을 입력으로 전달받아 emit 함.
        Flux.fromStream(Stream.of(200, 300, 400, 500, 600))
                //upstream 에서 emit 된 두개의 데이터를 순차적으로 누적 처리
                .reduce((a, b) -> a + b)
                //upstream에서 전달받은 두개의 숫자를 합하는 과정을 반복해 총 합계 구함.
                .subscribe(System.out::println);
    }
}
