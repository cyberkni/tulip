package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月3日 下午8:14:34
 */
public class IPQuery {
	
	public static void main(String[] args) throws Exception {
		query("60.161.0.165");
	}

	public static String query(String ip) throws Exception {
		Document doc = Jsoup.connect("http://www.ip138.com/ips1388.asp?ip=" + ip).get();
		Elements tables = doc.select("table");
		for(int i = 0; i < tables.size(); i++)
		System.err.println(tables.get(i));
		return "";
	}
}
