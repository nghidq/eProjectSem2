/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

/**
 *
 * @author David Nghi
 */
public class MD5Password {
    public static String inputText;
    public static String mdFivePass(String password){
        try {     
            MessageDigest msd = MessageDigest.getInstance("MD5");
            byte[] srcTextBytes = password.getBytes("UTF-8");
            byte[] enrTextBytes = msd.digest(srcTextBytes);
            
            BigInteger bigInt = new BigInteger(1, enrTextBytes);
            inputText = bigInt.toString();
            
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            JOptionPane.showMessageDialog(null, "Password encryption failed!", "Message", 1);
        }
        return inputText;
    } 
}
