
package org.jboss.as.quickstarts.ws_security.wsclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.jboss.as.quickstarts.ws_security.wsclient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CallSecuredWSMethod_QNAME = new QName("http://jboss.org/as/quickstarts/wssecurity", "callSecuredWSMethod");
    private final static QName _CallSecuredWSMethodResponse_QNAME = new QName("http://jboss.org/as/quickstarts/wssecurity", "callSecuredWSMethodResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.jboss.as.quickstarts.ws_security.wsclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CallSecuredWSMethodResponse }
     * 
     */
    public CallSecuredWSMethodResponse createCallSecuredWSMethodResponse() {
        return new CallSecuredWSMethodResponse();
    }

    /**
     * Create an instance of {@link CallSecuredWSMethod }
     * 
     */
    public CallSecuredWSMethod createCallSecuredWSMethod() {
        return new CallSecuredWSMethod();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CallSecuredWSMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jboss.org/as/quickstarts/wssecurity", name = "callSecuredWSMethod")
    public JAXBElement<CallSecuredWSMethod> createCallSecuredWSMethod(CallSecuredWSMethod value) {
        return new JAXBElement<CallSecuredWSMethod>(_CallSecuredWSMethod_QNAME, CallSecuredWSMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CallSecuredWSMethodResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jboss.org/as/quickstarts/wssecurity", name = "callSecuredWSMethodResponse")
    public JAXBElement<CallSecuredWSMethodResponse> createCallSecuredWSMethodResponse(CallSecuredWSMethodResponse value) {
        return new JAXBElement<CallSecuredWSMethodResponse>(_CallSecuredWSMethodResponse_QNAME, CallSecuredWSMethodResponse.class, null, value);
    }

}
