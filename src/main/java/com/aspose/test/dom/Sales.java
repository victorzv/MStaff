package com.aspose.test.dom;

import com.aspose.test.TypePerson;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Sales extends Person  implements IPerson {

    public static BigDecimal ADD_PERSENT;
    public static BigDecimal ADD_PERSENT_MAX;
    public static BigDecimal SUB_PERSENT;

    private static int number = 1;
    String name;
    LocalDate date;
    TypePerson typePerson = TypePerson.SALES;

    public Sales(LocalDate date) {
        this.date = date;
        this.name = "S" + number++;
    }

    public Sales(LocalDate date, String name) {
        this.name = name;
        this.date = date;
    }

    public Sales(){}

    @Override
    public void setNameAndDate(LocalDate date, String name) {
        this.name = name;
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public LocalDate getDateIn() {
        return date;
    }

    @Override
    public TypePerson getTypePerson() {
        return typePerson;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean canHaveSub() {
        return true;
    }

    @Override
    public BigDecimal getBaseRate() {
        return Person.BASE_RATE;
    }

    @Override
    public BigDecimal getPersentAdd() {
        return ADD_PERSENT;
    }

    @Override
    public BigDecimal getMaxPersentAdd() {
        return ADD_PERSENT_MAX;
    }

    @Override
    public BigDecimal getSubPersentAdd() {
        return SUB_PERSENT;
    }

}
