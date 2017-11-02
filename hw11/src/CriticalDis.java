import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
/**
 * Created by ASUS on 2016/6/8.
 */
public class CriticalDis {
    public static class Point {
        private double x;
        private double y;
        Point(double x,double y) {
            this.x = x;
            this.y = y;
        }

        public double distanceTo(Point that) {
            return Math.sqrt(Math.pow(this.x-that.x,2)+Math.pow(this.y-that.y,2));
        }

        public boolean compareXY(Point that) {
            return this.x + this.y < that.x + that.y;
        }

        public boolean isEdge(Point that) {
            return this.x < that.x && this.y < that.y;
        }

    }

    public static class Edge {
        private int indexFrom;
        private int indexTo;
        private double distance;
        Edge(int indexFrom, int indexTo, double distance) {
            this.indexFrom = indexFrom;
            this.indexTo = indexTo;
            this.distance = distance;
        }
    }

    public static class edgeComparator implements Comparator<Edge> {
        public int compare(Edge d1,Edge d2) {
            if (d1.distance < d2.distance) return -1;
            else if (d1.distance > d2.distance) return 1;
            else return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int size = Integer.parseInt(br.readLine());
            Point[] pArray = new Point[size];
            int sourceIndex = 0, targetIndex = 0;
            for (int i = 0; i < size; i++) {
                String[] xy = br.readLine().split(" ");
                Point p = new Point(Double.parseDouble(xy[0]), Double.parseDouble(xy[1]));
                pArray[i] = p;
                if (pArray[i].compareXY(pArray[sourceIndex])) sourceIndex = i;
                if (!pArray[i].compareXY(pArray[targetIndex])) targetIndex = i;
            }

            ArrayList<Edge> disList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (pArray[i].isEdge(pArray[j]))
                        disList.add(new Edge(i, j, pArray[i].distanceTo(pArray[j])));
                }
            }
            Edge[] disArray = disList.toArray(new Edge[disList.size()]);
            Arrays.sort(disArray,new CriticalDis.edgeComparator());
            MinPQ<Edge> pq = new MinPQ<>(new CriticalDis.edgeComparator());
            for (int i = 0; i < disArray.length; i++)
                pq.insert(disArray[i]);

            boolean isPath = false;
            double ans = 0;
            Digraph digraph = new Digraph(size);
            while (!isPath) {
                Edge e = pq.delMin();
                digraph.addEdge(e.indexFrom,e.indexTo);
                ans = e.distance;
                DirectedDFS d = new DirectedDFS(digraph,sourceIndex);
                isPath = d.marked(targetIndex);
            }
            System.out.printf("%1.3f\n", ans);
        }
    }
}
