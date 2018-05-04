package com.example.asd.com.healthcare;

/**
 * Created by Assaduzzaman Noor on 4/29/2016.
 */
public enum ModelObject {

    DAYONE(R.id.tittle,R.id.fastpage,R.id.ll,R.id.arrow,R.layout.one),
    DAYTWO(R.id.tittle1, R.id.tp1, R.id.lone, R.id.image1, R.layout.two),
    DAYTHREE(R.id.tittle2, R.id.tp2, R.id.ltwo, R.id.image2, R.layout.three),
    DAYFOUR(R.id.tittle3, R.id.tp3, R.id.lthree, R.id.image3, R.layout.four),
    DAYTFIVE(R.id.tittle4, R.id.tp4, R.id.lfour, R.id.images4, R.layout.five),
    DAYSIX(R.id.tittle5, R.id.tp5, R.id.lfive, R.id.image5, R.layout.six);

    private int mLayoutResId;

    ModelObject(int image1, int fastpage, int tittle1, int description, int layoutResId) {

        mLayoutResId = layoutResId;
    }



    public int getLayoutResId() {
        return mLayoutResId;
    }


}
