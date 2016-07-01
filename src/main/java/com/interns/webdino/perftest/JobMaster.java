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
  
		FileInputStream fileInputStream = new FileInputStream("dataBase1");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		jobs = (HashMap<String, Job>) objectInputStream.readObject();
		objectInputStream.close();
		fileInputStream.close();
    }
    
    public void save() throws IOException{
    	FileOutputStream fileOutputStream = new FileOutputStream("dataBase1");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(jobs);
		objectOutputStream.close();
		fileOutputStream.close();
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
