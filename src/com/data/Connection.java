package com.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.zasag.R;
import android.util.Log;

public class Connection 
{
	private static ArrayList<CleanedNews> newsContainer;
	public static String INFO = "";
	
	public static String getInfo(String url, String method, List<NameValuePair> params)
	{	 
		String line = null;

		try {
			if(method.equals("POST"))
			{
				Log.i("url", url);
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
				HttpResponse httpResponse = httpClient.execute(httpPost);
				Log.i("POST", "get response done...");
				HttpEntity httpEntity = httpResponse.getEntity();
				line = EntityUtils.toString(httpEntity,"UTF-8");
				Log.i("POST", "connection done!");
			}
			
			else if(method.equals("GET"))
			{
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);

				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				line = EntityUtils.toString(httpEntity,"UTF-8");
			}
			
		} catch (UnsupportedEncodingException e) {
			line = null;
			INFO = Default.errorInternetConn;
		} catch (MalformedURLException e) {
			line = null;
			INFO = Default.errorInternetConn;
		} catch (IOException e) {
			line = null;
			INFO = Default.errorInternetConn;
		}
		catch (Exception e) {
			Log.e("Exception Connection", e.getMessage());
		}

		return line;
	}
	
	public static ArrayList<CleanedNews> convertToMyFormatInfo(String result)
	{
		try
		{
			newsContainer = new ArrayList<CleanedNews>();
			CleanedNews cNews;
			JSONArray jArray = new JSONArray(result);
			JSONObject jObj = null;
			
			for(int i = 0; i < jArray.length(); i ++)
			{
				jObj = jArray.getJSONObject(i);
				cNews = new CleanedNews();
				cNews.setTitle(jObj.getString("title"));
				Log.i("id", jObj.getString("id"));
				cNews.setDate(jObj.getString("desc"));
				cNews.setContent(jObj.getString("content"));
				cNews.setDate(jObj.getString("date"));	
				//cNews.setImage_path(R.drawable.bbc);
				cNews.setPhoto(R.drawable.notepad);
				newsContainer.add(cNews);
			}
		}
		catch(Exception ex)
		{
			INFO = Default.errorInternetConn;
			return null;
		}
		return newsContainer;
	}
	
	
}
