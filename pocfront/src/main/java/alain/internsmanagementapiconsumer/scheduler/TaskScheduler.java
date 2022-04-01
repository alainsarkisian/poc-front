package alain.internsmanagementapiconsumer.scheduler;

import alain.internsmanagementapiconsumer.client.InternClient;
import com.alain.dto.json.Intern;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class TaskScheduler {

    private final InternClient internClient;
    private static final Logger logger = LoggerFactory.getLogger(TaskScheduler.class);


    @Scheduled(fixedRate = 5000L)
    @SchedulerLock(name = "TaskScheduler_scheduledTask",
            lockAtLeastForString = "PT5M", lockAtMostForString = "PT14M")
    public void addAnIntern(){
        //System.out.println("SCHEDULER");
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
            System.out.println("SCHEDULER LAUNCHED");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return result;
    }
}
