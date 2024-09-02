package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;

public class HttpResponse {
    // 로그 출력
    private static final Logger logger = LoggerFactory.getLogger(HttpResponse.class);

    // 데이터 아웃풋 스트림을 생성자를 통해 받아오는
    private final DataOutputStream dos;
    public HttpResponse(DataOutputStream dos) {
        this.dos = dos;
    }

    // 헤더 값 + blank
    public void response200Header(String contentType, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n"); // status line
            dos.writeBytes("Content-Type: " + contentType + ";charset=utf-8\r\n"); // header line
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n"); // header line
            dos.writeBytes("\r\n"); // blank line
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }


    // 바디 값 세팅
    public void responseBody(byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}