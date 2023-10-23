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
        1. mvn dependency:tree
1. **Test Driven Development - TDD**
    1. Write a failing test.
    1. Write a code to make a test pass.
    1. Refactor code.
    1. Repeat a process.
1. Using Docker to [build image](/home-work/calc/Dockerfile) or [build Java JAR file](/home-work/calc/Dockerfile-mvn-cache).
    1. Using make cli
        1. make image version=1.0
        1. make image-build-cached version=1.0
1. Integration Testing - ***USING CURL cli***
   1. Test group of software modules or components(Controller - Service - Repository).
   1. Should be performed after Unit Test.

## JAVA SPRING BOOT - WEB API DEVELOPMENT

### MOCKITO

![Testing with mockito](/images/spring-boot-testing.png)

1. Development process
    1. Create Mock for DAO(Data Access Object) - **@MockBean**.
    1. Inject mock into Service - **@Autowired**.
    1. Set up expectation.
    1. Calls method under test and assert results.
    1. Verify method calls - **Optional**.

### SPRING BOOT MVC

![SPRING BOOT MVC](/images/spring-boot-mvc.png)

1. Write design document in Swagger
1. Repository Layer with Junit Test - [LocationRepositoryTest.java](/spring-boot-mvc-demo/src/test/java/com/github/springbootmvcdemo/repository/LocationRepositoryTest.java)
   1. Define Entity Class
      1. Using jackson-databind to bind data request to Java Objects with @JsonProperty and @JsonIgnore.
      1. Using spring-boot-starter-validation to use @Valid for validation HTTP Requests in Controller Classes.
   1. Define Table - [Location.java](/spring-boot-mvc-demo/src/main/java/com/github/springbootmvcdemo/models/Location.java)
1. Service Layer with Junit Test(optional)
1. Controller Layer with Junit Test - [LocationControllerTest.java](/spring-boot-mvc-demo/src/test/java/com/github/springbootmvcdemo/controllers/LocationControllerTest.java)
   1. @WebMvcTest: loads only Spring MVC components - REST Controllers.
   1. @SpringBootTest: TODO
   1. MockMvc: performs an API calls(HTTP Requests) and Assertion the responses.
   1. Mockito: fakes Service Objects(@WebMvcTest does not load Service classes).
      1. @MockBean: Using to inject object if require - fakes Service Objects.
   1. ObjectMapper: serializes Java Objects to JSON and vice versa.
      1. https://fasterxml.github.io/jackson-databind/javadoc/2.6/com/fasterxml/jackson/databind/ObjectMapper.html
   1. JSONPath expression to validate JSON in responses.
      1. https://github.com/json-path/JsonPath
1. Integration Test with curl and Postman
1. Implement Security
   1. [Keycloak stand a lone server](https://www.keycloak.org/).
   2. Using own JWT Implementation
      1. jjwt-impl
      2. jjwt-jackson
      3. create jwt folder
         1. controllers folder
         2. models folder
            1. Request Model - AuthenticationRequest
            2. Response Model - AuthenticationResponse
            3. User Model to implement UserDetails from Spring Security - UserPrincipal.
               1. getAuthorities() func - retrieves the list of authorizations of the user.
               2. isAccountNonLocked() func - which checks whether the user is locked
               3. isAccountNonExpired() func - which validates that the user is valid and not yet expired
               4. isEnabled() func - which checks whether the user is active.
         1. services folder
         2. filers(middleware) folder
         3. utils folder
1. Implement Caching - using redis
2. Implement Rate Limit
3. Document API 





















