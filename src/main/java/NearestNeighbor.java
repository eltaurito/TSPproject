import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class NearestNeighbor {

    public static Route nearestNeighbor(Point[] nodes) {
        int locDistance=0, min=Integer.MAX_VALUE, i=1;

        ArrayList<Point> cities=new ArrayList<>(Arrays.asList(nodes));
        Route newRoute=new Route();

        int id_rnd=Main.r.nextInt((nodes.length-1))+1;
        Point cityStart=cities.get(id_rnd);         //NB starting by that city

        //Remove comment if you wanna start from first city
        //cityStart=cities.get(42);


        Point firstCity=new Point(cityStart.getId(),cityStart.getX(),cityStart.getY()); //Saving the starting city
        Point citymin=null;

        //each time a city is added to newroute, i must remove it from cities

        cities.remove(cityStart);
        newRoute.add(cityStart);

        while (!cities.isEmpty()) {
            for (Point city : cities) {

                locDistance=Main.matrix[cityStart.getId()-1][city.getId()-1];
                if (locDistance < min) {
                    min=locDistance;
                    citymin=city;
                }
            }
            cities.remove(citymin);
            cityStart=citymin;

            newRoute.add(cityStart);
            min=Integer.MAX_VALUE;
        }
        newRoute.add(firstCity);
        return newRoute;

    }
}
