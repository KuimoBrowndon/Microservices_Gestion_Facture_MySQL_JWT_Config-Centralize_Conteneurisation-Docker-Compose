package org.ms.customerservice.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "configs-customer")
public class ApplicationPropertiesConfiguration
{
    private String SECRET;
    private String AUTH_HEADER;
    private String AUTH_HEADER_PREFIX;

    public String getSECRET() {return SECRET;}
    public void setSECRET(String SECRET) {this.SECRET=SECRET;}

    public String getAUTH_HEADER(){return AUTH_HEADER;}
    public void setAUTH_HEADER(String AUTH_HEADER){this.AUTH_HEADER=AUTH_HEADER;}

    public String getAUTH_HEADER_PREFIX(){return AUTH_HEADER_PREFIX+" ";}
    public void setAUTH_HEADER_PREFIX(String AUTH_HEADER_PREFIX){this.AUTH_HEADER_PREFIX=AUTH_HEADER_PREFIX;}
}