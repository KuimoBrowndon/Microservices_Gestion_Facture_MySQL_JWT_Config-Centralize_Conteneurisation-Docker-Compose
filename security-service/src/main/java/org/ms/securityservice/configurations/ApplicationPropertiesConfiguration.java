package org.ms.securityservice.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "configs-security")
public class ApplicationPropertiesConfiguration
{
    private String SECRET;
    private String AUTH_HEADER;
    private String AUTH_HEADER_PREFIX;
    private long EXPIRE_ACCESS_TOKEN;
    private long EXPIRE_ACCESS_REFRESH_TOKEN;
    private String REFRESH_TOKEN_LINK;

    public String getSECRET() {return SECRET;}
    public void setSECRET(String SECRET) {this.SECRET=SECRET;}

    public String getAUTH_HEADER(){return AUTH_HEADER;}
    public void setAUTH_HEADER(String AUTH_HEADER){this.AUTH_HEADER=AUTH_HEADER;}

    public String getAUTH_HEADER_PREFIX(){return AUTH_HEADER_PREFIX+" ";}
    public void setAUTH_HEADER_PREFIX(String AUTH_HEADER_PREFIX){this.AUTH_HEADER_PREFIX=AUTH_HEADER_PREFIX;}

    public long getEXPIRE_ACCESS_TOKEN(){return EXPIRE_ACCESS_TOKEN;}
    public void setEXPIRE_ACCESS_TOKEN(long EXPIRE_ACCESS_TOKEN){this.EXPIRE_ACCESS_TOKEN=EXPIRE_ACCESS_TOKEN;}

    public long getEXPIRE_ACCESS_REFRESH_TOKEN(){return EXPIRE_ACCESS_REFRESH_TOKEN;}
    public void setEXPIRE_ACCESS_REFRESH_TOKEN(long EXPIRE_ACCESS_REFRESH_TOKEN){this.EXPIRE_ACCESS_REFRESH_TOKEN=EXPIRE_ACCESS_REFRESH_TOKEN;}

    public String getREFRESH_TOKEN_LINK(){return REFRESH_TOKEN_LINK;}
    public void setREFRESH_TOKEN_LINK(String REFRESH_TOKEN_LINK){this.REFRESH_TOKEN_LINK=REFRESH_TOKEN_LINK;}
}