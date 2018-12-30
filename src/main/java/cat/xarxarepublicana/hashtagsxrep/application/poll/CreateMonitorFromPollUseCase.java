package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Proposal;

import java.util.List;
import java.util.logging.Logger;

public class CreateMonitorFromPollUseCase {

    private static final Logger LOG = Logger.getLogger(CreateMonitorFromPollUseCase.class.getName());

    private final MonitorRepository monitorRepository;
    private final MonitorFactory monitorFactory;
    private final PollRepository pollRepository;

    public CreateMonitorFromPollUseCase(MonitorRepository monitorRepository, MonitorFactory monitorFactory, PollRepository pollRepository) {
        this.monitorRepository = monitorRepository;
        this.monitorFactory = monitorFactory;
        this.pollRepository = pollRepository;
    }

    public void createMonitorFromPoll() {
        Monitor monitor;
        Proposal winnerProposal;
        List<Poll> finishedPollsWithNoMonitor = pollRepository.findFinishedPollsWithNoMonitor();
        for (Poll poll: finishedPollsWithNoMonitor) {
            winnerProposal = pollRepository.findWinnerProposal(poll);
            if (winnerProposal != null) {
                monitor = monitorFactory.createMonitorFromPoll(poll.getId(), winnerProposal.getHashtag(), poll.getEndVotingTime());
                LOG.info("Creating new monitor from poll: " + monitor.getId() + " - " + monitor.getTwitterQuery());
                monitorRepository.save(monitor);
            } else {
                LOG.warning("Finished poll with no winner proposal, consider to delete it");
            }
        }
    }
}
