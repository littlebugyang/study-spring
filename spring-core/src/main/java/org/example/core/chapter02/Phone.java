package org.example.core.chapter02;

public class Phone {
    public String model;

    private Phone() {
    }

    // 配置 Bean 的 property 时，该类必须有对应 name 的 set 方法。
    public void setModel(String model) {
        this.model = model;
    }
}
