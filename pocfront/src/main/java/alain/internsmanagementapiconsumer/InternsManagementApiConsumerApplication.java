package alain.internsmanagementapiconsumer;

import alain.internsmanagementapiconsumer.client.InternClient;
import alain.internsmanagementapiconsumer.scheduler.TaskScheduler;
import com.alain.dto.json.Intern;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.core.SchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
//@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
@EnableFeignClients
/*@ComponentScan(basePackages = {"alain.internsmanagementapiconsumer.scheduler","alain.internsmanagementapiconsumer.config"},
basePackageClasses = {TaskScheduler.class})*/
@SpringBootApplication
public class InternsManagementApiConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternsManagementApiConsumerApplication.class, args);
    }

    @Component
    @EnableScheduling
    @RequiredArgsConstructor
    public class TaskScheduler {

        private final InternClient internClient;
        private final Logger logger = LoggerFactory.getLogger(alain.internsmanagementapiconsumer.scheduler.TaskScheduler.class);

        @Scheduled(fixedRate = 5000L)
        @SchedulerLock(name = "TaskScheduler_scheduledTask",
                lockAtLeastForString = "PT5S", lockAtMostForString = "PT1M")
        public void addAnIntern(){
            //System.out.println("GO");
            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            Intern internGenerated = new com.alain.dto.json.Intern();
            internGenerated.setFirstName(firstName);
            internGenerated.setLastName(lastName);
            String result = null;
            try {
                result = this.internClient.addAnIntern(internGenerated);
                logger.info(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}