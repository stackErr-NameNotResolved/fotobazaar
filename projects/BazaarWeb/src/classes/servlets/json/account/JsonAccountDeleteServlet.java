package classes.servlets.json.account;

import classes.database.DatabaseConnector;
import classes.servlets.base.JsonServlet;

import javax.json.JsonObjectBuilder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "JsonAccountDeleteServlet", urlPatterns = { "/json/account/delete"})
public class JsonAccountDeleteServlet extends JsonServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, JsonObjectBuilder builder) throws IOException {
        String idStr = req.getParameter("id");
        if (idStr == null || idStr.isEmpty()) return;

        int id = Integer.valueOf(idStr);
        if (id <= 0) return;

        DatabaseConnector.getInstance().executeNonQuery("DELETE FROM PHOTOGRAPHER WHERE ACCOUNT_ID = ?", id);
        builder.add("result", DatabaseConnector.getInstance().executeNonQuery("DELETE FROM ACCOUNT WHERE ID = ?", id).name());
    }
}
