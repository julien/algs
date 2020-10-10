bin?=bin
src?=src/Percolation.java

$(bin): $(src)
	javac -cp ".:algs4.jar" $(src) -d bin -g -Xlint:deprecation

run: $(bin)
	java -cp ".:algs4.jar:bin" Percolation 5 1 4 2 4 3 4 4 4 5 4

debug: $(bin)
	jdb -classpath ".:algs4.jar:bin" Percolation

download_libs:
	curl https://algs4.cs.princeton.edu/code/algs4.jar -o algs4.jar
	unzip algs4.jar -d algs4

tags: download_libs
	ctags -R .

clean:
	rm -f bin/*.class
