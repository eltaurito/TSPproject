import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static int[][] matrix=null;
    static int bestResult=0;
    static long seed=System.currentTimeMillis();
    static String filename="";
    static final Random r=new Random();
    static long beginTime=System.currentTimeMillis();
    public static void main(String[] args) throws IOException {

        Point[] initialCities=null;
        if(args.length==0)
            System.out.println("java -jar AlgoritmiProgetto.jar filename.tsp [seed]");
        if(args.length==1)
            filename=args[0];
        if(args.length==2)
            seed=Long.parseLong(args[1]);
        System.out.println("Taurisano TSP Opt: "+filename);
        initialCities=getCities(initialCities,filename);
        initializeMatrix(initialCities);
        //Class used to check the path's validation
        Validator val=new Validator();

        //Point[][] candidates=CandidateList.findCandidates(initialCities,15);


        //Starting with NB
        Route routeNb=NearestNeighboor.nearestNeighboor(initialCities);
        //System.out.println("Distance NB "+routeNb.getRealDistance());
        //System.out.println(val.Validate(routeNb.getRoute()));
        //***********************************

        //Start with 2opt with NBroute as input
        Route nuova=new twoOpt(routeNb).start();
        //System.out.println("Distance 2OPT "+nuova.getRealDistance());
        //System.out.println(val.Validate(nuova.getRoute()));
        //***********************************

        //Start with SA with 2opt as input
        Route nuova2=new SimulatedAnnealing(nuova.getRoute()).start();
        bestResult=nuova2.getRealDistance();
        System.out.println("Distance SA "+(bestResult));
        System.out.println(val.Validate(nuova2.getRoute()));
        //***********************************

        //routeNb.printToFile("C:\\Users\\Davide\\Desktop\\NB.txt");
        //nuova.printToFile("C:\\Users\\Davide\\Desktop\\2OPT.txt");
        //nuova2.printToFile("C:\\Users\\Davide\\Desktop\\SA.txt");
        nuova2.printToFileTour("SA_"+filename+".tour",filename,initialCities.length);
        TspManager tsp=new TspManager();
        tsp.writeSolution("Davide_Taurisano_results.txt");

    }

    private static Point[] getCities(Point[] nodes,String filename) throws IOException {

        FileReader file=null;
        try {
            //file=new FileReader(Main.class.getResource(filename).getFile());
            file=new FileReader(new File(filename));
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
            if(i==6){
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
    public static int getDistance(Point city_from,Point city_to){
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
