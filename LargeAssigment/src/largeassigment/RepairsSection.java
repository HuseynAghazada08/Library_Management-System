/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package largeassigment;

/**
 *
 * @author husey
 */


import java.util.Random;

/**
 * Handles repair operations for damaged items in the library.
 * Determines whether an item can be repaired or must be archived.
 */
public class RepairsSection {

    private int repairCount;
    private Random random;

    /**
     * Constructs a RepairsSection.
     */
    public RepairsSection() {
        this.repairCount = 0;
        this.random = new Random();
    }

    /**
     * Sends an item to the repair process and marks it as being repaired.
     *
     * @param item the item to be repaired
     */
    public void sendToRepair(Items item) {
        repairCount++;
        item.setStatus(ItemStatus.BEING_REPAIRED);
    }

    /**
     * Repairs the given item.
     * Randomly determines whether the damage is severe.
     *
     * @param item the item to repair
     * @return true if the item is archived due to severe damage,
     *         false if the item is successfully repaired
     */
    public boolean repairItem(Items item) {

        boolean severe = random.nextBoolean();

        if (severe) {
            item.setStatus(ItemStatus.ARCHIVED);
            return true;
        } else {
            item.setStatus(ItemStatus.AVAILABLE);
            return false;
        }
    }

    /**
     * Returns the number of items sent to repair.
     *
     * @return repair count
     */
    public int getRepairCount() {
        return repairCount;
    }
}
