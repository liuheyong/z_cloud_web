//package com.cloud.web.utils;
//
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.math.BigDecimal;
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * *
// *
// * @author zheng.shk
// * @version 1.0
// * @description {类描述}
// * @created 2014-5-26 下午4:44:00
// */
//public class POIExcelUtil {
//
//    protected transient final Logger log = LoggerFactory.getLogger(getClass());
//
//    /**
//     * 总行数
//     */
//    private int totalRows = 0;
//
//    /**
//     * 总列数
//     */
//    private int totalCells = 0;
//
//    /**
//     * 构造方法
//     */
//    public POIExcelUtil() {
//    }
//
//    /**
//     * <ul>
//     * <li>Description:[根据文件名读取excel文件]</li>
//     * <li>Created by [Huyvanpull] [Jan 20, 2010]</li>
//     * <li>Midified by [modifier] [modified time]</li>
//     * <ul>
//     *
//     * @param fileName
//     * @return
//     * @throws Exception
//     */
//    public List<ArrayList<String>> read(String fileName) {
//        List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();
//
//        /** 检查文件名是否为空或者是否是Excel格式的文件 */
//        if (fileName == null || !fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
//            return dataLst;
//        }
//
//        boolean isExcel2003 = true;
//        /** 对文件的合法性进行验证 */
//        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
//            isExcel2003 = false;
//        }
//
//        /** 检查文件是否存在 */
//        File file = new File(fileName);
//        if (file == null || !file.exists()) {
//            return dataLst;
//        }
//
//        try {
//            /** 调用本类提供的根据流读取的方法 */
//            dataLst = read(new FileInputStream(file), isExcel2003);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        /** 返回最后读取的结果 */
//        return dataLst;
//    }
//
//    /**
//     * <ul>
//     * <li>Description:[根据流读取Excel文件]</li>
//     * <li>Created by [Huyvanpull] [Jan 20, 2010]</li>
//     * <li>Midified by [modifier] [modified time]</li>
//     * <ul>
//     *
//     * @param inputStream
//     * @param isExcel2003
//     * @return
//     */
//    public List<ArrayList<String>> read(InputStream inputStream,
//                                        boolean isExcel2003) {
//        List<ArrayList<String>> dataLst = null;
//        try {
//            /** 根据版本选择创建Workbook的方式 */
//            Workbook wb = isExcel2003 ? new HSSFWorkbook(inputStream)
//                    : new XSSFWorkbook(inputStream);
//            dataLst = read(wb);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return dataLst;
//    }
//
//    /**
//     * 不处理纯数字类型的数据
//     *
//     * @param inputStream
//     * @param isExcel2003
//     * @return
//     */
//    public List<ArrayList<String>> readNoDecimalDeal(InputStream inputStream,
//                                                     boolean isExcel2003) {
//        List<ArrayList<String>> dataLst = null;
//        try {
//            /** 根据版本选择创建Workbook的方式 */
//            Workbook wb = isExcel2003 ? new HSSFWorkbook(inputStream)
//                    : new XSSFWorkbook(inputStream);
//            dataLst = readNoDecimalDeal(wb);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return dataLst;
//    }
//
//    //读取excel不去掉空格
//    public List<ArrayList<String>> read1(InputStream inputStream,
//                                         boolean isExcel2003) {
//        List<ArrayList<String>> dataLst = null;
//        try {
//            /** 根据版本选择创建Workbook的方式 */
//            Workbook wb = isExcel2003 ? new HSSFWorkbook(inputStream)
//                    : new XSSFWorkbook(inputStream);
//            dataLst = read1(wb);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return dataLst;
//    }
//
//    /**
//     * <ul>
//     * <li>Description:[得到总行数]</li>
//     * <li>Created by [Huyvanpull] [Jan 20, 2010]</li>
//     * <li>Midified by [modifier] [modified time]</li>
//     * <ul>
//     *
//     * @return
//     */
//    public int getTotalRows() {
//        return totalRows;
//    }
//
//    /**
//     * <ul>
//     * <li>Description:[得到总列数]</li>
//     * <li>Created by [Huyvanpull] [Jan 20, 2010]</li>
//     * <li>Midified by [modifier] [modified time]</li>
//     * <ul>
//     *
//     * @return
//     */
//    public int getTotalCells() {
//        return totalCells;
//    }
//
//    /**
//     * <ul>
//     * <li>Description:[读取数据]</li>
//     * <li>Created by [Huyvanpull] [Jan 20, 2010]</li>
//     * <li>Midified by [modifier] [modified time]</li>
//     * <ul>
//     *
//     * @param wb
//     * @return
//     */
//    private List<ArrayList<String>> read(Workbook wb) {
//        List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();
//
//        /** 得到第一个shell */
//        Sheet sheet = wb.getSheetAt(0);
//        this.totalRows = sheet.getPhysicalNumberOfRows();
//        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【总行数totalRows】:" + totalRows);
//        if (this.totalRows >= 1 && sheet.getRow(0) != null) {
//            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
//            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【总单元格数totalCells】:" + totalCells);
//        }
//
//        /** 循环Excel的行 zheng.sk,如果去掉第一行，则从1开始循环 */
//        for (int r = 1; r < this.totalRows; r++) {
//            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【检查总行数totalRows】:" + totalRows);
//            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【检查读取次数，当前是第:" + r + "次】");
//            Row row = sheet.getRow(r);
//            if (row == null) {
//                //timing-优化 当中间存在空行时，向下多检查一行
//                this.totalRows += 1;
//                continue;
//            }
//            List<String> nullList = new ArrayList<String>(); // 空列集合：用于验证获取的行信息是否为空
//            ArrayList<String> rowLst = new ArrayList<String>();
//            /** 循环Excel的列 */
//            for (short c = 0; c < this.getTotalCells(); c++) {
//                Cell cell = row.getCell(c);
//                String cellValue = "";
//                nullList.add(cellValue);
//                if (cell == null) {
//                    rowLst.add(cellValue);
//                    continue;
//                }
//                // zheng.sk 对于数字的类型转换
//                if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
//                    BigDecimal db = new BigDecimal(String.valueOf(cell.getNumericCellValue()));
//                    rowLst.add(db.toPlainString());
//                } else if (Cell.CELL_TYPE_FORMULA == cell.getCellType()) {
//                    //含有公式的处理
//                    try {
//                        rowLst.add(String.valueOf(cell.getStringCellValue()));
//                    } catch (IllegalStateException e) {
//                        log.error("", e);
//                        //如果公式生成的是数值，cell.getStringCellValue()方法会抛出IllegalStateException异常，在异常处理中使用cell
//						// .getNumericCellValue();
//                        rowLst.add(String.valueOf(cell.getNumericCellValue()));
//                    }
//                } else {
//                    rowLst.add(cell.getStringCellValue().trim().replace(" ", ""));
//                }
//            }
//            if (null != rowLst && !rowLst.equals(nullList)) { // 验证单行列集合是非为空：如果整行都为空，则舍弃该行不添加到集合
//                dataLst.add(rowLst);
//            }
//        }
//        return dataLst;
//    }
//
//    private List<ArrayList<String>> readNoDecimalDeal(Workbook wb) {
//        List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();
//
//        /** 得到第一个shell */
//        Sheet sheet = wb.getSheetAt(0);
//        this.totalRows = sheet.getPhysicalNumberOfRows();
//        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【总行数totalRows】:" + totalRows);
//        if (this.totalRows >= 1 && sheet.getRow(0) != null) {
//            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
//            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【总单元格数totalCells】:" + totalCells);
//        }
//
//        /** 循环Excel的行 zheng.sk,如果去掉第一行，则从1开始循环 */
//        for (int r = 1; r < this.totalRows; r++) {
//            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【检查总行数totalRows】:" + totalRows);
//            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【检查读取次数，当前是第:" + r + "次】");
//            Row row = sheet.getRow(r);
//            if (row == null) {
//                //timing-优化 当中间存在空行时，向下多检查一行
//                this.totalRows += 1;
//                continue;
//            }
//
//            List<String> nullList = new ArrayList<String>(); // 空列集合：用于验证获取的行信息是否为空
//            ArrayList<String> rowLst = new ArrayList<String>();
//            /** 循环Excel的列 */
//            for (short c = 0; c < this.getTotalCells(); c++) {
//                Cell cell = row.getCell(c);
//                String cellValue = "";
//                nullList.add(cellValue);
//                if (cell == null) {
//                    rowLst.add(cellValue);
//                    continue;
//                }
//                //处理纯数字默认加上(.0)问题
//                cell.setCellType(Cell.CELL_TYPE_STRING);
//                rowLst.add(cell.getStringCellValue().trim().replace(" ", ""));
//            }
//            if (!rowLst.equals(nullList)) { // 验证单行列集合是非为空：如果整行都为空，则舍弃该行不添加到集合
//                dataLst.add(rowLst);
//            }
//        }
//        return dataLst;
//    }
//
//    //读取excel不去掉空格
//    private List<ArrayList<String>> read1(Workbook wb) {
//        List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();
//
//        /** 得到第一个shell */
//        Sheet sheet = wb.getSheetAt(0);
//        this.totalRows = sheet.getPhysicalNumberOfRows();
//        if (this.totalRows >= 1 && sheet.getRow(0) != null) {
//            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
//        }
//
//        /** 循环Excel的行 zheng.sk,如果去掉第一行，则从1开始循环 */
//        for (int r = 1; r < this.totalRows; r++) {
//            Row row = sheet.getRow(r);
//            if (row == null) {
//                continue;
//            }
//
//            List<String> nullList = new ArrayList<String>(); // 空列集合：用于验证获取的行信息是否为空
//            ArrayList<String> rowLst = new ArrayList<String>();
//            /** 循环Excel的列 */
//            for (short c = 0; c < this.getTotalCells(); c++) {
//                Cell cell = row.getCell(c);
//                String cellValue = "";
//                nullList.add(cellValue);
//                if (cell == null) {
//                    rowLst.add(cellValue);
//                    continue;
//                }
//                // zheng.sk 对于数字的类型转换
//                if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
//                    BigDecimal db = new BigDecimal(String.valueOf(cell
//                            .getNumericCellValue()));
//                    rowLst.add(db.toPlainString());
//                } else {
//                    rowLst.add(cell.getStringCellValue());
//                }
//            }
//            if (null != rowLst && !rowLst.equals(nullList)) { // 验证单行列集合是非为空：如果整行都为空，则舍弃该行不添加到集合
//                dataLst.add(rowLst);
//            }
//        }
//        return dataLst;
//    }
//
//    /**
//     * <ul>
//     * <li>Description:[测试main方法]</li>
//     * <li>Created by [Huyvanpull] [Jan 20, 2010]</li>
//     * <li>Midified by [modifier] [modified time]</li>
//     * <ul>
//     *
//     * @param args
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception {
//        List<ArrayList<String>> dataLst = new POIExcelUtil()
//                .read("D:\\openAccount_00000001.xlsx");
//        System.out.println("rowSize:" + dataLst.size());
//        for (int i = 0; i < dataLst.size(); i++) {
//            ArrayList<String> cellLst = dataLst.get(i);
//            System.out.println("cellSize:" + cellLst.size());
//            for (int j = 0; j < cellLst.size(); j++) {
//                System.out.print(cellLst.get(j) + "|");
//            }
//        }
//        System.out.println("OK");
//    }
//
//    /**
//     * ********************************************************.<br>
//     *
//     * @param fileName
//     * @param list
//     * @param response
//     * @param headers
//     * @param columns
//     * @param cssList  设置文本格式
//     * @return ${return_type}
//     * @throws Exception
//     * @description 手动设置文本格式 <br>
//     * @author ldw <br>
//     * @created 2019/11/19 11:02  <br>
//     * ********************************************************.<br>
//     */
//    //创建单个excel ，记录数不超过60000条
//    @SuppressWarnings("deprecation")
//    public static <T> void createExcel(String fileName, List<T> list, HttpServletResponse response, String headers[], String columns[], List<Integer> cssList) {
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        // 声明一个工作薄
//        // 生成一个表格
//        HSSFSheet sheet = workbook.createSheet(fileName);
//        // 产生表格标题行
//        HSSFRow row = sheet.createRow(0);
//        for (short i = 0; i < headers.length; i++) {
//            HSSFCell cell = row.createCell(i);
//            cell.setCellValue(headers[i]);
//            if (cssList != null && cssList.size() > 0) {
//                for (int j = 0; j < cssList.size(); j++) {
//                    if (i == cssList.get(j)) {
//                        CellStyle css = workbook.createCellStyle();
//                        DataFormat format = workbook.createDataFormat();
//                        css.setDataFormat(format.getFormat("@"));
//                        sheet.setDefaultColumnStyle(i, css);
//                    }
//                }
//            }
//            sheet.setColumnWidth(i, 9000);
//        }
//
//        int index = 0;
//        Iterator<T> it = list.iterator();
//        while (it.hasNext()) {
//            index++;
//            row = sheet.createRow(index);
//            T t = (T) it.next();
//
//            for (short i = 0; i < columns.length; i++) {
//                String columnName = columns[i];
//                String getMethodName = "get"
//                        + columnName.substring(0, 1).toUpperCase()
//                        + columnName.substring(1);
//                HSSFCell cell = row.createCell(i);
//                try {
//                    Class<? extends Object> tCls = t.getClass();
//                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
//                    Object value = getMethod.invoke(t, new Object[]{});
//                    //判断值的类型后进行强制类型转换
//                    String textValue = null;
//                    if (value instanceof Date) {
//                        Date date = (Date) value;
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                        textValue = sdf.format(date);
//                    } else if (value instanceof Timestamp) {
//                        Timestamp date = (Timestamp) value;
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        textValue = sdf.format(date);
//                    } else {
//                        //其它数据类型都当作字符串简单处理
//                        if (value == null || "".equals(value))
//                            value = "";
//                        textValue = value.toString();
//                    }
//
//                    //如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
//                    if (textValue != null) {
//                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
//                        Matcher matcher = p.matcher(textValue);
//                        if (matcher.matches()) {
//                            //是数字当作double处理
//                            cell.setCellValue(Double.parseDouble(textValue));
//                        } else {
//                            cell.setCellValue(textValue);
//                        }
//                    }
//                } catch (SecurityException e) {
//                    e.printStackTrace();
//                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
//                } catch (IllegalArgumentException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        try {
//            response.setContentType("APPLICATION/vnd.ms-excel");
//            response.setHeader("Content-Disposition",
//					"attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO_8859_1") + ".xls");
//            OutputStream out = response.getOutputStream();
//            workbook.write(out);
//            out.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //创建大于60000条以上的导出
//    @SuppressWarnings("deprecation")
//    public static <T> void createExcels(String fileName, HttpServletResponse response, String headers[], List<T> list, String columns[]) {
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        int k = 0;
//        if (list.size() % 60000 != 0) {
//            k = list.size() / 60000;
//        }
//        if (list.size() <= 60000) {
//            k = 0;
//        }
//        for (int x = 0; x <= k; x++) {
//            HSSFSheet sheet = workbook.createSheet(fileName + "_" + x);
//            // 设置表格默认列宽度为30个字节
//            sheet.setDefaultColumnWidth((short) 30);
//            HSSFRow row = sheet.createRow(0);
//            for (short i = 0; i < headers.length; i++) {
//                HSSFCell cell = row.createCell(i);
//                cell.setCellValue(headers[i]);
//            }
//            Iterator<T> it = null;
//            int index = 0;
//            if (x == k) {
//                it = list.subList(x * 60000, list.size()).iterator();
//            } else {
//                it = list.subList(x * 60000, (x + 1) * 60000).iterator();
//            }
//            while (it.hasNext()) {
//                index++;
//                row = sheet.createRow(index);
//                T t = (T) it.next();
//                for (short i = 0; i < columns.length; i++) {
//                    String columnName = columns[i];
//                    String getMethodName = "get"
//                            + columnName.substring(0, 1).toUpperCase()
//                            + columnName.substring(1);
//                    HSSFCell cell = row.createCell(i);
//                    try {
//                        Class<? extends Object> tCls = t.getClass();
//                        Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
//                        Object value = getMethod.invoke(t, new Object[]{});
//                        //判断值的类型后进行强制类型转换
//                        String textValue = null;
//                        if (value instanceof Date) {
//                            Date date = (Date) value;
//                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                            textValue = sdf.format(date);
//                        } else {
//                            //其它数据类型都当作字符串简单处理
//                            if (value == null || "".equals(value))
//                                value = "";
//                            textValue = value.toString();
//                        }
//                        //如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
//                        if (textValue != null) {
//                            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
//                            Matcher matcher = p.matcher(textValue);
//                            if (matcher.matches()) {
//                                //是数字当作double处理
//                                cell.setCellValue(Double.parseDouble(textValue));
//                            } else {
//                                cell.setCellValue(textValue);
//                            }
//
//                        }
//                    } catch (SecurityException e) {
//                        e.printStackTrace();
//                    } catch (NoSuchMethodException e) {
//                        e.printStackTrace();
//                    } catch (IllegalArgumentException e) {
//                        e.printStackTrace();
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    } catch (InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        OutputStream out = null;
//        try {
//            response.setContentType("APPLICATION/vnd.ms-excel");
//            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO_8859_1") + ".xls");
//            out = response.getOutputStream();
//            workbook.write(out);
//            out.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    //导出卡密模板单独用的，添加了设置单元格格式为文本
//    @SuppressWarnings("deprecation")
//    public static <T> void createCouponExcels(String fileName, HttpServletResponse response, String headers[], List<T> list, String columns[]) {
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        int k = 0;
//        if (list.size() % 60000 != 0) {
//            k = list.size() / 60000;
//        }
//        if (list.size() <= 60000) {
//            k = 0;
//        }
//        for (int x = 0; x <= k; x++) {
//            HSSFSheet sheet = workbook.createSheet(fileName + "_" + x);
//
//            //设置为文本
//            CellStyle noteTitleStyle = workbook.createCellStyle();
//
//            HSSFDataFormat format = workbook.createDataFormat();
//            noteTitleStyle.setDataFormat(format.getFormat("@"));
//
//            // 设置表格默认列宽度为30个字节
//            sheet.setDefaultColumnWidth((short) 30);
//            HSSFRow row = sheet.createRow(0);
//            for (short i = 0; i < headers.length; i++) {
//                HSSFCell cell = row.createCell(i);
//                cell.setCellValue(headers[i]);
//                sheet.setDefaultColumnStyle(i, noteTitleStyle);
//            }
//            Iterator<T> it = null;
//            int index = 0;
//            if (x == k) {
//                it = list.subList(x * 60000, list.size()).iterator();
//            } else {
//                it = list.subList(x * 60000, (x + 1) * 60000).iterator();
//            }
//            while (it.hasNext()) {
//                index++;
//                row = sheet.createRow(index);
//                T t = (T) it.next();
//                for (short i = 0; i < columns.length; i++) {
//                    String columnName = columns[i];
//                    String getMethodName = "get"
//                            + columnName.substring(0, 1).toUpperCase()
//                            + columnName.substring(1);
//                    HSSFCell cell = row.createCell(i);
//                    try {
//                        Class<? extends Object> tCls = t.getClass();
//                        Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
//                        Object value = getMethod.invoke(t, new Object[]{});
//                        //判断值的类型后进行强制类型转换
//                        String textValue = null;
//                        if (value instanceof Date) {
//                            Date date = (Date) value;
//                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                            textValue = sdf.format(date);
//                        } else {
//                            //其它数据类型都当作字符串简单处理
//                            if (value == null || "".equals(value))
//                                value = "";
//                            textValue = value.toString();
//                        }
//                        //如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
//                        if (textValue != null) {
//                            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
//                            Matcher matcher = p.matcher(textValue);
//                            if (matcher.matches()) {
//                                //是数字当作double处理
//                                cell.setCellValue(Double.parseDouble(textValue));
//                            } else {
//                                cell.setCellValue(textValue);
//                            }
//
//                        }
//                    } catch (SecurityException e) {
//                        e.printStackTrace();
//                    } catch (NoSuchMethodException e) {
//                        e.printStackTrace();
//                    } catch (IllegalArgumentException e) {
//                        e.printStackTrace();
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    } catch (InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        OutputStream out = null;
//        try {
//            response.setContentType("APPLICATION/vnd.ms-excel");
//            response.setHeader("Content-Disposition",
//					"attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO_8859_1") + ".xls");
//            out = response.getOutputStream();
//            workbook.write(out);
//            out.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     * //TODO POI单元格内容水平左对齐与垂直居中
//     *
//     * @author wangjiucheng 2016年4月29日
//     */
//    public static CellStyle verticalCenterAndAlignLeft(Workbook wb, Row row) {
//        row.setHeightInPoints(24);
//        Font font = wb.createFont();
//        font.setFontHeightInPoints((short) 12);
//        font.setFontName("宋体");
//
//        CellStyle style = wb.createCellStyle();
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
//        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水平左对齐
//        style.setFont(font);// 将设置的字体放入到样式对象中
//        return style;
//    }
//
//    /**
//     * //TODO POI单元格内容水平与垂直居中
//     *
//     * @param wb
//     * @param row
//     * @return
//     * @author wangjiucheng 2016年4月29日
//     */
//    public static CellStyle verticalCenterAndAlignCenter(Workbook wb, Row row, float height) {
//        row.setHeightInPoints(height);
//        Font font = wb.createFont();
//        font.setFontHeightInPoints((short) 12);
//        font.setFontName("宋体");
//
//        CellStyle noteTitleStyle = wb.createCellStyle();
//        noteTitleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        noteTitleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
//        noteTitleStyle.setFont(font);
//        return noteTitleStyle;
//    }
//
//    /**
//     * //TODO 合并单元格
//     *
//     * @param firstRow 起始行
//     * @param lastRow  终止行
//     * @param firstCol 起始列
//     * @param lastCol  终止列
//     * @param sheet    sheet页
//     * @author wangjiucheng 2016年4月29日
//     */
//    public static void cellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol, Sheet sheet) {
//        // 有多少个列就合并多少个单元格
//        CellRangeAddress cra = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
//        // 进行合并
//        sheet.addMergedRegion(cra);
//
//    }
//
//    public static Sheet createSheet(Workbook wb, String sheetName) {
//        Sheet sheet = null;
//        // 判断是否手动设置的sheet的名称
//        if (sheetName != null && !"".equals(sheetName)) {
//            // 创建一个sheet页并设置名称
//            sheet = wb.createSheet(sheetName);
//        } else {
//            // 没有手动设置sheet名称使用其默认的名称
//            sheet = wb.createSheet();
//        }
//        return sheet;
//    }
//
//    // @描述：是否是2003的excel，返回true是2003
//    public static boolean isExcel2003(String filePath) {
//        return filePath.matches("^.+\\.(?i)(xls)$");
//    }
//
//    // @描述：是否是2007的excel，返回true是2007
//    public static boolean isExcel2007(String filePath) {
//        return filePath.matches("^.+\\.(?i)(xlsx)$");
//    }
//
//
//    /**
//     * 根据list<map>导出excel表
//     *
//     * @param fileName
//     * @param dataset
//     * @param response
//     * @param headers
//     * @param columNames
//     * @author lba
//     * 2018年3月7日
//     */
//    @SuppressWarnings("all")
//    public static void createExcelByMap(String fileName, List<Map<String, String>> dataset, HttpServletResponse response, String headers[], String columNames[]) {
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        // 声明一个工作薄
//        // 生成一个表格
//        HSSFSheet sheet = workbook.createSheet(fileName);
//        // 设置表格默认列宽度为30个字节
//        sheet.setDefaultColumnWidth((short) 30);
//        // 产生表格标题行
//        HSSFRow row = sheet.createRow(0);
//        HSSFCell cell = row.createCell(0);
//        for (short i = 0; i < headers.length; i++) {
//            cell = row.createCell(i);
//            cell.setCellValue(headers[i]);
//        }
//        int index = 0;
//        for (int ii = 0; ii < dataset.size(); ii++) {
//            index++;
//            row = sheet.createRow(index);
//            Map<String, String> map = dataset.get(ii);
//            for (short i = 0; i < columNames.length; i++) {
//                cell = row.createCell(i);
//                cell.setCellValue(map.get(columNames[i]));
//            }
//        }
//        OutputStream out = null;
//        try {
//            response.setContentType("APPLICATION/vnd.ms-excel");
//            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO_8859_1") + ".xls");
//            out = response.getOutputStream();
//            workbook.write(out);
//            out.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (out != null) {
//                try {out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     * 根据list<map>导出excel表 大于六万条数据的时候
//     *
//     * @param fileName
//     * @param dataset
//     * @param response
//     * @param headers
//     * @param columNames
//     */
//    @SuppressWarnings("all")
//    public static void createExcelsByMap(String fileName, List<Map<String, String>> dataset, HttpServletResponse response, String headers[], String columNames[]) {
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        int k = 0;
//        if (dataset.size() % '\uea60' != 0) {
//            k = dataset.size() / '\uea60';
//        }
//        if (dataset.size() <= 60000) {
//            k = 0;
//        }
//        for (int x = 0; x <= k; ++x) {
//            // 声明一个工作薄
//            // 生成一个表格
//            HSSFSheet sheet = workbook.createSheet("Sheet" + (x + 1));
//            // 设置表格默认列宽度为30个字节
//            sheet.setDefaultColumnWidth((short) 30);
//            // 产生表格标题行
//            HSSFRow row = sheet.createRow(0);
//            HSSFCell cell = row.createCell(0);
//            for (short i = 0; i < headers.length; i++) {
//                cell = row.createCell(i);
//                cell.setCellValue(headers[i]);
//            }
//            Iterator<Map<String, String>> it = null;
//            int index = 0;
//            if (x == k) {
//                it = dataset.subList(x * '\uea60', dataset.size()).iterator();
//            } else {
//                it = dataset.subList(x * '\uea60', (x + 1) * '\uea60').iterator();
//            }
//            while (it.hasNext()) {
//                ++index;
//                row = sheet.createRow(index);
//                Map<String, String> map = it.next();
//                for (short i = 0; i < columNames.length; i++) {
//                    cell = row.createCell(i);
//                    cell.setCellValue(map.get(columNames[i]));
//                }
//            }
//        }
//        OutputStream out = null;
//        try {
//            response.setContentType("APPLICATION/vnd.ms-excel");
//            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO_8859_1") + ".xls");
//            out = response.getOutputStream();
//            workbook.write(out);
//            out.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (out != null) {
//                try {out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
//
//    /**
//     * 根据list<map>导出excel表
//     *
//     * @param fileName
//     * @param dataset
//     * @param headers
//     * @param columNames
//     * @author lba
//     * 2018年3月7日
//     */
//    @SuppressWarnings("all")
//    public static byte[] createExcelByMapForByte(String fileName, List<Map<String, String>> dataset, String headers[], String columNames[]) {
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        ByteArrayOutputStream byout = null;
//        // 声明一个工作薄
//        // 生成一个表格
//        HSSFSheet sheet = workbook.createSheet(fileName);
//        // 设置表格默认列宽度为30个字节
//        sheet.setDefaultColumnWidth((short) 30);
//        // 产生表格标题行
//        HSSFRow row = sheet.createRow(0);
//        HSSFCell cell = row.createCell(0);
//        for (short i = 0; i < headers.length; i++) {
//            cell = row.createCell(i);
//            cell.setCellValue(headers[i]);
//        }
//        int index = 0;
//        for (int ii = 0; ii < dataset.size(); ii++) {
//            index++;
//            row = sheet.createRow(index);
//            Map<String, String> map = dataset.get(ii);
//            for (short i = 0; i < columNames.length; i++) {
//                cell = row.createCell(i);
//                cell.setCellValue(map.get(columNames[i]));
//            }
//        }
//        try {
//            byout = new ByteArrayOutputStream();
//            workbook.write(byout);
//            return byout.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return byout.toByteArray();
//    }
//
//    public static void excelTemplate(HttpServletResponse response, String fileName, List<String> sheetNameList, String headers[], List<String> remark) {
//        // 创建工作薄对象
//        HSSFWorkbook workbook = new HSSFWorkbook();
//
//        // 创建sheet页对象
//        HSSFSheet sheet1 = workbook.createSheet(sheetNameList.get(0));
//        // 设置表格默认列宽度为30个字节
//        sheet1.setDefaultColumnWidth((short) 15);
//        HSSFRow row = sheet1.createRow(0);
//        for (short i = 0; i < headers.length; i++) {
//            HSSFCell cell = row.createCell(i);
//            cell.setCellValue(headers[i]);
//        }
//        ;
//        //指定合并开始行、合并结束行 合并开始列、合并结束列
//        CellRangeAddress rangeAddress = new CellRangeAddress(20, 20, 0, 16);
//        sheet1.addMergedRegion(rangeAddress);
//        HSSFRow row1 = sheet1.createRow(20);
//        row1.setHeight((short) 2000);
//        //创建单元格，指定起始列号，从0开始
//        HSSFCell cell = row1.createCell((short) 0);
//
//        String str = "";
//        for (int i = 0; i < remark.size(); i++) {
//            str += remark.get(i) + "\r\n";
//        }
//
//        CellStyle style = workbook.createCellStyle();
//        style.setWrapText(true);
//        cell.setCellStyle(style);
//        cell.setCellValue(new HSSFRichTextString(str));
//
//        OutputStream out = null;
//        try {
//            response.setContentType("APPLICATION/vnd.ms-excel");
//            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO_8859_1") + ".xls");
//            out = response.getOutputStream();
//            workbook.write(out);
//            out.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @PostMapping("/downloadFile")
//    @ResponseBody
//    public void downloadExcel(String fileName) {
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletResponse response = servletRequestAttributes.getResponse();
//        String strPath = fileName;// 文件路径
//        if (strPath != null && !"".equals(strPath)) {
//            File file = new File(strPath);
//            String filename = generateFileName();
//            String extension = ".xls";
//            BufferedInputStream bis = null;
//            BufferedOutputStream bos = null;
//            if (file.exists()) {
//                response.setContentType("application/force-download");// 设置强制下载不打开
//                response.addHeader("Content-Disposition", "attachment;fileName=" + filename + extension);// 设置文件名
//                response.addHeader("Content-Length", String.valueOf(file.length()));//谷歌浏览器所需
//                try {
//                    bis = new BufferedInputStream(new FileInputStream(file));
//                    bos = new BufferedOutputStream(response.getOutputStream());
//                    byte[] buff = new byte[2048];
//                    int bytesRead;
//                    while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
//                        bos.write(buff, 0, bytesRead);
//                    bos.flush();
//                } catch (Exception e) {
//                    log.error(e.getMessage(),e);
//                    //deleteFile(strPath);
//                } finally {
//                    if (bis != null) {
//                        try {
//                            bis.close();
//                        } catch (IOException e) {
//                            log.error(e.getMessage(),e);
//                            //deleteFile(strPath);
//                        }
//                    }
//                    if (bos != null) {
//                        try {
//                            bos.close();
//                            //deleteFile(strPath);
//                        } catch (IOException e) {
//                            log.error(e.getMessage(),e);
//                            //deleteFile(strPath);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//}
