// https://google.github.io/styleguide/javaguide.html#s3-source-file-structure

public class helloworld {
    /*
     * public - So that JVM can execute the method from anywhere.
     * static
     * - The main method is to be called without an object.
     * - The modifiers public and static can be written in either order.
     * void - The main method doesn’t return anything.
     * main(String args[]) - Name configured in the JVM.
     * - The main method must be inside the class definition.
     * - The compiler executes the codes starting always from the main function.
     * String[]
     * - The main method accepts a single argument,
     * i.e., an array of elements of type String.
     */
    public static void main(String args[]) {
        System.out.println("hello world");
    }
}