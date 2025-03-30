package com.springboot.practice;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class ZipExample {
    public static void main(String[] args) throws InterruptedException {

        // ㅇㅣㅂ력으로 전달되는 여러 개의 publisher sequence 에서 emit 된 데이터를 결합.
        // 각 publisher 가 emit 하는 데이터를 하나씩 전달 받아
        // 새로운 데이터를 만든 후 downstream으로 전달함.
        // 각각의 sequence 에서 emit 되는 데이터 중 같은 index 의 데이터끼리 결합.
        // 각 emit 되는 데이터 시점이 달라 결합되어야 하는 데이터를 기다렸다가 결합함.

        // interval() : 파라미터로 전달한 시간을 주기로 0부터 1씩 증가한 숫자를 emit 함.
        // talek : 지정된 숫자만큼의 데이터만 emit 하고 종료함.

        // (1) 0.2 초에 한 번씩  >> (2) 보다 조금더 빨리 데이터를 emit 함.
        Flux<Long> source1 = Flux.interval(Duration.ofMillis(200L)).take(4);

        // (2) 0.4 초에 한 번씩
        Flux<Long> source2 = Flux.interval(Duration.ofMillis(400L)).take(6);

        Flux
                .zip(source1, source2, (data1, data2) -> data1 + data2)   // (3)
                // sequence 의 emit 시점이 다르더라도 대기했다가 두개의 데이터를 전달받음.
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(3000L);
    }
}
