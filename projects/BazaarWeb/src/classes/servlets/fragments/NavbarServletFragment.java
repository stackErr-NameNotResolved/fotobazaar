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
        doNavHome(request, response);
        doNavLogin(request, response);
        doNavAdmin(request, response);
        doNavLanguage(request, response);
        writer.write("</ul>");
        writer.write("</div>");
    }

    private void doNavAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        // Create header.
        writer.write("<li class=\"dropdown\">");
        writer.write("<a class=\"dropdown-toggle\" data-close-others=\"false\" data-delay=\"0\" data-hover=\"dropdown\" data-toggle=\"dropdown\" href=\"#\">" +  getLocal(request, "master.menu.admin"));
        writer.write("<i class=\"fa fa-angle-down\"></i>");
        writer.write("</a>");

        // Create sub-items for header.
        writer.write("<ul class=\"dropdown-menu\">");
        writer.write("<li><a href=\"" + createPath("pages", "admin", "createAccount") + "\">");
        writer.write(getLocal(request, "master.menu.admin.createAccount"));
        writer.write("</a></li>");
        writer.write("</ul>");

        writer.write("</li>");
    }

    protected void doNavHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        writer.write("<li>");
        writer.write("<a href=\"");
        writer.write(createPath("index.jsp"));
        writer.write("\">Home</a>");
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

            writer.write("<form name=\"submitForm\" method=\"POST\" action=\"" + createPath(LogOutServlet.class.getSimpleName()) + "\">");
            writer.write("</form>");
        } else {
            request.getSession().removeAttribute("username");
            request.getSession().removeAttribute("username-encrypted");

            writer.write(String.format("<a href=\"%s\">%s</a>", createPath("pages", "login.jsp"), getLocal(request, "master.menu.login")));

            writer.write("<form name=\"submitForm\" method=\"POST\" action=\"" + createPath("pages", "login.jsp") + "\"></form>");
        }
        writer.write("</li>");
    }

    protected void doNavLanguage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        writer.write("<li>");
        writer.write("<a>");
        writer.write("<form>");

        // Create select field.
        writer.write("<select id=\"language\" name=\"language\" onchange=\"submit()\">");

        // Write options.
        writer.write(getHtmlOption("English", "en", getLanguage(request).getLanguage().equals("en")));
        writer.write(getHtmlOption("Nederlands", "nl", getLanguage(request).getLanguage().equals("nl")));

        // Close select.
        writer.write("</select>");

        // Close form and link.
        writer.write("</form>");
        writer.write("</a>");
        writer.write("</li>");
    }

    private String getHtmlOption(String name, String value, boolean selected) {
        return String.format("<option value=\"%s\" %s>%s</option>", value, selected ? "selected" : "", name);
    }
}
