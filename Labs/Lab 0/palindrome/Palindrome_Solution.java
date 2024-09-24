// Solution
/*
 * author		: []
 * matric no.	: []
 */

import java.util.*;

public class Palindrome {
    /* use this method to check whether the string is palindrome word or not
     * 		PRE-Condition  :
     * 		POST-Condition :
     */
    public static boolean isPalindrome(String word) {
        return true;
    }

    public static void main(String[] args) {
        // declare the necessary variables
        String str1;
        String str2;

        // declare a Scanner object to read input
        Scanner sc = new Scanner(System.in);

        // read input and process them accordingly
        str1 = sc.nextLine();
        str2 = sc.nextLine();
        boolean isPalindrome = true;
        int length = str1.length();
        // simulate the problem
        for (int i = 0; i < length; i++) {
            if (str1.charAt(i) != str2.charAt(length - i - 1)) {
                isPalindrome = false;
                break;
            }
        }

        // output the result
        if (isPalindrome == true) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
