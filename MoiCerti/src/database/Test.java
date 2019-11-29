/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author David Nghi
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String cerDir = new java.io.File( "." ).getCanonicalPath();
        File dir = new File(cerDir);

        if (dir.isDirectory()) {
            File[] files = dir.listFiles();

            for (File f : files) {
                System.out.println(f.getName().equals("config.properties"));
            }
        }
    }
}
