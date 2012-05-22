package com.TODAY.Note;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.TODAY.R;

public class CalendarAdapter extends BaseAdapter
{
	private ArrayList<DayInfo> mDayList;
	private Context mContext;
	private int mResource;
	private LayoutInflater mLiInflater;
	private String yearAndMonth = "";
	//
	private ArrayList<String> mNoteList;
	private NotesDbAdapter mDbHelper;
	private Context context;
	private int counterForSearching = 0;
	private boolean isFirst = false;
	private boolean []hasContext;
	//
	/**
	 * Adpater ������
	 * 
	 * @param context
	 *            ���ؽ�Ʈ
	 * @param textResource
	 *            ���̾ƿ� ���ҽ�
	 * @param dayList
	 *            ��¥������ ����ִ� ����Ʈ
	 */
	public CalendarAdapter(Context context, int textResource, ArrayList<DayInfo> dayList, ArrayList<String> noteList,String yearAndMonth) //�������߰�
	{
		this.context = context;
		this.mContext = context;
		this.mDayList = dayList;
		this.mResource = textResource;
		this.mLiInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mNoteList = noteList;
		this.yearAndMonth = yearAndMonth;
		hasContext = new boolean[31];
		initHasContent();
	}
	
	public void initHasContent()
	{
		mDbHelper = new NotesDbAdapter(context);
        mDbHelper.open();       
        
        String dateForSearchingDb = "";
        for(int i=0;i<31;i++)
        {
        	dateForSearchingDb = yearAndMonth + "-" +String.valueOf(i+1);
        	Cursor cur = mDbHelper.fetchNoteByDay(dateForSearchingDb);		/// need to modify
        	if(cur.getCount() > 0)
    		{
    			hasContext[i] = true;
    		}
        	else
        		hasContext[i] = false;
        	cur.close();
        }
    			
        mDbHelper.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() //grid view���� ����ϴ�
	{
		// TODO Auto-generated method stub
		return mDayList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return mDayList.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) //�׸���信 ��¥ �ѷ��ִ� �κ�
	{
		
		
		DayInfo day = mDayList.get(position);
		DayViewHolde dayViewHolder;
		
		
		boolean isContained = false;

		if(convertView == null)
		{
			convertView = mLiInflater.inflate(mResource, null);

			if(position % 7 == 6)
			{
				convertView.setLayoutParams(new GridView.LayoutParams(getCellWidthDP()+getRestCellWidthDP(), getCellHeightDP()));
			}
			else
			{
				convertView.setLayoutParams(new GridView.LayoutParams(getCellWidthDP(), getCellHeightDP()));	
			}
			
			
			dayViewHolder = new DayViewHolde();

			dayViewHolder.llBackground = (LinearLayout)convertView.findViewById(R.id.day_cell_ll_background);
			dayViewHolder.tvDay = (TextView) convertView.findViewById(R.id.day_cell_tv_day);
			
			convertView.setTag(dayViewHolder);
		}
		else
		{
			dayViewHolder = (DayViewHolde) convertView.getTag();
		}

		
		if(day != null)
		{
			//Log.i("Day.getDay()", day.getDay());
			if(isFirst == false && day.getDay().compareTo("1") == 0)
			{
				isFirst = true;
				counterForSearching++;
			}

			if(counterForSearching > 0 && counterForSearching < 32)
			{
				int index = Integer.parseInt(day.getDay());
				if(hasContext[index-1] == true)
				{
					isContained = true;
				}
				
			}
			else
			{
				counterForSearching = 0;
			}

	        
	        
	        
	        
			dayViewHolder.tvDay.setText(day.getDay());

			if(day.isInMonth())
			{
				if(isContained)
				{
					dayViewHolder.tvDay.setTypeface(null, Typeface.BOLD_ITALIC);
					dayViewHolder.tvDay.setTextColor(Color.CYAN);
				}
				
				else if(position % 7 == 0)
				{
					dayViewHolder.tvDay.setTextColor(Color.RED);
				}
				else if(position % 7 == 6)
				{
					dayViewHolder.tvDay.setTextColor(Color.BLUE);
				}
				else
				{
					dayViewHolder.tvDay.setTextColor(Color.BLACK);
				}
			}
			else
			{
				dayViewHolder.tvDay.setTextColor(Color.GRAY);
			}
		//���⿡ 	mNoteList�迭�� ����Ǿ� �ִ� �κ��� ������ �־���� �ߴµ� �ȵ�.....
			if(mNoteList != null)
				if(mNoteList.contains(day.getDay()))
					dayViewHolder.tvDay.setTextColor(Color.GREEN);
				//
		}

		return convertView;
	}

	public class DayViewHolde
	{
		public LinearLayout llBackground;
		public TextView tvDay;
		
	}
//ũ�� �����ϴ� �κ�
	private int getCellWidthDP()
	{
//		int width = mContext.getResources().getDisplayMetrics().widthPixels;
		int cellWidth = 480/7;
		
		return cellWidth;
	}
	
	private int getRestCellWidthDP()
	{
//		int width = mContext.getResources().getDisplayMetrics().widthPixels;
		int cellWidth = 480%7;
		
		return cellWidth;
	}
	
	private int getCellHeightDP()
	{
//		int height = mContext.getResources().getDisplayMetrics().widthPixels;
		int cellHeight = 480/6;
		
		return cellHeight;
	}
	
}
