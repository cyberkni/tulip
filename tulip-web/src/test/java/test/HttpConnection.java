package test;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月9日 下午11:23:46
 */
public class HttpConnection {

	public static void main(String[] args) {
		final String serviceAddress3= "http://127.0.0.1:8080/login.htm";
		String mb05 = "C://Users/Administrator/Desktop/HttpConnection0.java";
		final RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory()); 
		FileSystemResource fsr = new FileSystemResource(mb05);
		final LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("name", "Qian.CHEN");
		map.add("passwd", "chenqian");
		map.add("requestMessage", fsr);
		String result = restTemplate.postForObject(serviceAddress3, map, String.class);
		System.out.println(result);
	}
}
