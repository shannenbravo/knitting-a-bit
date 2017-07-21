import java.io.*;
import java.util.*;

public class Project 
{
	//get these three from user input... somehow
	private String name;
	private String yarnName;
	private String colorWay;
	
	private String row;
	
	private int currentPair;
	private int currentRep;
	private int currentRow;
	
	private boolean current; //true if this project is the active pattern
	private boolean completed;
	
	private ArrayList<Integer> counters;
	private ArrayList<String> counterNames;
	
	private Pattern p;
	
	public Project()
	{
		name = " ";
		yarnName = " ";
		colorWay = " ";
		
		currentRow = 0;
		currentPair = 0;
		currentRep = p.getReps(currentPair);
		row = getNextRow();
		
		counters = new ArrayList<Integer>();
		counterNames = new ArrayList<String>();
		
		counters.add(0);
		counterNames.add("Current Row");
		
		current = false;
		completed = false;  
	}
	
	public Project(Pattern pat, String n)
	{
		this();
		p = pat;
		name = n;
	}
	
	public String getName(){return name;}
	
	public String getYarnName(){return yarnName;}
	public void changeYarnName(String n){yarnName = n;}
	
	public String getColorWay(){return colorWay;}
	public void changeColorWay(String a){colorWay = a;}
	
	public void newCounter(String n)
	{
		counters.add(0);
		counterNames.add(n);
	}
	
	public void setCurrent(boolean b)
	{
	    current = b;
	}
	
	public boolean isCurrent()
	{
		return current; 
	}
	
	public void incCounter(String n)
	{
		int i = counterNames.indexOf(n);
		counters.set(i, counters.get(i) + 1);
		
		if(i == 0)
			row = getNextRow();
	}
	
	public void removeCounter(String n)
	{
		int i = counterNames.indexOf(n);
		counters.remove(i);
		counterNames.remove(i);
	}
	
	public String getNextRow()
	{
		if(currentRow == p.getTotalRows())
			completed = true;
		
		else if(!completed)
		{
			if(currentRep == 0)
			{
				currentPair++;
				if(currentPair >= p.order.size())
					currentPair = 0;
				
				currentRep = p.getReps(currentPair);
			}
		
			row = (p.getSection(currentPair)).getNextRow();
			
			currentRep--;
			currentRow++;
			return row;
		}
		
		return "You're done!";
	}
	
}