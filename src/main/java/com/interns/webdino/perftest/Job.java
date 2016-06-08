package com.interns.webdino.perftest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(Job.class);

    private String name;
    private String url;
    private String rawXml;
    private String parsedXml;
    private HttpClientManager clientManager;
    private JobStatus status;

    public String run() {

        ResponseEntity<String> resp = null;

        RestTemplate rt = clientManager.getRestTemplate();

        resp = rt.getForEntity(this.url, String.class);

        this.status = JobStatus.STARTED;
        this.rawXml = resp.getBody();
        try {
			parseXML();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //LOGGER.info("Got result for job start: \n" + rawXml);
        return parsedXml;
        
    }

    public Job(String name, String url, HttpClientManager clientManager){

        this.name = name;
        this.url = "http://www.webpagetest.org/runtest.php?url=" + url + "&runs=1&f=xml&k=A.77d136a242db623122d15fab6a8bc2a7";
        this.clientManager = clientManager;

    }
    
    public void parseXML() throws Exception
    {
    	Document doc;
    	       try {
    	           // load webpage
    	           doc = Jsoup.connect(url).get();
    	          // System.out.println("load" + doc.toString());
    	 
    	           // extract webpage content
    	           Element link = doc.select("xmlurl").first();
    	           parsedXml = link.text();
    	            System.out.println("Linkn" + parsedXml);
    	 
    	           // Web link to JSON
    	           
    	       } catch (IOException e) {
    	           // TODO Auto-generated catch block
    	           e.printStackTrace();
    	       }
    	
    	
    	
    	/*try{
    		SAXParserFactory factory = SAXParserFactory.newInstance();
    	    SAXParser saxParser = factory.newSAXParser();
    	    DefaultHandler handler = new DefaultHandler(){
    	    	public void startElement(String)
    	    	
    	    	
    	    	
    	    	
    	    };


    	}catch(Exception e){
    		
    	}*/
    	
    	
    }
  

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
    
    public String getParsedXml() {
		return parsedXml;
	}

	public void setParsedXml(String parsedXml) {
		this.parsedXml = parsedXml;
	}

}
