package classes.servlets.base;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "BaseHttpServlet")
public abstract class BaseHttpServlet extends HttpServlet {

    protected String getLocal(HttpServletRequest request, String key) {
        return ResourceBundle.getBundle("languages.text", getLanguage(request)).getString(key);
    }

    protected Locale getLanguage(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute("language");
        return attribute instanceof String ? Locale.forLanguageTag((String) attribute) : (Locale) attribute;
    }
}
