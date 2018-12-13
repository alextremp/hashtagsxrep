package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.MonitorMapper;

import java.util.List;

public class JdbcMonitorRepository implements MonitorRepository {

    private final MonitorMapper monitorMapper;

    public JdbcMonitorRepository(MonitorMapper monitorMapper) {
        this.monitorMapper = monitorMapper;
    }

    @Override
    public void save(Monitor monitor) {
        monitorMapper.insert(monitor);
    }

    @Override
    public void updateCursor(Monitor monitor, String newCursor) {
        monitor.updateCursor(newCursor);
        monitorMapper.updateCursor(monitor);
    }

    @Override
    public Monitor findById(String id) {
        return monitorMapper.selectOneById(id);
    }

    @Override
    public List<Monitor> getActiveMonitors() {
        return monitorMapper.selectActive();
    }
}
