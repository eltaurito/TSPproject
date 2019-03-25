import java.util.ArrayList;
import java.util.List;

public class Validator {
    public boolean  Validate(Point[] route){
        List<Point> visited=new ArrayList<>();
        for (int i=0; i < route.length-2 ; i++) {
            if(visited.contains(route[i]))
                return false;
            visited.add(route[i]);
        }
        Point a=route[route.length-1];
        Point b=route[0];
        if((a.getX()==b.getX())&&(a.getY()==b.getY()))
            return true;

        return false;
    }
}
