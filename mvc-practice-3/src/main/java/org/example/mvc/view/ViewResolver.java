package org.example.mvc.view;

// 뷰네임을 받아서 뷰를 결정 하는 것
public interface ViewResolver {
    View resolveView(String viewName);
}
