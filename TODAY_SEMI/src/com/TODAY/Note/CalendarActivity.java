package com.TODAY.Note;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.TODAY.R;

public class CalendarActivity extends Activity implements OnItemClickListener, OnClickListener, OnTouchListener
{
	public static int SUNDAY 		= 1;
	public static int MONDAY 		= 2;
	public static int TUESDAY 		= 3;
	public static int WEDNSESDAY 	= 4;
	public static int THURSDAY 		= 5;
	public static int FRIDAY 		= 6;
	public static int SATURDAY 		= 7;
	
	private TextView mTvCalendarTitle;
	private GridView mGvCalendar;
	
	private ArrayList<DayInfo> mDayList;
	private CalendarAdapter mCalendarAdapter;
	
	Calendar mLastMonthCalendar;
	Calendar mThisMonthCalendar;
	Calendar mNextMonthCalendar;
	
	private ArrayList<String> mNoteList;
	private LinearLayout linear;
	
	
	
	float downX;
	float upX;
	
	
	//���� �־���� ���� �߰�����
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cal_activity);
		
		Button bLastMonth = (Button)findViewById(R.id.last_month);
		Button bNextMonth = (Button)findViewById(R.id.next_month);
		
		mTvCalendarTitle = (TextView)findViewById(R.id.calendar_title);
		mGvCalendar = (GridView)findViewById(R.id.calendar);
		linear = (LinearLayout)findViewById(R.id.calendar_Linear_Layout);
		
		
		bLastMonth.setOnClickListener(this);				// last month btn
		bNextMonth.setOnClickListener(this);				// next month btn
		
		mGvCalendar.setOnItemClickListener(this);
		linear.setOnTouchListener(this);
				
		mDayList = new ArrayList<DayInfo>();
	}


	@Override
	protected void onResume()
	{
		super.onResume();
		
		// �̹��� �� Ķ���� �ν��Ͻ��� �����Ѵ�.
		mThisMonthCalendar = Calendar.getInstance();
		mThisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1);
		getCalendar(mThisMonthCalendar);
	}

	private void getCalendar(Calendar calendar)
	{
		int lastMonthStartDay;
		int dayOfMonth;
		int thisMonthLastDay;
		
		mDayList.clear();
		
		// �̹��� �������� ������ ���Ѵ�. �������� �Ͽ����� ��� �ε����� 1(�Ͽ���)���� 8(������ �Ͽ���)�� �ٲ۴�.)
		dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
		thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		calendar.add(Calendar.MONTH, -1);
		Log.e("������ ��������", calendar.get(Calendar.DAY_OF_MONTH)+"");

		// �������� ������ ���ڸ� ���Ѵ�.
		lastMonthStartDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		calendar.add(Calendar.MONTH, 1);
		Log.e("�̹��� ������", calendar.get(Calendar.DAY_OF_MONTH)+"");
		
		if(dayOfMonth == SUNDAY)
		{
			dayOfMonth += 7;
		}
		
		lastMonthStartDay -= (dayOfMonth-1)-1;
		

		// Ķ���� Ÿ��Ʋ(��� ǥ��)�� �����Ѵ�. 
		mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "�� "
				+ (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "��");

		DayInfo day;
		
		Log.e("DayOfMOnth", dayOfMonth+"");
		
		for(int i=0; i<dayOfMonth-1; i++)
		{
			int date = lastMonthStartDay+i;
			day = new DayInfo();
			day.setDay(Integer.toString(date));
			day.setInMonth(false);
			
			mDayList.add(day);
		}
		for(int i=1; i <= thisMonthLastDay; i++)
		{
			day = new DayInfo();
			day.setDay(Integer.toString(i));
			day.setInMonth(true);
			
			mDayList.add(day);
		}
		for(int i=1; i<42-(thisMonthLastDay+dayOfMonth-1)+1; i++)
		{
			day = new DayInfo();
			day.setDay(Integer.toString(i));
			day.setInMonth(false);
			mDayList.add(day);
		}
		
		//GetNoteByMonth(); //���� ������ �ȵǼ� �ּ�ó���س��� ������...
		
		initCalendarAdapter();
	}

	//��, ���� ���ͼ� �ش� ��,���� ����� �޸� �ִٸ� mNoteList�迭�� �־��ش�
	private void GetNoteByMonth() {
		NotesDbAdapter mDbHelper = new NotesDbAdapter(this);
		Cursor note = mDbHelper.fetchNoteByMonth(mThisMonthCalendar.get(Calendar.YEAR), mThisMonthCalendar.get(Calendar.MONTH) + 1);		
        if( note.getCount() > 0 )
        {
            startManagingCursor(note);
            mNoteList.add(note.getString(note.getColumnIndexOrThrow(NotesDbAdapter.KEY_DAY)));
        }
	}

	/**
	 * �������� Calendar ��ü�� ��ȯ�մϴ�.
	 * 
	 * @param calendar
	 * @return LastMonthCalendar
	 */
	private Calendar getLastMonth(Calendar calendar)
	{
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		calendar.add(Calendar.MONTH, -1);
		mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "�� "
				+ (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "��");
		return calendar;
	}

	/**
	 * �������� Calendar ��ü�� ��ȯ�մϴ�.
	 * 
	 * @param calendar
	 * @return NextMonthCalendar
	 */
	private Calendar getNextMonth(Calendar calendar)
	{
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		calendar.add(Calendar.MONTH, +1);
		mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "�� "
				+ (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "��");
		return calendar;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long arg3)
	{
		DayInfo d = mDayList.get(position);
		int year = mThisMonthCalendar.get(Calendar.YEAR);
		int month = mThisMonthCalendar.get(Calendar.MONTH) + 1;
		Toast.makeText(this , year + "��" + month + "��" + d.getDay() + "��", Toast.LENGTH_SHORT).show();			// ���⼭ DB�� �����Ͽ� ������ ���ϰ� ���ָ� �ȴ�.

		String day = year + "-" + month + "-" + d.getDay(); //day�� ��Ʈ������ ��, ��, �� �־��ְ�
		
		Intent intent =new Intent(CalendarActivity.this, Note_Edit.class);
	
		intent.putExtra("day", year + "-" + month + "-" + d.getDay()); //day(��, ��, ��)�� (Note_Edit��)���ش�
		
		startActivity(intent);
	}
	
	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.last_month:
			mThisMonthCalendar = getLastMonth(mThisMonthCalendar);
			getCalendar(mThisMonthCalendar);
			break;
		case R.id.next_month:
			mThisMonthCalendar = getNextMonth(mThisMonthCalendar);
			getCalendar(mThisMonthCalendar);
			break;
		}
	}
		
	private void initCalendarAdapter()
	{
		String yearAndMonth = mThisMonthCalendar.get(Calendar.YEAR) + "-" + (mThisMonthCalendar.get(Calendar.MONTH) + 1);
		Log.i("Year And Month", yearAndMonth);
		mCalendarAdapter = new CalendarAdapter(this, R.layout.day, mDayList, mNoteList,yearAndMonth);
		mGvCalendar.setAdapter(mCalendarAdapter);
	}
	
	// customrmizing ����
	
	private void initCalendarAdapter(String yearAndMonth)
	{
		
		mCalendarAdapter = new CalendarAdapter(this, R.layout.day, mDayList, mNoteList,yearAndMonth);
		mGvCalendar.setAdapter(mCalendarAdapter);
	}




	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		
		
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			downX = event.getX();
			Log.i("downX", String.valueOf(downX));
		}
		else if(event.getAction() == MotionEvent.ACTION_UP){
			upX = event.getX();
			Log.i("upX", String.valueOf(upX));
			
			if( 5 < downX - upX ) {  // in case of left
				mThisMonthCalendar = getNextMonth(mThisMonthCalendar);
				getCalendar(mThisMonthCalendar);
				
				linear.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
						R.anim.push_right_in));
				//linear.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext()),
					//	R.anim.push_left_out));

			} else if (upX - downX > 5){ // in case of right direction

				linear.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
						R.anim.push_left_in));
				
				mThisMonthCalendar = getLastMonth(mThisMonthCalendar);
				getCalendar(mThisMonthCalendar);
			}
		}
		
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
}

