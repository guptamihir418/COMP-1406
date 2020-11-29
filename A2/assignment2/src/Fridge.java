//Mihir Gupta
// 101172281



//Creating fridge class inherited from product
public class Fridge extends Product {

    // creating every instance variable to private i.e. using encapsulation
    private double cubicFeet, price, revenue;
    private int wattage, stockQuantity, soldQuantity;
    private boolean hasFreezer;
    private String brand, color;

    // creating constructor
    public  Fridge(double price, int quantity, int wattage, String color, String brand, double cubicFeet, boolean freezer) {

        //using super for inheritance
        super(price, quantity);
        this.stockQuantity = quantity;
        this.price = price;
        this.wattage = wattage;
        this.color = color;
        this.brand = brand;
        this.cubicFeet = cubicFeet;
        this.hasFreezer = freezer;
        revenue = 0;
        stockQuantity=0;
    }
    // using inheritance from product class;
    public double sellUnits(int units) {
        stockQuantity = super.sendStock();
        soldQuantity = super.sendSold();
        revenue = super.sellUnits(units);
        return revenue;
    }


    // return the object properties as described in assignment specification
        public String toString() {


        // if the fridge has freezer it will return this branch.
            if (hasFreezer == true) {

                return cubicFeet+" cu. ft. "+brand+" Fridge with Freezer(" + color+ ","+ wattage + " watts)"+" ("+price+" dollars each," + stockQuantity +" in stock,"+ soldQuantity + " sold)";

            }

            //if the fridge doesn't have freezer it will return this branch
            return cubicFeet+" cu. ft. "+brand+" Fridge(" + color+ ","+ wattage + " watts)"+" ("+price+" dollars each," + stockQuantity +" in stock,"+ soldQuantity + " sold)";

        }





    }
