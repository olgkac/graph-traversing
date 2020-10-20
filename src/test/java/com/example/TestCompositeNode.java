package com.example;

import java.util.List;

public class TestCompositeNode extends TestNode implements ICompositeNode {
    private final List<INode> nodes;

    public TestCompositeNode(String code, String renderer, List<INode> nodes) {
        super(code, renderer);
        this.nodes = nodes;
    }

    @Override
    public List<INode> getNodes() {
        return nodes;
    }
}
