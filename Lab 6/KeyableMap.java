import java.util.Optional;
import java.util.Map;

class KeyableMap<V extends Keyable> implements Keyable {

    private final String key;
    private final ImmutableMap<String, V> map;

    KeyableMap(String key) {
        this.key = key;
        this.map = new ImmutableMap<String, V>();
    }

    KeyableMap(String key, ImmutableMap<String, V> map) {
        this.key = key;
        this.map = map;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    public ImmutableMap<String, V> getMap() {
        return this.map;
    }

    public KeyableMap<V> put(V item) {
        return new KeyableMap<V>(this.key, this.map.put(item.getKey(), item));
    }

    public Optional<V> get(String key) {
        return this.map.get(key);
    }

    @Override
    public String toString() {
        String result = this.key + ": {";
        boolean first = true;
        for (Map.Entry<String, V> e : this.map) {
            if (!first) {
                result += ", " + e.getValue();
            } else {
                result += e.getValue();
                first = false;
            }
        }
        result += "}";
        return result;
    }
}
