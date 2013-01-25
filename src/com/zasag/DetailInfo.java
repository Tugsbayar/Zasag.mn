package com.zasag;

import android.app.Activity;
import android.os.Bundle;

public class DetailInfo extends Activity 
{
	public static int infoID;
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailinfo);
        infoID = getIntent().getExtras().getInt("infoID");
    }
}
