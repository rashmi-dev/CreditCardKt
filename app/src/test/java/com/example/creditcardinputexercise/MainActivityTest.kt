package com.example.creditcardinputexercise

import org.junit.Assert
import org.junit.Test

class MainActivityTest {
    @Test
    fun field_empty() {
        val name = ""
        val activity = MainActivity()
        val b = activity.field_empty(name)
        Assert.assertEquals(false, b)
    }

    @Test
    fun field_empty2() {
        val name = "234234234234"
        val activity = MainActivity()
        val b = activity.field_empty(name)
        Assert.assertEquals(true, b)
    }

    @Test
    fun name_matches() {
        val name = "teretertertert"
        val activity = MainActivity()
        val b = activity.name_matches(name)
        Assert.assertEquals(true, b)
    }

    @Test
    fun name_matches2() {
        val name = "t "
        val activity = MainActivity()
        val b = activity.name_matches(name)
        Assert.assertEquals(true, b)
    }

    @Test
    fun name_matches3() {
        val name = "34546456456"
        val activity = MainActivity()
        val b = activity.name_matches(name)
        Assert.assertEquals(false, b)
    }

    @Test
    fun date_matches() {
        val name = "10/20"
        val activity = MainActivity()
        val b = activity.date_matches(name)
        Assert.assertEquals(true, b)
    }

    @Test
    fun date_matches1() {
        val name = "10+0020"
        val activity = MainActivity()
        val b = activity.date_matches(name)
        Assert.assertEquals(false, b)
    }

    @Test
    fun date_matches2() {
        val name = "fgkjdfhgkjdfg"
        val activity = MainActivity()
        val b = activity.date_matches(name)
        Assert.assertEquals(false, b)
    }

    @Test
    fun date_matches4() {
        val name = "10/99"
        val activity = MainActivity()
        val b = activity.date_matches(name)
        Assert.assertEquals(true, b)
    }

    @Test
    fun date_field_length() {
        val name = ""
        val activity = MainActivity()
        val b = activity.date_matches(name)
        Assert.assertEquals(false, b)
    }

    @Test
    fun date_field_length2() {
        val name = "10"
        val activity = MainActivity()
        val b = activity.date_matches(name)
        Assert.assertEquals(false, b)
    }

    @Test
    fun date_field_length3() {
        val name = "10/1999"
        val activity = MainActivity()
        val b = activity.date_matches(name)
        Assert.assertEquals(false, b)
    }

    @Test
    fun check_expiration() {
        val name = "08/20"
        val activity = MainActivity()
        val b = activity.check_expiration(name)
        Assert.assertEquals(false, b)
    }

    @Test
    fun check_expiration2() {
        val name = "10/20"
        val activity = MainActivity()
        val b = activity.check_expiration(name)
        Assert.assertEquals(true, b)
    }

    @Test
    fun check_expiration3() {
        val name = "10/19"
        val activity = MainActivity()
        val b = activity.check_expiration(name)
        Assert.assertEquals(false, b)
    }
}