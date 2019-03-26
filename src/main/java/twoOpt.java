import static sun.nio.ch.IOStatus.check;


public class twoOpt {
    /*
    *************************THIS IS IMPORTANT************
    The size of the tour is n° of cities + 1, because it contains
    the first city in the first and in the last position
     */

    Point[] tour;

    public twoOpt(Route tour) {
        this.tour=tour.getRoute().clone();
    }

    public void optswap(int from,int to) {
        for (int i=from+1, j=to; i < j; i++,j--) {
            Point tmp;
            tmp=tour[i];
            tour[i]=tour[j];
            tour[j]=tmp;
        }
    }


    public Route start() {
        int maxGain, gain, maxI=0, maxJ=0;
        int size=tour.length;

        do {
            maxGain=0;
            for (int i=0; i < size-2; i++) {
                for (int j=i+1; j < size-1; j++) {
                    gain=calculateGain(tour[i],tour[i+1],tour[j],tour[j+1]);
                    if (gain < 0 && gain < maxGain) {
                        maxGain=gain;
                        maxI=i;
                        maxJ=j;
                    }
                }
            }
            optswap(maxI,maxJ);
        } while (maxGain != 0);
        return new Route(tour);
    }


    //****CHECK THIS FUNCTION (how this gain works?) ******
    //a:i b:i+1 c:j d:j+1
    private int calculateGain(Point a,Point b,Point c,Point d) {
        int ab=Main.matrix[a.getId()-1][b.getId()-1];
        int cd=Main.matrix[c.getId()-1][d.getId()-1];
        int ac=Main.matrix[a.getId()-1][c.getId()-1];
        int bd=Main.matrix[b.getId()-1][d.getId()-1];
        return (ac+bd)-(ab+cd);

    }

}
