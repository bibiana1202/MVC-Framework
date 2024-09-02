package org.example;


import java.util.Objects;

public class RequestLine {
    private final String method; //GET
    private final String urlPath; // /calculate
    private QueryStrings queryStrings; // operand1=11&operator=*&operand2=55

    public RequestLine(String method, String urlPath, String queryString) {
        this.method = method;  //GET
        this.urlPath = urlPath;// /calculate
        this.queryStrings = new QueryStrings(queryString); // operand1=11&operator=*&operand2=55
    }

    public RequestLine(String requestLine) {
        // GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
        //프로토콜 규칙에 맞게 split 하여 해당 값을 세팅
        // -> 그 규약에 맞게 httprequest를 만들어주고
        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];  //GET
        String[] urlPathTokens = tokens[1].split("\\?"); // /calculate
        this.urlPath = urlPathTokens[0];
        if(urlPathTokens.length ==2) {
            this.queryStrings = new QueryStrings(urlPathTokens[1]);  // operand1=11&operator=*&operand2=55
        }
    }

    public boolean isGeteRequest() {
        return "GET".equals(this.method);
    }

    public boolean matchPath(String requestPath) {
        return urlPath.equals(requestPath);

    }

    public QueryStrings getQueryStrings() {
        return this.queryStrings;
    }

    // equals and hashcode : ? 왜만듬 ?
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(method, that.method) && Objects.equals(urlPath, that.urlPath) && Objects.equals(queryStrings, that.queryStrings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, urlPath, queryStrings);
    }



}
