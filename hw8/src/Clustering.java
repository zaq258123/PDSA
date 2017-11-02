import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Clustering implements Comparable<Clustering>{
    private double x = 0.0,y = 0.0;
    private ArrayList<ArrayList<Double>> list = new ArrayList<>();

    public void addPoint(ArrayList<Double> point) {
        this.list.add(point);
        this.x = point.get(0);
        this.y = point.get(1);
    }

    public void cluster(Clustering that) {
        for (int i = 0; i < that.getSize(); i++)
            this.list.add(that.list.get(i));

        double xSum = 0.0, ySum = 0.0;
        for (int i = 0; i < this.list.size(); i++) {
            xSum += list.get(i).get(0);
            ySum += list.get(i).get(1);
        }

        this.x = xSum/this.list.size();
        this.y = ySum/this.list.size();
    }

    public double getX() { return this.x; }

    public double getY() { return this.y; }

    public int getSize() { return this.list.size(); }

    public double distance(Clustering that) {
        return Math.sqrt(Math.pow(that.getX()-this.x,2) + Math.pow(that.getY()-this.y,2));
    }

    public double minDistance(Clustering that) {
        int sizeC1 = this.getSize(), sizeC2 = that.getSize();
        double minDist = this.distance(that);
        for (int i = 0; i < sizeC1; i++) {
            double x1 = this.list.get(i).get(0), y1 = this.list.get(i).get(1);

            for (int j = 0; j < sizeC2; j++) {
                double x2 = that.list.get(j).get(0), y2 = that.list.get(j).get(1);
                double dist = Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
                if (dist < minDist) minDist = dist;
            }
        }
        return minDist;
    }

    public int compareTo (Clustering that) {
        if (this.getSize() < that.getSize()) return 1;
        else if ((this.getSize() > that.getSize())) return -1;
        else return 0;
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int n = Integer.parseInt(br.readLine());
            if (n > 3) {
                ArrayList<Clustering> pointAll = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    Clustering point = new Clustering();
                    ArrayList<Double> xyAxis = new ArrayList<>();
                    String[] s = br.readLine().split(" ");
                    xyAxis.add(Double.parseDouble(s[0]));
                    xyAxis.add(Double.parseDouble(s[1]));
                    point.addPoint(xyAxis);
                    pointAll.add(point);
                }

                int clusterCount = n;

                while (clusterCount > 3) {
//                    System.out.println(clusterCount);
                    // find min distance
                    int firstIndex = 0, secondIndex = 1;
                    double minDistance = pointAll.get(0).distance(pointAll.get(1));
//                    System.out.println("M0 = " + minDistance);

                    for (int i = 0; i < clusterCount - 1; i++) {
                        for (int j = i + 1; j < clusterCount; j++) {
//                            System.out.println("D = " + pointAll.get(i).distance(pointAll.get(j)));
                            if (pointAll.get(i).distance(pointAll.get(j)) < minDistance) {
                                minDistance = pointAll.get(i).distance(pointAll.get(j));
                                firstIndex = i;
                                secondIndex = j;
                            }
//                            System.out.println("first = " + firstIndex + " / " + "second = " + secondIndex);
//                            System.out.println("M = " + minDistance);
                        }
                    }


                    if (pointAll.get(firstIndex).getSize() >= pointAll.get(secondIndex).getSize()) {
                        pointAll.get(firstIndex).cluster(pointAll.get(secondIndex));
                        pointAll.remove(secondIndex);
                    } else {
                        pointAll.get(secondIndex).cluster(pointAll.get(firstIndex));
                        pointAll.remove(firstIndex);
                    }
                    clusterCount--;
                }

                Clustering[] cluster = new Clustering[3];
                for (int i = 0; i < 3; i++)
                    cluster[i] = pointAll.get(i);

                double minDist = cluster[0].minDistance(cluster[1]);

                for (int i = 0; i < 2; i++)
                    for (int j = i+1; j < 3; j++)
                        if (cluster[i].minDistance(cluster[j]) < minDist) minDist = cluster[i].minDistance(cluster[j]);

                Arrays.sort(cluster);
                for (int i = 0; i < 3; i++) {
                    System.out.println(cluster[i].getSize() + " " + String.format("%.2f", cluster[i].getX()) + " " + String.format("%.2f", cluster[i].getY()));
                }
                System.out.println(String.format("%.2f",minDist));

//                System.out.println(pointAll[11].getX() + " / " + pointAll[11].getY());
//                System.out.println(pointAll[0].getX() + " / " + pointAll[0].getY());
//                System.out.println(pointAll[0].distance(pointAll[11]));
            } else {

                double[] pointX = new double[n];
                double[] pointY = new double[n];
                for (int i = 0; i < n; i++) {
                    String[] s = br.readLine().split(" ");
                    double x = Double.parseDouble(s[0]), y = Double.parseDouble(s[1]);
                    System.out.println("1 " + String.format("%.2f",x) + " " + String.format("%.2f",y));
                    pointX[i] = x;
                    pointY[i] = y;
                }
                if (n != 1) {
                    double minDist = Math.sqrt(Math.pow(pointX[0] - pointX[1], 2) + Math.pow(pointY[0] - pointY[1], 2));
                    for (int i = 0; i < n - 1; i++) {
                        for (int j = i + 1; j < n; j++) {
                            double dist = Math.sqrt(Math.pow(pointX[i] - pointX[j], 2) + Math.pow(pointY[i] - pointY[j], 2));
                            if (dist < minDist) minDist = dist;
                        }
                    }
                    System.out.println(String.format("%.2f", minDist));
                } else System.out.println(0.0);
            }
        }
    }
}
