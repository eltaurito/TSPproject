import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Route {
    private Point[] route;
    private int distance;
    private int cnt;

    public int getIterationNumber() {
        return iterationNumber;
    }

    private int iterationNumber;



    public Route() {
        distance=0;
        cnt=0;
        route=new Point[Main.matrix.length+1];

    }

    public Route(Point[] route) {
        distance=0;
        cnt=0;
        this.route=route.clone();
    }

    public void setRoute(Point[] x) {
        route=x;

    }

    public void add(Point p) {
        if (cnt < route.length) {
            route[cnt]=p;
            cnt++;
        }
    }

    public boolean routeContains(Point p) {
        return new ArrayList<>(Arrays.asList(route)).contains(p);
    }

    public int routeSize() {
        return route.length;
    }

    public Point getPoint(int index) {
        try {
            return route[index];
        } catch (Exception e) {
            return null;
        }
    }

    public void setPoint(int index,Point p) {
        route[index]=p;
    }

    public Point[] getRoute() {
        return route;
    }

    public int getDistance() {
        return distance;
    }

    public int getRealDistance() {
        int distance=0;
        for (int i=0; i < route.length-1; i++) {
            int cur=route[i].getId()-1;
            int next=route[i+1].getId()-1;
            distance+=Main.matrix[cur][next];
        }
        return distance;
    }



    public void printToFile(String path) throws FileNotFoundException {
        PrintWriter out=new PrintWriter(path);
        for (Point p : route) {
            out.println(p.getX()+"-"+p.getY());
        }
        out.close();

    }
    public double distLastToFirst(){
        return Main.getDistance(route[0],route[route.length-1]);
    }
    public void printToFileTour(String path,String filename,int length) throws FileNotFoundException {

        PrintWriter out=new PrintWriter(path);
        //out.write("SEED: "+Main.seed+"\n");
        out.write("NAME : "+filename+".opt.tour");
        out.write("\nCOMMENT : ");
        out.write("\nTYPE : TOUR");
        out.write("\nDIMENSION : "+length);
        out.write("\nTOUR SECTION\n");
        for (Point p : route) {
            out.println(p.getId());
        }
        out.println("-1");
        out.println("EOF");
        out.close();
    }


    public void setIterationNumber(int iterationNumber) {
        this.iterationNumber=iterationNumber;
    }
}
