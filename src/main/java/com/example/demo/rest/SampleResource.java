package com.example.demo.rest;

import com.example.demo.jobs.SampleJob;
import org.quartz.DateBuilder;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Paulo Henrique
 */
@RestController
public class SampleResource {

    @Autowired
    private Scheduler scheduler;

    @GetMapping("/schedule")
    public void schedule() throws SchedulerException {
        System.out.println("***********************Schedule");

        // define the job and tie it to our MyJob class
        JobDetail job = newJob(SampleJob.class)
                .withIdentity("job1", "group1")
                .build();

        /*
         //Trigger using cron expressions
         Trigger trigger = TriggerBuilder
         .newTrigger()
         .withIdentity("dummyTriggerName", "group1")
         .withSchedule(
         CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
         .build();
         */
        /*
         //Trigger that will fire only once
         trigger = (SimpleTrigger) newTrigger()
         .withIdentity("trigger5", "group1")
         .startAt(futureDate(5, IntervalUnit.MINUTE)) // use DateBuilder to create a date in the future
         .forJob(myJobKey) // identify job with its JobKey
         .build();
         */
        
        // Trigger the job to run now, and then repeat every 10 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);

    }

}
