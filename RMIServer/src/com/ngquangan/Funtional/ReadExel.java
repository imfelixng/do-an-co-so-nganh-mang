package com.ngquangan.Funtional;

import com.ngquangan.bean.CanBo;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadExel {


    static public ArrayList<CanBo> getDataFromXLS(File file) {
        FileInputStream inputStream = null;
        HSSFWorkbook workbook = null;
        ArrayList<CanBo> canBos = new ArrayList<>();
        try {

            inputStream = new FileInputStream(file);

            workbook = new HSSFWorkbook(inputStream);

            HSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            Row row = rowIterator.next();
            while (rowIterator.hasNext()) {
                row = rowIterator.next();

                CanBo cb = new CanBo();

                boolean gt = row.getCell(2).getStringCellValue().toLowerCase().equals("nam") ? true : false;

                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date date = formatter.parse(row.getCell(1).getStringCellValue());

                cb.setTeNV(row.getCell(0).getStringCellValue());
                cb.setNgaySinh(date);
                cb.setGioiTinh(gt);
                cb.setSoDT(row.getCell(3).getStringCellValue());
                cb.setEmail(row.getCell(4).getStringCellValue());
                cb.setPhongBan(row.getCell(5).getStringCellValue());
                cb.setChucVu(row.getCell(6).getStringCellValue());
                cb.setUsername(row.getCell(7).getStringCellValue());
                cb.setOnline(false);
                cb.setMaNV("cb_" + System.currentTimeMillis());
                canBos.add(cb);
                Thread.sleep(50);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return canBos;
    }

    static public ArrayList<CanBo> getDataFromXLSX(File file) {

        ArrayList<CanBo> canBos = new ArrayList<>();


        try {
            FileInputStream inputStream = new FileInputStream(file);

            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.rowIterator();

            Row row = rowIterator.next();

            while (rowIterator.hasNext()) {
                row = rowIterator.next();

                CanBo cb = new CanBo();

                boolean gt = row.getCell(2).getStringCellValue().toLowerCase().equals("nam") ? true : false;

                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date date = formatter.parse(row.getCell(1).getStringCellValue());

                cb.setTeNV(row.getCell(0).getStringCellValue());
                cb.setNgaySinh(date);
                cb.setGioiTinh(gt);
                cb.setSoDT(row.getCell(3).getStringCellValue());
                cb.setEmail(row.getCell(4).getStringCellValue());
                cb.setPhongBan(row.getCell(5).getStringCellValue());
                cb.setChucVu(row.getCell(6).getStringCellValue());
                cb.setUsername(row.getCell(7).getStringCellValue());
                cb.setOnline(false);
                cb.setMaNV("cb_" + System.currentTimeMillis());
                canBos.add(cb);
                Thread.sleep(50);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  canBos;
    }

}
