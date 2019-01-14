package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.notice.Notice;
import cat.xarxarepublicana.hashtagsxrep.domain.notice.NoticeRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Proposal;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.logging.Logger;

public class FinishPollUseCase {

    private static final Logger LOG = Logger.getLogger(FinishPollUseCase.class.getName());

    private final MonitorRepository monitorRepository;
    private final MonitorFactory monitorFactory;
    private final PollRepository pollRepository;
    private final NoticeRepository noticeRepository;

    public FinishPollUseCase(MonitorRepository monitorRepository, MonitorFactory monitorFactory, PollRepository pollRepository, NoticeRepository noticeRepository) {
        this.monitorRepository = monitorRepository;
        this.monitorFactory = monitorFactory;
        this.pollRepository = pollRepository;
        this.noticeRepository = noticeRepository;
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

                Notice notice = new Notice(
                        poll.getDescription(),
                        winnerProposal.getHashtag(),
                        winnerProposal.getSubject(),
                        poll.getId(),
                        StringUtils.leftPad("" + poll.getStartEventTime().getHour(), 2, "0") + ":" +StringUtils.leftPad("" + poll.getStartEventTime().getMinute(), 2, "0") + "h");
                noticeRepository.publish(notice);
            } else {
                LOG.warning("Finished poll with no winner proposal, consider to delete it");
            }
        }
    }
}
