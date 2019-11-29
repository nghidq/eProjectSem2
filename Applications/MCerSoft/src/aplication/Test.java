/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication;

import java.util.*;



/**
 *
 * @author David Nghi
 */
public class Test {

    private static int getDayExam(Calendar bd) {
        Calendar today = Calendar.getInstance();
        int day =  bd.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) <= bd.get(Calendar.DAY_OF_YEAR)) {
            return day + 30;
        }
        return day;
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        float y = calendar.get(Calendar.YEAR);
        float m = calendar.get(Calendar.MONTH);
        float d = calendar.get(Calendar.DAY_OF_YEAR);
        System.out.println(y);
        System.out.println(m);
        System.out.println(d);
        System.out.println(getDayExam(calendar));
        if(getDayExam(calendar) < 30 )
            System.out.println(getDayExam(calendar));
        else System.out.println("loi");
    }
}
