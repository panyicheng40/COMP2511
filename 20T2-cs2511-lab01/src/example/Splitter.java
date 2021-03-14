package example;
import java.util.Scanner;
public class Splitter {

    public static void main(String[] args){
        System.out.println("Enter a sentence specified by spaces only: ");
        // Add your code
        Scanner sentence = new Scanner(System.in);
        while (sentence.hasNextLine()) {
        	String str = sentence.next();
        	System.out.println(str);
        }
        sentence.close();
    }
}
