bin_base=$(pwd $0)
project_base=$(cd $bin_base/.. & pwd)
lib_base="${project_base}/lib"

echo $lib_base

java -cp .:$lib_base/* -Xmx4096m -debug my.pinkyo.SimpleSparkJddDemo /home/yinkn/Documents/data/res.log
