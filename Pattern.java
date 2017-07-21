package com.example.jakki.knitabit;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.*;
import java.util.*;

public class Pattern extends AppCompatActivity
{
	enum weight{
		LACE, SUPERFINE, FINGERING, FINE, SPORT, LIGHTWORSTED, DK, MEDIUM,
		WORSTED, BULKY, SUPERBULKY, NOTSPEC
	}
	
	enum type{
		SCARF, SWEATER, HAT, SOCKS, SHAWL, HOME, TOY, GLOVES, BLANKET, DISHIE, NOTSPEC
	}
	
	private type t;			//type of project to be used as a tagging system for sorting
	private weight w;		//weight of yarn required
	
	private int numColors;	//number of colors
	private int amountYarn;	//amount of yarn needed in grams
	private int szNeedles;	//size of needles needed
	private int numStitches; // number starting stitches
	private int gauge;		//calculated by the knitter inputting a number of rows knitted and
	                        //the length of the rows
	private int sz;			//either circumference or width if it's circular or straight
	private int height;		//length of finished product
	private int totalRows;	//total number of rows in a product
	
	private boolean CorS;	//true if this is a circular pattern false if otherwise
	private String name;
	
	//The order stores the name of a section in guide and the number of times it is repeated
	//as a mypair, which stores an int and a string
	//if you finish going through order, but haven't reached the number of rows
	//then you start at the beginning of the order
	private Map<String, Section> guide;
	protected ArrayList<MyPair> order;
	
	public Pattern()
	{
		order = new ArrayList<MyPair>();
		
		type t = type.NOTSPEC;
		weight w = weight.NOTSPEC; //set everything to negatives incase the knitter doesn't update them
		numColors = -1;	//number of colors
		amountYarn = -1;	//amount of yarn needed in grams
		szNeedles = -1;	//size of needles needed
		numStitches = -1; // number starting stitches
		gauge = -1;		
		sz = -1;
		height = -1;
		totalRows = -1;
	
		CorS = false;	//true if this is a circular pattern false if otherwise
		name = "Default";
    }
	
	public Pattern(String n)
	{
		this();
		name = n;
    }
	
	
	public int getNumColors(){return numColors;}
	public int changeNumColors(int a)
	{
		numColors = a;
		return numColors;
	}
	
	public int getAmountYarn(){return amountYarn;}
	public int changeAmountYarn(int a)
	{
		amountYarn = a;
		return amountYarn;
	}
	
	public int getSzNeedles(){return szNeedles;}
	public int changeSzNeedles(int a)
	{
		szNeedles = a;
		return szNeedles;
	}
	
	public int getNumStitches(){return numStitches;}
	public int changeNumStitches(int a)
	{
		numStitches = a;
		return numStitches;
	}
	
	public int getGauge(){return gauge;}
	public boolean calculateGauge(int rows, int inches)
	{
	    if(inches == 0)
	        return false;
	    else
            gauge = rows/inches;
            
        return true; 
		
	}
	
	public int getSz(){return sz;}
	public int changeSz(int a)
	{
		sz = a;
		return sz;
	}
	
	public int getHeight(){return height;}
	public int changeHeight(int a)
	{
		height = a;
		return height;
	}
	
	public int getTotalRows(){return totalRows;}
	public int changeTotalRows(int a)
	{
		totalRows = a;
		return totalRows;
	}
	
	//Will Calculate the total rows based off of a completed order and guide
	public int calcTotalRows()
	{
		int rows = 0;
		
		for(MyPair a : order)
		{
			rows += guide.get(a.getSec()).getRows() * a.getReps();
		}		
		
		totalRows = rows;
		return rows;
	}
	
	
	public weight getWeight(){return w;}
	public weight changeWeight(weight a)
	{
		w = a;
		return w;
	}
	
	public type getType(){return t;}
	public type changeType(type a)
	{
		t = a;
		return t;
	}
	
	public boolean getCorS(){return CorS;}
	public boolean changeCorS(boolean a)
	{
		CorS = a;
		return CorS;
	}
	
	public String getName(){return name;}
	public String changeName(String a)
	{
		name = a;
		return name;
	}
	
	//adds a section to the guide if it doesn't already exist
	//adds the name of the section and it's reps as next in the order
	//sec is the section you want to add re is how many repetitions you want it to complete
	public void addSection(Section sec, int re)
	{
		
		if(!guide.containsKey(sec.getName()))
		{
			guide.put(sec.getName(), sec);
		}
		
		order.add(new MyPair(sec.getName(), re));	
	}
	
	//removes a section from both the guide and the order
	public void remSection(String n)
	{
		guide.remove(n);
		
		for(int i = 0; i < order.size(); i++)
				if(order.get(i).getSec() == n)
					order.remove(i);
	}
	
	//removes an instance of a section from the order
	public void remSecFromOrder(int i)
	{
		order.remove(i);
	}
	
	public Section getSection(int i)
	{
		return guide.get((order.get(i)).getSec());
	}
	
	public int getReps(int i)
	{
		return (order.get(i)).getReps();
	}
	
	public class MyPair implements Serializable
	{
    private final String sec;
    private final int rep;

    public MyPair(String aSec, int aRep)
		{
			sec   = aSec;
			rep = aRep;
		}
		
		public String getSec()   { return sec; }
		public int getReps() { return rep; }
	}
	
}







