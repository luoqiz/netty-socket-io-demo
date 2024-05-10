package top.luoqiz.im.nettysocketio.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AnnoTask {

    @Scheduled(cron = "*/1 * * * * ?")
    public void sayWord() {
        System.out.println("world");
    }
}
