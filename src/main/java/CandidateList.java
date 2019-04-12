public class CandidateList {
    static Point[][] candidateList;
    static Point[] cities;
    static int nNeighbours;


    public static Point[][] findCandidates(Point[] cities,int neighbours){
        candidateList=new Point[cities.length][neighbours];
        cities=cities.clone();
        nNeighbours=neighbours;
        for (int i=0; i <candidateList.length ; i++) {
            Point[] nearCity=new Point[nNeighbours];
            Point[] citiesLocal=cities.clone();
            citiesLocal[i]=null;
            for (int j=0; j <nNeighbours ; j++) {
                //When i find a city near to another one
                //Remove it from the list
                nearCity[j]=nearestCity(cities[i],citiesLocal);
                citiesLocal[nearCity[j].getId()-1]=null;
            }
            candidateList[i]=nearCity;
        }

        return candidateList;
    }

    private static Point nearestCity(Point city,Point[] citiesLocal) {
        int distmin=Integer.MAX_VALUE;
        Point citymin=null;
        for (Point c:
             citiesLocal) {
            if(c==null)
                continue;
            int dist=Main.getDistance(city,c);
            if(dist<distmin){
                if(c.getId()==city.getId()) System.out.println("Equallllllllls");
                distmin=dist;
                citymin=c;
            }
        }
        return citymin;
    }


}
