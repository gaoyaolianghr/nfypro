package com.nfypro.system.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class ValidateCodeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 仅拦截登录请求（示例）
        if ("/api/login".equals(httpRequest.getRequestURI())) {
            String code = httpRequest.getParameter("code");
            String sessionCode = (String) httpRequest.getSession().getAttribute("validate_code");

            if (code == null || !code.equalsIgnoreCase(sessionCode)) {
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpResponse.getWriter().write("Invalid verification code");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    /*@Override
    public void init(FilterConfig filterConfig) {}*/
    @Override
    public void destroy() {}
}
