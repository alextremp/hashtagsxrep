package cat.xarxarepublicana.hashtagsxrep.application.monitor;

import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;

import java.util.List;

public class ListMonitorUseCase {

    private final MonitorRepository monitorRepository;

    public ListMonitorUseCase(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    public ListMonitorResponse listMonitor() {
        List<Monitor> monitorList = monitorRepository.getLastMonitors();
        return new ListMonitorResponse(monitorList);
    }

    public static class ListMonitorResponse {

        private final List<Monitor> monitorList;

        public ListMonitorResponse(List<Monitor> monitorList) {
            this.monitorList = monitorList;
        }

        public List<Monitor> getMonitorList() {
            return monitorList;
        }
    }
}
