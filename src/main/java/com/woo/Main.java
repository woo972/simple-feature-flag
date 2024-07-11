package com.woo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = "com.woo.persistence")
@EnableTransactionManagement
@ComponentScan(basePackages = "com.woo")
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        TestService service = context.getBean(TestService.class);
        service.testB();


        // 행위자
        // 1. engineer: feature flag를 코드에 사용 (SDK 사용)
        //  - anotation 활용
        // 2. admin: feature flag 생성, 조회, 편집, 삭제 (feature flag WEB 사용)
        //  - feature flag (id, flags(a/b/c...), activated, author, createdAt, updatedAt, archivedAt, trafficRatio, target(userId, deviceId))
        //  - 생성/편집/삭제: cache에 추가 및 업데이트
        //  - 조회: cache에서 읽기
        // 3. customer: feature flag에 의해 수행되는 로직 변경

        // 시스템 디자인
        // 1. SDK (traffic randomization, read feature flag)
        // 2. WEB (CRUD feature flag)
        // 3. DB / cache / zookeeper, central dogma(?)



        // 1차
        // feature flag값을 DB에서 주기적으로 polling하여 cache에 쌓는다. (도메인의 DB와, cache 사용)
        // 위 동작을 위한 feature flag SDK를 만든다.
    }


}