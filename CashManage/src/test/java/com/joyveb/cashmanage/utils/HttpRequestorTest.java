/*
package com.joyveb.cashmanage.utils;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CACHE_CONTROL;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.HttpVersion.HTTP_1_1;
import static org.apache.http.params.CoreConnectionPNames.SO_TIMEOUT;
import static org.apache.http.params.CoreConnectionPNames.STALE_CONNECTION_CHECK;
import static org.apache.http.params.CoreProtocolPNames.HTTP_CONTENT_CHARSET;
import static org.apache.http.params.CoreProtocolPNames.HTTP_ELEMENT_CHARSET;
import static org.apache.http.params.CoreProtocolPNames.PROTOCOL_VERSION;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Component;

@Component("httpRequestorTest")
public class HttpRequestorTest {

	public void reload() {
		lock.writeLock().lock();
		try {
			cm = new ThreadSafeClientConnManager();
			httpclient = new DefaultHttpClient(cm);
			DefaultHttpClient httpclient = new DefaultHttpClient();
			httpclient.setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
				public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
					return 10 * 1000;
				}
			});
			cm.setMaxTotal(1000);
			cm.setDefaultMaxPerRoute(200);
			httpclient.getParams().setParameter(PROTOCOL_VERSION, HTTP_1_1);
			httpclient.getParams().setParameter(HTTP_CONTENT_CHARSET, "UTF-8");
			httpclient.getParams().setParameter(HTTP_ELEMENT_CHARSET, "UTF-8");
			httpclient.getParams().setParameter(STALE_CONNECTION_CHECK, false);
			httpclient.getParams().setParameter(SO_TIMEOUT, 60000);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public String post(String ip, String xml) throws ClientProtocolException, IOException {
		lock.readLock().lock();
		try {
			StringEntity entity = new StringEntity(xml, "UTF-8");
			HttpPost httppost = null;
			httppost = new HttpPost("http://"+ip+":8080/CashManage/handle.action");
			httppost.setEntity(entity);
			httppost.addHeader(HEADER_ACCEPT);
			httppost.addHeader(HEADER_CACHE_CONTROL);
			httppost.addHeader(HEADER_CONTENT_TYPE);
			httppost.addHeader(HEADER_SOAPACTION);
			httppost.getParams().setParameter(SO_TIMEOUT, 800000);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			return httpclient.execute(httppost, responseHandler);
		} finally {
			lock.readLock().unlock();
		}
	}

	public void changeMaxTotal(int size) {
		lock.writeLock().lock();
		try {
			cm.setMaxTotal(size);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public void changeMaxPerRoute(int size) {
		lock.writeLock().lock();
		try {
			cm.setDefaultMaxPerRoute(size);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public void destroy() {
		lock.writeLock().lock();
		try {
			cm.shutdown();
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	private ThreadSafeClientConnManager cm;
	private DefaultHttpClient httpclient;
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final static Header HEADER_ACCEPT = new BasicHeader(ACCEPT, "application/soap+xml, application/dime, multipart/related, text*/
/*");
	private final static Header HEADER_CACHE_CONTROL = new BasicHeader(CACHE_CONTROL, "no-cache");
	private final static Header HEADER_CONTENT_TYPE = new BasicHeader(CONTENT_TYPE, "text/html");
	private final static Header HEADER_SOAPACTION = new BasicHeader("SOAPAction", "\"\"");
	
}
*/
