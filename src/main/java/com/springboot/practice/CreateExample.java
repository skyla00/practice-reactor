package com.springboot.practice;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class CreateExample {
    private static List<Integer> source= Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19);

    public static void main(String[] args) {
        // create : FluxSink 라는 람다 파라미터를 가지는 람다 표현식
        Flux.create(
                // FluxSink : 프로그래밍 방식으로 직접 signal 이벤트를 발생시켜
                // sequence 를 진행하도록 해주는 기능.
                (FluxSink<Integer> sink) -> {
                    // onRequest : subscriber 에서 데이터를 요청하면, 람다표현식이 실행됨.
                    sink.onRequest(n -> {
                        // for 문을 순회하며 next() 메서드로 list source 의 원소를 emit
                        for(int i = 0; i < source.size(); i ++) {
                            sink.next(source.get(i));
                        }
                        // list source 를 모두 emit 해서, sequence 를 종료하기 위해
                        // complete()를 호출.
                        sink.complete();
                    });
                    //sequence 가 완전히 종료 되기 직전에 호출.
                    //종료 직전 후처리 작업을 진행함.
                    sink.onDispose( () -> log.info("# clean up"));
                }).subscribe(data -> log.info("# onNext: {}", data));
    }
}
