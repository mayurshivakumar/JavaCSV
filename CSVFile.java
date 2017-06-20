package FileManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class CSVFile {

  String fileName;
  boolean  IsAsc = true;

  public CSVFile()
  {}

  /**
   * Reads the CSV file and returns array of records.
   *
   * @param  file String  file path to import the CSV file.
   * 
   * @return  ArrayList<Record>
   * 
   */
	public ArrayList<Record> CSVReadFile(String file) throws IOException{
		ArrayList<Record> Records = new ArrayList<Record>();
		try(BufferedReader in = new BufferedReader(new FileReader(file)))
		{
			String ln;
			boolean skip_first_line = true;
			while( (ln = in.readLine()) !=null) {
				
				//skip first line - column name.
				if(skip_first_line)
				{
					skip_first_line = false;
					continue;
				}
				String row[] = ln.split(",");
			    if(row.length == 9) {

			    Records.add(new Record(
			    		Integer.parseInt(row[0]), row[1], row[2], row[3], row[4],
			    		row[5], row[6], row[7], row[8]));
			  }
			    else {
			    	Records.add(new Record(
              Integer.parseInt(row[0]), row[1], row[2], row[3], row[4],
            row[5], row[6], row[7],  row[8], row[9]));
			    }
			}
			return Records;
		}


	}
	
	/**
	   * Sorts the array of records read.
	   *
	   * @param  Array of records.
	   * 
	   * @return  N/A
	   */

	public void sort(ArrayList<Record> Records)
	{
		 Collections.sort(Records, new Compare(this.IsAsc));
	}

	/**
	   * Prints array of record read and Sorted.
	   *
	   * @param  Array of record.
	   * 
	   * @return  N/A
	   * 
	   */
	public void print(ArrayList<Record> Records)
	{//iterate through record
		for(Record rec : Records){
			System.out.println(rec.id +"\t" +
							   rec.category + "\t" +
							   rec.descript +"\t" +
							   rec.dayOfTheWeek +"\t" +
							   rec.date +"\t" +
							   rec.time +"\t" +
							   rec.pdDistrict +"\t" +
							   rec.resolution +"\t" +
							   rec.address
							   );
		}
		System.out.println();
	}

	/**
	   * Sets the sort ascending order.
	   *
	   * @param  Boolean value.
	   * 
	   * @return  N/A
	   * 
	   */
	public void setSortDirection(boolean b)
	{
		this.IsAsc = b;
	}
	
	/**
	   * Writes the array of records to the CSV file.
	   *
	   * @param  Array of record and string path to export the results.
	   * 
	   * @return  N/A
	   * 
	   */

	public void save(ArrayList<Record> Records, String filePath) throws IOException{
		try(BufferedWriter out = new BufferedWriter(new FileWriter(filePath)))
		{
			out.write("IncidntNum,Category,Descript,DayOfWeek,Date,Time,PdDistrict,Resolution,Address");
			out.write("\n");
			for(Record rec : Records){

				out.write(rec.id +"," +
  							   rec.category + "," +
  							   rec.descript +"," +
  							   rec.dayOfTheWeek +"," +
  							   rec.date +"," +
  							   rec.time +"," +
                   rec.pdDistrict +"," +
                   rec.resolution +"," +
                   rec.address
						  );
				out.write("\n");
			}
			out.close();
		}
	}

	/**
	   * Searches for string value specified in array of record.
	   *
	   * @param  Array of record and string value to search.
	   * 
	   * @return  ArrayList<Record>
	   * 
	   */
	
	public ArrayList<Record> search(ArrayList<Record> Records, String search_catagory)
	{
    ArrayList<Record> searchRecords = new ArrayList<Record>();
		boolean recordFound = false;
		for(Record rec : Records){
			if(rec.category.equals(search_catagory))
			{
			   // Create records to be written to the file.
         recordFound  = true;
         searchRecords.add(rec);
			}
		}
		if(recordFound)
		{
			System.out.println("Records found, file generated.");
			System.out.println();
		}
		else
		{
      System.out.println("Record not found.");
		}

    return searchRecords;
	}

	/**
	   * Reads the transaction CSV file and appends the array of records to master file.
	   *
	   * @param  Master file Array of records and transaction Array of records.
	   * 
	   * @return  ArrayList<Record>
	   * 
	   */
	public ArrayList<Record> run_transaction(ArrayList<Record> Records_trans, ArrayList<Record> Records )
	{
		for(Record rec : Records_trans){

			if(rec.transaction.equals("insert")) {
			 //append the record
				Records.add(rec);
			}

			if(rec.transaction.equals("update")) {
				 //update the record
				Iterator<Record> i = Records.iterator();
				while (i.hasNext()){
					Record rec_temp = i.next();
					if(rec.id == rec_temp.id){
						// this is the record update
						rec_temp.category = rec.category;
						rec_temp.descript = rec.descript;
						rec_temp.dayOfTheWeek  = rec.dayOfTheWeek;
						rec_temp.date = rec.date;
						rec_temp.time = rec.time;
						rec_temp.pdDistrict = rec.pdDistrict;
						rec_temp.resolution = rec.resolution;
						rec_temp.address = rec.address;
					}
				}

			}
			if(rec.transaction.equals("delete")) {

				Iterator<Record> i = Records.iterator();
				while (i.hasNext()) {
				   Record r = i.next();
				  if(rec.id == r.id){
					  i.remove();
				  }
				}

			}
		}

		return Records;
	}

}
