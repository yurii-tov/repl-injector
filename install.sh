set -e


usage="usage: `basename $0` [-t <type>] [-p <port>] <path-to-web-inf>"
type=nrepl
port=7777


while getopts :t:p:h OPT; do
    case $OPT in
        t)
            type=$OPTARG
            ;;
        p)
            port=$OPTARG
            ;;
        *)
            echo $usage
            exit 0
    esac
done
shift $(($OPTIND - 1))


webinf="$1"
[ "$webinf" ] || { echo $usage; exit 0; }
webinf=$(readlink -f "$webinf")


cd "$(readlink -f $(dirname $0))"


echo "Installing jar file..."
cp -v target/*-jar-with-dependencies.jar "$webinf/lib/repl.jar"


printf "Installing servlet config..."
sed -i '/<!--REPL servlet-->/,/<\/servlet>/ d' "$webinf/web.xml"
sed -i '/<\/welcome-file-list>/ r servlet.xml' "$webinf/web.xml"
sed -i '/<!--REPL servlet-->/,/<\/servlet>/ {s:${type}:'$type':; s:${port}:'$port':}' "$webinf/web.xml"
echo ' done'
