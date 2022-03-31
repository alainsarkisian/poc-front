package alain.internsmanagementapiconsumer;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
@EnableFeignClients
//@ComponentScan(basePackages = {"alain.internsmanagementapiconsumer.scheduler","alain.internsmanagementapiconsumer.config"})
@SpringBootApplication
public class InternsManagementApiConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternsManagementApiConsumerApplication.class, args);
    }
}