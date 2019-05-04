public class Point {
    private int id;
    private double x;
    private double y;

    public int getId(){
        return id;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point(int id,double x,double y) {
        this.id=id;
        this.x=x;
        this.y=y;
    }

    public static int euclideanDistance(Point a,Point b){
        double x=b.getX()-a.getX();
        double y=b.getY()-a.getY();
        double res= Math.pow(x,2)+Math.pow(y,2);
        res=Math.sqrt(res);
        return (int)(res+0.5);
    }

    @Override
    public String toString() {
        return id+" "+x+" "+y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return  new Point(this.id,
                this.x,
                this.y);
    }


}
