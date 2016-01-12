package classes.servlets;

import classes.domain.models.Account;
import classes.servlets.base.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountViewServlet", urlPatterns = {"/AccountViewServlet"})
public class AccountViewServlet extends BaseHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getSession(req).setAttribute("accounts", Account.getAll());
        resp.sendRedirect("pages/admin/accountView.jsp");
    }
}
