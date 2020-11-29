//creating Desktop class.
public class Desktop {

    double CPU;
    int ram, storage;
    boolean drive;

    //creating constructor with four arguments
    public  Desktop(double c,int r, int s, boolean d){

        CPU = c;
        ram = r;
        storage = s;
        drive = d;
    }


    //using toString method to return the string containing the objects.
    public String toString() {

        //return ssd if drive is true
        if (drive == true) {

            return "Desktop PC with " + CPU + "ghz CPU, " + ram + "GB RAM, " + storage + "GB " + "SSD drive";

        }
        //else return hdd drive
        return "Desktop PC with " + CPU + "ghz CPU, " + ram + "GB RAM, " + storage + "GB " + "HDD drive";
    }

}
