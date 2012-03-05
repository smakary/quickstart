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

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.io.PrintWriter;

import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;
import javax.xml.ws.handler.Handler;

//import org.apache.ws.security.saml.SAMLUtil;
import org.jboss.as.quickstarts.ws_security.wsclient.SecuredService;
import org.jboss.as.quickstarts.ws_security.wsclient.SecuredService_Service;

import org.w3c.dom.Element;
import org.picketlink.identity.federation.api.wstrust.*;
import org.picketlink.identity.federation.api.wstrust.WSTrustClient.SecurityInfo;
import org.picketlink.identity.federation.core.wstrust.plugins.saml.SAMLUtil;
import org.picketlink.trust.jbossws.SAML2Constants;
import org.picketlink.trust.jbossws.handler.SAML2Handler;

/**
 * <p>
 * This is the quick start entry point
 * Simple servlet calling secured Web Service using WS trust client
 * Issuing a SAML2 security token and inject the token in the service request 
 * </p>
 * 
 * @author Sherif Makary MW SA
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/CallSecuredWSServlet")
public class CallSecuredWSServlet extends HttpServlet {

   static String PAGE_HEADER = "<html><head /><body>";

   static String PAGE_FOOTER = "</body></html>";
   
  

   public static SecuredService_Service service = new SecuredService_Service();
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  
	  PrintWriter writer = resp.getWriter();
	  String securedInfo = null;
	  //Get Service port
      SecuredService port=service.getSecuredServicePort();
      //Cast to Binding Port to inject the SAML 2 request 
      BindingProvider bp = (BindingProvider)port;
      //Get SAML2 Token for the request user, the user is identified in the domain security section
      Element assertion = getSAML2Assertion("UserA", "usera"); 
      //Inject the SAML2 token
      bp.getRequestContext().put(SAML2Constants.SAML2_ASSERTION_PROPERTY, assertion);
      List<Handler> handlers = bp.getBinding().getHandlerChain();
      handlers.add(new SAML2Handler());
      bp.getBinding().setHandlerChain(handlers); 
             
      //Call the Secured Web Service Method
      securedInfo=port.callSecuredWSMethod();
      writer.println(PAGE_HEADER);
      writer.println("<h1>" + "Successfully called Secured WS Method " + "</h1>");
      writer.println("<p>" + "Response from Secured WS Method : " + securedInfo  + "</p>");
      writer.println(PAGE_FOOTER);
      writer.close();
   }
   
   /**
    * <p>
    * The method will generate a SAML 2 security token
    * By Calling the PicketLink-STS Service
    * </p>
    * 
    * @author Sherif Makary MW SA
    * 
    */
   public Element getSAML2Assertion(String username, String password){
	   // Get a SAML2 Assertion Token from the STS
		   Element assertion = null;
		   try {
			 //Call the STS service  
			 WSTrustClient client = new WSTrustClient("PicketLinkSTS", "PicketLinkSTSPort",
	            "http://localhost:8080/picketlink-sts/PicketLinkSTS",
	            new SecurityInfo(username, password));
	         System.out.println("Invoking token service to get SAML assertion for " + username);
	         assertion = client.issueToken(SAMLUtil.SAML2_TOKEN_TYPE);
	         System.out.println("SAML assertion for " + username + " successfully obtained!");
	      } catch (Exception e) {
		     System.out.println("Failed to get assertion");	    	  
	         e.printStackTrace();
	         
	      } 
	      return assertion;
	   }

}
