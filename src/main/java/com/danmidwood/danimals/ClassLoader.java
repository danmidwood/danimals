package com.danmidwood.danimals;

import java.io.*;
/**
 * Write a description of class classLoader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ClassLoader
{
    static private java.util.regex.Pattern pat;
    static private String[] list;


    static public Object[] getClasses(Class type, String dir) {
        java.util.ArrayList rtn = new java.util.ArrayList();
        try {
            pat = java.util.regex.Pattern.compile("\\w+.class");
        } catch (Exception pse) { return null; }
        File path = new File(dir);
        list = path.list(
            new java.io.FilenameFilter() {
                public boolean accept(File dir, String name) {
                    String strippedName = new File(name).getName();
                    java.util.regex.Matcher mat = pat.matcher(strippedName);
                    return mat.matches();
                }
            }
        );
        
        for (int listIndex = 0; listIndex < list.length; listIndex++) {
            String thisString = list[listIndex];
            String endBit = thisString.substring(thisString.length()-5, thisString.length()).toLowerCase();
            if (endBit.equals("class")) {
                String firstBit = thisString.substring(0, thisString.length() - 6);
                try {
                    Class thisClass = Class.forName(firstBit);
                    // If the class is a descendent of the parameter type and is not abstract
                    if (type.isAssignableFrom(thisClass) && !(java.lang.reflect.Modifier.isAbstract(thisClass.getModifiers()))) {
                        rtn.add(thisClass);
                    }   
                } catch (Exception e) { System.out.println("Some error occured"); }
                catch (Error er) {} // Do nothing
            }
        }        
        return rtn.toArray();
    }

    

}
