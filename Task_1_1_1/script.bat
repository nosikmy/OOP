javadoc -d .\make\ -sourcepath .\src\main\java -subpackages ru.nsu.aramazanova

javac -d .\make\bin\ -sourcepath .\src\main\java .\src\main\java\ru\nsu\aramazanova\Main.java

mkdir .\make\jar
jar cf .\make\jar\Main.jar -C .\make\bin .
