package com.springboot.practice;

import reactor.core.publisher.Flux;

import java.util.stream.Stream;

public class LogExample {
    public static void main(String[] args) {
        // publisher 에서 발생하는 signal 이벤트를 로그로 출력해주는 역할.
        Flux
                // 데이터를 emit 할 때 request signal 이벤트가 발생함.
                // onNextSignal 이벤트 발생
                .fromStream(Stream.of(200, 300, 400, 500, 600))
                .log()
                // 정상적으로 종료 되면 onCompleteSignal 이벤트 발생
                .reduce((a, b) -> a + b)
                .log()
                .subscribe(System.out::println);
    }
}
