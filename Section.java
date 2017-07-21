package com.example.jakki.knitabit;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.*;
import java.util.*;
import android.os.Parcel;
import android.os.Parcelable;

public class Section implements Parcelable
{
	private String name;
	private int numRows;
	
	private int currentRow;
	
	//private Boolean conCirc = true; // This only becomes relevant if we make it possible
									// to save sections seperately from patterns
	//private Boolean Circ;	//true if the section is circular
	
	private ArrayList<String> rows;
	
	public Section()
	{
		name = "A Section";
		numRows = 0;
		
		//Circ = false;
		
		currentRow = 0;
		rows = new ArrayList<String>(numRows);
	}
	
	public Section(String n, int nr)
	{
		this();
		
		name = n;
		numRows = nr;
		
		//Circ = c;
	}
	
	public Section(String n)
	{
		this();
		
		name = n;
		
		
	//	Circ = c;
	}
	
	public String getName(){return name;}
	public void changeName(String i)
	{
		name = i;
	}
	
/*	public boolean getCirc(){return Circ;}
	public void changeCirc(boolean i)
	{
		Circ = i;
	}*/
	
	public int getRows(){return numRows;}
	public int changeRows(int i)
	{
		numRows = i;
		return numRows;
	}
	
/*	//returns true if you can use the section with a straight pattern
	public Boolean isStraight()
	{
		return(!Circ || Circ && conCirc);
	}
	
	//returns true if you can use the section with a circular pattern
	public Boolean isCircular()
	{
		return(Circ);
	}
*/	
	//returns the string instruction of the current row
	public String getCurrentRow()
	{
	    return rows.get(currentRow);
	}
	
	//returns the string instruction of the next row
	//if the current row is equal to the total number of rows
	//then current row will be set to 0
	public String getNextRow()
	{
		if(currentRow == numRows)
			currentRow = 0;
		    
		return rows.get(currentRow++);
	}
	
	//returns the string instruction of the previous row
	//if the current row is equal to zero
	//then current row will be set to the last row in the section
	public String getPrevRow()
	{
		if(currentRow == 0)
			currentRow = numRows - 1;
		
		return rows.get(currentRow--);
	}

	//adds a string instruction of a row
	public void addRow(String r)
	{
		rows.add(r);
	}
	
    protected Section(Parcel in) {
        name = in.readString();
        numRows = in.readInt();
        currentRow = in.readInt();
        if (in.readByte() == 0x01) {
            rows = new ArrayList<String>();
            in.readList(rows, String.class.getClassLoader());
        } else {
            rows = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(numRows);
        dest.writeInt(currentRow);
        if (rows == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(rows);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Section> CREATOR = new Parcelable.Creator<Section>() {
        @Override
        public Section createFromParcel(Parcel in) {
            return new Section(in);
        }

        @Override
        public Section[] newArray(int size) {
            return new Section[size];
        }
    };
}
