/*
Copyright: Nicol Allegra
Copied just for a school's project

 */

import java.util.Arrays;
import java.util.Random;

public class SimulatedAnnealing {
    Point[] tour;

    public SimulatedAnnealing(Point[] tour) {
        this.tour=tour.clone();
    }

    public Route start() {
        double temp=100, alpha=0.97;

        Point[] currentTour=tour.clone();
        Point[] bestTour=currentTour.clone();
        int size=538;

        long beginTime=System.currentTimeMillis();

        //Must implement a condition
        while ((System.currentTimeMillis()-beginTime) < 140000 || (new Route(bestTour).getRealDistance() == size)) {
            for (int i=0; i < 100; i++) {
                Point[] lista=swap(currentTour);
                Point[] tourTwoOpt=new twoOpt(new Route(lista)).start().getRoute();

                int distNext=new Route(tourTwoOpt).getRealDistance();
                int distCurrent=new Route(currentTour).getRealDistance();


                if (distNext < distCurrent) {
                    currentTour=new; if (distNext < new Route(bestTour).getRealDistance()) {
                        bestTour=tourTwoOpt;
                    }
                } else if (randomVerify(distNext,distCurrent,temp)) {
                    currentTour=tourTwoOpt;
                }
            }
            temp*=alpha;
        }
        return new Route(bestTour);
    }

    private boolean randomVerify(int distNext,int distCurrent,double temp) {
        double r= new Random().nextDouble();
        double difference=-((double) distNext - (double) distCurrent);
        double d = Math.pow(Math.E, (difference / temp));
        return (r<d);
    }

    //MUST: remember that the start city is stored two times
    //at the begin and at the end
    private Point[] swap(Point[] currentTour) {
        Point[] cities=new Point[currentTour.length];
        int a=0,b=1,c=2,d=3;
        int position[]=new int[4];
        for (int i=0; i <position.length ; i++) {
            //Must check if the last city is included
            position[i]=new Random().nextInt(currentTour.length-1)

        } Arrays.sort(position);
        int cont=0;
        cities[cont]=cities[position[a]];
        for (int i=position[c]; i <=position[d] ; i++) {
            cities[cont]=currentTour[i];
            cont++;
        }
        for (int i=position[a]+1; i <=position[b] ; i++) {
            cities[cont]=currentTour[i];
            cont++;
        }
        for (int i=position[d]+1; i <currentTour.length -1; i++) {
            cities[cont]=currentTour[i];
            cont++;
        }
        for (int i=0; i <position[a] ; i++) {
            cities[cont]=currentTour[i];
            cont++;
        }
        cities[cont++]=currentTour[position[]]



    }

}

