test:
	mvn package clean && mvn compile exec:java -Dexec.mainClass="com.ardublock.Main"

compile:
	mvn clean package

install:
	mkdir -p ~/Arduino/tools/ArduBlockTool/tool
	cp target/ardublock-all.jar ~/Arduino/tools/ArduBlockTool/tool/ardublock-all.jar

install2:
	mkdir -p ~/sketchbook/tools/ArduBlockTool/tool
	cp target/ardublock-all.jar ~/sketchbook/tools/ArduBlockTool/tool/ardublock-all.jar
