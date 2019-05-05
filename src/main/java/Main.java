import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Main {
    static int[][] matrix;
    static int bestResult;
    static long seed;
    static String filename;
    static  Random r;
    static long beginTime;
    public static int iterationsNumber;
    public static int iterationsNumberArg;

    public static void main(String[] args) throws IOException {
        matrix=null;
        bestResult=0;
        seed=System.currentTimeMillis();
        filename="";
        r=new Random();
        beginTime=System.currentTimeMillis();
        iterationsNumber=0;
        iterationsNumberArg=0;

        Point[] initialCities=null;
        if (args.length == 0) System.out.println("java -jar AlgoritmiProgetto.jar filename.tsp [seed]");
        if (args.length >= 1) {
            filename=args[0];
            System.out.println("Taurisano TSP Opt: "+filename);
        }
        if (args.length >= 2) {
            seed=Long.parseLong(args[1]);

        }
        if (args.length >= 3) {
            iterationsNumberArg=Integer.parseInt(args[2]);
            System.out.println("Iterations to be done: "+iterationsNumberArg);
        }
        System.out.println("Seed: "+seed);
        r.setSeed(seed);
        initialCities=getCities(initialCities,filename);
        initializeMatrix(initialCities);

        Validator val=new Validator();  //Class used to check the path's validation

        Route routeNb=NearestNeighbor.nearestNeighbor(initialCities);
        System.out.println("Distance NB "+routeNb.getRealDistance());


        Route nuova=new TwoOpt(routeNb).start();
        System.out.println("Distance 2OPT "+nuova.getRealDistance());



        Route nuova2=new SimulatedAnnealing(nuova.getRoute()).start();
        bestResult=nuova2.getRealDistance();
        System.out.println("Distance SA "+(bestResult)+"\nNumber of iterations: "+nuova2.getIterationNumber());



        nuova2.printToFileTour("OPT_"+filename+".tour",filename,initialCities.length);
        //TspManager tsp=new TspManager();
       // tsp.writeSolution("Davide_Taurisano_results.txt");

        System.out.println("EXECUTION TIME: "+(System.currentTimeMillis()-beginTime));
    }

    private static Point[] getCities(Point[] nodes,String filename) throws IOException {

        FileReader file=null;
        try {
            file=new FileReader(Main.class.getResource(filename).getFile());
            //file=new FileReader(new File(filename));
        } catch (Exception e) {
            System.out.println(filename+" File not found.");
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
            if (i == 6) {
                bestResult=Integer.parseInt((line.split(":")[1]).trim()); //n of cities
            }
            if (i > 7) {
                String[] lines=line.split(" ");
                //System.out.println(lines[0]);
                nodes[Integer.parseInt(lines[0])-1]=            //i = city.id -1
                        new Point(Integer.parseInt(lines[0]),   //id
                                Double.parseDouble(lines[1]),   //coord x
                                Double.parseDouble(lines[2]));  //coord y

            }
            i++;

        }
        return nodes;
    }

    public static int getDistance(Point city_from,Point city_to) {
        return matrix[city_from.getId()-1][city_to.getId()-1];
    }

    private static void initializeMatrix(Point[] nodes) {
        for (int j=0; j < nodes.length; j++) {                    //create matrix of distance
            for (int k=0; k < nodes.length; k++) {
                matrix[j][k]=Point.euclideanDistance(nodes[j],nodes[k]);
            }
        }
    }


}
