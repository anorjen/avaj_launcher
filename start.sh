javac -sourcepath ./src -d out src/ru/_21_school/AvajLauncher.java
jar -cmf manifest.mf avaj.jar -C out .
java -jar avaj.jar scenario.txt
