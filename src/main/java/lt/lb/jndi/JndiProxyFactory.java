package lt.lb.jndi;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

/**
 *
 * @author laim0nas100
 */
public class JndiProxyFactory implements ObjectFactory {

    @Override
    public Object getObjectInstance(Object obj, Name name, Context cntxt, Hashtable<?, ?> hshtbl) throws Exception {
        if (obj == null || !(obj instanceof Reference)) {
            return null;
        }
        Reference ref = (Reference) obj;
        RefAddr refAddr = ref.get("realName");
        if (refAddr == null) {
            throw new IllegalArgumentException("Expected property: 'realName' alongside with proxied jndi resource");
        }
        String realResource = refAddr.getContent().toString();

        List<String> failedNames = new ArrayList<>();
        //try local
        try {
            return tryWithPrefix(failedNames, realResource, "", cntxt);
        } catch (NamingException naming) {
        }
        InitialContext initialContext = new InitialContext(hshtbl);
        try {
            return tryWithPrefix(failedNames, realResource, "java:comp/env/", initialContext);
        } catch (NamingException ex) {
        }

        try {
            return tryWithPrefix(failedNames, realResource, "java:", initialContext);
        } catch (NamingException ex) {
        }

        throw new NamingException("Failed to resolve proxy resource using names:" + failedNames);
    }

    private Object tryWithPrefix(List<String> names, String name, String prefix, Context ctx) throws NamingException {
        if (!name.startsWith(prefix)) {
            name = prefix + name;
        }
        names.add(name);
        return ctx.lookup(name);
    }

}
