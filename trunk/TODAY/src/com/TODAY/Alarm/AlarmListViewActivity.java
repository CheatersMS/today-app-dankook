package com.TODAY.Alarm;

import com.TODAY.R;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * ListView
 *
 * @author Mike
 */
public class AlarmListViewActivity extends Activity {

	DataListView list;
	IconTextListAdapter adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // window feature for no title - must be set prior to calling setContentView.
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // create a DataGridView instance
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        list = new DataListView(this);

        // create an DataAdapter and a MTable
        adapter = new IconTextListAdapter(this);

		// add items
		Resources res = getResources();
		//adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon05), "�߾��� ��Ʈ����", "30,000 �ٿ�ε�", "900 ��"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.alarmicon), "8:00pm","mon,tue"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.alarmicon), "9:00pm","mon,tue"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.alarmicon), "10:00pm","mon,tue"));
		
		
		
		
		// call setAdapter()
		list.setAdapter(adapter);
		


		// use adapter.notifyDataSetChanged() to apply changes after adding items dynamically
		// adapter.notifyDataSetChanged();


		// set listener
		list.setOnDataSelectionListener(new OnDataSelectionListener() {
			public void onDataSelected(AdapterView parent, View v, int position, long id) {
				IconTextItem curItem = (IconTextItem) adapter.getItem(position);
				String[] curData = curItem.getData();

				Toast.makeText(getApplicationContext(), "Selected : " + curData[0], 2000).show();
			}
		});


        // display as the main layout
        setContentView(list, params);
    }





}