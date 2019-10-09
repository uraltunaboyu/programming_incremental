package model;


import exceptions.UpgradeAlreadyExists;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {
    private String name;
    private String companyName;
    private double money;
    private int prestigeToBeGained;
    private int prestige;
    private List<Upgrade> upgrades = new ArrayList<>();
    private HashMap<Item, Integer> items = new HashMap<>();


    //EFFECTS: creates a player
    public Player(int money) {
        this.money = money;
    }

    //getters

    //EFFECTS: returns name
    public String getName() {
        return name;
    }

    //EFFECTS: returns money
    public double getMoney() {
        return money;
    }


    //EFFECTS: return company name
    public String getCompanyName() {
        return companyName;
    }


    //EFFECTS: returns prestige level
    public int getPrestige() {
        return prestige;
    }


    //EFFECTS: returns potential prestige
    public int getPrestigeToBeGained() {
        return prestigeToBeGained;
    }


    //EFFECTS: returns the list of items the player has
    public Set<Item> getItems() {
        return items.keySet();
    }

    //EFFECTS: returns the map of player items
    public Map<Item, Integer> getItemMap() {
        return items;
    }


    //EFFECTS: returns the list of upgrades the player has
    public List<Upgrade> getUpgrades() {
        return upgrades;
    }

    //setters

    //EFFECTS: sets name
    public void setName(String name) {
        this.name = name;
    }


    //EFFECTS: sets money
    public void setMoney(double money) {
        this.money = money;
    }

    //EFFECTS: add prestige points
    public void setPrestigeToBeGained(int prestigeToBeGained) {
        this.prestigeToBeGained = prestigeToBeGained;
    }


    //EFFECTS: sets the company name
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    //helpers

    //EFFECTS: returns true if a player has an item, false otherwise
    public Boolean itemsContain(Item item) {
        String name = item.getName();
        for (Item i: items.keySet()) {
            if (i.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns the number of an item a player has
    public int getItemNumber(Item item) {
        String name = item.getName();
        for (Item i: items.keySet()) {
            if (i.getName().equals(name)) {
                return items.get(i);
            }
        }
        return 0;
    }

    //EFFECTS: rounds the player's money
    public void roundMoney() {
        money = Math.round(money * 100d) / 100d;
    }

    //EFFECTS: returns true if player has the upgrade, false otherwise
    public boolean upgradesContain(Upgrade u) {
        String name = u.getName();
        for (Upgrade upgrade : upgrades) {
            if (upgrade.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES : this
    //EFFECTS: reset money, items, and upgrades, but add accumulated prestige points
    public void prestigePlayer() {
        money = 1;
        prestige = +prestigeToBeGained;
        upgrades.clear();
        items.clear();
        prestigeToBeGained = 0;
    }


    //MODIFIES : this
    //EFFECTS : adds the upgrade to the player if the player has enough money
    public void purchase(Item i, Upgrade u) throws UpgradeAlreadyExists {
        if (money >= u.getCost()) {
            if (i.purchasedUpgrades.contains(u)) {
                throw new UpgradeAlreadyExists();
            }
            i.purchasedUpgrades.add(u);
            i.addUpgrade(u);
            upgrades.add(u);
            money -= u.getCost();
            roundMoney();
            System.out.println(String.format("You have purchased the upgrade %s! You have %s dollars left.",
                    u.getName(), money));
        } else {
            System.out.println(String.format("You need to have %s dollars, but you have %s.",
                    u.getCost(), money));
        }
    }

    //MODIFIES : this
    //EFFECTS : adds the item to the player if the player has enough money
    public void purchase(Item i, int purchaseAmount) {
        if (money >= i.getCost() * purchaseAmount) {
            if (items.containsKey(i)) {
                items.replace(i, items.get(i) + purchaseAmount);
                System.out.println(String.format("You have purchased %s more of the item %s! You have %s dollars left",
                        purchaseAmount, i.getName(), money));
            } else {
                items.put(i, purchaseAmount);
                System.out.println(String.format("You have purchased %s of the item %s! You have %s dollars left",
                        purchaseAmount, i.getName(), money - i.getCost() * purchaseAmount));
            }
            money -= i.getCost() * purchaseAmount;
            roundMoney();
        } else {
            System.out.println(String.format("You need to have %s dollars, but you have %s.",
                    i.getCost() * purchaseAmount, money));
        }
    }
}
