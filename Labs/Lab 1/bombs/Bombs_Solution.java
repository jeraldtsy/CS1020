// Solution
/*
 * Name		:
 * Matric No.	:
 */

import java.util.*;

public class Bombs {
    final int maxCol = 50;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row;
        int col;
        int numQueries;
        int bombSize;

        row = sc.nextInt();
        col = sc.nextInt();

        int[][] arr = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        numQueries = sc.nextInt();
        for (int i = 0; i < numQueries; i++) {
            bombSize = sc.nextInt();
            deployBomb(arr, row, col, bombSize);
        }
        countPoints(arr, row, col);
    }

    public static void deployBomb(int[][] arr, int row, int col, int bombSize) {
        int bestRow = 0;
        int bestCol = 0;
        int maxTargets = 0;
        int currTargets;
        int bombStartRow, bombStartCol, bombEndRow, bombEndCol;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                bombStartRow = i - ((bombSize - 1) / 2);
                bombStartCol = j - ((bombSize - 1) / 2);
                bombEndRow = i + ((bombSize - 1) / 2);
                bombEndCol = j + ((bombSize - 1) / 2);
                currTargets = 0;

                for (int k = bombStartRow; k <= bombEndRow; k++) {
                    for (int l = bombStartCol; l <= bombEndCol; l++) {
                        //negative index
                        if ((k < 0) || (l < 0)) {
                            continue;
                        }
                        //positive: greater than row and col
                        else if ((k >= row) || (l >= col)) {
                            continue;
                        }
                        //count bombs
                        else {
                            if (arr[k][l] == 1) {
                                currTargets++;
                            }
                        }
                    }
                }

                if (currTargets > maxTargets) {
                    maxTargets = currTargets;
                    bestRow = i;
                    bestCol = j;
                }
            }
        }
        System.out.println(bestRow + " " + bestCol);
    }

    public static void countPoints(int[][] arr, int row, int col) {
        int bestRow = 0;
        int bestCol = 0;
        int maxPoints = 0;
        int bestBomb = 1;
        int currPoints;
        int bombStartRow, bombStartCol, bombEndRow, bombEndCol;

        for (int bombSize = 1; bombSize <= 49; bombSize += 2) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    bombStartRow = i - ((bombSize - 1) / 2);
                    bombStartCol = j - ((bombSize - 1) / 2);
                    bombEndRow = i + ((bombSize - 1) / 2);
                    bombEndCol = j + ((bombSize - 1) / 2);
                    currPoints = 0;

                    for (int k = bombStartRow; k <= bombEndRow; k++) {
                        for (int l = bombStartCol; l <= bombEndCol; l++) {
                            //negative index
                            if ((k < 0) || (l < 0)) {
                                continue;
                            }
                            //positive: greater than row and col
                            else if ((k >= row) || (l >= col)) {
                                continue;
                            }
                            //count bombs
                            else {
                                if (arr[k][l] == 1) {
                                    currPoints += 3;
                                } else {
                                    currPoints -= 1;
                                }
                            }
                        }
                    }
                    
                    if (currPoints > maxPoints) {
                        maxPoints = currPoints;
                        bestRow = i;
                        bestCol = j;
                        bestBomb = bombSize;
                    }
                }
            }
        }
        System.out.println(bestRow + " " + bestCol + " " + bestBomb);
    }
}