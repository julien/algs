bin?=LinkedStackOfStrings.class
src?=LinkedStackOfStrings.java
CLASSPATH=".:libs/algs4.jar"

$(bin): $(src)
	javac -cp $(CLASSPATH) $(src) -g -Xlint:deprecation -Xlint:unchecked

run: $(bin)
	java -cp $(CLASSPATH) LinkedStackOfStrings < tobe.txt

debug: $(bin)
	jdb -classpath $(CLASSPATH) LinkedStackOfStrings

libs:
	mkdir -p libs
	curl https://algs4.cs.princeton.edu/code/algs4.jar -o libs/algs4.jar
	unzip libs/algs4.jar -d libs/algs4

