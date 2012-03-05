/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and/or its affiliates,
 * and individual contributors as indicated by the @author tags.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 *
 * (C) 2012,
 * @author Sherif Makary Red Hat MW SA.*/

package org.jboss.as.quickstarts.ws_security;



import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;



/**
 * <p>
 * This is a simple Pojo and used for the web service implementation, 
 * And to generate the WS Client
 * The service is secured using a service policy defined in /WEB-INF/jboss-wsse.xml
 * </p>
 * 
 * @author Sherif Makary MW SA
 * 
 */



@WebService(name="SecuredService", serviceName="SecuredService", targetNamespace="http://jboss.org/as/quickstarts/wssecurity")
@HandlerChain(file="/jaxws-chain-handler-config.xml")
public class SecuredWSI {

	
	/**
	 * <p>
	 *  
	 * The method is secured using a service policy defined in /WEB-INF/jboss-wsse.xml
	 * </p>
	 * 
	 * @author Sherif Makary MW SA
	 * 
	 */

	
	@WebMethod
	public String callSecuredWSMethod(){
		
		return " Successfully called secured callSecuredWSMethod ";
	}
	
}
