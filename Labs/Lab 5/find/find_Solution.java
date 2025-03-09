import java.util.*;

public class Find {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int strLength;
        int subStrLength;
        int numQueries;

        strLength = sc.nextInt();
        subStrLength = sc.nextInt();
        sc.nextLine();

        String str1 = sc.nextLine();
        String str2 = sc.nextLine();

        numQueries = sc.nextInt();
        sc.nextLine();

        LinkedHashMap<String, Integer> hashmap = new LinkedHashMap<>();
        ArrayList<String> arrList = new ArrayList<>();

        for (int i = 0; i < numQueries; i++) {
            String query = sc.nextLine();
            hashmap.put(query, 0);
            arrList.add(query);
        }


        for (String query : hashmap.keySet()) {
            boolean containsStr1 = false;
            boolean containsStr2 = false;

            // Check for query in substrings of str1
            for (int j = 0; j <= strLength - subStrLength; j++) {
                if (str1.substring(j, j + subStrLength).equals(query)) {
                    containsStr1 = true;
                    break; // No need to check further if found
                }
            }

            // Check for query in substrings of str2
            for (int j = 0; j <= strLength - subStrLength; j++) {
                if (str2.substring(j, j + subStrLength).equals(query)) {
                    containsStr2 = true;
                    break; // No need to check further if found
                }
            }

            // Update hashmap based on findings
            if (containsStr1 && containsStr2) {
                hashmap.put(query, 3); // Found in both
            } else if (containsStr1) {
                hashmap.put(query, 1); // Found only in str1
            } else if (containsStr2) {
                hashmap.put(query, 2); // Found only in str2
            } else {
                hashmap.put(query, 0); // Not found in either
            }
        }

        for (int i = 0; i < arrList.size(); i++) {
            if (hashmap.containsKey(arrList.get(i))) {
                System.out.println(hashmap.getOrDefault(arrList.get(i), 0));
            }
        }
    }
}
