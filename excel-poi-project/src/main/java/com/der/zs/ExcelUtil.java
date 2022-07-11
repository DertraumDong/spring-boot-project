package com.der.zs;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 名称：
 * 阐述：
 *
 * @author dertraum
 * @date 2020/10/31 15:26
 */
public class ExcelUtil {

    public static void main(String[] args) {
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中获取数据
     * @since 2020-10-31 19:53:00
     * @throws Exception exception
     */
    private static void loadData() throws Exception {
        File file  = new File("C:/Users/Administrator/Desktop/20210801_20210831.xlsx");
        if(!file.exists()){
            throw new Exception("file is not exists");
        }
        // 创建工作薄对象
        Workbook workbook;
        workbook = WorkbookFactory.create(file);
        // 获取Sheet
        Sheet sheet = workbook.getSheetAt(0);
        // 最后一行
        int count = sheet.getLastRowNum()-3;
        // 第一行
        int first = 8;
        List<ExcelVo> shouruVoList = new ArrayList<ExcelVo>();
        List<ExcelVo> zhichuVoList = new ArrayList<ExcelVo>();
        for (int i = first;i<=count;i++){
            Row row = sheet.getRow(i);
            // 交易日期
            String dateStr = row.getCell(0).getStringCellValue().replaceAll("\t","");
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            Date date1 = df.parse(dateStr);
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String date = df2.format(date1);
            // 交易时间
            String time = row.getCell(1).getStringCellValue().replaceAll("\t","");
            String beizhu = row.getCell(6).getStringCellValue().replaceAll("\t","");
            ExcelVo excelVo = new ExcelVo(date,time,beizhu);
            if(row.getCell(2)!=null){
                double shouru = row.getCell(2).getNumericCellValue();
                excelVo.setShouru(shouru);
                shouruVoList.add(excelVo);
            }
            if(row.getCell(3)!=null) {
                double zhichu = row.getCell(3).getNumericCellValue();
                excelVo.setZhichu(zhichu);
                zhichuVoList.add(excelVo);
            }
        }
        downloadExcel(zhichuVoList,shouruVoList);
    }

    private static void downloadExcel(List<ExcelVo> zhichuVoList,List<ExcelVo> shouruVoList) throws IOException {
        Workbook wb = new XSSFWorkbook();
        // 支出
        int zhichuSize = zhichuVoList.size();
        if(zhichuSize > 0){
            Sheet sheet1 = wb.createSheet("支出");
            sheet1.setColumnWidth(0,20*256);
            sheet1.setColumnWidth(3,40*256);
            for(int i = 0 ; i < zhichuSize ; i++){
                ExcelVo excelVo = zhichuVoList.get(i);
                setHeader(sheet1);
                Row row = sheet1.createRow(i+1);
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(excelVo.getDate() + " " +excelVo.getTime());
                /*Cell cell1 = row.createCell(1);
                cell1.setCellValue(excelVo.getShouru());*/
                Cell cell2 = row.createCell(2);
                cell2.setCellValue(excelVo.getZhichu());
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(excelVo.getBeizhu());
            }
        }
        // 收入
        int shouruSize = shouruVoList.size();
        if(shouruSize > 0){
            Sheet sheet2 = wb.createSheet("收入");
            sheet2.setColumnWidth(0,20*256);
            sheet2.setColumnWidth(3,40*256);
            for(int i = 0 ; i < shouruSize ; i++){
                ExcelVo excelVo = shouruVoList.get(i);
                setHeader(sheet2);
                Row row = sheet2.createRow(i+1);
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(excelVo.getDate() + " " + excelVo.getTime());
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(excelVo.getShouru());
                /*Cell cell2 = row.createCell(2);
                cell2.setCellValue(excelVo.getZhichu());*/
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(excelVo.getBeizhu());
            }
        }
        long str = System.currentTimeMillis();
        OutputStream fileOut = new FileOutputStream("C:/Users/Administrator/Desktop/testExcel/test1"+ str +".xlsx");
        wb.write(fileOut);
    }

    /**
     * 建立行首
     * @since 2020-10-31 19:52:00
     * @param sheet sheet
     */
    private static void setHeader(Sheet sheet){
        Row row = sheet.createRow(0);
        Cell cell0 = row.createCell(0);
        cell0.setCellValue("交易时间");
        Cell cell1 = row.createCell(1);
        cell1.setCellValue("收入金额");
        Cell cell2 = row.createCell(2);
        cell2.setCellValue("支出金额");
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("备注");
    }

    private static class ExcelVo{
        private String date;
        private String time;
        private double shouru;
        private double zhichu;
        private String beizhu;

        public ExcelVo(String date, String time, String beizhu) {
            this.date = date;
            this.time = time;
            this.beizhu = beizhu;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public double getShouru() {
            return shouru;
        }

        public void setShouru(double shouru) {
            this.shouru = shouru;
        }

        public double getZhichu() {
            return zhichu;
        }

        public void setZhichu(double zhichu) {
            this.zhichu = zhichu;
        }

        public String getBeizhu() {
            return beizhu;
        }

        public void setBeizhu(String beizhu) {
            this.beizhu = beizhu;
        }
    }

}
