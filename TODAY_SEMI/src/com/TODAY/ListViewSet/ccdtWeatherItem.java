package com.TODAY.ListViewSet ;

import android.graphics.drawable.Drawable;

public class ccdtWeatherItem
{
	Drawable dtWicon ;	// ������
	String dtWday;
	String dtWdate ; // ����
	String dtWweather ;	// ���� ����
	String dtWlow;	// �µ�
	String dtWhigh;	// ����

	public ccdtWeatherItem(Drawable dtWicon, String dtWday, String dtWdate, String dtWweather, String dtWlow, String dtWhigh)
	{
		this.dtWicon = dtWicon;
		this.dtWday = dtWday;
		this.dtWdate = dtWdate;
		this.dtWweather = dtWweather;
		this.dtWlow = dtWlow;
		this.dtWhigh = dtWhigh;
	}
}