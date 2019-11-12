package cat.xarxarepublicana.hashtagsxrep.infrastructure.timer;

import cat.xarxarepublicana.hashtagsxrep.application.poll.FinishPollUseCase;
import java.util.logging.Logger;
import org.springframework.scheduling.annotation.Scheduled;

public class FinishedPollTimer {

  private static final Logger LOG = Logger.getLogger(FinishedPollTimer.class.getName());

  private final FinishPollUseCase finishPollUseCase;

  public FinishedPollTimer(FinishPollUseCase finishPollUseCase) {
    this.finishPollUseCase = finishPollUseCase;
  }

  @Scheduled(fixedDelayString = "${app.finishedPoll.timer.delay}", initialDelay = 60000)
  public void onTime() {
    LOG.info(">> onTime >> Finished Poll Timer :: START");
    finishPollUseCase.createMonitorFromPoll();
    LOG.info(">> onTime >> Finished Poll Timer :: END");
  }
}
