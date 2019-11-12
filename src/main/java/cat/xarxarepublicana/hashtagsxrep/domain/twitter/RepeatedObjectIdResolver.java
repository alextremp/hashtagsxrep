package cat.xarxarepublicana.hashtagsxrep.domain.twitter;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import java.util.HashMap;
import java.util.Map;

public class RepeatedObjectIdResolver implements ObjectIdResolver {
  protected Map<ObjectIdGenerator.IdKey, Object> items;

  public RepeatedObjectIdResolver() {
    this.items = new HashMap<>();
  }

  public void bindItem(ObjectIdGenerator.IdKey id, Object ob) {
    if (!this.items.containsKey(id)) {
      this.items.put(id, ob);
    }
  }

  public Object resolveId(ObjectIdGenerator.IdKey id) {
    return this.items.get(id);
  }

  public boolean canUseFor(ObjectIdResolver resolverType) {
    return resolverType.getClass() == this.getClass();
  }

  public ObjectIdResolver newForDeserialization(Object context) {
    return new RepeatedObjectIdResolver();
  }
}

