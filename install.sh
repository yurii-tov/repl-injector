set -e
webinf="${1?'Usage: ./install <path-to-web-inf>'}"
webinf=$(readlink -f "$webinf")


cd "$(readlink -f $(dirname $0))"


cp -v ../target/repl-1.0-SNAPSHOT-jar-with-dependencies.jar "$webinf/lib/repl.jar"


printf "Install web.xml settings..."
sed -i '/<!--REPL servlet-->/,/<\/servlet>/ d' "$webinf/web.xml"
sed -i '/<\/welcome-file-list>/ r servlet.xml' "$webinf/web.xml"
echo ' done'
