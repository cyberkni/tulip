package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月26日 下午7:42:58
 */
public class RdbDump {
	public static void main1(String[] args) {
		double d = 14781027 / 0.88;
		NumberFormat CURRENCY_INSTANCE = NumberFormat.getCurrencyInstance();
		System.out.println(CURRENCY_INSTANCE.format(d));
	}

	public static void main(String[] args) throws Exception {
		// 生成内存数据报告
		// rdb -c memory /var/lib/redis/dump.rdb > memory.csv
		// Runtime.getRuntime().exec("rdb -c memory /var/lib/redis/dump.rdb > memory.csv");
		// database,type,key,size_in_bytes,encoding,num_elements,len_largest_element
		// 0,string,"u:ava:praise:2397542-0",103,string,8,8
		// sort -n -r rdbmemory.csv
		BufferedReader reader = null;
		FileWriter fileWriter = null;
		try {
			File dump_file = new File("/home/lf/error/memory.csv");
			File target_dump_file = new File("/home/lf/error/rdbmemory.csv");
			reader = new BufferedReader(new FileReader(dump_file));
			fileWriter = new FileWriter(target_dump_file, true);
			String line = null;
			long lc = 0;
			while((line = reader.readLine()) != null) {
				try {
					String[] sp = StringUtils.split(line, ",");
					String tmp = sp[0];
					sp[0] = sp[3];
					sp[3] = tmp;
					fileWriter.write(toString(sp));
					lc++;
				} catch (Exception e) {
					//
				}
			}
			System.err.println("一共：" + lc + "行");
		} finally {
			if(reader != null) {
				reader.close();
				reader = null;
			}
			if(fileWriter != null) {
				fileWriter.flush();
				fileWriter.close();
				fileWriter = null;
			}
		}
	}
	
	public static String toString(String[] sw) {
		StringBuffer b = new StringBuffer();
		for (String string : sw) {
			b.append(string).append(",");
		}
		String s = b.toString();
		return s.substring(0, s.length() - 1) + "\n";
	}

	public static final class Dump {
		public String database;
		public String type;
		public String key;
		public long size_in_bytes;
		public String encoding;
		public long num_elements;
		public String len_largest_element;

		@Override
		public String toString() {
			return size_in_bytes + ", " + database + ", " + type + ", " + key + ", " + encoding + ", " + num_elements + ", " + len_largest_element;
		}
	}
}