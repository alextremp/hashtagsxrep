package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper;

import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtraction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TwitterExtractionMapper {
    void insert(TwitterExtraction twitterExtraction);
}
