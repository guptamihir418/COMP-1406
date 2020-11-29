//Mihir Gupta
// 101172281

// creating product class
// its a parent class
public class Product {

    // Creating private variables and using encapsulation
    private double price;
    private int stockQuantity;
    private int soldQuantity;

    //creating constructor that  will be inherited to other class.
    public Product(double price, int quantity) {
        this.price = price;
        this.stockQuantity = quantity;


    }

    //creating sellUnits method
    public double sellUnits(int units) {
        if (stockQuantity >= units && units>=0) {
            stockQuantity -= units;
            soldQuantity += units;
            return units * price;

        } else {
            return 0.0;

        }
    }
    // using getter method to return stockQuantity;
    int sendStock(){
        return stockQuantity;
    }
    // using getter method to return soldQuantity.
    int sendSold(){
        return soldQuantity;
    }
}


