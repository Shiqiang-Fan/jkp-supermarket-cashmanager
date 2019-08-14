package com.joyveb.cashmanage.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joyveb.cashmanage.action.Actionable;
import com.joyveb.cashmanage.bean.HeadEntityHolder;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.utils.JsonUtil;

@Slf4j
public class HandleServlet extends HttpServlet {

	private static final long serialVersionUID = -4813847264787342794L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long startTime = System.currentTimeMillis();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		String reqJson = null;
		String respJson = "client request";
		HeadEntityHolder en = null;
		String command = null;
		String clientIp = this.getIpAddr(request);
		// build transmessage
		TransMessageEntity message = new TransMessageEntity();
		message.setRequest(request);
		message.setResponse(response);
		try {
			reqJson = this.getRequestContent(request);
			log.debug("收到了来自--" + clientIp + "的请求---json：" + reqJson);
			printHeader(request);
			if (StringUtils.isNotBlank(reqJson)) {
				en = JsonUtil.json2Bean(reqJson, HeadEntityHolder.class);
				if(null!=en.getHead()){
					command = String.valueOf(en.getHead().getCommand());
					message.setReqJson(reqJson);
					message.setHead(en.getHead());
					Actionable action = getAction(command, ctx);
					if(command!="2008"){
						log.debug("FRONT_REQUEST: cmd is [" + command + "] costtime is [" + (System.currentTimeMillis() - startTime) + "] JSON is [" + reqJson
								+ "] ");
					}
					respJson = action.action(message);
				}else{
					log.warn("接收请求json2Bean为空！");
				}
			} else {
				log.warn("接收请求内容为空,商户IP[" + clientIp + "]");
			}
		} catch (JsonParseException e) {
			log.warn("处理请求解析错误,商户IP[" + clientIp + "], 接收到数据[" + reqJson + "]", e);
		} catch (Exception e) {
			log.warn("处理请求未知异常,商户IP[" + clientIp + "], 接收到数据[" + reqJson + "]", e);
		} finally {
			try {
				log.debug("FRONT_RESPONSE:cmd is [" + command + "] costtime is [" + (System.currentTimeMillis() - startTime) + "] JSON is [" + respJson
						+ "]");
				// log.debug("session id is--"+request.getSession().getId());
				// response.addCookie(new Cookie("JSESSIONID",
				// request.getSession().getId()));
				message.getResponse().setHeader("Content-type", "text/html;charset=UTF-8");
				message.getResponse().setCharacterEncoding("UTF-8");
				message.getResponse().getWriter().write(respJson);
				message.getResponse().getWriter().flush();
				message.getResponse().getWriter().close();
			} catch (IOException e) {
				log.warn("响应内容时异常", e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Actionable getAction(String command, WebApplicationContext ctx) throws Exception {
		Map<String, Actionable> commands = ctx.getBean("commands", Map.class);
		Actionable act = commands.get(command);
		if (act == null) {
			throw new RuntimeException("no such command[" + command + "] action.");
		}
		return act;
	}

	private String getRequestContent(HttpServletRequest request) throws IOException {
		InputStream is = null;
		String xml = null;
		try {
			is = request.getInputStream();
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte[] data = new byte[8192];
			int rsize = 0;
			do {
				rsize = is.read(data);
				if (rsize > 0) {
					bout.write(data, 0, rsize);
				}
			} while (rsize > 0);
			xml = new String(bout.toByteArray());
			bout.close();
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return xml;
	}

	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// 打印header
	@SuppressWarnings("rawtypes")
	private void printHeader(HttpServletRequest request) {
		Enumeration names = request.getHeaderNames();
		StringBuilder sb = new StringBuilder("headerInfo---");
		while (names.hasMoreElements()) {
			String name = names.nextElement().toString();
			Enumeration headers = request.getHeaders(name);
			sb.append(name).append(":");
			while (headers.hasMoreElements()) {
				sb.append(headers.nextElement()).append(" ");
			}
			sb.append("\n");
		}
		log.debug("头部信息---" + sb.toString());
	}

}