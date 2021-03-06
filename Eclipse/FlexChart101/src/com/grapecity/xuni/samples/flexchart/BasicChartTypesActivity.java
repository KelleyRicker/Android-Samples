package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Switch;

import com.grapecity.xuni.flexchart.*;

public class BasicChartTypesActivity extends Activity
{

	private FlexChart mChart;
	private Spinner mChartTypeSpinner;
	private Spinner mStackingSpinner;
	private Switch mRotatedSwitch;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic_chart_types);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);
		mChartTypeSpinner = (Spinner) findViewById(R.id.chartTypeSpinner);
		mStackingSpinner = (Spinner) findViewById(R.id.stackingSpinner);
		mRotatedSwitch = (Switch) findViewById(R.id.rotatedSwitch);

		// set the binding for X-axis of FlexChart
		mChart.setBindingX("name");

		// initialize series elements and set the binding to variables of
		// ChartPoint
		ChartSeries series_sales = new ChartSeries(mChart, "Sales", "sales");
		ChartSeries series_expenses = new ChartSeries(mChart, "Expenses", "expenses");
		ChartSeries series_downloads = new ChartSeries(mChart, "Downloads", "downloads");

		// add series to list
		mChart.getSeries().add(series_sales);
		mChart.getSeries().add(series_expenses);
		mChart.getSeries().add(series_downloads);

		// setting the source of data/items in FlexPie
		mChart.setItemsSource(ChartPoint.getList());

		// create custom adapter for first spinner and initialize with string
		// array
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.chartTypeSpinnerValues, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mChartTypeSpinner.setAdapter(adapter1);
		mChartTypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// set chartType based on user selection
				switch (position)
				{
					case 0:
						mChart.setChartType(ChartType.AREA);
						break;
					case 1:
						mChart.setChartType(ChartType.SPLINEAREA);
						break;
					case 2:
						mChart.setChartType(ChartType.SPLINESYMBOLS);
						break;
					case 3:
						mChart.setChartType(ChartType.SPLINE);
						break;
					case 4:
						mChart.setChartType(ChartType.LINESYMBOLS);
						break;
					case 5:
						mChart.setChartType(ChartType.LINE);
						break;
					case 6:
						mChart.setChartType(ChartType.SCATTER);
						break;
					case 7:
						mChart.setChartType(ChartType.BAR);
						break;
					case 8:
						mChart.setChartType(ChartType.COLUMN);
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		// create custom adapter for second spinner and initialize with string
		// array
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.stackingSpinnerValues, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mStackingSpinner.setAdapter(adapter2);
		mStackingSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// set stacking type
				switch (position)
				{
					case 0:
						mChart.setStackingType(ChartStackingType.NONE);
						break;
					case 1:
						mChart.setStackingType(ChartStackingType.STACKED);
						break;
					case 2:
						mChart.setStackingType(ChartStackingType.STACKED100PC);
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		mRotatedSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				// set rotated flag
				mChart.setRotated(isChecked);
			}
		});
	}
}
