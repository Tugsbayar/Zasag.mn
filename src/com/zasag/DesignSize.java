package com.zasag;

public class DesignSize 
{
	public final static int HEADER_SIZE_WIDTH = 600;
	public final static int HEADER_SIZE_HEIGHT = 61;
	
	public static int getHeight(int width)
	{
		return (width * HEADER_SIZE_HEIGHT) / HEADER_SIZE_WIDTH;
	}
}
