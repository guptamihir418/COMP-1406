//Mihir Gupta
// 101172281

import java.util.Scanner;
public class ElectronicStore {
    private final int MAX_PRODUCTS = 10;
    private String name;
    private double revenue;
    private Product[] products = new Product[MAX_PRODUCTS];

    Scanner j = new Scanner(System.in);

    public ElectronicStore(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public boolean addProduct(Product p) {
        for (int i = 0; i < MAX_PRODUCTS; i++) {
            if (products[i] == null) {
                products[i] = p;
                return true;

            }
        }
        return false;

    }

    public void sellProducts() {
        printStock();
        int index = -1, amount = -1;
        while (true) {
            System.out.println("Enter the index you want to buy?");
            index = j.nextInt();
            if (index < 1 || index > 10) {
                System.out.println("Entered the wrong number. Enter the number again");

            } else {
                break;

            }
        }
        while (true) {
            System.out.println("Enter the amount of item you want to buy?");
            amount = j.nextInt();
            if (amount < 0) {
                System.out.println("Entered the wrong amount. Enter the amount again");

            }
            else{
                break;
            }
            sellProducts(index, amount);
        }
    }
    public void sellProducts(int item, int amount){
        if (item >= 0 && item<MAX_PRODUCTS) {
            if (products[item] != null) {
                revenue += products[item].sellUnits(amount);
            }

        }

    }

    public void printStock() {
        for (int i = 0; i < MAX_PRODUCTS; i++) {
            if (products[i] == null) {
                break;
            } else {
                System.out.println(i + ". " + products[i].toString());
            }
        }
    }
    public double getRevenue(){
        return revenue;

    }

}
