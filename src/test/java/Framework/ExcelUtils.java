package Framework;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import static Framework.GlobalVariable.GV;


public class ExcelUtils {

    // private static XSSFSheet ExcelWSheet;
    // private static XSSFWorkbook ExcelWBook;
    //private static XSSFCell Cell;
    private static XSSFRow Row;
    private static List<String> executeList = new ArrayList<String>();
    private static String excelFile = "Config.xlsx";


    public static String testMethod(String cipherInfo) {
        String result = "";
        try {
            String infoBase64 = new String(Base64.decodeBase64(cipherInfo), "utf-8");
            Cipher decryptCipher = Cipher.getInstance("DES");
            Key key = getKey("uitest".getBytes());
            decryptCipher.init(2, key);
            result = new String(decryptCipher.doFinal(hexStr2ByteArr(infoBase64)));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static byte[] hexStr2ByteArr(String strIn) {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        byte[] arrOut = new byte[iLen / 2];

        for(int i = 0; i < iLen; i += 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte)Integer.parseInt(strTmp, 16);
        }

        return arrOut;
    }

    private static Key getKey(byte[] arrBTmp) {
        byte[] arrB = new byte[8];

        for(int i = 0; i < arrBTmp.length && i < arrB.length; ++i) {
            arrB[i] = arrBTmp[i];
        }

        return new SecretKeySpec(arrB, "DES");
    }

    public static Object[][] getTestData_1SetData(String sheetName, String stepID, int rowNo) throws Exception {
        String[][] testData = new String[1][4];
        int totalDataSet = 1;
        int startCol = 5;

        HSSFWorkbook wb = new HSSFWorkbook();


        try {
            FileInputStream ExcelFile = new FileInputStream(excelFile);
            XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
            XSSFSheet ExcelWSheet = ExcelWBook.getSheet(sheetName);
            String tempValue;

            if (getCellData(ExcelWSheet, rowNo, 2).equals(stepID)) {

                testData[0][0] = getCellData(ExcelWSheet, rowNo, startCol);//get expected result
                testData[0][1] = getCellData(ExcelWSheet, rowNo, startCol + 1);// get actual result
                testData[0][2] = getCellData(ExcelWSheet, rowNo, startCol + 2);// get page object
                testData[0][3] = getCellData(ExcelWSheet, rowNo, startCol + 3);// get test data


            } else {
                System.out.println("For the test step: " + stepID + ": the passed rowNo value is not correct! Please correct it and try again.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;

    }

    public static Object[][] getTestData_MultipleSetData(String sheetName, int startRowNo, int endRowNo, int totalDataSet) throws Exception {
        String[][] testData = new String[totalDataSet][4];
        int startCol = 5;

        try {
            FileInputStream ExcelFile = new FileInputStream(excelFile);
            XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
            XSSFSheet ExcelWSheet = ExcelWBook.getSheet(sheetName);
            String tempValue;
            testData[0][0] = "";
            testData[0][1] = "";
            testData[0][2] = "";


            for (int j = 0; j < totalDataSet; j++) {
                for (int i = 0; startRowNo + i <= endRowNo; i++) {
                    testData[j][0] = testData[j][0] + ";" + getCellData(ExcelWSheet, startRowNo + i, startCol);// get expected result
                    testData[j][1] = testData[j][1] + ";" + getCellData(ExcelWSheet, startRowNo + i, startCol + 1);//get actual result
                    testData[j][2] = testData[j][2] + ";" + getCellData(ExcelWSheet, startRowNo + i, startCol + 2);//get page object
                    testData[j][3] = testData[j][3] + ";" + getCellData(ExcelWSheet, startRowNo + i, startCol + 3 + j);//get test data
                }


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;

    }

    public static String getStepName(String sheetName, String stepID) throws Exception {
        String testStepName = null;
        // to be designed
        return testStepName;

    }

    public static String getBrowser(String sheetName, int rowNo, int colNo) throws Exception {
        String browser = "";
        try {
            FileInputStream ExcelFile = new FileInputStream(excelFile);
            XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
            XSSFSheet ExcelWSheet = ExcelWBook.getSheet(sheetName);
            browser = getCellData(ExcelWSheet, rowNo, colNo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return browser;
    }

    public static void setGlobalVariable(String sheetName, int GVName_colNo, int GVValue_colNo) throws Exception {
        String browser = "";
        try {
            FileInputStream ExcelFile = new FileInputStream(excelFile);
            XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
            XSSFSheet ExcelWSheet = ExcelWBook.getSheet(sheetName);
            for (int i = 1; i < ExcelWSheet.getLastRowNum() + 1; i++) {
                if (!getCellData(ExcelWSheet, i, GVName_colNo).equals("")) {
                    GV.put(getCellData(ExcelWSheet, i, GVName_colNo), getCellData(ExcelWSheet, i, GVValue_colNo));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getConfigData(String sheetName, int rowNo, int colNo) throws Exception {
        String browser = "";
        try {
            FileInputStream ExcelFile = new FileInputStream(excelFile);
            XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
            XSSFSheet ExcelWSheet = ExcelWBook.getSheet(sheetName);
            browser = getCellData(ExcelWSheet, rowNo, colNo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return browser;
    }


    private static String getCellData(XSSFSheet ExcelWSheet, int RowNum, int ColNum) throws Exception {
        //row # RowNum starts from 0
        //column # ColNum starts from 0
        String cellData = "";
        XSSFCell Cell;
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            if (Cell != null) {
                Cell.setCellType(Cell.CELL_TYPE_STRING);
                cellData = Cell.getStringCellValue().trim();
                /*
                if (Cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    double val = Cell.getNumericCellValue();
                    cellData = String.valueOf((long) val);
                } else if (Cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    cellData = Cell.getStringCellValue().trim();
                }*/
            } else {
                cellData = "";
            }
            return cellData;
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }
    }


    private static String getCellData(String excelFileName, String sheetName, int RowNum, int ColNum) throws Exception {
        //row # RowNum starts from 0
        //column # ColNum starts from 0
        XSSFCell Cell;
        String cellData = "";
        try {
            FileInputStream ExcelFile = new FileInputStream(excelFileName);
            XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
            XSSFSheet ExcelWSheet = ExcelWBook.getSheet(sheetName);
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            if (Cell != null) {
                if (Cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    double val = Cell.getNumericCellValue();
                    cellData = String.valueOf((long) val);
                } else if (Cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    cellData = Cell.getStringCellValue().trim();
                }
            } else {
                cellData = "";
            }
            return cellData;
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }
    }

    private static String getCellData(XSSFWorkbook ExcelWBook, XSSFSheet ExcelWSheet, int RowNum, int ColNum) throws Exception {
        //row # RowNum starts from 0
        //column # ColNum starts from 0
        String cellData = "";
        XSSFCell Cell;
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            if (Cell != null) {
                if (Cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    double val = Cell.getNumericCellValue();
                    cellData = String.valueOf((long) val);
                } else if (Cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    cellData = Cell.getStringCellValue().trim();
                }
            } else {
                cellData = "";
            }
            return cellData;
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }
    }

    public static String getCellData(String excelFileName, String sheetName, String rowName, String columnName) throws Exception {
        //row # RowNum starts from 0
        //column # ColNum starts from 0
        XSSFCell Cell;
        String cellData = "";
        int rowNo = 0;
        int colNo = 0;
        try {
            FileInputStream ExcelFile = new FileInputStream(excelFileName);
            XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
            XSSFSheet ExcelWSheet = ExcelWBook.getSheet(sheetName);
            if (rowName.matches("[0-9]+")) {
                rowNo = Integer.parseInt(rowName);
            } else {
                rowNo = getRowNo(ExcelWBook, ExcelWSheet, rowName);
            }
            if (columnName.matches("[0-9]+")) {
                colNo = Integer.parseInt(columnName);
            } else {
                colNo = getColNo(ExcelWBook, ExcelWSheet, columnName);
            }

            Cell = ExcelWSheet.getRow(rowNo).getCell(colNo);
            if (Cell != null) {
                if (Cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    double val = Cell.getNumericCellValue();
                    cellData = String.valueOf((long) val);
                } else if (Cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    cellData = Cell.getStringCellValue().trim();
                }
            } else {
                cellData = "";
            }
            return cellData;
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }
    }


    private static int getRowNo(XSSFWorkbook ExcelWBook, XSSFSheet ExcelWSheet, String rowName) throws Exception {
        //row # RowNum starts from 0
        //column # ColNum starts from 0
        int rowNo = 0;
        try {
            for (int i = 0; i < ExcelWSheet.getLastRowNum() + 1; i++) {
                if (getCellData(ExcelWBook, ExcelWSheet, i, 0).equals(rowName)) {
                    rowNo = i;
                    return rowNo;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }
        return rowNo;
    }

    private static int getColNo(XSSFWorkbook ExcelWBook, XSSFSheet ExcelWSheet, String colName) throws Exception {
        //row # RowNum starts from 0
        //column # ColNum starts from 0
        int colNo = 0;
        try {
            for (int i = 0; i < 1000; i++) {
                if (getCellData(ExcelWBook, ExcelWSheet, i, 0).equals(colName)) {
                    colNo = i;
                    return colNo;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }
        return colNo;
    }


    public static void writeToExcel(XSSFSheet ExcelWSheet, int x, int y, String value) {
        //row # x starts from 0
        //column # y starts from 0
        try {
            //FileInputStream ExcelFile = new FileInputStream(excelFileName);
            // XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
            // XSSFSheet ExcelWSheet = ExcelWBook.getSheet(sheetName);
            Row row = ExcelWSheet.getRow((short) y);
            //XSSFRow row=ExcelWSheet.getRow(y);
            Cell cell = row.createCell(x);
            //cell.setCellValue(value);
            //XSSFCell cell=row.getCell((short) x);
            cell.setCellValue(value);

            //FileOutputStream os;
            //os = new FileOutputStream();
            // ExcelWSheet.getWorkbook().write
            //ExcelWBook.write(os);
            //os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeToExcel(String ExcelFile, String sheetName, int x, int y, String value) {
        //row # x starts from 0
        //column # y starts from 0
        try {
            File f = new File(ExcelFile);
            f.createNewFile();
            FileInputStream ExcelFileInput = new FileInputStream(ExcelFile);
            XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFileInput);

            XSSFWorkbook ExcelWBook1 = new XSSFWorkbook(ExcelFile);
            //XSSFSheet ExcelWSheet = ExcelWBook.getSheet(sheetName);
            XSSFSheet ExcelWSheet = ExcelWBook.createSheet(sheetName);
            Row row = ExcelWSheet.getRow((short) y);
            //XSSFRow row=ExcelWSheet.getRow(y);
            Cell cell = row.createCell(x);
            //cell.setCellValue(value);
            //XSSFCell cell=row.getCell((short) x);
            cell.setCellValue(value);

            FileOutputStream os;
            os = new FileOutputStream(ExcelFile);
            // ExcelWSheet.getWorkbook().write
            ExcelWBook.write(os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void createExcel(String excelFile, String sheetName, String[] titleRow) {
        //row # x starts from 0
        //column # y starts from 0
        try {
            FileInputStream ExcelFile = new FileInputStream(excelFile);
            XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
            XSSFSheet xs = ExcelWBook.createSheet(sheetName);
            XSSFSheet ExcelWSheet = ExcelWBook.getSheet(sheetName);
            // Row row = ExcelWSheet.getRow((short) y);
            //XSSFRow row=ExcelWSheet.getRow(y);
            // Cell cell = row.createCell(x);
            //cell.setCellValue(value);
            //XSSFCell cell=row.getCell((short) x);
            //cell.setCellValue(value);

            FileOutputStream os;
            os = new FileOutputStream(excelFile);
            ExcelWBook.write(os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateToExcel(String ExcelFile, String sheetName, int rowNo, int colNo, String value) {
        try {
            File f = new File(ExcelFile);
            FileInputStream ExcelFileInput = new FileInputStream(f);
            XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFileInput);
            XSSFSheet ExcelWSheet = ExcelWBook.getSheet(sheetName);
            Row row = ExcelWSheet.getRow(rowNo);
            Cell cell = row.getCell(colNo);
            cell.setCellValue(value);
            FileOutputStream os;
            os = new FileOutputStream(ExcelFile);
            ExcelWBook.write(os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void buildToExcel(String excelFile, String colName, String colvalue) throws Exception {
        FileInputStream ExcelFile = new FileInputStream(excelFile);
        XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
        XSSFSheet ExcelWSheet = ExcelWBook.getSheetAt(0);
        String sheetName = ExcelWSheet.getSheetName();
        int colNo = getColNo(ExcelWBook, ExcelWSheet, colName);
        updateToExcel(excelFile, sheetName, 1, colNo, colvalue);
    }

}
