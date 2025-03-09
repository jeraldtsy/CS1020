import java.util.*;

public class Dna{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int strLength, subStrLength, numQueries, ans;
        String str, subStr, searchText;

        strLength = sc.nextInt();
        subStrLength = sc.nextInt();
        sc.nextLine();
        str = sc.nextLine();
        numQueries = sc.nextInt();
        sc.nextLine();

        ArrayList<String> arrList = new ArrayList<>();
        HashMap<String, Integer> hashmap = new HashMap<>();

        for (int i = 0; i < numQueries; i++) {
            subStr = sc.nextLine();
            arrList.add(subStr);
            hashmap.put(subStr, 0);
        }

        for (int i = 0; i <= strLength - subStrLength; i++) {
            ans = 0;
            searchText = str.substring(i, i+subStrLength);
            if (hashmap.containsKey(searchText)) {
                hashmap.put(searchText, hashmap.get(searchText) + 1);
            }

        }
        for (int i = 0; i < arrList.size(); i++) {
            System.out.println(hashmap.getOrDefault(arrList.get(i), 0));
        }


    }
}