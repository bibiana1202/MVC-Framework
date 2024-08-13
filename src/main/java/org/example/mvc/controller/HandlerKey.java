package org.example.mvc.controller;

import java.util.Objects;

// 역할 :
public class HandlerKey {
    private final RequestMethod requestMethod;
    private final String uriPath;

    public HandlerKey(RequestMethod requestMethod, String uriPath) {
        this.requestMethod = requestMethod;
        this.uriPath = uriPath;
    }

    // 객체 끼리 비교 -> equals , hashcode 필요함
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandlerKey that = (HandlerKey) o;
        return requestMethod == that.requestMethod && Objects.equals(uriPath, that.uriPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestMethod, uriPath);
    }
}
