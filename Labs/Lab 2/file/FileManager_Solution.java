//Solution
/*
Name:
Matric Number:
*/

import java.util.*;

class FileManager {
    private static List<Folder> listOfFolders = new ArrayList<Folder>();

    public static void main(String[] args) {
        int numQueries;
        Scanner sc = new Scanner(System.in);
        String strInput;
        String[] arr;

        numQueries = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numQueries; i++) {
            strInput = sc.nextLine();
            arr = strInput.split(" ");

            if (arr[0].equals("Createfolder")) {
                createFolder(arr[1]);

            } else if (arr[0].equals("Createfile")) {
                createFile(arr[1], Integer.parseInt(arr[2]), arr[3]);
            } else if (arr[0].equals("Deletefile")) {
                deleteFile(arr[1]);
            } else if (arr[0].equals("Count")) {
                countFolder(arr[1]);
            } else if (arr[0].equals("Movefile")) {
                moveFile(arr[1], arr[2]);
            } else if (arr[0].equals("Findlargest")) {
                findLargest();
            }
        }
    }

    public static void createFolder(String folderName) {
        Folder folder = new Folder();
        folder.setFolderName(folderName);
        listOfFolders.add(folder);
    }

    public static void createFile(String fileName, int fileSize, String folderName) {
        File file = new File();
        file.setFileName(fileName);
        file.setFileSize(fileSize);
        file.setFolderName(folderName);

        for (Folder folder : listOfFolders) {
            if (folder.getFolderName().equals(folderName)) {
                folder.addFile(file);
            }
        }
    }

    public static void deleteFile(String fileName) {
        for (Folder folder : listOfFolders) {
            for (File file : folder.getList()) {
                if (file.getFileName().equals(fileName)) {
                    folder.deleteFile(file);
                    break;
                }
            }
        }
    }

    public static void countFolder(String folderName) {
        int fileSize = 0;

        for (Folder folder : listOfFolders) {
            if (folder.getFolderName().equals(folderName)) {
                for (File file : folder.getList()) {
                    fileSize += file.getFileSize();
                }
                break;
            }
        }
        System.out.println(fileSize);
    }

    public static void moveFile(String fileName, String folderName) {
        File moveFile = null;
        for (Folder folder : listOfFolders) {
            for (File file : folder.getList()) {
                if ((file.getFileName().equals(fileName))) {
                    moveFile = file;
                    folder.deleteFile(file);
                    break;
                }
            }
        }

        for (Folder folder : listOfFolders) {
            if (folder.getFolderName().equals(folderName)) {
                folder.addFile(moveFile);
            }
        }
    }

    public static void findLargest() {
        int largestFileSize = 0;
        int currFileSize;
        String strFile = "";

        for (Folder folder : listOfFolders) {
            currFileSize = 0;
            for (File file : folder.getList()) {
                currFileSize += file.getFileSize();
            }
            if (currFileSize > largestFileSize) {
                largestFileSize = currFileSize;
                strFile = folder.getFolderName();
            }
        }
        System.out.println(strFile);
    }

}

class File {
    private String fileName;
    private int fileSize;
    private String folderName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getFileSize() {
        return this.fileSize;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}

class Folder {
    private String folderName;
    private List<File> listOfFiles = new ArrayList<File>();

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public void addFile(File file) {
        listOfFiles.add(file);
    }

    public void deleteFile(File file) {
        for (int i = 0; i < listOfFiles.size(); i++) {
            if (listOfFiles.get(i).getFileName().equals(file.getFileName())) {
                listOfFiles.remove(i);
            }
        }
    }

    public List<File> getList() {
        return listOfFiles;
    }
}
