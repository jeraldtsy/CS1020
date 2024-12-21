import java.util.*;

public class Subway {
    private static LinkedList stationList = new LinkedList();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arrQuery;

        int numInputs;
        numInputs = sc.nextInt();
        String strInput;
        sc.nextLine();

        for (int i = 0; i < numInputs; i++) {
            strInput = sc.nextLine();
            Node node = new Node(strInput);
            stationList.insert(node);
        }

        numInputs = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numInputs; i++) {
            strInput = sc.nextLine();
            arrQuery = strInput.split(" ");

            switch (arrQuery[0]) {
                case "TIME":
                    calculateTime(arrQuery[1], arrQuery[2]);
                    break;
                case "BUILD":
                    buildStation(arrQuery[1], arrQuery[2]);
                    break;
                case "PATH":
                    bestPath(arrQuery[1], arrQuery[2]);
                    break;
                case "PRINT":
                    printStations(arrQuery[1]);
                    break;
            }
        }
    }

    public static void calculateTime(String startStation, String endStation) {
        int intervals = 0;
        int time;
        int fwdSteps = 0, bckSteps = 0;

        Node start = stationList.getHead();
        Node end = stationList.getHead();
        Node current;

        while (!(start.getStationName().equals(startStation))) {
            start = start.getNext();
        }

        while (!(end.getStationName().equals(endStation))) {
            end = end.getNext();
        }

        current = start;

        while (!(current == end)) {
            if (current.getNext() == null) {
                current = stationList.getHead();
            } else {
                current = current.getNext();
            }
            fwdSteps++;
        }

        current = end;

        while (!(current == start)) {
            if (current.getNext() == null) {
                current = stationList.getHead();
            } else {
                current = current.getNext();
            }
            bckSteps++;
        }

        if (fwdSteps <= bckSteps) {
            intervals = fwdSteps;
        } else if (bckSteps < fwdSteps) {
            intervals = bckSteps;
        }

        if (intervals == 0) {
            time = 0;
        } else {
            time = (2 * intervals) + intervals - 1;
        }
        System.out.println(time);
    }

    public static void buildStation(String precedeStation, String newStation) {
        Node node = new Node(newStation);//middle
        Node beforeNew;//left
        Node afterNew;//right

        beforeNew = stationList.getHead();

        while (!(beforeNew.getStationName().equals(precedeStation))) {
            beforeNew = beforeNew.getNext();
        }
        afterNew = beforeNew.getNext();
        if (afterNew == null) {
            afterNew = stationList.getHead();
        }

        beforeNew.setNext(node);
        node.setPrevious(beforeNew);

        node.setNext(afterNew);
        afterNew.setPrevious(node);

        System.out.println("station " + newStation + " has been built");
    }

    public static void bestPath(String startStation, String endStation) {
        Node current = stationList.getHead();
        Node fwdNode, bckNode;

        int fwdCount = 0;
        int bckCount = 0;

        while (!(current.getStationName().equals(startStation))) {
            current = current.getNext();
        }

        fwdNode = current;
        bckNode = current;

        //move forward
        while (!(fwdNode.getStationName().equals(endStation))) {
            fwdNode = fwdNode.getNext();
            if (fwdNode == null) {
                fwdNode = stationList.getHead();
            }
            fwdCount++;
        }

        //move backward
        while (!(bckNode.getStationName().equals(endStation))) {
            bckNode = bckNode.getPrevious();
            if (bckNode == null) {
                bckNode = stationList.getTail();
            }
            bckCount++;
        }

        fwdNode = current;
        bckNode = current;

        if (fwdCount <= bckCount) {
            while (!(fwdNode.getStationName().equals(endStation))) {
                System.out.print(fwdNode.getStationName() + " ");
                fwdNode = fwdNode.getNext();
                if (fwdNode == null) {
                    fwdNode = stationList.getHead();
                }
            }
        } else {
            while (!(bckNode.getStationName().equals(endStation))) {
                System.out.print(bckNode.getStationName() + " ");
                bckNode = bckNode.getPrevious();
                if (bckNode == null) {
                    bckNode = stationList.getTail();
                }
            }
        }

        System.out.println(endStation);
    }

    public static void printStations(String startStation) {
        Node current = stationList.getHead();
        Node end;

        while (!(current.getStationName().equals(startStation))) {
            current = current.getNext();
        }

        end = current.getPrevious();

        if (end == null) {
            end = stationList.getTail();
        }

        while (!(current.getStationName().equals(end.getStationName()))) {
            System.out.print(current.getStationName() + " ");
            if (current.getNext() == null) {
                current = stationList.getHead();
            } else {
                current = current.getNext();
            }
        }
        System.out.println(end.getStationName());
    }

}

class Node {
    private String stationName;
    private Node next;
    private Node previous;

    public Node(String stationName) {
        this.stationName = stationName;
        this.next = null;
        this.previous = null;
    }

    public String getStationName() {
        return this.stationName;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
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
            node.setPrevious(tail);
            this.tail = node;
        }
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }
}