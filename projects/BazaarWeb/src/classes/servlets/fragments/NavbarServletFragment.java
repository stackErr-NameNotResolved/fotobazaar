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

@WebServlet(name = "NavbarServletFragment", urlPatterns = {"/NavbarServletFragment"})
public class NavbarServletFragment extends BaseHttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        writer.write("<div class=\"navbar-collapse collapse\">");
        writer.write("<ul class=\"nav navbar-nav\">");
        doHome(request, response);
        doNavLogin(request, response);
        doLanguage(request, response);
        writer.write("</ul>");
        writer.write("</div>");
    }

    protected void doHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        writer.write("<li>");
        writer.write("<a href=\"");
        writer.write(getServletContext().getContextPath() + "/index.jsp");
        writer.write(">Home</a>");
        writer.write("</li>");
    }

    protected void doNavLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write("<li>");

        String username = (String) request.getSession().getAttribute("username");
        String encrypted = (String) request.getSession().getAttribute("username-encrypted");

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
        writer.write("</li>");
    }

    protected void doLanguage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        writer.write("<li><a>");
        writer.write("<form>");
        writer.write("<select id=\"language\" name=\"language\" onchange=\"submit()\">");

        // Write options.
        writer.write(getHtmlOption("English", "en", getLanguage(request).getLanguage().equals("en")));
        writer.write(getHtmlOption("Nederlands", "nl", getLanguage(request).getLanguage().equals("nl")));

        writer.write("</select>");
    }

    private String getHtmlOption(String name, String value, boolean selected) {
        return String.format("<option value=\"%s\" %s>%s</option>", value, selected ? "selected" : "", name);
    }
}
