package com.aspose.test.service;

import com.aspose.test.Staff;
import com.aspose.test.TypePerson;
import com.aspose.test.dom.IPerson;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CalculateSalary {

    final static Map<TypePerson, Supplier<Algoritm>> map = new HashMap<>();
    static {
        map.put(TypePerson.EMPLOYEE, EmployeeAlgoritm::new);
        map.put(TypePerson.MANAGER, ManagerAlgoritm::new);
        map.put(TypePerson.SALES, SalesAlgoritm::new);
    }

    public CalculateSalary() {}

    public void calculate(IPerson IPerson, Staff staff){
        map.get(IPerson.getTypePerson()).get().calc(IPerson, staff);
    }
}
