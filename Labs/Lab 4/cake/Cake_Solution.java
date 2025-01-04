import java.util.*;

public class Cake {
    private static Queue<Cake> cakeQueue = new LinkedList();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cakeSize;
        int maxRaisin;
        String strInput;
        String[] arr;

        cakeSize = sc.nextInt();
        maxRaisin = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cakeSize; i++) {
            strInput = sc.nextLine();
            arr = strInput.split(" ");

            Cake cake = new Cake(strInput.charAt(0), Integer.parseInt(arr[1]));
            cakeQueue.offer(cake);
        }

        calculateCake(maxRaisin);
    }

    public static void calculateCake(int maxRaisin) {
        int currRaisin;
        int curr = 0;
        int ans = 0;

        Iterator<Cake> outerIterator = cakeQueue.iterator();

        while (outerIterator.hasNext()) {
            Cake currCake = outerIterator.next();
            currRaisin = 0;
            curr = 0;

            if (currCake.getType() == 'R') {
                currRaisin++;
            }

            curr = currCake.getQuantity();

            Iterator<Cake> innerIterator = cakeQueue.iterator();

            while (innerIterator.hasNext()) {
                if (innerIterator.next() == currCake) {
                    break;
                }
            }

            while (innerIterator.hasNext()) {
                Cake slidingCake = innerIterator.next();

                if (slidingCake.getType() == 'R') {
                    currRaisin++;
                    if (currRaisin > maxRaisin) {
                        break;
                    }
                }
                curr += slidingCake.getQuantity();
            }
            if (curr > ans) {
                ans = curr;
            }
        }
        System.out.println(ans);
    }
}


class Cake {
    private char type;
    private int quantity;

    public Cake(char type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public char getType() {
        return this.type;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
