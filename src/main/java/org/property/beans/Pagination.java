package org.property.beans;

/**
 * Created by sowmyaparameshwara on 3/21/17.
 */
public class Pagination {

    private int totalNumberOfItems;

    private int pageOffset;

    private int pageSize;

    public Pagination(int totalNumberOfItems, int pageOffset,int pageSize){
        this.totalNumberOfItems = totalNumberOfItems;
        this.pageOffset = pageOffset;
        this.pageSize = pageSize;
    }

    public int getTotalNumberOfItems() {
        return totalNumberOfItems;
    }

    public void setTotalNumberOfItems(int totalNumberOfItems) {
        this.totalNumberOfItems = totalNumberOfItems;
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
