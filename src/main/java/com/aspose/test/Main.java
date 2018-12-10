package com.aspose.test;

import com.aspose.test.dom.*;
import com.aspose.test.service.CalculateSalary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {


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

        boolean fill_random = Boolean.parseBoolean(props.getProperty("FILL_RANDOM", "false"));
        int FILL_RANDOM_COUNT = Integer.valueOf(props.getProperty("FILL_RANDOM_COUNT", "100"));

        FactoryPerson factoryPerson = new FactoryPerson();

        if (!fill_random) {
            IPerson E1 = factoryPerson.createPerson(TypePerson.EMPLOYEE, LocalDate.of(2002, 11, 21), "E1");
            IPerson E2 = factoryPerson.createPerson(TypePerson.EMPLOYEE, LocalDate.of(2002, 11, 21), "E2");
            IPerson E3 = factoryPerson.createPerson(TypePerson.EMPLOYEE, LocalDate.of(2018, 11, 21), "E3");
            IPerson E4 = factoryPerson.createPerson(TypePerson.EMPLOYEE, LocalDate.of(2013, 11, 21), "E4");
            IPerson E5 = factoryPerson.createPerson(TypePerson.EMPLOYEE, LocalDate.of(2017, 11, 21), "E5");
            IPerson E6 = factoryPerson.createPerson(TypePerson.EMPLOYEE, LocalDate.of(2008, 11, 21), "E6");

            IPerson M1 = factoryPerson.createPerson(TypePerson.MANAGER, LocalDate.of(2002, 11, 21), "M1");
            IPerson M2 = factoryPerson.createPerson(TypePerson.MANAGER, LocalDate.of(2003, 11, 21), "M2");
            IPerson M3 = factoryPerson.createPerson(TypePerson.MANAGER, LocalDate.of(2004, 11, 21), "M3");
            IPerson M4 = factoryPerson.createPerson(TypePerson.MANAGER, LocalDate.of(2005, 11, 21), "M4");
            IPerson M5 = factoryPerson.createPerson(TypePerson.MANAGER, LocalDate.of(2005, 11, 21), "M5");

            IPerson S1 = factoryPerson.createPerson(TypePerson.SALES, LocalDate.of(1995, 11, 21), "S1");
            IPerson S2 = factoryPerson.createPerson(TypePerson.SALES, LocalDate.of(1996, 11, 21), "S2");
            IPerson S3 = factoryPerson.createPerson(TypePerson.SALES, LocalDate.of(1997, 11, 21), "S3");
            IPerson S4 = factoryPerson.createPerson(TypePerson.SALES, LocalDate.of(1998, 11, 21), "S4");
            IPerson S5 = factoryPerson.createPerson(TypePerson.SALES, LocalDate.of(1991, 11, 21), "S5");

            Staff staff = new Staff();
            staff.addSubsToChief(S1, M2, M3, E1);
            staff.addSubsToChief(M3, E6, M4, S4);
            staff.addSubsToChief(M2, S3, M1);
            staff.addSubsToChief(S3, E4);
            staff.addSubsToChief(M1, E5, S2);
            staff.addSubsToChief(S2, E2, E3);
            staff.addSubsToChief(S5, S1, M5);

            CalculateSalary calculateSalary = new CalculateSalary();

        /*
        * calculate salary M2
        * */
            staff.prepareCalculate(M2);
            calculateSalary.calculate(M2, staff);
            System.out.println("Salary " + M2.getName() + " = " + staff.getSalary(M2).doubleValue());

        /*
        * set salary to zero
        * */
            staff.flashSalary();

        /*
        * for calculate all salary need calculate salary for head element
        * */
            IPerson headIPerson = staff.getHead();
            staff.flashSalary();
            staff.prepareCalculate(headIPerson);
            calculateSalary.calculate(headIPerson, staff);

            staff.getAllSalary().entrySet().forEach(
                    x -> {
                        System.out.println(x.getKey().getName() + " = " + x.getValue().doubleValue());
                    }
            );

            BigDecimal allSalary = staff.getAllSalary().entrySet()
                    .stream()
                    .map(x -> x.getValue())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            System.out.println("Total salary: " + allSalary.doubleValue());
        }
        else{
            Staff staff = new Staff();
            IPerson previousPerson = null;
            for (int i = 0; i < FILL_RANDOM_COUNT; i+=2){
                IPerson P1 = factoryPerson.createPerson(TypePerson.SALES, LocalDate.now().minusYears(getRandom(1, 10)), "S"+i);
                IPerson P2 = null;
                switch (getRandom(1, 3)){
                    case 1: P2 = factoryPerson.createPerson(TypePerson.EMPLOYEE, LocalDate.now().minusYears(getRandom(1, 10)), "E"+(i-1)); break;
                    case 2: P2 = factoryPerson.createPerson(TypePerson.MANAGER, LocalDate.now().minusYears(getRandom(1, 10)), "M"+(i-1)); break;
                    case 3: P2 = factoryPerson.createPerson(TypePerson.SALES, LocalDate.now().minusYears(getRandom(1, 10)), "S"+(i-1)); break;
                }

                staff.addPesonChief(P2, P1);
                if (previousPerson == null)
                {
                    previousPerson = P1;
                }
                else {
                    staff.addPesonChief(P1, previousPerson);
                }
            }
            CalculateSalary calculateSalary = new CalculateSalary();
            IPerson theHead = staff.getHead();
            staff.prepareCalculate(theHead);
            calculateSalary.calculate(theHead, staff);
            System.out.println(theHead.getName() + " salary is " + staff.getSalary(theHead));

            BigDecimal allSalary = staff.getAllSalary().entrySet()
                    .stream()
                    .map(x -> x.getValue())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            System.out.println("Total salary: " + allSalary.doubleValue());
        }
    }

    private static int getRandom(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public File getResourseFile(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file;
    }

}
