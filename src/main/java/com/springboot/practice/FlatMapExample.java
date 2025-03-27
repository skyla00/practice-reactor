package com.springboot.practice;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * flatMap() 기본 예제
 */
@Slf4j
public class FlatMapExample {
    public static void main(String[] args) throws InterruptedException {
        Flux
                // 구구단 2단부터 7단까지
                .range(2, 6)
                .flatMap(dan -> Flux
                        // 내부에서 하나의 단을 출력하도록 1 ~ 9 숫자 지정
                        .range(1, 9)
                        // inner sequence 를 처리할 스레드 할당.
                        // 여러 개의 스레드가 비동기 적으로 동작 함.
                        // 이렇게 추가 스레드를 할당할 경우, 작업의 처리 순서를 보장하지 않음.
                        .publishOn(Schedulers.parallel())
                        .map(num -> dan + " x " + num + " = " + dan * num)) // (4)
                .subscribe(log::info);

        Thread.sleep(100L);
    }
}