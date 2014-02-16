package com.ahiel.kodesh.prays.creator;


/**
 * User: ahiel
 * Date: 30/11/12
 * Time: 15:07
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.*;
import android.widget.*;
import com.ahiel.kodesh.prays.Start;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Mincha extends Activity {
    AudioManager aManager;
    TextView aleinu;
    TextView amida;
    TextView anenu;
    TextView ashrei;
    TextView avinu;
    int backgroundColor;
    Button close;
    int color;
    protected ImageView decrease_font;
    EditText et;
    GestureDetector gd;
    TextView haftara;
    protected ImageView icon;
    TextView ihud;
    protected ImageView image;
    protected ImageView increase_font;
    LayoutInflater inflater;
    TextView kdusha;
    TextView kohanim;
    protected ImageView modes_menu;
    TextView modim;
    boolean nusachChanged = false;
    String[] parts;
    PopupWindow pw;
    LinearLayout rl;
    SeekBar sb;
    PopupWindow st;
    ScrollView sv;
    Timer t;
    TextView tahanun;
    protected TextView temp1;
    Typeface tf;
    LinearLayout theTitle;
    protected TextView title;
    int titleColor;
    TextView torah;
    TextView tv;
    TextView tvAnenu;
    TextView tvKdushAshkenaz1;
    TextView tvKdushAshkenaz2;
    TextView tvKdusha;
    TextView tvKohanim;
    TextView tvModim;
    PowerManager.WakeLock wl;

    private void createLayout() {
        this.rl = new LinearLayout(this);
        if ((Start.theme != 3) || (Start.theme != 2))
            this.rl.setBackgroundColor(this.backgroundColor);
        this.rl.setOrientation(1);
        this.gd = new GestureDetector(this, new GestureListener(null));
        this.rl.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
                return Mincha.this.gd.onTouchEvent(paramMotionEvent);
            }
        });
        if (Start.nusach == 0) {
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            this.tv.setText("לשם יחוד");
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(0);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(2131100050);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(1);
            this.rl.addView(this.tv);
        }
        if (Start.nusach != 2)
            if (Start.nusach != 3) {
                this.ihud = new TextView(this);
                this.ihud.setTextColor(this.titleColor);
                if (Start.nusach != 0)
                    break label5178;
                this.ihud.setText("למנצח");
            }
        label414:
        label2718:
        label5794:
        label6306:
        while (true) {
            this.ihud.setTypeface(this.tf);
            this.ihud.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.ihud.setGravity(5);
            this.ihud.setFocusableInTouchMode(true);
            this.ihud.setId(2);
            this.rl.addView(this.ihud);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            if (Start.nusach == 0) {
                this.tv.setText(2131100051);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(3);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                this.tv.setText("קרבן התמיד");
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(4);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (Start.nusach != 3)
                    break label5218;
                this.tv.setText(2131100056);
                label595:
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(5);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                this.tv.setText("פיטום הקטורת");
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(35);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (Start.nusach != 0)
                    break label5251;
                this.tv.setText(2131099930);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(6);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                this.tv.setText("אשרי יושבי ביתך");
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(7);
                this.rl.addView(this.tv);
                this.ashrei = new TextView(this);
                this.ashrei.setTextColor(this.color);
                this.ashrei.setText(2131099931);
                this.ashrei.setTypeface(this.tf);
                this.ashrei.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.ashrei.setGravity(5);
                this.ashrei.setFocusableInTouchMode(true);
                this.ashrei.setId(8);
                this.rl.addView(this.ashrei);
                if ((Start.anenu) && (Start.mode != 1)) {
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    if (!Start.hebLayout)
                        break label5291;
                    this.tv.setText("פתיחת ההיכל");
                    label1072:
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(90);
                    this.rl.addView(this.tv);
                    this.torah = new TextView(this);
                    this.torah.setTextColor(this.color);
                    if (Start.nusach != 0)
                        break label5317;
                    if (!Start.noTahanun)
                        break label5304;
                    this.torah.setText(2131100137);
                    this.torah.setTypeface(this.tf);
                    this.torah.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.torah.setGravity(5);
                    this.torah.setId(91);
                    this.torah.setFocusableInTouchMode(true);
                    this.rl.addView(this.torah);
                    if (Start.nusach != 0)
                        break label5376;
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    if (!Start.hebLayout)
                        break label5350;
                    this.tv.setText("הוצאת ספר תורה");
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(94);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131100139);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(95);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    this.tv.setText("הגבהת ספר תורה");
                    label1466:
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(96);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131100140);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(97);
                    this.rl.addView(this.tv);
                    label1611:
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    this.tv.setText("ברכות התורה");
                    label1650:
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(98);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    if (Start.nusach != 0)
                        break label5833;
                    this.tv.setText(2131100141);
                    label1745:
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(99);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    this.tv.setText("קריאת התורה");
                    label1840:
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(98);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131100415);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(99);
                    this.rl.addView(this.tv);
                    if ((Start.nusach == 1) || (Start.nusach == 2) || (Start.nusach == 3)) {
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.titleColor);
                        if (!Start.hebLayout)
                            break label5880;
                        this.tv.setText("הגבהת ספר תורה");
                        label2045:
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(100);
                        this.rl.addView(this.tv);
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.color);
                        this.tv.setText(2131100241);
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(101);
                        this.rl.addView(this.tv);
                    }
                    if ((Start.nusach != 0) || (Start.tishaBov)) {
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.titleColor);
                        this.tv.setText("הפטרה");
                        label2241:
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(102);
                        this.rl.addView(this.tv);
                        this.haftara = new TextView(this);
                        this.haftara.setTextColor(this.color);
                        this.haftara.setText(2131099933);
                        this.haftara.setTypeface(this.tf);
                        this.haftara.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.haftara.setGravity(5);
                        this.haftara.setId(103);
                        this.haftara.setFocusableInTouchMode(true);
                        this.rl.addView(this.haftara);
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.color);
                        if (!Start.tishaBov)
                            break label5940;
                        if (Start.nusach != 0)
                            break label5906;
                        this.tv.setText(2131100416);
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(104);
                        this.rl.addView(this.tv);
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.color);
                        this.tv.setText(2131099934);
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(105);
                        this.rl.addView(this.tv);
                    }
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    this.tv.setText("החזרת ספר תורה");
                    label2623:
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(106);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    if (Start.nusach != 0)
                        break label5972;
                    this.tv.setText(2131100142);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(107);
                    this.rl.addView(this.tv);
                }
                if (Start.nusach == 0) {
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131100052);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(9);
                    this.rl.addView(this.tv);
                }
                if (Start.mode != 1) {
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    this.tv.setText("חצי קדיש");
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(10);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    if (Start.nusach != 0)
                        break label6019;
                    this.tv.setText(2131099927);
                    label3004:
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(11);
                    this.rl.addView(this.tv);
                }
                if (!Start.zman) ;
            }
            label5538:
            label7590:
            try {
                Date localDate1 = new Date();
                sunsetTime = Start.calender.getSunset();
                this.tv = new TextView(this);
                if (localDate1.before(sunsetTime))
                    this.tv.setTextColor(Start.colorZmanimRight);
                while (true) {
                    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(sunsetTime.getHours())).append(":");
                    if (sunsetTime.getMinutes() >= 10)
                        break label6211;
                    localObject = "0" + sunsetTime.getMinutes();
                    str3 = localObject;
                    if (!Start.hebLayout)
                        break label6252;
                    if (!Start.rtl_fix)
                        break label6224;
                    this.tv.setText("שקיעה: " + Tools.reverse(str3));
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    this.tv.setGravity(17);
                    this.tv.setId(88);
                    this.rl.addView(this.tv);
                    label3274:
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    this.tv.setText("עמידה");
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(12);
                    this.rl.addView(this.tv);
                    this.amida = new TextView(this);
                    this.amida.setTextColor(this.color);
                    if ((Start.nusach != 0) && (Start.nusach != 3))
                        break label6293;
                    this.amida.setText(2131099648);
                    this.amida.setTypeface(this.tf);
                    this.amida.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.amida.setGravity(5);
                    this.amida.setFocusableInTouchMode(true);
                    this.amida.setId(13);
                    this.rl.addView(this.amida);
                    draw18();
                    if ((Start.aseret) || ((Start.anenu) && (Start.nusach != 0))) {
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.titleColor);
                        if (!Start.hebLayout)
                            break label6306;
                        this.tv.setText("אבינו מלכנו");
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(15);
                        this.rl.addView(this.tv);
                        this.avinu = new TextView(this);
                        this.avinu.setTextColor(this.color);
                        if ((Start.nusach != 0) || (!Start.aseret))
                            break label6319;
                        this.avinu.setText(2131099932);
                        this.avinu.setTypeface(this.tf);
                        this.avinu.setTextSize(Start.fontSize);
                        this.avinu.setId(14);
                        this.avinu.setFocusableInTouchMode(true);
                        this.rl.addView(this.avinu);
                    }
                    if ((!Start.noTahanun) && (!Start.erev) && (new Date().getDay() != 5))
                        break label6371;
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    if (Start.nusach == 0)
                        this.tv.setText(2131099923);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(15);
                    this.rl.addView(this.tv);
                    if (Start.mode != 1) {
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.titleColor);
                        this.tv.setText("קדיש תתקבל");
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(18);
                        this.rl.addView(this.tv);
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.color);
                        if (Start.nusach != 0)
                            break label6811;
                        this.tv.setText(2131099926);
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(19);
                        this.rl.addView(this.tv);
                    }
                    if (Start.nusach != 0)
                        break label7590;
                    if (!Start.tishaBov)
                        break label6935;
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    if (!Start.hebLayout)
                        break label6896;
                    this.tv.setText("נחמה");
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(108);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131100420);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(109);
                    this.rl.addView(this.tv);
                    if (Start.mode != 1) {
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.titleColor);
                        this.tv.setText("קדיש");
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(110);
                        this.rl.addView(this.tv);
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.color);
                        this.tv.setText(2131099925);
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(111);
                        this.rl.addView(this.tv);
                    }
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    this.tv.setText("בשוב השם");
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(110);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131100421);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(111);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setText("");
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    if (Start.mode != 2)
                        break label7576;
                    this.tv.setTextColor(-65536);
                    this.tv.setText("קדיש יהא שלמא");
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(22);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131099925);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(23);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    this.tv.setText("עלינו לשבח");
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(24);
                    this.rl.addView(this.tv);
                    this.aleinu = new TextView(this);
                    this.aleinu.setTextColor(this.color);
                    this.aleinu.setText(2131099928);
                    this.aleinu.setTypeface(this.tf);
                    this.aleinu.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.aleinu.setGravity(5);
                    this.aleinu.setFocusableInTouchMode(true);
                    this.aleinu.setId(25);
                    this.rl.addView(this.aleinu);
                    if (Start.ledavid) {
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.titleColor);
                        this.tv.setText("לדוד");
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(60);
                        this.rl.addView(this.tv);
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.color);
                        this.tv.setText(2131100256);
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(61);
                        this.rl.addView(this.tv);
                    }
                    return;
                    label5178:
                    if (Start.nusach != 1)
                        break;
                    this.ihud.setText("פרשת הכיור");
                    break;
                    if (Start.nusach != 1)
                        break label414;
                    this.tv.setText(2131099956);
                    break label414;
                    label5218:
                    if (Start.nusach == 1) {
                        this.tv.setText(2131100218);
                        break label595;
                    }
                    this.tv.setText(2131099929);
                    break label595;
                    if (Start.nusach == 1) {
                        this.tv.setText(2131099957);
                        break label778;
                    }
                    if (Start.nusach != 3)
                        break label778;
                    this.tv.setText(2131100055);
                    break label778;
                    label5291:
                    this.tv.setText("The Opening of the Ark");
                    break label1072;
                    label5304:
                    this.torah.setText(2131100136);
                    break label1173;
                    label5317:
                    if (((Start.nusach != 1) && (Start.nusach != 2)) || (Start.noTahanun))
                        break label1173;
                    this.torah.setText(2131100236);
                    break label1173;
                    label5350:
                    this.tv.setText("Taking Out the Torah");
                    break label1282;
                    label5363:
                    this.tv.setText("Raising Up the Torah");
                    break label1466;
                    if ((Start.nusach != 1) && (Start.nusach != 2) && (Start.nusach != 3))
                        break label1611;
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    if (Start.hebLayout) {
                        this.tv.setText("הוצאת ספר תורה");
                        label5436:
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(93);
                        this.rl.addView(this.tv);
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.color);
                        if ((!Start.aseret) || (Start.nusach != 1))
                            break label5794;
                        this.tv.setText(2131100238);
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(94);
                        this.rl.addView(this.tv);
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.titleColor);
                        this.tv.setText("בריך שמיה");
                    }
                    while (true) {
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(95);
                        this.rl.addView(this.tv);
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.color);
                        this.tv.setText(2131100239);
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(96);
                        this.rl.addView(this.tv);
                        break;
                        this.tv.setText("Taking Out the Torah");
                        break label5436;
                        this.tv.setText(2131100237);
                        break label5538;
                        label5807:
                        this.tv.setText("Brich Shmeih");
                    }
                    label5820:
                    this.tv.setText("The Blessing of the Torah");
                    break label1650;
                    label5833:
                    if ((Start.nusach != 1) && (Start.nusach != 2) && (Start.nusach != 3))
                        break label1745;
                    this.tv.setText(2131100242);
                    break label1745;
                    label5867:
                    this.tv.setText("The Reading of the Torah");
                    break label1840;
                    label5880:
                    this.tv.setText("Raising Up the Torah");
                    break label2045;
                    this.tv.setText("Haftara");
                    break label2241;
                    if ((Start.nusach != 1) && (Start.nusach != 2) && (Start.nusach != 3))
                        break label2439;
                    this.tv.setText(2131100417);
                    break label2439;
                    label5940:
                    if (!Start.anenu)
                        break label2439;
                    this.tv.setText(2131100424);
                    break label2439;
                    label5959:
                    this.tv.setText("The Returning of the Torah");
                    break label2623;
                    label5972:
                    if (Start.nusach == 1) {
                        this.tv.setText(2131100244);
                        break label2718;
                    }
                    if ((Start.nusach != 2) && (Start.nusach != 3))
                        break label2718;
                    this.tv.setText(2131100171);
                    break label2718;
                    if ((Start.nusach == 1) || (Start.nusach == 3)) {
                        if (Start.aseret) {
                            this.tv.setText(2131099954);
                            break label3004;
                        }
                        this.tv.setText(2131099953);
                        break label3004;
                    }
                    if (Start.nusach != 2)
                        break label3004;
                    if (Start.aseret) {
                        this.tv.setText(2131099942);
                        break label3004;
                    }
                    this.tv.setText(2131099941);
                    break label3004;
                    this.tv.setTextColor(-65536);
                }
            } catch (Exception localException) {
                label6211:
                label6224:
                label6252:
                label6896:
                label6909:
                do
                    while (true) {
                        Date localDate2;
                        Object localObject;
                        String str3;
                        this.tv = new TextView(this);
                        this.tv.setTextColor(-65536);
                        if (Start.hebLayout)
                            this.tv.setText("לא ניתן להציג זמנים");
                        while (true) {
                            this.tv.setTypeface(this.tf);
                            this.tv.setTextSize(Start.fontSize);
                            this.tv.setGravity(17);
                            this.tv.setId(88);
                            this.rl.addView(this.tv);
                            break label3274;
                            localObject = Integer.valueOf(localDate2.getMinutes());
                            break;
                            this.tv.setText("שקיעה: " + str3);
                            break label3223;
                            this.tv.setText("Shkia: " + str3);
                            break label3223;
                            this.tv.setText("Zmanim cannot be displayed");
                        }
                        this.amida.setText(2131099766);
                        continue;
                        this.tv.setText("Avinu Malkenu");
                        continue;
                        if ((Start.nusach != 1) && (Start.nusach != 2))
                            continue;
                        if (Start.aseret) {
                            this.avinu.setText(2131099958);
                            continue;
                        }
                        if (!Start.anenu)
                            continue;
                        this.avinu.setText(2131099959);
                        continue;
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.titleColor);
                        this.tv.setText("תחנון");
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(16);
                        this.rl.addView(this.tv);
                        this.tahanun = new TextView(this);
                        this.tahanun.setTextColor(this.color);
                        if (Start.nusach == 0)
                            this.tahanun.setText(2131100385);
                        while (true) {
                            this.tahanun.setTypeface(this.tf);
                            this.tahanun.setTextSize(Start.fontSize);
                            if (Start.rtl_fix)
                                this.tahanun.setGravity(5);
                            this.tahanun.setFocusableInTouchMode(true);
                            this.tahanun.setId(17);
                            this.rl.addView(this.tahanun);
                            if (Start.nusach != 3)
                                break;
                            this.tv = new TextView(this);
                            this.tv.setTextColor(this.color);
                            this.tv.setText(2131100391);
                            this.tv.setTypeface(this.tf);
                            this.tv.setTextSize(Start.fontSize);
                            if (Start.rtl_fix)
                                this.tv.setGravity(5);
                            this.tv.setId(18);
                            this.rl.addView(this.tv);
                            this.tv = new TextView(this);
                            this.tv.setTextColor(this.color);
                            this.tv.setText(2131100392);
                            this.tv.setTypeface(this.tf);
                            this.tv.setTextSize(Start.fontSize);
                            if (Start.rtl_fix)
                                this.tv.setGravity(5);
                            this.tv.setId(19);
                            this.rl.addView(this.tv);
                            break;
                            if (Start.nusach == 1) {
                                this.tahanun.setText(2131100395);
                                continue;
                            }
                            if (Start.nusach == 3) {
                                this.tahanun.setText(2131100390);
                                continue;
                            }
                            if (Start.nusach != 2)
                                continue;
                            this.tahanun.setText(2131100388);
                        }
                        if ((Start.nusach == 1) || (Start.nusach == 3)) {
                            if (Start.aseret) {
                                this.tv.setText(2131099952);
                                continue;
                            }
                            this.tv.setText(2131099951);
                            continue;
                        }
                        if (Start.nusach != 2)
                            continue;
                        if (Start.aseret) {
                            this.tv.setText(2131099940);
                            continue;
                        }
                        this.tv.setText(2131099939);
                        continue;
                        this.tv.setText("Redemption");
                        continue;
                        this.tv.setText("Kaddish");
                        continue;
                        this.tv.setText("When God Will Return");
                        continue;
                        if (Start.globalDate.getDay() == 5) {
                            this.tv = new TextView(this);
                            this.tv.setTextColor(this.titleColor);
                            TextView localTextView2 = this.tv;
                            if (Start.hebLayout) ;
                            for (String str2 = "ה מלך"; ; str2 = "God has set his royalness") {
                                localTextView2.setText(str2);
                                this.tv.setTypeface(this.tf);
                                this.tv.setTextSize(Start.fontSize);
                                if (Start.rtl_fix)
                                    this.tv.setGravity(5);
                                this.tv.setId(20);
                                this.tv.setFocusableInTouchMode(true);
                                this.rl.addView(this.tv);
                                this.tv = new TextView(this);
                                this.tv.setTextColor(this.color);
                                this.tv.setText(2131100054);
                                this.tv.setTypeface(this.tf);
                                this.tv.setTextSize(Start.fontSize);
                                if (Start.rtl_fix)
                                    this.tv.setGravity(5);
                                this.tv.setId(21);
                                this.rl.addView(this.tv);
                                break;
                            }
                        }
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.titleColor);
                        this.tv.setBackgroundColor(-3355444);
                        TextView localTextView1 = this.tv;
                        String str1;
                        if (Start.hebLayout) {
                            str1 = "+ למנצח בצורת מנורה";
                            localTextView1.setText(str1);
                            this.tv.setTypeface(this.tf);
                            this.tv.setTextSize(Start.fontSize);
                            this.tv.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View paramView) {
                                    if (!Mincha.this.image.isShown()) {
                                        Mincha.this.temp1 = ((TextView) Mincha.this.findViewById(26));
                                        TextView localTextView2 = Mincha.this.temp1;
                                        if (Start.hebLayout) ;
                                        for (String str2 = "- למנצח בצורת מנורה"; ; str2 = "Lamnatzeach - Menora -") {
                                            localTextView2.setText(str2);
                                            Mincha.this.image.setVisibility(0);
                                            return;
                                        }
                                    }
                                    Mincha.this.temp1 = ((TextView) Mincha.this.findViewById(26));
                                    TextView localTextView1 = Mincha.this.temp1;
                                    if (Start.hebLayout) ;
                                    for (String str1 = "+ למנצח בצורת מנורה"; ; str1 = "Lamnatzeach - Menora +") {
                                        localTextView1.setText(str1);
                                        Mincha.this.image.setVisibility(8);
                                        break;
                                    }
                                }
                            });
                            if (Start.rtl_fix)
                                this.tv.setGravity(5);
                            this.tv.setId(26);
                            this.rl.addView(this.tv);
                            this.image = new ImageView(this);
                            if (Start.theme != 1)
                                break label7563;
                            this.image.setImageResource(2130837526);
                        }
                        while (true) {
                            this.image.setVisibility(8);
                            this.rl.addView(this.image);
                            this.tv = new TextView(this);
                            this.tv.setText("");
                            this.rl.addView(this.tv);
                            this.tv = new TextView(this);
                            this.tv.setTextColor(this.titleColor);
                            this.tv.setText("למנצח");
                            this.tv.setTypeface(this.tf);
                            this.tv.setTextSize(Start.fontSize);
                            if (Start.rtl_fix)
                                this.tv.setGravity(5);
                            this.tv.setId(20);
                            this.tv.setFocusableInTouchMode(true);
                            this.rl.addView(this.tv);
                            this.tv = new TextView(this);
                            this.tv.setTextColor(this.color);
                            this.tv.setText(2131100053);
                            this.tv.setTypeface(this.tf);
                            this.tv.setTextSize(Start.fontSize);
                            if (Start.rtl_fix)
                                this.tv.setGravity(5);
                            this.tv.setId(21);
                            this.tv.setFocusableInTouchMode(true);
                            this.rl.addView(this.tv);
                            break;
                            str1 = "Lamnatzeach - Menora +";
                            break label7202;
                            this.image.setImageResource(2130837525);
                        }
                        this.tv.setTextColor(this.titleColor);
                    }
                while ((Start.nusach != 1) && (Start.nusach != 2) && (Start.nusach != 3));
                label6319:
                label6371:
                if (Start.nusach != 1)
                    break label7826;
            }
        }
        label778:
        label1173:
        label3223:
        label6811:
        label7202:
        this.tv = new TextView(this);
        label1282:
        label2439:
        label5906:
        label6293:
        label6935:
        label7576:
        this.tv.setTextColor(this.titleColor);
        label5251:
        label5893:
        label6922:
        label7563:
        if (Start.hebLayout) {
            this.tv.setText("+למנצח");
            label7657:
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(20);
            this.tv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramView) {
                    TextView localTextView1 = (TextView) Mincha.this.findViewById(20);
                    TextView localTextView2 = (TextView) Mincha.this.findViewById(21);
                    if (localTextView2.getVisibility() == 8) {
                        localTextView2.setVisibility(0);
                        StringBuilder localStringBuilder2 = new StringBuilder("-");
                        if (Start.hebLayout) ;
                        for (String str2 = "למנצח"; ; str2 = "Lamnatseach") {
                            localTextView1.setText(str2);
                            return;
                        }
                    }
                    localTextView2.setVisibility(8);
                    StringBuilder localStringBuilder1 = new StringBuilder("+");
                    if (Start.hebLayout) ;
                    for (String str1 = "למנצח"; ; str1 = "Lamnatseach") {
                        localTextView1.setText(str1);
                        break;
                    }
                }
            });
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(2131100053);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(21);
            this.tv.setVisibility(8);
            this.rl.addView(this.tv);
            label7826:
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            this.tv.setText("עלינו לשבח");
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(20);
            this.rl.addView(this.tv);
            this.aleinu = new TextView(this);
            this.aleinu.setTextColor(this.color);
            if (Start.nusach != 1)
                break label8366;
            this.aleinu.setText(2131099955);
            label7955:
            this.aleinu.setTypeface(this.tf);
            this.aleinu.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.aleinu.setGravity(5);
            this.aleinu.setFocusableInTouchMode(true);
            this.aleinu.setId(21);
            this.rl.addView(this.aleinu);
            if (Start.mode != 1) {
                this.tv = new TextView(this);
                if (Start.mode != 2)
                    break label8406;
                this.tv.setTextColor(this.titleColor);
                label8056:
                this.tv.setText("קדיש יהא שלמא");
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(22);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if ((Start.nusach == 1) || (Start.nusach == 3)) {
                    if (!Start.aseret)
                        break label8420;
                    this.tv.setText(2131099950);
                }
                label8175:
                if (Start.nusach == 2) {
                    if (!Start.aseret)
                        break label8433;
                    this.tv.setText(2131099938);
                }
            }
        }
        while (true) {
            label5376:
            label6019:
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(23);
            this.rl.addView(this.tv);
            if (Start.nusach != 3)
                break;
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(2131100207);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(61);
            this.rl.addView(this.tv);
            break;
            this.tv.setText("+Lamnatseach");
            break label7657;
            label8366:
            if (Start.nusach == 2) {
                this.aleinu.setText(2131099943);
                break label7955;
            }
            if (Start.nusach != 3)
                break label7955;
            this.aleinu.setText(2131100206);
            break label7955;
            label8406:
            this.tv.setTextColor(this.titleColor);
            break label8056;
            label8420:
            this.tv.setText(2131099949);
            break label8175;
            label8433:
            this.tv.setText(2131099937);
        }
    }

    private void draw18() {
        String str82;
        label148:
        String str83;
        label244:
        String str109;
        label346:
        String str84;
        label466:
        label752:
        String str85;
        label854:
        String str86;
        label950:
        label1142:
        String str87;
        label1334:
        String str88;
        label1526:
        String str107;
        label1748:
        String str89;
        label2004:
        String str90;
        label2449:
        label2551:
        String str91;
        label2647:
        String str92;
        label2839:
        label2941:
        String str93;
        label3037:
        String str94;
        label3229:
        String str95;
        label3421:
        String str96;
        label3523:
        String str97;
        label3619:
        label3811:
        String str98;
        label3913:
        String str99;
        label4009:
        label4207:
        String str105;
        label4111:
        label4422:
        label4718:
        String str103;
        label4844:
        String str100;
        if (Start.nusach == 0) {
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView82 = this.tv;
            if (Start.hebLayout) {
                str82 = "ברכת אבות";
                localTextView82.setText(str82);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(44);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.aseret)
                    break label5727;
                this.tv.setText(2131099650);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(45);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView83 = this.tv;
                if (!Start.hebLayout)
                    break label5740;
                str83 = "ברכת גבורות";
                localTextView83.setText(str83);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(46);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.earlyWinter)
                    break label5748;
                this.tv.setText(2131099652);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(47);
                this.rl.addView(this.tv);
                if (Start.mode != 1) {
                    this.tvKdusha = new TextView(this);
                    this.tvKdusha.setTextColor(this.titleColor);
                    this.tvKdusha.setBackgroundColor(-3355444);
                    if (Start.mode != 2)
                        break label5788;
                    TextView localTextView109 = this.tvKdusha;
                    if (!Start.hebLayout)
                        break label5780;
                    str109 = "קדושה -";
                    localTextView109.setText(str109);
                    this.tvKdusha.setTypeface(this.tf);
                    this.tvKdusha.setTextSize(Start.fontSize);
                    this.tvKdusha.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View paramView) {
                            if (!Mincha.this.kdusha.isShown()) {
                                Mincha.this.kdusha.setVisibility(0);
                                TextView localTextView2 = Mincha.this.tvKdusha;
                                if (Start.hebLayout) ;
                                for (String str2 = "קדושה -"; ; str2 = "Kedushah -") {
                                    localTextView2.setText(str2);
                                    return;
                                }
                            }
                            Mincha.this.kdusha.setVisibility(8);
                            TextView localTextView1 = Mincha.this.tvKdusha;
                            if (Start.hebLayout) ;
                            for (String str1 = "קדושה +"; ; str1 = "Kedushah +") {
                                localTextView1.setText(str1);
                                break;
                            }
                        }
                    });
                    if (Start.rtl_fix)
                        this.tvKdusha.setGravity(5);
                    this.tvKdusha.setId(88);
                    this.rl.addView(this.tvKdusha);
                    this.kdusha = new TextView(this);
                    this.kdusha.setTextColor(this.color);
                    this.kdusha.setText(2131099654);
                    this.kdusha.setTypeface(this.tf);
                    this.kdusha.setTextSize(Start.fontSize);
                    if (Start.mode != 2)
                        this.kdusha.setVisibility(8);
                    if (Start.rtl_fix)
                        this.kdusha.setGravity(5);
                    this.kdusha.setFocusableInTouchMode(true);
                    this.kdusha.setId(89);
                    this.rl.addView(this.kdusha);
                    this.tv = new TextView(this);
                    this.tv.setText("");
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    this.rl.addView(this.tv);
                }
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView84 = this.tv;
                if (!Start.hebLayout)
                    break label5823;
                str84 = "ברכת הקדושה";
                localTextView84.setText(str84);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(48);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.aseret)
                    break label5831;
                this.tv.setText(2131099656);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(49);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView85 = this.tv;
                if (!Start.hebLayout)
                    break label5844;
                str85 = "ברכת הדעת";
                localTextView85.setText(str85);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(50);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099657);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(51);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView86 = this.tv;
                if (!Start.hebLayout)
                    break label5852;
                str86 = "ברכת התשובה";
                localTextView86.setText(str86);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(52);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099659);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(53);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView87 = this.tv;
                if (!Start.hebLayout)
                    break label5860;
                str87 = "ברכת הסליחה";
                localTextView87.setText(str87);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(54);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099660);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(55);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView88 = this.tv;
                if (!Start.hebLayout)
                    break label5868;
                str88 = "ברכת הגאולה";
                localTextView88.setText(str88);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(56);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099661);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(57);
                this.rl.addView(this.tv);
                if ((Start.anenu) && (Start.mode != 1)) {
                    this.tvAnenu = new TextView(this);
                    this.tvAnenu.setTextColor(this.titleColor);
                    this.tvAnenu.setBackgroundColor(-3355444);
                    if (Start.mode != 2)
                        break label5884;
                    TextView localTextView107 = this.tvAnenu;
                    if (!Start.hebLayout)
                        break label5876;
                    str107 = "עננו של שליח ציבור -";
                    localTextView107.setText(str107);
                    this.tvAnenu.setTypeface(this.tf);
                    this.tvAnenu.setTextSize(Start.fontSize);
                    this.tvAnenu.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View paramView) {
                            if (!Mincha.this.anenu.isShown()) {
                                Mincha.this.anenu.setVisibility(0);
                                TextView localTextView2 = Mincha.this.tvAnenu;
                                if (Start.hebLayout) ;
                                for (String str2 = "עננו של שליח ציבור -"; ; str2 = "Anenu by the Chazan -") {
                                    localTextView2.setText(str2);
                                    return;
                                }
                            }
                            Mincha.this.anenu.setVisibility(8);
                            TextView localTextView1 = Mincha.this.tvAnenu;
                            if (Start.hebLayout) ;
                            for (String str1 = "עננו של שליח ציבור +"; ; str1 = "Anenu by the Chazan +") {
                                localTextView1.setText(str1);
                                break;
                            }
                        }
                    });
                    if (Start.rtl_fix)
                        this.tvAnenu.setGravity(5);
                    this.tvAnenu.setId(28);
                    this.rl.addView(this.tvAnenu);
                    this.anenu = new TextView(this);
                    this.anenu.setTextColor(this.color);
                    this.anenu.setText(2131099662);
                    this.anenu.setTypeface(this.tf);
                    this.anenu.setTextSize(Start.fontSize);
                    if (Start.mode != 2)
                        this.anenu.setVisibility(8);
                    if (Start.rtl_fix)
                        this.anenu.setGravity(5);
                    this.anenu.setId(29);
                    this.rl.addView(this.anenu);
                    this.tv = new TextView(this);
                    this.tv.setText("");
                    this.rl.addView(this.tv);
                }
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView89 = this.tv;
                if (!Start.hebLayout)
                    break label5919;
                str89 = "ברכת הרפואה";
                localTextView89.setText(str89);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(58);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099663);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(59);
                this.rl.addView(this.tv);
                if (Start.sick.length() > 0) {
                    this.tv = new TextView(this);
                    this.tv.setText("");
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(Start.sickAll);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(60);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setText("");
                    this.rl.addView(this.tv);
                }
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099664);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(61);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView90 = this.tv;
                if (!Start.hebLayout)
                    break label5927;
                str90 = "ברכת השנים";
                localTextView90.setText(str90);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(62);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.winter)
                    break label5935;
                this.tv.setText(2131099666);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(63);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView91 = this.tv;
                if (!Start.hebLayout)
                    break label5948;
                str91 = "ברכת קיבוץ הגלויות";
                localTextView91.setText(str91);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(64);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099667);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(65);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView92 = this.tv;
                if (!Start.hebLayout)
                    break label5956;
                str92 = "ברכת המשפט";
                localTextView92.setText(str92);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(66);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.aseret)
                    break label5964;
                this.tv.setText(2131099669);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(67);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView93 = this.tv;
                if (!Start.hebLayout)
                    break label5977;
                str93 = "ברכת המינים";
                localTextView93.setText(str93);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(68);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099670);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(69);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView94 = this.tv;
                if (!Start.hebLayout)
                    break label5985;
                str94 = "ברכת הצדיקים";
                localTextView94.setText(str94);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(70);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099671);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(70);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView95 = this.tv;
                if (!Start.hebLayout)
                    break label5993;
                str95 = "ברכת בנין ירושלים";
                localTextView95.setText(str95);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(71);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.tishaBov)
                    break label6001;
                this.tv.setText(2131099673);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(72);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView96 = this.tv;
                if (!Start.hebLayout)
                    break label6014;
                str96 = "ברכת הישועה";
                localTextView96.setText(str96);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(73);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099674);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(74);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView97 = this.tv;
                if (!Start.hebLayout)
                    break label6022;
                str97 = "ברכת התפילה";
                localTextView97.setText(str97);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(75);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.anenu)
                    break label6030;
                this.tv.setText(2131099676);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(76);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView98 = this.tv;
                if (!Start.hebLayout)
                    break label6043;
                str98 = "ברכת העבודה";
                localTextView98.setText(str98);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(77);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.roshHodesh)
                    break label6051;
                this.tv.setText(2131099678);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(78);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView99 = this.tv;
                if (!Start.hebLayout)
                    break label6102;
                str99 = "ברכת ההודאה";
                localTextView99.setText(str99);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(79);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099681);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(80);
                this.rl.addView(this.tv);
                if (Start.mode == 0) {
                    this.tvModim = new TextView(this);
                    this.tvModim.setTextColor(this.titleColor);
                    this.tvModim.setBackgroundColor(-3355444);
                    if (Start.mode != 2)
                        break label6118;
                    TextView localTextView105 = this.tvModim;
                    if (!Start.hebLayout)
                        break label6110;
                    str105 = "מודים דרבנן -";
                    localTextView105.setText(str105);
                    this.tvModim.setTypeface(this.tf);
                    this.tvModim.setTextSize(Start.fontSize);
                    this.tvModim.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View paramView) {
                            if (!Mincha.this.modim.isShown()) {
                                Mincha.this.modim.setVisibility(0);
                                TextView localTextView2 = Mincha.this.tvModim;
                                if (Start.hebLayout) ;
                                for (String str2 = "מודים דרבנן -"; ; str2 = "Modim D'Rabbanan -") {
                                    localTextView2.setText(str2);
                                    return;
                                }
                            }
                            Mincha.this.modim.setVisibility(8);
                            TextView localTextView1 = Mincha.this.tvModim;
                            if (Start.hebLayout) ;
                            for (String str1 = "מודים דרבנן +"; ; str1 = "Modim D'Rabbanan +") {
                                localTextView1.setText(str1);
                                break;
                            }
                        }
                    });
                    if (Start.rtl_fix)
                        this.tvModim.setGravity(5);
                    this.tvModim.setId(30);
                    this.rl.addView(this.tvModim);
                    this.modim = new TextView(this);
                    this.modim.setTextColor(this.color);
                    this.modim.setText(2131099687);
                    this.modim.setTypeface(this.tf);
                    this.modim.setTextSize(Start.fontSize);
                    this.modim.setVisibility(8);
                    if (Start.rtl_fix)
                        this.modim.setGravity(5);
                    this.modim.setId(31);
                    this.modim.setFocusableInTouchMode(true);
                    this.rl.addView(this.modim);
                    this.tv = new TextView(this);
                    this.tv.setText("");
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    this.rl.addView(this.tv);
                }
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (((!Start.purim) || (Start.doMukaf)) && ((!Start.doMukaf) || (!Start.mukaf)))
                    break label6153;
                this.tv.setText(2131099685);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(81);
                this.rl.addView(this.tv);
                if ((Start.anenu) && (Start.mode != 1)) {
                    this.tvKohanim = new TextView(this);
                    this.tvKohanim.setTextColor(this.titleColor);
                    this.tvKohanim.setBackgroundColor(-3355444);
                    if (Start.mode != 2)
                        break label6212;
                    TextView localTextView103 = this.tvKohanim;
                    if (!Start.hebLayout)
                        break label6204;
                    str103 = "ברכת כהנים -";
                    localTextView103.setText(str103);
                    this.tvKohanim.setTypeface(this.tf);
                    this.tvKohanim.setTextSize(Start.fontSize);
                    this.tvKohanim.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View paramView) {
                            if (!Mincha.this.kohanim.isShown()) {
                                Mincha.this.kohanim.setVisibility(0);
                                TextView localTextView2 = Mincha.this.tvKohanim;
                                if (Start.hebLayout) ;
                                for (String str2 = "ברכת כהנים -"; ; str2 = "Priestly Blessing -") {
                                    localTextView2.setText(str2);
                                    return;
                                }
                            }
                            Mincha.this.kohanim.setVisibility(8);
                            TextView localTextView1 = Mincha.this.tvKohanim;
                            if (Start.hebLayout) ;
                            for (String str1 = "ברכת כהנים +"; ; str1 = "Priestly Blessing +") {
                                localTextView1.setText(str1);
                                break;
                            }
                        }
                    });
                    if (Start.rtl_fix)
                        this.tvKohanim.setGravity(5);
                    this.tvKohanim.setId(32);
                    this.rl.addView(this.tvKohanim);
                    this.kohanim = new TextView(this);
                    this.kohanim.setTextColor(this.color);
                    this.kohanim.setText(2131099688);
                    this.kohanim.setTypeface(this.tf);
                    this.kohanim.setTextSize(Start.fontSize);
                    if (Start.mode != 2)
                        this.kohanim.setVisibility(8);
                    if (Start.rtl_fix)
                        this.kohanim.setGravity(5);
                    this.kohanim.setId(33);
                    this.rl.addView(this.kohanim);
                    this.tv = new TextView(this);
                    this.tv.setText("");
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    this.rl.addView(this.tv);
                }
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView100 = this.tv;
                if (!Start.hebLayout)
                    break label6247;
                str100 = "ברכת השלום";
                label5122:
                localTextView100.setText(str100);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(82);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.aseret)
                    break label6255;
                this.tv.setText(2131099690);
                label5224:
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(83);
                this.rl.addView(this.tv);
                if (Start.mode != 2)
                    break label6281;
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                if (!Start.hebLayout)
                    break label6268;
                this.tv.setText("בקשות +");
                label5326:
                this.tv.setTypeface(this.tf);
                this.tv.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View paramView) {
                        TextView localTextView1 = (TextView) Mincha.this.findViewById(84);
                        TextView localTextView2 = (TextView) Mincha.this.findViewById(85);
                        TextView localTextView3 = (TextView) Mincha.this.findViewById(86);
                        TextView localTextView4 = (TextView) Mincha.this.findViewById(87);
                        if (!localTextView2.isShown()) {
                            localTextView2.setVisibility(0);
                            localTextView3.setVisibility(0);
                            localTextView4.setVisibility(0);
                            if (Start.hebLayout)
                                localTextView1.setText("בקשות -");
                        }
                        while (true) {
                            return;
                            localTextView1.setText("Pleas -");
                            continue;
                            localTextView2.setVisibility(8);
                            localTextView3.setVisibility(8);
                            localTextView4.setVisibility(8);
                            if (Start.hebLayout) {
                                localTextView1.setText("בקשות +");
                                continue;
                            }
                            localTextView1.setText("Pleas +");
                        }
                    }
                });
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(84);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099691);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                if (Start.mode == 2)
                    this.tv.setVisibility(8);
                this.tv.setId(85);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(Start.pasuk);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                if (Start.mode == 2)
                    this.tv.setVisibility(8);
                this.tv.setId(86);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.aseret)
                    break label6395;
                this.tv.setText(2131099693);
                label5646:
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                if (Start.mode == 2)
                    this.tv.setVisibility(8);
                this.tv.setId(87);
                this.rl.addView(this.tv);
            }
        }
        label5727:
        label5740:
        label5748:
        label5876:
        label5884:
        label6014:
        label7551:
        do {
            return;
            str82 = "Patriarchs";
            break;
            this.tv.setText(2131099649);
            break label148;
            str83 = "God's Might";
            break label244;
            if (Start.aseret) {
                this.tv.setText(2131099653);
                break label346;
            }
            this.tv.setText(2131099651);
            break label346;
            str109 = "Kedushah -";
            break label466;
            TextView localTextView108 = this.tvKdusha;
            if (Start.hebLayout) ;
            for (String str108 = "קדושה +"; ; str108 = "Kedushah +") {
                localTextView108.setText(str108);
                break;
            }
            str84 = "Holiness of God's Name";
            break label752;
            this.tv.setText(2131099655);
            break label854;
            str85 = "Insight";
            break label950;
            str86 = "Repentance";
            break label1142;
            str87 = "Forgiveness";
            break label1334;
            str88 = "Redemption";
            break label1526;
            str107 = "Anenu by the Chazan";
            break label1748;
            TextView localTextView106 = this.tvAnenu;
            if (Start.hebLayout) ;
            for (String str106 = "עננו של שליח ציבור +"; ; str106 = "Anenu by the Chazan +") {
                localTextView106.setText(str106);
                break;
            }
            str89 = "Health and Healing";
            break label2004;
            str90 = "Year of Prosperity";
            break label2449;
            this.tv.setText(2131099665);
            break label2551;
            str91 = "Ingathering of the Exiles";
            break label2647;
            str92 = "Justice";
            break label2839;
            this.tv.setText(2131099668);
            break label2941;
            str93 = "Against Heretics";
            break label3037;
            str94 = "The Righteous";
            break label3229;
            str95 = "Rebuilding Jerusalem";
            break label3421;
            this.tv.setText(2131099672);
            break label3523;
            str96 = "Davidic Reign";
            break label3619;
            str97 = "Acceptance of Prayer";
            break label3811;
            this.tv.setText(2131099675);
            break label3913;
            str98 = "Temple Service";
            break label4009;
            if (Start.passover) {
                this.tv.setText(2131099680);
                break label4111;
            }
            if (Start.sukot) {
                this.tv.setText(2131099679);
                break label4111;
            }
            this.tv.setText(2131099677);
            break label4111;
            str99 = "Thanksgiving";
            break label4207;
            str105 = "Modim D'Rabbanan -";
            break label4422;
            TextView localTextView104 = this.tvModim;
            if (Start.hebLayout) ;
            for (String str104 = "מודים דרבנן +"; ; str104 = "Modim D'Rabbanan +") {
                localTextView104.setText(str104);
                break;
            }
            if (Start.hanuca) {
                this.tv.setText(2131099684);
                break label4718;
            }
            if (Start.aseret) {
                this.tv.setText(2131099683);
                break label4718;
            }
            this.tv.setText(2131099682);
            break label4718;
            str103 = "Priestly Blessing -";
            break label4844;
            TextView localTextView102 = this.tvKohanim;
            if (Start.hebLayout) ;
            for (String str102 = "ברכת כהנים +"; ; str102 = "Priestly Blessing +") {
                localTextView102.setText(str102);
                break;
            }
            str100 = "Peace";
            break label5122;
            this.tv.setText(2131099689);
            break label5224;
            this.tv.setText("Pleas +");
            break label5326;
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView101 = this.tv;
            if (Start.hebLayout) ;
            for (String str101 = "בקשות"; ; str101 = "Pleas") {
                localTextView101.setText(str101);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(84);
                this.rl.addView(this.tv);
                break;
            }
            this.tv.setText(2131099692);
            break label5646;
            if (Start.nusach == 1) {
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView55 = this.tv;
                String str55;
                String str56;
                String str81;
                String str57;
                String str58;
                String str59;
                String str60;
                String str61;
                String str79;
                String str62;
                String str63;
                String str64;
                String str65;
                String str66;
                String str67;
                String str68;
                String str69;
                String str70;
                String str71;
                String str72;
                String str77;
                String str76;
                String str73;
                if (Start.hebLayout) {
                    str55 = "ברכת אבות";
                    localTextView55.setText(str55);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(44);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    if (!Start.aseret)
                        break label12139;
                    this.tv.setText(2131099768);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(45);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView56 = this.tv;
                    if (!Start.hebLayout)
                        break label12152;
                    str56 = "ברכת גבורות";
                    localTextView56.setText(str56);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(46);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    if (!Start.aseret)
                        break label12160;
                    this.tv.setText(2131099771);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(47);
                    this.rl.addView(this.tv);
                    if (Start.mode != 1) {
                        this.tvKdusha = new TextView(this);
                        this.tvKdusha.setTextColor(this.titleColor);
                        this.tvKdusha.setBackgroundColor(-3355444);
                        if (Start.mode != 2)
                            break label12200;
                        TextView localTextView81 = this.tvKdusha;
                        if (!Start.hebLayout)
                            break label12192;
                        str81 = "קדושה -";
                        localTextView81.setText(str81);
                        this.tvKdusha.setTypeface(this.tf);
                        this.tvKdusha.setTextSize(Start.fontSize);
                        this.tvKdusha.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View paramView) {
                                if (!Mincha.this.kdusha.isShown()) {
                                    Mincha.this.kdusha.setVisibility(0);
                                    TextView localTextView2 = Mincha.this.tvKdusha;
                                    if (Start.hebLayout) ;
                                    for (String str2 = "קדושה -"; ; str2 = "Kedusha -") {
                                        localTextView2.setText(str2);
                                        return;
                                    }
                                }
                                Mincha.this.kdusha.setVisibility(8);
                                TextView localTextView1 = Mincha.this.tvKdusha;
                                if (Start.hebLayout) ;
                                for (String str1 = "קדושה +"; ; str1 = "Kedusha +") {
                                    localTextView1.setText(str1);
                                    break;
                                }
                            }
                        });
                        if (Start.rtl_fix)
                            this.tvKdusha.setGravity(5);
                        this.tvKdusha.setId(26);
                        this.rl.addView(this.tvKdusha);
                        this.kdusha = new TextView(this);
                        this.kdusha.setTextColor(this.color);
                        this.kdusha.setText(2131099772);
                        this.kdusha.setTypeface(this.tf);
                        this.kdusha.setTextSize(Start.fontSize);
                        if (Start.mode != 2)
                            this.kdusha.setVisibility(8);
                        if (Start.rtl_fix)
                            this.kdusha.setGravity(5);
                        this.kdusha.setId(27);
                        this.kdusha.setFocusableInTouchMode(true);
                        this.rl.addView(this.kdusha);
                        this.tv = new TextView(this);
                        this.tv.setText("");
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        this.rl.addView(this.tv);
                    }
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView57 = this.tv;
                    if (!Start.hebLayout)
                        break label12235;
                    str57 = "ברכת הקדושה";
                    localTextView57.setText(str57);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(48);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    if (!Start.aseret)
                        break label12243;
                    this.tv.setText(2131099774);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(49);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView58 = this.tv;
                    if (!Start.hebLayout)
                        break label12256;
                    str58 = "ברכת הדעת";
                    localTextView58.setText(str58);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(50);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131099775);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(51);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView59 = this.tv;
                    if (!Start.hebLayout)
                        break label12264;
                    str59 = "ברכת התשובה";
                    localTextView59.setText(str59);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(52);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131099777);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(53);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView60 = this.tv;
                    if (!Start.hebLayout)
                        break label12272;
                    str60 = "ברכת הסליחה";
                    localTextView60.setText(str60);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(54);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131099778);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(55);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView61 = this.tv;
                    if (!Start.hebLayout)
                        break label12280;
                    str61 = "ברכת הגאולה";
                    localTextView61.setText(str61);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(56);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131099779);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(57);
                    this.rl.addView(this.tv);
                    if ((Start.anenu) && (Start.mode != 1)) {
                        this.tvAnenu = new TextView(this);
                        this.tvAnenu.setTextColor(this.titleColor);
                        this.tvAnenu.setBackgroundColor(-3355444);
                        if (Start.mode != 2)
                            break label12296;
                        TextView localTextView79 = this.tvAnenu;
                        if (!Start.hebLayout)
                            break label12288;
                        str79 = "עננו של שליח ציבור +";
                        localTextView79.setText(str79);
                        this.tvAnenu.setTypeface(this.tf);
                        this.tvAnenu.setTextSize(Start.fontSize);
                        this.tvAnenu.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View paramView) {
                                if (!Mincha.this.anenu.isShown()) {
                                    Mincha.this.anenu.setVisibility(0);
                                    TextView localTextView2 = Mincha.this.tvAnenu;
                                    if (Start.hebLayout) ;
                                    for (String str2 = "עננו של שליח ציבור -"; ; str2 = "Anenu by the Chazan -") {
                                        localTextView2.setText(str2);
                                        return;
                                    }
                                }
                                Mincha.this.anenu.setVisibility(8);
                                TextView localTextView1 = Mincha.this.tvAnenu;
                                if (Start.hebLayout) ;
                                for (String str1 = "עננו של שליח ציבור +"; ; str1 = "Anenu by the Chazan +") {
                                    localTextView1.setText(str1);
                                    break;
                                }
                            }
                        });
                        if (Start.rtl_fix)
                            this.tvAnenu.setGravity(5);
                        this.tvAnenu.setId(28);
                        this.rl.addView(this.tvAnenu);
                        this.anenu = new TextView(this);
                        this.anenu.setTextColor(this.color);
                        this.anenu.setText(2131099780);
                        this.anenu.setTypeface(this.tf);
                        this.anenu.setTextSize(Start.fontSize);
                        if (Start.mode != 2)
                            this.anenu.setVisibility(8);
                        if (Start.rtl_fix)
                            this.anenu.setGravity(5);
                        this.anenu.setId(29);
                        this.rl.addView(this.anenu);
                        this.tv = new TextView(this);
                        this.tv.setText("");
                        this.rl.addView(this.tv);
                    }
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView62 = this.tv;
                    if (!Start.hebLayout)
                        break label12331;
                    str62 = "ברכת הרפואה";
                    localTextView62.setText(str62);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(58);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131099781);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(59);
                    this.rl.addView(this.tv);
                    if (Start.sick.length() > 0) {
                        this.tv = new TextView(this);
                        this.tv.setText("");
                        this.rl.addView(this.tv);
                        this.tv = new TextView(this);
                        this.tv.setTextColor(this.color);
                        this.tv.setText(Start.sickAll);
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(60);
                        this.rl.addView(this.tv);
                        this.tv = new TextView(this);
                        this.tv.setText("");
                        this.rl.addView(this.tv);
                    }
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131099782);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(61);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView63 = this.tv;
                    if (!Start.hebLayout)
                        break label12339;
                    str63 = "ברכת השנים";
                    localTextView63.setText(str63);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(62);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    if (!Start.winter)
                        break label12347;
                    this.tv.setText(2131099784);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(63);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView64 = this.tv;
                    if (!Start.hebLayout)
                        break label12360;
                    str64 = "ברכת קיבוץ הגלויות";
                    localTextView64.setText(str64);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(64);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131099785);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(65);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView65 = this.tv;
                    if (!Start.hebLayout)
                        break label12368;
                    str65 = "ברכת המשפט";
                    localTextView65.setText(str65);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(66);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    if (!Start.aseret)
                        break label12376;
                    this.tv.setText(2131099787);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(67);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView66 = this.tv;
                    if (!Start.hebLayout)
                        break label12389;
                    str66 = "ברכת המינים";
                    localTextView66.setText(str66);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(68);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131099788);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(69);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView67 = this.tv;
                    if (!Start.hebLayout)
                        break label12397;
                    str67 = "ברכת הצדיקים";
                    localTextView67.setText(str67);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(70);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131099789);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(71);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView68 = this.tv;
                    if (!Start.hebLayout)
                        break label12405;
                    str68 = "ברכת בנין ירושלים";
                    localTextView68.setText(str68);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(72);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    if (!Start.tishaBov)
                        break label12413;
                    this.tv.setText(2131099791);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(73);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView69 = this.tv;
                    if (!Start.hebLayout)
                        break label12426;
                    str69 = "ברכת הישועה";
                    localTextView69.setText(str69);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(74);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131099792);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(75);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView70 = this.tv;
                    if (!Start.hebLayout)
                        break label12434;
                    str70 = "ברכת התפילה";
                    localTextView70.setText(str70);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(76);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    if (!Start.anenu)
                        break label12442;
                    this.tv.setText(2131099794);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(77);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView71 = this.tv;
                    if (!Start.hebLayout)
                        break label12455;
                    str71 = "ברכת העבודה";
                    localTextView71.setText(str71);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(78);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    if (!Start.roshHodesh)
                        break label12463;
                    this.tv.setText(2131099796);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(79);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView72 = this.tv;
                    if (!Start.hebLayout)
                        break label12514;
                    str72 = "ברכת ההודאה";
                    localTextView72.setText(str72);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(80);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131099799);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(81);
                    this.rl.addView(this.tv);
                    if (Start.mode == 0) {
                        this.tvModim = new TextView(this);
                        this.tvModim.setTextColor(this.titleColor);
                        this.tvModim.setBackgroundColor(-3355444);
                        TextView localTextView77 = this.tvModim;
                        if (!Start.hebLayout)
                            break label12522;
                        str77 = "מודים דרבנן +";
                        localTextView77.setText(str77);
                        this.tvModim.setTypeface(this.tf);
                        this.tvModim.setTextSize(Start.fontSize);
                        this.tvModim.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View paramView) {
                                if (!Mincha.this.modim.isShown()) {
                                    Mincha.this.modim.setVisibility(0);
                                    TextView localTextView2 = Mincha.this.tvModim;
                                    if (Start.hebLayout) ;
                                    for (String str2 = "מודים דרבנן -"; ; str2 = "Modim D'Rabbanan -") {
                                        localTextView2.setText(str2);
                                        return;
                                    }
                                }
                                Mincha.this.modim.setVisibility(8);
                                TextView localTextView1 = Mincha.this.tvModim;
                                if (Start.hebLayout) ;
                                for (String str1 = "מודים דרבנן +"; ; str1 = "Modim D'Rabbanan +") {
                                    localTextView1.setText(str1);
                                    break;
                                }
                            }
                        });
                        if (Start.rtl_fix)
                            this.tvModim.setGravity(5);
                        this.tvModim.setId(30);
                        this.rl.addView(this.tvModim);
                        this.modim = new TextView(this);
                        this.modim.setTextColor(this.color);
                        this.modim.setText(2131099800);
                        this.modim.setTypeface(this.tf);
                        this.modim.setTextSize(Start.fontSize);
                        this.modim.setVisibility(8);
                        if (Start.rtl_fix)
                            this.modim.setGravity(5);
                        this.modim.setId(31);
                        this.modim.setFocusableInTouchMode(true);
                        this.rl.addView(this.modim);
                        this.tv = new TextView(this);
                        this.tv.setText("");
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        this.rl.addView(this.tv);
                    }
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    if (((!Start.purim) || (Start.doMukaf)) && ((!Start.doMukaf) || (!Start.mukaf)))
                        break label12530;
                    this.tv.setText(2131099804);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(82);
                    this.rl.addView(this.tv);
                    if (Start.mode != 1) {
                        this.tvKohanim = new TextView(this);
                        this.tvKohanim.setTextColor(this.titleColor);
                        this.tvKohanim.setBackgroundColor(-3355444);
                        if (Start.mode != 2)
                            break label12589;
                        TextView localTextView76 = this.tvKohanim;
                        if (!Start.hebLayout)
                            break label12581;
                        str76 = "ברכת כהנים +";
                        localTextView76.setText(str76);
                        this.tvKohanim.setTypeface(this.tf);
                        this.tvKohanim.setTextSize(Start.fontSize);
                        if (!Start.anenu)
                            break label12624;
                        this.tvKohanim.setVisibility(0);
                        this.tvKohanim.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View paramView) {
                                if (!Mincha.this.kohanim.isShown()) {
                                    Mincha.this.kohanim.setVisibility(0);
                                    TextView localTextView2 = Mincha.this.tvKohanim;
                                    if (Start.hebLayout) ;
                                    for (String str2 = "ברכת כהנים -"; ; str2 = "Priestly Blessing -") {
                                        localTextView2.setText(str2);
                                        return;
                                    }
                                }
                                Mincha.this.kohanim.setVisibility(8);
                                TextView localTextView1 = Mincha.this.tvKohanim;
                                if (Start.hebLayout) ;
                                for (String str1 = "ברכת כהנים +"; ; str1 = "Priestly Blessing +") {
                                    localTextView1.setText(str1);
                                    break;
                                }
                            }
                        });
                        if (Start.rtl_fix)
                            this.tvKohanim.setGravity(5);
                        this.tvKohanim.setId(32);
                        this.rl.addView(this.tvKohanim);
                        this.kohanim = new TextView(this);
                        this.kohanim.setTextColor(this.color);
                        this.kohanim.setText(2131099806);
                        this.kohanim.setTypeface(this.tf);
                        this.kohanim.setTextSize(Start.fontSize);
                        if (Start.mode != 2)
                            this.kohanim.setVisibility(8);
                        if (Start.rtl_fix)
                            this.kohanim.setGravity(5);
                        this.kohanim.setId(33);
                        this.rl.addView(this.kohanim);
                        this.tv = new TextView(this);
                        this.tv.setText("");
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        this.rl.addView(this.tv);
                    }
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView73 = this.tv;
                    if (!Start.hebLayout)
                        break label12636;
                    str73 = "ברכת השלום";
                    localTextView73.setText(str73);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(83);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    if (!Start.aseret)
                        break label12644;
                    this.tv.setText(2131099808);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(84);
                    this.rl.addView(this.tv);
                    if (Start.mode != 2)
                        break label12670;
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    if (!Start.hebLayout)
                        break label12657;
                    this.tv.setText("בקשות +");
                    this.tv.setTypeface(this.tf);
                    this.tv.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View paramView) {
                            TextView localTextView1 = (TextView) Mincha.this.findViewById(85);
                            TextView localTextView2 = (TextView) Mincha.this.findViewById(86);
                            TextView localTextView3 = (TextView) Mincha.this.findViewById(87);
                            TextView localTextView4 = (TextView) Mincha.this.findViewById(88);
                            if (!localTextView2.isShown()) {
                                localTextView2.setVisibility(0);
                                localTextView3.setVisibility(0);
                                localTextView4.setVisibility(0);
                                if (Start.hebLayout)
                                    localTextView1.setText("בקשות -");
                            }
                            while (true) {
                                return;
                                localTextView1.setText("Pleas -");
                                continue;
                                localTextView2.setVisibility(8);
                                localTextView3.setVisibility(8);
                                localTextView4.setVisibility(8);
                                if (Start.hebLayout) {
                                    localTextView1.setText("בקשות +");
                                    continue;
                                }
                                localTextView1.setText("Pleas +");
                            }
                        }
                    });
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(85);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(2131099809);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    if (Start.mode == 2)
                        this.tv.setVisibility(8);
                    this.tv.setId(86);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(Start.pasuk);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    if (Start.mode == 2)
                        this.tv.setVisibility(8);
                    this.tv.setId(87);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    if (!Start.aseret)
                        break label12784;
                    this.tv.setText(2131099811);
                }
                while (true) {
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    if (Start.mode == 2)
                        this.tv.setVisibility(8);
                    this.tv.setId(88);
                    this.rl.addView(this.tv);
                    break;
                    str55 = "Patriarchs";
                    break label6455;
                    this.tv.setText(2131099767);
                    break label6557;
                    str56 = "God's Might";
                    break label6653;
                    if (Start.earlyWinter) {
                        this.tv.setText(2131099770);
                        break label6755;
                    }
                    this.tv.setText(2131099769);
                    break label6755;
                    str81 = "Kedusha -";
                    break label6875;
                    TextView localTextView80 = this.tvKdusha;
                    if (Start.hebLayout) ;
                    for (String str80 = "קדושה +"; ; str80 = "Kedusha +") {
                        localTextView80.setText(str80);
                        break;
                    }
                    str57 = "Holiness of God's Name";
                    break label7161;
                    this.tv.setText(2131099773);
                    break label7263;
                    str58 = "Insight";
                    break label7359;
                    str59 = "Repentance";
                    break label7551;
                    str60 = "Forgiveness";
                    break label7743;
                    str61 = "Redemption";
                    break label7935;
                    str79 = "Anenu by the Chazan +";
                    break label8157;
                    TextView localTextView78 = this.tvAnenu;
                    if (Start.hebLayout) ;
                    for (String str78 = "עננו של שליח ציבור -"; ; str78 = "Anenu by the Chazan -") {
                        localTextView78.setText(str78);
                        break;
                    }
                    str62 = "Health and Healing";
                    break label8413;
                    str63 = "Year of Prosperity";
                    break label8858;
                    this.tv.setText(2131099783);
                    break label8960;
                    str64 = "Ingathering of the Exiles";
                    break label9056;
                    str65 = "Justice";
                    break label9248;
                    this.tv.setText(2131099786);
                    break label9350;
                    str66 = "Against Heretics";
                    break label9446;
                    str67 = "The Righteous";
                    break label9638;
                    str68 = "Rebuilding Jerusalem";
                    break label9830;
                    this.tv.setText(2131099790);
                    break label9932;
                    str69 = "Davidic Reign";
                    break label10028;
                    str70 = "Acceptance of Prayer";
                    break label10220;
                    this.tv.setText(2131099793);
                    break label10322;
                    str71 = "Temple Service";
                    break label10418;
                    if (Start.passover) {
                        this.tv.setText(2131099798);
                        break label10520;
                    }
                    if (Start.sukot) {
                        this.tv.setText(2131099797);
                        break label10520;
                    }
                    this.tv.setText(2131099795);
                    break label10520;
                    str72 = "Thanksgiving";
                    break label10616;
                    str77 = "Modim D'Rabbanan +";
                    break label10824;
                    if (Start.hanuca) {
                        this.tv.setText(2131099803);
                        break label11120;
                    }
                    if (Start.aseret) {
                        this.tv.setText(2131099802);
                        break label11120;
                    }
                    this.tv.setText(2131099801);
                    break label11120;
                    str76 = "Priestly Blessing +";
                    break label11240;
                    TextView localTextView75 = this.tvKohanim;
                    if (Start.hebLayout) ;
                    for (String str75 = "ברכת כהנים -"; ; str75 = "Priestly Blessing -") {
                        localTextView75.setText(str75);
                        break;
                    }
                    this.tvKohanim.setVisibility(8);
                    break label11283;
                    str73 = "Peace";
                    break label11532;
                    this.tv.setText(2131099807);
                    break label11634;
                    this.tv.setText("Pleas +");
                    break label11736;
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.titleColor);
                    TextView localTextView74 = this.tv;
                    if (Start.hebLayout) ;
                    for (String str74 = "בקשות"; ; str74 = "Pleas") {
                        localTextView74.setText(str74);
                        this.tv.setTypeface(this.tf);
                        this.tv.setTextSize(Start.fontSize);
                        if (Start.rtl_fix)
                            this.tv.setGravity(5);
                        this.tv.setId(85);
                        this.rl.addView(this.tv);
                        break;
                    }
                    this.tv.setText(2131099810);
                }
            }
            if (Start.nusach != 2)
                continue;
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView28 = this.tv;
            String str28;
            String str29;
            String str54;
            String str30;
            String str31;
            String str32;
            String str33;
            String str34;
            String str52;
            String str35;
            String str36;
            String str37;
            String str38;
            String str39;
            String str40;
            String str41;
            String str42;
            String str43;
            String str44;
            String str45;
            String str50;
            String str49;
            String str46;
            if (Start.hebLayout) {
                str28 = "ברכת אבות";
                localTextView28.setText(str28);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(44);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.aseret)
                    break label18533;
                this.tv.setText(2131099768);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(45);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView29 = this.tv;
                if (!Start.hebLayout)
                    break label18546;
                str29 = "ברכת גבורות";
                localTextView29.setText(str29);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(46);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.aseret)
                    break label18554;
                this.tv.setText(2131099771);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(47);
                this.rl.addView(this.tv);
                if (Start.mode != 1) {
                    this.tvKdusha = new TextView(this);
                    this.tvKdusha.setTextColor(this.titleColor);
                    this.tvKdusha.setBackgroundColor(-3355444);
                    if (Start.mode != 2)
                        break label18594;
                    TextView localTextView54 = this.tvKdusha;
                    if (!Start.hebLayout)
                        break label18586;
                    str54 = "קדושה -";
                    localTextView54.setText(str54);
                    this.tvKdusha.setTypeface(this.tf);
                    this.tvKdusha.setTextSize(Start.fontSize);
                    this.tvKdusha.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View paramView) {
                            if (!Mincha.this.kdusha.isShown()) {
                                Mincha.this.kdusha.setVisibility(0);
                                Mincha.this.tvKdushAshkenaz1.setVisibility(8);
                                Mincha.this.tvKdushAshkenaz2.setVisibility(8);
                                TextView localTextView2 = Mincha.this.tvKdusha;
                                if (Start.hebLayout) ;
                                for (String str2 = "קדושה -"; ; str2 = "Kedusha -") {
                                    localTextView2.setText(str2);
                                    return;
                                }
                            }
                            Mincha.this.kdusha.setVisibility(8);
                            Mincha.this.tvKdushAshkenaz1.setVisibility(0);
                            Mincha.this.tvKdushAshkenaz2.setVisibility(0);
                            TextView localTextView1 = Mincha.this.tvKdusha;
                            if (Start.hebLayout) ;
                            for (String str1 = "קדושה +"; ; str1 = "Kedusha +") {
                                localTextView1.setText(str1);
                                break;
                            }
                        }
                    });
                    if (Start.rtl_fix)
                        this.tvKdusha.setGravity(5);
                    this.tvKdusha.setId(26);
                    this.rl.addView(this.tvKdusha);
                    this.kdusha = new TextView(this);
                    this.kdusha.setTextColor(this.color);
                    if (!Start.aseret)
                        break label18629;
                    this.kdusha.setText(2131099695);
                    this.kdusha.setTypeface(this.tf);
                    this.kdusha.setTextSize(Start.fontSize);
                    if (Start.mode != 2)
                        this.kdusha.setVisibility(8);
                    if (Start.rtl_fix)
                        this.kdusha.setGravity(5);
                    this.kdusha.setId(27);
                    this.kdusha.setFocusableInTouchMode(true);
                    this.rl.addView(this.kdusha);
                    this.tv = new TextView(this);
                    this.tv.setText("");
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    this.rl.addView(this.tv);
                }
                this.tvKdushAshkenaz1 = new TextView(this);
                this.tvKdushAshkenaz1.setTextColor(this.titleColor);
                TextView localTextView30 = this.tvKdushAshkenaz1;
                if (!Start.hebLayout)
                    break label18642;
                str30 = "ברכת הקדושה";
                localTextView30.setText(str30);
                this.tvKdushAshkenaz1.setTypeface(this.tf);
                this.tvKdushAshkenaz1.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tvKdushAshkenaz1.setGravity(5);
                this.tvKdushAshkenaz1.setId(48);
                this.rl.addView(this.tvKdushAshkenaz1);
                this.tvKdushAshkenaz2 = new TextView(this);
                this.tvKdushAshkenaz2.setTextColor(this.color);
                if (!Start.aseret)
                    break label18650;
                this.tvKdushAshkenaz2.setText(2131099697);
                this.tvKdushAshkenaz2.setTypeface(this.tf);
                this.tvKdushAshkenaz2.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tvKdushAshkenaz2.setGravity(5);
                this.tvKdushAshkenaz2.setId(49);
                this.rl.addView(this.tvKdushAshkenaz2);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView31 = this.tv;
                if (!Start.hebLayout)
                    break label18663;
                str31 = "ברכת הדעת";
                localTextView31.setText(str31);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(50);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099698);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(51);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView32 = this.tv;
                if (!Start.hebLayout)
                    break label18671;
                str32 = "ברכת התשובה";
                localTextView32.setText(str32);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(52);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099777);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(53);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView33 = this.tv;
                if (!Start.hebLayout)
                    break label18679;
                str33 = "ברכת הסליחה";
                localTextView33.setText(str33);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(54);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099700);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(55);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView34 = this.tv;
                if (!Start.hebLayout)
                    break label18687;
                str34 = "ברכת הגאולה";
                localTextView34.setText(str34);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(56);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099701);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(57);
                this.rl.addView(this.tv);
                if ((Start.anenu) && (Start.mode != 1)) {
                    this.tvAnenu = new TextView(this);
                    this.tvAnenu.setTextColor(this.titleColor);
                    this.tvAnenu.setBackgroundColor(-3355444);
                    if (Start.mode != 2)
                        break label18703;
                    TextView localTextView52 = this.tvAnenu;
                    if (!Start.hebLayout)
                        break label18695;
                    str52 = "עננו של שליח ציבור -";
                    localTextView52.setText(str52);
                    this.tvAnenu.setTypeface(this.tf);
                    this.tvAnenu.setTextSize(Start.fontSize);
                    this.tvAnenu.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View paramView) {
                            if (!Mincha.this.anenu.isShown()) {
                                Mincha.this.anenu.setVisibility(0);
                                TextView localTextView2 = Mincha.this.tvAnenu;
                                if (Start.hebLayout) ;
                                for (String str2 = "עננו של שליח ציבור -"; ; str2 = "Anenu by the Chazan -") {
                                    localTextView2.setText(str2);
                                    return;
                                }
                            }
                            Mincha.this.anenu.setVisibility(8);
                            TextView localTextView1 = Mincha.this.tvAnenu;
                            if (Start.hebLayout) ;
                            for (String str1 = "עננו של שליח ציבור +"; ; str1 = "Anenu by the Chazan +") {
                                localTextView1.setText(str1);
                                break;
                            }
                        }
                    });
                    if (Start.rtl_fix)
                        this.tvAnenu.setGravity(5);
                    this.tvAnenu.setId(28);
                    this.rl.addView(this.tvAnenu);
                    this.anenu = new TextView(this);
                    this.anenu.setTextColor(this.color);
                    this.anenu.setText(2131099702);
                    this.anenu.setTypeface(this.tf);
                    this.anenu.setTextSize(Start.fontSize);
                    if (Start.mode != 2)
                        this.anenu.setVisibility(8);
                    if (Start.rtl_fix)
                        this.anenu.setGravity(5);
                    this.anenu.setId(29);
                    this.rl.addView(this.anenu);
                    this.tv = new TextView(this);
                    this.tv.setText("");
                    this.rl.addView(this.tv);
                }
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView35 = this.tv;
                if (!Start.hebLayout)
                    break label18738;
                str35 = "ברכת הרפואה";
                localTextView35.setText(str35);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(58);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099703);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(59);
                this.rl.addView(this.tv);
                if (Start.sick.length() > 0) {
                    this.tv = new TextView(this);
                    this.tv.setText("");
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setTextColor(this.color);
                    this.tv.setText(Start.sickAll);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(60);
                    this.rl.addView(this.tv);
                    this.tv = new TextView(this);
                    this.tv.setText("");
                    this.rl.addView(this.tv);
                }
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099782);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(61);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView36 = this.tv;
                if (!Start.hebLayout)
                    break label18746;
                str36 = "ברכת השנים";
                localTextView36.setText(str36);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(62);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.winter)
                    break label18754;
                this.tv.setText(2131099705);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(63);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView37 = this.tv;
                if (!Start.hebLayout)
                    break label18767;
                str37 = "ברכת קיבוץ הגלויות";
                localTextView37.setText(str37);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(64);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099706);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(65);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView38 = this.tv;
                if (!Start.hebLayout)
                    break label18775;
                str38 = "ברכת המשפט";
                localTextView38.setText(str38);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(66);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.aseret)
                    break label18783;
                this.tv.setText(2131099708);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(67);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView39 = this.tv;
                if (!Start.hebLayout)
                    break label18796;
                str39 = "ברכת המינים";
                localTextView39.setText(str39);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(68);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099709);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(69);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView40 = this.tv;
                if (!Start.hebLayout)
                    break label18804;
                str40 = "ברכת הצדיקים";
                localTextView40.setText(str40);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(70);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099710);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(71);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView41 = this.tv;
                if (!Start.hebLayout)
                    break label18812;
                str41 = "ברכת בנין ירושלים";
                localTextView41.setText(str41);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(72);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.tishaBov)
                    break label18820;
                this.tv.setText(2131099791);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(73);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView42 = this.tv;
                if (!Start.hebLayout)
                    break label18833;
                str42 = "ברכת הישועה";
                localTextView42.setText(str42);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(74);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099792);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(75);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView43 = this.tv;
                if (!Start.hebLayout)
                    break label18841;
                str43 = "ברכת התפילה";
                localTextView43.setText(str43);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(76);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.anenu)
                    break label18849;
                this.tv.setText(2131099713);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(77);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView44 = this.tv;
                if (!Start.hebLayout)
                    break label18862;
                str44 = "ברכת העבודה";
                localTextView44.setText(str44);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(78);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.roshHodesh)
                    break label18870;
                this.tv.setText(2131099715);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(79);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView45 = this.tv;
                if (!Start.hebLayout)
                    break label18921;
                str45 = "ברכת ההודאה";
                localTextView45.setText(str45);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(80);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099718);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(81);
                this.rl.addView(this.tv);
                if (Start.mode == 0) {
                    this.tvModim = new TextView(this);
                    this.tvModim.setTextColor(this.titleColor);
                    this.tvModim.setBackgroundColor(-3355444);
                    TextView localTextView50 = this.tvModim;
                    if (!Start.hebLayout)
                        break label18929;
                    str50 = "מודים דרבנן +";
                    localTextView50.setText(str50);
                    this.tvModim.setTypeface(this.tf);
                    this.tvModim.setTextSize(Start.fontSize);
                    this.tvModim.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View paramView) {
                            if (!Mincha.this.modim.isShown()) {
                                Mincha.this.modim.setVisibility(0);
                                TextView localTextView2 = Mincha.this.tvModim;
                                if (Start.hebLayout) ;
                                for (String str2 = "מודים דרבנן -"; ; str2 = "Modim D'Rabbanan -") {
                                    localTextView2.setText(str2);
                                    return;
                                }
                            }
                            Mincha.this.modim.setVisibility(8);
                            TextView localTextView1 = Mincha.this.tvModim;
                            if (Start.hebLayout) ;
                            for (String str1 = "מודים דרבנן +"; ; str1 = "Modim D'Rabbanan +") {
                                localTextView1.setText(str1);
                                break;
                            }
                        }
                    });
                    if (Start.rtl_fix)
                        this.tvModim.setGravity(5);
                    this.tvModim.setId(30);
                    this.rl.addView(this.tvModim);
                    this.modim = new TextView(this);
                    this.modim.setTextColor(this.color);
                    this.modim.setText(2131099800);
                    this.modim.setTypeface(this.tf);
                    this.modim.setTextSize(Start.fontSize);
                    this.modim.setVisibility(8);
                    if (Start.rtl_fix)
                        this.modim.setGravity(5);
                    this.modim.setId(31);
                    this.modim.setFocusableInTouchMode(true);
                    this.rl.addView(this.modim);
                    this.tv = new TextView(this);
                    this.tv.setText("");
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    this.rl.addView(this.tv);
                }
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (((!Start.purim) || (Start.doMukaf)) && ((!Start.doMukaf) || (!Start.mukaf)))
                    break label18937;
                this.tv.setText(2131099722);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(82);
                this.rl.addView(this.tv);
                if (Start.mode != 1) {
                    this.tvKohanim = new TextView(this);
                    this.tvKohanim.setTextColor(this.titleColor);
                    this.tvKohanim.setBackgroundColor(-3355444);
                    if (Start.mode != 2)
                        break label18996;
                    TextView localTextView49 = this.tvKohanim;
                    if (!Start.hebLayout)
                        break label18988;
                    str49 = "ברכת כהנים -";
                    localTextView49.setText(str49);
                    this.tvKohanim.setTypeface(this.tf);
                    this.tvKohanim.setTextSize(Start.fontSize);
                    if (!Start.anenu)
                        break label19031;
                    this.tvKohanim.setVisibility(0);
                    this.tvKohanim.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View paramView) {
                            if (!Mincha.this.kohanim.isShown()) {
                                Mincha.this.kohanim.setVisibility(0);
                                TextView localTextView2 = Mincha.this.tvKohanim;
                                if (Start.hebLayout) ;
                                for (String str2 = "ברכת כהנים -"; ; str2 = "Priestly Blessing -") {
                                    localTextView2.setText(str2);
                                    return;
                                }
                            }
                            Mincha.this.kohanim.setVisibility(8);
                            TextView localTextView1 = Mincha.this.tvKohanim;
                            if (Start.hebLayout) ;
                            for (String str1 = "ברכת כהנים +"; ; str1 = "Priestly Blessing +") {
                                localTextView1.setText(str1);
                                break;
                            }
                        }
                    });
                    if (Start.rtl_fix)
                        this.tvKohanim.setGravity(5);
                    this.tvKohanim.setId(32);
                    this.rl.addView(this.tvKohanim);
                    this.kohanim = new TextView(this);
                    this.kohanim.setTextColor(this.color);
                    this.kohanim.setText(2131099806);
                    this.kohanim.setTypeface(this.tf);
                    this.kohanim.setTextSize(Start.fontSize);
                    if (Start.mode == 0)
                        this.kohanim.setVisibility(8);
                    if (Start.rtl_fix)
                        this.kohanim.setGravity(5);
                    this.kohanim.setId(33);
                    this.rl.addView(this.kohanim);
                    this.tv = new TextView(this);
                    this.tv.setText("");
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    this.rl.addView(this.tv);
                }
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView46 = this.tv;
                if (!Start.hebLayout)
                    break label19043;
                str46 = "ברכת השלום";
                localTextView46.setText(str46);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(83);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.aseret)
                    break label19051;
                this.tv.setText(2131099727);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(84);
                this.rl.addView(this.tv);
                if (Start.mode != 2)
                    break label19077;
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                if (!Start.hebLayout)
                    break label19064;
                this.tv.setText("בקשות +");
                this.tv.setTypeface(this.tf);
                this.tv.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View paramView) {
                        TextView localTextView1 = (TextView) Mincha.this.findViewById(84);
                        TextView localTextView2 = (TextView) Mincha.this.findViewById(85);
                        TextView localTextView3 = (TextView) Mincha.this.findViewById(86);
                        TextView localTextView4 = (TextView) Mincha.this.findViewById(87);
                        if (!localTextView2.isShown()) {
                            localTextView2.setVisibility(0);
                            localTextView3.setVisibility(0);
                            localTextView4.setVisibility(0);
                            if (Start.hebLayout)
                                localTextView1.setText("בקשות -");
                        }
                        while (true) {
                            return;
                            localTextView1.setText("Pleas -");
                            continue;
                            localTextView2.setVisibility(8);
                            localTextView3.setVisibility(8);
                            localTextView4.setVisibility(8);
                            if (Start.hebLayout) {
                                localTextView1.setText("בקשות +");
                                continue;
                            }
                            localTextView1.setText("Pleas +");
                        }
                    }
                });
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(84);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(2131099728);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.mode == 2)
                    this.tv.setVisibility(8);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(86);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(Start.pasuk);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                if (Start.mode == 2)
                    this.tv.setVisibility(8);
                this.tv.setId(87);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                if (!Start.aseret)
                    break label19191;
                this.tv.setText(2131099730);
            }
            while (true) {
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                if (Start.mode == 2)
                    this.tv.setVisibility(8);
                this.tv.setId(88);
                this.rl.addView(this.tv);
                break;
                str28 = "Patriarchs";
                break label12844;
                this.tv.setText(2131099767);
                break label12946;
                str29 = "God's Might";
                break label13042;
                if (Start.earlyWinter) {
                    this.tv.setText(2131099770);
                    break label13144;
                }
                this.tv.setText(2131099769);
                break label13144;
                str54 = "Kedusha -";
                break label13264;
                TextView localTextView53 = this.tvKdusha;
                if (Start.hebLayout) ;
                for (String str53 = "קדושה +"; ; str53 = "Kedusha +") {
                    localTextView53.setText(str53);
                    break;
                }
                this.kdusha.setText(2131099694);
                break label13381;
                str30 = "Holiness of God's Name";
                break label13556;
                this.tvKdushAshkenaz2.setText(2131099696);
                break label13658;
                str31 = "Insight";
                break label13754;
                str32 = "Repentance";
                break label13946;
                str33 = "Forgiveness";
                break label14138;
                str34 = "Redemption";
                break label14330;
                str52 = "Anenu by the Chazan -";
                break label14552;
                TextView localTextView51 = this.tvAnenu;
                if (Start.hebLayout) ;
                for (String str51 = "עננו של שליח ציבור +"; ; str51 = "Anenu by the Chazan +") {
                    localTextView51.setText(str51);
                    break;
                }
                str35 = "Health and Healing";
                break label14808;
                str36 = "Year of Prosperity";
                break label15253;
                this.tv.setText(2131099704);
                break label15355;
                str37 = "Ingathering of the Exiles";
                break label15451;
                str38 = "Justice";
                break label15643;
                this.tv.setText(2131099707);
                break label15745;
                str39 = "Against Heretics";
                break label15841;
                str40 = "The Righteous";
                break label16033;
                str41 = "Rebuilding Jerusalem";
                break label16225;
                this.tv.setText(2131099711);
                break label16327;
                str42 = "Davidic Reign";
                break label16423;
                str43 = "Acceptance of Prayer";
                break label16615;
                this.tv.setText(2131099712);
                break label16717;
                str44 = "Temple Service";
                break label16813;
                if (Start.passover) {
                    this.tv.setText(2131099717);
                    break label16915;
                }
                if (Start.sukot) {
                    this.tv.setText(2131099716);
                    break label16915;
                }
                this.tv.setText(2131099714);
                break label16915;
                str45 = "Thanksgiving";
                break label17011;
                str50 = "Modim D'Rabbanan +";
                break label17219;
                if (Start.hanuca) {
                    this.tv.setText(2131099721);
                    break label17515;
                }
                if (Start.aseret) {
                    this.tv.setText(2131099720);
                    break label17515;
                }
                this.tv.setText(2131099719);
                break label17515;
                str49 = "Priestly Blessing -";
                break label17635;
                TextView localTextView48 = this.tvKohanim;
                if (Start.hebLayout) ;
                for (String str48 = "ברכת כהנים +"; ; str48 = "Priestly Blessing +") {
                    localTextView48.setText(str48);
                    break;
                }
                this.tvKohanim.setVisibility(8);
                break label17678;
                str46 = "Peace";
                break label17926;
                this.tv.setText(2131099726);
                break label18028;
                this.tv.setText("Pleas +");
                break label18130;
                this.tv = new TextView(this);
                this.tv.setTextColor(this.titleColor);
                TextView localTextView47 = this.tv;
                if (Start.hebLayout) ;
                for (String str47 = "בקשות"; ; str47 = "Pleas") {
                    localTextView47.setText(str47);
                    this.tv.setTypeface(this.tf);
                    this.tv.setTextSize(Start.fontSize);
                    if (Start.rtl_fix)
                        this.tv.setGravity(5);
                    this.tv.setId(84);
                    this.rl.addView(this.tv);
                    break;
                }
                this.tv.setText(2131099729);
            }
        }
        while (Start.nusach != 3);
        label5780:
        label5788:
        label5823:
        label5831:
        label5844:
        label5852:
        label5860:
        label5868:
        label6001:
        label6268:
        label6653:
        label7935:
        this.tv = new TextView(this);
        label5919:
        label5927:
        label5935:
        label5948:
        label5956:
        label5964:
        label5977:
        label5985:
        label5993:
        label6255:
        label6395:
        label12413:
        label12670:
        label18687:
        this.tv.setTextColor(this.titleColor);
        label6022:
        label6030:
        label6043:
        label6051:
        label6102:
        label6110:
        label6118:
        label6247:
        label7161:
        label13946:
        label15355:
        label18812:
        TextView localTextView1 = this.tv;
        label6153:
        label6204:
        label6212:
        label6755:
        label9446:
        label10220:
        label10616:
        label14330:
        String str1;
        label6281:
        label6455:
        label6875:
        label7263:
        label9056:
        label9830:
        label11120:
        label11634:
        label12152:
        label18554:
        String str2;
        label6557:
        label7359:
        label8157:
        label11240:
        label12139:
        label12272:
        label12280:
        label18937:
        String str27;
        label7743:
        label8413:
        label12256:
        label12264:
        label12397:
        label12405:
        label18679:
        label19064:
        String str3;
        label8858:
        label9248:
        label9638:
        label9932:
        label10322:
        label11736:
        label12389:
        label12522:
        label12530:
        label13042:
        label13556:
        label19191:
        String str4;
        label8960:
        label9350:
        label10028:
        label10418:
        label10824:
        label12235:
        label12243:
        label12376:
        label12514:
        label12644:
        label12657:
        label17011:
        label18804:
        label19444:
        String str5;
        label10520:
        label12192:
        label12200:
        label12331:
        label12339:
        label12347:
        label12360:
        label12368:
        label12624:
        label12636:
        label12784:
        label18546:
        label20341:
        String str6;
        label11283:
        label12442:
        label12455:
        label12463:
        label13144:
        label13658:
        label15451:
        label15841:
        label16615:
        label17515:
        label18028:
        label18671:
        label18929:
        String str7;
        label11532:
        label12434:
        label12581:
        label12589:
        label13264:
        label14552:
        label16225:
        label17635:
        label18533:
        label18663:
        label18796:
        label19951:
        label20725:
        String str25;
        label12160:
        label12288:
        label12296:
        label12426:
        label12844:
        label13381:
        label14808:
        label18650:
        label18783:
        label18921:
        label19051:
        String str8;
        label12946:
        label13754:
        label16327:
        label16717:
        label18130:
        label18775:
        label19031:
        label19043:
        String str9;
        label14138:
        label17219:
        label18629:
        label18642:
        label19545:
        label21750:
        String str10;
        label15253:
        label15643:
        label16033:
        label16423:
        label16813:
        label18738:
        label18746:
        label18754:
        label18767:
        label19665:
        label20053:
        label21846:
        String str11;
        label15745:
        label16915:
        label18586:
        label18594:
        label18862:
        label18870:
        label20947:
        label22140:
        String str12;
        label17678:
        label18703:
        label18833:
        label18841:
        label18849:
        label18988:
        label18996:
        label20149:
        label21203:
        label22236:
        String str13;
        label17926:
        label18695:
        label19249:
        label20533:
        String str14;
        label18820:
        label19077:
        label19349:
        label22038:
        label22428:
        label22620:
        String str15;
        label21648:
        label22722:
        String str16;
        label22818:
        label23010:
        String str17;
        label23112:
        String str18;
        label23208:
        label23406:
        String str23;
        label23310:
        label23614:
        label23910:
        String str22;
        label24030:
        String str19;
        if (Start.hebLayout) {
            str1 = "ברכת אבות";
            localTextView1.setText(str1);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(44);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            if (!Start.aseret)
                break label24928;
            this.tv.setText(2131099732);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(45);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView2 = this.tv;
            if (!Start.hebLayout)
                break label24941;
            str2 = "ברכת גבורות";
            localTextView2.setText(str2);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(46);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            if (!Start.aseret)
                break label24949;
            this.tv.setText(2131099735);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(47);
            this.rl.addView(this.tv);
            if (Start.mode != 1) {
                this.tvKdusha = new TextView(this);
                this.tvKdusha.setTextColor(this.titleColor);
                this.tvKdusha.setBackgroundColor(-3355444);
                if (Start.mode != 2)
                    break label24989;
                TextView localTextView27 = this.tvKdusha;
                if (!Start.hebLayout)
                    break label24981;
                str27 = "קדושה -";
                localTextView27.setText(str27);
                this.tvKdusha.setTypeface(this.tf);
                this.tvKdusha.setTextSize(Start.fontSize);
                this.tvKdusha.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View paramView) {
                        if (!Mincha.this.kdusha.isShown()) {
                            Mincha.this.kdusha.setVisibility(0);
                            TextView localTextView2 = Mincha.this.tvKdusha;
                            if (Start.hebLayout) ;
                            for (String str2 = "קדושה -"; ; str2 = "Kedusha -") {
                                localTextView2.setText(str2);
                                return;
                            }
                        }
                        Mincha.this.kdusha.setVisibility(8);
                        TextView localTextView1 = Mincha.this.tvKdusha;
                        if (Start.hebLayout) ;
                        for (String str1 = "קדושה +"; ; str1 = "Kedusha +") {
                            localTextView1.setText(str1);
                            break;
                        }
                    }
                });
                if (Start.rtl_fix)
                    this.tvKdusha.setGravity(5);
                this.tvKdusha.setId(26);
                this.rl.addView(this.tvKdusha);
                this.kdusha = new TextView(this);
                this.kdusha.setTextColor(this.color);
                this.kdusha.setText(2131099772);
                this.kdusha.setTypeface(this.tf);
                this.kdusha.setTextSize(Start.fontSize);
                if (Start.mode != 2)
                    this.kdusha.setVisibility(8);
                if (Start.rtl_fix)
                    this.kdusha.setGravity(5);
                this.kdusha.setId(27);
                this.kdusha.setFocusableInTouchMode(true);
                this.rl.addView(this.kdusha);
                this.tv = new TextView(this);
                this.tv.setText("");
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                this.rl.addView(this.tv);
            }
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView3 = this.tv;
            if (!Start.hebLayout)
                break label25024;
            str3 = "ברכת הקדושה";
            localTextView3.setText(str3);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(48);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            if (!Start.aseret)
                break label25032;
            this.tv.setText(2131099774);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(49);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView4 = this.tv;
            if (!Start.hebLayout)
                break label25045;
            str4 = "ברכת הדעת";
            localTextView4.setText(str4);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(50);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(2131099775);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(51);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView5 = this.tv;
            if (!Start.hebLayout)
                break label25053;
            str5 = "ברכת התשובה";
            localTextView5.setText(str5);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(52);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(2131099777);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(53);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView6 = this.tv;
            if (!Start.hebLayout)
                break label25061;
            str6 = "ברכת הסליחה";
            localTextView6.setText(str6);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(54);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(2131099778);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(55);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView7 = this.tv;
            if (!Start.hebLayout)
                break label25069;
            str7 = "ברכת הגאולה";
            localTextView7.setText(str7);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(56);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(2131099737);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(57);
            this.rl.addView(this.tv);
            if ((Start.anenu) && (Start.mode != 1)) {
                this.tvAnenu = new TextView(this);
                this.tvAnenu.setTextColor(this.titleColor);
                this.tvAnenu.setBackgroundColor(-3355444);
                if (Start.mode != 2)
                    break label25085;
                TextView localTextView25 = this.tvAnenu;
                if (!Start.hebLayout)
                    break label25077;
                str25 = "עננו של שליח ציבור +";
                localTextView25.setText(str25);
                this.tvAnenu.setTypeface(this.tf);
                this.tvAnenu.setTextSize(Start.fontSize);
                this.tvAnenu.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View paramView) {
                        if (!Mincha.this.anenu.isShown()) {
                            Mincha.this.anenu.setVisibility(0);
                            TextView localTextView2 = Mincha.this.tvAnenu;
                            if (Start.hebLayout) ;
                            for (String str2 = "עננו של שליח ציבור -"; ; str2 = "Anenu by the Chazan -") {
                                localTextView2.setText(str2);
                                return;
                            }
                        }
                        Mincha.this.anenu.setVisibility(8);
                        TextView localTextView1 = Mincha.this.tvAnenu;
                        if (Start.hebLayout) ;
                        for (String str1 = "עננו של שליח ציבור +"; ; str1 = "Anenu by the Chazan +") {
                            localTextView1.setText(str1);
                            break;
                        }
                    }
                });
                if (Start.rtl_fix)
                    this.tvAnenu.setGravity(5);
                this.tvAnenu.setId(28);
                this.rl.addView(this.tvAnenu);
                this.anenu = new TextView(this);
                this.anenu.setTextColor(this.color);
                this.anenu.setText(2131099780);
                this.anenu.setTypeface(this.tf);
                this.anenu.setTextSize(Start.fontSize);
                if (Start.mode != 2)
                    this.anenu.setVisibility(8);
                if (Start.rtl_fix)
                    this.anenu.setGravity(5);
                this.anenu.setId(29);
                this.rl.addView(this.anenu);
                this.tv = new TextView(this);
                this.tv.setText("");
                this.rl.addView(this.tv);
            }
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView8 = this.tv;
            if (!Start.hebLayout)
                break label25120;
            str8 = "ברכת הרפואה";
            localTextView8.setText(str8);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(58);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(2131099781);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(59);
            this.rl.addView(this.tv);
            if (Start.sick.length() > 0) {
                this.tv = new TextView(this);
                this.tv.setText("");
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setTextColor(this.color);
                this.tv.setText(Start.sickAll);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(60);
                this.rl.addView(this.tv);
                this.tv = new TextView(this);
                this.tv.setText("");
                this.rl.addView(this.tv);
            }
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(2131099782);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(61);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView9 = this.tv;
            if (!Start.hebLayout)
                break label25128;
            str9 = "ברכת השנים";
            localTextView9.setText(str9);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(62);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            if (!Start.winter)
                break label25136;
            this.tv.setText(2131099741);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(63);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView10 = this.tv;
            if (!Start.hebLayout)
                break label25149;
            str10 = "ברכת קיבוץ הגלויות";
            localTextView10.setText(str10);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(64);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(2131099742);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(65);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView11 = this.tv;
            if (!Start.hebLayout)
                break label25157;
            str11 = "ברכת המשפט";
            localTextView11.setText(str11);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(66);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            if (!Start.aseret)
                break label25165;
            this.tv.setText(2131099787);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(67);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView12 = this.tv;
            if (!Start.hebLayout)
                break label25178;
            str12 = "ברכת המינים";
            localTextView12.setText(str12);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(68);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(2131099743);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(69);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView13 = this.tv;
            if (!Start.hebLayout)
                break label25186;
            str13 = "ברכת הצדיקים";
            localTextView13.setText(str13);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(70);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(2131099744);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(71);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView14 = this.tv;
            if (!Start.hebLayout)
                break label25194;
            str14 = "ברכת בנין ירושלים";
            localTextView14.setText(str14);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(72);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            if (!Start.tishaBov)
                break label25202;
            this.tv.setText(2131099746);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(73);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView15 = this.tv;
            if (!Start.hebLayout)
                break label25215;
            str15 = "ברכת הישועה";
            localTextView15.setText(str15);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(74);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(2131099792);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(75);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView16 = this.tv;
            if (!Start.hebLayout)
                break label25223;
            str16 = "ברכת התפילה";
            localTextView16.setText(str16);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(76);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            if (!Start.anenu)
                break label25231;
            this.tv.setText(2131099748);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(77);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView17 = this.tv;
            if (!Start.hebLayout)
                break label25244;
            str17 = "ברכת העבודה";
            localTextView17.setText(str17);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(78);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            if (!Start.roshHodesh)
                break label25252;
            this.tv.setText(2131099750);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(79);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView18 = this.tv;
            if (!Start.hebLayout)
                break label25303;
            str18 = "ברכת ההודאה";
            localTextView18.setText(str18);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(80);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(2131099753);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(81);
            this.rl.addView(this.tv);
            if (Start.mode == 0) {
                this.tvModim = new TextView(this);
                this.tvModim.setTextColor(this.titleColor);
                this.tvModim.setBackgroundColor(-3355444);
                TextView localTextView23 = this.tvModim;
                if (!Start.hebLayout)
                    break label25311;
                str23 = "מודים דרבנן +";
                localTextView23.setText(str23);
                this.tvModim.setTypeface(this.tf);
                this.tvModim.setTextSize(Start.fontSize);
                this.tvModim.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View paramView) {
                        if (!Mincha.this.modim.isShown()) {
                            Mincha.this.modim.setVisibility(0);
                            TextView localTextView2 = Mincha.this.tvModim;
                            if (Start.hebLayout) ;
                            for (String str2 = "מודים דרבנן -"; ; str2 = "Modim D'Rabbanan -") {
                                localTextView2.setText(str2);
                                return;
                            }
                        }
                        Mincha.this.modim.setVisibility(8);
                        TextView localTextView1 = Mincha.this.tvModim;
                        if (Start.hebLayout) ;
                        for (String str1 = "מודים דרבנן +"; ; str1 = "Modim D'Rabbanan +") {
                            localTextView1.setText(str1);
                            break;
                        }
                    }
                });
                if (Start.rtl_fix)
                    this.tvModim.setGravity(5);
                this.tvModim.setId(30);
                this.rl.addView(this.tvModim);
                this.modim = new TextView(this);
                this.modim.setTextColor(this.color);
                this.modim.setText(2131099754);
                this.modim.setTypeface(this.tf);
                this.modim.setTextSize(Start.fontSize);
                this.modim.setVisibility(8);
                if (Start.rtl_fix)
                    this.modim.setGravity(5);
                this.modim.setId(31);
                this.modim.setFocusableInTouchMode(true);
                this.rl.addView(this.modim);
                this.tv = new TextView(this);
                this.tv.setText("");
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                this.rl.addView(this.tv);
            }
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            if (((!Start.purim) || (Start.doMukaf)) && ((!Start.doMukaf) || (!Start.mukaf)))
                break label25319;
            this.tv.setText(2131099758);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(82);
            this.rl.addView(this.tv);
            if (Start.mode != 1) {
                this.tvKohanim = new TextView(this);
                this.tvKohanim.setTextColor(this.titleColor);
                this.tvKohanim.setBackgroundColor(-3355444);
                if (Start.mode != 2)
                    break label25378;
                TextView localTextView22 = this.tvKohanim;
                if (!Start.hebLayout)
                    break label25370;
                str22 = "ברכת כהנים +";
                localTextView22.setText(str22);
                this.tvKohanim.setTypeface(this.tf);
                this.tvKohanim.setTextSize(Start.fontSize);
                if (!Start.anenu)
                    break label25413;
                this.tvKohanim.setVisibility(0);
                label24073:
                this.tvKohanim.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View paramView) {
                        if (!Mincha.this.kohanim.isShown()) {
                            Mincha.this.kohanim.setVisibility(0);
                            TextView localTextView2 = Mincha.this.tvKohanim;
                            if (Start.hebLayout) ;
                            for (String str2 = "ברכת כהנים -"; ; str2 = "Priestly Blessing -") {
                                localTextView2.setText(str2);
                                return;
                            }
                        }
                        Mincha.this.kohanim.setVisibility(8);
                        TextView localTextView1 = Mincha.this.tvKohanim;
                        if (Start.hebLayout) ;
                        for (String str1 = "ברכת כהנים +"; ; str1 = "Priestly Blessing +") {
                            localTextView1.setText(str1);
                            break;
                        }
                    }
                });
                if (Start.rtl_fix)
                    this.tvKohanim.setGravity(5);
                this.tvKohanim.setId(32);
                this.rl.addView(this.tvKohanim);
                this.kohanim = new TextView(this);
                this.kohanim.setTextColor(this.color);
                this.kohanim.setText(2131099760);
                this.kohanim.setTypeface(this.tf);
                this.kohanim.setTextSize(Start.fontSize);
                if (Start.mode != 2)
                    this.kohanim.setVisibility(8);
                if (Start.rtl_fix)
                    this.kohanim.setGravity(5);
                this.kohanim.setId(33);
                this.rl.addView(this.kohanim);
                this.tv = new TextView(this);
                this.tv.setText("");
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                this.rl.addView(this.tv);
            }
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView19 = this.tv;
            if (!Start.hebLayout)
                break label25425;
            str19 = "ברכת השלום";
            label24322:
            localTextView19.setText(str19);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(83);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            if (!Start.aseret)
                break label25433;
            this.tv.setText(2131099762);
            label24424:
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(84);
            this.rl.addView(this.tv);
            if (Start.mode != 2)
                break label25459;
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            if (!Start.hebLayout)
                break label25446;
            this.tv.setText("בקשות +");
            label24526:
            this.tv.setTypeface(this.tf);
            this.tv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramView) {
                    TextView localTextView1 = (TextView) Mincha.this.findViewById(85);
                    TextView localTextView2 = (TextView) Mincha.this.findViewById(86);
                    TextView localTextView3 = (TextView) Mincha.this.findViewById(87);
                    TextView localTextView4 = (TextView) Mincha.this.findViewById(88);
                    if (!localTextView2.isShown()) {
                        localTextView2.setVisibility(0);
                        localTextView3.setVisibility(0);
                        localTextView4.setVisibility(0);
                        if (Start.hebLayout)
                            localTextView1.setText("בקשות -");
                    }
                    while (true) {
                        return;
                        localTextView1.setText("Pleas -");
                        continue;
                        localTextView2.setVisibility(8);
                        localTextView3.setVisibility(8);
                        localTextView4.setVisibility(8);
                        if (Start.hebLayout) {
                            localTextView1.setText("בקשות +");
                            continue;
                        }
                        localTextView1.setText("Pleas +");
                    }
                }
            });
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            this.tv.setId(85);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(2131099763);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            if (Start.mode == 2)
                this.tv.setVisibility(8);
            this.tv.setId(86);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            this.tv.setText(Start.pasuk);
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            if (Start.mode == 2)
                this.tv.setVisibility(8);
            this.tv.setId(87);
            this.rl.addView(this.tv);
            this.tv = new TextView(this);
            this.tv.setTextColor(this.color);
            if (!Start.aseret)
                break label25573;
            this.tv.setText(2131099765);
        }
        while (true) {
            this.tv.setTypeface(this.tf);
            this.tv.setTextSize(Start.fontSize);
            if (Start.rtl_fix)
                this.tv.setGravity(5);
            if (Start.mode == 2)
                this.tv.setVisibility(8);
            this.tv.setId(88);
            this.rl.addView(this.tv);
            break;
            str1 = "Patriarchs";
            break label19249;
            label24928:
            this.tv.setText(2131099731);
            break label19349;
            label24941:
            str2 = "God's Might";
            break label19444;
            label24949:
            if (Start.earlyWinter) {
                this.tv.setText(2131099734);
                break label19545;
            }
            this.tv.setText(2131099733);
            break label19545;
            label24981:
            str27 = "Kedusha -";
            break label19665;
            label24989:
            TextView localTextView26 = this.tvKdusha;
            if (Start.hebLayout) ;
            for (String str26 = "קדושה +"; ; str26 = "Kedusha +") {
                localTextView26.setText(str26);
                break;
            }
            label25024:
            str3 = "Holiness of God's Name";
            break label19951;
            label25032:
            this.tv.setText(2131099773);
            break label20053;
            label25045:
            str4 = "Insight";
            break label20149;
            label25053:
            str5 = "Repentance";
            break label20341;
            label25061:
            str6 = "Forgiveness";
            break label20533;
            label25069:
            str7 = "Redemption";
            break label20725;
            label25077:
            str25 = "Anenu by the Chazan +";
            break label20947;
            label25085:
            TextView localTextView24 = this.tvAnenu;
            if (Start.hebLayout) ;
            for (String str24 = "עננו של שליח ציבור -"; ; str24 = "Anenu by the Chazan -") {
                localTextView24.setText(str24);
                break;
            }
            label25120:
            str8 = "Health and Healing";
            break label21203;
            label25128:
            str9 = "Year of Prosperity";
            break label21648;
            label25136:
            this.tv.setText(2131099740);
            break label21750;
            label25149:
            str10 = "Ingathering of the Exiles";
            break label21846;
            label25157:
            str11 = "Justice";
            break label22038;
            label25165:
            this.tv.setText(2131099786);
            break label22140;
            label25178:
            str12 = "Against Heretics";
            break label22236;
            label25186:
            str13 = "The Righteous";
            break label22428;
            label25194:
            str14 = "Rebuilding Jerusalem";
            break label22620;
            label25202:
            this.tv.setText(2131099745);
            break label22722;
            label25215:
            str15 = "Davidic Reign";
            break label22818;
            label25223:
            str16 = "Acceptance of Prayer";
            break label23010;
            label25231:
            this.tv.setText(2131099747);
            break label23112;
            label25244:
            str17 = "Temple Service";
            break label23208;
            label25252:
            if (Start.passover) {
                this.tv.setText(2131099752);
                break label23310;
            }
            if (Start.sukot) {
                this.tv.setText(2131099751);
                break label23310;
            }
            this.tv.setText(2131099749);
            break label23310;
            label25303:
            str18 = "Thanksgiving";
            break label23406;
            label25311:
            str23 = "Modim D'Rabbanan +";
            break label23614;
            label25319:
            if (Start.hanuca) {
                this.tv.setText(2131099757);
                break label23910;
            }
            if (Start.aseret) {
                this.tv.setText(2131099756);
                break label23910;
            }
            this.tv.setText(2131099755);
            break label23910;
            label25370:
            str22 = "Priestly Blessing +";
            break label24030;
            label25378:
            TextView localTextView21 = this.tvKohanim;
            if (Start.hebLayout) ;
            for (String str21 = "ברכת כהנים -"; ; str21 = "Priestly Blessing -") {
                localTextView21.setText(str21);
                break;
            }
            label25413:
            this.tvKohanim.setVisibility(8);
            break label24073;
            label25425:
            str19 = "Peace";
            break label24322;
            label25433:
            this.tv.setText(2131099807);
            break label24424;
            label25446:
            this.tv.setText("Pleas +");
            break label24526;
            label25459:
            this.tv = new TextView(this);
            this.tv.setTextColor(this.titleColor);
            TextView localTextView20 = this.tv;
            if (Start.hebLayout) ;
            for (String str20 = "בקשות"; ; str20 = "Pleas") {
                localTextView20.setText(str20);
                this.tv.setTypeface(this.tf);
                this.tv.setTextSize(Start.fontSize);
                if (Start.rtl_fix)
                    this.tv.setGravity(5);
                this.tv.setId(85);
                this.rl.addView(this.tv);
                break;
            }
            label25573:
            this.tv.setText(2131099764);
        }
    }

    private void drawAgain() {
        int i;
        switch (Start.font) {
            default:
                i = 0;
            case 0:
            case 1:
            case 2:
            case 3:
        }
        while (true) {
            if (i >= 120) {
                return;
                this.tf = Typeface.createFromAsset(getAssets(), "TIMES.TTF");
                break;
                this.tf = Typeface.createFromAsset(getAssets(), "Guttman Keren-Normal.TTF");
                break;
                this.tf = Typeface.createFromAsset(getAssets(), "NRKIS.TTF");
                break;
                this.tf = Typeface.createFromAsset(getAssets(), "Cardob101.ttf");
                break;
            }
            try {
                TextView localTextView = (TextView) findViewById(i);
                localTextView.setTextSize(Start.fontSize);
                localTextView.setTypeface(this.tf);
                i++;
            } catch (Exception localException) {
                while (true)
                    i++;
            }
        }
    }

    private void drawHeader() {
        if (Start.hebLayout) {
            getWindow().setFeatureInt(7, 2130903045);
            if (!Start.hebLayout)
                break label265;
            this.title = ((TextView) findViewById(2131427348));
            this.title.setText("מנחה");
            this.icon = ((ImageView) findViewById(2131427345));
            this.icon.setImageResource(2130837539);
            this.increase_font = ((ImageView) findViewById(2131427342));
            label86:
            ((LinearLayout) findViewById(2131427340)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramView) {
                    Mincha.this.sv.smoothScrollTo(0, Mincha.this.sv.getTop());
                }
            });
            this.inflater = ((LayoutInflater) getSystemService("layout_inflater"));
            this.theTitle = ((LinearLayout) findViewById(2131427341));
            this.theTitle.setBackgroundResource(2130837569);
            if (!Start.hebLayout)
                break label330;
        }
        label265:
        label330:
        for (View localView = this.inflater.inflate(2130903055, null, false); ; localView = this.inflater.inflate(2130903054, null, false)) {
            this.pw = new PopupWindow(localView, -2, -2, true);
            this.theTitle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramView) {
                    Mincha.this.openNusach();
                }
            });
            this.st = new PopupWindow(this.inflater.inflate(2130903061, null, false), -1, -2, true);
            this.increase_font.setImageResource(2130837536);
            this.increase_font.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramView) {
                    Mincha.this.st.setTouchable(true);
                    Mincha.this.st.setFocusable(true);
                    Mincha.this.st.setOutsideTouchable(true);
                    Mincha.this.st.showAsDropDown(paramView);
                    Mincha.this.st.setAnimationStyle(2131165186);
                    Mincha.this.sb = ((SeekBar) Mincha.this.st.getContentView().findViewById(2131427380));
                    Mincha.this.sb.setProgress(Start.fontSize);
                    Mincha.this.sb.setMax(50);
                    Mincha.this.sb.setSoundEffectsEnabled(false);
                    Mincha.this.sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean) {
                            Start.fontSize = paramInt;
                            Mincha.this.drawAgain();
                        }

                        public void onStartTrackingTouch(SeekBar paramSeekBar) {
                        }

                        public void onStopTrackingTouch(SeekBar paramSeekBar) {
                        }
                    });
                    TextView localTextView1 = (TextView) Mincha.this.st.getContentView().findViewById(2131427379);
                    String str1;
                    String str2;
                    label198:
                    String str3;
                    label315:
                    int i;
                    label358:
                    String str4;
                    label446:
                    Button localButton2;
                    if (Start.hebLayout) {
                        str1 = "גודל גופן";
                        localTextView1.setText(str1);
                        TextView localTextView2 = (TextView) Mincha.this.st.getContentView().findViewById(2131427381);
                        if (!Start.hebLayout)
                            break label542;
                        str2 = "בחר גופן";
                        localTextView2.setText(str2);
                        Spinner localSpinner1 = (Spinner) Mincha.this.st.getContentView().findViewById(2131427382);
                        ArrayAdapter localArrayAdapter1 = ArrayAdapter.createFromResource(Mincha.this, 2131230722, 17367048);
                        localArrayAdapter1.setDropDownViewResource(17367050);
                        localSpinner1.setAdapter(localArrayAdapter1);
                        localSpinner1.setSelection(Start.font);
                        localSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
                                Start.font = paramInt;
                                Mincha.this.drawAgain();
                            }

                            public void onNothingSelected(AdapterView<?> paramAdapterView) {
                            }
                        });
                        if (Start.free)
                            localSpinner1.setEnabled(false);
                        TextView localTextView3 = (TextView) Mincha.this.st.getContentView().findViewById(2131427344);
                        if (!Start.hebLayout)
                            break label549;
                        str3 = "בחר מצב תפילה";
                        localTextView3.setText(str3);
                        Spinner localSpinner2 = (Spinner) Mincha.this.st.getContentView().findViewById(2131427384);
                        Mincha localMincha = Mincha.this;
                        if (!Start.hebLayout)
                            break label556;
                        i = 2131230735;
                        ArrayAdapter localArrayAdapter2 = ArrayAdapter.createFromResource(localMincha, i, 17367048);
                        localArrayAdapter2.setDropDownViewResource(17367050);
                        localSpinner2.setAdapter(localArrayAdapter2);
                        localSpinner2.setSelection(Start.mode);
                        localSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
                                if (Start.mode != paramInt) {
                                    Start.mode = paramInt;
                                    Mincha.this.reDraw();
                                }
                            }

                            public void onNothingSelected(AdapterView<?> paramAdapterView) {
                            }
                        });
                        if (Start.free)
                            localSpinner2.setEnabled(false);
                        Button localButton1 = (Button) Mincha.this.st.getContentView().findViewById(2131427385);
                        if (!Start.hebLayout)
                            break label563;
                        str4 = "עבור להגדרות";
                        localButton1.setText(str4);
                        localButton1.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View paramView) {
                                Mincha.this.startActivity(new Intent(Mincha.this, Preferences.class));
                                Mincha.this.st.dismiss();
                            }
                        });
                        Mincha.this.close = ((Button) Mincha.this.st.getContentView().findViewById(2131427365));
                        localButton2 = Mincha.this.close;
                        if (!Start.hebLayout)
                            break label570;
                    }
                    label542:
                    label549:
                    label556:
                    label563:
                    label570:
                    for (String str5 = "סגור"; ; str5 = "Close") {
                        localButton2.setText(str5);
                        Mincha.this.close.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View paramView) {
                                Mincha.this.st.dismiss();
                            }
                        });
                        return;
                        str1 = "Font Size";
                        break;
                        str2 = "Choose Font";
                        break label198;
                        str3 = "Choose Tefila Mode";
                        break label315;
                        i = 2131230736;
                        break label358;
                        str4 = "Go To Settings";
                        break label446;
                    }
                }
            });
            return;
            getWindow().setFeatureInt(7, 2130903044);
            break;
            this.title = ((TextView) findViewById(2131427343));
            this.title.setText("Mincha");
            this.icon = ((ImageView) findViewById(2131427342));
            this.icon.setImageResource(2130837539);
            this.increase_font = ((ImageView) findViewById(2131427345));
            break label86;
        }
    }

    private void setParts() {
        ArrayList localArrayList = new ArrayList();
        String str15;
        if (Start.nusach != 2) {
            if (Start.nusach != 0)
                break label300;
            if (!Start.hebLayout)
                break label292;
            str15 = "לשם יחוד";
        }
        while (true) {
            localArrayList.add(str15);
            String str1;
            label48:
            String str2;
            label77:
            String str13;
            label107:
            String str3;
            label125:
            String str4;
            label143:
            String str5;
            label161:
            String str12;
            label185:
            String str11;
            if (Start.hebLayout) {
                str1 = "אשרי";
                localArrayList.add(str1);
                if ((Start.anenu) || (Start.tishaBov)) {
                    if (!Start.hebLayout)
                        break label336;
                    str2 = "קריאת התורה";
                    localArrayList.add(str2);
                    if ((Start.nusach != 0) && (Start.anenu)) {
                        if (!Start.hebLayout)
                            break label344;
                        str13 = "הפטרה";
                        localArrayList.add(str13);
                    }
                }
                if (!Start.hebLayout)
                    break label352;
                str3 = "עמידה";
                localArrayList.add(str3);
                if (!Start.hebLayout)
                    break label360;
                str4 = "קדושה";
                localArrayList.add(str4);
                if (!Start.hebLayout)
                    break label368;
                str5 = "מודים דרבנן";
                localArrayList.add(str5);
                if (Start.aseret) {
                    if (!Start.hebLayout)
                        break label376;
                    str12 = "אבינו מלכנו";
                    localArrayList.add(str12);
                }
                if (!Start.noTahanun) {
                    if (!Start.hebLayout)
                        break label384;
                    str11 = "תחנון";
                    label209:
                    localArrayList.add(str11);
                }
            }
            try {
                String str10;
                if (Start.nusach == 0) {
                    if (Start.globalDate.getDay() != 5)
                        break label400;
                    if (!Start.hebLayout)
                        break label392;
                    str10 = "השם מלך";
                    label243:
                    localArrayList.add(str10);
                }
                while (true) {
                    if (!Start.hebLayout)
                        break label501;
                    str7 = "עלינו לשבח";
                    localArrayList.add(str7);
                    this.parts = new String[localArrayList.size()];
                    i = 0;
                    if (i < localArrayList.size())
                        break label509;
                    return;
                    label292:
                    str15 = "Leshem Yihud";
                    break;
                    label300:
                    if (Start.hebLayout) ;
                    for (String str14 = "פרשת הכיור"; ; str14 = "Kyior") {
                        localArrayList.add(str14);
                        break;
                    }
                    str1 = "Ashrei";
                    break label48;
                    label336:
                    str2 = "Torah Reading";
                    break label77;
                    label344:
                    str13 = "Haftara";
                    break label107;
                    label352:
                    str3 = "Amida";
                    break label125;
                    label360:
                    str4 = "Kdusha";
                    break label143;
                    label368:
                    str5 = "Modim D'rabanan";
                    break label161;
                    label376:
                    str12 = "Avinu Malkenu";
                    break label185;
                    label384:
                    str11 = "Tahanun";
                    break label209;
                    label392:
                    str10 = "Hashem Malach";
                    break label243;
                    label400:
                    if (!Start.hebLayout)
                        break label457;
                    str9 = "למנצח";
                    label410:
                    localArrayList.add(str9);
                }
            } catch (Exception localException) {
                while (true) {
                    int i;
                    String str9;
                    if (new Date().getDay() == 5) {
                        if (Start.hebLayout) ;
                        for (String str8 = "השם מלך"; ; str8 = "Hashem Malach") {
                            localArrayList.add(str8);
                            break;
                            label457:
                            str9 = "Lamnatzeach";
                            break label410;
                        }
                    }
                    if (Start.hebLayout) ;
                    for (String str6 = "למנצח"; ; str6 = "Lamnatzeach") {
                        localArrayList.add(str6);
                        break;
                    }
                    label501:
                    String str7 = "Aleinu";
                    continue;
                    label509:
                    this.parts[i] = ((String) localArrayList.get(i));
                    i++;
                }
            }
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        PowerManager localPowerManager = (PowerManager) getSystemService("power");
        if (Start.mode == 2) {
            Start.keepAwake = true;
            Start.silent = 0;
        }
        if (Start.keepAwake) {
            this.wl = localPowerManager.newWakeLock(10, "My Tag");
            this.wl.acquire();
        }
        if (Start.font == 0)
            this.tf = Typeface.createFromAsset(getAssets(), "TIMES.TTF");
        label244:
        while (true) {
            requestWindowFeature(7);
            reDraw();
            drawHeader();
            this.aManager = ((AudioManager) getSystemService("audio"));
            if ((this.aManager.getRingerMode() == 2) && (Start.silent == 2))
                new AlertDialog.Builder(this).setMessage(Start.vibrateMessage).setPositiveButton(Start.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Mincha.this.aManager.setRingerMode(1);
                        Start.vibrate = true;
                    }
                }).setNegativeButton(Start.no, null).show();
            if ((this.aManager.getRingerMode() == 2) && (Start.silent == 1))
                this.aManager.setRingerMode(2);
            while (true) {
                return;
                if (Start.font != 1)
                    break label244;
                this.tf = Typeface.createFromAsset(getAssets(), "Guttman Keren-Normal.TTF");
                break;
                if ((this.aManager.getRingerMode() != 2) || (Start.silent != 0))
                    continue;
                this.aManager.setRingerMode(1);
                Start.vibrate = true;
            }
        }
    }

    public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo) {
        super.onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
        if ((Start.mode != 2) && (Start.context))
            openNusach();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.wl != null)
            this.wl.release();
        if (Start.vibrate) {
            this.aManager = ((AudioManager) getSystemService("audio"));
            this.aManager.setRingerMode(2);
            Start.vibrate = false;
        }
    }

    protected void onPuase() {
        super.onPause();
        this.t.cancel();
    }

    protected void onResume() {
        super.onResume();
        if (Start.afterSettings) {
            reDraw();
            Start.afterSettings = false;
        }
    }

    protected void openNusach() {
        this.nusachWheel = ((WheelView) this.pw.getContentView().findViewById(2131427370));
        this.partWheel = ((WheelView) this.pw.getContentView().findViewById(2131427371));
        WheelView localWheelView = this.nusachWheel;
        Resources localResources = getResources();
        int i;
        String str1;
        label196:
        String str2;
        label245:
        Intent localIntent;
        AlertDialog.Builder localBuilder;
        if (Start.hebLayout) {
            i = 2131230729;
            localWheelView.setViewAdapter(new ArrayWheelAdapter(this, localResources.getStringArray(i)));
            this.nusachWheel.setCurrentItem(Start.nusach, false);
            this.partWheel.setViewAdapter(new ArrayWheelAdapter(this, this.parts));
            this.nusachWheel.addChangingListener(new OnWheelChangedListener() {
                public void onChanged(WheelView paramWheelView, int paramInt1, int paramInt2) {
                    Mincha.this.nusachChanged = true;
                    Start.nusach = paramInt2;
                    Mincha.this.setParts();
                    Mincha.this.partWheel.setViewAdapter(new ArrayWheelAdapter(Mincha.this, Mincha.this.parts));
                }
            });
            this.pw.setTouchable(true);
            this.pw.setFocusable(true);
            this.pw.setOutsideTouchable(true);
            this.pw.showAsDropDown(this.theTitle);
            this.pw.setAnimationStyle(2131165186);
            Button localButton1 = (Button) this.pw.getContentView().findViewById(2131427373);
            if (!Start.hebLayout)
                break label363;
            str1 = "סגור";
            localButton1.setText(str1);
            localButton1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramView) {
                    Mincha.this.pw.dismiss();
                }
            });
            Button localButton2 = (Button) this.pw.getContentView().findViewById(2131427365);
            if (!Start.hebLayout)
                break label371;
            str2 = "עבור";
            localButton2.setText(str2);
            localButton2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramView) {
                    if (Mincha.this.nusachChanged) {
                        Start.nusach = Mincha.this.nusachWheel.getCurrentItem();
                        Mincha.this.reDraw();
                        Mincha.this.nusachChanged = false;
                    }
                    String str = Mincha.this.parts[Mincha.this.partWheel.getCurrentItem()];
                    if (Start.hebLayout)
                        if ((str == "לשם יחוד") || (str == "פרשת הכיור"))
                            Mincha.this.ihud.requestFocus();
                    while (true) {
                        Mincha.this.pw.dismiss();
                        return;
                        if (str == "אשרי") {
                            Mincha.this.ashrei.requestFocus();
                            continue;
                        }
                        if (str == "קריאת התורה") {
                            Mincha.this.torah.requestFocus();
                            continue;
                        }
                        if (str == "הפטרה") {
                            Mincha.this.haftara.requestFocus();
                            continue;
                        }
                        if (str == "עמידה") {
                            Mincha.this.amida.requestFocus();
                            continue;
                        }
                        if (str == "תחנון") {
                            Mincha.this.tahanun.requestFocus();
                            continue;
                        }
                        if (str == "אבינו מלכנו") {
                            Mincha.this.avinu.requestFocus();
                            continue;
                        }
                        if ((str == "למנצח") || (str == "השם מלך")) {
                            ((TextView) Mincha.this.findViewById(21)).requestFocus();
                            continue;
                        }
                        if (str == "עלינו לשבח") {
                            Mincha.this.aleinu.requestFocus();
                            continue;
                        }
                        if (str == "קדושה") {
                            Mincha.this.kdusha.setVisibility(0);
                            if (Start.hebLayout)
                                Mincha.this.tvKdusha.setText("קדושה -");
                            while (true) {
                                Mincha.this.kdusha.requestFocus();
                                break;
                                Mincha.this.tvKdusha.setText("Kdusha -");
                            }
                        }
                        if (str != "מודים דרבנן")
                            continue;
                        Mincha.this.modim.setVisibility(0);
                        if (Start.hebLayout)
                            Mincha.this.tvModim.setText("מודים דרבנן -");
                        while (true) {
                            Mincha.this.modim.requestFocus();
                            break;
                            Mincha.this.tvModim.setText("Modim D'rabanan -");
                        }
                        if ((str == "Leshem Yihud") || (str == "Kyior")) {
                            Mincha.this.ihud.requestFocus();
                            continue;
                        }
                        if (str == "Ashrei") {
                            Mincha.this.ashrei.requestFocus();
                            continue;
                        }
                        if (str == "Torah Reading") {
                            Mincha.this.torah.requestFocus();
                            continue;
                        }
                        if (str == "Haftara") {
                            Mincha.this.haftara.requestFocus();
                            continue;
                        }
                        if (str == "Amida") {
                            Mincha.this.amida.requestFocus();
                            continue;
                        }
                        if (str == "Tahanun") {
                            Mincha.this.tahanun.requestFocus();
                            continue;
                        }
                        if (str == "Avinu Malkenu") {
                            Mincha.this.avinu.requestFocus();
                            continue;
                        }
                        if ((str == "Lamnatzeach") || (str == "Hashem Malach")) {
                            ((TextView) Mincha.this.findViewById(21)).requestFocus();
                            continue;
                        }
                        if (str == "Aleinu") {
                            Mincha.this.aleinu.requestFocus();
                            continue;
                        }
                        if (str == "Kdusha") {
                            Mincha.this.kdusha.setVisibility(0);
                            Mincha.this.tvKdusha.setText("Kdusha -");
                            Mincha.this.kdusha.requestFocus();
                            continue;
                        }
                        if (str != "Modim D'rabanan")
                            continue;
                        Mincha.this.modim.setVisibility(0);
                        Mincha.this.tvModim.setText("Modim D'rabanan -");
                        Mincha.this.modim.requestFocus();
                    }
                }
            });
            if (Start.free) {
                localIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://market.android.com/details?id=com.karriapps.smartsiddur"));
                localBuilder = new AlertDialog.Builder(this);
                if (!Start.hebLayout)
                    break label379;
            }
        }
        label363:
        label371:
        label379:
        for (String str3 = "קיים רק בגרסה המלאה, רכוש כעת?"; ; str3 = "Pro version only, do you want to purchase now for only 2.99$") {
            localBuilder.setMessage(str3).setPositiveButton(Start.yes, new DialogInterface.OnClickListener(localIntent) {
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Mincha.this.startActivity(this.val$intent);
                    Mincha.this.pw.dismiss();
                }
            }).setNegativeButton(Start.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Mincha.this.pw.dismiss();
                }
            }).setCancelable(false).show();
            return;
            i = 2131230730;
            break;
            str1 = "Close";
            break label196;
            str2 = "Change";
            break label245;
        }
    }

    protected void reDraw() {
        setParts();
        if ((Start.atsmaut) && (Start.mizrochnik)) {
            Start.noTahanun = true;
            switch (Start.theme) {
                default:
                    switch (Start.font) {
                        default:
                            label52:
                            label84:
                            createLayout();
                            this.ashrei.requestFocus();
                            this.sv = new ScrollView(this);
                            this.sv.setPadding(20, 20, 20, 20);
                            if (Start.theme == 3)
                                this.sv.setBackgroundResource(2130837505);
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                    }
                case 0:
                case 1:
                case 2:
                case 3:
            }
        }
        while (true) {
            this.sv.addView(this.rl);
            setContentView(this.sv);
            registerForContextMenu(this.rl);
            if (Start.portraitOnly)
                setRequestedOrientation(1);
            if (this.t != null)
                this.t.cancel();
            this.t = new Timer();
            if (Start.scroll)
                this.t.schedule(new TimerTask() {
                    public void run() {
                        Mincha.this.sv.post(new Runnable() {
                            public void run() {
                                Mincha.this.sv.scrollBy(0, Start.scrollValue);
                            }
                        });
                    }
                }
                        , 0L, 400L);
            return;
            if ((!Start.atsmaut) || (Start.mizrochnik))
                break;
            Start.noTahanun = false;
            break;
            this.color = -16777216;
            this.backgroundColor = -1;
            this.titleColor = Color.rgb(50, 205, 50);
            break label52;
            this.color = -1;
            this.backgroundColor = -16777216;
            this.titleColor = Color.rgb(50, 205, 50);
            break label52;
            this.color = -16777216;
            this.titleColor = Color.rgb(0, 153, 255);
            break label52;
            this.color = -16777216;
            this.titleColor = Color.rgb(150, 109, 56);
            break label52;
            this.tf = Typeface.createFromAsset(getAssets(), "TIMES.TTF");
            break label84;
            this.tf = Typeface.createFromAsset(getAssets(), "Guttman Keren-Normal.TTF");
            break label84;
            this.tf = Typeface.createFromAsset(getAssets(), "NRKIS.TTF");
            break label84;
            this.tf = Typeface.createFromAsset(getAssets(), "Cardob101.ttf");
            break label84;
            if (Start.theme == 2) {
                this.sv.setBackgroundResource(2130837543);
                continue;
            }
            if (Start.theme == 0) {
                this.sv.setBackgroundColor(-1);
                continue;
            }
            if (Start.theme != 1)
                continue;
            this.sv.setBackgroundColor(-16777216);
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private GestureListener() {
        }

        public boolean onDoubleTap(MotionEvent paramMotionEvent) {
            if (Start.fullScreen) {
                ((View) Mincha.this.getWindow().findViewById(2131427340).getParent()).setVisibility(0);
                Mincha.this.getWindow().addFlags(2048);
                Mincha.this.getWindow().clearFlags(1024);
                Start.fullScreen = false;
            }
            while (true) {
                return super.onDoubleTap(paramMotionEvent);
                ((View) Mincha.this.getWindow().findViewById(2131427340).getParent()).setVisibility(8);
                Mincha.this.getWindow().setFlags(1024, 1024);
                Mincha.this.getWindow().clearFlags(2048);
                Start.fullScreen = true;
            }
        }

        public boolean onDown(MotionEvent paramMotionEvent) {
            return super.onDown(paramMotionEvent);
        }
    }
}