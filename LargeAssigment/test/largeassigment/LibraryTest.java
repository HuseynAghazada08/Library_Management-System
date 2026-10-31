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
 * Unit tests for the Library class.
 * Tests integration of items, members, borrowing, returning and search.
 */
public class LibraryTest {

    private Library library;
    private Member member;
    private Book book;
    private Thesis thesis;
    private OnlineResearchPaper paper;

    /**
     * Initializes library with sample data before each test.
     */
    @Before
    public void setUp() {
        library = new Library();

        member = new Member(1, "Bence", "bence@elte.hu",
                MemberCatagory.STUDENT);

        book = new Book(1, "Clean_Code", "Robert_C_Martin",
                464, "Software_Engineering", 2);

        thesis = new Thesis("Prof_Smith", 2024, 1,
                5, "AI_Thesis", "John_Doe",
                120, "Artificial_Intelligence");

        paper = new OnlineResearchPaper(6, "Deep_Learning_Survey",
                "Jane_Roe", 30, "Machine_Learning");

        library.addMember(member);
        library.addItem(book);
        library.addItem(thesis);
        library.addItem(paper);
    }

    /**
     * Tests borrowing a book through the library.
     */
    @Test
    public void testBorrowBook() {
        boolean result = library.borrowItem(1, 1);

        assertTrue("Borrowing book should succeed", result);
        assertEquals("Borrowed copies should be 1",
                1, book.getBorrowedCopies());
    }

    /**
     * Tests returning a book in preserved condition.
     */
    @Test
    public void testReturnBookPreserved() {
        library.borrowItem(1, 1);

        boolean result = library.returnItem(1, 1, ReturnCondition.PRESERVED);

        assertTrue("Return should succeed", result);
        assertEquals("Book should be available",
                ItemStatus.AVAILABLE, book.getStatus());
    }

    /**
     * Tests returning a damaged thesis.
     */
    @Test
   
    public void testReturnThesisDamaged() {

    assertTrue(library.borrowItem(1, 5));

    boolean result = library.returnItem(1, 5, ReturnCondition.DAMAGED);

    assertTrue(result);

    Items item = library.searchByTitle("AI_Thesis").get(0);

    assertEquals(ItemStatus.BEING_REPAIRED, item.getStatus());
    }

    /**
     * Tests accessing an online research paper.
     */
    @Test
    public void testAccessResearchPaper() {
        boolean result = library.borrowItem(1, 6);

        assertTrue("Accessing research paper should succeed", result);
        assertEquals("View count should be 1",
                1, paper.getViewCount());
    }

    /**
     * Tests searching items by title.
     */
    @Test
    public void testSearchByTitle() {
        assertEquals("Search should find one item",
                1, library.searchByTitle("Clean_Code").size());
    }

    /**
     * Tests searching items by author.
     */
    @Test
    public void testSearchByAuthor() {
        assertEquals("Search should find one item",
                1, library.searchByAuthor("Robert_C_Martin").size());
    }

    /**
     * Tests digital copy creation and usage through library.
     */
    @Test
    public void testDigitalCopyUsage() {
        library.createDigitalCopy(1);
        library.viewDigitalCopy(1);
        library.downloadDigitalCopy(1);

        assertEquals("View count should be 1",
                1, book.getDigitalCopy().getViewCount());
        assertEquals("Download count should be 1",
                1, book.getDigitalCopy().getDownloadCount());
    }

    /**
     * Tests finding the most popular item.
     */
    @Test
    public void testMostPopularItem() {
        library.borrowItem(1, 1);
        library.borrowItem(1, 1);

        assertEquals("Most popular item should be the book",
                book, library.getMostPopularItem());
    }

    /**
     * Tests finding the most active member.
     */
    @Test
    public void testMostActiveMember() {
        library.borrowItem(1, 1);
        library.returnItem(1, 1, ReturnCondition.PRESERVED);

        assertEquals("Member should be most active",
                member, library.getMostActiveMember());
    }
}