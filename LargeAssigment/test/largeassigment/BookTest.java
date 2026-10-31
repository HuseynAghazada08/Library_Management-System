/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package largeassigment;

/**
 *
 * @author husey
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Book class.
 */
public class BookTest {

    private Book book;

    /**
     * Creates a fresh book before each test.
     */
    @Before
    public void setUp() {
        book = new Book(1, "Clean_Code", "Robert_C_Martin",
                464, "Software_Engineering", 2);
    }

    /**
     * Tests that a new book with copies is available.
     */
    @Test
    public void testIsAvailable() {
        assertTrue("Book should be available initially", book.isAvailable());
    }

    /**
     * Tests successful borrowing of a book.
     */
    @Test
    public void testBorrowItem() {
        boolean result = book.borrowItem();

        assertTrue("Borrowing should succeed", result);
        assertEquals("Borrowed copies should be 1", 1, book.getBorrowedCopies());
        assertEquals("Borrow count should be 1", 1, book.getBorrowCount());
    }

    /**
     * Tests that borrowing fails when all copies are borrowed.
     */
    @Test
    public void testBorrowItemWhenUnavailable() {
        book.borrowItem();
        book.borrowItem();

        boolean result = book.borrowItem();

        assertFalse("Borrowing should fail when no copies are available", result);
        assertEquals("Book should be unavailable",
                ItemStatus.UNAVAILABLE, book.getStatus());
    }

    /**
     * Tests returning a preserved book.
     */
    @Test
    public void testReturnItemPreserved() {
        book.borrowItem();
        book.returnItem(false);

        assertEquals("Borrowed copies should return to 0",
                0, book.getBorrowedCopies());
        assertEquals("Book should become available",
                ItemStatus.AVAILABLE, book.getStatus());
    }

    /**
     * Tests returning a damaged book.
     */
    @Test
    public void testReturnItemDamaged() {
        book.borrowItem();
        book.returnItem(true);

        assertEquals("Damaged book should need repair",
                ItemStatus.NEEDS_REPAIR, book.getStatus());
    }

    /**
     * Tests adding copies to the book.
     */
    @Test
    public void testAddCopies() {
        book.addCopies(3);

        assertEquals("Copies should increase to 5", 5, book.getCopies());
    }

    /**
     * Tests creating a digital copy.
     */
    @Test
    public void testCreateDigitalCopy() {
        assertFalse("Book should not have digital copy initially",
                book.hasDigitalCopy());

        book.createDigitalCopy();

        assertTrue("Book should have digital copy after creation",
                book.hasDigitalCopy());
        assertEquals("Digital copy id should match book id",
                book.getId(), book.getDigitalCopy().getItemId());
    }

    /**
     * Tests checking whether a digital copy exists.
     */
    @Test
    public void testHasDigitalCopy() {
        assertFalse("Digital copy should not exist initially", book.hasDigitalCopy());

        book.createDigitalCopy();

        assertTrue("Digital copy should exist after creation", book.hasDigitalCopy());
    }

    /**
     * Tests viewing a digital copy.
     */
    @Test
    public void testViewDigitalCopy() {
        book.createDigitalCopy();

        book.viewDigitalCopy();

        assertEquals("View count should be 1",
                1, book.getDigitalCopy().getViewCount());
    }

    /**
     * Tests downloading a digital copy.
     */
    @Test
    public void testDownloadDigitalCopy() {
        book.createDigitalCopy();

        book.downloadDigitalCopy();

        assertEquals("Download count should be 1",
                1, book.getDigitalCopy().getDownloadCount());
    }
}