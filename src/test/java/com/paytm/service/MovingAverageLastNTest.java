package com.paytm.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class MovingAverageLastNTest {

    Trait iMovingAverageLastN;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){
        iMovingAverageLastN = new MovingAverageLastN();
    }

    @Test
    public void testAddElements() {
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 4.8, 9.0);
        assertEquals(iMovingAverageLastN.getElements().size(), 6);
    }

    @Test
    public void testAddElement() {
        iMovingAverageLastN.addElement(5.3);
        assertEquals(iMovingAverageLastN.getElements().size(), 1);
    }

    @Test
    public void testGetElements() {
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 4.8, 9.0);
        iMovingAverageLastN.addElement(5.3);
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 3.8);
        assertEquals(iMovingAverageLastN.getElements().size(),12);
    }

    @Test
    public void testGetLastNElements() throws Exception{
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 4.8, 9.0);
        iMovingAverageLastN.addElement(5.3);
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 3.8);
        assertEquals(iMovingAverageLastN.getLastNElements(3).get(0),new Double(7.0));
        assertEquals(iMovingAverageLastN.getLastNElements(3).get(2),new Double(3.8));
    }

    @Test
    public void testGetLastZeroElements() throws Exception{
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 4.8, 9.0);
        iMovingAverageLastN.addElement(5.3);
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 3.8);
        assertEquals(iMovingAverageLastN.getLastNElements(0).size(),0);
    }

    @Test
    public void testGetLastAllElements() throws Exception{
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 4.8, 9.0);
        iMovingAverageLastN.addElement(5.3);
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 3.8);
        assertEquals(iMovingAverageLastN.getLastNElements(12).get(0),new Double(5.0));
        assertEquals(iMovingAverageLastN.getLastNElements(12).get(11),new Double(3.8));
    }

    @Test
    public void testGetMovingAvgOfLastNElement() throws Exception{
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 4.8, 9.0);
        iMovingAverageLastN.addElement(5.3);
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 3.8);
        assertEquals(iMovingAverageLastN.getMovingAvgOfLastNElement(4),new Double(roundToTwoPlaces((6.0+7.0+2.0+3.8)/4)));
    }

    @Test
    public void testGetMovingAvgOfCompleteList() throws Exception{
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 4.8, 9.0);
        iMovingAverageLastN.addElement(5.3);
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 3.8);
        assertEquals(iMovingAverageLastN.getMovingAvgOfLastNElement(12),new Double(roundToTwoPlaces((5.0+6.0+7.0+2.0+4.8+9.0+5.3+5.0+6.0+7.0+2.0+3.8)/12)));
    }

    @Test
    public void testGetMovingAvgOfZeroElements() throws Exception{
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 4.8, 9.0);
        iMovingAverageLastN.addElement(5.3);
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 3.8);
        assertEquals(iMovingAverageLastN.getMovingAvgOfLastNElement(0),new Double (0));
    }

    @Test
    public void testGetMovingAvgOfListGreaterThanSize() throws Exception{
        expectedException.expect(Exception.class);
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 4.8, 9.0);
        iMovingAverageLastN.addElement(5.3);
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 3.8);
        iMovingAverageLastN.getMovingAvgOfLastNElement(13);
    }

    @Test
    public void testGetMovingAvgOfNegativeSizeElements() throws Exception{
        expectedException.expect(Exception.class);
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 4.8, 9.0);
        iMovingAverageLastN.addElement(5.3);
        iMovingAverageLastN.addElements(5.0,6.0,7.0,2.0, 3.8);
        iMovingAverageLastN.getMovingAvgOfLastNElement(-1);
    }

    private static double roundToTwoPlaces(double d) {
        return ((long) (d < 0 ? d * 100 - 0.5 : d * 100 + 0.5)) / 100.0;
    }


}