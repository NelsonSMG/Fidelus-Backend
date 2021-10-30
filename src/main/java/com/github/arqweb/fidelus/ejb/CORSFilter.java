package com.github.arqweb.fidelus.ejb;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
            ServletException {
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        HttpServletRequest httpReq = (HttpServletRequest) req;
        System.out.print("FILTRO CORS");
        httpResp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS");
        httpResp.setHeader("Access-Control-Allow-Origin", "*");
        if (httpReq.getMethod().equalsIgnoreCase("OPTIONS")) {
            httpResp.setHeader("Access-Control-Allow-Headers",
                    httpReq.getHeader("Access-Control-Request-Headers"));
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
