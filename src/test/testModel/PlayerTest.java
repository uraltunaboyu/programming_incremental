package testModel;

import exceptions.NotEnoughMoney;
import exceptions.PurchaseFailed;
import exceptions.UpgradeAlreadyExists;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    Player testPlayer;
    Item cheapItem;
    Item expensiveItem;
    Upgrade cheapUpgrade;
    Upgrade expensiveUpgrade;
    int initialMoney = 200;
    List<Upgrade> upgrades = new ArrayList<>();

    @BeforeEach
    void setup(){
        testPlayer = new Player(initialMoney);
        cheapItem = new Item("Cheap Item", 10, 1, upgrades);
        expensiveItem = new Item("Expensive Item", 100, 1, upgrades);
        cheapUpgrade = new Upgrade("Cheap Upgrade", 10, 1.1);
        expensiveUpgrade = new Upgrade("Expensive Upgrade", 100, 1.1);

    }

    @Test
    public void purchaseTest() throws PurchaseFailed {
        testPlayer.purchase(cheapItem);
        testPlayer.purchase(cheapItem, cheapUpgrade);
        try {
            testPlayer.purchase(expensiveItem);
            testPlayer.purchase(expensiveItem);
            fail();
        } catch (NotEnoughMoney ignored){

        }


        assertTrue(testPlayer.itemsContain(cheapItem));
        assertTrue(testPlayer.upgradesContain(cheapUpgrade));
        assertFalse(testPlayer.upgradesContain(expensiveUpgrade));
    }

    @Test
    public void prestigePlayer() throws PurchaseFailed {
        testPlayer.setMoney(150);
        testPlayer.setPrestigeToBeGained(20);
        addItemToPlayer(cheapItem, 6);
        testPlayer.purchase(cheapItem, cheapUpgrade);

        assertTrue(testPlayer.itemsContain(cheapItem));
        assertEquals(6, testPlayer.getItemNumber(cheapItem));
        assertTrue(testPlayer.upgradesContain(cheapUpgrade));
        assertEquals(0, testPlayer.getPrestige());

        testPlayer.prestigePlayer();

        assertEquals(1, testPlayer.getMoney());
        assertTrue(testPlayer.getItems().isEmpty());
        assertTrue(testPlayer.getUpgrades().isEmpty());
        assertEquals(testPlayer.getPrestigeToBeGained(), 0);
        assertEquals(testPlayer.getPrestige(), 20);
    }

    @Test
    public void playerNameSetTest() {
        testPlayer.setName("Test");
        testPlayer.setCompanyName("TestCompany");
        assertEquals("Test", testPlayer.getName());
        assertEquals("TestCompany", testPlayer.getCompanyName());
    }

    public void addItemToPlayer(Item i, int purchaseAmount) {
        Map<Item, Integer> items = testPlayer.getItemMap();
        if (items.containsKey(i)) {
            items.replace(i, items.get(i) + purchaseAmount);
        } else {
            items.put(i, purchaseAmount);
        }
    }



}
