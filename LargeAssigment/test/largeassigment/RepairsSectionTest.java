/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */


/**
 *
 * @author husey
 */
package largeassigment;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the RepairsSection class.
 */
public class RepairsSectionTest {

    private RepairsSection repairsSection;
    private Book book;

    /**
     * Initializes a RepairsSection and a damaged book before each test.
     */
    @Before
    public void setUp() {
        repairsSection = new RepairsSection();
        book = new Book(1, "Clean_Code", "Robert_C_Martin",
                464, "Software_Engineering", 1);
    }

    /**
     * Tests sending an item to repair.
     * Verifies that repair count increases and status changes.
     */
    @Test
    public void testSendToRepair() {
        repairsSection.sendToRepair(book);

        assertEquals("Repair count should be 1",
                1, repairsSection.getRepairCount());
        assertEquals("Book should be in BEING_REPAIRED state",
                ItemStatus.BEING_REPAIRED, book.getStatus());
    }

    /**
     * Tests repairing an item.
     * Verifies that the item ends up either AVAILABLE or ARCHIVED.
     */
    @Test
    public void testRepairItem() {
        repairsSection.sendToRepair(book);

        boolean archived = repairsSection.repairItem(book);

        if (archived) {
            assertEquals("Book should be archived",
                    ItemStatus.ARCHIVED, book.getStatus());
        } else {
            assertEquals("Book should be available after repair",
                    ItemStatus.AVAILABLE, book.getStatus());
        }
    }

    /**
     * Tests multiple repairs increase repair count correctly.
     */
    @Test
    public void testMultipleRepairsCount() {
        repairsSection.sendToRepair(book);
        repairsSection.sendToRepair(book);

        assertEquals("Repair count should be 2",
                2, repairsSection.getRepairCount());
    }
}