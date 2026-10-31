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
 * Unit tests for the Member class.
 */
public class MemberTest {

    private Member member;
    private Book book1, book2, book3, book4;

    /**
     * Initializes a member and some books before each test.
     */
    @Before
    public void setUp() {
        member = new Member(1, "Bence", "bence@elte.hu",
                MemberCatagory.STUDENT);

        book1 = new Book(1, "A", "X", 100, "T", 1);
        book2 = new Book(2, "B", "X", 100, "T", 1);
        book3 = new Book(3, "C", "X", 100, "T", 1);
        book4 = new Book(4, "D", "X", 100, "T", 1);
    }

    /**
     * Tests that a member can borrow up to the initial limit (3 items).
     */
    @Test
    public void testBorrowLimit() {
        assertTrue(member.borrowItem(book1));
        assertTrue(member.borrowItem(book2));
        assertTrue(member.borrowItem(book3));

        // 4th should fail
        assertFalse("Member should not exceed borrow limit",
                member.borrowItem(book4));
    }

    /**
     * Tests returning items in preserved condition.
     * Verifies that successful return count increases.
     */
    @Test
    public void testReturnItemPreserved() {
        member.borrowItem(book1);

        boolean result = member.returnItem(book1, ReturnCondition.PRESERVED);

        assertTrue("Return should succeed", result);
        assertEquals("Borrowed items should be 0",
                0, member.getBorrowedItemCount());
        assertEquals("Successful returns should be 1",
                1, member.getSuccessfulReturns());
    }

    /**
     * Tests that damaged return does not increase preserved count.
     */
    @Test
    public void testReturnItemDamaged() {
        member.borrowItem(book1);

        member.returnItem(book1, ReturnCondition.DAMAGED);

        assertEquals("Successful returns should not increase for damaged items",
                0, member.getSuccessfulReturns());
    }

    /**
     * Tests that after 3 preserved returns, borrow limit increases.
     */
    @Test
    public void testBorrowLimitIncreaseAfterThreePreservedReturns() {

        // Borrow 3 books
        member.borrowItem(book1);
        member.borrowItem(book2);
        member.borrowItem(book3);

        // Return all preserved
        member.returnItem(book1, ReturnCondition.PRESERVED);
        member.returnItem(book2, ReturnCondition.PRESERVED);
        member.returnItem(book3, ReturnCondition.PRESERVED);

        // Now limit should increase → can borrow 4th
        assertTrue("Borrow limit should increase after 3 preserved returns",
                member.borrowItem(book4));
    }

    /**
     * Tests that member cannot return an item not borrowed.
     */
    @Test
    public void testReturnItemNotBorrowed() {
        boolean result = member.returnItem(book1, ReturnCondition.PRESERVED);

        assertFalse("Return should fail if item was not borrowed", result);
    }
}