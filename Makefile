bin?=PercolationStats.class
src?=unionfind/PercolationStats.java

$(bin): $(src)
	javac -cp ".:algs4.jar:unionfind" $(src) -g -Xlint:deprecation

run: $(bin)
	java -cp ".:algs4.jar:unionfind" PercolationStats 1000 1000

debug: $(bin)
	jdb -classpath ".:algs4.jar:unionfind" PercolationStats

download_libs:
	curl https://algs4.cs.princeton.edu/code/algs4.jar -o algs4.jar
	unzip algs4.jar -d algs4

tags: download_libs
	ctags -R .

clean:
	rm -f $(bin)
