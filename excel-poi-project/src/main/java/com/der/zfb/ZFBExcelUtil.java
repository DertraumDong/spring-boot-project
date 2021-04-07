package com.der.zfb;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 支付宝文件转换
 */
public class ZFBExcelUtil {

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
        File shourufile  = new File("E:\\OneDrive\\Documents\\个人\\随手记文件夹\\支付宝\\20200501_20210331_shouru.xlsx");
        if(!shourufile.exists()){
            throw new Exception("file is not exists");
        }
        List<ZFBExcelVo> shouruVoList = buildList(shourufile);

        File zhichufile  = new File("E:\\OneDrive\\Documents\\个人\\随手记文件夹\\支付宝\\20200501_20210331_zhichu.xlsx");
        if(!zhichufile.exists()){
            throw new Exception("file is not exists");
        }
        List<ZFBExcelVo> zhichuVoList = buildList(zhichufile);

        downloadExcel(zhichuVoList,shouruVoList);
    }

    private static List<ZFBExcelVo> buildList(File file) throws IOException, InvalidFormatException, ParseException {
        // 创建工作薄对象
        Workbook workbook;
        workbook = WorkbookFactory.create(file);
        // 获取Sheet
        Sheet sheet = workbook.getSheetAt(0);
        // 最后一行
        int count = sheet.getLastRowNum()-7;
        // 第一行
        int first = 5;

        List<ZFBExcelVo> voList = new ArrayList<ZFBExcelVo>();
        for (int i = first;i<=count;i++){
            Row row = sheet.getRow(i);
            // 交易日期
            String dateStr = row.getCell(4).getStringCellValue().replaceAll("\t","");
            String time = row.getCell(5).getStringCellValue().replaceAll("\t","");
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            Date date1 = df.parse(dateStr);
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String date = df2.format(date1);
            // 交易时间
            //String time = row.getCell(1).getStringCellValue().replaceAll("\t","");
            String beizhu = row.getCell(9).getStringCellValue().replaceAll("\t","");
            if(beizhu.substring(0,3).equals("余额宝")){
                continue;
            }
            double money = row.getCell(10).getNumericCellValue();
            ZFBExcelVo excelVo = new ZFBExcelVo(date,time,beizhu);
            excelVo.setMoney(money);
            voList.add(excelVo);
        }
        return voList;
    }

    private static void downloadExcel(List<ZFBExcelVo> zhichuVoList,List<ZFBExcelVo> shouruVoList) throws IOException {
        Workbook wb = new XSSFWorkbook();
        // 支出
        int zhichuSize = zhichuVoList.size();
        if(zhichuSize > 0){
            Sheet sheet1 = wb.createSheet("支出");
            sheet1.setColumnWidth(0,20*256);
            sheet1.setColumnWidth(3,40*256);
            for(int i = 0 ; i < zhichuSize ; i++){
                ZFBExcelVo excelVo = zhichuVoList.get(i);
                setHeader(sheet1);
                Row row = sheet1.createRow(i+1);
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(excelVo.getDate() + " " +excelVo.getTime());
                /*Cell cell1 = row.createCell(1);
                cell1.setCellValue(excelVo.getShouru());*/
                Cell cell2 = row.createCell(2);
                cell2.setCellValue(excelVo.getMoney());
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
                ZFBExcelVo excelVo = shouruVoList.get(i);
                setHeader(sheet2);
                Row row = sheet2.createRow(i+1);
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(excelVo.getDate() + " " +excelVo.getTime());
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(excelVo.getMoney());
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

    private static class ZFBExcelVo{
        private String date;
        private String time;
        private double money;
        private String beizhu;

        public ZFBExcelVo( String date, String time, String beizhu) {
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

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getBeizhu() {
            return beizhu;
        }

        public void setBeizhu(String beizhu) {
            this.beizhu = beizhu;
        }
    }

}
