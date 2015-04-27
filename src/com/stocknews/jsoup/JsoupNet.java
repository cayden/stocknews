/**
 * JsoupNet.java
 * Copyright (C) 2015
 * All right reserved. 2015-4-27
 */
package com.stocknews.jsoup;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.stocknews.constants.Constants;
import com.stocknews.util.LogsUtil;

/**
 *  
 * @author cuiran
 * @version 1.0.0
 */
public class JsoupNet {
	private static final String TAG="JsoupNet";

	public static ArrayList<HtmlInfo> getHtmlInfos(){
		ArrayList<HtmlInfo> infos=null;
		Document doc = null;
		try{
			infos=new ArrayList<HtmlInfo>();
			doc=Jsoup.connect(Constants.SERVER_URL).get();
			
			 Elements  elements1= doc.getElementsByAttributeValue("class", "mod newslist");
			 System.out.println( elements1.size());
			 String html=elements1.get(0).html();
			 Document doc1=Jsoup.parse(html);
			 Elements elements= doc1.select("li");
			 for(Element element:elements ){
				 Elements links=element.select("a");
				 HtmlInfo info=new HtmlInfo();
				 for(Element link : links) { 
					 String target=link.attr("target"); 
					 if("_blank".equals(target)){
						 String linkHref = link.attr("href"); 
						 String linkText = link.text();
						 String pushTime= element.select("span").get(0).text();
						 info.setSubject(linkText);
						 info.setLink(linkHref);
						 System.out.println(linkText+"~~~~"+linkHref+"~~"+pushTime);
						 infos.add(info);
					 }
				 }
				 
			 }
		}catch(Exception e){
			LogsUtil.d(TAG, "getHtmlInfos error", e);
		}
		return infos;
	}
	
	public static String getHtml(String url){
		String str="";
		
		Document doc = null;
		try{
			doc=Jsoup.connect(url).get();
			Elements  elements1= doc.getElementsByAttributeValue("id", "Cnt-Main-Article-QQ");
			LogsUtil.d(TAG, "size="+elements1.size());
			str=elements1.get(0).html();
			
		}catch(Exception e){
			LogsUtil.e(TAG, "getHtmlInfos error", e);
		}
		return str;
	}
}
