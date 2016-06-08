package com.interns.webdino.perftest;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class JobMaster {
	//Store all jobs
    private HashMap<String,Job> jobs = new HashMap<>();
    //return job name
    public Job getJob(String name){
        return jobs.get(name);
    }
    //add jobs to hash map
    public void addJob(Job job){
        jobs.put(job.getName(), job);
    }
    //remove jobs from hash map
    public void removeJob(String name){
        jobs.remove(name);
    }
    //execute job
    public void runJob(String name){

        Job job = jobs.get(name);

        if(job != null){
            job.run();
        }
    }
    //Tells status of job
    public JobStatus getJobStatus(String name){

        Job job = jobs.get(name);

        if(job != null){
            return job.getStatus();
        } else {
            return null;
        }
    }




}
