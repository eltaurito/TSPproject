import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static int[][] matrix=null;

    public static void main(String[] args) throws IOException {
        Point[] initialCities=null;
        String filename=args[0];
        initialCities=getCities(initialCities,filename);
        initializeMatrix(initialCities);

        //Class used to check the path's validation
        Validator val=new Validator();


        //Starting with NB
        Route routeNb=NearestNeighboor.nearestNeighboor(initialCities);
        System.out.println("Distance NB "+routeNb.getRealDistance());
        System.out.println(val.Validate(routeNb.getRoute()));
        //***********************************

        //Start with 2opt with NBroute as input
        Route nuova=new twoOpt(routeNb).start();
        System.out.println("Distance 2OPT "+nuova.getRealDistance());
        System.out.println(val.Validate(nuova.getRoute()));
        //***********************************



        routeNb.printToFile("C:\\Users\\Davide\\Desktop\\NB.txt");
        nuova.printToFile("C:\\Users\\Davide\\Desktop\\output2.txt");

    }

    private static Point[] getCities(Point[] nodes,String filename) throws IOException {
        FileReader file=null;
        try {
            file=new FileReader(Main.class.getResource(filename).getFile());
        } catch (Exception e) {
            System.out.println("File not found.");
        }

        BufferedReader b=new BufferedReader(file);
        String line=null; //I'm going to read the file line by line
        int i=1, id=0;
        while ((line=b.readLine()) != null) {
            if (line.equals("EOF")) break;

            if (i == 4) {
                int nNodes=Integer.parseInt(line.split(" ")[1]); //n of cities
                nodes=new Point[nNodes];
                matrix=new int[nNodes][nNodes];
            }
            if (i > 7) {
                String[] lines=line.split(" ");
                nodes[Integer.parseInt(lines[0])-1]=            //i = city.id -1
                        new Point(Integer.parseInt(lines[0]),   //id
                                Double.parseDouble(lines[1]),   //coord x
                                Double.parseDouble(lines[2]));  //coord y

            }
            i++;

        }
        return nodes;
    }

    private static void initializeMatrix(Point[] nodes) {
        for (int j=0; j < nodes.length; j++) {                    //create matrix of distance
            for (int k=0; k < nodes.length; k++) {
                matrix[j][k]=Point.euclideanDistance(nodes[j],nodes[k]);
            }
        }
    }


}
