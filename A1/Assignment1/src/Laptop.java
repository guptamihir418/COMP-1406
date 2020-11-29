//creating laptop class
public class Laptop {

        double CPU;
        int ram, storage, screenSize;
        boolean drive;


        //creating constructor with five arguments.
        public  Laptop(double c,int r, int s, boolean d,int ss){

            CPU = c;
            ram = r;
            storage = s;
            drive = d;
            screenSize = ss;
        }


        //using toString method to return strings indicating object
        public String toString() {

            //condition for boolean drive if true will return ssd drive
                if (drive == true) {
                    return screenSize+"\""+" Laptop PC with " + CPU + "ghz CPU, " + ram + "GB RAM, " + storage + "GB " + "SSD drive";
                }
                // else will return hdd drive
                return screenSize+"\""+" Laptop PC with " + CPU + "ghz CPU, " + ram + "GB RAM, " + storage + "GB " + "HDD drive";
        }
}

