package lt.lb.jndi;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author laim0nas100
 */
public class ResourceMap implements Map<String, Object> {

    public ResourceMap() {
    }

    protected String type;
    protected String auth;
    protected String name;
    protected String factory;
    protected String scope;
    protected String singleton;
    protected Map<String, Object> map = new ConcurrentHashMap<>();

    public String getType() {
        return type;
    }

    public String getAuth() {
        return auth;
    }

    public String getName() {
        return name;
    }

    public String getFactory() {
        return factory;
    }

    public String getScope() {
        return scope;
    }

    public String getSingleton() {
        return singleton;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public Optional getObj(String name) {
        return Optional.ofNullable(getMap().getOrDefault(name, null));
    }

    public Optional<String> getStr(String name) {
        return getObj(name).map(m -> String.valueOf(m));
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return map.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Object> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return map.entrySet();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.type);
        hash = 83 * hash + Objects.hashCode(this.auth);
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.factory);
        hash = 83 * hash + Objects.hashCode(this.scope);
        hash = 83 * hash + Objects.hashCode(this.singleton);
        hash = 83 * hash + Objects.hashCode(this.map);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResourceMap other = (ResourceMap) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.auth, other.auth)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.factory, other.factory)) {
            return false;
        }
        if (!Objects.equals(this.scope, other.scope)) {
            return false;
        }
        if (!Objects.equals(this.singleton, other.singleton)) {
            return false;
        }
        if (!Objects.equals(this.map, other.map)) {
            return false;
        }
        return true;
    }

}
