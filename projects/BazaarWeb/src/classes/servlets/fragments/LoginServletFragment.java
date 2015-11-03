package classes.servlets.fragments;

import classes.domain.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServletFragment", urlPatterns = {"/LoginServletFragment"})
public class LoginServletFragment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String encrypted = (String) request.getSession().getAttribute("username-encrypted");

        if (Session.checkSessionData(username, encrypted, request.getRemoteAddr())) {
            request.getSession().setAttribute("login-href", "");
            request.getSession().setAttribute("login-text", "master.menu.logout");


//            String buttonText = ResourceBundle.getBundle("text").getString("master.menu.logout");
//            response.getWriter().write("<A href=\"javascript:document.submitForm.submit()\"><fmt:message key=\"" + buttonText + "\"/></A>");
//            response.getWriter().write("<form name=\"submitForm\" method=\"POST\" action=\"LogOutServlet\"></form>");
        } else {
            request.getSession().removeAttribute("username");
            request.getSession().removeAttribute("username-encrypted");

//            String buttonText = ResourceBundle.getBundle("text").getString("master.menu.login");
//            response.getWriter().write("<A href=\"javascript:document.submitForm.submit()\"><fmt:message key=\"" + buttonText + "\"/></A>");
//            response.getWriter().write("<form name=\"submitForm\" method=\"POST\" action=\"/BazaarWeb/pages/login.jsp\"></form>");
        }
    }
}
