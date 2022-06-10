package ru._1c.tools;


import clojure.java.api.Clojure;
import clojure.lang.IFn;


public class Repl {

    public static Thread start(String port) {
        final Thread thread = new Thread(() -> {
                IFn require = Clojure.var("clojure.core", "require");
                require.invoke(Clojure.read("nrepl.cmdline"));
                IFn main = Clojure.var("nrepl.cmdline", "-main");
                main.invoke("--port", port, "--middleware", "[cider.nrepl/cider-middleware]");
        });
        thread.start();
        return thread;
    }

}
