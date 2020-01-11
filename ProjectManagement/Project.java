package ProjectManagement;

import java.util.ArrayList;


public class Project {
	String name;
	int priority,budget;
	ArrayList<Job>[] jobs; 

	public Project(String name,int priority,int budget){
		this.name=name;
		this.budget=budget;
		this.priority=priority;
		jobs=new ArrayList[2];
		jobs[0]=new ArrayList<Job>();
		jobs[1]=new ArrayList<Job>();
	}

	public void addBudget(int append){
		budget+=append;
	}

	public int getBudget(){
		return budget;
	}

	public int getPriority(){
		return priority;
	}

	public void addJob(Job job){
		jobs[0].add(job);
	}

	public void completeJob(Job job){
		jobs[0].remove(job);
		jobs[1].add(job);
	}

	public ArrayList<Job>[] getJobs(){
		return jobs;
	}

	@Override
	public String toString(){
		return name;
	}
}
