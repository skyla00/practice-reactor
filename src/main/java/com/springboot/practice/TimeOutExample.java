package com.springboot.practice;

import com.springboot.practice.data.Coffee;
import com.springboot.practice.data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Collectors;

@Slf4j
public class TimeOutExample {
    public static void main(String[] args) throws InterruptedException {
        getCoffees()
                // 아래 1회 재구독 후 중복 데이터 제거 위해 set으로 변환
                .collect(Collectors.toSet())
                .subscribe(bookSet -> bookSet
                        .stream()
                        .forEach(data ->
                                log.info("{} : {}", data.getKoreanName(), data.getPrice())));

        Thread.sleep(12000);
    }

    private static Flux<Coffee> getCoffees() {
        final int[] count = {0};
        return Flux
                .fromIterable(SampleData.coffeeList)
                // 주어진 시간만큼 각각의 데이터 emit 을 지연시킴,
                // Coffee 객체 0.5 초에 한번씩 emit
                .delayElements(Duration.ofMillis(500))
                .map(coffee -> {
                    try {
                        count[0]++;
                        if (count[0] == 3) {
                            // 세번째 Coffee 2초 더 지연
                            Thread.sleep(2000);
                        }
                    } catch (InterruptedException e) {
                    }

                    return coffee;
                })
                // 2 초 안에 데이터가 emit 되지 않으면 onErrorSignal 발생.
                .timeout(Duration.ofSeconds(2))
                // 1회 재구독 해 Sequence 다시 시작
                .retry(1)
                .doOnNext(coffee -> log.info("# getCoffees > doOnNext: {}, {}",
                        coffee.getKoreanName(), coffee.getPrice()));
    }
}
