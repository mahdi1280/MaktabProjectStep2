package ir.maktab.maktabprojectstep2.idpay.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentInterceptor {

    private final String apiKey;
    private final String sandbox;

    public PaymentInterceptor(@Value("${app.idPay.merchant}") String apiKey, @Value("${app.idPay.api.sandbox}") String sandbox) {
        this.apiKey = apiKey;
        this.sandbox = sandbox;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            template.header("X-API-KEY", apiKey);
            template.header("X-SANDBOX", sandbox);
        };
    }
}
