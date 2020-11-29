//creating fridge class
public class Fridge {

    double size;
    String color;
    boolean freezer;

    //fridge constructor with 3 arguments
    public Fridge(double s, String c, boolean f) {

        size = s;
        color = c;
        freezer = f;
    }


    //using to string method to return the string of object.
    public String toString() {

        return size + "Cubic foot Fridge with Freezer" + "(" + color + ")";
    }
}

