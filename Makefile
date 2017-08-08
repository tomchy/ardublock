test:
	mvn package clean && mvn compile exec:java -Dexec.mainClass="com.ardublock.Main"

compile:
	mvn clean package

install:
	cp target/ardublock-all.jar ~/Arduino/tools/ArduBlockTool/tool/ardublock-all.jar
