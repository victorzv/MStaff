package com.aspose;

import com.aspose.test.Staff;
import com.aspose.test.TypePerson;
import com.aspose.test.dom.Employee;
import com.aspose.test.dom.FactoryPerson;
import com.aspose.test.dom.IPerson;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestStaff {
    @Test
    public void testStaffChief(){
        FactoryPerson factoryPerson = new FactoryPerson();

        IPerson E1 = factoryPerson.createPerson(TypePerson.EMPLOYEE, LocalDate.of(2002, 11, 21), "E1");
        IPerson M1 = factoryPerson.createPerson(TypePerson.MANAGER, LocalDate.of(2002, 11, 21), "M1");

        Staff staff = new Staff();
        staff.addPesonChief(E1, M1);

        Assert.assertEquals(M1, staff.getChief(E1));
    }

    @Test
    public void testSalary(){
        IPerson E = new Employee(LocalDate.now().minusYears(1));
        Staff staff = new Staff();
        staff.addPerson(E);
        BigDecimal salary = Employee.BASE_RATE.add(Employee.BASE_RATE.multiply(Employee.ADD_PERSENT));
    }
}
