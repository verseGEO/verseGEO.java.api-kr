package com.mw30p.p2e;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.cholocb.util.common.JsonUtil;
import com.cholocb.util.crypto.HmacSha256Util;
import com.mw30p.p2e.api.VerseB;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import sun.net.www.protocol.http.HttpURLConnection;

public class APITest {

	protected static final String VIEW_NAME_JSON				=	"common/jsonview";

	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Usage : VerseB exRate|exReq|OutAddrVerify|OutPaswordReg|WithdrawReq|StatusReq");
			return;
		}

		// Test 설정
		VerseB.setIMode(0);
		// Real 설정
		//VerseB.setIMode(1);

		HashMap<String, String>		hmIn		= new HashMap<String, String>();
		HashMap<String, String>		hmOut		= new HashMap<String, String>();
		switch (args[0]) {
			// Exchange Rate
			case "exRate" :
				// 파라메터 설정
				hmIn.put("merchantInformation.merchantId",			"000000000001");
				hmIn.put("merchantInformation.merchantSiteId",		"000001");
				hmIn.put("customerId",								"000000000001");
				hmIn.put("fromCurrency",							"GOLD");
				hmIn.put("toCurrency",								"SLAYB");
				hmIn.put("fromAmount",								"100");
				hmIn.put("key",										"9F2488BFAE5FB03B9B483FA1CF1EC94B3B4B2E52780A2B53ED67E581110CC980");
				hmIn.put("initialVector",							"64750805549512089392625993734535");

				// API 호출
				hmOut							= VerseB.exRate(hmIn);
				System.out.println(hmOut);
				if ("SUCCESS".equals(hmOut.get("status"))) {
					// 정상 처리 응답 수신
					String exRate	= hmOut.get("exRate");
					String toAmount	= hmOut.get("toAmount");
					System.out.println("exRate="+exRate+" toAmount="+toAmount);
				} else {
					// 오류 처리 응답 수신
					String errCd	= hmOut.get("errorInformation.errCd");
					String reason	= hmOut.get("errorInformation.reason");
					System.out.println("errCd="+errCd+" reason="+reason);
				}
				return;
			// Exchange
			case "exReq" :
				// 파라메터 설정
				hmIn.put("merchantInformation.merchantId",			"000000000001");
				hmIn.put("merchantInformation.merchantSiteId",		"000001");
				hmIn.put("customerId",								"000000000001");
				hmIn.put("fromCurrency",							"GOLD");
				hmIn.put("toCurrency",								"SLAYB");
				hmIn.put("fromAmount",								"100");
				hmIn.put("exchangeRate",							"10%");
				hmIn.put("notifyUrl",								"http://10.30.1.51:8180/p2e/AsyncResult.jsp");
				hmIn.put("key",										"9F2488BFAE5FB03B9B483FA1CF1EC94B3B4B2E52780A2B53ED67E581110CC980");
				hmIn.put("initialVector",							"64750805549512089392625993734535");

				// API 호출
				hmOut							= VerseB.exReq(hmIn);
				System.out.println(hmOut);
				if ("SUCCESS".equals(hmOut.get("status"))) {
					// 정상 처리 응답 수신
					String txId	= hmOut.get("txId");
					System.out.println("txId="+txId);
				} else {
					// 오류 처리 응답 수신
					String errCd	= hmOut.get("errorInformation.errCd");
					String reason	= hmOut.get("errorInformation.reason");
					System.out.println("errCd="+errCd+" reason="+reason);
				}
				return;
			// OutAddrVerify
			case "OutAddrVerify" :
				// 파라메터 설정
				hmIn.put("merchantInformation.merchantId",			"000000000001");
				hmIn.put("merchantInformation.merchantSiteId",		"000001");
				hmIn.put("customerId",								"000000000001");
				hmIn.put("outAddress",								"0x8bDfa1f6393146f005F1C84050c553367c1e27d2");
				hmIn.put("key",										"9F2488BFAE5FB03B9B483FA1CF1EC94B3B4B2E52780A2B53ED67E581110CC980");
				hmIn.put("initialVector",							"64750805549512089392625993734535");

				// API 호출
				hmOut							= VerseB.OutAddrVerify(hmIn);
				System.out.println(hmOut);
				if ("SUCCESS".equals(hmOut.get("status"))) {
					// 정상 처리 응답 수신
					System.out.println("Recv Success");
				} else {
					// 오류 처리 응답 수신
					String errCd	= hmOut.get("errorInformation.errCd");
					String reason	= hmOut.get("errorInformation.reason");
					System.out.println("errCd="+errCd+" reason="+reason);
				}
				return;
			case "OutPaswordReg" :
				// 파라메터 설정
				hmIn.put("merchantInformation.merchantId",			"000000000001");
				hmIn.put("merchantInformation.merchantSiteId",		"000001");
				hmIn.put("customerId",								"000000000001");
				hmIn.put("outAddress",								"0x8bDfa1f6393146f005F1C84050c553367c1e27d2");
				hmIn.put("outPassword",								"Q!w2e3r4");
				hmIn.put("key",										"9F2488BFAE5FB03B9B483FA1CF1EC94B3B4B2E52780A2B53ED67E581110CC980");
				hmIn.put("initialVector",							"64750805549512089392625993734535");

				// API 호출
				hmOut							= VerseB.OutPaswordReg(hmIn);
				System.out.println(hmOut);
				if ("SUCCESS".equals(hmOut.get("status"))) {
					// 정상 처리 응답 수신
					System.out.println("Recv Success");
				} else {
					// 오류 처리 응답 수신
					String errCd	= hmOut.get("errorInformation.errCd");
					String reason	= hmOut.get("errorInformation.reason");
					System.out.println("errCd="+errCd+" reason="+reason);
				}
				return;
			case "WithdrawReq" :
				// 파라메터 설정
				hmIn.put("merchantInformation.merchantId",			"000000000001");
				hmIn.put("merchantInformation.merchantSiteId",		"000001");
				hmIn.put("customerId",								"000000000001");
				hmIn.put("outAddress",								"0x8bDfa1f6393146f005F1C84050c553367c1e27d2");
				hmIn.put("outPassword",								"Q!w2e3r4");
				hmIn.put("fromCurrency",							"SLAYB");
				hmIn.put("toCurrency",								"SLAYB");
				hmIn.put("fromAmount",								"10");
				hmIn.put("notifyUrl",								"http://10.30.1.51:8180/p2e/AsyncResult.jsp");
				hmIn.put("key",										"9F2488BFAE5FB03B9B483FA1CF1EC94B3B4B2E52780A2B53ED67E581110CC980");
				hmIn.put("initialVector",							"64750805549512089392625993734535");

				// API 호출
				hmOut							= VerseB.WithdrawReq(hmIn);
				System.out.println(hmOut);
				if ("SUCCESS".equals(hmOut.get("status"))) {
					// 정상 처리 응답 수신
					String txId		= hmOut.get("txId");
					System.out.println("txId="+txId);
				} else {
					// 오류 처리 응답 수신
					String errCd	= hmOut.get("errorInformation.errCd");
					String reason	= hmOut.get("errorInformation.reason");
					System.out.println("errCd="+errCd+" reason="+reason);
				}
				return;
			default :
				System.out.println("Usage : VerseB exRate|exReq|OutAddrVerify|OutPaswordReg|WithdrawReq|StatusReq");
				return;
		}

	}

	/* 채널 서버에서 수신 처리 개발 필요 */
	/* Spring Framework WAS 기반으로 처리 예시로 채널 서버의 상황에 따라 자체 개발 필요 */
	/* 수신 처리 Start */
	public ModelAndView recvNotify(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(VIEW_NAME_JSON);

		String				key				= "9F2488BFAE5FB03B9B483FA1CF1EC94B3B4B2E52780A2B53ED67E581110CC980";
		try {
			String			sIn				=	readInputStream(request.getInputStream());

			@SuppressWarnings("rawtypes")
			Map	mJsonReq	= new HashMap();

			try {
				String contentType = request.getContentType();
				if(contentType == null || contentType.indexOf("application/json") == -1){
					response.setStatus(HttpURLConnection.HTTP_NOT_ACCEPTABLE);

					JSONObject resJson = new JSONObject();
					resJson.put("status", 		"ERROR");
					resJson.put("errCode", 		406);
					resJson.put("reason", 		"Not Acceptable");
					resJson.put("version", 		"1.0");
					modelAndView.addObject("jsonData", resJson.toString());
					modelAndView.setViewName(VIEW_NAME_JSON);
					return modelAndView;
				}

				mJsonReq	=	JSONObject.fromObject(sIn);

			} catch (JSONException e) {
				e.printStackTrace();
				response.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);

				JSONObject resJson = new JSONObject();
				resJson.put("status", 		"ERROR");
				resJson.put("errCode", 		500);
				resJson.put("reason", 		"Bad request.");
				resJson.put("version", 		"1.0");
				modelAndView.addObject("jsonData", resJson.toString());
				modelAndView.setViewName(VIEW_NAME_JSON);
				return modelAndView;
			}

			// 실제 수신 처리 수행 Start
			String			errCd				= "0000";
			String			sSign				= HmacSha256Util.unSignFromJSONStr(sIn, key);
			if ("".equals(sSign)) {
				// 에러 처리
				errCd	= "8003";
			}
			if ("confirm".equals(mJsonReq.get("status"))) {
				// 채널 DB 업데이트 처리
				System.out.println("DB Transaction Status Update");
			} else {
				errCd	= "9999";
			}

			HashMap<String, String> hmOut		= new HashMap<String, String>();
			if ("0000".equals(errCd)) {
				hmOut.put("merchantInformation.merchantId",		(String) mJsonReq.get("merchantInformation.merchantId"));
				hmOut.put("merchantInformation.merchantSiteId",	(String) mJsonReq.get("merchantInformation.merchantSiteId"));
				hmOut.put("clientReferenceInformation.code",	(String) mJsonReq.get("clientReferenceInformation.code"));
				hmOut.put("customerId",							(String) mJsonReq.get("customerId"));
				hmOut.put("status",								"SUCCESS");
			} else {
				hmOut.put("merchantInformation.merchantId",		(String) mJsonReq.get("merchantInformation.merchantId"));
				hmOut.put("merchantInformation.merchantSiteId",	(String) mJsonReq.get("merchantInformation.merchantSiteId"));
				hmOut.put("clientReferenceInformation.code",	(String) mJsonReq.get("clientReferenceInformation.code"));
				hmOut.put("customerId",							(String) mJsonReq.get("customerId"));
				hmOut.put("status",								"DECLINED");
				hmOut.put("errorInformation.errCd",				errCd);
				hmOut.put("errorInformation.reason",			"ERROR");
			}
			// 응답 전송 처리
			JSONObject		oSendJson			= JsonUtil.makeJsonFromMap(hmOut);
			String			sSendJson			= oSendJson.toString();
			sSendJson							= HmacSha256Util.signFromJSONStr(sSendJson, key);
			modelAndView.addObject("jsonData",	sSendJson);

			// 실제 수신 처리 수행 End
			modelAndView.setViewName(VIEW_NAME_JSON);
			return modelAndView;

		} catch (Exception e) {
			response.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
			// 반환값인 ModelAndView 인스턴스 생성
			modelAndView = new ModelAndView();
			modelAndView.setViewName(VIEW_NAME_JSON);
			//modelAndView.addObject(RES_STATUS, ae.getErrCd());
			
			String errCd		= "500";
			
			JSONObject resJson = new JSONObject();
			resJson.put("status", 		"ERROR");
			resJson.put("errCode", 		500);
			resJson.put("reason", 		"["+errCd+"] Error");
			resJson.put("version", 		"1.0");
			modelAndView.addObject("jsonData", resJson.toString());

			return modelAndView;
		}

	}

	private String readInputStream(InputStream is) {
		BufferedReader	brIn		=	new BufferedReader(new InputStreamReader(is));
		StringBuilder	sbIn		=	new StringBuilder();
		String			sStr		=	null;

		try {
			while ((sStr = brIn.readLine()) != null) {
				sbIn.append(sStr);
			}
		} catch (Exception e) {
				return "";
		}
		return sbIn.toString();
	}
	/* 수신 처리 End */

}
