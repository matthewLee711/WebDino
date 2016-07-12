package com.interns.webdino.perftest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.springframework.stereotype.Component;



@Component
public class JobMaster {

    private HashMap<String,Job> jobs = new HashMap<>();
    private Job[] jobCompleted = new Job[6];

    public Job getJob(String name){
        return jobs.get(name);
    }

    public void addJob(Job job){
        jobs.put(job.getName(), job);
    }

    public void removeJob(String name){
        jobs.remove(name);
    }
    
    public void load() throws IOException, ClassNotFoundException{
  
		FileInputStream fis = new FileInputStream("dataBase1.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		jobs = (HashMap) ois.readObject();
		ois.close();
		fis.close();
    }
    
    public void save() throws IOException{
    	FileOutputStream fos = new FileOutputStream("dataBase1.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(jobs);
		oos.close();
		fos.close();
    }

    public String runJob(String name){	//void

        Job job = jobs.get(name);

        if(job != null){
            return job.run();//job.run
        }
        return "";	//delete
    }

    public JobStatus getJobStatus(String name){

        Job job = jobs.get(name);

        if(job != null){
            return job.getStatus();
        } else {
            return null;
        }
    }
    
    
    //Store completed job 
    public Job[] finishedJob(Job status){
    	//issue: can not give unique identifier for each html box.
    	//each box points to an index in job.
    	//each completed job is going to be added to the completed array
    	//javascript is then going to iterate through each box and display the contents of each job object
    	
    	return jobCompleted;
    }
    
    
    
    //Check xml status -- returns testing, waiting, complete
    public String getxmlInfo(String name){
    	
    	Job job = jobs.get(name);
    	return job.getParsedXml(false);/////////////////////////////////////////////////
    }
    
    //if test complete, get information
    public String getfirstByte(String name) {
    	Job job = jobs.get(name);
    	return job.getfirstByte();
    }
    
    public String getloadTime(String name) {
    	Job job = jobs.get(name);
    	return job.getloadTime();
    }




}
