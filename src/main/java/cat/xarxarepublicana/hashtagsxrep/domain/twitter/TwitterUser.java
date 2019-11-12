package cat.xarxarepublicana.hashtagsxrep.domain.twitter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.text.StringEscapeUtils;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id_str",
    scope = TwitterUser.class,
    resolver = RepeatedObjectIdResolver.class
)
public class TwitterUser {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE MMM dd HH:mm:ss Z yyyy", locale = "en")
  private Date createdAt;
  private String idStr;
  private String screenName;
  private String name;
  private Integer followersCount;
  private Integer friendsCount;
  private String lang;
  private String location;
  private String profileImageUrlHttps;
  private boolean verified;
  @JsonProperty("protected")
  private boolean protectedx;

  public void setLocation(String location) {
    this.location = StringEscapeUtils.escapeJava(location);
  }

  public void setName(String name) {
    this.name = StringEscapeUtils.escapeJava(name);
  }
}
