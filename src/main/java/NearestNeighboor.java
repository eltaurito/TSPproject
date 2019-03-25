import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class NearestNeighboor {

    public static Route nearestNeighboor(int nNodes,Point[] nodes,int[][] matrix){
        System.out.println("---------Computing Nearest neighboor---------");

        Route visited=new Route(matrix,nodes);
        int locDistance=0;

        int min=Integer.MAX_VALUE,i=1;

        ArrayList<Point> cities=new ArrayList<>(Arrays.asList(nodes));
        Route newRoute=new Route(matrix);
        int id_rnd=new Random().nextInt((nNodes-1))+1;
        Point cityStart=cities.get(id_rnd);
        Point firstCity=new Point(cityStart.getId(),
                cityStart.getX(),
                cityStart.getY());
        Point citymin=null;
        cities.remove(cityStart);
        newRoute.add(cityStart);
        int tot=0;
        int index=1;
        while(!cities.isEmpty()){
            for (Point city:
                 cities) {
                locDistance=matrix[cityStart.getId()-1][city.getId()-1];
                //System.out.println("Check "+cityStart.getId()+ " with "+city.getId());
                if(locDistance<min){
                  //  System.out.println("find "+locDistance);
                    min=locDistance;
                    citymin=city;
                }
            }
            cities.remove(citymin);
            cityStart=citymin;
            //tot+=min;
            //System.out.println(index+" : "+min+ " tot "+tot);
            index++;
            newRoute.add(cityStart);
            min=Integer.MAX_VALUE;
        }
        newRoute.add(firstCity);
        return newRoute;

    }
}
