package com.TODAY.UIByDaeyoungCho;

import java.util.Calendar;
import java.util.TimeZone;


import android.widget.TextView;

public class SetDateInTextView {

	public SetDateInTextView()
	{
		
	}
	public static TextView setDate(TextView txt)
	{
		Calendar cal= Calendar.getInstance( ) ;
		String mDay = new String(DayInt2String(cal.get(Calendar.DAY_OF_WEEK))) ;

		TimeZone jst = TimeZone.getTimeZone ("JST") ;
		Calendar mCal = Calendar.getInstance ( jst ) ;
		txt.setText(mCal.get(Calendar.YEAR) + "�� " + (mCal.get(Calendar.MONTH)+1) + "�� " + mCal.get(Calendar.DATE) + "�� " + mDay + "����\n");
		return txt; 
		
	}
	
	public static String DayInt2String(int iDay)
	{
		String mDay = new String() ;
		switch(iDay)
		{
		case 1:		mDay = "��" ; break ;
		case 2:		mDay = "��" ; break ;
		case 3:		mDay = "ȭ" ; break ;
		case 4:		mDay = "��" ; break ;
		case 5:		mDay = "��" ; break ;
		case 6:		mDay = "��" ; break ;
		case 7:		mDay = "��" ; break ;
		default :	mDay = "��" ;
		}		
		return mDay ;
	}
}
