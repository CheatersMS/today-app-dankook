package com.TODAY.HtmlParserByJuyoungJin;

import java.util.Calendar;
import java.util.StringTokenizer;


public class ccFood
{
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		(2012-05-22)

	private String store ;
	private String menu ;

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		������ (2012-05-22)

	public ccFood()
	{
		setStore(new String()) ;
		setMenu(new String()) ;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		������ (2012-05-22)

	public ccFood(String store, String menu)
	{
		store = store.replace("\\", " ") ;
		
		this.setStore(store) ;		
		this.setMenu(menu) ;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		�����̳� �߽� ���� �ϳ��� ������ �� �ֵ��� �ڸ���

	public static String removeMenu(String menu, int mOption)
	{
		String mRemove = new String("����") ;
		String mRemain = new String(menu) ;
		StringBuilder mResult = new StringBuilder("") ;

		if(mOption == 2) { mRemove = "�߽�" ; }

		int SP = 0 ;
		int CP = mRemain.indexOf("\n") ;
		int EP = mRemain.length() ;
		int count = 0 ;

		while(mRemain.length() > 1)
		{
			if((mOption == 1) && (count == 8)) break ;
			if((mOption == 2) && (count == 6)) break ;
			String mTemp = mRemain.substring(SP, CP+1) ;
			mRemain = mRemain.substring(CP+1, EP) ;
			CP = mRemain.indexOf("\n") ;
			EP = mRemain.length() ;
			if(mTemp.indexOf(mRemove) > -1) { continue ; }
			mResult.append(mTemp) ;
			count ++ ;
		}		
		return mResult.toString().replaceAll((mOption==1)?"�߽�:":"����:", "") ;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		���ʿ��� �κ� ����

	public static String removeFrom(String menu)
	{
		String mResult = new String(menu) ;

		int rSP = 0 ;
		int rEP = mResult.length() ;
		int SP = mResult.indexOf("(") ;
		int EP = mResult.indexOf(")") ;

		while(SP > -1)
		{
			StringBuilder mCut = new StringBuilder("") ;
			mCut.append(mResult.substring(rSP, SP)) ;
			mCut.append(mResult.substring(EP+1, rEP)) ;			
			mResult = mCut.toString() ; 
			rEP = mResult.length() ;
			SP = mResult.indexOf("(") ;
			EP = mResult.indexOf(")") ;
		}
		return mResult ;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		���� ��¥�� �޴��� �����
	
	public static String setToday(String FD)
	{
		String mResult = new String(FD) ; 

		int SP = 0 ;
		int EP = 0 ;

		Calendar cal= Calendar.getInstance( ) ;
		switch(cal.get(Calendar.DAY_OF_WEEK))
		{
		case 1:
			SP = mResult.indexOf("�Ͽ���") + 18 ;
			EP = mResult.indexOf("���") ;
			break ;
		case 2:
			SP = mResult.indexOf("������") + 18 ;
			EP = mResult.indexOf("ȭ����") ;
			break ;
		case 3:
			SP = mResult.indexOf("ȭ����") + 18 ;
			EP = mResult.indexOf("������") ;
			break ;
		case 4:
			SP = mResult.indexOf("������") + 18 ;
			EP = mResult.indexOf("�����") ;
			break ;
		case 5:
			SP = mResult.indexOf("�����") + 18 ;
			EP = mResult.indexOf("�ݿ���") ;
			break ;
		case 6:
			SP = mResult.indexOf("�ݿ���") + 18 ;
			EP = mResult.indexOf("�����") ;
			break ;
		case 7:
			SP = mResult.indexOf("�����") + 18 ;
			EP = mResult.indexOf("�Ͽ���") ;
			break ;
		default :
			break ;
		}				

		mResult = mResult.substring(SP+1, EP) ;		
		mResult = mResult.replaceAll("\n<�ѹ�>", "\n<�ѹ�>") ;
		mResult = mResult.replaceAll("<�Ҵ�>", "\n<�Ҵ�>") ;
		mResult = mResult.replaceAll("<��õ>", "\n<��õ>") ;
		mResult = mResult.replaceAll("<��ǰ>", "\n<��ǰ>") ;
		mResult = mResult.replaceAll("\\", "\n") ;
		mResult = mResult.replaceAll("�߽�", "\n�߽�") ;
		mResult = mResult.replaceAll("����", "\n����") ;

		mResult = removeFrom(mResult) ;
		mResult = removeMenu(mResult, 1) ;

		return mResult ;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	//	String to ccFood Array (2012-05-22)
	
	public static ccFood[] stringToClass()
	{
		StringTokenizer ST = new StringTokenizer(setToday(ccHTMLParsing.DownloadHtml(2)), "\n") ;

		ccFood[] FD = new ccFood[(ST.countTokens())/2] ;
		int i = 0 ;

		while((ST.countTokens()) > 0)
		{
			FD[i] = new ccFood(ST.nextToken(), ST.nextToken()) ;
			i++ ;
		}
				
		return FD ;		
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//		�ʿ��� �κ��� ���۰� �� �ε����� ���ѵ� �߶󳽴�

	public static String cleanData(StringBuilder html)
	{
		int SP = 0 ;
		int EP = 0 ;

		SP = html.indexOf("<tbody id=\"contents_1\">");
		EP = html.indexOf("</tbody>") ;

		String mResult = new String(html.toString().substring(SP, EP)) ;

		mResult = mResult.replaceAll("<tbody id=\"contents_1\">", "") ;
		mResult = mResult.replaceAll("<td class=\"tdleft\">", "") ;
		mResult = mResult.replaceAll("<+[a-zA-Z0-9]*>+", "") ;
		mResult = mResult.replaceAll("</+[a-zA-Z0-9]*>+", "") ;
		mResult = mResult.replaceAll("<+[a-zA-Z0-9]*=+\"[a-zA-Z0-9]*\">+", "") ;
		mResult = mResult.replaceAll("<+[a-zA-Z0-9]*/>+", "") ;
		mResult = mResult.replaceAll("\t", "") ;
		mResult = mResult.replaceAll("   ", "") ;
		mResult = mResult.replaceAll("  ", "") ;
		mResult = mResult.replaceAll("\n\n\n", "\n") ;
		mResult = mResult.replaceAll("\n\n", "\n") ;
		
		return mResult.toString() ;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

}