package com.der.easy;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.der.easy.dto.DataExcel;
import com.der.easy.dto.DataExcelDto;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liudong
 * 2023/6/19 14:52
 * @version 1.0
 */
@Slf4j
public class EasyExcelDemo {
    public static void main(String[] args) {
//        testARead();
        testBRead();
//        testWrite();
    }

    private static void testARead(){
        System.out.println("teatA DataExcel");
        // 获取当前目录的绝对路径
        String currentDirectory = System.getProperty("user.dir");

        // 构建文件的完整路径
        String filePath = currentDirectory + File.separator + "/excel-poi-project/src/main/java/com/der/easy/tmp/testImport.xlsx";
        EasyExcel.read(filePath, DataExcel.class, new PageReadListener<DataExcel>(dataList -> {
            for (DataExcel demoData : dataList) {
                System.out.println("读取到一条数据" + JSON.toJSONString(demoData));
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }
        })).sheet().doRead();
    }

    private static void testBRead(){
        System.out.println("teatA DataExcelDto");
        // 获取当前目录的绝对路径
        String currentDirectory = System.getProperty("user.dir");

        // 构建文件的完整路径
        String filePath = currentDirectory + File.separator + "/excel-poi-project/src/main/java/com/der/easy/tmp/testImport.xlsx";

        //
        List<DataExcelDto> dataExcelDtoList = new ArrayList<>();

        EasyExcel.read(filePath, DataExcelDto.class, new PageReadListener<DataExcelDto>(dataList -> {
            for (DataExcelDto dataExcelDto : dataList) {
                System.out.println("读取到一条数据" + JSON.toJSONString(dataExcelDto));
                log.info("读取到一条数据{}", JSON.toJSONString(dataExcelDto));
                dataExcelDtoList.add(dataExcelDto);
            }
        })).sheet().doRead();

        System.out.println("读取数据" + JSON.toJSONString(dataExcelDtoList));
    }

    private static void testWrite(){
        String filePath = "D:/tmp/exportFile.xlsx";
        List<DataExcelDto> list = new ArrayList<>();
        list.add(DataExcelDto.builder().dataA("haha").dataB("123456").build());
        list.add(DataExcelDto.builder().dataA("xixi").dataB("789456").build());
        EasyExcel.write(filePath,DataExcelDto.class).sheet("export1").doWrite(list);
        System.out.println("导出数据" + JSON.toJSONString(list));
    }

}
