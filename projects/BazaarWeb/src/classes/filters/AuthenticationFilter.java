package classes.filters;

import classes.domain.Session;
import classes.filters.base.BaseHttpFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends BaseHttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException {
        response.setContentType("text/html");
        response.getWriter().write(String.valueOf(Session.checkSessionData(request.getAttribute("username").toString(), request.getAttribute("username-encrypted").toString(), request.getRemoteAddr())));
    }
}
