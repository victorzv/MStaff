package com.aspose.test.service;

import com.aspose.test.Staff;
import com.aspose.test.dom.IPerson;
import com.aspose.test.dom.Person;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.Period;

public class EmployeeAlgoritm implements Algoritm{
    MathContext mc = Algoritm.mc;
    @Override
    public void calc(IPerson IPerson, Staff staff) {
        Period diffYear = Period.between(IPerson.getDateIn(), LocalDate.now());
        int years = diffYear.getYears();
        BigDecimal add = IPerson.getPersentAdd().multiply(IPerson.getBaseRate()).multiply(new BigDecimal(years), mc);
        BigDecimal additionalSalary = (add.compareTo(IPerson.getBaseRate().multiply(IPerson.getMaxPersentAdd()))>1)? IPerson.getBaseRate().multiply(IPerson.getMaxPersentAdd()):add;
        staff.setSalary(IPerson, IPerson.getBaseRate().add(additionalSalary, mc));
    }
}
