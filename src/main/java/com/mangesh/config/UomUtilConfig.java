package com.mangesh.config;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;
/**
 * 
 * @author mvadk Uom Pie chart and and bar chart method
 *
 */
@Component
public class UomUtilConfig {

	/**
	 * bar chart method()
	 * 
	 * @param pathGet the Original store location of pie chart
	 * @param data    Get the list of data from db side
	 */
	public void generatePieChart(String path, List<Object[]> data) {

		DefaultPieDataset dataSet = new DefaultPieDataset();
		for (Object[] ob : data) {
			dataSet.setValue(String.valueOf(ob[0]), Double.valueOf(ob[1].toString()));
		}

		JFreeChart chart = ChartFactory.createPieChart3D("UOM TYPE DATA AND COUNT", dataSet);
		PiePlot plot = (PiePlot) chart.getPlot();
		PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0} :{1} ({2}) ", new DecimalFormat("0"),
				new DecimalFormat("0%"));
		plot.setLabelGenerator(gen);

		try {
			ChartUtils.saveChartAsJPEG(new File(path + "/uomA.jpg"), chart, 500, 300);
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

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (Object[] ob : data) {
			dataSet.setValue(Double.valueOf(ob[1].toString()), String.valueOf(ob[0]), "");
		}
		JFreeChart chart = ChartFactory.createBarChart("UOM TYPE DATA AND COUNT", "UOM TYPE", "COUNT", dataSet);
		try {
			ChartUtils.saveChartAsJPEG(new File(path + "/uomB.jpg"), chart, 500, 350);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
