CLASSPATH=".:libs/algs4.jar"

compile:
	javac -cp $(CLASSPATH) FixedCapacityStackOfStrings.java -g -Xlint:deprecation -Xlint:unchecked

run: compile
	java -cp $(CLASSPATH) FixedCapacityStackOfStrings < tobe.txt

debug: compile
	jdb -classpath $(CLASSPATH) FixedCapacityStackOfStrings

libs:
	mkdir -p libs
	curl https://algs4.cs.princeton.edu/code/algs4.jar -o libs/algs4.jar
	unzip libs/algs4.jar -d libs/algs4

