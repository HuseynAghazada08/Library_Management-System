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
 * Unit tests for the Thesis class.
 */
public class ThesisTest {

    private Thesis thesis;

    /**
     * Creates a fresh thesis before each test.
     */
    @Before
    public void setUp() {
        thesis = new Thesis("Prof_Smith", 2024, 1,
                5, "AI_Thesis", "John_Doe",
                120, "Artificial_Intelligence");
    }

    /**
     * Tests that a thesis is initially available.
     */
    @Test
    public void testIsAvailable() {
        assertTrue("Thesis should be available initially", thesis.isAvailable());
    }

    /**
     * Tests successful borrowing of a thesis.
     */
    @Test
    public void testBorrowItem() {
        boolean result = thesis.borrowItem();

        assertTrue("Borrowing thesis should succeed", result);
        assertEquals("Borrowed copies should be 1",
                1, thesis.getBorrowedCopies());
        assertEquals("Borrow count should be 1",
                1, thesis.getBorrowCount());
    }

    /**
     * Tests that borrowing fails when thesis is unavailable.
     */
    @Test
    public void testBorrowItemWhenUnavailable() {
        thesis.borrowItem();

        boolean result = thesis.borrowItem();

        assertFalse("Borrowing should fail when no thesis copies are available", result);
        assertEquals("Thesis should be unavailable",
                ItemStatus.UNAVAILABLE, thesis.getStatus());
    }

    /**
     * Tests returning a preserved thesis.
     */
    @Test
    public void testReturnItemPreserved() {
        thesis.borrowItem();
        thesis.returnItem(false);

        assertEquals("Borrowed copies should become 0",
                0, thesis.getBorrowedCopies());
        assertEquals("Thesis should become available",
                ItemStatus.AVAILABLE, thesis.getStatus());
    }

    /**
     * Tests returning a damaged thesis.
     */
    @Test
    public void testReturnItemDamaged() {
        thesis.borrowItem();
        thesis.returnItem(true);

        assertEquals("Damaged thesis should need repair",
                ItemStatus.NEEDS_REPAIR, thesis.getStatus());
    }

    /**
     * Tests adding copies to a thesis.
     */
    @Test
    public void testAddCopies() {
        thesis.addCopies(2);

        assertEquals("Total copies should become 3",
                3, thesis.getTotalCopies());
    }
}