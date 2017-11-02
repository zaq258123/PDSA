import java.io.BufferedReader;
import java.io.FileReader;
/**
 * Created by Cavitation on 2016/6/24.
 */
public class FindHub {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int size = Integer.parseInt(br.readLine());
            Point2D[] pointArray = new Point2D[size];
            for (int i = 0; i < size; i++) {
                String[] xy = br.readLine().split(" ");
                Point2D p = new Point2D(Double.parseDouble(xy[0]),Double.parseDouble(xy[1]));
                pointArray[i] = p;
            }

            EdgeWeightedGraph edgeWGraph = new EdgeWeightedGraph(size);
            for (int i = 0; i < size-1; i++) {
                for (int j = i + 1; j < size; j++) {
                    edgeWGraph.addEdge(new Edge(i, j, pointArray[i].distanceTo(pointArray[j])));
                }
            }
            KruskalMST kMST = new KruskalMST(edgeWGraph);

            EdgeWeightedDigraph edgeWDigraph = new EdgeWeightedDigraph(size);
            for (Edge e : kMST.edges()) {
                int v = e.either();
                int w = e.other(v);
                double weighted = e.weight();
                DirectedEdge go = new DirectedEdge(v,w,weighted);
                DirectedEdge back = new DirectedEdge(w,v,weighted);
                edgeWDigraph.addEdge(go);
                edgeWDigraph.addEdge(back);
            }

            MinPQ<Double> pq = new MinPQ<>();
            for (int i = 0; i < size; i++) {
                double sumVertex = 0.0;
                DijkstraSP sp = new DijkstraSP(edgeWDigraph,i);
                for (int j = 0; j < size; j++) {
                    sumVertex += sp.distTo(j);
                }
                pq.insert(sumVertex);
            }
            System.out.printf("%5.5f\n", pq.delMin());
        }
    }
}
