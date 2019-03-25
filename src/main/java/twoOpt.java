import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static sun.nio.ch.IOStatus.check;


public class twoOpt {
    int[][] matrix;
    Point[] nodes;
    /*
    *************************THIS IS IMPORTANT************
    The size of the route is n° of cities + 1, because it contains
    the first city in the first and in the last position
     */

    Point[] route;

    public twoOpt(int[][] matrix,Point[] nodes,Route route) {
        this.matrix=matrix.clone();
        this.nodes=nodes.clone();
        this.route=route.getRoute().clone();
    }

    public void optswap(int from,int to) {
        for (int i=from+1, j=to; i < j; i++,j--) {
            Point tmp;
            tmp=route[i];
            route[i]=route[j];
            route[j]=tmp;
        }
    }


    public Route start() {
        int maxGain, gain, maxI = 0, maxJ = 0;
        int size = route.length;

        do{
            maxGain = 0;
            for (int i = 0; i < size - 2; i++) {
                for (int j = i + 1; j < size -1; j++) {
                    gain = calculateGain(route[i],route[i+1],route[j],route[j+1]);
                    if (gain < 0 && gain < maxGain) {
                        maxGain = gain;
                        maxI = i;
                        maxJ = j;
                    }
                }
            }
            optswap( maxI, maxJ);
        }while(maxGain != 0);
        return new Route(matrix,route);
    }


    //****CHECK THIS FUNCTION (how this gain works?) ******
    //a:i b:i+1 c:j d:j+1
    private int calculateGain(Point a,Point b,Point c,Point d) {
        int ab=matrix[a.getId()-1][b.getId()-1];
        int cd=matrix[c.getId()-1][d.getId()-1];
        int ac=matrix[a.getId()-1][c.getId()-1];
        int bd=matrix[b.getId()-1][d.getId()-1];
        return  (ac + bd) - (ab + cd);

    }

}
