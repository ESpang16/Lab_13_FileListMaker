import java.util.Scanner;

public class SafeInput {
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int result = 0;
        boolean done = false;
        String trash;
        do {
            System.out.println(prompt + "[" + low + "-" + high + "]:");
            if (pipe.hasNextInt()) {
                result = pipe.nextInt();
                pipe.nextLine();
                if (result >= low && result <= high) {
                    done = true;
                } else {
                    System.out.println("You must enter a value in the range [" + low + "-" + high + "]:");
                }
            }else { trash=pipe.nextLine();
                System.out.println("Invalid input." + trash + "Please enter a valid integer.");
            }
        } while (!done);
        return result;
    }
    public static String getRegExString(Scanner pipe, String prompt, String regExPattern){
        String value;
        boolean done = false;
        do {
            System.out.println(prompt +":");
            value=pipe.nextLine();
            if(value.matches(regExPattern)){
                done = true;
            }else{
                System.out.println("\n Invalid input: " + value);
            }
        }while (!done);
        return value;
    }
    public static String getNonZeroLengthString(Scanner pipe, String prompt){
        String retString;
        do {
            System.out.print("\n" +prompt + ": ");
            retString=pipe.nextLine();
        }while (retString.isEmpty());
        return retString;
    }
    public static int getInt(Scanner pipe, String prompt) {
        int result = 0;
        boolean done = false;
        String trash;
        do {
            System.out.println(prompt + ":");
            if (pipe.hasNextInt()) {
                result = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.next();
                System.out.println("Invalid input! You must enter an integer:");
            }
        } while (!done);
        return result;
    }
    public static double getDouble(Scanner pipe, String prompt) {
        double result = 0.0;
        boolean done = false;
        String trash;
        do {
            System.out.println(prompt + ":");
            if (pipe.hasNextDouble()) {
                result = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.next();
                System.out.println("Invalid input! You must enter a decimal or whole number:");
            }
        } while (!done);
        return result;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double result = 0.0;
        boolean done = false;
        String trash;
        do {
            System.out.println(prompt + "[" + low + "-" + high + "]:");
            if (pipe.hasNextDouble()) {
                result = pipe.nextDouble();
                pipe.nextLine();
                if (result >= low && result <= high) {
                    done = true;
                } else {
                    System.out.println("You must enter a value in the range [" + low + "-" + high + "]:");
                }
            }else{System.out.println("You must enter a value in the range [" + low + "-" + high + "]:");
                trash=pipe.nextLine();
            }
        } while (!done);
        return result;
    }
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String choice="";
        boolean done = false;
        String trash="";
        do {
            System.out.println(prompt + ":");
            choice=pipe.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                done=true;
            }else if (choice.equalsIgnoreCase("N")) {
                done=true;
            }else {
                System.out.println("Invalid input! You need to enter [Y or N].");
            }
        } while(!done);
        return choice.equalsIgnoreCase("Y");
    }
    public static void prettyHeader(String msg) {
        final int WIDTH = 60;
        int msgLength = msg.length();
        int total = WIDTH - (msgLength + 6);
        int left = total / 2;
        int right = total - left;
        for (int i = 0; i < WIDTH; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.print("***");
        for (int i = 0; i < left; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < right; i++) {
            System.out.print(" ");
        }
        System.out.println("***");
        for (int i = 0; i < WIDTH; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}
