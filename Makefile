CLASSPATH=".:bin:lib/algs4.jar:src"

compile:
	javac -cp $(CLASSPATH) src/Solver.java \
		-g -Xlint:deprecation -Xlint:unchecked -d bin

run: compile
	java -cp $(CLASSPATH) Solver puzzle04.txt

debug: compile
	jdb -classpath $(CLASSPATH) Solver puzzle04.txt

lib:
	mkdir -p lib
	curl https://algs4.cs.princeton.edu/code/algs4.jar -o lib/algs4.jar
	unzip lib/algs4.jar -d lib/algs4

