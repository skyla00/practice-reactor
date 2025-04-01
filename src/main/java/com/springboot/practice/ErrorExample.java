package com.springboot.practice;

import com.springboot.practice.data.Coffee;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 에러 기본 예제
 */
@Slf4j
public class ErrorExample {
    public static void main(String[] args) {
        // 의도적으로 onError Signal 발생시킬 때 사용.
        // justOrEmpty : 파라미터로 전달되는 데이터소스가 null 이어도 에러가 발생하지 않음.
        Mono.justOrEmpty(findVerifiedCoffee())
                // Upstream에서 전달되는 데이터가 null 이면 대체 동작을 수행할 수 있음.
                .switchIfEmpty(Mono.error(new RuntimeException("Not found coffee")))  // (2)
                .subscribe(
                        data -> log.info("{} : {}", data.getKoreanName(), data.getPrice()),
                        error -> log.error("# onError: {}", error.getMessage()));  // (3)
    }

    private static Coffee findVerifiedCoffee() {
        return null;
    }
}
