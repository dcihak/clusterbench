/*
 * Copyright 2013 Radoslav Husár
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.test.clusterbench.ejb.stateful;

import org.jboss.test.clusterbench.common.ejb.CommonStatefulSBImpl;

import javax.ejb.EJBException;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Radoslav Husar
 * @version Dec 2011
 */
@Stateful
public class RemoteStatefulSBImpl extends CommonStatefulSBImpl implements RemoteStatefulSB, SessionSynchronization {

    private static final Logger LOGGER = Logger.getLogger(RemoteStatefulSBImpl.class.getName());

    @Override
    public void afterBegin() throws EJBException, RemoteException {

    }

    @Override
    public void beforeCompletion() throws EJBException, RemoteException {

    }

    /**
     * Methods getSerial and getSerialAndIncrement do not perform transactional operation so we need to check if transactions on this EJB were successfully committed.
     *
     * @param committed
     * @throws EJBException
     * @throws RemoteException
     */
    @Override
    public void afterCompletion(boolean committed) throws EJBException, RemoteException {
        if (committed) {
            LOGGER.log(Level.INFO, "Transaction committed successfully.");
        } else {
            LOGGER.log(Level.SEVERE, "Transaction of the stateful bean RemoteStatefulSBImpl was not successfully committed!");
        }
    }
}
