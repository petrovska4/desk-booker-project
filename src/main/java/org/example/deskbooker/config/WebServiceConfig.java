package org.example.deskbooker.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
@ComponentScan(basePackages = "org.example.deskbooker")
public class WebServiceConfig {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(org.springframework.context.ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean
    public XsdSchema officeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema/office.xsd"));
    }

    @Bean
    public XsdSchema deskSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema/desk.xsd"));
    }

    @Bean(name = "officeServiceWsdl")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema officeSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("OfficeServicePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.example.org/deskbooker");
        wsdl11Definition.setSchema(officeSchema);
        return wsdl11Definition;
    }

    @Bean(name = "deskServiceWsdl")
    public DefaultWsdl11Definition deskWsdlDefinition(XsdSchema deskSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("DeskServicePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.example.org/deskbooker");
        wsdl11Definition.setSchema(deskSchema);
        return wsdl11Definition;
    }

}
