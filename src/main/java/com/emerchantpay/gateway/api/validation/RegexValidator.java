package com.emerchantpay.gateway.api.validation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class RegexValidator {

    // Regular expressions
    public static final Pattern VALID_EMAIL_REGEX = Pattern
            .compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PHONE_REGEX = Pattern.compile("^[0-9\\+]{1,}[0-9\\\\-]{3,15}$");
    public static final Pattern VALID_URL_REGEX = Pattern
            .compile("\\b(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    // Credit card Regular expressions
    public static final Pattern VALID_CREDIT_CARD_HOLDER = Pattern.compile("(?<! )[-a-zA-Z' ]{2,26}");
    public static final Pattern VALID_CREDIT_CARD_NUMBER = Pattern.compile("^(?:4[0-9]{12}(?:[0-9]{3})"
            + "?|[25][1-7][0-9]{14}|6"
            + "(?:s011|5[0-9][0-9])"
            + "[0-9]{12}|3[47][0-9]{13}|3"
            + "(?:0[0-5]|[68][0-9])"
            + "[0-9]{11}|"
            + "(?:2131|1800|35\\d{3})\\d{11})$");
    public static final Pattern VALID_CREDIT_CARD_CVV = Pattern.compile("^[0-9]{3,4}$");
    public static final Pattern VALID_CREDIT_CARD_EXP_MONTH = Pattern.compile("^(0?[1-9]|1[012])$");
    public static final Pattern VALID_CREDIT_CARD_EXP_YEAR = Pattern.compile("^\\d{4}$");

    // Gift card Regex
    public static final Pattern VALID_GIFT_CARD_NUMBER = Pattern.compile("[1-9][0-9]{9,20}");

    private ArrayList<String> invalidParamsList = new ArrayList<String>();

    // Validate amount
    public Boolean isValidAmount(BigDecimal amount) {
        if (amount.doubleValue() > 0 && amount != null) {
            invalidParamsList.removeAll(Arrays.asList("amount"));
            return true;
        } else {
            invalidParamsList.add("amount");
            return false;
        }
    }

    public Boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            invalidParamsList.add("customer_email");
            return false;
        } else if (VALID_EMAIL_REGEX.matcher(email).matches()) {
            invalidParamsList.removeAll(Arrays.asList("customer_email"));
            return true;
        } else {
            invalidParamsList.add("customer_email");
            return false;
        }
    }

    public Boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            invalidParamsList.add("customer_phone");
            return false;
        } else if (VALID_PHONE_REGEX.matcher(phone).matches()) {
            invalidParamsList.removeAll(Arrays.asList("customer_phone"));
            return true;
        } else {
            invalidParamsList.add("customer_phone");
            return false;
        }
    }

    // Validate Url
    public Boolean isValidUrl(String key, String value) {
        if (value == null || value.isEmpty()) {
            invalidParamsList.add(key);
            return false;
        } else if (VALID_URL_REGEX.matcher(value).matches()) {
            invalidParamsList.removeAll(Arrays.asList(key));
            return true;
        } else {
            invalidParamsList.add(key);
            return false;
        }
    }

    // Validate Credit card details
    public Boolean isValidCardHolder(String cardholder) {
        if (cardholder == null || cardholder.isEmpty()) {
            invalidParamsList.add("card_holder");
            return false;
        } else if (VALID_CREDIT_CARD_HOLDER.matcher(cardholder).matches()) {
            invalidParamsList.removeAll(Arrays.asList("card_holder"));
            return true;
        } else {
            invalidParamsList.add("card_holder");
            return false;
        }
    }

    public Boolean isValidCardNumber(String cardnumber) {
        if (cardnumber == null || cardnumber.isEmpty()) {
            invalidParamsList.add("card_number");
            return false;
        } else if (VALID_CREDIT_CARD_NUMBER.matcher(cardnumber).matches()) {
            invalidParamsList.removeAll(Arrays.asList("card_number"));
            return true;
        } else {
            invalidParamsList.add("card_number");
            return false;
        }
    }

    public Boolean isValidCvv(String cvv) {
        if (cvv == null || cvv.isEmpty()) {
            invalidParamsList.add("cvv");
            return false;
        } else if (VALID_CREDIT_CARD_CVV.matcher(cvv).matches()) {
            invalidParamsList.removeAll(Arrays.asList("cvv"));
            return true;
        } else {
            invalidParamsList.add("cvv");
            return false;
        }
    }

    public Boolean isValidExpMonth(String month) {
        if (month == null || month.isEmpty()) {
            invalidParamsList.add("expiration_month");
            return false;
        } else if (VALID_CREDIT_CARD_EXP_MONTH.matcher(month).matches()) {
            invalidParamsList.removeAll(Arrays.asList("expiration_month"));
            return true;
        } else {
            invalidParamsList.add("expiration_month");
            return false;
        }
    }

    public Boolean isValidExpYear(String year) {
        if (year == null || year.isEmpty()) {
            invalidParamsList.add("expiration_year");
            return false;
        } else if (VALID_CREDIT_CARD_EXP_YEAR.matcher(year).matches()) {
            invalidParamsList.removeAll(Arrays.asList("expiration_year"));
            return true;
        } else {
            invalidParamsList.add("expiration_year");
            return false;
        }
    }

    // Validate Gift card
    public Boolean isValidGiftCard(String giftcardNumber) {
        if (giftcardNumber == null && giftcardNumber.isEmpty()) {
            invalidParamsList.add("gift_card_number");
            return false;
        } else if (VALID_GIFT_CARD_NUMBER.matcher(giftcardNumber).matches()) {
            invalidParamsList.removeAll(Arrays.asList("gift_card_number"));
            return true;
        } else {
            invalidParamsList.add("gift_card_number");
            return false;
        }
    }

    public void clearInvalidParams() {
        invalidParamsList.clear();
    }

    public ArrayList<String> getInvalidParams() {
        return invalidParamsList;
    }
}