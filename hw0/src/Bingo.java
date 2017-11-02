import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

            // read file from args[0] in Java 7 style
            try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

                // read a line and split by ','
                String[] data = br.readLine().split(",");

                // store the first integer in variable stringCount (number of announced strings)
                int stringCount = Integer.parseInt(data[0]);

                // store the second integer in variable num (dimension of matrix: num * num)
                int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];

            // printf in Java (you should comment out or delete this in your final submission)
            // System.out.printf("number of announced strings: %d\ndimension of matrix: %d x %d\n", stringCount, num, num);

            /*  now you can write your own solution to hw0
             *  you can follow the instruction described below:
             * 
             *  1. read the rest content of the file
             *  2. store the announce strings (2nd line of the file) in variable announce
             *  3. store the matrix (from the 3rd line to the end of the file) in variable matrix
             *  4. compare the matrix and announce strings (this is the tricky part)
             *  5. output how many 'straight line' are there in the matrix
             * 
             *  [note]
             *  you can use every data structure in standard Java packages (Java 8 supported)
             *  the packages in stdlib.jar and algs4.jar are also available for you to use
             *
             *  [hint]
             *  1. you should check whether Java pass the variable by references or by values.
             *  2. some data structure such as HashSet, HashMap, Arrays, ArrayList, Vector are very
             *     useful for solving problems. 
             */
            String readLine;
            int announceLine = 1, matrixLineStart = 2 , matrixCount = 0;
            while ((readLine = br.readLine()) != null)
            {
                if (announceLine++ < matrixLineStart) {
                    for (int i = 0; i < stringCount; i++) {
                        String[] bingoTarget = readLine.split(",");
                        announce[i] = bingoTarget[i];
                    }
                }
                else if (announceLine++ >= matrixLineStart) {
                    for (int j  = 0; j < num; j ++) {
                        String[] matrixTarget = readLine.split(",");
                        matrix[matrixCount][j] = matrixTarget[j];
                    }
                    matrixCount++;
                }
            }

            int[][] bingoMatrix = new int[num][num];
            for (int k = 0; k < stringCount; k++){
                for (int i = 0; i < num; i++) {
                    for (int j = 0; j < num; j++) {
                        if (matrix[i][j].equals(announce[k])) {
                            bingoMatrix[i][j] = 1;
                        }
                    }
                }
            }

            int straightLines = 0,  rightUp= 0, leftDown = 0;
            for (int i = 0; i < num; i++) {
                int rowCount = 0, colCount = 0;
                rightUp = rightUp + bingoMatrix[i][i];
                leftDown = leftDown + bingoMatrix[i][num-i-1];
                for (int j = 0; j < num; j++){
                    rowCount = rowCount + bingoMatrix[i][j];
                    colCount = colCount + bingoMatrix[j][i];
                }
                if (rowCount == num)
                    straightLines++;
                if (colCount == num)
                    straightLines++;
            }

            if (rightUp == num)
                straightLines++;
            if (leftDown == num)
                straightLines++;

            br.close();
            System.out.println(straightLines);
        }
    }
}
