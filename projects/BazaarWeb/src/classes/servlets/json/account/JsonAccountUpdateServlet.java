package classes.servlets.json.account;

import classes.database.DatabaseConnector;
import classes.servlets.base.JsonServlet;

import javax.json.JsonObjectBuilder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "JsonAccountUpdateServlet", urlPatterns = { "/json/account/update"})
public class JsonAccountUpdateServlet extends JsonServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, JsonObjectBuilder builder) throws IOException {
        String idStr = req.getParameter("id");
        String accessStr = req.getParameter("access");

        int id = Integer.valueOf(idStr == null || idStr.isEmpty() ? "0" : idStr);
        if (id <= 0) return;

        int access = Integer.valueOf(accessStr == null || accessStr.isEmpty() ? "0" : accessStr);
        if (accessStr == null) return;

        // Account.
        builder.add("result", DatabaseConnector.getInstance().executeNonQuery("UPDATE ACCOUNT SET ACCESS = ? WHERE ID = ?", access, id).name());
    }
}
