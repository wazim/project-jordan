package net.wazim.jordan;

import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.properties.JordanProperties;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class JordanScheduler {

    public JordanScheduler(JordanProperties properties, BluRayDatabase database) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(AmazonGoer.class)
                .withIdentity("amazonJob", "group1")
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("amazonTrigger", "group1")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInMinutes(properties.minutesToRefresh())
                                .repeatForever())
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.getContext().put("database", database);
        scheduler.getContext().put("properties", properties);

        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
