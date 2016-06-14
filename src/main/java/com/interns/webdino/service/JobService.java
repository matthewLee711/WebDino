package com.interns.webdino.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interns.webdino.client.support.HttpClientManager;
import com.interns.webdino.perftest.Job;
import com.interns.webdino.perftest.JobMaster;

@RestController
@RequestMapping("/job")
public class JobService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

    @Autowired
    JobMaster jobMaster;

    @Autowired
    HttpClientManager clientManager;

    @RequestMapping(method = { RequestMethod.GET })
    public ResponseEntity<Job> getJob(HttpServletRequest req,
            @RequestParam(value = "name", required = false) String name) {

        Job job = null;
        System.out.println("pppppp: " + name);
        if (null != name) {
            job = jobMaster.getJob(name);
        }

        return new ResponseEntity<>(job, HttpStatus.OK);

    }
    
    
    @RequestMapping(path="/ttfb", method = { RequestMethod.GET })
    public ResponseEntity<String> getTTFB(HttpServletRequest req,
            @RequestParam(value = "name", required = false) String name) {

        Job job = null;
        String result = null;
        System.out.println("ggggg: " + name);

        if (null != name) {
            job = jobMaster.getJob(name);
        }
        if(job != null){
        	result = job.getParsedXml();
        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @RequestMapping(method = { RequestMethod.POST })
    public ResponseEntity<Job> startJob(HttpServletRequest req,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "url", required = true) String url,
            @RequestParam(value = "mock", required = false, defaultValue = "false") boolean mock) {
    	System.out.println(name + " " + url + " " + mock);
        Job job = new Job(name, url, clientManager, mock);

        jobMaster.addJob(job); System.out.print("added " + job.getName());
        jobMaster.runJob(job.getName());
        
        jobMaster.getxmlInfo(job.getName());
        
        return new ResponseEntity<>(job, HttpStatus.OK);

    }

}