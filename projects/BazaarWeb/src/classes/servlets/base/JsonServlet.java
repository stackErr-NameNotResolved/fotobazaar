package classes.servlets.base;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class JsonServlet extends BaseHttpServlet {
    @Override
    protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        JsonObjectBuilder builder = Json.createObjectBuilder();
        doPost(req, resp, builder);
        resp.getWriter().write(builder.build().toString());
        resp.getWriter().flush();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp, JsonObjectBuilder builder) {

    }
}
