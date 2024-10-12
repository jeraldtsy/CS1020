// Solution

import java.util.*;

public class Forum {
    private static ArrayList<User> listOfUsers = new ArrayList<User>();
    private static ArrayList<Thread> listOfThreads = new ArrayList<Thread>();
    private static ArrayList<Post> listOfPosts = new ArrayList<Post>();

    public static void Main(String[] args) {
        //define your main method here
        Scanner sc = new Scanner(System.in);

        int numUsers, numThreads, numQueries;
        String cmd, strInput;
        String[] arr;

        numUsers = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numUsers; i++) {
            User user = new User();
            strInput = sc.nextLine();
            user.setUserName(strInput);
            listOfUsers.add(user);
        }

        numThreads = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread();
            strInput = sc.nextLine();
            thread.setThreadName(strInput);
            listOfThreads.add(thread);
        }

        numQueries = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numQueries; i++) {
            cmd = sc.nextLine();
            arr = cmd.split(" ");

            if (arr[0].equals("post")) {
                String msg = "";
                for (int j = 3; j < arr.length; j++) {
                    msg += arr[j] + " ";
                }
                msg = msg.substring(0, msg.length() - 1);
                postThread(arr[1], arr[2], msg);

            } else if (arr[0].equals("count")) {
                String[] queryArr = cmd.split(" ");
                int ans = 0;

                for (int j = 2; j < queryArr.length; j++) {
                    ans += countThread(queryArr[j]);
                }
                System.out.println(ans);

            } else if (arr[0].equals("numpost")) {
                numPost(arr[1]);
            } else if (arr[0].equals("maxpost")) {
                maxPost(arr[1]);
            } else if (arr[0].equals("content")) {
                content(arr[1], Integer.parseInt(arr[2]));
            }
        }
        sc.close();
    }

    public static void postThread(String threadName, String userName, String message) {
        boolean threadExists = false;
        boolean userExists = false;
        int currThreadIndex = 0;
        int currUserIndex = 0;

        for (int i = 0; i < listOfThreads.size(); i++) {
            if (threadName.equals(listOfThreads.get(i).getThreadName())) {
                currThreadIndex = i;
                threadExists = true;
                break;
            }
        }

        for (int i = 0; i < listOfUsers.size(); i++) {
            if (userName.equals(listOfUsers.get(i).getUserName())) {
                currUserIndex = i;
                userExists = true;
                break;
            }
        }

        if (!(threadExists)) {
            System.out.println("no such thread");
        } else if (!(userExists)) {
            System.out.println("no such user");
        } else {
            Post post = new Post();
            post.setMessage(message);
            post.setUserName(userName);
            listOfPosts.add(post);
            listOfUsers.get(currUserIndex).incrementPost();
            listOfThreads.get(currThreadIndex).incrementThread();
            System.out.println(post.getMessage());
        }

    }

    public static int countThread(String threadName) {
        for (Thread thread : listOfThreads) {
            if (thread.getThreadName().equals(threadName)) {
                return thread.getThreadSize();
            }
        }

        return -1;
    }

    public static void numPost(String userName) {
        boolean userExists = false;
        int numMsgs = 0;

        for (int i = 0; i < listOfUsers.size(); i++) {
            if (listOfUsers.get(i).getUserName().equals(userName)) {
                userExists = true;
                numMsgs = listOfUsers.get(i).getNumPosts();
            }
        }

        if (!(userExists)) {
            System.out.println("no such user");
        } else {
            System.out.println(numMsgs);
        }
    }

    public static void maxPost(String threadName) {
        int max = 0;
        int temp;
        boolean threadExists = false;
        String username = "";

        for (Thread thread : listOfThreads) {
            if (thread.getThreadName().equals(threadName)) {
                threadExists = true;
                break;
            }
        }
        if (!(threadExists)) {
            System.out.println("no such thread");
        } else {
            for (Thread thread : listOfThreads) {
                if (thread.getThreadName().equals(threadName)) {
                    for (User user : listOfUsers) {
                        temp = 0;
                        for (Post post : listOfPosts) {
                            if (post.getUserName().equals(user.getUserName())) {
                                temp++;
                            }
                        }
                        if (temp > max) {
                            max = temp;
                            username = user.getUserName();
                        } else if (temp == max) {
                            if (username.compareTo(user.getUserName()) > 0) {
                                username = user.getUserName();
                            }
                        }
                    }
                }
            }
        }
        System.out.println(username);
    }

    public static void content(String threadName, int msgNum) {
        boolean threadExists = false;
        boolean postExists = false;

        for (Thread thread : listOfThreads) {
            if (thread.getThreadName().equals(threadName)) {
                threadExists = true;
                break;
            }
        }

        if ((listOfPosts.size() + 1) > msgNum) {
            postExists = true;
        }

        if (!(threadExists)) {
            System.out.println("no such thread");
        } else if (!(postExists)) {
            System.out.println("no such post");
        } else {
            System.out.println(listOfPosts.get(msgNum - 1).getMessage());
        }
    }
}


//threadList contains a list of posts
class Thread {
    //define the appropriate attributes, constructor, and methods here
    private String threadName;
    private ArrayList<Post> threadList = new ArrayList<Post>();
    private int threadSize = 0;

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getThreadName() {
        return this.threadName;
    }

    public void addToThread(Post post) {
        threadList.add(post);
        incrementThread();
    }

    public void incrementThread() {
        this.threadSize++;
    }

    public int getThreadSize() {
        return this.threadSize;
    }
}

class Post {
    //define the appropriate attributes, constructor, and methods here
    private String message;
    private String userName;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }
}

class User {
    //define the appropriate attributes, constructor, and methods here
    private String userName;
    private int numPosts = 0;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public int getNumPosts() {
        return this.numPosts;
    }

    public void incrementPost() {
        this.numPosts++;
    }
}
