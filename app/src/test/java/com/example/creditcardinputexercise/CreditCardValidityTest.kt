package com.example.creditcardinputexercise

import org.junit.Assert
import org.junit.Test

class CreditCardValidityTest {
    @Test
    fun checkLuhn() {
        val card_name = "371449635398431"
        val validity = CreditCardValidity()
        val b = validity.checkLuhn(card_name)
        Assert.assertEquals(true, b)
    }

    @Test
    fun checkLuhn2() {
        val card_name = "671449635398436"
        val validity = CreditCardValidity()
        val b = validity.checkLuhn(card_name)
        Assert.assertEquals(false, b)
    }

    @Test
    fun checkLuhn3() {
        val card_name = "4532421174341278"
        val validity = CreditCardValidity()
        val b = validity.checkLuhn(card_name)
        Assert.assertEquals(true, b)
    }

    @Test
    fun checkLuhn4() {
        val card_name = "5569755825672968"
        val validity = CreditCardValidity()
        val b = validity.checkLuhn(card_name)
        Assert.assertEquals(true, b)
    }

    @Test
    fun check_length() {
        val card_name = "5569755825672968"
        val validity = CreditCardValidity()
        val b = validity.check_length(card_name)
        Assert.assertEquals(true, b)
    }

    @Test
    fun check_length2() {
        val card_name = "453242117434127890"
        val validity = CreditCardValidity()
        val b = validity.check_length(card_name)
        Assert.assertEquals(false, b)
    }

    @Test
    fun check_length3() {
        val card_name = "67144963539"
        val validity = CreditCardValidity()
        val b = validity.check_length(card_name)
        Assert.assertEquals(false, b)
    }

    @Test
    fun check_substring4() {
        val card_name_one = "371449635398431"
        val validity = CreditCardValidity()
        val b = validity.check_substring(card_name_one)
        Assert.assertEquals(true, b)
    }

    @Test
    fun check_substring5() {
        val card_name_one = "5569755825672968"
        val validity = CreditCardValidity()
        val b = validity.check_substring(card_name_one)
        Assert.assertEquals(true, b)
    }

    @Test
    fun check_substring() {
        val card_name_one = "4532421174341278"
        val validity = CreditCardValidity()
        val b = validity.check_substring(card_name_one)
        Assert.assertEquals(true, b)
    }

    @Test
    fun check_substring2() {
        val card_name_one = "1532421174341278"
        val validity = CreditCardValidity()
        val b = validity.check_substring(card_name_one)
        Assert.assertEquals(false, b)
    }

    @Test
    fun check_substring3() {
        val card_name_one = "75324211743412"
        val validity = CreditCardValidity()
        val b = validity.check_substring(card_name_one)
        Assert.assertEquals(false, b)
    }

    @Test
    fun get_card_name_am() {
        val card_name_one = "371449635398436"
        val validity = CreditCardValidity()
        val card_name = validity.get_card_name(card_name_one)
        Assert.assertEquals("American", card_name)
    }

    @Test
    fun get_card_name_discover() {
        val card_name_one = "671449635398436"
        val validity = CreditCardValidity()
        val card_name = validity.get_card_name(card_name_one)
        Assert.assertEquals("Discover", card_name)
    }

    @Test
    fun get_card_name_visa() {
        val card_name_one = "4532421174341278"
        val validity = CreditCardValidity()
        val card_name = validity.get_card_name(card_name_one)
        Assert.assertEquals("Visa", card_name)
    }

    @Test
    fun get_card_name_master() {
        val card_name_one = "5569755825672968"
        val validity = CreditCardValidity()
        val card_name = validity.get_card_name(card_name_one)
        Assert.assertEquals("Master", card_name)
    }
}