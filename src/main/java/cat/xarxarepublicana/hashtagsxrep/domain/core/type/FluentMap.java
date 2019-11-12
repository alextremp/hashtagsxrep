package cat.xarxarepublicana.hashtagsxrep.domain.core.type;

import cat.xarxarepublicana.hashtagsxrep.domain.core.validation.NotNull;
import java.util.HashMap;

public class FluentMap<K, V> extends HashMap<K, V> {

  public FluentMap<K, V> fluentPut(K key, V value) {
    super.put(key, value);
    return this;
  }

  public V getNotNull(Object key) {
    V v = super.get(key);
    NotNull.validate(v, "value for: " + key);
    return v;
  }
}
