package com.mateuszwiorek.budget;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class My3DCategoryChart extends JFrame {
    private static final long serialVersionUID = -8625975218180144099L;


    public My3DCategoryChart(String title) {
        super(title);
        JFreeChart chart = create3DBarChart(createCategoryDataset(), PlotOrientation.VERTICAL);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(800,600));
        setContentPane(chartPanel);
    }

    private CategoryDataset createCategoryDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Money> listToChart = new BudgetDAO().viewAll();
        if(!listToChart.isEmpty()){
        for (int i=0; i<listToChart.size(); i++) {
            if(listToChart.get(i).getMoneyType()=="incomes")
                dataset.addValue(listToChart.get(i).getValue(), listToChart.get(i).getName(), "Incomes");
            else if(listToChart.get(i).getMoneyType()=="expenses")
                dataset.addValue(listToChart.get(i).getValue()*(-1), listToChart.get(i).getName(), "Expenses");

            }
        }

        return dataset;

    }


     JFreeChart create3DBarChart(CategoryDataset dataset,
                                        PlotOrientation plotOrientation) {
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Chart for incomes and expenses", //Chart Title
                "Type", //Domain Axis Label
                "Ammount in PLN", //Range Axis Label
                dataset, //Data
                plotOrientation, //Orientation
                true, //Include Legend
                true, //Tooltips
                false //Urls
        );
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(CategoryLabelPositions
                .createUpRotationLabelPositions(Math.PI / 16.0));

        CategoryItemRenderer renderer = plot.getRenderer();
        BarRenderer r = (BarRenderer) renderer;
        r.setMaximumBarWidth(0.1);
        chart.setBackgroundPaint(Color.cyan);
        return chart;

    }


    public void saveChart(JFreeChart chart, String fileLocation) {
        try {
            ChartUtilities.saveChartAsJPEG(new File(fileLocation), chart, 800,
                    600);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Problem occurred creating chart.");
        }
    }
}
