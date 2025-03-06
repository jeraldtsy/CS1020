import java.util.*;

public class Chemistry {
    private static Stack<Integer> elementsStack = new Stack<>();
    private static ArrayList<Element> elementList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numElements, mass;
        char elementName;
        String chemicalName;
        numElements = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numElements; i++) {
            elementName = sc.next().charAt(0);
            mass = sc.nextInt();

            Element element = new Element(elementName, mass);
            elementList.add(element);
        }

        sc.nextLine();
        chemicalName = sc.nextLine();

        calculateMass(chemicalName);

    }

    public static void calculateMass(String chemicalName) {
        int ans = 0;

        for (char ch : chemicalName.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                for (Element searchElement : elementList) {
                    if (searchElement.getName() == ch) {
                        elementsStack.push(searchElement.getMass());
                    }
                }
            } else if (Character.isDigit(ch)) {
                int temp;

                temp = elementsStack.pop();
                temp *= Character.getNumericValue(ch);

                elementsStack.push(temp);
            } else if (ch == '(') {
                elementsStack.push(-1);
            } else if (ch == ')') {
                int temp = 0;

                while (!(elementsStack.empty())) {
                    if (elementsStack.peek() == -1) {
                        elementsStack.pop();
                        break;
                    } else {
                        temp += elementsStack.pop();
                    }
                }
                elementsStack.push(temp);
            }
        }

        while (!(elementsStack.empty())) {
            ans += elementsStack.pop();
        }

        System.out.println(ans);
    }
}

class Element {
    private char name;
    private int mass;

    public Element(char name, int mass) {
        this.name = name;
        this.mass = mass;
    }

    public int getMass() {
        return this.mass;
    }

    public char getName() {
        return this.name;
    }
}