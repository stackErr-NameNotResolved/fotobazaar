package classes.servlets;

import classes.servlets.base.JsonServlet;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminCreateAccountServlet", urlPatterns = {"/AdminCreateAccountServlet"})
public class AdminCreateAccountServlet extends JsonServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp, JsonObjectBuilder builder) {
        String user = request.getParameter("inputUsername");
        String pass = request.getParameter("inputPassword");

        // Create json.
        builder.add("errors", Json.createArrayBuilder().add("Error 1"));
    }
}
