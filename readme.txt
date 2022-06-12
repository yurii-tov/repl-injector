* Inject Clojure nREPL into live application


** Tested with
   - Maven 3.6.1


** Preparation
   mvn clean package


** Installation
   ./install.sh <path-to-web-inf>
   e.g. ./install.sh /opt/my-app/<...>/WEB-INF

   
** REPL types
   i.e. valid values for "type"
   - socket
     "dumb" REPL which talks in plain text
     No specific client is required (i.e., all you need is "nc localhost 7777")
     Useful in harsh environments
   - nrepl
     Full-blown network REPL which uses its own protocol. Requires appropriate client (e.g. lein repl)
