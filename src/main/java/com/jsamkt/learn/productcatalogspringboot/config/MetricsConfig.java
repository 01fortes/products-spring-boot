package com.jsamkt.learn.productcatalogspringboot.config;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.NamingConvention;
import io.micrometer.registry.otlp.OtlpConfig;
import io.micrometer.registry.otlp.OtlpMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public MeterRegistry meterRegistry(OtlpConfig otlpConfig) {
        var result = new OtlpMeterRegistry(otlpConfig, Clock.SYSTEM);
        result.config().namingConvention(NamingConvention.snakeCase);
        return result;
    }

}
