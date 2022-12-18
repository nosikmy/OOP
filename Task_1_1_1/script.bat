javadoc -d .\make\ -sourcepath .\src\main\java -subpackages ru.nsu.aramazanova

javac -d .\make\bin\ -sourcepath .\src\main\java .\src\main\java\ru\nsu\aramazanova\HeapSort.java

mkdir .\make\jar
jar cf .\make\jar\HeapSort.jar -C .\make\bin .

java -classpath .\make\jar\HeapSort.jar ru.nsu.aramazanova.HeapSort
