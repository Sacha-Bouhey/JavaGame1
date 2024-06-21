package lolice.xyz.Players;

import java.util.Scanner;

public class Player_choice  {
    public static String GetClassName() {
        Scanner UserClass = new Scanner(System.in);

        System.out.println("Enter your class: ");
        String ClassName = UserClass.nextLine();
        return ClassName;
    }
    public static String GetSkillName() {
        Scanner UserSkill = new Scanner(System.in);
        System.out.println("Enter your skill: ");
        String SkillName = UserSkill.nextLine();
        return SkillName;
    }
}
