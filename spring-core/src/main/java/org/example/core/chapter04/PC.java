package org.example.core.chapter04;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PC {
    @Value("MagicBook")
    public String model;
}
