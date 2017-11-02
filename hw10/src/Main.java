import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2016/5/24.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            List<Point2D> pointList = new ArrayList<>();

            while (br.ready()) {
                String[] xy = br.readLine().split(" ");
                pointList.add(new Point2D(Double.parseDouble(xy[0]),Double.parseDouble(xy[1])));
            }
            Point2D[] point = pointList.toArray(new Point2D[pointList.size()]);
            FindNeighbors f = new FindNeighbors();
            f.init(point);
            Point2D p = new Point2D(0.3833339428,0.1459115606);
            Point2D[] a = f.query(p,20);

            for (int i = 0; i < a.length; i++)
                System.out.println(a[i].x() + " / " + a[i].y() + " / " + p.distanceTo(a[i]));
        }
    }
}
