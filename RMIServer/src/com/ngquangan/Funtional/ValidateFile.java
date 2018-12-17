package com.ngquangan.Funtional;

import java.io.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateFile {

    static boolean checkChuoi(String chuoi) {

        int length = chuoi.length();

        for(int i = 0; i < length ;i ++) {
            char kitu = chuoi.charAt(i);
            if(kitu >= 'A' && kitu <= 'z' || kitu == ' ') {
                return true;
            }
        }

        return false;
    }


    public static boolean checkTenCanBo(String tencanbo, int i) {

        BufferedWriter writer = null;
        boolean check = true;
        String rowError = "\nDòng " + i + ":";
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("errors.txt", true)));

            if(tencanbo == null) {
                rowError += " Tên cán bộ không được null";
                check =  false;
            }
            if(tencanbo.equals("")) {
                rowError += " Tên cán bộ không được để trống";
                check = false;
            } else {

                String temp = Normalizer.normalize(tencanbo, Normalizer.Form.NFD);
                Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
                String tencanbo_mahoa = pattern.matcher(temp).replaceAll("").replace('đ','d').replace('Đ','D');

                if(!checkChuoi(tencanbo_mahoa)) {
                    rowError += " Tên cán bộ chỉ bao gồm kí tự và khoảng chắn";
                    check = false;
                }

            }
            if(!check) {
                writer.write(rowError);
                writer.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return check;

        }

    }

    public static boolean checkNgaySinh(String ngaysinh, int i) {
        BufferedWriter writer = null;
        boolean check = true;
        String rowError = "\nDòng " + i + ":";
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("errors.txt", true)));

            if(ngaysinh == null) {
                rowError += " Ngày sinh không được null";
                check = false;
            }
            if(ngaysinh.equals("")) {
                rowError += " Ngày sinh không được để trống";
                check = false;
            } else {

                Pattern pattern = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");

                Matcher matcher = pattern.matcher(ngaysinh);

                boolean checkNgaySinh = matcher.matches();

                if(!checkNgaySinh) {
                    rowError += " Ngày sinh không đúng định dạng";
                    check = false;
                }

            }

            if(!check) {
                writer.write(rowError);
                writer.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return check;
        }
    }

    public static boolean checkGioiTinh(String gioitinh, int i) {

        BufferedWriter writer = null;
        boolean check = true;
        String rowError = "\nDòng " + i + ":";
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("errors.txt", true)));

            if(gioitinh == null) {
                rowError += " Giới tính không được null";
                check = false;
            }
            if(gioitinh.equals("")) {
                rowError += " Giới tính không được để trống";
                check = false;
            } else {
                String temp = Normalizer.normalize(gioitinh, Normalizer.Form.NFD);
                Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
                String gioitinh_mahoa = pattern.matcher(temp).replaceAll("").replace('đ','d').replace('Đ','D');
                if(!(gioitinh_mahoa.toLowerCase().equals("nam") || gioitinh_mahoa.toLowerCase().equals("nu"))) {
                    rowError += " Giới tính chỉ có thể là nam hoặc nữ";
                    check = false;
                }
            }

            if(!check) {
                writer.write(rowError);
                writer.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return check;
        }

    }

    public static boolean checkSoDT(String soDT, int i) {
        BufferedWriter writer = null;
        boolean check = true;
        String rowError = "\nDòng " + i + ":";
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("errors.txt", true)));

            if(soDT == null) {
                rowError += " Số điện thoại không được null";
                check = false;
            }
            if(soDT.equals("")) {
                rowError += " Số điện thoại không được để trống";
                check = false;
            } else {

                Pattern pattern = Pattern.compile("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$");

                Matcher matcher = pattern.matcher(soDT);

                boolean checkSDT = matcher.matches();

                if(!checkSDT) {
                    rowError += " Số điện thoại không đúng định dạng";
                    check = false;
                }

            }

            if(!check) {
                writer.write(rowError);
                writer.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return check;
        }
    }

    public static boolean checkEmail(String email, int i) {
        BufferedWriter writer = null;
        boolean check = true;
        String rowError = "\nDòng " + i + ":";
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("errors.txt", true)));

            if(email == null) {
                rowError += " Email không được null";
                check = false;
            }
            if(email.equals("")) {
                rowError += " Email không được để trống";
                check = false;
            } else {
                Pattern pattern = Pattern.compile("^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$");
                Matcher matcher = pattern.matcher(email);
                boolean checkEmail = matcher.matches();

                if(!checkEmail) {
                    rowError += " Email không đúng định dạng";
                    check = false;
                }

            }

            if(!check) {
                writer.write(rowError);
                writer.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return check;
        }
    }

    public static boolean checkPhongBan(String phongban, int i) {
        BufferedWriter writer = null;
        boolean check = true;
        String rowError = "\nDòng " + i + ":";
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("errors.txt", true)));

            if(phongban == null) {
                rowError += " Phòng ban không được null";
                check = false;
            }
            if(phongban.equals("")) {
                rowError += " Phòng ban không được để trống";
                check = false;
            } else {

                String temp = Normalizer.normalize(phongban, Normalizer.Form.NFD);
                Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
                String phongban_mahoa = pattern.matcher(temp).replaceAll("").replace('đ','d').replace('Đ','D');

                if(!checkChuoi(phongban_mahoa)) {
                    rowError += " Phòng ban chỉ bao gồm kí tự và khoảng chắn";
                    check = false;
                }
            }

            if(!check) {
                writer.write(rowError);
                writer.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return check;
        }
    }

    public static boolean checkChucVu(String chucvu, int i) {
        BufferedWriter writer = null;
        boolean check = true;
        String rowError = "\nDòng " + i + ":";
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("errors.txt", true)));

            if(chucvu == null) {
                rowError += " Chức vụ không được null";
                check = false;
            }
            if(chucvu.equals("")) {
                rowError += " Chức vụ không được để trống";
                check = false;
            } else {
                String temp = Normalizer.normalize(chucvu, Normalizer.Form.NFD);
                Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
                String phongban_mahoa = pattern.matcher(temp).replaceAll("").replace('đ','d').replace('Đ','D');

                if(!checkChuoi(phongban_mahoa)) {
                    rowError += " Chức vụ chỉ bao gồm kí tự và khoảng chắn";
                    check = false;
                }
            }

            if(!check) {
                writer.write(rowError);
                writer.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return check;
        }
    }

    public static boolean checkUsername(String username, ArrayList<String> usernames, int i) {

        BufferedWriter writer = null;
        boolean check = true;
        String rowError = "\nDòng " + i + ":";
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("errors.txt", true)));

            if(username == null) {
                rowError += " Username không được null";
                check = false;
            }
            if(username.equals("")) {
                rowError += " Username không được để trống";
                check = false;
            }

            boolean checkErr = false;

            for(String usernameDB : usernames) {
                if(username.equals(usernameDB)) {
                    checkErr = true;
                    break;
                }
            }

            if(checkErr) {
                rowError += " Username đã tồn tại";
                check = false;
            }
            if(!check) {
                writer.write(rowError);
                writer.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return check;
        }

    }

}
