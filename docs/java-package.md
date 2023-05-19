# JAVA PACKAGES

- Java packages are just folders
  - Allow you to structure your application to avoid naming conflicts.
  - Grouping of related types.
- [Content of Circle.java](java/src/com/alochym/shape/Circle.java).
- Folder structure

  ```java
  java/src/
  `-- com
      `-- alochym
          `-- shape
              `-- Circle.java
  ```

- java file
  - The definition of a class is saved in `a file with the same name as the class name` and a .java extension
  - Sample structure of java file extension

  ```java
  package <package_name>;

  import <package_name>;

  public class <class_name> {
    // Variable declarations

    // constructor methods

    // other methods definitions
  }
  ```
  
- [Naming Packages](https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html)
- ***[Access control modifiers](https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html)***.
- **TODO for module - advanced of packages**.