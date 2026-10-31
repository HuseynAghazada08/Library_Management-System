/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package largeassigment;

/**
 *
 * @author husey
 */




/**
 * Main simulation class for the library system.
 */
public class LargeAssigment {

    /**
     * Starts the library simulation.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Library library = new Library();

        library.read("items.txt", "members.txt");

        System.out.println("Library data loaded.");

        boolean borrowedBook = library.borrowItem(1, 1);
        System.out.println("Member 1 borrowed book 1: " + borrowedBook);

        boolean returnedBook = library.returnItem(1, 1, ReturnCondition.PRESERVED);
        System.out.println("Member 1 returned book 1 preserved: " + returnedBook);

        boolean borrowedThesis = library.borrowItem(2, 5);
        System.out.println("Member 2 borrowed thesis 5: " + borrowedThesis);

        boolean returnedThesis = library.returnItem(2, 5, ReturnCondition.DAMAGED);
        System.out.println("Member 2 returned thesis 5 damaged: " + returnedThesis);

        library.performRepairCheck();

        boolean accessedPaper = library.borrowItem(3, 6);
        System.out.println("Member 3 accessed research paper 6: " + accessedPaper);

        System.out.println("\nSearch by title Clean_Code:");
        for (Items item : library.searchByTitle("Clean_Code")) {
            System.out.println(item.getId() + " " + item.getTitle());
        }

        Items popularItem = library.getMostPopularItem();
        if (popularItem != null) {
            System.out.println("\nMost popular item: " + popularItem.getTitle());
        }

        Member activeMember = library.getMostActiveMember();
        if (activeMember != null) {
            System.out.println("Most active member ID: " + activeMember.getId());
        }
        
        library.createDigitalCopy(1);
        library.viewDigitalCopy(1);
        library.viewDigitalCopy(1);
        library.downloadDigitalCopy(1);

        Book digitalBook = library.getMostPopularDigitalItem();
        if (digitalBook != null) {
            System.out.println("Most popular digital book: " + digitalBook.getTitle());
        } else {
            System.out.println("No digital book found.");
        }

        System.out.println("\nSimulation finished.");
        
        


        Book mostViewed = library.getMostViewedDigitalItem();
        

        if (mostViewed != null) {
    
            System.out.println("Most viewed digital book: " + mostViewed.getTitle());

        }


        Book mostDownloaded = library.getMostPopularDigitalItem();

        if (mostDownloaded != null) {
    
            System.out.println("Most downloaded digital book: " + mostDownloaded.getTitle());

        }
    
    }
}