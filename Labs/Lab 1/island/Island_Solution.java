// Solution
/*
 Name:
 Matric No.:
 */

import java.util.*;

public class Island {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int row;
        int col;

        row = sc.nextInt();
        col = sc.nextInt();

        int[][] arr = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        checkMap(arr, row, col);


        //count island dimension
        // turn to 0, add to 1 count
    }

    public static void checkMap(int[][] arr, int row, int col) {
        int startRow, startCol, endRow, endCol;
        int islandCount = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (arr[i][j] == 1) {
                    startRow = i;
                    startCol = j;
                    endRow = i;
                    endCol = j;

                    while ((endCol != col) && ((arr[endRow][endCol] == 1))) {
                        endCol++;
                    }
                    endCol -= 1;

                    while ((endRow != row) && ((arr[endRow][endCol] == 1))) {
                        endRow++;
                    }
                    endRow -= 1;

                    for (int k = startRow; k < (endRow + 1); k++) {
                        for (int l = startCol; l < (endCol + 1); l++) {
                            if (arr[k][l] == 1) {
                                arr[k][l] = 0;
                            }
                        }
                    }
                    islandCount++;
                }
            }
        }
        System.out.println(islandCount);
    }
}

