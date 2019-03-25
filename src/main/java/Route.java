import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Route {
    private Point[] route;
    private int distance;
    private int[][] matrix;
    private int cnt;
    public Route(int[][] matrix){
        distance=0;
        this.matrix=matrix.clone();
        cnt=0;
        route=new Point[matrix.length+1];
        //route=new Point[matrix.length];
    }
    public Route(int[][] matrix,Point[] route){
        distance=0;
        this.matrix=matrix.clone();
        cnt=0;
        this.route=route.clone();
    }

    public void setRoute(Point[] x){
        route=x;

    }
    public void add(Point p){
        if(cnt<route.length){
            route[cnt]=p;
            cnt++;
        }
    }
    public boolean routeContains(Point p){
        return new ArrayList<>(Arrays.asList(route)).contains(p);
    }
    public int routeSize(){
        return route.length;
    }
    public Point getPoint(int index){
        try {
            return  route[index];
        }catch (Exception e)
        {
            return null;
        }
    }
    public void setPoint(int index,Point p){
        route[index]=p;
    }
    public Point[] getRoute(){
        return route;
    }
    public int getDistance(){
        return distance;
    }
    public int getRealDistance(){
        int distance=0;
        for (int i=0; i <route.length-1 ; i++) {
            int cur=route[i].getId()-1;
            int next=route[i+1].getId()-1;
            distance+=matrix[cur][next];
        }
        return distance;
    }



    public void printToFile(String path) throws FileNotFoundException {
        PrintWriter out=new PrintWriter(path);
        for (Point p:
             route) {
           out.println(p.getX()+"-"+p.getY());
        }
        out.close();

    }



}
