package com.example.demo.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Paulo Henrique
 */
public class SampleJob implements Job {
     
    public SampleJob() {
      }

      public void execute(JobExecutionContext context) throws JobExecutionException {
          System.err.println("Sample Job Executing");
      }
}
