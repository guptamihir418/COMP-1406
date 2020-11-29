//Mihir Gupta
// 101172281

//creating Desktop class.
public class Laptop extends Product{

    private double cpuSpeed, price, screenSize,revenue;
    private int ram, storage, stockQuantity, soldQuantity;
    private boolean ssd;


    //creating constructor with four arguments
    public  Laptop(double price, int quantity, double cpuSpeed, int ram, boolean ssd, int storage, double screenSize){

        super(price,quantity);
        this.stockQuantity = quantity;
        this.price = price;
        this.cpuSpeed = cpuSpeed;
        this.ram = ram;
        this.storage = storage;
        this.ssd = ssd;
        this.screenSize = screenSize;

    }

    public double sellUnits(int units) {
        stockQuantity = super.sendStock();
        soldQuantity = super.sendSold();
        revenue = super.sellUnits(units);
        return revenue;
    }


    //using toString method to return the string containing the objects.
    public String toString() {

        //return ssd if boolean ssd is true
        if (ssd == true) {

            return screenSize+" Laptop PC with " + cpuSpeed + "ghz CPU, " + ram + "GB RAM, " + storage + "GB " + "SSD drive"+"("+price+" dollars each,"+ stockQuantity+" in stock,"+soldQuantity+ " sold)";

        }
        //else return hdd drive
        return screenSize+" Laptop PC with " + cpuSpeed + "ghz CPU, " + ram + "GB RAM, " + storage + "GB " + "HDD drive"+"("+price+" dollars each,"+ stockQuantity+" in stock,"+soldQuantity+ " sold)";

    }

}

