import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N;
        int numQueries;
        N = sc.nextInt();

        if (N == 1) {
            String strGate;
            int bit1, bit2;
            numQueries = sc.nextInt();

            for (int i = 0; i < numQueries; i++) {
                strGate = sc.next();
                bit1 = sc.nextInt();
                bit2 = sc.nextInt();

                if (strGate.equals("AND")) {
                    if ((bit1 == 1) && (bit2 == 1)) {
                        System.out.println("1");
                    } else {
                        System.out.println("0");
                    }
                } else if (strGate.equals("OR")) {
                    if ((bit1 == 1) || (bit2 == 1)) {
                        System.out.println("1");
                    } else {
                        System.out.println("0");
                    }
                }
            }
        } else if (N == 2) {
            String strGate;
            int bit1, bit2;

            strGate = sc.next();

            while (!(strGate.equals("0"))) {
                bit1 = sc.nextInt();
                bit2 = sc.nextInt();

                if (strGate.equals("AND")) {
                    if ((bit1 == 1) && (bit2 == 1)) {
                        System.out.println("1");
                    } else {
                        System.out.println("0");
                    }
                } else if (strGate.equals("OR")) {
                    if ((bit1 == 1) || (bit2 == 1)) {
                        System.out.println("1");
                    } else {
                        System.out.println("0");
                    }
                }
                strGate = sc.next();
            }
        } else {
            String strGate = "";
            int bit1, bit2;

            while (sc.hasNext()) {
                strGate = sc.next();
                bit1 = sc.nextInt();
                bit2 = sc.nextInt();

                if (strGate.equals("AND")) {
                    if ((bit1 == 1) && (bit2 == 1)) {
                        System.out.println("1");
                    } else {
                        System.out.println("0");
                    }
                } else if (strGate.equals("OR")) {
                    if ((bit1 == 1) || (bit2 == 1)) {
                        System.out.println("1");
                    } else {
                        System.out.println("0");
                    }
                }

            }
        }
    }
}
