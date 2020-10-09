package com.mangesh.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author mvadk This is generated Order Method pie chart and bar chart
 * 
 */
@Component
public class OrderUtilConfig {

	private static final Logger log = LoggerFactory.getLogger(OrderUtilConfig.class);

	/**
	 * Pie chart Method
	 * 
	 * @param path Get the Original store location of pie chart
	 * @param data Get the list of data from db side
	 */
	public void generatePieChart(String path, List<Object[]> data) {

		log.info("OrderUtilConfig.generatePieChart() Execution started....!!");

		DefaultPieDataset dataset = new DefaultPieDataset();

		for (Object[] ob : data) {
			dataset.setValue(String.valueOf(ob[0]), Double.valueOf(ob[1].toString()));
		}
		JFreeChart chart = ChartFactory.createPieChart3D("ORDER TYPE PIE CHART", dataset);
		// chart.setBackgroundPaint(Color.GREEN);

		try {
			ChartUtils.saveChartAsJPEG(new File(path + "/orderPie.jpg"), chart, 500, 300);
		} catch (IOException e) {
			log.error("Got Error In Order Method Pie chart.....!!");
			e.printStackTrace();
		}
		log.info("OrderUtilConfig.generatePieChart() Execution Ended.....!!");
	}

	/**
	 * bar chart method()
	 * 
	 * @param pathGet the Original store location of pie chart
	 * @param data    Get the list of data from db side
	 */
	public void generateBarChart(String path, List<Object[]> data) {
		log.info("OrderUtilConfig.generateBarChart() Execution started....!!");
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Object[] ob : data) {
			dataset.setValue(Double.valueOf(ob[1].toString()), String.valueOf(ob[0]), "");
		}

		JFreeChart chart = ChartFactory.createBarChart("ORDER TYPE BAR CHART", "ORDER TYPE", "COUNT", dataset);
		// chart.setBackgroundPaint(Color.CYAN);
		// chart.setBorderPaint(Color.WHITE);
		try {
			ChartUtils.saveChartAsJPEG(new File(path + "/orderBar.jpg"), chart, 500, 500);
		} catch (IOException e) {
			log.error("Got Error In Order Method Bar chart.....!!");
			e.printStackTrace();
		}
		log.info("OrderUtilConfig.generateBarChart() Execution Ended....!!");
	}

}
