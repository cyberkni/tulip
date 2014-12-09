package com.yeepay.fpay.utils;

import java.io.File;
import java.io.IOException;

/**
 * 自动创建文件目录的Appender
 * 
 * @author fei.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月5日 下午5:28:46
 */
public class DailyRollingFileAppender extends org.apache.log4j.DailyRollingFileAppender {
	public synchronized void setFile(String fileName, boolean append, boolean bufferedIO, int bufferSize)
			throws IOException {
		File logfile = new File(fileName);
		logfile.getParentFile().mkdirs();
		super.setFile(fileName, append, bufferedIO, bufferSize);
	}
}