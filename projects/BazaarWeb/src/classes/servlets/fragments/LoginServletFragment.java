package classes.servlets.fragments;

import classes.domain.Session;
import classes.servlets.LogOutServlet;
import classes.servlets.base.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServletFragment", urlPatterns = {"/LoginServletFragment"})
public class LoginServletFragment extends BaseHttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String encrypted = (String) request.getSession().getAttribute("username-encrypted");

        PrintWriter writer = response.getWriter();
        if (Session.checkSessionData(username, encrypted, request.getRemoteAddr())) {
            request.getSession().setAttribute("login-href", "");
            request.getSession().setAttribute("login-text", "master.menu.logout");

            writer.write("<A href=\"");
            writer.write("javascript:document.submitForm.submit()");
            writer.write("\">");
            writer.write(getLocal(request, "master.menu.logout"));
            writer.write("</A>");

            writer.write("<form name=\"submitForm\" method=\"POST\" action=\"" + LogOutServlet.class.getSimpleName() + "\">");
            writer.write("</form>");
        } else {
            request.getSession().removeAttribute("username");
            request.getSession().removeAttribute("username-encrypted");

            writer.write("<A href=\"");
            writer.write(getServletContext().getContextPath() + "/pages/login.jsp");
            writer.write("\">");
            writer.write(getLocal(request, "master.menu.login"));
            writer.write("</A>");

            writer.write("<form name=\"submitForm\" method=\"POST\" action=\"" + getServletContext().getContextPath() + "/pages/login.jsp\"></form>");
        }
    }
}
