package org.androidtown.ui.listview;

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
public class SampleListViewActivity extends Activity {

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
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon05), "�߾��� ��Ʈ����", "30,000 �ٿ�ε�", "900 ��"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon06), "���� - ��ȣ�� ����", "26,000 �ٿ�ε�", "1500 ��"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon05), "ģ��ã�� (Friends Seeker)", "300,000 �ٿ�ε�", "900 ��"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon06), "���� �˻�", "120,000 �ٿ�ε�", "900 ��"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon05), "����ö �뼱�� - ����", "4,000 �ٿ�ε�", "1500 ��"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon06), "����ö �뼱�� - ����", "6,000 �ٿ�ε�", "1500 ��"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon05), "����ö �뼱�� - LA", "8,000 �ٿ�ε�", "1500 ��"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon06), "����ö �뼱�� - ������", "7,000 �ٿ�ε�", "1500 ��"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon05), "����ö �뼱�� - �ĸ�", "9,000 �ٿ�ε�", "1500 ��"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon06), "����ö �뼱�� - ������", "38,000 �ٿ�ε�", "1500 ��"));

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