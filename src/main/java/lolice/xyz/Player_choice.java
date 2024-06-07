package lolice.xyz;

import java.util.Scanner;

public class Player_choice  {
    public static String GetClassName(String[] args) {
        Scanner UserClass = new Scanner(System.in);
        System.out.println("Enter your class: ");
        String ClassName = UserClass.nextLine();
        return ClassName;
    }
}
