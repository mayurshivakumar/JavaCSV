package FileManagement;


	import java.io.File;
	import java.io.IOException;
	import java.util.Map;

	import org.jfree.chart.ChartFactory;
	import org.jfree.chart.ChartUtilities;
	import org.jfree.chart.JFreeChart;
	import org.jfree.data.general.DefaultPieDataset;

	public class Plot {
		
		/**
		   * Plots the pie chart.
		   *
		   * @param  Map<String, Double>, file path to save the chart and name of the chart.
		   * 
		   * @return  N/A
		   * 
		   */
      
		public static void plot_pie_chart(Map<String, Double> data, String filePath, String chartTitle)
		{
			DefaultPieDataset dataset = new DefaultPieDataset( );
			  for(Map.Entry m:data.entrySet()){  
				  dataset.setValue((String) m.getKey(), new Double( (double) m.getValue() ) );
				  }  
			  JFreeChart chart = ChartFactory.createPieChart(
				         chartTitle,   // chart title
				         dataset,          // data
				         true,             // include legend
				         true,
				         false);
				         
				      int width = 1500;   /* Width of the image */
				      int height = 1500;  /* Height of the image */ 
				      File pieChart = new File(filePath); 
				      try {
						ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
						System.out.println(filePath +" graph generated.");
						 System.out.println();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
		}
	}
