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
    public void updateCursor(Monitor monitor, String nextQueryString) {
        monitor.updateCursor(nextQueryString);
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

    @Override
    public String getMaxTweetId(String monitorId) {
        return monitorMapper.selectMaxTweetId(monitorId);
    }

    @Override
    public List<Monitor> getLastMonitors() {
        return monitorMapper.selectLast(10);
    }

    @Override
    public Monitor findByTwitterQuery(String twitterQuery) {
        return monitorMapper.selectOneByTwitterQuery(twitterQuery);
    }

    @Override
    public void disable(String id) {
        monitorMapper.disable(id);
    }

    @Override
    public void delete(Monitor monitor) {
        monitorMapper.delete(monitor.getId());
    }
}
