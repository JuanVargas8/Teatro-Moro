package cl.teatromoro.common.server;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class InstanceHeaderFilter implements Filter {

    private final String applicationName;

    public InstanceHeaderFilter(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        if (response instanceof HttpServletResponse) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("X-Instance-Port", String.valueOf(request.getLocalPort()));
            httpResponse.setHeader("X-Instance-Id", applicationName);
        }
        
        chain.doFilter(request, response);
    }
}
