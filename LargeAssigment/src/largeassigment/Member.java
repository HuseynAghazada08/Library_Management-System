/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package largeassigment;

import java.util.ArrayList;

/**
 *
 * @author husey
 */

/**
 * Represents a library member who can borrow and return items.
 */
public class Member {
    private int id;
    private String name;
    private String email;
    private MemberCatagory category;
    private ArrayList <Items> borrowedItems = new ArrayList<>();
    private int borrowLimit;
    private int successfulReturns;
    private int preservedReturns;
    
    /**
     * Constructs a Member object.
     *
     * @param id unique identifier of the member
     * @param name name of the member
     * @param email email of the member
     * @param category category of the member
     */
    public Member(int id, String name, String email, MemberCatagory category){
        this.id = id;
        this.name = name;
        this.email = email;
        this.category = category;
        this.borrowedItems = new ArrayList<>();
        this.borrowLimit = 3;
        this.successfulReturns = 0;
        this.preservedReturns = 0;
        
    }
    
    /**
     * Checks whether the member can borrow more items.
     *
     * @return true if current borrowed items are less than limit, false otherwise
     */
    public boolean canBorrow(){
        return borrowedItems.size() < borrowLimit;
    }
    
      /**
     * Adds an item to the member's borrowed list if possible.
     *
     * @param item the item to borrow
     * @return true if borrowing succeeded, false otherwise
     */
    public boolean borrowItem(Items item){
        if (canBorrow() == false){
            return false;
        }
        borrowedItems.add(item);
        return true;
        
    }
    
     /**
     * Returns an item and updates return statistics.
     *
     * @param item the item being returned
     * @param condition the condition of the returned item
     * @return true if the item was successfully returned, false otherwise
     */
    public boolean returnItem(Items item, ReturnCondition condition){
        if(borrowedItems.contains(item)){
            borrowedItems.remove(item);
        
        if(condition == ReturnCondition.PRESERVED){
            successfulReturns++;
            preservedReturns++;
            increaseBorrowLimitIfEligible();
        }
        return true;
        }
        return false;
    }
    
     /**
     * Increases borrow limit after every 3 preserved returns.
     */
    public void increaseBorrowLimitIfEligible(){
        if(preservedReturns == 3){
            borrowLimit++;
            preservedReturns = 0;
        }
    }
    
    
    /**
     * Returns the number of currently borrowed items.
     *
     * @return count of borrowed items
     */
    public int getBorrowedItemCount(){
        return borrowedItems.size();
    }
    
    
    public int getId() {
    return id;
    }
    
    public int getSuccessfulReturns() {
    return successfulReturns;
    }
    

    
}
