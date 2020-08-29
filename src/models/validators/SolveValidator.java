package models.validators;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Solve2;

public class SolveValidator {

    public static List<String> validate(Solve2 s2) {
        List<String> errors = new ArrayList<String>();

        String date_error = _validateDate(s2.getDay());
        if (!date_error.equals("")) {
            errors.add(date_error);
        }

        String tt_error = _validateTT(s2.getTarget_minute(),s2.getTarget_second());
        if (!tt_error.equals("")) {
            errors.add(tt_error);
        }

        String st_error = _validateST(s2.getSolve_minute(),s2.getSolve_second());
        if (!st_error.equals("")) {
            errors.add(st_error);
        }



        return errors;
    }

    private static String _validateDate(String date) {

        try {
            if (date.equals("") | date == null) {
                return "解いた（特予定の）日付を入力してください";
            }
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date da =  sdFormat.parse(date);

        } catch (java.text.ParseException e) {

            return "解いた（解く予定の）日付をyyyy-mm-ddの形で入力してください";
        }

        return "";
    }

    private static String _validateTT(Integer m,Integer s) {
        if (m ==null || s == null) {
            return "目標時間を入力してください。";
        } else if (m != null && s != null) {
            if (m < 0 || s<0 || s > 59 ) {
                return "分の欄には0以上を、秒の欄には0以上59以下を入力してください。";
            }
        }
        return "";
    }

    private static String _validateST(Integer m,Integer s) {
        if ((m ==null && s != null)|| (m !=null && s == null) ) {
            return "解答時間は、分・秒どちらも入力してください。";

        }  else if (m != null && s != null) {
            if (m < 0 || s<0 || s > 59 ) {
                return "分の欄には0以上を、秒の欄には0以上59以下を入力してください。";
            }
        }
        return "";
    }


}
