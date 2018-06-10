package com.Y3.AnalyticsTeam.CT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class CorsFilter implements Filter {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        if (req instanceof HttpServletRequest) {
            LOGGER.info("### "+((HttpServletRequest)req).getRequestURL()+" ,method->"+((HttpServletRequest) req).getMethod() );
            Map<String, String[]> map = ((HttpServletRequest)req).getParameterMap();
            if(!map.isEmpty()) {
                LOGGER.debug("### Parameter -> ");
                for (String key : map.keySet()) {
                    LOGGER.debug("### " + key + " : " + map.get(key)[0]);
                }
            }

        }
        chain.doFilter(req, res);
    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
