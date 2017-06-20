package FileManagement;

import java.io.*;
import java.util.*;

public class FileManagement {

private static BufferedReader in;

public static void main(String args[]) throws IOException{

		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			String choice = "1";
			String file_name = "";
			CSVFile csv = new CSVFile();
			ArrayList<Record> Records;
			ArrayList<Record> Records_trans;
			while (!choice.equals("5")){
				
				System.out.println();
				System.out.println("Enter 1 to sorted csv file:");
				System.out.println("Enter 2 to run transaction on csv file:");
				System.out.println("Enter 3 to category to search:");
				System.out.println("Enter 4 to analyze catagory:");
				System.out.println("Enter 5 to Quit:");
				System.out.println();

				System.out.println("Enter your choice:");
				choice = in.readLine();
				switch (choice) {
				case "1":
					// sort CSV file to a file.
					System.out.println("Enter the .csv Master file name:");
					file_name = in.readLine();
					Records = csv.CSVReadFile(file_name);
					csv.sort(Records);
					csv.save(Records, "list_sorted.csv");
					break;
				case "2":
					//Run the transaction file and save a sorted file.
					System.out.println("Enter transaction file name:");
					file_name = in.readLine();	
					Records_trans = csv.CSVReadFile(file_name);
					System.out.println("Enter .csv Master file name:");
					file_name = in.readLine();
					Records = csv.CSVReadFile(file_name);
					Records = csv.run_transaction(Records_trans,Records );
					csv.setSortDirection(true);
					csv.sort(Records);
					csv.save(Records, "list_sorted.csv");
					break;
				case "3":
				 // Search and write to file.
					 System.out.println("Enter csv file name:");
					 file_name = in.readLine();
					 Records = csv.CSVReadFile(file_name);
					 System.out.println("Enter category to search:");
					 String search_catagory = in.readLine();
					 Records = csv.search(Records, search_catagory);
					 csv.sort(Records);
					 csv.save(Records, "search_list_sorted.csv");
					 
					 // Read the file to generate graph
					 Records = csv.CSVReadFile("search_list_sorted.csv");
					 // Graph by district
					 Map<String, Double> rd = Record.analyze_records_catagory_by_district(Records);
					 Plot.plot_pie_chart(rd, "catagory.png", "PD District Report ");
					 
					// Graph by Resolution
					 Map<String, Double> rr = Record.analyze_records_catagory_by_resolution(Records);
					 Plot.plot_pie_chart(rr, "resolution.png", "Resolution Report ");
					 
					break;
				case "4" :
					// Analyze the report.
					 System.out.println("Enter .csv Master file name:");
					 file_name = in.readLine();
					Records = csv.CSVReadFile(file_name);
					Map<String, Double> r = Record.analyze_records_catagory(Records);
					Plot.plot_pie_chart(r, "Police_Report.png", "Police Report");
					break;
				case "5" :
					System.exit(0);
					break;
				}
			}

			System.exit(0);

		} catch(IOException e) {
			System.out.println("File doesn't exist");
			System.exit(0);
		}
	}
}
