package com.invict.cash_api.cash_api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cashmoney")
public class CashMoneyApiProperty {

    private String originPermitida = "http://localhost:8000";

    public String getOriginPermitida() {
        return originPermitida;
    }



    public void setOriginPermitida(String originPermitida) {
        this.originPermitida = originPermitida;
    }



    private final Seguranca seguranca= new Seguranca();

    public Seguranca getSeguranca(){
        return seguranca;
    }



    public static class Seguranca {
        
        private boolean enableHttps;
        
        public boolean isEnableHttps(){
            return this.enableHttps;
        }

        public void setEnableHttps(boolean valor){
            this.enableHttps = valor;
        }
        
    }
}
