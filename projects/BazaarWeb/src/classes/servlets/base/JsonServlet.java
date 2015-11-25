package classes.servlets.base;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class JsonServlet extends BaseHttpServlet {
    private void setJsonResponse(HttpServletResponse resp) {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
    }

    @Override
    protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setJsonResponse(resp);
        JsonObjectBuilder builder = Json.createObjectBuilder();

        doPost(req, resp, builder);

        if (resp.getStatus() != HttpServletResponse.SC_OK) return;
        resp.getWriter().write(builder.build().toString());
        resp.getWriter().flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setJsonResponse(resp);
        JsonObjectBuilder builder = Json.createObjectBuilder();

        doGet(req, resp, builder);

        if (resp.getStatus() != HttpServletResponse.SC_OK) return;
        resp.getWriter().write(builder.build().toString());
        resp.getWriter().flush();
    }

    /**
     * Uses the {@link JsonObjectBuilder} to return a JSON response.
     *
     * @param req     Request that was received from the client's browser.
     * @param resp    Response that has been created on the server.
     * @param builder {@link JsonObjectBuilder} to return a JSON response.
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, JsonObjectBuilder builder) throws IOException {
    }

    /**
     * Uses the {@link JsonObjectBuilder} to return a JSON response.
     *
     * @param req     Request that was received from the client's browser.
     * @param resp    Response that has been created on the server.
     * @param builder {@link JsonObjectBuilder} to return a JSON response.
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, JsonObjectBuilder builder) {
    }
}
