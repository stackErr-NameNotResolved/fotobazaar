package classes.filters;

import classes.filters.base.BaseHttpFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends BaseHttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException {
//        String username = String.valueOf(request.getAttribute("username"));
//        String encrypted = String.valueOf(request.getAttribute("username-encrypted"));
//
//        if (username == null || username.isEmpty() || encrypted == null || encrypted.isEmpty()) return;
//        Session.checkSessionData(username, encrypted, request.getRemoteAddr())
    }
}

