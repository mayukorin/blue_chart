package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Person;
import utils.DBUtil;

public class PersonValidator {

    public static List<String> validate(Person p,Boolean code_duplicate_check_flag,Boolean password_check_flag) {

        List<String> errors = new ArrayList<String>();

        String code_error = _validateCode(p.getCode(),code_duplicate_check_flag);

        if (!code_error.equals("")) {
            errors.add(code_error);
        }

        String name_error = _validateName(p.getName());
        if (!name_error.equals("")) {
            errors.add(name_error);
        }

        String password_error = _validatePassword(p.getPassword(),password_check_flag);
        if (!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    private static String _validateCode(String code,Boolean code_duplicate_check_flag) {

        if (code == null || code.equals("")) {
            return "IDを入力してください";
        }

        if (code_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();

            long person_count = (long)em.createNamedQuery("checkRegisteredCode",Long.class).setParameter("code",code).getSingleResult();
            em.close();
            if (person_count > 0) {
                return "入力されたIDはすでに使われています。";

            }
        }

        return "";
    }

    private static String _validateName(String name) {
        if (name == null || name.equals("")) {
            return "氏名を入力してください";
        }

        return "";
    }

    private static String _validatePassword(String password,Boolean password_check_flag) {

        if (password_check_flag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }

        return "";
    }


}
