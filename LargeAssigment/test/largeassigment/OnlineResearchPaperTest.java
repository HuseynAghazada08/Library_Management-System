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
 * Unit tests for the OnlineResearchPaper class.
 */
public class OnlineResearchPaperTest {

    private OnlineResearchPaper paper;

    /**
     * Creates a fresh online research paper before each test.
     */
    @Before
    public void setUp() {
        paper = new OnlineResearchPaper(6, "Deep_Learning_Survey",
                "Jane_Roe", 30, "Machine_Learning");
    }

    /**
     * Tests that online research papers are always available.
     */
    @Test
    public void testIsAvailable() {
        assertTrue("Online research paper should always be available",
                paper.isAvailable());
    }

    /**
     * Tests viewing an online research paper.
     */
    @Test
    public void testView() {
        paper.view();

        assertEquals("View count should be 1",
                1, paper.getViewCount());
    }

    /**
     * Tests downloading an online research paper.
     */
    @Test
    public void testDownload() {
        paper.download();

        assertEquals("Download count should be 1",
                1, paper.getDownloadCount());
        assertEquals("Borrow/usage count should be 1 after download",
                1, paper.getBorrowCount());
    }

    /**
     * Tests multiple views and downloads.
     */
    @Test
    public void testMultipleViewsAndDownloads() {
        paper.view();
        paper.view();
        paper.download();

        assertEquals("View count should be 2",
                2, paper.getViewCount());
        assertEquals("Download count should be 1",
                1, paper.getDownloadCount());
        assertEquals("Usage count should be 1",
                1, paper.getBorrowCount());
    }
}