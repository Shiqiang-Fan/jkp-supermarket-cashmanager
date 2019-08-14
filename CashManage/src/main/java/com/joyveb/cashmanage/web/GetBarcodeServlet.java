package com.joyveb.cashmanage.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

import com.joyveb.cashmanage.utils.DESCoder;
import com.joyveb.cashmanage.utils.MixBarcodeUtil;

@Slf4j
public class GetBarcodeServlet extends HttpServlet {

	private static final long serialVersionUID = 56890894234786L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
		request.setCharacterEncoding("UTF-8");
		
		String result = "加密失败！";
		try {
			String barcode = request.getParameter("barcode");
			String desKey = request.getParameter("deskey");
			if (StringUtils.isBlank(desKey)) {
				desKey = "0123456789666666";
			}
			log.debug("barcode:"+barcode+" desKey:"+desKey);
			if (StringUtils.isNotBlank(barcode)) {
				if(barcode.length() == 40 || barcode.length() == 41 ){
					result = DESCoder.desEncrypt(MixBarcodeUtil.encodeBarcode(barcode), desKey);;
				} else {
					result = "barcode不合法！";
				}
			}
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
			
		} catch (IOException e) {
			log.warn("response writer error", e);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
		doPost(request, response);
	}

}