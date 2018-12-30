package cat.xarxarepublicana.hashtagsxrep.infrastructure.timer;

import cat.xarxarepublicana.hashtagsxrep.application.poll.CreateMonitorFromPollUseCase;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.logging.Logger;

public class FinishedPollTimer {

    private static final Logger LOG = Logger.getLogger(FinishedPollTimer.class.getName());

    private final CreateMonitorFromPollUseCase createMonitorFromPollUseCase;

    public FinishedPollTimer(CreateMonitorFromPollUseCase createMonitorFromPollUseCase) {
        this.createMonitorFromPollUseCase = createMonitorFromPollUseCase;
    }

    @Scheduled(fixedDelayString = "${app.finishedPoll.timer.delay}")
    public void onTime() {
        LOG.info(">> onTime >> Finished Poll Timer :: START");
        createMonitorFromPollUseCase.createMonitorFromPoll();
        LOG.info(">> onTime >> Finished Poll Timer :: END");
    }
}
