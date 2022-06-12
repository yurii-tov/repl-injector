package ru._1c.tools;


import java.util.Map;
import java.util.HashMap;
import java.util.function.Consumer;
import clojure.java.api.Clojure;
import clojure.lang.IFn;


public class Repl {
    
    public static void start(String type, String port) {
        final IFn require = Clojure.var("clojure.core", "require");
        final Map<String, Consumer<String>> repls = new HashMap<String, Consumer<String>>() {{
                this.put("nrepl", (port) -> {
                        require.invoke(Clojure.read("nrepl.cmdline"));
                        IFn main = Clojure.var("nrepl.cmdline", "-main");
                        main.invoke("--port", port, "--middleware", "[cider.nrepl/cider-middleware]");
                });
                this.put("socket", (port) -> {
                        require.invoke(Clojure.read("clojure.core.server"));
                        require.invoke(Clojure.read("clojure.main"));
                        IFn startServer = Clojure.var("clojure.core.server", "start-server");
                        startServer.invoke(Clojure.read(String.format("{:name \"socket-repl\" :port %s :accept clojure.main/repl}", port)));
                });
        }};
        final Thread thread = new Thread(() -> repls.get(type).accept(port));
        thread.start();
        System.out.printf("Starting %s REPL at localhost:%s\n", type, port);
    }

}
