import java.util.*;

public class Main {
    private static Stack<Element> radiationStack = new Stack<>();
    private static ArrayList<Element> arrList = new ArrayList();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numElements;
        int elementStrength;
        int years;
        numElements = sc.nextInt();


        for (int i = 0; i < numElements; i++) {
            elementStrength = sc.nextInt();

            Element element = new Element(elementStrength, 0);
            arrList.add(element);
        }

        years = doDecay();

        System.out.println(years);
    }

    public static int doDecay() {
        int years = 0;
        boolean decayHappened = true;
        boolean incrementedDone;

        while (decayHappened) {
            decayHappened = false;
            incrementedDone = false;
            for (int i = 0; i < arrList.size(); i++) {
                if ((radiationStack.isEmpty())) {
                    radiationStack.push(arrList.get(i));
                } else {
                    if (radiationStack.peek().getStrength() > arrList.get(i).getStrength()) {
                        radiationStack.pop();
                        radiationStack.push(arrList.get(i));
                    } else if (radiationStack.peek().getStrength() < arrList.get(i).getStrength()) {
                        radiationStack.pop();
                        radiationStack.push(arrList.get(i));
                        arrList.remove(i);
                        i--;

                        if (!(incrementedDone)) {
                            incrementedDone = true;
                            decayHappened = true;
                            years++;
                        }
                    }
                }
            }
            radiationStack.clear();
            if (!(decayHappened)) {
                break;
            }
        }
        return years;
    }
}

class Element {
    private int strength;
    private int years;

    public Element(int strength, int years) {
        this.strength = strength;
        this.years = years;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getYears() {
        return this.years;
    }

    public void setYears(int years) {
        this.years = years;
    }
}