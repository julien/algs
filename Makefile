bin?=bin
src?=src/Percolation.java

$(bin): $(src)
	javac -cp ".:algs4.jar" $(src) -d bin -g

run: $(bin)
	java -cp ".:algs4.jar:bin" Percolation

debug: $(bin)
	jdb -classpath ".:algs4.jar:bin" Percolation

clean:
	rm -f bin/*.class
