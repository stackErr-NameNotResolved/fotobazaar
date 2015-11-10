package classes.servlets.base;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "BaseHttpServlet")
public abstract class BaseHttpServlet extends HttpServlet {

    /**
     * Gets the language-centered text based on the given {@link Locale} and key.
     *
     * @param locale {@link Locale} specifying the language to get the text from.
     * @param key    Name of the key used to lookup the text in the resource file.
     * @return Text that was read from the language resource file.
     */
    protected String getLocal(Locale locale, String key) {
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
    protected String getLocal(HttpServletRequest request, String key) {
        return getLocal(getLanguage(request), key);
    }

    /**
     * Gets the language that was set for the request.
     *
     * @param request Request that has the language variable set.
     * @return {@link Locale} that the {@link HttpServletRequest request} is set on.
     */
    protected Locale getLanguage(HttpServletRequest request) {
        if (request == null) throw new IllegalArgumentException("Parameter request must not be null.");

        Object attribute = request.getSession().getAttribute("language");
        return attribute instanceof String ? Locale.forLanguageTag((String) attribute) : (Locale) attribute;
    }
}
