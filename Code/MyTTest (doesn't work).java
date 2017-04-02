package org.apache.commons.math3.stat.inference;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
public class MyTTest extends TTest{
	protected TTest myTest = new TTest();
	public double classTest(double m1, double m2, double s1, double s2, double n1, double n2){
		return myTest.tTest(m1, m2, s1, s2, n1, n2);
    }
}