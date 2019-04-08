/*
Copyright: Nicol Allegra
Copied just for a school's project

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SimulatedAnnealing {
    Point[] tour;
    Validator val=new Validator();

    public SimulatedAnnealing(Point[] tour) {
        this.tour=tour.clone();
    }

    public Route start() {
        //Is common to start with a temp of 100
        //Alpha: Value of temp decreasing
        double temp=1000, alpha=0.97;
        Point[] bestTour=tour.clone();
        int size=Main.bestResult;

        long beginTime=System.currentTimeMillis();

        //Must implement a condition
        while (((System.currentTimeMillis()-beginTime) < 140000 )&& (new Route(bestTour).getRealDistance() != size)) {
            //System.out.println(temp);
            for (int i=0; i < 100; i++) {
                Point[] lista=swap(tour);
                //System.out.println(val.Validate(lista));
                Point[] tourTwoOpt=new twoOpt(new Route(lista)).start().getRoute();

                int distNext=new Route(tourTwoOpt).getRealDistance();
                int distCurrent=new Route(tour).getRealDistance();


                if (distNext < distCurrent) {
                    tour=tourTwoOpt;
                    if (distNext < new Route(bestTour).getRealDistance()) {
                        //System.out.println(distNext);
                        bestTour=tourTwoOpt;

                    }
                } else if (randomVerify(distNext,distCurrent,temp)) {
                    tour=tourTwoOpt;

                }

            }
            temp*=alpha;

        }
        return new Route(bestTour);
    }

    private boolean randomVerify(int distNext,int distCurrent,double temp) {
        double r=new Random().nextDouble();
        double difference=-((double) distNext-(double) distCurrent);
        double d=Math.pow(Math.E,(difference / temp));

        //d=difference/temp;
        return (r < d);
    }

    //MUST: remember that the start city is stored two times
    //at the begin and at the end
    private Point[] swap(Point[] currentTour) {
        Point[] cities=new Point[currentTour.length];
        cities[0]=tour[0];
        cities[cities.length-1]=cities[0];


        List<Integer> position=new ArrayList<>();
        //System.out.println("Start: "+currentTour[0]);

        //a     b       c       d
        //0     1       2       3
        ThreadLocalRandom.current().ints(1,currentTour.length-1).distinct().limit(4).forEach(e->position.add(e));
        Collections.sort(position);
        int a=0, b=1, c=2, d=3;

        int turn=0;

        for (int i=0; i <= position.get(a); i++) {
            cities[i]=currentTour[i];
            turn++;
        }
        for (int i=position.get(c)+1; i <= position.get(d); i++) {
            cities[turn]=currentTour[i];
            turn++;
        }
        for (int i=position.get(b)+1; i <= position.get(c); i++) {
            cities[turn]=currentTour[i];
            turn++;
        }
        for (int i=position.get(a)+1; i <= position.get(b); i++) {
            cities[turn]=currentTour[i];
            turn++;
        }
        for (int i=position.get(d)+1; i < currentTour.length; i++) {
            cities[i]=currentTour[i];
            //turn++;
        }
        boolean verified=new Validator().Validate(cities);
        if (!verified) {
            int x=0;
        }
        return cities;


    }

}

