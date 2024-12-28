import java.util.*;

public class Passing {
    private static LinkedList playerList = new LinkedList();

    public static void passing(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numPlayers;
        int numPasses;
        int limit;
        ArrayList<Integer> arrList = new ArrayList<Integer>();

        numPlayers = sc.nextInt();
        numPasses = sc.nextInt();
        limit = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numPlayers; i++) {
            Node node = new Node(sc.nextLine());
            playerList.insert(node);
        }

        for (int i = 0; i < numPasses; i++) {
            arrList.add(sc.nextInt());
        }

        runGame(arrList, limit, numPlayers);
    }

    public static void runGame(ArrayList<Integer> arrList, int limit, int numPlayers) {
        int numPasses;

        Node previous = playerList.getHead();
        Node current = playerList.getHead();

        for (int i = 0; i < arrList.size(); i++) {
            numPasses = 0;

            while (numPasses != (arrList.get(i) % numPlayers)) {
                current = current.getNext();
                numPasses++;
            }

            System.out.println(current.getPlayerName());
            current.incrementNumPasses();

            if (limit == current.getNumPasses()) {
                Node nextPlayer = current.getNext();
                playerList.delete(current);
                numPlayers--;
                current = nextPlayer;
                previous = nextPlayer;
            } else {
                swap(previous, current);
                current = playerList.getHead();
            }
        }
    }

    public static void swap(Node current, Node previous) {
        String tempPlayer;
        int tempNumPasses;

        tempPlayer = current.getPlayerName();
        tempNumPasses = current.getNumPasses();

        current.setPlayerName(previous.getPlayerName());
        current.setNumPasses(previous.getNumPasses());

        previous.setPlayerName(tempPlayer);
        previous.setNumPasses(tempNumPasses);

    }

    public static void print() {
        Node current = playerList.getHead();
        for (int i = 0; i < 5; i++) {
            if (current == null)
                break;
            System.out.print(current.getPlayerName() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

}

class Node {
    private String playerName;
    private int numPasses;
    private boolean hasBall;
    private Node next;


    public Node(String playerName) {
        this.playerName = playerName;
        this.numPasses = 0;
        this.next = null;
        this.hasBall = false;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setHasBall(boolean hasBall) {
        this.hasBall = hasBall;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void incrementNumPasses() {
        this.numPasses += 1;
    }

    public int getNumPasses() {
        return this.numPasses;
    }

    public void setNumPasses(int numPasses) {
        this.numPasses = numPasses;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

class LinkedList {
    private Node head = null;
    private Node tail = null;

    public void insert(Node node) {
        if (this.head == null) {
            this.head = node;
            this.tail = head;
        } else {
            this.tail.setNext(node);
            this.tail = node;
        }
    }

    public void delete(Node node) {
        Node current;
        Node previous;

        if (head == node) {
            head = head.getNext();
        } else {
            current = head;
            previous = null;

            while ((current != null) && (current != node)) {
                previous = current;
                current = current.getNext();
            }
            previous.setNext(current.getNext());
        }
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }
}