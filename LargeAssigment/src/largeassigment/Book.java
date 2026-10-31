/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package largeassigment;

/**
 *
 * @author husey
 */
public class Book extends Items {
    private int totalCopies;
    private int borrowedCopies;
    private ItemStatus itemStatus;
    private DigitalCopy digitalCopy;
    

    public Book(int id, String title, String author, int pageCount, String topic, int totalCopies) {
        super(id, title, author, pageCount, topic);
        this.totalCopies = totalCopies;
        this.borrowedCopies = 0;
        this.itemStatus = ItemStatus.AVAILABLE;
        this.digitalCopy = null;
    }

    

    
    /**
     * Checks whether the book is available for borrowing.
     *
     * @return true if at least one copy is available and item is not under repair or archived
     */
    @Override
    public boolean isAvailable() {
        return itemStatus != ItemStatus.NEEDS_REPAIR
                && itemStatus != ItemStatus.BEING_REPAIRED
                && itemStatus != ItemStatus.ARCHIVED
                && borrowedCopies < totalCopies;
    }

    /**
     * Borrows one physical copy of the book if available.
     *
     * @return true if borrowing was successful, false otherwise
     */
    public boolean borrowItem() {
        if (!isAvailable()) {
            return false;
        }

        borrowedCopies++;
        incrementBorrowCount();

        if (borrowedCopies == totalCopies) {
            itemStatus = ItemStatus.UNAVAILABLE;
        } else {
            itemStatus = ItemStatus.BORROWED;
        }

        return true;
    }

    /**
     * Returns one borrowed copy of the book.
     *
     * @param damaged true if the returned book is damaged, false if preserved
     */
    public void returnItem(boolean damaged) {
        if (borrowedCopies > 0) {
            borrowedCopies--;
        }

        if (damaged) {
            itemStatus = ItemStatus.NEEDS_REPAIR;
        } else {
            itemStatus = ItemStatus.AVAILABLE;
        }
    }

    /**
     * Adds new physical copies to the book.
     *
     * @param amount number of copies to add
     */
    public void addCopies(int amount) {
        if (amount > 0) {
            totalCopies += amount;

            if (itemStatus == ItemStatus.UNAVAILABLE && borrowedCopies < totalCopies) {
                itemStatus = ItemStatus.AVAILABLE;
            }
        }
    }

    /**
     * Creates a digital copy for this book.
     */
    public void createDigitalCopy() {
        if (digitalCopy == null) {
            digitalCopy = new DigitalCopy(getId());
        }
    }

    /**
     * Checks whether this book has a digital copy.
     *
     * @return true if digital copy exists, false otherwise
     */
    public boolean hasDigitalCopy() {
        return digitalCopy != null;
    }

    /**
     * Records one view for the digital copy.
     */
    public void viewDigitalCopy() {
        if (digitalCopy != null) {
            digitalCopy.view();
        }
    }

    /**
     * Records one download for the digital copy.
     */
    public void downloadDigitalCopy() {
        if (digitalCopy != null) {
            digitalCopy.download();
        }
    }

    // ===== Getters & Setters =====

    public int getCopies() {
        return totalCopies;
    }

    public void setCopies(int copies) {
        this.totalCopies = copies;
    }

    public int getBorrowedCopies() {
        return borrowedCopies;
    }

    public void setBorrowedCopies(int borrowedCopies) {
        this.borrowedCopies = borrowedCopies;
    }

    @Override
    public ItemStatus getStatus() {
        return itemStatus;
    }

    @Override
    public void setStatus(ItemStatus status) {
        this.itemStatus = status;
    }
    
    

    public DigitalCopy getDigitalCopy() {
        return digitalCopy;
    }

    public void setDigitalCopy(DigitalCopy digitalCopy) {
        this.digitalCopy = digitalCopy;
    }
}
    
    
    

