package com.aspose.test.dom;

import com.aspose.test.TypePerson;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FactoryPerson {

    final static Map<TypePerson, Supplier<IPerson>> map = new HashMap<>();
    static {
        map.put(TypePerson.EMPLOYEE, Employee::new);
        map.put(TypePerson.MANAGER, Manager::new);
        map.put(TypePerson.SALES, Sales::new);
    }

    public IPerson createPerson(TypePerson typePerson, LocalDate date, String name) {
        IPerson IPerson = map.get(typePerson).get();
        IPerson.setNameAndDate(date, name);
        return IPerson;
    }
}
