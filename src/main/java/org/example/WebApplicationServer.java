package org.example;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

// WebApplicationServer = 메인 메소드 실행할시 톰캣이 실행되게끔 한다.
public class WebApplicationServer {
    private static final Logger log = LoggerFactory.getLogger(WebApplicationServer.class);

    public static void main(String[] args) throws Exception {
        // 메인 메소드시 임베디드 톰캣이 실행되도록
        String webappDirLocation = "webapps/"; // 경로를 입력했을때 해당 디렉토리를 루트 디렉토리로 본다
        Tomcat tomcat = new Tomcat(); // 그 하위에 있는 파일들을 Tomcat이 찾아서 실행하는 형태로 동작한다는 의미
        tomcat.setPort(8080); // 8080 포트
        // tomcat 이 실행되면 webapps 밑에 빌드된 webapplicationServer 가 자동적으로 들어온다.
        tomcat.getConnector();
        tomcat.addWebapp("/",new File(webappDirLocation).getAbsolutePath());
        // = localhost 8080 으로 입력했을때 web directory location , 즉 webapps라는 디렉토리를 바라보겠다
        log.info("configuring app with basedir : {}", new File("./"+webappDirLocation).getAbsolutePath());

        tomcat.start(); // exception 던지면 사라짐...왜.....
        tomcat.getServer().await();
    }
}