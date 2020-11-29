// creating electronic class
public class ElectronicStore {

    String name;

    //creating arrays of desktop, fridge for items to store in these arrays.
    Desktop desktops[];
    Laptop laptops[];
    Fridge fridges[];

//electronic class constructor with an argument, i.e. name of the store
    public ElectronicStore(String n) {
        name = n;


        //using constructor of the other classes to construct items
        Desktop d1, d2, d3;
        Laptop l1, l2, l3;
        Fridge f1, f2, f3;

        d1 = new Desktop(3.5, 8, 500, false);
        d2 = new Desktop(3.0, 16, 250, true);
        d3 = new Desktop(4.3, 32, 500, true);

        l1 = new Laptop(3.1, 32, 500, true, 15);
        l2 = new Laptop(2.5, 8, 250, false, 13);
        l3 = new Laptop(3.0, 16, 250, true, 15);

        f1 = new Fridge(16.5, "Black", true);
        f2 = new Fridge(12.0, "White", false);
        f3 = new Fridge(23.0, "Stainless steel", true);


        //Storing the items in the arrays.
        desktops = new Desktop[3];
        desktops[0] = d1;
        desktops[1] = d2;
        desktops[2] = d3;

        laptops = new Laptop[3];
        laptops[0] = l1;
        laptops[1] = l2;
        laptops[2] = l3;

        fridges = new Fridge[3];
        fridges[0] = f1;
        fridges[1] = f2;
        fridges[2] = f3;


    }

    //creating printStock void method for printing items in the array list.
    public void printStock() {

        //Using for loop to iterate through arrays and print items.
        for (int index = 0; index <= desktops.length - 1; index++) {
            System.out.println(desktops[index].toString());
        }
        System.out.println();
        for (int index = 0; index <= laptops.length - 1; index++) {
            System.out.println(laptops[index].toString());
        }
        System.out.println();
        for (int index = 0; index <= fridges.length - 1; index++) {
            System.out.println(fridges[index].toString());
        }
        System.out.println();
    }


    //creating boolean searchStock method that takes string as an argument and search for the items typed by the user.
    public boolean searchStock(String name) {

        //using for loop to iterate over things in the arrays and finding the entered items in the list of items.
        for (int index = 0; index <= fridges.length-1 ; index++) {


            //using if statement to find the substring contained by the strings.
            //using s1.contains(s1) for finding the substrings
            //using .toLowerCase to make the strings case insensitive
            // returning true if the conditions are meat.
            if (fridges[index].toString().toLowerCase().contains(name.toLowerCase())|| laptops[index].toString().toLowerCase().contains(name.toLowerCase())||desktops[index].toString().toLowerCase().contains(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}