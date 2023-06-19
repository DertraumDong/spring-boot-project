package com.der.easy.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.der.easy.dto.DataExcel;

/**
 * @author liudong
 * 2023/6/19 14:54
 * @version 1.0
 */
public class DataListener implements ReadListener<DataExcel> {

    /**
     * 这个每一条数据解析都会来调用
     * @param dataExcel
     * @param analysisContext
     */
    public void invoke(DataExcel dataExcel, AnalysisContext analysisContext) {
        System.out.println("");
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     */
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println();
    }
}
