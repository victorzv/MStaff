package com.aspose.test.service;

import com.aspose.test.Staff;
import com.aspose.test.dom.IPerson;
import com.aspose.test.dom.Sales;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

public class SalesAlgoritm implements Algoritm {
    @Override
    public void calc(IPerson IPerson, Staff staff) {
        MathContext mc = Algoritm.mc;
        Period diffYear = Period.between(IPerson.getDateIn(), LocalDate.now());
        int years = diffYear.getYears();

        BigDecimal add = IPerson.getPersentAdd().multiply(IPerson.getBaseRate(), mc).multiply(new BigDecimal(years), mc);
        BigDecimal additionalSalary = (add.compareTo(IPerson.getBaseRate().multiply(IPerson.getMaxPersentAdd()))>1)? IPerson.getBaseRate().multiply(IPerson.getMaxPersentAdd()):add;

        AdditionalSalary addSalary = new AdditionalSalary();
        getSubSalary(IPerson, staff, addSalary);
        staff.setSalary(IPerson, IPerson.getBaseRate().add(additionalSalary, mc).add(addSalary.getSalary(), mc));
    }

    private void getSubSalary(IPerson IPerson, Staff staff, AdditionalSalary additionalSalary){
        Set<IPerson> listSubs = staff.getSub(IPerson);
        additionalSalary.setSalary(additionalSalary.getSalary().add(Sales.SUB_PERSENT.multiply(listSubs.stream().map(x->staff.getSalary(x)).reduce(BigDecimal.ZERO, BigDecimal::add), mc), mc));
        for (IPerson p : listSubs){
            getSubSalary(p, staff, additionalSalary);
        }
    }


    class AdditionalSalary{
        BigDecimal salary = BigDecimal.ZERO;
        void setSalary(BigDecimal salary){
            this.salary = salary;
        }
        BigDecimal getSalary(){
            return salary;
        }
    }
}
