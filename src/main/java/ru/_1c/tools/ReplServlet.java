package ru._1c.tools;

import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ReplServlet extends GenericServlet {

    @Override
    public void init() {
        Repl.start();
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
    }
}
