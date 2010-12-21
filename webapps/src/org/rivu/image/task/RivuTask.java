package org.rivu.image.task;

import org.rivu.image.core.RivuContext;
import org.springframework.core.task.TaskExecutor;

public class RivuTask {
	private TaskExecutor taskExecutor;
	public void execute() throws Exception {
		for(final JobDetail job : RivuContext.jobList){
			if(!job.isRun()){
				taskExecutor.execute(new Runnable() {
					public void run() {
						new Fetcher(job).run();
					}
				});
			}
		}
	}
	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}
	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	
}
