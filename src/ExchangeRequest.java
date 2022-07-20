import com.cholocb.p2e.api.VerseB;

HashMap<String, String> hmIn = new HashMap<String, String>();
hmIn.put(“merchantInformation.merchantId”,	“000000000001”);
hmIn.put(“merchantInformation.merchantSiteId”,	“000001”);
hmIn.put(“customerId”,				“000000000001”);
hmIn.put(“fromCurrency”, 			“GOLD”);
hmIn.put(“toCurrency”, 				“SLAYB”);
hmIn.put(“fromAmount”, 				“100”);
hmIn.put(“exchangeRate”, 			“10%”);
hmIn.put(“notifyUrl”, 				“https://versegeo.com/API/backNotify”);
hmIn.put(“key”, 				“0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF”);
hmIn.put(“initialVector”, 			“0123456789ABCDEF0123456789ABCDEF”);
HashMap<String, String> hmOut = VerseB.exReq(hmIn);
