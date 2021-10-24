package lt.lb.jndi;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

/**
 *
 * @author laim0nas100
 */
public class ResourceMapFactory implements ObjectFactory {

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {

        if (obj == null || !(obj instanceof Reference)) {
            return null;
        }
        ResourceMap result = new ResourceMap();
        Reference ref = (Reference) obj;

        Enumeration<RefAddr> addrs = ref.getAll();
        while (addrs.hasMoreElements()) {
            RefAddr addr = addrs.nextElement();

            final String type = addr.getType();
            Object content = addr.getContent();
            switch (type) {
                case "type": {
                    result.type = String.valueOf(content);
                    break;
                }
                case "auth": {
                    result.auth = String.valueOf(content);
                    break;
                }
                case "name": {
                    result.name = String.valueOf(content);
                    break;
                }
                case "factory": {
                    result.factory = String.valueOf(content);
                    break;
                }
                case "scope": {
                    result.scope = String.valueOf(content);
                    break;
                }
                case "singleton": {
                    result.singleton = String.valueOf(content);
                    break;
                }
                default: {
                    result.map.put(type, content);
                }
            }
        }
        return result;
    }
}
