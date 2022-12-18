javadoc -d .\make\ -sourcepath .\src\main\java -subpackages ru.nsu.aramazanova

javac -d .\make\bin\ -sourcepath .\src\main\java .\src\main\java\ru\nsu\aramazanova\MyStack.java

mkdir .\make\jar
jar cf .\make\jar\MyStack.jar -C .\make\bin .

java -classpath .\make\jar\MyStack.jar ru.nsu.aramazanova.MyStack
