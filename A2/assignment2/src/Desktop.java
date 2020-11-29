//creating Desktop class.
public class Desktop extends Product{

    private double cpuSpeed, price, revenue;
    private int ram, storage, stockQuantity, soldQuantity;
    private boolean ssd;
    private String profile;

    //creating constructor with four arguments
    public  Desktop(double price, int quantity, double cpuSpeed, int ram, boolean ssd, int storage, String profile){
        super(price,quantity);
        this.stockQuantity = quantity;
        this.price = price;
        this.cpuSpeed = cpuSpeed;
        this.ram = ram;
        this.storage = storage;
        this.ssd = ssd;
        this.profile = profile;
        soldQuantity = 0;
        revenue = 0;

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

            return profile+" Desktop PC with " + cpuSpeed + "ghz CPU, " + ram + "GB RAM, " + storage + "GB " + "SSD drive"+"("+price+" dollars each,"+ stockQuantity+" in stock,"+soldQuantity+ " sold)";

        }
        //else return hdd drive
        return profile+" Desktop PC with " + cpuSpeed + "ghz CPU, " + ram + "GB RAM, " + storage + "GB " + "HDD drive"+"("+price+" dollars each,"+ stockQuantity+" in stock,"+soldQuantity+ " sold)";

    }

}
