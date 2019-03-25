import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static int[][] matrix=null;

    public static void main(String[] args) throws IOException {
        String filename=args[0];
        FileReader file=null;
        Point[] nodes=null;


        int nNodes=0;
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
                nNodes=Integer.parseInt(line.split(" ")[1]); //n of cities
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
        for (int j=0; j < nNodes; j++) {                    //create matrix of distance
            for (int k=0; k < nNodes; k++) {
                matrix[j][k]=Point.euclideanDistance(nodes[j],nodes[k]);
            }
        }

        Route routeNb=NearestNeighboor.nearestNeighboor(nNodes,nodes,matrix);
        System.out.println("Distance NB "+routeNb.getRealDistance());
        Route nuova=new twoOpt(matrix,nodes,routeNb).start();
        System.out.println("Distance 2OPT "+nuova.getRealDistance());

        //Route visitednew=new twoOpt(matrix,nodes,routeNb).start();
        //System.out.println("Distance 2OPT "+visitednew.getRealDistance());
        //System.out.println("DIstance 2opt "+visitednew.getDistance());


        routeNb.printToFile("C:\\Users\\Davide\\Desktop\\NB.txt");
        nuova.printToFile("C:\\Users\\Davide\\Desktop\\output2.txt");

    }


}
