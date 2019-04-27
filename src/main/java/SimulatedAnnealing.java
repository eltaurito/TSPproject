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

        double temp=Main.r.nextInt(100)+100, alpha=(Main.r.nextDouble()*0.07)+0.90;
        Route bestTour=new Route(tour.clone());
        //Point[] bestTour=tour.clone();
        int size=Main.bestResult;


        //long beginTime=System.currentTimeMillis();

        //Must implement a condition
        while (((System.currentTimeMillis()-Main.beginTime) < 15000) && (bestTour.getRealDistance() != size)) {
            // System.out.println(temp);
            for (int i=0; i < 100; i++) {

                Point[] tourTwoOpt=new twoOpt(new Route(swap(tour))).start().getRoute();

                int distNext=new Route(tourTwoOpt).getRealDistance();
                int distCurrent=new Route(tour).getRealDistance();


                if (distNext < distCurrent) {
                    tour=tourTwoOpt;
                    if (distNext < bestTour.getRealDistance()) {
                        //System.out.println(distNext);
                        bestTour=new Route(tourTwoOpt);

                    }
                } else if (randomVerify(distNext,distCurrent,temp)) {
                    tour=tourTwoOpt;
                }
            }
            temp*=alpha;
        }
        return bestTour;
    }

    private boolean randomVerify(int distNext,int distCurrent,double temp) {
        double difference=-((double) distNext-(double) distCurrent);
        double d=Math.pow(Math.E,(difference / temp));

        //d=difference/temp;
        return (Main.r.nextDouble() < d);
    }

    //MUST: remember that the start city is stored two times
    //at the begin and at the end
    private Point[] swap(Point[] currentTour) {
        Point[] cities=new Point[currentTour.length];
        cities[0]=tour[0];
        cities[cities.length-1]=cities[0];


        int[] position=new int[4];
        //System.out.println("Start: "+currentTour[0]);

        //a     b       c       d
        //0     1       2       3
        // ThreadLocalRandom.current().setSeed(Main.seed);
        //ThreadLocalRandom.current().ints(1,currentTour.length-1).distinct().limit(4).forEach(e->position.add(e));

        do {
            position[0]=Main.r.nextInt(currentTour.length-1);
            position[1]=Main.r.nextInt(currentTour.length-1);
            position[2]=Main.r.nextInt(currentTour.length-1);
            position[3]=Main.r.nextInt(currentTour.length-1);
        } while (position[1] < position[0] || position[2] < position[1] || position[3] < position[2]);
        int a=0, b=1, c=2, d=3;

        int turn=0;

        for (int i=0; i <= position[a]; i++) {
            cities[i]=currentTour[i];
            turn++;
        }
        for (int i=position[c]+1; i <= position[d]; i++) {
            cities[turn]=currentTour[i];
            turn++;
        }
        for (int i=position[b]+1; i <= position[c]; i++) {
            cities[turn]=currentTour[i];
            turn++;
        }
        for (int i=position[a]+1; i <= position[b]; i++) {
            cities[turn]=currentTour[i];
            turn++;
        }
        for (int i=position[d]+1; i < currentTour.length; i++) {
            cities[i]=currentTour[i];
            //turn++;
        }
        boolean verified=new Validator().Validate(cities);
        if (!verified) {
            int x=0;
        }
        return cities;


    }

    private Point[] swapAlt(Point[] currentTour) {
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
        cities[a]=currentTour[position.get(a)];
        turn++;
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
        for (int i=position.get(d)+1; i < currentTour.length-1; i++) {
            cities[turn]=currentTour[i];
            turn++;
        }
        for (int i=0; i < position.get(a); i++) {
            cities[turn]=currentTour[i];
            turn++;
        }

        cities[currentTour.length-1]=cities[0];

//        for (int i=0; i <= position.get(a); i++) {
//            cities[i]=currentTour[i];
//            turn++;
//        }


        boolean verified=new Validator().Validate(cities);
        if (!verified) {
            int x=0;
        }
        return cities;


    }


}

