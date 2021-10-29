package ru._1c.tools;


import clojure.java.api.Clojure;
import clojure.lang.IFn;


public class Repl {


    public static void main(String[] args) {
        start();
    }


    public static Thread thread;


    public static void start() {
        thread = new Thread(() -> {
            IFn require = Clojure.var("clojure.core", "require");
            require.invoke(Clojure.read("nrepl.cmdline"));
            IFn main = Clojure.var("nrepl.cmdline", "-main");
            main.invoke("--port", "7888", "--middleware", "[cider.nrepl/cider-middleware]");
        });
        thread.start();
    }


    public static void stop() {
        if (thread != null) thread.interrupt();
    }

}
