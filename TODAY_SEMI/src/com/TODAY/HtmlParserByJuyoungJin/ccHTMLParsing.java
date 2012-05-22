// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//		Android ���ø����̼ǿ��� ���ͳݿ� �����ϱ� ���ؼ��� ���ͳ� ���� ������ �ʿ��ϴ�
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//		Apache Commons Library�� �̿��� HTML �о����
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//		-	Java������ 'URL Class'�� 'URLConnection Class'�� �̿��ؼ� HTTP ����� �� �� �ִ�
//		-	Android������ Commons�� 'HttpClient Interface'�� ������ Class��
//			DefaultHttpClient Class�� ������ HTTP ����� ����� �� �ִ�
//		-	Android���� 'Apache HttpClient Library'�� �⺻���� ���ԵǾ� �ִ�
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//		HttpGet(url)	
//		-	�ȵ���̵忡�� HTML�� �о���� ������� ���
//		-	�α����� ���� Get ��� ��� (�ּҿ� ID�� ��й�ȣ�� ����Ͽ� ����)
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//		Internet ������ �ȵ� ��츦 ����Ͽ� Try/Catch�� �̿��Ѵ�
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//			�Է½�Ʈ���� ������ ����
//			������ ���پ� �б� ���ؼ� BufferedReader�� ����
//			������ ù���� ������ �����ؼ� null ���� ���ѵ� ������ ���ٸ� ������ �����Ѵ�
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

package com.TODAY.HtmlParserByJuyoungJin ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.TODAY.HtmlParserByJuyoungJin.*;

public class ccHTMLParsing
{
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	private static String urlTT = "http://203.237.226.95:8080/mobile/login/login_ok.jsp?userid=32071467&userpw=jj119&returnUrl=../m7/m7_c1.jsp&instanceid=" ;
	private static String urlFD = "http://203.237.226.95:8080/mobile/m11/m11_c1_2.jsp?instanceid=/d1_3_1_2" ;
	private static String urlNT = "http://203.237.226.95:8080/mobile/m8/m8_c2.jsp?instanceid=/d1_2";
	
	public static String DownloadHtml(int option)
	{
		DefaultHttpClient client = new DefaultHttpClient() ;
		StringBuilder html = new StringBuilder() ;

		try
		{
			HttpGet httpget ;
			
			if(option == 1)
				httpget = new HttpGet (urlTT);
			else if(option == 2)
				httpget = new HttpGet(urlFD);
			else
				httpget = new HttpGet(urlNT);
			
			HttpResponse response = client.execute(httpget) ;
			InputStreamReader isr = new InputStreamReader(response.getEntity().getContent()) ;
			BufferedReader br = new BufferedReader(isr) ;
			String line = br.readLine() ;

			while (line != null)
			{
				html.append(line + '\n') ;
				line = br.readLine() ;
			}
			br.close() ;
		}
		catch (Exception e)
		{
			e.printStackTrace() ;
		}

		if (option == 1)
			return ccTimeTable.cleanData(html);
		else if(option == 2)
			return ccFood.cleanData(html);
		else
			return ccNotice.cleanData(html);
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

}