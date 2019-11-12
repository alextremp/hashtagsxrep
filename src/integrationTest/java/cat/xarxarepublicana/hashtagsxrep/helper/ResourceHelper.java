package cat.xarxarepublicana.hashtagsxrep.helper;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ResourceHelper {

  public String readResource(Resource resource) {
    try {
      return IOUtils.toString(resource.getURI());
    } catch (IOException e) {
      throw new RuntimeException("Error reading resource", e);
    }
  }
}
