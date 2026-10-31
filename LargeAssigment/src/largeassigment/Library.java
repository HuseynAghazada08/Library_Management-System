/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package largeassigment;

/**
 *
 * @author husey
 */



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the library system.
 * Manages items, members, borrowing, returning, searching, repairs, and file reading.
 */
public class Library {

    private ArrayList<Items> items;
    private ArrayList<Member> members;
    private ArrayList<Items> archivedItems;
    private RepairsSection repairsSection;

    /**
     * Constructs a Library object.
     */
    public Library() {
        items = new ArrayList<>();
        members = new ArrayList<>();
        archivedItems = new ArrayList<>();
        repairsSection = new RepairsSection();
    }

    /**
     * Reads items and members from files.
     *
     * @param itemsFile path to items file
     * @param membersFile path to members file
     */
    public void read(String itemsFile, String membersFile) {
        readItems(itemsFile);
        readMembers(membersFile);
    }

    /**
     * Reads items from the given file and adds them to the library.
     *
     * @param fileName path to items file
     */
    private void readItems(String fileName) {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)))) {

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String title = sc.next();
                String author = sc.next();
                int pageCount = sc.nextInt();
                String topic = sc.next();
                String type = sc.next();
                int copies = sc.nextInt();

                if (type.equalsIgnoreCase("Book")) {
                    addItem(new Book(id, title, author, pageCount, topic, copies));
                } else if (type.equalsIgnoreCase("Thesis")) {
                    addItem(new Thesis("Unknown", 2024, copies, id, title, author, pageCount, topic));
                } else if (type.equalsIgnoreCase("ResearchPaper")) {
                    addItem(new OnlineResearchPaper(id, title, author, pageCount, topic));
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Items file error: " + e.getMessage());
        }
    }

    /**
     * Reads members from the given file and adds them to the library.
     *
     * @param fileName path to members file
     */
    private void readMembers(String fileName) {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)))) {

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String name = sc.next();
                String email = sc.next();
                String cat = sc.next();

                MemberCatagory category;

                if (cat.equalsIgnoreCase("Student")) {
                    category = MemberCatagory.STUDENT;
                } else if (cat.equalsIgnoreCase("Faculty")) {
                    category = MemberCatagory.FACULTY;
                } else {
                    category = MemberCatagory.ALUMNI;
                }

                addMember(new Member(id, name, email, category));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Members file error: " + e.getMessage());
        }
    }

    /**
     * Adds an item to the library collection.
     *
     * @param item the item to add
     */
    public void addItem(Items item) {
        items.add(item);
    }

    /**
     * Adds a member to the library.
     *
     * @param member the member to add
     */
    public void addMember(Member member) {
        members.add(member);
    }

    /**
     * Finds a member by id.
     *
     * @param id member id
     * @return matching member, or null if not found
     */
    private Member findMember(int id) {
        for (Member m : members) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    /**
     * Finds an item by id.
     *
     * @param id item id
     * @return matching item, or null if not found
     */
    private Items findItem(int id) {
        for (Items i : items) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    /**
     * Borrows or accesses an item for a member.
     *
     * @param memberId id of the member
     * @param itemId id of the item
     * @return true if operation succeeds, false otherwise
     */
    public boolean borrowItem(int memberId, int itemId) {
        Member member = findMember(memberId);
        Items item = findItem(itemId);

        if (member == null || item == null) {
            return false;
        }

        if (!item.isAvailable()) {
            return false;
        }

        if (item instanceof OnlineResearchPaper) {
            ((OnlineResearchPaper) item).view();
            return true;
        }

        if (!member.canBorrow()) {
            return false;
        }

        boolean success = false;

        if (item instanceof Book) {
            success = ((Book) item).borrowItem();
        } else if (item instanceof Thesis) {
            success = ((Thesis) item).borrowItem();
        }

        if (!success) {
            return false;
        }

        return member.borrowItem(item);
    }

    /**
     * Returns a borrowed item.
     *
     * @param memberId id of the member
     * @param itemId id of the item
     * @param condition return condition
     * @return true if return succeeds, false otherwise
     */
    public boolean returnItem(int memberId, int itemId, ReturnCondition condition) {
        Member member = findMember(memberId);
        Items item = findItem(itemId);

        if (member == null || item == null) {
            return false;
        }

        if (item instanceof OnlineResearchPaper) {
            return false;
        }

        if (!member.returnItem(item, condition)) {
            return false;
        }

        if (item instanceof Book) {
            ((Book) item).returnItem(condition == ReturnCondition.DAMAGED);
        } else if (item instanceof Thesis) {
            ((Thesis) item).returnItem(condition == ReturnCondition.DAMAGED);
        }

        if (condition == ReturnCondition.DAMAGED) {
            repairsSection.sendToRepair(item);
        }

        return true;
    }

    /**
     * Searches items by title.
     *
     * @param title title to search
     * @return list of matching items
     */
    public ArrayList<Items> searchByTitle(String title) {
        ArrayList<Items> result = new ArrayList<>();

        for (Items i : items) {
            if (i.getTitle().equalsIgnoreCase(title)) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * Searches items by author.
     *
     * @param author author to search
     * @return list of matching items
     */
    public ArrayList<Items> searchByAuthor(String author) {
        ArrayList<Items> result = new ArrayList<>();

        for (Items i : items) {
            if (i.getAuthor().equalsIgnoreCase(author)) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * Performs repair check and processes damaged items.
     * Severely damaged items are moved to the archive.
     */
    public void performRepairCheck() {
    ArrayList<Items> toArchive = new ArrayList<>();

    for (Items i : items) {
        if (i.getStatus() == ItemStatus.NEEDS_REPAIR) {
            repairsSection.sendToRepair(i);
        }

        if (i.getStatus() == ItemStatus.BEING_REPAIRED) {
            boolean archived = repairsSection.repairItem(i);

            if (archived) {
                toArchive.add(i);
            }
        }
    }

    for (Items i : toArchive) {
        items.remove(i);
        archivedItems.add(i);
    }
}

    

    /**
     * Finds the most popular item based on borrow count.
     *
     * @return the most borrowed item, or null if there are no items
     */
    public Items getMostPopularItem() {
        if (items.isEmpty()) {
            return null;
        }

        Items best = items.get(0);

        for (Items i : items) {
            if (i.getBorrowCount() > best.getBorrowCount()) {
                best = i;
            }
        }

        return best;
    }
    /**
 * Creates a digital copy for a book.
 *
 * @param itemId id of the book
 * @return true if digital copy was created or already exists, false otherwise
 */
public boolean createDigitalCopy(int itemId) {
    Items item = findItem(itemId);

    if (item instanceof Book) {
        Book book = (Book) item;
        book.createDigitalCopy();
        return true;
    }

    return false;
}

/**
 * Records one view for a book's digital copy.
 *
 * @param itemId id of the book
 * @return true if view was recorded, false otherwise
 */
public boolean viewDigitalCopy(int itemId) {
    Items item = findItem(itemId);

    if (item instanceof Book) {
        Book book = (Book) item;

        if (book.hasDigitalCopy()) {
            book.viewDigitalCopy();
            return true;
        }
    }

    return false;
}

/**
 * Records one download for a book's digital copy.
 *
 * @param itemId id of the book
 * @return true if download was recorded, false otherwise
 */
public boolean downloadDigitalCopy(int itemId) {
    Items item = findItem(itemId);

    if (item instanceof Book) {
        Book book = (Book) item;

        if (book.hasDigitalCopy()) {
            book.downloadDigitalCopy();
            return true;
        }
    }

    return false;
}

/**
 * Finds the most viewed digital book.
 *
 * @return the most viewed digital book, or null if no digital copy exists
 */
public Book getMostViewedDigitalItem() {
    Book best = null;
    int maxViews = -1;

    for (Items item : items) {
        if (item instanceof Book) {
            Book book = (Book) item;

            if (book.hasDigitalCopy()) {
                int views = book.getDigitalCopy().getViewCount();

                if (views > maxViews) {
                    maxViews = views;
                    best = book;
                }
            }
        }
    }

    return best;
}

    /**
     * Finds the most active member based on successful returns.
     *
     * @return the most active member, or null if there are no members
     */
    public Member getMostActiveMember() {
        if (members.isEmpty()) {
            return null;
        }

        Member best = members.get(0);

        for (Member m : members) {
            if (m.getSuccessfulReturns() > best.getSuccessfulReturns()) {
                best = m;
            }
        }

        return best;
    }

    /**
     * Finds the most popular digital book based on download count.
     *
     * @return the most downloaded digital book, or null if no digital book exists
     */
    public Book getMostPopularDigitalItem() {
        Book best = null;
        int maxDownloads = -1;

        for (Items i : items) {
            if (i instanceof Book b) {

                if (b.hasDigitalCopy()) {
                    int downloads = b.getDigitalCopy().getDownloadCount();

                    if (downloads > maxDownloads) {
                        maxDownloads = downloads;
                        best = b;
                    }
                }
            }
        }

        return best;
    }
}





