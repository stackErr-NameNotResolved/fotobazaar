package classes.servlets;

import classes.database.DatabaseConnector;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response, JsonObjectBuilder builder) throws IOException {
        String user = request.getParameter("inputUsername");
        String pass = request.getParameter("inputPassword");

        if (user == null || user.isEmpty()) {
            setStatus(response, ResponseStatusCodes.UNPROCESSABLE_ENTITY);
            addErrorMessage(response, "Gebruikersnaam is niet ingevoerd.<br/>");
            return;
        }
        if (pass == null || pass.isEmpty()) {
            setStatus(response, ResponseStatusCodes.UNPROCESSABLE_ENTITY);
            addErrorMessage(response, "Wachtwoord is niet ingevoerd.<br/>");
            return;
        }

        if (Account.registerNewAccount(user, pass, Account.Rights.Photographer)) {
            builder.add("response", String.format("Account '%s' is aangemaakt.", user));
        } else if (DatabaseConnector.getInstance().executeQuery("SELECT * FROM account WHERE USERNAME = ?", user).containsData()) {
            setStatus(response, ResponseStatusCodes.UNPROCESSABLE_ENTITY);
            addErrorMessage(response, String.format("Account '%s' bestaat al.", user));
        } else {
            setStatus(response, ResponseStatusCodes.UNPROCESSABLE_ENTITY);
            addErrorMessage(response, String.format("Account '%s' kon niet aangemaakt worden.", user));
        }
    }
}
