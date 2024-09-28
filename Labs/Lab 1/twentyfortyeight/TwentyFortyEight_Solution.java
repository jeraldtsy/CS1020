// Solution
/*
 * * Name		:
 * Matric No.		:
 */

import java.util.*;

public class TwentyFortyEight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] arr = new int[4][4];
        int direction;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        direction = sc.nextInt();

        if (direction == 0) {
            goLeft(arr);
        } else if (direction == 1) {
            goUp(arr);
        } else if (direction == 2) {
            goRight(arr);
        } else if (direction == 3) {
            goDown(arr);
        }

    }

    public static void goLeft(int[][] arr) {
        moveLeft(arr);
        printBoard(arr);
    }

    public static void goUp(int[][] arr) {
        rotateRight(arr);
        rotateRight(arr);
        rotateRight(arr);
        moveLeft(arr);
        rotateRight(arr);
        printBoard(arr);
    }

    public static void goRight(int[][] arr) {
        rotateRight(arr);
        rotateRight(arr);
        moveLeft(arr);
        rotateRight(arr);
        rotateRight(arr);
        printBoard(arr);
    }

    public static void goDown(int[][] arr) {
        rotateRight(arr);
        moveLeft(arr);
        rotateRight(arr);
        rotateRight(arr);
        rotateRight(arr);
        printBoard(arr);
    }

    public static void rotateRight(int[][] arr) {
        int temp;

        for (int i = 0; i < 4; i++) {
            for (int j = i; j < 4; j++) {
                temp = arr[j][i];
                arr[j][i] = arr[i][j];
                arr[i][j] = temp;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                temp = arr[i][j];
                arr[i][j] = arr[i][4 - j - 1];
                arr[i][4 - j - 1] = temp;
            }
        }
    }

    public static void moveLeft(int[][] arr) {
        int index;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                index = j;
                while (arr[i][j] == 0) {
                    index++;
                    if (index == 4) {
                        break;
                    }
                    if (arr[i][index] != 0) {
                        arr[i][j] = arr[i][index];
                        arr[i][index] = 0;
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == arr[i][j + 1]) {
                    arr[i][j] *= 2;
                    arr[i][j + 1] = 0;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                index = j;
                while (arr[i][j] == 0) {
                    index++;
                    if (index == 4) {
                        break;
                    }
                    if (arr[i][index] != 0) {
                        arr[i][j] = arr[i][index];
                        arr[i][index] = 0;
                    }
                }
            }
        }
    }

    public static void printBoard(int[][] arr) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
