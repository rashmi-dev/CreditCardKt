package com.example.creditcardinputexercise

//check card validity
class CreditCardValidity {
    fun checkLuhn(get_card_number: String): Boolean {
        val security_length = get_card_number.length
        var sum = 0
        var count = 1
        for (i in security_length - 1 downTo 0) {
            var value = get_card_number[i] - '0'
            if (count % 2 == 0) value = value * 2
            sum += value / 10
            sum += value % 10
            count = count + 1
        }
        return sum % 10 == 0
    }

    fun check_length(get_card_number: String): Boolean {
        val security_length = get_card_number.length
        return if (security_length >= 13 && security_length <= 16) true else false
    }

    //check if the first few characters are right
    fun check_substring(get_card_number: String): Boolean {
        val security_length = get_card_number.length
        var ret = false
        if (security_length != 0) {
            ret = if (get_card_number.substring(0, 1).toInt() == 4 || get_card_number.substring(0, 1).toInt() == 5 || get_card_number.substring(0, 1).toInt() == 6 || get_card_number.substring(0, 2).toInt() == 37) {
                true
            } else {
                false
            }
        }
        return ret
    }

    //get card name based on first few characters
    fun get_card_name(get_card_number: String): String {
        var ret = ""
        if (get_card_number.substring(0, 1).toInt() == 4) {
            ret = "Visa"
        }
        if (get_card_number.substring(0, 1).toInt() == 5) {
            ret = "Master"
        }
        if (get_card_number.substring(0, 1).toInt() == 6) {
            ret = "Discover"
        }
        if (get_card_number.substring(0, 2).toInt() == 37) {
            ret = "American"
        }
        return ret
    }
}