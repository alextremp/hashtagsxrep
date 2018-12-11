package cat.xarxarepublicana.hashtagsxrep.domain.monitor;

import java.util.List;

public interface MonitorRepository {
    void save(Monitor monitor);

    Monitor findById(String id);

    List<Monitor> getActiveMonitors();
}
