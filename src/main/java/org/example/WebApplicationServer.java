package org.example;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebApplicationServer {

    private static final Logger log = LoggerFactory.getLogger(WebApplicationServer.class);

    public static void main(String[] args) throws LifecycleException {
        // 웹 애플리케이션 실행 위치, 톰캣의 루트 디렉토리
        String webappDirLocation = "webapps/";

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080); // 톰캣의 포트를 8080 으로 실행

        //웹 애플리케이션 디렉토리를 웹앱스 밑에 WEB-INF 밑에 classes 밑에서 찾는다.
        //톰캣 실행할때 해당 경로에서 자바 클래스를 찾는다 -> application developer's guide - deployment - apache tomcat -> standard directory layout
        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        log.info("configuring app with basedir: {}", new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();

        // 도커 : 컨테이너 기반의 가상화 플랫폼
        // 컨테이너 기반의 가상화 vs 하이퍼바이퍼 기반의 가상화(os가상화)

        // (1) 하이퍼바이퍼 기반의 가상화(os가상화)
        // 호스트는 물리서버 , 게스트는 사상서버
        // 호스트와 게스트 연결하는 하이퍼 바이저 : 서버 가상화 기술로서
        // 호스트 서버에 설치되고 호스트와 게스트를 나누는 역할을 하며
        // 각각의 게스트는 하이퍼바이저에 의해 관리되며 시스템 자원을 할당 받게 된다.
        // 하이퍼 바이저 기반의 가상화는 격리된 환경에서 또 하나의 가상 서버를 실행 하는 기술
        // 게스트 os 는 다양한 os 선택 가능 , 게스트 os 에서 실행되는 어플리케이션이 호스트 자원을 사용하기 위해서는 반드시 게스트 os 를 거쳐야 하므로 속도가 느릴수 있다.

        // (2) 컨테이너 기반의 가상화
        // 격리된 환경에서 프로세스를 실행하는 기술
        // 애플리케이션은 도커 엔진을 통해 호스트 자원을 사용할수 있고,
        //  구조적으로 게스트 os 가 없기 때문에 용량 가벼움
        // 도커 허브 : 도커에서 저장하는 이미지 저장소 ex) 깃허브 ->https://hub.docker.com/_/mysql 이미지 확인 가능
        // 도커 컴포즈 : 다중 컨테이너를 정의하고 실행하기 위한 도구
        // 즉, mysql,웹서버,카프카 라던가 여러개의 프로세스를 하나의 파일로 정의하여 동시에 실행하기위한 도구
        // yaml 을 사용하여 다중 컨테이너 구성 가능
        // https://docs.docker.com/compose/
        // 즉, 여러개의 서비스를 정의하고 이를 한번에 실행 하는 도구.


        // 자바 단위 테스팅 프레임워크: JUnit5
        // 테스트 코드 가독성을 높여주는 자바 라이브러리 : AssertJ

        // TDD
        // Test Driven Development(테스트 주도 개발)
        // 프로덕션 코드 보다 테스트 코드를 먼저 작성하는 개발방법
        // TFD(Test First Development) + 리팩토링
        // 기능 동작을 검증 (메소드 단위)

        // BDD
        // Behavior Driven Development(행위 주도 개발)
        // 시나리오 기반으로 테스트 코드를 작성하는 개발 방법
        // 하나의 시나리오는 Given, When, Then 구조를 가짐

    }
}