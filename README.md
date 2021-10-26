# JNDIProxy
JNDI simple String map resource and JNDI resource proxy, when you want a multiple JNDI properties to use the same resource, but in a different name, so just define a JNDI proxy with a name and a realName property to redirect to actual resource.

Tomcat config example how to use JndiProxy (need to be in a classpath):
```
<Resource 
    name="jdbc/productionPool" 
    realName="jdbc/devPool"
    type="javax.sql.DataSource"
    factory="lt.lb.jndi.JndiProxyFactory" 
/>
```
