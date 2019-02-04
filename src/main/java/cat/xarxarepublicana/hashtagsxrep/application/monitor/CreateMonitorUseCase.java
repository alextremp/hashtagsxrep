package cat.xarxarepublicana.hashtagsxrep.application.monitor;

import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;

import java.time.LocalDateTime;

public class CreateMonitorUseCase {

    private final MonitorRepository monitorRepository;
    private final MonitorFactory monitorFactory;

    public CreateMonitorUseCase(MonitorRepository monitorRepository, MonitorFactory monitorFactory) {
        this.monitorRepository = monitorRepository;
        this.monitorFactory = monitorFactory;
    }

    public void createMonitor(User author, String twitterQuery, LocalDateTime endTime) {
        Monitor monitor = monitorFactory.createNewMonitor(author.getId(), author.getNickname(), twitterQuery, endTime);
        monitorRepository.save(monitor);
    }
}
