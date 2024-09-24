import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        int result;

        result = str1.compareTo(str2);

        if (result < 0) {
            System.out.println("1");
        } else if (result > 0) {
            System.out.println("2");
        } else {
            System.out.println("0");
        }
    }
}
