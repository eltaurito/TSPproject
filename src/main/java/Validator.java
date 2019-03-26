import java.util.ArrayList;

import java.util.List;

public class Validator { List<Point> cities;

    public boolean check(Point[] tour){
        boolean[] ce=new boolean[tour.length];
        for(Point c:tour){
            ce[c.getId()-1]=true;
        }
        for(boolean b:ce)
            if(!b)
                return false;

        return  true;
    }
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
