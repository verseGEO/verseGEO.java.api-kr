import com.cholocb.p2e.api.VerseB;

HashMap<String, String> hmIn = new HashMap<String, String>();
hmIn.put(“merchantInformation.merchantId”, 	“000000000001”);
hmIn.put(“merchantInformation.merchantSiteId”, 	“000001”);
hmIn.put(“customerId”, 				“000000000001”);
hmIn.put(“outAddress”, 				“0xcce4726a8bca553e31c5341fa456a43062b46520”);
hmIn.put(“outPassword”, 			“abcd1234”);
hmIn.put(“key”, 				“0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF”);
hmIn.put(“initialVector”, 			“0123456789ABCDEF0123456789ABCDEF”);
HashMap<String, String> hmOut = VerseB.OutPasswordReg(hmIn);
