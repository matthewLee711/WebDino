package com.interns.webdino.perftest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.interns.webdino.client.support.HttpClientManager;

public class Job{

    private static final Logger LOGGER = LoggerFactory.getLogger(Job.class);

    private String name;
    private String url;
    private String rawXml;
    private HttpClientManager clientManager;
    private JobStatus status;

    public void run() {

        ResponseEntity<String> resp = null;

        RestTemplate rt = clientManager.getRestTemplate();

        resp = rt.getForEntity(this.url, String.class);

        LOGGER.info("Got result for job start: \n" + resp.getBody());

        this.status = JobStatus.STARTED;
        this.rawXml = resp.getBody();

    }

    public Job(String name, String url, HttpClientManager clientManager){

        this.name = name;
        this.url = "http://www.webpagetest.org/runtest.php?url=homedepot.com&runs=1&f=xml&k=A.77d136a242db623122d15fab6a8bc2a7";
        this.clientManager = clientManager;

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

}
