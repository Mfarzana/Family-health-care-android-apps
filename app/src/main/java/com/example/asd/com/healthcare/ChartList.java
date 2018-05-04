package com.example.asd.com.healthcare;

/**
 * Created by Assaduzzaman Noor on 4/29/2016.
 */
public class ChartList {
    String Pname;
    String Dtype;
    String Dmenu;

    public ChartList(String pname, String dmenu, String dtype) {
        Pname = pname;
        Dmenu = dmenu;
        Dtype = dtype;
    }

    public String getPname() {
        return Pname;
    }

    public String getDtype() {
        return Dtype;
    }

    public String getDmenu() {
        return Dmenu;
    }
}
