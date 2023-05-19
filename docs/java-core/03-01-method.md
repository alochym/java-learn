# JAVA METHOD

- [Define Methods](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html).
- There are 2 types of methods
  - [Return a value from methods](https://docs.oracle.com/javase/tutorial/java/javaOO/returnvalue.html).

    ```java
    public class Alochym {
        public int sumNumbers(int... x){
            var int total = 0;
            
            for(int v : x){
                total += v;
            }

            return total;
        }
    }
    ```
  - No return value from methods.

    ```java
    public class Alochym {
        public void sayHello(){
            System.out.println("Hello World");
        }
    }
    ```