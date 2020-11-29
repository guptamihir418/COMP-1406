//Mihir Gupta
// 101172281


//creating public class and inherited from product.
public class ToasterOven extends Product {

    //using encapsulation
    private double price, revenue;
    private int wattage, stockQuantity, soldQuantity,width;
    private boolean convection;
    private String brand, color;

    public ToasterOven(double price, int quantity, int wattage, String color, String brand, int width, boolean convection) {
        super(price, quantity);

        this.stockQuantity = quantity;
        this.price = price;
        this.wattage = wattage;
        this.color = color;
        this.brand = brand;
        this.convection = convection ;
        this.width = width;
        revenue = 0;
        soldQuantity = 0;
    }

    public double sellUnits(int units) {
        stockQuantity = super.sendStock();
        soldQuantity = super.sendSold();
        revenue = super.sellUnits(units);
        return revenue;
    }

    public String toString() {


        if (convection == true) {

            return width+" inch "+brand+" Toaster with convection(" + color+ ","+ wattage + " watts)"+" ("+price+" dollars each," + stockQuantity +" in stock,"+ soldQuantity + " sold)";

        }

        return width+" inch "+brand+" Toaster(" + color+ ","+ wattage + " watts)"+" ("+price+" dollars each," + stockQuantity +" in stock,"+ soldQuantity + " sold)";

    }

}
