package com.nfypro.system.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = "/*")
public class CacheRequestFilter implements Filter {

    private final Map<String, String> requestCache = new HashMap<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestKey = httpRequest.getRequestURI() + "?" + httpRequest.getQueryString();

        // 检查缓存是否存在
        if (requestCache.containsKey(requestKey)) {
            response.getWriter().write(requestCache.get(requestKey));
            return;
        }

        // 缓存响应内容（需自定义）
        CachedResponseWrapper wrapper = new CachedResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, wrapper);

        String responseContent = wrapper.getContent();
        requestCache.put(requestKey, responseContent);
    }

    /*@Override
    public void init(FilterConfig filterConfig) {}*/
    @Override
    public void destroy() {}
}

// 自定义 Response Wrapper 类（用于缓存响应内容）
class CachedResponseWrapper extends HttpServletResponseWrapper {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintWriter writer;

    public CachedResponseWrapper(HttpServletResponse response) {
        super(response);
        writer = new PrintWriter(outputStream);
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    public String getContent() {
        writer.flush();
        return outputStream.toString();
    }
}
