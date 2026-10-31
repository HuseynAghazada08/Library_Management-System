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
 * Represents a digital copy of a book.
 * Tracks views and downloads.
 */
public class DigitalCopy {

    private final int itemId;
    private int viewCount;
    private int downloadCount;

    /**
     * Constructs a DigitalCopy linked to an item.
     *
     * @param itemId id of the item
     */
    public DigitalCopy(int itemId) {
        this.itemId = itemId;
        this.viewCount = 0;
        this.downloadCount = 0;
    }

    /**
     * Records a view of the digital copy.
     */
    public void view() {
        viewCount++;
    }

    /**
     * Records a download of the digital copy.
     */
    public void download() {
        downloadCount++;
    }

    /**
     * Returns the number of views.
     *
     * @return view count
     */
    public int getViewCount() {
        return viewCount;
    }

    /**
     * Returns the number of downloads.
     *
     * @return download count
     */
    public int getDownloadCount() {
        return downloadCount;
    }

    /**
     * Returns the associated item id.
     *
     * @return item id
     */
    public int getItemId() {
        return itemId;
    }
}
