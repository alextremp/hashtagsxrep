package cat.xarxarepublicana.hashtagsxrep.domain.report;

public class InteractorResume {

    private final Integer interactorCount;
    private final Integer interactedCount;

    public InteractorResume(Integer interactorCount, Integer interactedCount) {
        this.interactorCount = interactorCount;
        this.interactedCount = interactedCount;
    }

    public Integer getInteractorCount() {
        return interactorCount;
    }

    public Integer getInteractedCount() {
        return interactedCount;
    }
}
