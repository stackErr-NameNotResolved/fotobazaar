package classes.servlets.base;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "BaseHttpServlet")
public abstract class BaseHttpServlet extends HttpServlet {

    protected String getLocal(HttpServletRequest request, String key) {
        return ResourceBundle.getBundle("languages.text", Locale.forLanguageTag(request.getSession().getAttribute("language").toString())).getString(key);
    }
}
