package com.TODAY.ViewSet;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.TODAY.R;
import com.TODAY.HtmlParserByJuyoungJin.ccHTMLParsing;
import com.TODAY.HtmlParserByJuyoungJin.ccTimeTable;
import com.TODAY.ListViewSet.ccTTItem;

public class TimeTable extends LinearLayout implements ParseInfo{
	
	private Context context;
	Detail_TimeTable detailView;
	ArrayList<ccTTItem> list;
	ArrayList<String> todayTimeTable;
	public TimeTable(Context context) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_timetable, this, true);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parsingInfo() {
		// needed to add
		// TODO Auto-generated method stub
//		
//		ccHTMLParsing parser = new ccHTMLParsing();
//		String str = parser.DownloadHtml(1);
//		todayTimeTable = new ArrayList<String>();
//		todayTimeTable.add(str);
		
		
		list = new ArrayList<ccTTItem>();
	
		list.add(new ccTTItem("����Ʈ�������", "��6,7", "�ڿ���517"));
		list.add(new ccTTItem("�ڷᱸ��", "ȭ6,7, ��8", "�ڿ���516"));
		list.add(new ccTTItem("����Ʈ�������", "��6,7", "�ڿ���517"));
		// for testing!!!
		
		
		/****************** This part will be used *************
		ccTimeTable[] TT = ccTimeTable.stringToClass() ;
		for(int i=0; i<TT.length; i++)
		{
			list.add(new ccTTItem(TT[i].getTitle(), TT[i].getTime(), TT[i].getTime())) ;			// need to be modified
		}			
		
		*****************************/
		
		//list = parser.getTimeTable().getArrayList();
		
//		Log.i("TT Parsing Info", str);
	}
	
	public ArrayList<String> getTodayTimeTable()
	{
		return todayTimeTable;
	}

	@Override
	public ArrayList<?> getArrayList() {
		
		return list;
		// TODO Auto-generated method stub
	}

	@Override
	public LinearLayout getDetailView() {
		// TODO Auto-generated method stub
//		detailView = new Detail_TimeTable(context);
//		return detailView;
		return null;
	}
	
}
