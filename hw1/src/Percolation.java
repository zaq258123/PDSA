import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Percolation {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int num = Integer.parseInt(br.readLine());

            ArrayList<String> lines = new ArrayList<String>();

            while (br.ready())
                lines.add(br.readLine());
            br.close();

            int[] row = new int[num*num];
            int[] col = new int[num*num];
            int[][] id = new int[num][num];

            for (int i = 0; i < lines.size(); i++) {
                String[] coordinates = lines.get(i).split(",");
                row[i] = Integer.parseInt(coordinates[0]);
                col[i] = Integer.parseInt(coordinates[1]);
            }

            if (num < 3) {
                if (num == 1) {
                    if (lines.size() != 0)
                        System.out.println(1 + "," + 1);
                    else
                        System.out.println(-1);
                } else {
                    int colCount1 = 0;
                    int colCount2 = 0;
                    for (int j = 0; j < lines.size(); j++) {
                        if (col[j] == 1)
                            colCount1++;
                        else
                            colCount2++;

                        if (colCount1 == 2 || colCount2 == 2) {
                            System.out.println(row[j] + "," + col[j]);
                            j = lines.size();
                        }
                        if (j == lines.size()-1 && !(colCount1 == 2 || colCount2 == 2))
                            System.out.println(-1);
                    }
                }
            } else {
                int i = 0;
                boolean isPercolation = false;
                while (!isPercolation) {
                    id[row[i] - 1][col[i] - 1] = row[i];
                    for (int j = i; j >= 0; j--) {
                        if (row[j] != 1 && row[j] != num) {
                            // up
                            int upBlock = id[row[j] - 2][col[j] - 1];
                            // down
                            int downBlock = id[row[j]][col[j] - 1];
                            // left
                            int leftBlock;
                            if (col[j] == 1)
                                leftBlock = 0;
                            else
                                leftBlock = id[row[j] - 1][col[j] - 2];

                            // right
                            int rightBlock;
                            if (col[j] == num)
                                rightBlock = 0;
                            else
                                rightBlock = id[row[j] - 1][col[j]];

                            boolean firstRow = (upBlock == 1 || downBlock == 1 || leftBlock == 1 || rightBlock == 1);
                            boolean lastRow = (upBlock == num || downBlock == num || leftBlock == num || rightBlock == num);

                            if (firstRow) {
                                id[row[j] - 1][col[j] - 1] = 1;
                            } else if (lastRow) {
                                id[row[j] - 1][col[j] - 1] = num;
                            }

                            if (firstRow && lastRow) {
                                j = 0;
                                isPercolation = true;
                                System.out.println(row[i] + "," + col[i]);
                            }
                        }
                    }
                    if (i < lines.size()) {
                        i++;
                    }
                    if (i > lines.size() - 1 && !isPercolation) {
                        isPercolation = true;
                        System.out.println(-1);
                    }
                }
            }
        }
    }
}

