package com.mangesh.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

/**
 * 
 * @author mvadk WhUserUtilConfig Pie chart and and bar chart method
 *
 */
@Component
public class WhUserUtilConfig {

	/**
	 * bar chart method()
	 * 
	 * @param pathGet the Original store location of pie chart
	 * @param data    Get the list of data from db side
	 */
	public void generatePieChart(String path, List<Object[]> data) {

		DefaultPieDataset dataset = new DefaultPieDataset();

		for (Object[] ob : data) {
			dataset.setValue(String.valueOf(ob[0]), Double.valueOf(ob[1].toString()));
		}
		JFreeChart chart = ChartFactory.createPieChart3D("WHUSER ID TYPE PIE CHART", dataset);
		// chart.setBackgroundPaint(Color.GREEN);

		try {
			ChartUtils.saveChartAsJPEG(new File(path + "/whUserPie.jpg"), chart, 500, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * bar chart method()
	 * 
	 * @param pathGet the Original store location of pie chart
	 * @param data    Get the list of data from db side
	 */
	public void generateBarChart(String path, List<Object[]> data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Object[] ob : data) {
			dataset.setValue(Double.valueOf(ob[1].toString()), String.valueOf(ob[0]), "");
		}

		JFreeChart chart = ChartFactory.createBarChart("WHUSER ID TYPE BAR CHART", "WHUSER ID TYPE", "COUNT", dataset);
		// chart.setBackgroundPaint(Color.CYAN);
		// chart.setBorderPaint(Color.WHITE);
		try {
			ChartUtils.saveChartAsJPEG(new File(path + "/whUserBar.jpg"), chart, 500, 500);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
