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

    public CreateMonitorResponse createMonitor(User author, String twitterQuery, LocalDateTime startTime) {
        Monitor monitor = monitorFactory.createNewMonitor(author.getId(), author.getNickname(), twitterQuery, startTime);
        monitorRepository.save(monitor);
        Monitor byId = monitorRepository.findById(monitor.getId());
        return new CreateMonitorResponse(monitor);
    }

    public static class CreateMonitorResponse {
        private final Monitor monitor;

        public CreateMonitorResponse(Monitor monitor) {
            this.monitor = monitor;
        }

        public Monitor getMonitor() {
            return monitor;
        }
    }
}
