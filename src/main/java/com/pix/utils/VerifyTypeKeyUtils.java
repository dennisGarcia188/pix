package com.pix.utils;

import com.pix.constants.Constants;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VerifyTypeKeyUtils {

    public String fillType(String key) {

        if (key.startsWith("+")) {
            return Constants.TYPE_KEY_CELPHONE;
        }
        if (key.contains("@")) {
            return Constants.TYPE_KEY_EMAIL;
        }
        if (key.length() == 14) {
            return Constants.TYPE_KEY_CNPJ;
        }
        if (key.length() == 11) {
            return Constants.TYPE_KEY_CPF;
        } else {
            return Constants.TYPE_KEY_ALEATORY;
        }

    }

}
