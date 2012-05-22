package com.TODAY.ViewSet;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.TODAY.R;
import com.TODAY.HtmlParserByJuyoungJin.ccFood;
import com.TODAY.HtmlParserByJuyoungJin.ccHTMLParsing;
import com.TODAY.ListViewSet.ccFoodItem;

public class Food extends LinearLayout implements ParseInfo{

	View view;
	Context context;
	Detail_Food detailView;
	ArrayList<ccFoodItem> list;
	public Food(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_food, this, true);
		
		
	}
	


	@Override
	public void parsingInfo() {
		// TODO Auto-generated method stub
//		ccHTMLParsing parser = new ccHTMLParsing();
//		String str = parser.DownloadHtml(0);
		list = new ArrayList<ccFoodItem>(); 	// for testing

		
		
		
		/************** will be used
		ccFood[] FD = ccFood.stringToClass();
		for(int i=0;i<FD.length;i++)
		{
			list.add(new ccFoodItem(FD[i].getStore(), FD[i].getMenu()));
		}
		
		************************/
		list.add(new ccFoodItem("�л��Ĵ�", "�ѹ� 1,900 �߽�:�ҹ�(��:������), ���, �����, ���ʹ�ħ, ��ġ(����:������) ����:�ҹ�(��:������), �̿���, ��ġ����, ���ֳ���, ��ġ(����:������)"));
		list.add(new ccFoodItem("�л��Ĵ�", "��õ 3,000 �߽�:���̶��̽� (����:�̱���)&ġŲ�(����:������) ����:���������"));
		list.add(new ccFoodItem("�л��Ĵ�", "�Ҵ� 3,000 �߽�:�ø޹мҹ�*�ָԹ� ����:��ġ��ġ�(����:������)"));
		
		//Log.i("Food Parsing", str);
		
	}

	@Override
	public ArrayList<?> getArrayList() {
		// TODO Auto-generated method stub

		
		return list;
	}

	@Override
	public LinearLayout getDetailView() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
}
