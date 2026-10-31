/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package largeassigment;

/**
 *
 * @author husey
 */

/**
 * Represents a Thesis item in the library.
 * A thesis can be borrowed and returned similarly to books,
 * but it does not have a digital copy.
 */

public class Thesis extends Items {
    private String advisor;
    private int defenceYear;
    private int totalCopies;
    private int borrowedCopies;
    private ItemStatus itemstatus;
    
    /**
     * Constructs a Thesis object with the given attributes.
     *
     * @param advisor the advisor of the thesis
     * @param defenceYear the year the thesis was defended
     * @param totalCopies number of physical copies
     * @param id unique identifier
     * @param title title of the thesis
     * @param author author of the thesis
     * @param pageCount number of pages
     * @param topic topic of the thesis
     */
    public Thesis(String advisor, int defenceYear, int totalCopies, int id, String title, String author, int pageCount, String topic){
        super(id, title, author, pageCount, topic);
        this.advisor = advisor;
        this.defenceYear = defenceYear;
        this.borrowedCopies = 0;
        this.totalCopies = totalCopies;
        this.itemstatus = ItemStatus.AVAILABLE;
    }
    
     /**
     * Checks whether the thesis is available for borrowing.
     *
     * @return true if at least one copy is available and the thesis is not under repair or archived
     */
    @Override
    public boolean isAvailable() {
           return itemstatus != ItemStatus.NEEDS_REPAIR
            && itemstatus != ItemStatus.BEING_REPAIRED
            && itemstatus != ItemStatus.ARCHIVED
            && borrowedCopies < totalCopies;
      
    }
 /**
 * Borrows one physical copy of the thesis if available.
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
        itemstatus = ItemStatus.UNAVAILABLE;
    } else {
        itemstatus = ItemStatus.BORROWED;
    }

    return true;
}
/**
 * Returns one borrowed thesis copy.
 *
 * @param damaged true if the returned thesis is damaged, false otherwise
 */
public void returnItem(boolean damaged) {
    if (borrowedCopies > 0) {
        borrowedCopies--;
    }

    if (damaged) {
        itemstatus = ItemStatus.NEEDS_REPAIR;
    } else {
        itemstatus = ItemStatus.AVAILABLE;
    }
}
/**
 * Adds new physical copies of the thesis.
 *
 * @param amount number of copies to add
 */
public void addCopies(int amount) {
    if (amount > 0) {
        totalCopies += amount;

        if (itemstatus == ItemStatus.UNAVAILABLE && borrowedCopies < totalCopies) {
            itemstatus = ItemStatus.AVAILABLE;
        }
    }
}

@Override
public void setStatus(ItemStatus status) {
    this.itemstatus = status;
}

public String getAdvisor() {
    return advisor;
}

public void setAdvisor(String advisor) {
    this.advisor = advisor;
}

public int getDefenceYear() {
    return defenceYear;
}

public void setDefenceYear(int defenceYear) {
    this.defenceYear = defenceYear;
}

public int getTotalCopies() {
    return totalCopies;
}

public int getBorrowedCopies() {
    return borrowedCopies;
}

public ItemStatus getItemstatus() {
    return itemstatus;
}
@Override
public ItemStatus getStatus() {
    return itemstatus;
}
    
}
