package classes.filters;

import classes.filters.base.BaseHttpFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "authentication", urlPatterns = { "/pages/admin/*" })
public class AuthenticationFilter extends BaseHttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Redirect.
        if (session.getAttribute("account") == null) {
            session.setAttribute("redirecturl", request.getRequestURI());
            response.sendRedirect(contextPath + "/pages/login.jsp");
        }
        super.doFilter(request, response, chain);
    }
}

