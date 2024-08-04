package org.example;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {

    // 프로토콜 = http request 구조에는 requestLine  , header , body 가 있어야 함
    private final RequestLine requestLine;
//    private final RequestHeaders httpHeaders;
//    private final Body body;

    public HttpRequest(BufferedReader br) throws IOException {
        this.requestLine = new RequestLine(br.readLine()); // 프로토콜의 첫번째 라인이 requsetline 이므로
    }

    public boolean isGetRequest() {
        return requestLine.isGeteRequest();
    }

    public boolean matchPath(String path) {
        return requestLine.matchPath(path);
    }

    public QueryStrings getQueryStrings() {
        return requestLine.getQueryStrings();
    }



}
