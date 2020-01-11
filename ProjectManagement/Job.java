package ProjectManagement;

public class Job implements Comparable<Job> {
	String name;
	User user;
	Project project;
	int runtime;
	int id;

	public Job(String name,Project project,User user,int runtime,int id){
		this.name=name;
		this.user=user;
		this.project=project;
		this.runtime=runtime;
		this.id=id;
	}

	public int getRuntime(){
		return runtime;
	}

	public Project getProject(){
		return project;
	}

	public User getUser(){
		return user;
	}

	@Override
	public String toString(){
		return name;
	}

    @Override
    public int compareTo(Job job) {
        if (this.project.getPriority()<job.project.getPriority())
        	return -1;
        else if(this.project.getPriority()==job.project.getPriority()){
        	if (this.id<job.id)
        		return 1;
        	else
        		return -1;
        }
        else 
        	return 1;
    }
}