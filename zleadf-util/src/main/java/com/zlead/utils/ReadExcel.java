package com.zlead.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ReadExcel {


    // 总行数
    private int totalRows = 0;
    // 总条数
    private int totalCells = 0;
    // 错误信息接收器
    private String errorMsg;

    // 构造方法
    public ReadExcel() {
    }

    // 获取总行数
    public int getTotalRows() {
        return totalRows;
    }

    // 获取总列数
    public int getTotalCells() {
        return totalCells;
    }

    // 获取错误信息
    public String getErrorInfo() {
        return errorMsg;
    }


   // 读EXCEL文件，获取信息集合

    public List<List< Object>> getExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();// 获取文件名
        //        List<Map<String, Object>> userList = new LinkedList<Map<String, Object>>();
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            return createExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   // 根据excel里面的内容读取客户信息

     // @param is      输入流
     // @param isExcel2003   excel是2003还是2007版本

    public List<List< Object>> createExcel(InputStream is, boolean isExcel2003) {
        try {
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            return readExcelValue(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


     //     * 读取Excel里面客户的信息

    private List<List<Object>> readExcelValue(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
        List<List<Object>> listOb = new ArrayList<>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            // 循环Excel的列
            Map<String, Object> map = new HashMap<String, Object>();
            List<Object> list = new ArrayList<>();
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    cell.setCellType(cell.CELL_TYPE_STRING);
                    if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        list.add(cell.getNumericCellValue());
                         }else{
                        list.add(cell.getStringCellValue());
                    }

/*

                    if(c == 0){
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String ShopId = String.valueOf(cell.getNumericCellValue());
                            map.put("ShopId", ShopId.substring(0, ShopId.length() - 2 > 0 ? ShopId.length() - 2 : 1));// 名称
                        }else{
                            map.put("ShopId", cell.getStringCellValue());
                        }
                    } else if (c == 1) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String ShopName = String.valueOf(cell.getNumericCellValue());
                            map.put("ShopName", ShopName.substring(0, ShopName.length() - 2 > 0 ? ShopName.length() - 2 : 1));
                        } else {
                            map.put("ShopName", cell.getStringCellValue());//
                        }
                    } else if (c == 2) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String ShopStatus = String.valueOf(cell.getNumericCellValue());
                            map.put("ShopStatus", ShopStatus.substring(0, ShopStatus.length() - 2 > 0 ? ShopStatus.length() - 2 : 1));// 名称
                        } else {
                            map.put("ShopStatus", cell.getStringCellValue());//
                        }
                    } else if (c == 3) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String ContactsName = String.valueOf(cell.getNumericCellValue());
                            map.put("ContactsName", ContactsName.substring(0, ContactsName.length() - 2 > 0 ? ContactsName.length() - 2 : 1));// 名称
                        } else {
                            map.put("ContactsName", cell.getStringCellValue());//
                        }
                    } else if (c == 4) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {

                            String ContactsPhone = cell.getStringCellValue();
                            map.put("ContactsPhone", ContactsPhone);// 名称
                        } else {
                            map.put("ContactsPhone", cell.getStringCellValue());//
                        }
                    } else if (c == 5) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String CompanyTopRegionId = String.valueOf(cell.getNumericCellValue());
                            map.put("CompanyTopRegionId", CompanyTopRegionId.substring(0, CompanyTopRegionId.length() - 2 > 0 ? CompanyTopRegionId.length() - 2 : 1));// 名称
                        } else {
                            map.put("CompanyTopRegionId", cell.getStringCellValue());//
                        }
                    } else if (c == 6) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String CompanyCityRegionId = String.valueOf(cell.getNumericCellValue());
                            map.put("CompanyCityRegionId", CompanyCityRegionId.substring(0, CompanyCityRegionId.length() - 2 > 0 ? CompanyCityRegionId.length() - 2 : 1));// 名称
                        } else {
                            map.put("CompanyCityRegionId", cell.getStringCellValue());//
                        }
                    } else if (c == 7) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String CompanyRegionId = String.valueOf(cell.getNumericCellValue());
                            map.put("CompanyRegionId", CompanyRegionId.substring(0, CompanyRegionId.length() - 2 > 0 ? CompanyRegionId.length() - 2 : 1));// 名称
                        } else {
                            map.put("CompanyRegionId", cell.getStringCellValue());//
                        }
                    } else if (c == 8) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String CompanyAddress = String.valueOf(cell.getNumericCellValue());
                            map.put("CompanyAddress", CompanyAddress.substring(0, CompanyAddress.length() - 2 > 0 ? CompanyAddress.length() - 2 : 1));// 名称
                        } else {
                            map.put("CompanyAddress", cell.getStringCellValue());//
                        }
                    } else if (c == 9) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String IsActive = String.valueOf(cell.getNumericCellValue());
                            map.put("IsActive", IsActive.substring(0, IsActive.length() - 2 > 0 ? IsActive.length() - 2 : 1));// 名称
                        } else {
                            map.put("IsActive", cell.getStringCellValue());//
                        }
                    } else if (c == 10) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String IsAuth = String.valueOf(cell.getNumericCellValue());
                            map.put("IsAuth", IsAuth.substring(0, IsAuth.length() - 2 > 0 ? IsAuth.length() - 2 : 1));// 名称
                        } else {
                            map.put("IsAuth", cell.getStringCellValue());//
                        }
                    }
*/

                }else {
                    list.add("");
                }
            }
            // 添加到list
            listOb.add(list);
            userList.add(map);
        }
        System.out.println(listOb);
        return listOb;
    }


     // 验证EXCEL文件

    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    // @描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

}
