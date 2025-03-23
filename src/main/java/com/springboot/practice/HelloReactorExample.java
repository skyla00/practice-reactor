package com.springboot.practice;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class HelloReactorExample {
    public static void main(String[] args) throws InterruptedException {

        // reactor sequence 가 여러 건의 데이터를 처리함.
        Flux
                // just : 원본 데이터 소스로부터 데이터를 emit 하는 Publisher 역할
                .just("hello", "reactor")
                // map : Publisher 로 부터 전달받은 데이터를 가공.
                .map(m -> m.toUpperCase())
                // publishOn : 스레드 관리자 역할을 하는 Scheduler 를 지정.
                // scheduler 가 Sequence 상에서 실행되는 스레드를 관리하는 역할.
                .publishOn(Schedulers.parallel())
                // 1. Publisher가 emit 한 데이터를 전달받아서 처리하는 역할
                .subscribe(System.out::println,
                // 2. 에러가 발생할 경우, 해당 에러를 전달받아서 처리
                        error -> System.out.println(error.getMessage()),
                        // 3. Sequence 가 종료된 후 후처리.
                        () -> System.out.println("# onComplete"));
                // subscriber에게 전달되어 각각의 동작 수행


        // Sequence 에 Scheduler 를 지정하면 main 외의 별도 스레드가 하나 더 생김.
        // 데몬 스레드 이기 때문에 주 스레드인 main 스레드가 종료되면 동시에 종료됨.
        // main 스레드를 0.1 초 지연시켜 Scheduler 로 지정한 데몬 스레드를 통해
        // 해당 Sequence 가 정상 됭작 하도록 함.
        Thread.sleep(100L);
    }
}
