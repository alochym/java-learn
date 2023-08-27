# Create Java Project with MAVEN

- `mvn archetype:generate`

  ```bash
    Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 2072: 
    Choose org.apache.maven.archetypes:maven-archetype-quickstart version: 
    1: 1.0-alpha-1
    2: 1.0-alpha-2
    3: 1.0-alpha-3
    4: 1.0-alpha-4
    5: 1.0
    6: 1.1
    7: 1.3
    8: 1.4
    Choose a number: 8: 
    Define value for property 'groupId': com.github
    Define value for property 'artifactId': calc
    Define value for property 'version' 1.0-SNAPSHOT: : 
    Define value for property 'package' com.github: : 
    Confirm properties configuration:
    groupId: com.github
    artifactId: calc
    version: 1.0-SNAPSHOT
    package: com.github
    Y: : Y
  ```

- Folder strcuture

  ```bash
  calc/
    ├── pom.xml
    └── src
        ├── main
        │   └── java
        │       └── com
        │           └── github
        │               └── App.java
        └── test
            └── java
                └── com
                    └── github
                        └── AppTest.java
  ```

- Update [pom.xml](../calc/pom.xml) using Junit 5

  ```yaml
  <!-- <dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.11</version>
    <scope>test</scope>
  </dependency> -->
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.9.2</version>
    <scope>test</scope>
  </dependency>
  ```