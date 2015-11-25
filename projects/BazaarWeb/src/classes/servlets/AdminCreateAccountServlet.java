package classes.servlets;

import classes.domain.models.Account;
import classes.servlets.base.JsonServlet;

import javax.json.JsonObjectBuilder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminCreateAccountServlet", urlPatterns = {"/AdminCreateAccountServlet"})
public class AdminCreateAccountServlet extends JsonServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp, JsonObjectBuilder builder) throws IOException {
        String user = request.getParameter("inputUsername");
        String pass = request.getParameter("inputPassword");

        if (user == null || user.isEmpty()) addError(resp, "Gebruikersnaam is niet ingevoerd.<br/>");
        if (pass == null || pass.isEmpty()) addError(resp, "Wachtwoord is niet ingevoerd.<br/>");

        if (Account.registerNewAccount(user, pass, Account.Rights.Photographer)) {
            builder.add("response", String.format("Account '%s' is aangemaakt.", user));
        } else {
            builder.add("response", String.format("Account '%s' kon niet aangemaakt worden.", user));
        }
    }
}
