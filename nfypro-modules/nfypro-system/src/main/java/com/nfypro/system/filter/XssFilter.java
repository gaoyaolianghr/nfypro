package com.nfypro.system.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.text.StringEscapeUtils;

@WebFilter(urlPatterns = "/*")
public class XssFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 对请求参数进行 XSS 转义
        XssRequestWrapper wrappedRequest = new XssRequestWrapper((HttpServletRequest) request);
        chain.doFilter(wrappedRequest, response);
    }

    /*@Override
    public void init(FilterConfig filterConfig) {}*/
    @Override
    public void destroy() {}
}

// 自定义 Request Wrapper（转义参数）
class XssRequestWrapper extends HttpServletRequestWrapper {
    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return StringEscapeUtils.escapeHtml4(value); // 使用 Apache Commons Text
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) return null;
        return Arrays.stream(values)
                .map(StringEscapeUtils::escapeHtml4)
                .toArray(String[]::new);
    }
}
