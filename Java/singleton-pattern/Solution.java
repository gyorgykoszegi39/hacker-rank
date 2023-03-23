import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.reflect.*;


class Singleton{
    public String str;
    private static Singleton singleInstance;
    
    private Singleton() {
        
    }
    
    public static Singleton getSingleInstance() {
        if(singleInstance == null) {
            singleInstance = new Singleton();
        }
        return singleInstance;
    }
}

class Solution {
    public static void main(String args[]) {
        Singleton singleton1 = Singleton.getSingleInstance();
        Singleton singleton2 = Singleton.getSingleInstance();

        System.out.println("singleton1: " + singleton1);
        System.out.println("singleton2: " + singleton2);
    }
}