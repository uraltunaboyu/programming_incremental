package ui;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();

        System.out.println("Please enter your name:");

        Scanner name = new Scanner(System.in);

        player.setName(name.next());

        System.out.println("Hello, " + player.name + " your account has " + player.money + " to start with.");

        Item basicClicker = new Item("Basic Clicker", 10, 0.1);

        Upgrade firstClick = new Upgrade("First Clicker", 10, 1.2, basicClicker);
        Upgrade upgradedClicker = new Upgrade("Upgraded Clicker", 50, 1.5, basicClicker);

        player.purchaseUpgrade(firstClick);
        player.purchaseUpgrade(upgradedClicker);

        player.showUpgrades();
    }
}