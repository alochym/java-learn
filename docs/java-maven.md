# JAVA MAVEN

## Installation

- [Download Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html#installation)
- [Window Prerequisites](https://maven.apache.org/guides/getting-started/windows-prerequisites.html).
- [Configuration Maven](https://maven.apache.org/guides/mini/guide-configuring-maven.html)

## CLI

1. Create project

   ```bash
   mvn archetype:generate \
       -DgroupId=com.alochym \
       -DartifactId=calculator-app \
       -DarchetypeArtifactId=maven-archetype-quickstart \
       -DarchetypeVersion=1.4 \
       -DinteractiveMode=true

   # (1) groupId
   # (2) artifactId
   # (3) archetypetypeArtifactId
   #        2048: remote -> org.apache.maven.archetypes:maven-archetype-quickstart (An archetype which contains a sample Maven project.)
   # (4) archetypeVersion - related to (3)

   # sample values
   #   groupId: com.alochym
   #   artifactId: calculator-app
   #   version: 1.0-SNAPSHOT
   #   package: com.alochym
   ```

2. We should go to root directory of source code - `calculator-app` folder.
3. Update [pom.xml](./calculator-app/pom.xml) to use the `maven-jar-plugin` for ***Add A Class-Path Entry To The [Manifest](https://docs.oracle.com/javase/tutorial/deployment/jar/manifestindex.html)***.
4. Compile source code - `mvn compile` or ***we can run `mvn test` to test our unit test***.
5. Create a `jar` file - `mvn package`.
6. Check content of `jar` file - `jar tf .\target\calculator-app-1.0-SNAPSHOT.jar`
7. Test the `jar` file - `java -jar .\target\calculator-app-1.0-SNAPSHOT.jar`.

## JAR command not found - Windows 

- Edit `Enviroment Variables > System Variables > Path` > Edit > New > `C:\Program Files\Java\jdk-19\bin`

## REFERENCE LINKS
- [Create Project](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html#creating-a-project).
- [Build Package]().
- [Run Test](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html#maven-phases). 
- [Maven in 5 min](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html).
- [Java MANIFEST FILE](https://docs.oracle.com/javase/tutorial/deployment/jar/manifestindex.html).