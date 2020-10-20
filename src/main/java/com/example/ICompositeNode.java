package com.example;

import java.util.List;

interface ICompositeNode extends INode {
    List<INode> getNodes();
}

