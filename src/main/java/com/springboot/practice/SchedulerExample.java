package com.springboot.practice;

import ch.qos.logback.classic.Logger;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchedulerExample {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .range(1, 10)
                // 구독 발생 직후에 트리거 됨. 구독 직후에 실행되는 스레드와 동일한 스레드에서 실행.
                .doOnSubscribe(subscription -> log.info("# doOnSubscribe"))
                // subscribeOn : 구독 직후 실행되는 Operator 체인의 실행 스레드를
                // scheduler 에서 지정한 스레드로 변경하겠다.
                .subscribeOn(Schedulers.boundedElastic())
                // publishOn 을 추가할 때마다 추가한 publishOn을 기준으로 Downstream 쪽
                // 스레드가 publishOn 에서 sScheduler 로 지정한 스레드로 변경
                .publishOn(Schedulers.parallel())
                .filter(n -> n % 2 == 0)
                .doOnNext(data -> log.info("#filter doOnNext"))
                .publishOn(Schedulers.parallel())
                .map(n -> n*2)
                .doOnNext(data -> log.info("# map doOnNext"))
                .subscribe(data -> log.info("# onNext : {}", data));

        Thread.sleep(100L);
    }
}
