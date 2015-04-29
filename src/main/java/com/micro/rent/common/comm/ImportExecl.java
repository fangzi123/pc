package com.micro.rent.common.comm;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述：测试excel读取 导入的jar包
 * <p/>
 * poi-3.8-beta3-20110606.jar
 * <p/>
 * poi-ooxml-3.8-beta3-20110606.jar
 * <p/>
 * poi-examples-3.8-beta3-20110606.jar
 * <p/>
 * poi-excelant-3.8-beta3-20110606.jar
 * <p/>
 * poi-ooxml-schemas-3.8-beta3-20110606.jar
 * <p/>
 * poi-scratchpad-3.8-beta3-20110606.jar
 * <p/>
 * xmlbeans-2.3.0.jar
 * <p/>
 * dom4j-1.6.1.jar
 * <p/>
 * jar包官网下载地址：http://poi.apache.org/download.html
 * <p/>
 * 下载poi-bin-3.8-beta3-20110606.zipp
 * @作者：建宁
 * @时间：2012-08-29 下午16:27:15
 */

public class ImportExecl {

    /**
     * 总行数
     */

    private int totalRows = 0;

    /**
     * 总列数
     */

    private int totalCells = 0;

    /**
     * 错误信息
     */

    private String errorInfo;

    /**
     * 构造方法
     */

    public ImportExecl() {

    }

    /**
     * @描述：得到总行数
     * @作者：建宁
     * @时间：2012-08-29 下午16:27:15
     * @参数：@return
     * @返回值：int
     */

    public int getTotalRows() {

        return totalRows;

    }

    /**
     * @描述：得到总列数
     * @作者：建宁
     * @时间：2012-08-29 下午16:27:15
     * @参数：@return
     * @返回值：int
     */

    public int getTotalCells() {

        return totalCells;

    }

    /**
     * @描述：得到错误信息
     * @作者：建宁
     * @时间：2012-08-29 下午16:27:15
     * @参数：@return
     * @返回值：String
     */

    public String getErrorInfo() {

        return errorInfo;

    }

    /**
     * @描述：验证excel文件
     * @作者：建宁
     * @时间：2012-08-29 下午16:27:15
     * @参数：@param filePath　文件完整路径
     * @参数：@return
     * @返回值：boolean
     */

    public boolean validateExcel(String filePath) {

        /** 检查文件名是否为空或者是否是Excel格式的文件 */

        if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))) {

            errorInfo = "文件名不是excel格式";

            return false;

        }

        /** 检查文件是否存在 */

        File file = new File(filePath);

        if (file == null || !file.exists()) {

            errorInfo = "文件不存在";

            return false;

        }

        return true;

    }

    /**
     * @描述：根据文件名读取excel文件
     * @作者：建宁
     * @时间：2012-08-29 下午16:27:15
     * @参数：@param filePath 文件完整路径
     * @参数：@return
     * @返回值：List
     */

    public Map<String, List<List<String>>> read(String filePath) {

        Map<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();

        InputStream is = null;

        try {

            /** 验证文件是否合法 */

            if (!validateExcel(filePath)) {

                System.out.println(errorInfo);

                return null;

            }

            /** 判断文件的类型，是2003还是2007 */

            boolean isExcel2003 = true;

            if (WDWUtil.isExcel2007(filePath)) {

                isExcel2003 = false;

            }

            /** 调用本类提供的根据流读取的方法 */

            File file = new File(filePath);

            is = new FileInputStream(file);

            map = read(is, isExcel2003);

            is.close();

        } catch (Exception ex) {

            ex.printStackTrace();

        } finally {

            if (is != null) {

                try {

                    is.close();

                } catch (IOException e) {

                    is = null;

                    e.printStackTrace();

                }

            }

        }

        /** 返回最后读取的结果 */

        return map;

    }

    /**
     * @描述：根据流读取Excel文件
     * @作者：建宁
     * @时间：2012-08-29 下午16:40:15
     * @参数：@param inputStream
     * @参数：@param isExcel2003
     * @参数：@return
     * @返回值：List
     */

    public Map<String, List<List<String>>> read(InputStream inputStream, boolean isExcel2003) {

        List<List<String>> projectList = null;
        List<List<String>> houseList = null;
        List<List<String>> houseTrafficList = null;
        Map<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
        try {

            /** 根据版本选择创建Workbook的方式 */

            Workbook wb = null;

            if (isExcel2003) {
                wb = new HSSFWorkbook(inputStream);
            } else {
                wb = new XSSFWorkbook(inputStream);
            }
            projectList = read(wb, 0);
            houseList = read(wb, 1);
            houseTrafficList = read(wb, 2);
            map.put("project", projectList);
            map.put("house", houseList);
            map.put("houseTraffic", houseTrafficList);
        } catch (IOException e) {

            e.printStackTrace();

        }

        return map;

    }

    /**
     * @描述：读取数据
     * @作者：建宁
     * @时间：2012-08-29 下午16:50:15
     * @参数：@param Workbook
     * @参数：@return
     * @返回值：List<List<String>>
     */

    private List<List<String>> read(Workbook wb, int flag) {

        List<List<String>> dataLst = new ArrayList<List<String>>();

        /** 得到第一个shell */

        Sheet sheet = wb.getSheetAt(flag);
        /** 得到Excel的行数 */

        this.totalRows = sheet.getPhysicalNumberOfRows();

        /** 得到Excel的列数 */

        if (this.totalRows >= 1 && sheet.getRow(0) != null) {

            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();

        }
        DecimalFormat df = new DecimalFormat("#0.######");//使用DecimalFormat类对科学计数法格式的数字进行格式化
        /** 循环Excel的行 */

        for (int r = 0; r < this.totalRows; r++) {

            Row row = sheet.getRow(r);

            if (row == null) {

                continue;

            }

            List<String> rowLst = new ArrayList<String>();

            /** 循环Excel的列 */

            for (int c = 0; c < this.getTotalCells(); c++) {

                Cell cell = row.getCell(c);

                String cellValue = "";

                if (null != cell) {
                    // 以下是判断数据的类型
                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                            cellValue = df.format(cell.getNumericCellValue()) + "";
                            cellValue = StringUtil.removePointZero(cellValue);
                            break;
                        case HSSFCell.CELL_TYPE_STRING: // 字符串
                            cellValue = cell.getStringCellValue();
                            break;

                        case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                            cellValue = cell.getBooleanCellValue() + "";
                            break;

                        case HSSFCell.CELL_TYPE_FORMULA: // 公式
                            cellValue = cell.getCellFormula() + "";
                            break;

                        case HSSFCell.CELL_TYPE_BLANK: // 空值
                            cellValue = "";
                            break;

                        case HSSFCell.CELL_TYPE_ERROR: // 故障
                            cellValue = "非法字符";
                            break;

                        default:
                            cellValue = "未知类型";
                            break;
                    }
                }

                rowLst.add(cellValue);

            }

            /** 保存第r行的第c列 */

            dataLst.add(rowLst);

        }

        return dataLst;

    }

    /**
     *
     * @描述：main测试方法
     *
     * @作者：建宁
     *
     * @时间：2012-08-29 下午17:12:15
     *
     * @参数：@param args
     *
     * @参数：@throws Exception
     *
     * @返回值：void
     */

//	public static void main(String[] args) throws Exception
//	{
//
//		ImportExecl poi = new ImportExecl();
//
//		// List<List<String>> list = poi.read("d:/aaa.xls");
//
//		List<List<String>> list = poi.read("c:/book.xlsx");
//
//		if (list != null)
//		{
//
//			for (int i = 0; i < list.size(); i++)
//			{
//
//				System.out.print("第" + (i) + "行");
//
//				List<String> cellList = list.get(i);
//
//				for (int j = 0; j < cellList.size(); j++)
//				{
//
//					// System.out.print("    第" + (j + 1) + "列值：");
//
//					System.out.print("    " + cellList.get(j));
//
//				}
//				System.out.println();
//
//			}
//
//		}
//
//	}

}

/**
 * @描述：工具类
 * @作者：建宁
 * @时间：2012-08-29 下午16:30:40
 */

class WDWUtil {

    /**
     * @描述：是否是2003的excel，返回true是2003
     * @作者：建宁
     * @时间：2012-08-29 下午16:29:11
     * @参数：@param filePath　文件完整路径
     * @参数：@return
     * @返回值：boolean
     */

    public static boolean isExcel2003(String filePath) {

        return filePath.matches("^.+\\.(?i)(xls)$");

    }

    /**
     * @描述：是否是2007的excel，返回true是2007
     * @作者：建宁
     * @时间：2012-08-29 下午16:28:20
     * @参数：@param filePath　文件完整路径
     * @参数：@return
     * @返回值：boolean
     */

    public static boolean isExcel2007(String filePath) {

        return filePath.matches("^.+\\.(?i)(xlsx)$");

    }

}
