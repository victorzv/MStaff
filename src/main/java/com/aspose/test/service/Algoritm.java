package com.aspose.test.service;

import com.aspose.test.Staff;
import com.aspose.test.dom.IPerson;

import java.math.MathContext;
import java.math.RoundingMode;

public interface Algoritm {
    MathContext mc = new MathContext(3, RoundingMode.CEILING);
    void calc(IPerson IPerson, Staff staff);
}
