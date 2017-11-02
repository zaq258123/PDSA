import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
/**
 * Created by ASUS on 2016/3/11.
 */
public class LabelCC {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] data = br.readLine().split(",");
            int num = Integer.parseInt(data[0]);

            int[][] id = new int[num][num];

            ArrayList<String> lines = new ArrayList<String>();
            ArrayList<String> idChange = new ArrayList<String>();

            while (br.ready())
                lines.add(br.readLine());
            br.close();

            for (String line : lines) {
                String[] coordinates = line.split(",");
                int row = Integer.parseInt(coordinates[0]) - 1;
                int col = Integer.parseInt(coordinates[1]) - 1 ;
                id[row][col] = -1;
            }

            int idLabel = 1;
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (i == 0) {
                        if (id[i][j] != -1) {
                            if (j == 0) {
                                id[i][j] = idLabel;
                                idLabel++;
                            } else {
                                id[i][j] = idLabel;
                                idLabel++;
                            }
                        }
                    } else {
                        if (id[i][j] != -1) {
                            if (j == 0) {
                                if (id[i - 1][j] != -1) {
                                    id[i][j] = id[i - 1][j];
                                } else {
                                    if (id[i][j + 1] == -1) {
                                        id[i][j] = idLabel;
                                        idLabel++;
                                    }
                                }
                            } else {
                                int upId = id[i - 1][j];
                                int leftId = id[i][j - 1];
                                Boolean isUpId = (upId == -1);
                                Boolean isLeftId = (leftId == -1);
                                if (isUpId && isLeftId) {
                                    id[i][j] = idLabel;
                                    idLabel++;
                                } else if (!isUpId && !isLeftId){
                                    if (upId > leftId) {
                                        id[i][j] = upId;
                                        String numberChange = upId + "," + leftId;
                                        idChange.add(numberChange);
                                    } else if (upId == leftId) {
                                        id[i][j] = leftId;
                                    } else {
                                        id[i][j] = leftId;
                                        String numberChange = leftId + "," + upId;
                                        idChange.add(numberChange);
                                    }
                                } else {
                                    if (isUpId)
                                        id[i][j] = leftId;
                                    if (isLeftId)
                                        id[i][j] = upId;
                                }
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    System.out.print(id[i][j] + " ");
                }
                System.out.println(" ");
            }

            for (int i = 0; i < idChange.size(); i++)
                System.out.println(idChange.get(i));

            int[] parents = new int[idLabel-1];
            for (int i = 0; i < idChange.size(); i++) {
                String[] parentId = idChange.get(i).split(",");
                parents[Integer.parseInt(parentId[0])-1] = Integer.parseInt(parentId[1]);
            }

//            for (int i = 0; i < parents.length; i++) {
//                    System.out.print(parents[i] + "");
//                }

            int targetRow = Integer.parseInt(data[1]);
            int targetCol = Integer.parseInt(data[2]);
            int targetId = id[targetRow-1][targetCol-1];
            if (targetId != -1) {
                if (targetId != parents[targetId-1]) {
                    System.out.println(parents[targetId - 1]);
                } else {
                    System.out.println(targetId);
                }
            } else {
                System.out.println(0);
            }

        }
    }
}