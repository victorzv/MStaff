package com.aspose;

import com.aspose.test.Main;
import com.aspose.test.Staff;
import com.aspose.test.TypePerson;
import com.aspose.test.dom.*;
import com.aspose.test.service.Algoritm;
import com.aspose.test.service.CalculateSalary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Properties;

public class StaffTest {
    FactoryPerson factoryPerson;

    @Before
    public void createDataBeforeTest(){
        Properties props = new Properties();
        try {
            Main main = new Main();
            props.load(new FileInputStream(main.getResourseFile("settings.ini")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Person.BASE_RATE = BigDecimal.valueOf(Double.valueOf(props.getProperty("BASE_RATE", "1000.0")));
        Manager.ADD_PERSENT = BigDecimal.valueOf(Double.valueOf(props.getProperty("MANAGER_ADD_PERSENT", "0.05")));
        Manager.ADD_PERSENT_MAX =  BigDecimal.valueOf(Double.valueOf(props.getProperty("MANAGER_ADD_PERSENT_MAX", "0.4")));
        Manager.SUB_PERSENT =  BigDecimal.valueOf(Double.valueOf(props.getProperty("MANAGER_SUB_PERSENT", "0.005")));
        Employee.ADD_PERSENT =  BigDecimal.valueOf(Double.valueOf(props.getProperty("EMPLOYEE_ADD_PERSENT", "0.03")));
        Employee.ADD_PERSENT_MAX =  BigDecimal.valueOf(Double.valueOf(props.getProperty("EMPLOYEE_ADD_PERSENT_MAX", "0.3")));
        Sales.ADD_PERSENT =  BigDecimal.valueOf(Double.valueOf(props.getProperty("SALES_ADD_PERSENT", "0.01")));
        Sales.ADD_PERSENT_MAX =  BigDecimal.valueOf(Double.valueOf(props.getProperty("SALES_ADD_PERSENT_MAX", "0.35")));
        Sales.SUB_PERSENT =  BigDecimal.valueOf(Double.valueOf(props.getProperty("SALES_SUB_PERSENT", "0.005")));


        factoryPerson = new FactoryPerson();
    }

    @Test
    public void chiefShouldBeCreated(){

        IPerson E1 = factoryPerson.createPerson(TypePerson.EMPLOYEE, LocalDate.of(2002, 11, 21), "E1");
        IPerson M1 = factoryPerson.createPerson(TypePerson.MANAGER, LocalDate.of(2002, 11, 21), "M1");

        Staff staff = new Staff();
        staff.addPesonChief(E1, M1);

        Assert.assertEquals(M1, staff.getChief(E1));
    }

    @Test
    public void salaryShouldBeEquals(){
        IPerson E = factoryPerson.createPerson(TypePerson.EMPLOYEE, LocalDate.now().minusYears(1), "E1");
        Staff staff = new Staff();
        staff.addPerson(E);
        BigDecimal salary = E.getBaseRate().add(E.getBaseRate().multiply(E.getPersentAdd(), Algoritm.mc), Algoritm.mc);
        staff.prepareCalculate(E);
        CalculateSalary calculateSalary = new CalculateSalary();
        calculateSalary.calculate(E, staff);
        Assert.assertEquals(salary, staff.getSalary(E));
    }

    @Test
    public void employeeCanNotBeChief(){
        IPerson E1 = factoryPerson.createPerson(TypePerson.EMPLOYEE, LocalDate.of(2002, 11, 21), "E1");
        IPerson M1 = factoryPerson.createPerson(TypePerson.MANAGER, LocalDate.of(2002, 11, 21), "M1");
        Staff staff = new Staff();
        Assert.assertEquals(false, staff.addPesonChief(M1, E1));
    }


}
