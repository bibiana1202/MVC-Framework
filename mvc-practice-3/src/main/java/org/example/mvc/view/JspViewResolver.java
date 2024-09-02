package org.example.mvc.view;

import static org.example.mvc.view.RedirectView.DEFAULT_REDIRECT_PREFIX;

public class JspViewResolver implements ViewResolver {
    @Override
    public View resolveView(String viewName) {

        // 리다이렉트 형태
        if(viewName.startsWith(DEFAULT_REDIRECT_PREFIX)){
            return new RedirectView(viewName);
        }
        // JSP면 JSP로 보여준다.
        return new JspView(viewName+".jsp"); // .jsp 를 붙여준다!!!!!
    }
}
