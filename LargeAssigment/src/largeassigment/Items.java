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
 * Abstract base class representing a general item in the library.
 */
public abstract class Items{

    private int id;
    private String title;
    private String author;
    private int pageCount;
    private String topic;
    private int borrowCount;

    /**
     * Constructs an Item with given attributes.
     *
     * @param id unique identifier of the item
     * @param title title of the item
     * @param author author of the item
     * @param pageCount number of pages
     * @param topic topic/category of the item
     */
    public Items(int id, String title, String author, int pageCount, String topic) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
        this.topic = topic;
        this.borrowCount = 0;
    }

    /**
     * Checks whether the item is currently available.
     * Must be implemented by subclasses.
     *
     * @return true if available, false otherwise
     */
    public abstract boolean isAvailable();
    
    
    
    /**
     * 
     * @param status 
     * sets the status of item
     */
    
    
    public abstract void setStatus(ItemStatus status);
    
   
    public abstract ItemStatus getStatus();

    /**
     * Increments how many times the item has been borrowed.
     */
    public void incrementBorrowCount() {
        borrowCount++;
    }

    /**
     * Returns total borrow count of the item.
     *
     * @return borrow count
     */
    public int getBorrowCount() {
        return borrowCount;
    }

    // ===== Getters & Setters =====

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
