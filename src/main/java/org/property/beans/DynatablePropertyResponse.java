package org.property.beans;

import java.util.List;

/**
 * Created by sowmyaparameshwara on 3/20/17.
 */
public class DynatablePropertyResponse {

    private List<DynatablePropertyRecords> records;

    private int queryRecordCount;

    private int totalRecordCount;

    public List<DynatablePropertyRecords> getRecords() {
        return records;
    }

    public void setRecords(List<DynatablePropertyRecords> records) {
        this.records = records;
    }


    public int getQueryRecordCount() {
        return queryRecordCount;
    }

    public void setQueryRecordCount(int queryRecordCount) {
        this.queryRecordCount = queryRecordCount;
    }

    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }



}
