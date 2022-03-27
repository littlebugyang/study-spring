package org.example.core.chapter04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpecificConfig {
    @Bean
    public PC macBook() {
        PC pc = new PC();
        pc.model = "MackBook";
        return pc;
    }

    @Bean
    public Tablet matePad() {
        Tablet tablet = new Tablet();
        tablet.model = "MatePad";
        return tablet;
    }

    @Bean("iPad")
    public Tablet notIPad() {
        return new Tablet();
    }
}
