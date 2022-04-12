package wolfPubDB.menu;

import java.util.*;

public class SalesMenu{

    public static void salesMenu (){
        Scanner sc = new Scanner(System.in);
        String[] args = null;
        while (true) {
            System.out.println("Welcome to Sales!!");
            System.out.println("Please select from the following!!");
            System.out.println("1. Distributor Operations");
            System.out.println("2. Order Operations");
            System.out.println("3. Reports");
            System.out.println("4. Back to Main Menu");
            int option = sc.nextInt();
            try {
                switch (option) {
                    case 1:
                        DistributorMenu.distributorMenu();
                        break;
                    case 2:
                        OrderMenu.orderMenu();
                        break;
                    case 3:
                        ReportMenu.reportMenu();
                        break;
                    case 4:
                        MainMenu.main(args);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


}