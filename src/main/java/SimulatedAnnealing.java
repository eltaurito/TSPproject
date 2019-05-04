public class SimulatedAnnealing {
    Point[] tour;
    Validator val=new Validator();

    public SimulatedAnnealing(Point[] tour) {
        this.tour=tour.clone();
    }

    public Route start() {
        double temp=Main.r.nextInt(100)+100, alpha=(Main.r.nextDouble() * 0.07)+0.90;
        Route bestTour=new Route(tour.clone());
        int size=Main.bestResult;

        int cnt=1;

        while (stopCondition(bestTour,size,cnt)) {

            for (int i=0; i < 100; i++) {
                Point[] tourTwoOpt=new TwoOpt(new Route(swap(tour))).start().getRoute(); //Apply 2OPT to the current tour
                int distNext=new Route(tourTwoOpt).getRealDistance();
                int distCurrent=new Route(tour).getRealDistance();

                if (distNext < distCurrent) {
                    tour=tourTwoOpt;
                    if (distNext < bestTour.getRealDistance()) {
                        bestTour=new Route(tourTwoOpt);
                    }
                } else if (randomVerify(distNext,distCurrent,temp)) {
                    tour=tourTwoOpt;
                }
            }
            cnt++;
            temp*=alpha;
        }

        bestTour.setIterationNumber(cnt);
        Main.iterationsNumber=cnt;

        return bestTour;
    }

    private boolean stopCondition(Route bestTour,int size,int cnt) {
        if(Main.iterationsNumberArg>0){
            if(cnt<Main.iterationsNumberArg)
                return true;
            else{
                System.out.println("cnt: "+cnt+" it: "+Main.iterationsNumberArg);
                return false;}
        }else{
            return ((System.currentTimeMillis()-Main.beginTime) < 150000) && (bestTour.getRealDistance() != size);
        }

    }

    private boolean randomVerify(int distNext,int distCurrent,double temp) {
        double difference=-((double) distNext-(double) distCurrent);
        double d=Math.pow(Math.E,(difference / temp));
        return (Main.r.nextDouble() < d);
    }
    private Point[] swap(Point[] currentTour) {
        Point[] cities=new Point[currentTour.length];
        cities[0]=tour[0];
        cities[cities.length-1]=cities[0];


        int[] position=new int[4];
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

        return cities;


    }

}

