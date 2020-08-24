package models.validators;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import models.Solve2;

public class SolveValidator {

    public static List<String> validate(Solve2 s2) {
        List<String> errors = new ArrayList<String>();

        String date_error = _validateDate(s2.getDay());
        if (!date_error.equals("")) {
            errors.add(date_error);
        }

        String tt_error = _validateTT(s2.getTargettime());
        if (!tt_error.equals("")) {
            errors.add(tt_error);
        }



        return errors;
    }

    private static String _validateDate(String date) {
        if (date == null || date.equals("")) {
            return "解いた（解く予定の）日付を入力してください";

        }
        return "";
    }

    private static String _validateTT(LocalTime tt) {
        if (tt == null) {
            return "目標時間を入力してください。";
        }
        return "";
    }


}
