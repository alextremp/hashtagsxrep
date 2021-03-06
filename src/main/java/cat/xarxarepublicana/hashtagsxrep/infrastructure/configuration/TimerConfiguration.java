package cat.xarxarepublicana.hashtagsxrep.infrastructure.configuration;

import cat.xarxarepublicana.hashtagsxrep.application.monitor.MonitorDataExtractionUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.FinishPollUseCase;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.timer.FinishedPollTimer;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.timer.MonitorTimer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class TimerConfiguration {

  @Bean
  public MonitorTimer monitorTimer(MonitorDataExtractionUseCase monitorDataExtractionUseCase) {
    return new MonitorTimer(monitorDataExtractionUseCase);
  }

  @Bean
  public FinishedPollTimer finishedPollTimer(FinishPollUseCase finishPollUseCase) {
    return new FinishedPollTimer(finishPollUseCase);
  }
}
