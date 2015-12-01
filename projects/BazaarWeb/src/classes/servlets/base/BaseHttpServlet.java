package classes.servlets.base;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "BaseHttpServlet")
public abstract class BaseHttpServlet extends HttpServlet {
    public enum ResponseStatusCodes {

        /**
         * Everything went find with the request and could be handled.
         */
        OK(200),

        /**
         * The request has been fulfilled and resulted in a new resource being created.
         */
        CREATED(201),

        /**
         * The server cannot or will not process the request due to something that is
         * perceived to be a client error (e.g., malformed request syntax,
         * invalid request message framing, or deceptive request routing).
         */
        BAD_REQUEST(400),

        /**
         * Similiar to {@literal ResponseStatusCodes.FORBIDDEN}, but specifically for use when
         * authentication is required and has failed or has not yet been provided. The
         * response must include a WWW-Authenticate header field containing a challenge applicable
         * to the equested resource. {@literal ResponseStatusCodes.UNAUTHORIZED} semantically means "unauthenticated", i.e.
         * "you don't have necessary credentials".
         */
        UNAUTHORIZED(401),

        /**
         * The request was a valid request, but the server is refusing to
         * respond to it. Unlike a {@literal ResponseStatusCodes.UNAUTHORIZED}, authenticating
         * will make no difference. {@literal ResponseStatusCodes.FORBIDDEN} error semantically means
         * "unauthorized", i.e. "You don't have necessary permissions for the resource".
         */
        FORBIDDEN(403),

        /**
         * The requested resource could not be found but may be available again in the
         * future. Subsequent requests by the client are permissible.
         */
        NOT_FOUND(404),

        /**
         * The request was well-formed but was unable to be followed due to semantic errors.
         */
        UNPROCESSABLE_ENTITY(422),

        LOCKED(423);

        int code;

        public int getCode() {
            return code;
        }

        ResponseStatusCodes(int code) {
            this.code = code;
        }
    }

    /**
     * Sets the response status to return to the client. Returns true if it was set.
     *
     * @param response Response to set the status on.
     * @param status   Error to set.
     * @return True if status was set, false otherwise.
     */
    protected boolean setStatus(HttpServletResponse response, ResponseStatusCodes status) {
        if (response == null || status == null) return false;
        response.setStatus(status.getCode());
        return true;
    }

    /**
     * Appends to the message message.
     *
     * @param response Response to write to the client.
     * @param message  Status message to append to the response.
     * @throws IOException
     */
    protected void addErrorMessage(HttpServletResponse response, String message) throws IOException {
        if (response == null) return;
        if (message == null || message.isEmpty()) return;
        setStatus(response, ResponseStatusCodes.UNPROCESSABLE_ENTITY);
        response.getWriter().write(message);
    }

    /**
     * Creates a new path to the relative directory of the file on the server.
     *
     * @param parts Additional parts of the path to append.
     * @return Relative path to the file on the server.
     */
    protected String createPath(String... parts) {
        return Paths.get(getServletContext().getContextPath(), parts).toString();
    }

    /**
     * Gets the language-centered text based on the given {@link Locale} and key.
     *
     * @param locale {@link Locale} specifying the language to get the text from.
     * @param key    Name of the key used to lookup the text in the resource file.
     * @return Text that was read from the language resource file.
     */
    protected static String getLocal(Locale locale, String key) {
        if (locale == null) throw new IllegalArgumentException("Parameter locale must not be null.");
        if (key == null || key.isEmpty())
            throw new IllegalArgumentException("Parameter key must not be null or empty.");

        return ResourceBundle.getBundle("languages.text", locale).getString(key);
    }

    /**
     * Gets the language-centered text based on the given key.
     *
     * @param request Request that has the language variable set. Is used to get the {@link Locale} of the browser.
     * @param key     Name of the language text to look up on.
     * @return Text that was read from the language resource file.
     */
    protected static String getLocal(HttpServletRequest request, String key) {
        return getLocal(getLanguage(request), key);
    }

    /**
     * Gets the language that was set for the request.
     *
     * @param request Request that has the language variable set.
     * @return {@link Locale} that the {@link HttpServletRequest request} is set on.
     */
    protected static Locale getLanguage(HttpServletRequest request) {
        if (request == null) throw new IllegalArgumentException("Parameter request must not be null.");

        Object attribute = request.getSession().getAttribute("language");
        return attribute instanceof String ? Locale.forLanguageTag((String) attribute) : (Locale) attribute;
    }

    @Override
    public String getInitParameter(String name) {
        return super.getInitParameter(name);
    }
}
