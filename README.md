################################################################################

   Moving Average of Last N elements

################################################################################


1. MovingAverageLastNTest is Junit test class which tests different APIs provided in MovingAverageLastN service class

2. IMovingAverageLastN expose public APIs of MovingAverageLastN to client MovingAverageLastNTest class

3.  MovingAverageLastN maintain sum of total elements of the moving list, all elements in the list and map of position of element as key and total sum of  elements upto the key element position

4. MovingAverageLastN provide API addElements which add multiple elements at once in the moving list

5. MovingAverageLastN provide API addElement which add single element in the moving list

6. MovingAverageLastN provide API getElements which fetch all the elements in the list

7. MovingAverageLastN provide API getLastNElements which fetch last N elements from the moving list

8. MovingAverageLastN provide API getMovingAvgOfLastNElement which calculate average of last N elements using formula (sum of all elements)-(sum of elements prior to last N elements)/N. Average is rounded upto 2 decimal places.
   For this map of movingSumMap which stores element position and sum of elements upto that position. This helps in efficiently calculating moving average of Last N elements in constant time at any instant of time

9. MovingAverageLastN provide API printLastNElements which prints last N elements of moving list of elements



