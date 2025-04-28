package com.jsamkt.learn.productcatalogspringboot.config;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.registry.otlp.OtlpConfig;
import io.micrometer.registry.otlp.OtlpMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public MeterFilter renameAllMetrics() {
        return new MeterFilter() {
            @Override
            public Meter.Id map(Meter.Id id) {
                return id.withName(id.getName().replace(".", "_"));
            }
        };
    }

//    @Bean
//    public OtlpMeterRegistry otlpMeterRegistry() {
//        return new OtlpMeterRegistry(
//                OtlpConfig,
//                Clock.SYSTEM
//        );
//    }

}
