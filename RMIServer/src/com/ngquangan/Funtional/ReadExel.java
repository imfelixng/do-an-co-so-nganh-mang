package com.ngquangan.Funtional;

import com.ngquangan.Server.ServerImpl;
import com.ngquangan.bean.CanBo;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.*;
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

            BufferedWriter writer = null;
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("errors.txt", false)));
            writer.write(file.getAbsolutePath());
            writer.flush();

            Row row = rowIterator.next();

            ArrayList<String> errors = new ArrayList<>();
            int i = 0;
            boolean error = false;
            while (rowIterator.hasNext()) {
                boolean checkErr = false;

                i++;
                row = rowIterator.next();

                String tennhanvien = row.getCell(0) != null ? row.getCell(0).getStringCellValue().trim() : "";
                String ngaysinh = row.getCell(1) != null ? row.getCell(1).getStringCellValue().trim() : "";
                String gioitinh = row.getCell(2) != null ? row.getCell(2).getStringCellValue().trim() : "";
                String sodienthoai = row.getCell(3) != null ? row.getCell(3).getStringCellValue().trim() : "";
                String email = row.getCell(4) != null ? row.getCell(4).getStringCellValue().trim() : "";
                String phongban = row.getCell(5) != null ? row.getCell(5).getStringCellValue().trim() : "";
                String chucvu = row.getCell(6) != null ? row.getCell(6).getStringCellValue().trim() : "";
                String username = row.getCell(7) != null ? row.getCell(7).getStringCellValue().trim() : "";

                if(!ValidateFile.checkTenCanBo(tennhanvien, i)) {
                    checkErr = true;
                }

                if(!ValidateFile.checkNgaySinh(ngaysinh, i)) {
                    checkErr = true;
                }

                if(!ValidateFile.checkGioiTinh(gioitinh, i)) {
                    checkErr = true;
                }

                if(!ValidateFile.checkSoDT(sodienthoai, i)) {
                    checkErr = true;
                }

                if(!ValidateFile.checkEmail(email, i)) {
                    checkErr = true;
                }

                if(!ValidateFile.checkPhongBan(phongban, i)) {
                    checkErr = true;
                }

                if(!ValidateFile.checkChucVu(chucvu, i)) {
                    checkErr = true;
                }

                ArrayList<String> usernames = ServerImpl.getUsernames();

                if(!ValidateFile.checkUsername(username, usernames, i)) {
                    checkErr = true;
                }


                if(!checkErr) {
                    CanBo cb = new CanBo();

                    boolean gt = gioitinh.toLowerCase().equals("nam") ? true : false;

                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date date = formatter.parse(ngaysinh);

                    cb.setTeNV(tennhanvien);
                    cb.setNgaySinh(date);
                    cb.setGioiTinh(gt);
                    cb.setSoDT(sodienthoai);
                    cb.setEmail(email);
                    cb.setPhongBan(phongban);
                    cb.setChucVu(chucvu);
                    cb.setUsername(username);
                    cb.setOnline(false);
                    cb.setMaNV("cb_" + System.currentTimeMillis());
                    canBos.add(cb);
                    Thread.sleep(50);
                }else {
                    error = true;
                    continue;
                }

            }

            if(error) {
                JOptionPane.showMessageDialog(null, "Có 1 số dòng lỗi trong file exel, kiểm tra lỗi ở file errors.txt!");
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

            BufferedWriter writer = null;
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("errors.txt", false)));
            writer.write(file.getAbsolutePath());
            writer.flush();

            Row row = rowIterator.next();

            ArrayList<String> errors = new ArrayList<>();
            int i = 0;
            boolean error = false;
            while (rowIterator.hasNext()) {
                boolean checkErr = false;

                i++;
                row = rowIterator.next();

                String tennhanvien = row.getCell(0) != null ? row.getCell(0).getStringCellValue().trim() : "";
                String ngaysinh = row.getCell(1) != null ? row.getCell(1).getStringCellValue().trim() : "";
                String gioitinh = row.getCell(2) != null ? row.getCell(2).getStringCellValue().trim() : "";
                String sodienthoai = row.getCell(3) != null ? row.getCell(3).getStringCellValue().trim() : "";
                String email = row.getCell(4) != null ? row.getCell(4).getStringCellValue().trim() : "";
                String phongban = row.getCell(5) != null ? row.getCell(5).getStringCellValue().trim() : "";
                String chucvu = row.getCell(6) != null ? row.getCell(6).getStringCellValue().trim() : "";
                String username = row.getCell(7) != null ? row.getCell(7).getStringCellValue().trim() : "";

                if(!ValidateFile.checkTenCanBo(tennhanvien, i)) {
                    checkErr = true;
                }

                if(!ValidateFile.checkNgaySinh(ngaysinh, i)) {
                    checkErr = true;
                }

                if(!ValidateFile.checkGioiTinh(gioitinh, i)) {
                    checkErr = true;
                }

                if(!ValidateFile.checkSoDT(sodienthoai, i)) {
                    checkErr = true;
                }

                if(!ValidateFile.checkEmail(email, i)) {
                    checkErr = true;
                }

                if(!ValidateFile.checkPhongBan(phongban, i)) {
                    checkErr = true;
                }

                if(!ValidateFile.checkChucVu(chucvu, i)) {
                    checkErr = true;
                }

                ArrayList<String> usernames = ServerImpl.getUsernames();

                if(!ValidateFile.checkUsername(username, usernames, i)) {
                    checkErr = true;
                }


                if(!checkErr) {
                    CanBo cb = new CanBo();

                    boolean gt = gioitinh.toLowerCase().equals("nam") ? true : false;

                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date date = formatter.parse(ngaysinh);

                    cb.setTeNV(tennhanvien);
                    cb.setNgaySinh(date);
                    cb.setGioiTinh(gt);
                    cb.setSoDT(sodienthoai);
                    cb.setEmail(email);
                    cb.setPhongBan(phongban);
                    cb.setChucVu(chucvu);
                    cb.setUsername(username);
                    cb.setOnline(false);
                    cb.setMaNV("cb_" + System.currentTimeMillis());
                    canBos.add(cb);
                    Thread.sleep(50);
                }else {
                    error = true;
                    continue;
                }

            }

            if(error) {
                JOptionPane.showMessageDialog(null, "Có 1 số dòng lỗi trong file exel, kiểm tra lỗi ở file errors.txt!");
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
