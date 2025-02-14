package com.nfypro.system.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = "/*")
public class BlackListUrlFilter implements Filter {

    private static final List<String> BLACKLIST_URLS = Arrays.asList("/api/blocked", "/admin/secret");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        /*String requestUri = httpRequest.getRequestURI();
        if (BLACKLIST_URLS.contains(requestUri)) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.getWriter().write("Forbidden: URL is blocked");
            return;
        }*/

        chain.doFilter(request, response);
    }

    /*@Override
    public void init(FilterConfig filterConfig) {}*/
    @Override
    public void destroy() {}
}