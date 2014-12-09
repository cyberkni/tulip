package com.yeepay.fpay.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 易宝金融平台主要通过HTTP POST文件流方式，传递报文。
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 上午9:56:03
 */
public class HttpClientUtils {
	
	public static void main(String[] args) {
		final String serviceAddress3= "http://172.17.253.118:8999/api/investor/register";
		String mb05 = "/DCIM/halfMB.jar";
		final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());           
		FileSystemResource fsr = new FileSystemResource(mb05);
		final LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("name", "superplik");
		map.add("requestMessage", fsr);
		Object result = restTemplate.postForObject(serviceAddress3, map, String.class);
	}

	public static String connectionPost(String uri, String req) throws IOException {
		System.err.println("req XML : " + req);
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuilder response = new StringBuilder();
		try {
			URL _url = new URL(uri + "?requestMessage=" + req);
			HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
			connection.setRequestMethod("POST");
			
//			connection.setRequestProperty("accept", "*/*");
//			connection.setRequestProperty("connection", "Keep-Alive");
//			connection.setRequestProperty("user-agent", "andpay.me");
			
			connection.setDoOutput(true);
			connection.setDoInput(true);

			out = new PrintWriter(connection.getOutputStream());
			out.print(req);
			out.flush();

			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
		} finally {
			if (out != null) {
				out.close();
				out = null;
			}
			if (in != null) {
				in.close();
				in = null;
			}
		}
		return response.toString();
	}
}