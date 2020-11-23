package com.example.creditcardinputexercise

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

public class MainActivity : AppCompatActivity() {
    private var get_card: TextInputLayout? = null
    private var get_date: TextInputLayout? = null
    private var get_security_code: TextInputLayout? = null
    private var get_first_name: TextInputLayout? = null
    private var get_last_name: TextInputLayout? = null
    private var get_card_number: TextInputEditText? = null
    private var get_mm_yy_edit: TextInputEditText? = null
    private var get_security_code_edit: TextInputEditText? = null
    private var get_first_name_edit: TextInputEditText? = null
    private var get_last_name_edit: TextInputEditText? = null
    private var submit_button: Button? = null
    private var card_name: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        get_card = findViewById(R.id.card_number)
        get_date = findViewById(R.id.mm_yy)
        get_security_code = findViewById(R.id.security_code)
        get_first_name = findViewById(R.id.first_name)
        get_last_name = findViewById(R.id.last_name)
        get_card_number = findViewById(R.id.card_number_edit)
        get_mm_yy_edit = findViewById(R.id.mm_yy_edit)
        get_security_code_edit = findViewById(R.id.security_code_edit)
        get_first_name_edit = findViewById(R.id.first_name_edit)
        get_last_name_edit = findViewById(R.id.last_name_edit)
        submit_button = findViewById(R.id.submit)
        submit_button!!.setOnClickListener(View.OnClickListener {
            set_error_false()
            clear_focus()
            val cvv = get_security_code_edit!!.getText().toString()
            val card_num = get_card_number!!.getText().toString()
            val last_name = get_last_name_edit!!.getText().toString()
            val first_name = get_first_name_edit!!.getText().toString()
            val dt = get_mm_yy_edit!!.getText().toString()
            if (checkPaymentValidity(card_num) && checkCvv(cvv, card_name) && check_name(first_name, last_name) && date(dt)) {
                display_alert()
            }
        })
    }

    //clear focus before displaying the alert
    fun clear_focus() {
        get_last_name!!.clearFocus()
        get_card_number!!.clearFocus()
        get_mm_yy_edit!!.clearFocus()
        get_security_code_edit!!.clearFocus()
        get_first_name_edit!!.clearFocus()
        get_last_name_edit!!.clearFocus()
        submit_button!!.clearFocus()
    }

    //clear error messages and padding before displaying alert
    fun set_error_false() {
        get_card!!.isErrorEnabled = false
        get_date!!.isErrorEnabled = false
        get_security_code!!.isErrorEnabled = false
        get_first_name!!.isErrorEnabled = false
        get_last_name!!.isErrorEnabled = false
    }

    //check if the card name if valid, not empty and correct
    fun checkPaymentValidity(card_num: String): Boolean {
        val validity = CreditCardValidity()
        val length = validity.check_length(card_num)
        val str = validity.check_substring(card_num)
        val luhn = validity.checkLuhn(card_num)
        if (!length) {
            get_card!!.error = "Invalid length"
            return false
        }
        if (!str) {
            get_card!!.error = "Invalid start number"
            return false
        }
        if (!luhn) {
            get_card!!.error = "Invalid number"
            return false
        }
        if (length && str && luhn) {
            card_name = validity.get_card_name(card_num)
            Log.d("MainActivity", card_name)
            get_card!!.error = null
            return true
        }
        return false
    }

    //check if cvv field is valid
    fun checkCvv(cvv: String, card_name: String?): Boolean {
        val len = cvv.length
        if (!field_empty(cvv)) {
            get_security_code!!.error = "Field Empty"
            return false
        } else {
            if (card_name != null) {
                if (card_name === "Visa" || card_name === "Master" || card_name === "Discover") {
                    return if (len == 3) {
                        get_card_number!!.error = null
                        true
                    } else {
                        get_security_code!!.error = "Invalid length"
                        false
                    }
                } else if (card_name === "American") {
                    return if (len == 4) {
                        get_card_number!!.error = null
                        true
                    } else {
                        get_security_code!!.error = "Invalid length"
                        false
                    }
                }
            }
        }
        return true
    }

    //check if field is empty
    fun field_empty(name: String): Boolean {
        val len = name.length
        var ret = true
        if (len <= 0) {
            ret = false
        } else if (len > 0) {
            ret = true
        }
        return ret
    }

    //individual name field check
    fun name_matches(name: String): Boolean {
        val len = name.length
        var ret = true
        val pattern = Regex("[a-zA-Z ]+")
        if (!pattern.matches(name)) {
            ret = false
        }
        return ret
    }



    //integrated name field check
    fun check_name(first_name: String, last_name: String): Boolean {
        if (!field_empty(first_name)) {
            get_first_name!!.error = "Field Empty"
            return false
        }
        if (!field_empty(last_name)) {
            get_last_name!!.error = "Field Empty"
            return false
        }
        if (!name_matches(first_name)) {
            get_first_name!!.error = "Invalid name"
            return false
        }
        if (!name_matches(last_name)) {
            get_last_name!!.error = "Invalid name"
            return false
        }
        return true
    }

    //individual date field matches
    fun date_matches(date_text: String): Boolean {
        var ret = true
        var pattern = Regex("^(0[1-9]|1[0-2])/[0-9]{2}\$")
        if (!pattern.matches(date_text)) {
            ret = false
        }
        return ret
    }

    //individual date field check
    fun date_field_length(date_text: String): Boolean {
        val len = date_text.length
        var ret = true
        if (len > 5 || len < 5) {
            ret = false
        }
        return ret
    }

    //integrated check date field for empty and format
    fun date(date_text: String): Boolean {
        if (!field_empty(date_text)) {
            get_date!!.error = "Field Empty"
            return false
        }
        if (!date_field_length(date_text)) {
            get_date!!.error = "Invalid Length"
            return false
        }
        if (!date_matches(date_text)) {
            get_date!!.error = "Wrong format"
            return false
        }
        if (!check_expiration(date_text)) {
            get_date!!.error = "Card Expired"
            return false
        }
        return true
    }

    //check expiration date
    fun check_expiration(date_text: String): Boolean {
        val c = Calendar.getInstance()
        val month = c[Calendar.MONTH] + 1
        val year = c[Calendar.YEAR]
        val y2 = year.toString().substring(2, 4).toInt()
        val d1 = date_text.substring(0, 2).toInt()
        val y1 = date_text.substring(3, 5).toInt()
        if (y1 > 0 && y1 < 20) {
            return false
        }
        if (y1 < 100 && y1 > 50) {
            return false
        }
        return if (y1 == y2 && d1 < month) {
            false
        } else true
    }

    //display alert
    fun display_alert() {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage("Payment Successful")
        dialog.setPositiveButton("OK"
        ) { dialog, which ->
            //Toast.makeText(getApplicationContext(),"Payment Successful",Toast.LENGTH_LONG).show();
        }
        val alertDialog = dialog.create()
        alertDialog.show()
    }
}




