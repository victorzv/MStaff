package com.aspose.test.dom;

import com.aspose.test.TypePerson;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee extends Person implements IPerson  {

    public static BigDecimal ADD_PERSENT;
    public static BigDecimal ADD_PERSENT_MAX;

    public Employee(LocalDate date, String name) {
        this.name = name;
        this.date = date;
        typePerson = TypePerson.EMPLOYEE;
    }

    public Employee(){
        typePerson = TypePerson.EMPLOYEE;
    }

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

    public Employee(LocalDate date) {
        this.date = date;
        setName("E" + number++);
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
        return false;
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
        return null;
    }

}
