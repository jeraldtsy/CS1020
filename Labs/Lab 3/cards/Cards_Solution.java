import java.util.*;

public class Cards {
    private static LinkedList cardList = new LinkedList();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCards, numQueries;
        String strInput;
        String[] arr;

        numCards = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numCards; i++) {
            strInput = sc.nextLine();
            arr = strInput.split(" ");
            Node node = new Node(arr[0], Integer.parseInt(arr[1]));
            cardList.insert(node);
        }

        numQueries = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numQueries; i++) {
            strInput = sc.nextLine();
            arr = strInput.split(" ");

            switch (arr[0]) {
                case "swap":
                    swap(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]));
                    break;
                case "details":
                    details(Integer.parseInt(arr[1]));
                    break;
                case "position":
                    position(arr[1]);
                    break;
                case "shuffle":
                    shuffle();
                    break;
                case "print":
                    print();
                    break;
            }
        }
    }


    public static void swap(int a, int b, int c, int d) {
        LinkedList newList = new LinkedList();

        int index = 0;
        Node current = cardList.getHead();
        Node newNode;

        //inserted elements before a
        while (index < (a - 1)) {
            newNode = new Node(current.getName(), current.getAge());
            newList.insert(newNode);
            current = current.getNext();
            index++;
        }

        //insert elements from c to d
        index = 0;
        current = cardList.getHead();
        //move to index c first
        while (index < (c - 1)) {
            current = current.getNext();
            index++;
        }

        //now insert elements from c to d
        while (index != d) {
            newNode = new Node(current.getName(), current.getAge());
            newList.insert(newNode);
            current = current.getNext();
            index++;
        }

        //insert elements from b to c, only if c - b is greater than 1
        if ((c - b) > 1) {
            index = 0;
            current = cardList.getHead();
            while (index < b) {
                current = current.getNext();
                index++;
            }

            for (int i = index; i < (c - 1); i++) {
                newNode = new Node(current.getName(), current.getAge());
                newList.insert(newNode);
                current = current.getNext();
            }
        }

        index = 0;
        current = cardList.getHead();

        //move to index a
        while (index < (a - 1)) {
            current = current.getNext();
            index++;
        }

        while (index != b) {
            newNode = new Node(current.getName(), current.getAge());
            newList.insert(newNode);
            current = current.getNext();
            index++;
        }

        //add the last elements
        index = 0;
        current = cardList.getHead();

        if ((d - 1) != cardList.getSize()) {
            while (index != d) {
                current = current.getNext();
                index++;
            }

            while (index != cardList.getSize()) {
                newNode = new Node(current.getName(), current.getAge());
                newList.insert(newNode);
                current = current.getNext();
                index++;
            }
        }

        cardList.clearList();
        current = newList.getHead();

        while (current != null) {
            cardList.insert(current);
            current = current.getNext();
        }
        System.out.println("swap has been performed");

        //printing for checking
      /*  current = newList.getHead();

        while (current != null) {
            System.out.println(current.getName());
            current = current.getNext();
        }*/
    }

    public static void details(int index) {
        int counter = 0;
        Node current = cardList.getHead();

        while ((current != null) && (counter != (index - 1))) {
            current = current.getNext();
            counter++;
        }

        System.out.println(current.getName() + " " + current.getAge());
    }

    public static void position(String name) {
        int index = 1;

        Node current = cardList.getHead();

        while ((current != null) && (!(current.getName().equals(name)))) {
            current = current.getNext();
            index++;
        }

        System.out.println(index);
    }

    public static void shuffle() {
        int size = cardList.getSize();
        int mid = size / 2;
        Node current = cardList.getHead();
        Node deckList1Node;
        Node deckList2Node;

        LinkedList deckList1 = new LinkedList();
        LinkedList deckList2 = new LinkedList();

        if ((size % 2) == 1) {
            for (int i = 0; i <= mid; i++) {
                Node newNode = new Node(current.getName(), current.getAge());
                deckList1.insert(newNode);
                current = current.getNext();
            }

            for (int i = (mid + 1); i < size; i++) {
                Node newNode = new Node(current.getName(), current.getAge());
                deckList2.insert(newNode);
                current = current.getNext();
            }
        } else {
            for (int i = 0; i < mid; i++) {
                Node newNode = new Node(current.getName(), current.getAge());
                deckList1.insert(newNode);
                current = current.getNext();
            }

            for (int i = mid; i < size; i++) {
                Node newNode = new Node(current.getName(), current.getAge());
                deckList2.insert(newNode);
                current = current.getNext();
            }
        }

        cardList.clearList();
        deckList1Node = deckList1.getHead();
        deckList2Node = deckList2.getHead();

        for (int i = 0; i < size; i++) {
            if ((i % 2) == 0) {
                cardList.insert(deckList1Node);
                deckList1Node = deckList1Node.getNext();
            } else {
                cardList.insert(deckList2Node);
                deckList2Node = deckList2Node.getNext();
            }
        }

        System.out.println("Shuffle has been performed");
    }

    public static void print() {
        Node current = cardList.getHead();

        while (current != null) {
            System.out.print(current.getName() + " ");
            current = current.getNext();

            if (current.getNext() == null) {
                System.out.print(current.getName());
                break;
            }
        }
        System.out.println();
    }
}

class Node {
    private String name;
    private int age;
    private Node next;
    private Node previous;

    public Node(String name, int age) {
        this.name = name;
        this.age = age;
        this.next = null;
        this.previous = null;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node node) {
        this.next = node;
    }

    public Node getPrevious() {
        return this.previous;
    }

    public void SetPrevious(Node node) {
        this.previous = node;
    }
}

class LinkedList {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public void insert(Node node) {
        if (this.head == null) {
            this.head = node;
            this.tail = head;
            this.size = 1;
        } else {
            this.tail.setNext(node);
            this.tail = node;
            this.size++;
        }
    }

    public Node getHead() {
        return head;
    }

    public int getSize() {
        return this.size;
    }

    public void clearList() {
        this.head = null;
    }
}