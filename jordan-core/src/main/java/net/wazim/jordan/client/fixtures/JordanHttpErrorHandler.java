package net.wazim.jordan.client.fixtures;

import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.ErrorHandler;

import java.net.MalformedURLException;
import java.net.URL;

public class JordanHttpErrorHandler {

    public static JordanCssErrorHandler newJordanCssErrorHandler() {
        return new JordanCssErrorHandler();
    }

    public static JordanJavascriptErrorHandler newJordanJavascriptErrorHandler() {
        return new JordanJavascriptErrorHandler();
    }

    private static class JordanCssErrorHandler implements ErrorHandler{
        @Override
        public void warning(CSSParseException e) throws CSSException {

        }

        @Override
        public void error(CSSParseException e) throws CSSException {

        }

        @Override
        public void fatalError(CSSParseException e) throws CSSException {

        }
    }

    private static class JordanJavascriptErrorHandler implements JavaScriptErrorListener {

        @Override
        public void scriptException(HtmlPage htmlPage, ScriptException e) {

        }

        @Override
        public void timeoutError(HtmlPage htmlPage, long l, long l2) {

        }

        @Override
        public void malformedScriptURL(HtmlPage htmlPage, String s, MalformedURLException e) {

        }

        @Override
        public void loadScriptError(HtmlPage htmlPage, URL url, Exception e) {

        }
    }


}
