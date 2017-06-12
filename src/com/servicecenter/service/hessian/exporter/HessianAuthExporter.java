package com.servicecenter.service.hessian.exporter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.remoting.caucho.HessianServiceExporter;

import com.utils.MD5HashUtil;
import com.utils.StringUtil;

public class HessianAuthExporter extends HessianServiceExporter {

	protected final Logger log = Logger.getLogger(getClass());

	public static final String HEAD_KEY = "hessianDemo";
	public static final String HEAD_NAME = "sign";

	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String head = request.getHeader(HEAD_NAME);
		String time = request.getHeader("timestamp");
		if (StringUtil.isEmptyStr(head) || !head.equalsIgnoreCase(sign(time))) {
			// 记录异常日志
			log.info("hessian service auth fail..");
			return;
		}
		super.handleRequest(request, response);
	}

	private String sign(String timestamp) {
		return MD5HashUtil.hashCode(timestamp + HEAD_KEY);
	}

}
