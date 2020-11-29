/* Assignment 1
Made by Mihir Gupta
Student number: 101172281
Deadline: 31st january,2020
 */




// importing Scanner
import java.util.Scanner;

//creating ElectronicStoreTester class
// this is the main class that runs all the code from different classes.
public class ElectronicStoreTester{
    public static void main(String[] args){

        // calling the electronicStore class
        ElectronicStore e1;

        //using constructor of electronicStore class
        //name of the store is "Electronic Store".
        e1 = new ElectronicStore("Electronic Store");

        //giving some space before the system starts printing items.
        System.out.println("");

        //Printing the introduction
        //not necessary
        System.out.println("\tWelcome to the "+e1.name);

        //calling thr printStock method from electronicStore class to print the items.
        e1.printStock();

        //calling scanner method.
        Scanner input = new Scanner(System.in);

        //creating the infinite while loop, that would prompt user to type the name of items
        // until user input quit
        while (true) {

            System.out.println("Enter the name of item you want to check. ");

            //taking input from the user.
            String stringCheck = input.nextLine();

            //calling the searchStock method from electronicStore class and comparing the strings.
            if (e1.searchStock(stringCheck)) {

                // if the string entered matches the item in the list would print this.
                System.out.println("The matching item is in the inventory");

            }
            //if the entered string is "quit" would end the loop
            else if(stringCheck.contentEquals("quit")||stringCheck.contentEquals("Quit")){
                System.out.println("Goodbye");
                break;
            }

            //if the string entered doesn't match the items, will print this.
            else {
                System.out.println("The matching item is not in the inventory.");
            }

        }


    }
}