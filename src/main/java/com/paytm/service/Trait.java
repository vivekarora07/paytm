package com.paytm.service;

import java.util.List;

public interface Trait {

    void addElements(Double... elementList);

    void addElement(Double element);

    List<Double> getElements();

    List<Double> getLastNElements(int n) throws Exception;

    Double getMovingAvgOfLastNElement(int n) throws Exception;

    void printLastNElements(int n) throws Exception;


}
