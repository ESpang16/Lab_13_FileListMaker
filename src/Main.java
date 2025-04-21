import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> myArrlist = new ArrayList<>();
        boolean quit = false;
        boolean needsToBeSaved = false;
        String currentFileName = "";

        do {
            displayList(myArrlist);
            String choice = getMenuChoice(in);
            try {
                switch (choice) {
                    case "A":
                        addItem(myArrlist, in);
                        needsToBeSaved = true;
                        break;
                    case "I":
                        insertItem(myArrlist, in);
                        needsToBeSaved = true;
                        break;
                    case "D":
                        deleteItem(myArrlist, in);
                        needsToBeSaved = true;
                        break;
                    case "M":
                        moveItem(myArrlist, in);
                        needsToBeSaved = true;
                        break;
                    case "C":
                        clearList(myArrlist, in);
                        needsToBeSaved = true;
                        break;
                    case "V":
                        displayList(myArrlist);
                        break;
                    case "O":
                        if (needsToBeSaved && !myArrlist.isEmpty()) {
                            if (SafeInput.getYNConfirm(in, "You have unsaved changes. Save before loading?")) {
                                if (currentFileName.isEmpty()) {
                                    currentFileName = SafeInput.getNonZeroLengthString(in, "Enter filename to save to") + ".txt";
                                }
                                saveFile(myArrlist, currentFileName);
                                needsToBeSaved = false;
                            }
                        }
                        loadFile(myArrlist, in);
                        currentFileName = SafeInput.getNonZeroLengthString(in, "Enter filename just loaded (base name)") + ".txt";
                        needsToBeSaved = false;
                        break;
                    case "S":
                        if (currentFileName.isEmpty()) {
                            currentFileName = SafeInput.getNonZeroLengthString(in, "Enter filename to save to") + ".txt";
                        }
                        saveFile(myArrlist, currentFileName);
                        needsToBeSaved = false;
                        break;
                    case "Q":
                        if (needsToBeSaved) {
                            if (SafeInput.getYNConfirm(in, "You have unsaved changes. Save before quitting?")) {
                                if (currentFileName.isEmpty()) {
                                    currentFileName = SafeInput.getNonZeroLengthString(in, "Enter filename to save to") + ".txt";
                                }
                                saveFile(myArrlist, currentFileName);
                            }
                        }
                        quit = true;
                        System.out.println("Goodbye!");
                        break;
                }
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            }
        } while (!quit);
    }

    public static void displayList(ArrayList<String> list) {
        System.out.println("\n=== Current List ===");
        if (list.isEmpty()) {
            System.out.println("[List is empty]");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, list.get(i));
            }
        }
    }

    public static String getMenuChoice(Scanner in) {
        System.out.println("\n=== Menu ===");
        System.out.println("A - Add\nI - Insert\nD - Delete\nM - Move\nC - Clear\nV - View\nO - Open\nS - Save\nQ - Quit");
        return SafeInput.getRegExString(in, "Enter your choice", "[AaIiDdMmCcVvOoSsQq]").toUpperCase();
    }

    public static void addItem(ArrayList<String> list, Scanner in) {
        String item = SafeInput.getNonZeroLengthString(in, "Enter item to add");
        list.add(item);
        System.out.println("Item added!");
    }

    public static void insertItem(ArrayList<String> list, Scanner in) {
        int index = list.isEmpty() ? 0 : SafeInput.getRangedInt(in, "Enter position to insert at", 1, list.size() + 1) - 1;
        String item = SafeInput.getNonZeroLengthString(in, "Enter item to insert");
        list.add(index, item);
        System.out.println("Item inserted!");
    }

    public static void deleteItem(ArrayList<String> list, Scanner in) {
        if (list.isEmpty()) {
            System.out.println("List is empty. Nothing to delete.");
        } else {
            int index = SafeInput.getRangedInt(in, "Enter number of item to delete", 1, list.size()) - 1;
            String removed = list.remove(index);
            System.out.println("Removed: " + removed);
        }
    }

    public static void moveItem(ArrayList<String> list, Scanner in) {
        if (list.size() < 2) {
            System.out.println("Need at least two items to move.");
            return;
        }
        int fromIndex = SafeInput.getRangedInt(in, "Enter the number of the item to move", 1, list.size()) - 1;
        int toIndex = SafeInput.getRangedInt(in, "Enter the new position for the item", 1, list.size()) - 1;

        String item = list.remove(fromIndex);
        list.add(toIndex, item);
        System.out.println("Item moved!");
    }

    public static void clearList(ArrayList<String> list, Scanner in) {
        if (SafeInput.getYNConfirm(in, "Are you sure you want to clear the list?")) {
            list.clear();
            System.out.println("List cleared!");
        }
    }

    public static void saveFile(ArrayList<String> list, String fileName) throws IOException {
        PrintWriter out = new PrintWriter(fileName);
        for (String item : list) {
            out.println(item);
        }
        out.close();
        System.out.println("List saved to " + fileName);
    }

    public static void loadFile(ArrayList<String> list, Scanner in) {
        try {
            list.clear();
            String fileName = SafeInput.getNonZeroLengthString(in, "Enter the file name to load") + ".txt";
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                list.add(fileScanner.nextLine());
            }
            fileScanner.close();
            System.out.println("List loaded from " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Make sure it's in the project folder.");
        }
    }
}