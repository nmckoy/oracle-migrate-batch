package gov.fcc.run;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Migrate {

    public static void main(String[] args) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Migrate.run();
    }

    private static void run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        ApplicationContext context = new ClassPathXmlApplicationContext("oracle-migrate-job.xml");

        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
//        Job job = context.getBean("migrateJob", Job.class);
        Job job = context.getBean("migrateJobPartitioned", Job.class);

        JobParameters params = new JobParametersBuilder()
                .addString("sqlTest",          MigrateInterface.sqlTest)
                .addString("fromClause",   MigrateInterface.fromClause)
                .addString("selectClause", MigrateInterface.selectClause)
                .addString("whereClause",  MigrateInterface.whereClause)
                .addString("sortKey",      MigrateInterface.sortKey)
                .toJobParameters();

        JobExecution jobExecution = jobLauncher.run(job, params);

        System.out.println(new Date() + ": " + jobExecution.getExitStatus().getExitCode());
    }
}
