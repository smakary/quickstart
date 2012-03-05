ws-security:  Using JEE Declarative Security to Control Access Web Service using SAML@ and Trust
====================
Author: Sherif Makary, RH MW SA

This example demonstrates the use of JEE declarative security to control access to Web Services using SAML2 token, Trust and Security in JBoss AS7 and JBoss Enterprise Application Platform 6.

The example can be deployed using Maven from the command line or from Eclipse using JBoss Tools.

To implement web service security using SMAL2 token trust, you need to:

1. Use chain handler to inform the container that there're security handlers need to be called for this  web service, the chain handler annotation is, `@HandlerChain(file="/jaxws-chain-handler-config.xml")`
The path to the chain handler file is, /webapp/WEB-INF/classes/jaxws-chain-handler-config.xml, please refer to the config file for the details
2. Implement a security policy to determine access control to the web service methods, by adding /webapp/WEB-INF/jboss-wsse.xml, please refer to the config file for the details
3. Add a security-domain to your jboss-web.xml, please refer to the /webapp/WEB-INF/jboss-web.xml for the security domain xml, this is will point the authentication class to the login module  
4. Configure a security domain in standalone.xml
5. Make sure the security domain referenced in the jboss-web.xml is defined in the JBoss AS standalone.xml configuration file. Find the `<subsystem xmlns="urn:jboss:domain:security:1.1">` and copy the following XML snippet into the `<security-domains>` section:

                <security-domain name="SecuredWSService" cache-type="required">
                    <authentication>
                        <login-module code="org.picketlink.identity.federation.bindings.jboss.auth.SAML2STSLoginModule" flag="required">
                            <module-option name="configFile" value="sts-config.properties"/>
                            <module-option name="password-stacking" value="useFirstPass"/>
                        </login-module>
                    </authentication>
                </security-domain>
6. Add STS service configuration file sts-config.properties, for the security handler to get access to the STS service to verify the SAML2 assertion, please refer to /webapp/WEB-INF/classes/sts-config.properties for the config details
7. Deploy the picketlink-sts.war service war to the standalone server profile -deployment folder-
8. Inspect the picketlink-sts.war file and locate the service security domain identified in the WEB-INF/jboss-web.xml and determine the user and roles properties files identified in that domain by locating the info inside standalone.xml per step 5
9. Add user name `UserA` and password `usera` to the user properties file and `UserA` to the STS Service role in the roles properties files identified in step 8


The following is the sequence of events in the quick start flow:

1. The entry point for the quick start is the CallSecuredWSServlet Servlet
2. The container will take notice of the WS security constraints through the chain handler annotation
3. When the CallSecuredWSServlet is called it will send a request to the PicketLinkSTS service to issue a SAML2 token representing the requester
4. Then the Servlet will call the secured WS method using the SAML2 token
5. When the container receive the request it will call the security handler which will verify the SAML2 token against the PicketLink STS service
6. The container will use the identity in embedded in the SAML2 token to process the access policy for the secured WS method
7. If the principal is a member of the allowed role, the requester will gain access to the secured method


For more information, refer to the  <a href="https://docs.jboss.org/author/display/AS71/Getting+Started+Developing+Applications+Guide" title="Getting Started Developing Applications Guide">Getting Started Developing Applications Guide</a> and find Security --> Security.


## Deploying the Quickstart

First you need to start JBoss AS 7 (or JBoss Enterprise Application Platform 6). To do this, run

    $JBOSS_HOME/bin/standalone.sh

or if you are using Windows

    $JBOSS_HOME/bin/standalone.bat

To deploy the application, you first need to produce the archive:

    mvn clean package


You can now deploy the artifact to JBoss AS by executing the following command:

                mvn jboss-as:deploy

This will deploy `target/jboss-as-ws-security` to the running instance of JBoss AS.

## Testing the Quickstart

The application will be running at the following URL <http://localhost:8080/jboss-as-ws-security/CallSecuredWSServlet/>.
The browser will display the following security info:

                Successfully called Secured WS Method
		Response from Secured WS Method : Successfully called secured callSecuredWSMethod

Change the role in the quickstart /webapp/WEB-INF/jboss-wsse.xml files to 'gooduser1'. 
Rebuild the application using by typing the following command:


                mvn clean package

Re-deploy the application by typing:

                mvn jboss-as:deploy

Refresh the browser, clear the active login, and you should get a security exception similar to the following: 

                HTTP Status 403 - Access to the requested resource has been denied

                type Status report
                message Access to the requested resource has been denied
                description Access to the specified resource (Access to the requested resource has been denied) has been forbidden.
