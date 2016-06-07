package com.interns.webdino.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interns.webdino.model.EchoResponseModel;

@RestController
@RequestMapping("/echo")
public class EchoService extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(EchoService.class);
	//Threads
	private Thread t;
	// Create Map to store threads
	Map<String, Thread> hm = new HashMap<String, Thread>();

	// make two methods, one for get and one for post
	@RequestMapping(method = { RequestMethod.GET })
	public ResponseEntity<EchoResponseModel> echoGet(
	        HttpServletRequest req,
	        @RequestParam(name="value", required = false) String value) {

		EchoResponseModel model = new EchoResponseModel(value);

		ResponseEntity<EchoResponseModel> result = ResponseEntity.ok(model);

		return result;

	}

	// each time input is made, create a thread for it.
	@RequestMapping(method = { RequestMethod.POST })
	public ResponseEntity<EchoResponseModel> echoPost(HttpServletRequest req,
			@RequestParam(value = "value", required = false) String value,
			@RequestParam(value = "data", required = false) String data, @RequestBody String body) throws Exception {

		EchoResponseModel response;
		System.out.println("asdjfkasjdfskjdffdshksdfhkdsfjkd");

		/*
		//spawn thread
		Thread t = new Thread();
		//store thread in map
		addThreadToMap(t);

		//check threads in map
		//call thread from map
		hm.get("t" + hm.size()).run();
		*/

		// put a thread around this function
		urlTester("www.homedepot.com", "1", "xml", "dasfdsfadsf");

		if (value == null && data == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		System.out.println("asdjfkasjdfskjdffdshksdfhkdsfjkd");
		LOGGER.info("value param was %s", value);
		LOGGER.info("data param was %s", data);

		if (value != null) {
			response = new EchoResponseModel(value);
		} else {
			response = new EchoResponseModel(data);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	//starts thread
	public void start(String urlTester, String runs, String fileType, String key) throws Exception {
		// put a thread around this function
		urlTester("www.homedepot.com", "1", "xml", "dasfdsfadsf");
	}

	// This will add the thread with the key [t0 .... tn]
	public void addThreadToMap(Thread t) {
		/*Set set = hm.entrySet();
		Iterator i = set.iterator();
		Map.Entry me;
		//check map keys that each
		while(i.hasNext()) {
			//iterate through map
			me = (Map.Entry)i.next();
		}*/
		//return null key

		//random key generator -- prevent key collision


		//add thread to map -- keys should actually delete themselves
		hm.put("t" + hm.size(), t);
	}

	public void urlTester(String urlTester, String runs, String fileType, String key) throws Exception {
		// http://www.webpagetest.org/runtest.php?url=guru99.com&runs=1&f=xml&k=A.9be00fc39e0fe97ae0165d9b0ad614cc
		String test = "http://www.webpagetest.org/runtest.php?url=" + urlTester + "&runs=" + runs + "&f=" + "xml"
				+ "&k=A.9be00fc39e0fe97ae0165d9b0ad614cc";

		String sURL = "http://www.webpagetest.org/runtest.php?url=homedepot.com&runs=1&f=xml&k=A.77d136a242db623122d15fab6a8bc2a7"; // just
																																	// a
																																	// string

		Document doc;
		try {
			// load webpage
			doc = Jsoup.connect(sURL).get();
			// System.out.println(doc.toString());

			// extract webpage content
			Element link = doc.select("jsonurl").first();
			// System.out.println(link.toString());

			// Web link to JSON
			String linkText = link.text();

			// String json =
			// "http://www.webpagetest.org/jsonResult.php?test=160606_GS_X0N";
			String extractJson = readUrl(linkText);// replace with link Text
			System.out.println("beep");
			System.out.println(extractJson.toString());

			// Create json extractor
			JSONObject obj = new JSONObject(extractJson);

			// String pageData = obj.getJSONObject("data").getString("id");
			int status = (int) obj.get("statusCode");
			// System.out.println(status);

			// Checks if Test is complete
			String reset;
			while (200 != status) {
				reset = readUrl(linkText);
				obj = new JSONObject(reset);
				status = (int) obj.get("statusCode");

				// Testing status
				if (100 == status) {
					TimeUnit.SECONDS.sleep(3);
					System.out.println("Testing");
				}
				// Wait status
				else if (200 != status) {
					try {
						TimeUnit.SECONDS.sleep(3);
						System.out.println("waiting");
					} catch (InterruptedException e) {
						// Handle exception
					}
				}
				// Error handling
				else if (400 <= status) {
					System.out.println("error");
					break;
				}
				System.out.println(status);
			}
			System.out.println("ayy");

			int TTFB = obj.getJSONObject("data").getJSONObject("runs").getJSONObject("1").getJSONObject("firstView")
					.getInt("TTFB");
			int loadTime = obj.getJSONObject("data").getJSONObject("runs").getJSONObject("1").getJSONObject("firstView")
					.getInt("loadTime");
			System.out.println("ttfb: " + TTFB);
			System.out.println("loadtime: " + loadTime);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// End of urlTester

	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

}

/*
 * package com.interns.webdino.service;
 *
 * import javax.servlet.http.HttpServletRequest;
 *
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController;
 *
 * import com.interns.webdino.model.EchoResponseModel;
 *
 * @RestController
 *
 * @RequestMapping("/echo") public class EchoService {
 *
 * private static final Logger LOGGER =
 * LoggerFactory.getLogger(EchoService.class);
 *
 * //make two methods, one for get and one for post
 *
 * @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}) public
 * ResponseEntity<EchoResponseModel> echo( HttpServletRequest req,
 *
 * @RequestParam(value="value", required=false) String value,
 *
 * @RequestParam(value="data", required=false) String data,
 *
 * @RequestBody String body){
 *
 * EchoResponseModel response;
 *
 * if (value == null && data == null) { return
 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); }
 *
 * LOGGER.info("value param was %s", value); LOGGER.info("data param was %s",
 * data);
 *
 * if(value != null){ response = new EchoResponseModel(value); } else { response
 * = new EchoResponseModel(data); }
 *
 * return new ResponseEntity<>(response, HttpStatus.OK);
 *
 * }
 *
 *
 *
 * }
 */