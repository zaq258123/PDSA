
import java.util.Arrays;

/**
 * Created by ASUS on 2016/4/5.
 */
public class Main {
    public static void main(String[] args) {
        // StdRandom Test
        double rand1,rand2;
        int yMin = 0;
        Point2D[] test = new Point2D[10];
        Point2D[] test2 = new Point2D[10];

        // plot setting
        StdDraw.setCanvasSize(500,500);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.setPenRadius(0.03);

        for (int i = 0; i < 10; i++) {
            rand1 = StdRandom.uniform();
            rand2 = StdRandom.uniform();
//            System.out.println((int)(rand1*1000)/1000.0 + " / " + (int)(rand2*1000)/1000.0);
            test[i] = new Point2D(rand1,rand2);
//            test2[i] = new Point2D(rand1,rand2);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.text(rand1, rand2 - 0.03, String.format("%.4f",rand1) + " / " + (int)(rand2*1000)/1000.0 + " ( " + i + " ) ");
//            StdDraw.setPenColor(StdDraw.BLACK);
//            test[i].draw();

            if (i > 0) {
                int yOrder = Point2D.Y_ORDER.compare(test[yMin],test[i]);
                if (yOrder == 1) yMin = i;
            }
        }

        for (int i = 0; i < 10; i++)
            test2[i] = test[i];


        // Arrays.sort
        Point2D p = new Point2D(test[yMin].x(), test[yMin].y());
        Arrays.sort(test2,p.ATAN2_ORDER);

        int[] sortIndex = new int[10];
        int[] isConvex = new int[11];
        for (int i = 0; i < 11; i++)
                isConvex[i] = i;

        for (int i = 0; i < 10; i++) {
            boolean e = false;
            int j = 0;
            while (!e) {
                e = test2[i].equals(test[j]);
                if (e)
                    sortIndex[i] = j;
                else
                    j++;
            }
        }

        for (int i = 0; i < 10; i++) {
            StdDraw.setPenColor(StdDraw.BLACK);
            test2[i].draw();
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(test2[i].x(), test2[i].y() + 0.03, i + "(new) / " + sortIndex[i] + "(old)");
        }

        // plot yMin
        StdDraw.setPenColor(StdDraw.BLUE);
        test[yMin].draw();

        int index = 0;
        int[] ccwArray = new int[3];
        int ansCount = 10;

        while (index < isConvex.length) {
            if (ccwArray[2] == 10)
                break;
            int ccwCount = 0;
            int j = index;
            while (ccwCount < 3) {
                if (isConvex[j] != -1) {
                    ccwArray[ccwCount] = isConvex[j];
                    ccwCount++;
                }
                j++;
//                System.out.println(j+" /j");
//                System.out.println(index+" /index");
            }

            System.out.println(ccwArray[0]+"/"+ccwArray[1]+"/"+ccwArray[2]);
            if (ccwArray[2] == 10)
                break;

            int temp = Point2D.ccw(test2[ccwArray[0]],test2[ccwArray[1]],test2[ccwArray[2]]);
            if (temp == 1) {
                index++;
//                System.out.println("ha");
            } else {
                isConvex[ccwArray[1]] = -1;
                ansCount--;
                if (index != 0){
                    index--;
//                    System.out.println("hi");
                }

            }
        }

        int[] newisConvex = new int[9];
        for (int i = 0; i < isConvex.length-2; i++) {
            newisConvex[i] = isConvex[i+1];
        }
        int[] ans = new int[ansCount];
        int[] ansNew = new int[ansCount];
        ansNew[0] = sortIndex[0];
        ans[0] = sortIndex[0];

        StdDraw.setPenColor(StdDraw.GREEN);
        for (int i = 0; i < 9; i++){
            if (newisConvex[i] != -1) {
                ans[ansCount-1] = sortIndex[newisConvex[i]];
                test2[newisConvex[i]].draw();
                ansCount--;
            }
        }

        for (int i = 0; i < ans.length; i++) {
            int tempMin = 10;
            if (i > 0) {
                for (int j = 0; j < ans.length; j++) {
                    if ((ans[j] < tempMin) && (ans[j] > ansNew[i - 1])) {
                        tempMin = ans[j];
                    }
                }
            } else {
                for (int j = 0; j < ans.length; j++) {
                    if (ans[j] < tempMin) {
                        tempMin = ans[j];
                    }
                }
            }
            ansNew[i] = tempMin;
            System.out.println(ans[i] + "/ "+ ansNew[i]);
        }
    }
}
