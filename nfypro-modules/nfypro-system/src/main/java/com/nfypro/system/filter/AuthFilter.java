package com.nfypro.system.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 从请求头中获取 Token（示例）
        /*String token = httpRequest.getHeader("Authorization");
        if (token == null || !validateToken(token)) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Unauthorized: Missing or invalid token");
            return;
        }*/

        // 继续执行后续过滤器或控制器
        chain.doFilter(request, response);
    }

    private boolean validateToken(String token) {
        // 调用认证服务验证 Token（需自行实现）
        return token.startsWith("Bearer ");
    }

    /*@Override
    public void init(FilterConfig filterConfig) {}*/
    @Override
    public void destroy() {}
}