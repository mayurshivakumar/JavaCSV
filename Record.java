package FileManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Record {
	public int id;
	public String category;
	public String descript;
	public String dayOfTheWeek;
	public String date;
	public String time;
	public String pdDistrict;
	public String resolution;
	public String address;
	public String transaction;

	/**
	   * Constructor for transaction file fields.
	   */
	public Record( int id, String category, String descript, String dayOfTheWeek,
	String date, String time, String pdDistrict,String resolution,String address,
  String transaction)
	{
		this.id = id;
		this.category = category;
	  this.descript = descript;
		this.dayOfTheWeek = dayOfTheWeek;
		this.date = date;
		this.time = time;
		this.pdDistrict = pdDistrict;
		this.resolution = resolution;
		this.address = address;
	  this.transaction = transaction;
	}

	/**
	   * Constructor for the master file fields.
	   */
	public Record( int id, String category, String descript, String dayOfTheWeek,
	String date, String time, String pdDistrict,String resolution,String address)
	{
		this.id = id;
		this.category = category;
	    this.descript = descript;
		this.dayOfTheWeek = dayOfTheWeek;
		this.date = date;
		this.time = time;
		this.pdDistrict = pdDistrict;
		this.resolution = resolution;
		this.address = address;
	}

	/**
	   * Reads the Category from the array of records and counts the frequency of it.
	   *
	   * @param  ArrayList<Record>
	   * 
	   * @return  Map<String, Double>
	   * 
	   */
	public static Map<String, Double> analyze_records_catagory(ArrayList<Record> Records)
	{
		 Map<String, Double> map=new HashMap<String, Double>();
		 for(Record rec : Records){
			 if(map.containsKey(rec.category)){
				 // has category increment value
				 map.put(rec.category, map.get(rec.category) + 1);
			 }
			 else {
				 map.put(rec.category, (double) 1);
			 }
		 }
       return map;
	}
	
	/**
	   * Reads the Location value(PDDistrict) from the array of records and counts the frequency of it.
	   *
	   * @param  ArrayList<Record>
	   * 
	   * @return  Map<String, Double>
	   * 
	   */
	
	public static Map<String, Double> analyze_records_catagory_by_district(ArrayList<Record> Records)
	{
		 Map<String, Double> map=new HashMap<String, Double>();
		 for(Record rec : Records){
			 if(map.containsKey(rec.pdDistrict)){
				 // has pdDistrict increment value
				 map.put(rec.pdDistrict, map.get(rec.pdDistrict) + 1);
			 }
			 else {
				 map.put(rec.pdDistrict, (double) 1);
			 }
		 }
       return map;
	}
	
	/**
	   * Reads the Resolution value from the array of records and counts the frequency of it.
	   *
	   * @param  ArrayList<Record>
	   * 
	   * @return  Map<String, Double>
	   * 
	   */
	public static Map<String, Double> analyze_records_catagory_by_resolution(ArrayList<Record> Records)
	{
		 Map<String, Double> map=new HashMap<String, Double>();
		 for(Record rec : Records){
			 if(map.containsKey(rec.resolution)){
				 // has pdDistrict increment value
				 map.put(rec.resolution, map.get(rec.resolution) + 1);
			 }
			 else {
				 map.put(rec.resolution, (double) 1);
			 }
		 }
       return map;
	}



}
