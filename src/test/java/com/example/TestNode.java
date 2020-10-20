package com.example;

public class TestNode implements INode {
    private final String code;
    private final String renderer;

    public TestNode(String code, String renderer) {
        this.code = code;
        this.renderer = renderer;
    }

    public String getCode() {
        return code;
    }

    public String getRenderer() {
        return renderer;
    }
}
