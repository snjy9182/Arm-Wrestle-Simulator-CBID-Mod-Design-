// to compile and run -cp commons-math3-3.6.1.jar
//import org.apache.commons.math3.stat.inference.*;

//import org.apache.commons.math3.distribution.TDistribution;

package org.apache.commons.math3.stat.inference;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import java.util.Scanner;

public class ArmWrestle extends MyTTest{
//protected TTest myTest = new TTest();
    public static void main(String[] args) {

//initialization
        String gender;
        double gender1, gender2, length1, length2, angle1, angle2;
        double[] maxForce1, maxForce2;

        Scanner d = new Scanner(System.in);
        Scanner s = new Scanner(System.in);


//input person 1
        System.out.println("Gender of person 1, M/F");
        gender = s.nextLine();
        gender1 = genderToBinary(gender);
        System.out.println("Length (cm) of arm of person 1");
        length1 = d.nextDouble();
        System.out.println("Angle (degrees) of arm wrestle for person 1");
        angle1 = d.nextDouble();

//input person 2
        System.out.println("Gender of person 2, M/F");
        gender = s.nextLine();
        gender2 = genderToBinary(gender);
        System.out.println("Length (cm) of arm of person 2");
        length2 = s.nextDouble();
        System.out.println("Angle (degrees) of arm wrestle for person 1");
        angle2 = d.nextInt();

//calculate estimated max force and its error
        maxForce1 = maxForce(gender1, length1, angle1);
        maxForce2 = maxForce(gender2, length2, angle2);

//compare who will win
        double tStat = tStat(maxForce1[0], maxForce2[0], maxForce1[1], maxForce2[1], 26.0, 26.0);
        double dF = dF(maxForce1[0], maxForce2[0], maxForce1[1], maxForce2[1], 26.0, 26.0);
        double pValue = classTest(172, 173, 8, 9, 26, 26);
        //double pVal = prob(dF, tStat);
        //System.out.println("t stat = " + tStat + ", df = " + dF + ", Probability = " + pVal);
    }

/*
        TTest test = new TTest();
        double pValue = ((ArmWrestle)test).tTest(maxForce1[0], maxForce2[0], maxForce1[1], maxForce2[1], 26.0, 26.0);
        System.out.println(pValue);double pValue = this.tTest(maxForce1[0], maxForce2[0], maxForce1[1], maxForce2[1], 26.0, 26.0);
        System.out.println(pValue);
    */

//methods
    public static double genderToBinary(String s){
        if (s.equals("M"))
            return 1.0;
        else
            return 0.0; 
    }

    public static double[] maxForce(double gender, double length, double angle){
        double beta1, beta1error, beta2, beta2error, maxForceVal, partialB1, partialB2, maxForceError;
        beta1 = 2173.017; beta1error = 134.190;
        beta2 = 101.359; beta2error = 7.075;
        
        maxForceVal = (beta1/(length*Math.sin(Math.toRadians(angle))))+beta2*(gender);

        partialB1 = 1/(length*Math.sin(Math.toRadians(angle)));
        partialB2 = gender;
        maxForceError = Math.sqrt(Math.pow(partialB1*beta1error, 2) + Math.pow(partialB2*beta2error, 2));

        return new double[] {maxForceVal, maxForceError};
    }
    public static double tStat (double m1, double m2, double s1, double s2, double n1, double n2){
        return (m1-m2)/Math.pow(Math.pow(s1, 2)/n1+Math.pow(s2, 2)/n2, 0.5);
    }
    public static double dF (double m1, double m2, double s1, double s2, double n1, double n2){
        double s1Df = Math.pow(s1, 2)/n1;
        double s2Df = Math.pow(s2, 2)/n2;
        return Math.pow(s1Df+s2Df, 2)/(Math.pow(s1Df, 2)/(n1-1)+(Math.pow(s2Df, 2)/(n2-1)));
    }
    //public static double prob (double df, double t){
    //    TDistribution tdist = new TDistribution(df);
    //    return 2*tdist.cumulativeProbability(t);
    //}
}