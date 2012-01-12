package org.jboss.test.clusterbench.ejb.singleton;

import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Singleton;
import org.jboss.test.clusterbench.common.SerialBean;
// import org.jboss.ejb3.annotation.Clustered;

/**
 * Scope interface javax.enterprise.context.SessionScoped is not allowed on singleton enterprise beans. Only @Dependent is
 * allowed on singleton enterprise beans.
 *
 * @author Radoslav Husar
 * @version Dec 2011
 */
@Singleton
@LocalBean
// @Clustered -- JBAS014549: @Clustered annotation is currently not supported for singleton EJB.
public class LocalSingletonSB {

    private SerialBean bean;

    public LocalSingletonSB() {
        bean = new SerialBean();
    }

    public int getSerialAndIncrement() {
        int serial = bean.getSerial();

        bean.setSerial(serial + 1);

        return serial;
    }

    @Remove
    private void destroy() {
        bean = null;
    }
}
