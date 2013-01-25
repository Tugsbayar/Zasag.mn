package com.zasag;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.data.CleanedNews;
import com.data.Connection;
import com.data.Default;
import com.zasag.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;

/*
 * Developed By mBm Technology LLC
 * */
public class ZasagActivity extends Activity 
{
	// info **************************************
	List<NameValuePair> nameValuePairs;
	private int width;
	private int height;
	
	// design sections ***************************
	private Intent detailedInfo;
	CustomListViewAdapter adapter;
    
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        
		detailedInfo = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        detailedInfo.setClass(getBaseContext(), DetailInfo.class);

        if(loadData() == true)
        {
        	setContentView(R.layout.info);
        	showDesign();
        }
    }
	
	private boolean loadData()
	{
		ArrayList<CleanedNews> newsContainer = new ArrayList<CleanedNews>();
		nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("subcategory", "1"));
		
		String infos = Connection.getInfo(Default.HOSTNAME + Default.DIRECTORY + Default.PAGECONTENT, "POST", nameValuePairs);
		if(infos == null)
		{
			showMessage();
			return false;
		}
		
		newsContainer = Connection.convertToMyFormatInfo(infos);
		if(newsContainer == null)
		{
			showMessage();
			return false;
		}
		
		adapter = new CustomListViewAdapter(this, R.layout.list_item, newsContainer);		
		return true;
	}
	
	public void showDesign()
	{
		ListView listView = (ListView) findViewById(R.id.listOfInfo);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {
				detailedInfo.putExtra("infoID", arg2);
				startActivityForResult(detailedInfo, 0);
			}
		});
		setSize();
	}
	
	public void setSize()
	{
		Display window = getWindowManager().getDefaultDisplay();
        width = window.getWidth();
        height = window.getHeight();
        
        RelativeLayout headerLayout = (RelativeLayout)findViewById(R.id.headerback);
        headerLayout.setLayoutParams(params)
	}
	
	public void showMessage()
	{
		AlertDialog showInfo = new AlertDialog.Builder(this).create();
		showInfo.setTitle("Warning!");
		showInfo.setMessage(Connection.INFO);
		showInfo.setIcon(R.drawable.ic_launcher);
		showInfo.setButton("OK", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});
		showInfo.show();
	}
}