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
 * Represents an online research paper.
 * It is always available and tracks views and downloads.
 */

public class OnlineResearchPaper extends Items {
    private int viewCount;
    private int downloadCount;
    
     /**
     * Constructs an OnlineResearchPaper object.
     *
     * @param id unique identifier
     * @param title title of the paper
     * @param author author of the paper
     * @param pageCount number of pages
     * @param topic topic of the paper
     */
    public OnlineResearchPaper(int id, String title, String author, int pageCount, String topic){
          super(id, title, author, pageCount, topic);
          this.downloadCount = 0;
          this.viewCount = 0;
    }

     /**
     * Online research papers are always available.
     *
     * @return true always
     */
    @Override
    public boolean isAvailable() {
        return true;
    }
    
    /**
     * Records a view of the research paper.
     */
    public void view(){
        this.viewCount++;
    }
    
     /**
     * Records a download of the research paper
     * and increments the usage count.
     */
    public void download(){
        this.downloadCount++;
        incrementBorrowCount();
    }
    public int getViewCount(){
        return this.viewCount;
    }
    public int getDownloadCount(){
        return this.downloadCount;
    }

    @Override
    public void setStatus(ItemStatus status) {
        
    }
    @Override
    public ItemStatus getStatus() {
    return ItemStatus.AVAILABLE; 
    }
}
