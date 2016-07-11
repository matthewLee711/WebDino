package com.interns.webdino.perftest;

import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/*import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;*/

import com.interns.webdino.client.support.HttpClientManager;

public class Job{

    /**
	 * 
	 */
	//private static final long serialVersionUID = -4388859193052577178L;

	private static final Logger LOGGER = LoggerFactory.getLogger(Job.class);
    
    public String statusCode;
    public String firstByte;
    public String loadTime;
    public String name;
    private String url;
    private String rawXml;
    private String parsedXml;
    private HttpClientManager clientManager;
    private JobStatus status;
    private boolean mock;
    
    public int firstAverage=0, fullAverage=0;
    public ArrayList<Integer> firstByteAverage = new ArrayList<Integer>();
    public ArrayList<Integer> fullLoadAverage = new ArrayList<Integer>();
    
    public JSONArray firstByteAverageJson = new JSONArray();
    public JSONArray fullLoadAverageJson = new JSONArray();

    int keyCounter =0;

    public String run() {

        ResponseEntity<String> resp = null;

        if(this.mock){

            this.rawXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response><statusCode>200</statusCode><statusText>Ok</statusText><data><testId>160608_S6_2GFY</testId><ownerKey>e11c3d6ad20d76b8f158044105a58fefc2871562</ownerKey><xmlUrl>http://www.webpagetest.org/xmlResult/160608_S6_2GFY/</xmlUrl><userUrl>http://www.webpagetest.org/result/160608_S6_2GFY/</userUrl><summaryCSV>http://www.webpagetest.org/result/160608_S6_2GFY/page_data.csv</summaryCSV><detailCSV>http://www.webpagetest.org/result/160608_S6_2GFY/requests.csv</detailCSV><jsonUrl>http://www.webpagetest.org/jsonResult.php?test=160608_S6_2GFY</jsonUrl></data></response>";

        } else {

            RestTemplate rt = clientManager.getRestTemplate();
            resp = rt.getForEntity(this.url, String.class);

            this.rawXml = resp.getBody();
        }

        this.status = JobStatus.STARTED;

        try {
            parseXML();
        } catch (Exception e) {
            LOGGER.error("ParseXML failed", e);
        }


        return parsedXml;

    }

    public Job(String name, String url, HttpClientManager clientManager, boolean mock) {
    	
        this.name = name;
        //A.77d136a242db623122d15fab6a8bc2a7
        //A.9be00fc39e0fe97ae0165d9b0ad614cc
        System.out.println("Key: " + keyCounter%2);
        if(keyCounter%2==1)
        {
	        this.url = "http://www.webpagetest.org/runtest.php?url="
	        			+ url
	        			+ "&runs=1&f=xml&k=A.9be00fc39e0fe97ae0165d9b0ad614cc";
        }
        else
        {
        	 this.url = "http://www.webpagetest.org/runtest.php?url="
        		        + url
        		        + "&runs=1&f=xml&k=A.77d136a242db623122d15fab6a8bc2a7";
        }
        keyCounter++;
        this.clientManager = clientManager;
        this.mock = mock;
    }

    public void parseXML() throws Exception {
        Document doc;
        try {
            // load webpage
            doc = Jsoup.connect(url).get();
            //System.out.println("load" + doc.toString());

            // extract webpage content
            Element link = doc.select("xmlurl").first();
            parsedXml = link.text();
            System.out.println("Linkn" + parsedXml);

            // Web link to JSON

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRawXml() {
        return rawXml;
    }

    public void setRawXml(String rawXml) {
        this.rawXml = rawXml;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }
    
    //EXtract TTFB and loadTime
    public String getParsedXml(boolean average) {
    	Document doc;
        try {
        	//parsedXml
        	String xmlurl = parsedXml;
            //http://www.homedepot.com/p/Husky-16-oz-Fiberglass-Claw-Hammer-N-G16CHD-HN/205386272
        	//http://www.webpagetest.org/xmlResult/160608_N0_1GNM/
        	//Extract status code and data from parsedXml link  
            doc = Jsoup.connect(xmlurl).get();
            //System.out.println("load" + doc.toString());
            
            //Retrieve status code
            statusCode = doc.getElementsByTag("statuscode").text();
            System.out.println("Inside getParsed: " + statusCode);
            
            //Conditional for status codes and extracting text
            

            if("200".compareTo(statusCode) == 0 && average) {
            	Elements ttfbs = doc.getElementsByTag("ttfb");
            	Elements loadtimes = doc.getElementsByTag("loadtime");
            	//System.out.println(ttfbs + "and" + loadtimes);
            	
            	
            	for(Element loadtime : loadtimes) { loadTime = loadtime.text(); break; }
            	for(Element ttfb : ttfbs) { firstByte = ttfb.text(); break; }
            	System.out.println("Inner loadTime: " + loadTime);
            	System.out.println("Inner loadByte: " + firstByte);
            	
            	firstByteAverage.add(Integer.parseInt(firstByte));
            	fullLoadAverage.add(Integer.parseInt(loadTime));
            	if(new Integer(Integer.parseInt(loadTime))<25000)
            	{
            		firstByteAverageJson.add(new Integer(Integer.parseInt(firstByte)));
            		fullLoadAverageJson.add(new Integer(Integer.parseInt(loadTime)));
            	}
            	else
            	{
            		firstByteAverageJson.add(0);
            		fullLoadAverageJson.add(0);
            	}
            	if(firstByteAverageJson.size()>15||fullLoadAverageJson.size()>15)
            	{
            		firstByteAverageJson.remove(0);
            		fullLoadAverageJson.remove(0);
            		System.out.println("Removed Ellement");
            	}
            		
            	System.out.println("Lol: " + firstByteAverageJson);
            	average();
            	
            	return "200";
            }
            else if("100".compareTo(statusCode) == 0) {
            	//waiting
            	System.out.println("In Queue");
            	return "100";
            }
            else if("101".compareTo(statusCode) == 0) {
            	//testing
            	System.out.println("Testing");
            	return "101";
            }
            else if("200".compareTo(statusCode) == 0 )
            {
            	return "200";
            }
            else {
            	System.out.println("Failure");
            }
            
            

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    	
    	
    	return "beep";
    }
    
    public void average()
    {	
    	firstAverage=0; fullAverage=0;
    	for(int add:firstByteAverage)
    	{
    		firstAverage+=add;
    	}
    	firstAverage = firstAverage/firstByteAverage.size();
    	
    	for(int add:fullLoadAverage)
    	{
    		if(add<25000)
    		{
    			fullAverage+=add;
    		}
    	}
    	fullAverage = fullAverage/fullLoadAverage.size();
    	System.out.println("Averages: " + firstAverage + " " + fullAverage);
    }
    
    public String getfirstByte() {
    	return firstByte;
    }
    
    public String getloadTime() {
    	return loadTime;
    }
    
    
    public void setParsedXml(String parsedXml) {
        this.parsedXml = parsedXml;
    }

}
