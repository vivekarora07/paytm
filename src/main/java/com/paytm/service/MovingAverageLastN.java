package com.paytm.service;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovingAverageLastN implements Trait {
    private List<Double> elements;
    private Map<Integer, Double> movingSumMap;
    private double sum;



    public MovingAverageLastN(){
        elements = new ArrayList<>();
        movingSumMap =new HashMap<>();
        sum=0;
    }

    public void addElements(@NonNull Double... elementList){
        for(Double element : elementList){
            populateElement(element);
        }
    }

    private void populateElement(@NonNull Double element){
        elements.add(element);
        sum+=element;
        movingSumMap.putIfAbsent(elementsSize(),sum);
    }

    public void addElement(@NonNull Double element){
        populateElement(element);
    }


    public List<Double> getElements(){
        return elements;
    }

    public List<Double> getLastNElements(@NonNull int n) throws Exception{
        if(n>=0 && n<=elementsSize()) {
            List<Double> returnedElements = new ArrayList<>();
            for (int i = elementsSize() - n; i < elementsSize(); i++) {
                returnedElements.add(elements.get(i));
            }
            return returnedElements;
        }else{
            throw new Exception("Requested number of elements of size "+n+" is greater than total size of "+elementsSize());
        }
    }

    public Double getMovingAvgOfLastNElement(@NonNull int n) throws Exception {
        if(n>=0 && n<=elementsSize()) {
            Double avg = 0.0;
            if(n==elementsSize()){
                avg = movingSumMap.get(elementsSize())/n;
            }else {
                avg = ((movingSumMap.get(elementsSize()) - movingSumMap.get(elementsSize() - n)) / n);
            }
            avg = roundToTwoPlaces(avg);
            return avg;
        }else{
            throw new Exception("Requested average size of last "+n+" is greater than total list size of "+elementsSize());
        }
    }

    public static double roundToTwoPlaces(@NonNull double d) {
        return ((long) (d < 0 ? d * 100 - 0.5 : d * 100 + 0.5)) / 100.0;
    }

    private int elementsSize(){
        return elements.size();
    }

    public void printLastNElements(@NonNull int n) throws Exception{
        if(n>0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Last " + n + " Element[");
            for (Double element : getLastNElements(n)) {
                sb.append(element + ",");
            }
            sb.append("\b]");
            System.out.println(sb.toString());
        }else{
            throw new Exception("No Elements in the list");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Element[");
        for(Double element : getElements()){
            sb.append(element+",");
        }
        sb.append("\b]\n");

        return sb.toString();
    }

    public static void main(String args[]){
        MovingAverageLastN movingAverageLastN =new MovingAverageLastN();
        movingAverageLastN.addElements(5.0,4.0,3.0);
        movingAverageLastN.addElement(2.0);
        movingAverageLastN.addElements(1.0,6.0);
        System.out.println(movingAverageLastN.toString());
        try {
        movingAverageLastN.printLastNElements(3);
                    System.out.println("Average="+movingAverageLastN.getMovingAvgOfLastNElement(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
