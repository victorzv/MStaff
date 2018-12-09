package com.aspose.test.dom;

import com.aspose.test.TypePerson;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IPerson {

    //BigDecimal BASE_RATE = new BigDecimal(1000.00d);

    BigDecimal getBaseRate();

    BigDecimal getPersentAdd();

    BigDecimal getMaxPersentAdd();

    BigDecimal getSubPersentAdd();

    void setNameAndDate(LocalDate date, String name);

    LocalDate getDateIn();

    TypePerson getTypePerson();

    String getName();

    boolean canHaveSub();

}
