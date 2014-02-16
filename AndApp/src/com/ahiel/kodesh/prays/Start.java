package com.ahiel.kodesh.prays;

import com.ahiel.kodesh.hebrewDate.CalendarDate;

/**
 * User: ahiel
 * Date: 09/12/12
 * Time: 23:38
 */
public class Start {
    public static int theme = 0;
    public static int nusach = 0;
    public static boolean rtl_fix = false;
    public static boolean noTahanun = false;
    public static boolean tishaBov = false;
    public static boolean zman = false;
    public static int fontSize = 10;
    public static boolean roshHodesh;
    public static boolean purim;
    public static boolean doMukaf;
    public static boolean mukaf;
    public static boolean ledavid;

    public static boolean atsmaut;

    public static boolean erev; //if ((!Start.noTahanun) && (!Start.erev) && (new Date().getDay() != 5))
    public static boolean aseret = false;
    public static boolean hanuca;
    public static boolean hebLayout;
    public static boolean anenu = false;
    public static Object calender;
    public static int mode;// chazan or yachid
    public static CalendarDate globalDate;
    public static boolean earlyWinter;
    public static StringBuilder sick;
    public static boolean winter;

    public static CharSequence pasuk;
    public static boolean fullScreen;
    public static boolean mizrochnik;
    public static boolean portraitOnly;
}
