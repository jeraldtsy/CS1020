// Solution
/**
 * Name         :
 * Matric No.   :
 * Plab Acct.   :
 */

import java.sql.Array;
import java.util.*;

public class Storage {
    private static ArrayList<Box> boxList = new ArrayList<Box>();
    private static ArrayList<Item> itemOnHandList = new ArrayList<Item>();

    public void run() {
        // treat this as your "main" method
        Scanner sc = new Scanner(System.in);
        int numBoxes, maxHand, boxSize, numQueries;
        String cmd;
        String[] arr;

        numBoxes = sc.nextInt();
        maxHand = sc.nextInt();
        boxSize = sc.nextInt();
        numQueries = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numBoxes; i++) {
            Box box = new Box(boxSize, (i + 1));
            boxList.add(box);
        }

        for (int i = 0; i < numQueries; i++) {
            cmd = sc.nextLine();
            arr = cmd.split(" ");

            switch (arr[0]) {
                case "purchase":
                    purchaseItem(maxHand, arr[1], Integer.parseInt(arr[2]));
                    break;
                case "deposit":
                    depositItem(arr[1]);
                    break;
                case "withdraw":
                    withdrawItem(arr[1], maxHand);
                    break;
                case "location":
                    locationItem(arr[1]);
                    break;
                case "valuable":
                    valuableItem();
                    break;

            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public static void purchaseItem(int maxHand, String itemName, int itemValue) {
        Item item = new Item(itemName, itemValue);

        if (itemOnHandList.size() < maxHand) {
            itemOnHandList.add(item);
            System.out.println("item " + itemName + " is now being held");
            item.setItemLocation(0);

        } else {
            for (int i = 0; i < boxList.size(); i++) {
                if (!(boxList.get(i).isFull())) {
                    boxList.get(i).addItem(item);
                    System.out.println("item " + itemName + " has been deposited to box " + (i + 1));
                    item.setItemLocation((i + 1));
                    break;
                }
            }
        }
    }

    public static void depositItem(String itemName) {
        boolean isDeposited = false;
        boolean isPurchased = false;
        int currItemIndex = 0;

        for (Box box : boxList) {
            isDeposited = box.checkItem(itemName);
            if (isDeposited) {
                System.out.println("item " + itemName + " is already in storage");
            }
        }

        for (Item item : itemOnHandList) {
            if (item.getItemName().equals(itemName)) {
                isPurchased = true;
            }
            if (isPurchased) {
                break;
            }
            currItemIndex++;
        }

        if (!(isDeposited) && (isPurchased)) {
            for (Box box : boxList) {
                if (!(box.isFull())) {
                    box.addItem(itemOnHandList.get(currItemIndex));
                    itemOnHandList.remove(currItemIndex);
                    System.out.println("item " + itemName + " has been deposited to box " + box.getBoxNum());
                    break;
                }
            }
        }
    }

    public static void withdrawItem(String itemName, int maxHand) {
        boolean isPurchased = false;
        int currItemIndex;

        for (Item item : itemOnHandList) {
            if (item.getItemName().equals(itemName)) {
                isPurchased = true;
                System.out.println("item " + itemName + " is already being held");
                break;
            }
        }

        if (!isPurchased) {
            for (Box box : boxList) {
                List<Item> itemsInBox = box.getItemsInBoxList();
                for (int i = 0; i < itemsInBox.size(); i++) {
                    Item item = itemsInBox.get(i);
                    if (item.getItemName().equals(itemName)) {
                        isPurchased = true;
                        itemOnHandList.add(item);
                        itemsInBox.remove(i);
                        i--;
                    }
                }
            }
        }

        if (!(isPurchased)) {
            System.out.println("item " + itemName + " does not exist");
        }

    }

    public static void locationItem(String itemName) {
        boolean isDeposited;

        for (Item item : itemOnHandList) {
            if (item.getItemName().equals(itemName)) {
                System.out.println("item " + itemName + " is being held");
            }
        }

        for (int i = 0; i < boxList.size(); i++) {
            isDeposited = boxList.get(i).checkItem(itemName);

            if (isDeposited) {
                System.out.println("item " + itemName + " is in box " + (i + 1));
                break;
            }
        }
    }

    public static void valuableItem() {
        int mostValue = 0;
        String mostValuableItem = "";

        for (Item item : itemOnHandList) {
            if (item.getItemValue() > mostValue) {
                mostValue = item.getItemValue();
                mostValuableItem = item.getItemName();
            } else if (item.getItemValue() == mostValue) {
                if ((mostValuableItem.compareTo(item.getItemName()) > 0)) {
                    mostValuableItem = item.getItemName();
                }
            }
        }

        for (Box box : boxList) {
            for (Item item : box.getItemsInBoxList()) {
                if (item.getItemValue() > mostValue) {
                    mostValue = item.getItemValue();
                    mostValuableItem = item.getItemName();
                } else if (item.getItemValue() == mostValue) {
                    if ((mostValuableItem.compareTo(item.getItemName()) > 0)) {
                        mostValuableItem = item.getItemName();
                    }
                }
            }
        }
        System.out.println(mostValuableItem);
    }
}


class Box {
    // define appropriate attributes, constructor, and methods
    private int boxSize;
    private int boxNum;
    private ArrayList<Item> itemsInBoxList = new ArrayList<>();

    public Box(int boxSize, int boxNum) {
        this.boxSize = boxSize;
        this.boxNum = boxNum;
    }

    public void addItem(Item item) {
        itemsInBoxList.add(item);
    }

    public void removeItem(Item item) {
        itemsInBoxList.remove(item);
    }

    public ArrayList<Item> getItemsInBoxList() {
        return this.itemsInBoxList;
    }

    public boolean isFull() {
        if (itemsInBoxList.size() == this.boxSize) {
            return true;
        }
        return false;
    }

    public int getBoxNum() {
        return this.boxNum;
    }

    public boolean checkItem(String itemName) {
        for (Item item : itemsInBoxList) {
            if (item.getItemName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }
}

class Item {
    // define appropriate attributes, constructor, and methods
    private String itemName;
    private int itemLocation;
    private int itemValue;

    public Item(String itemName, int itemValue) {
        this.itemName = itemName;
        this.itemValue = itemValue;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemLocation(int itemLocation) {
        this.itemLocation = itemLocation;
    }

    public int getItemValue() {
        return this.itemValue;
    }
}
