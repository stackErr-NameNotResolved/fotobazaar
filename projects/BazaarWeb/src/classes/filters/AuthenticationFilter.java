package classes.filters;

import classes.domain.models.Account;
import classes.filters.base.BaseHttpFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "authentication", urlPatterns = {"/pages/admin/*"})
public class AuthenticationFilter extends BaseHttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Redirect.
        Account account = (Account) session.getAttribute("account");
        int rights = session.getServletContext().getInitParameter("rights") == null ? 0 : Integer.valueOf(session.getServletContext().getInitParameter("rights"));
        if (account != null) {
            session.setAttribute("authresult", AuthResult.OK);
//            if (account.getRight() <= rights) {
//                session.setAttribute("authresult", AuthResult.OK);
//            } else {
//                session.setAttribute("authresult", AuthResult.InsufficientRights);
//            }
        } else {
            session.setAttribute("authresult", AuthResult.NoAccount);
        }

        if (!session.getAttribute("authresult").equals(AuthResult.OK)) {
            session.setAttribute("redirecturl", request.getRequestURI());
            response.sendRedirect(contextPath + "/pages/login.jsp");
            return;
        }

        super.doFilter(request, response, chain);
    }

    public enum AuthResult {
        OK,
        InsufficientRights,
        NoAccount
    }
}

