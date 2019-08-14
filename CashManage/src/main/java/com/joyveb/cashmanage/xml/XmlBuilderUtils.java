package com.joyveb.cashmanage.xml;

import com.ximpleware.AutoPilot;
import com.ximpleware.XPathParseException;

public class XmlBuilderUtils {


	public static String getXPathValue(AutoPilot ap, String xPath) throws XPathParseException {
		ap.selectXPath(xPath);
		boolean b = ap.evalXPathToBoolean();
		if(b == false) {
			throw new XPathParseException("no such xpath:[" + xPath + "]");
		}
		return ap.evalXPathToString();
	}

	public static String getXPathValue(AutoPilot ap, String xPath,
			String defaultv) throws XPathParseException {
		ap.selectXPath(xPath);
		String txt = ap.evalXPathToString();
		if (txt == null || txt.length() <= 0) {
			return defaultv;
		}
		return txt;
	}

}
