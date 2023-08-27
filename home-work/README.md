# JAVA CORE HOME WORK

1. Using [Maven](./docs/maven.md) to create project structure.
1. Learn [Java comment](https://google.github.io/styleguide/javaguide.html#s4.8.6-comments).
1. Learn Java Variables
    - [Naming conventions](https://google.github.io/styleguide/javaguide.html#s5.3-camel-case).
    - [Rule declare variable](https://google.github.io/styleguide/javaguide.html#s4.8.2-variable-declarations).
    - [Constant variable](https://google.github.io/styleguide/javaguide.html#s5.2.4-constant-names).
1. Learn [Java Primitive Data Type](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html).
1. Learn Java Method
    - [Define Methods](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html).
    - There are 2 types of methods
        - [Return a value from methods](https://docs.oracle.com/javase/tutorial/java/javaOO/returnvalue.html).

            ```java
            public class Calc {
                /*
                 * Input 2 or more Integer numbers.
                 * Output Total of numbers.
                 */ 
                public int sumNumbers(int... x){
                    var int total = 0;

                    for(int v : x){
                        total += v;
                    }

                    return total;
                }

                /*
                 * Input 2 Integer numbers.
                 * Output Total of 2 numbers.
                 */ 
                public int sumTwoNumbers(int x, int y){
                    return x + y;
                }
            }
            ```

        - No return value from methods.

            ```java
            public class Greeting {
                public void sayHello(){
                    System.out.println("Hello World");
                }
            }
            ```

1. Learn [Java Junit](https://junit.org/junit5/docs/current/user-guide/).
    1. Junit lifecycle

        ```bash
        @BeforeAll

            @BeforeEach
                @Test Method One
            @AfterEach
        
            @BeforeEach
                @Test Method Two
            @AfterEach
        
        @AfterAll
        ```

    1. Development process
        1. Add Maven dependancy pakacges for Junit.

            ```xml
            <dependency>
                <groupId>org.junit.jupiter</groupId>
                <artifactId>junit-jupiter</artifactId>
                <version>5.9.2</version>
                <scope>test</scope>
            </dependency>
            ```
        1. Create test packages.
        1. Create unit test.
            1. Setup steps - Create an instance of class to test.
            1. Execute steps - Call method you want to test.
            1. Assert steps - check result and verify that if it is expected result.
        1. Run unit test.
    1. [Code example](/home-work/calc/src/test/java/com/github/calculator/CalculatorTest.java).
    1. Run test code
        1. mvn test
        1. mvn test -Dtest=CalculatorTest#testSumTwoNumber
            1. CalculatorTest is the test class name.
            1. testSumTwoNumber is the test method.
        1. mvn test -Dtest=CalculatorTest
        1. mvn test -Dtest=AppTest#shouldAnswerWithTrue
            1. AppTest is the test class name.
            1. shouldAnswerWithTrue is the test method.
1. Using Docker to [build image](/home-work/calc/Dockerfile) or [build Java JAR file](/home-work/calc/Dockerfile-mvn-cache).
    1. Using make cli
        1. make image version=1.0
        1. make image-build-cached version=1.0

## JAVA SPRING BOOT - WEB API DEVELOPMENT

### 