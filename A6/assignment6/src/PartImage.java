
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PartImage {

    private boolean[][] pixels;
    private boolean[][] visited;
    private int rows;
    private int cols;

    //Creates a new, blank PartImage with the given rows (r) and columns (c)
    public PartImage(int r, int c) {
        rows = r;
        cols = c;
        visited = new boolean[r][c];
        pixels = new boolean[r][c];
    }

    //Creates a new PartImage containing rw rows and cl columns
    //Initializes the 2D boolean pixel array based on the provided byte data
    //A 0 in the byte data is treated as false, a 1 is treated as true
    public PartImage(int rw, int cl, byte[][] data) {
        this(rw, cl);
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (data[r][c] == 1) {
                    pixels[r][c] = true;
                } else {
                    pixels[r][c] = false;
                }
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean getPixel(int r, int c) {
        return pixels[r][c];
    }

    public void print() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean pixel = pixels[i][j];
                System.out.print(pixel ? "*" : "-");
            }
            System.out.println(); // next line
        }

    }

    public Point2D findStart() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean pixel = pixels[i][j];
                if (pixel) {
                    return new Point2D(i, j);
                }
            }
        }
        return null;
    }

    public int partSize() {
        int size = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean pixel = pixels[i][j];
                if (pixel) {
                    size++;
                }
            }
        }
        return size;
    }

    private void expandFrom(int r, int c) {

        if (r < 0 || r > rows - 1 || c < 0 || c > cols - 1) {
            return;
        } else if (visited[r][c] || !pixels[r][c]) {
            return;
        }

        visited[r][c] = true; // visited true
        pixels[r][c] = false; // black to white

        expandFrom(r, c + 1); // go right
        expandFrom(r + 1, c); // go down
        expandFrom(r, c - 1); // go left
        expandFrom(r - 1, c); // go up
    }

    private int perimeterOf(int r, int c) {

        int perimeter = 0;
        // check whether the pixel is out of the border and then count the perimeter
        if (r < 0) {
            perimeter++;
        }
        if (r > rows - 1) {
            perimeter++;
        }
        if (c < 0) {
            perimeter++;
        }
        if (c > cols - 1) {
            perimeter++;
        }
        
        // if this pixel is not an valid pixel
        if(perimeter != 0) {
            return perimeter;
        }
        
        // if the pixel is visited
        if(visited[r][c]) {
            // return 0 perimeter
            return 0;
        } else if(!pixels[r][c]) {
            // if it is a white pixel perimeter count is 1
            return 1;
        }

        visited[r][c] = true; // visited true

        // go through all the pixels and find count the perimeters and return the total
        return perimeter + perimeterOf(r, c + 1) + perimeterOf(r + 1, c) 
                + perimeterOf(r, c - 1) + perimeterOf(r - 1, c);
    }

    public boolean isBroken() {
        Point2D p = findStart();
        expandFrom((int) p.getX(), (int) p.getY());
        return (partSize() != 0);
    }

    public int perimeter() {
        Point2D p = findStart();
        return perimeterOf((int) p.getX(), (int) p.getY());
    }

    public int countPieces() {
        int count = 1;
        // check how many times need to expand the whole image untill not broken  
        while (isBroken()) {         
            count++;
        }
        return count;
    }

    public static PartImage readFromFile(String fileName) throws InvalidPartImageException {

        PartImage partImage = null;

        try {
            File file = new File(fileName);
            // open the given file to scan
            Scanner scanner = new Scanner(file);

            // read the size of the image
            int rows = 0, columns = 0;

            // read line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                int currentColumns = line.split(",").length;
                if (rows > 0) {
                    // if the number of pixels in all rows are different
                    if (currentColumns != columns) {
                        // throw an invalid part image exception
                        throw new InvalidPartImageException(fileName);
                    }
                }
                columns = currentColumns;
                rows++; // count the rows
            }
            scanner.close();

            byte[][] data = new byte[rows][columns];

            // read the data
            // open another scanner
            scanner = new Scanner(file);

            for (int i = 0; i < 10; i++) { // each row

                // read the line
                String line = scanner.nextLine().trim();
                String[] dataStr = line.split(",");

                // each column
                for (int j = 0; j < columns; j++) {
                    switch (dataStr[j]) {
                        case "0":
                            data[i][j] = 0;
                            break;
                        case "1":
                            data[i][j] = 1;
                            break;
                        default:
                            // throw an invalid part image exception
                            throw new InvalidPartImageException(fileName);

                    }
                }

            }

            // now define the part image instance
            partImage = new PartImage(rows, columns, data);

            scanner.close();
        } catch (IOException ex) {
            return null;
        }

        return partImage;
    }
}
