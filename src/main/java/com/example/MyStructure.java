package com.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class MyStructure implements IMyStructure {


    private final List<INode> nodes;

    public MyStructure(List<INode> nodes) {
        this.nodes = nodes;
    }

    @Override
    public int count() {
        Set<INode> visitedNodes = new HashSet<>();
        for (INode node : nodes) {
            traversNode(node, visitedNodes);
        }
        return visitedNodes.size();
    }


    @Override
    public INode findByCode(String code) {
        return searchRootNodes(iNode -> code.equals(iNode.getCode()));
    }
    
    @Override
    public INode findByRenderer(String renderer) {
        return searchRootNodes(iNode -> renderer.equals(iNode.getRenderer()));
    }

    private void traversNode(INode node, Set<INode> visitedNodes) {
        if (visitedNodes.contains(node)) {
            return;
        }
        visitedNodes.add(node);
        if (node instanceof ICompositeNode) {
            ICompositeNode compositeNode = (ICompositeNode) node;
            for (INode innerNode : compositeNode.getNodes()) {
                traversNode(innerNode, visitedNodes);
            }
        }
    }
    
    private INode searchRootNodes(Predicate<INode> condition) {
        Set<INode> visitedNodes = new HashSet<>();
        for (INode node : nodes) {
            if (!visitedNodes.contains(node)) {
                visitedNodes.add(node);
                INode singleRootNodeSearchResult = searchNode(condition, node, visitedNodes);
                if (singleRootNodeSearchResult != null) {
                    return singleRootNodeSearchResult;
                }
            }
        }
        return null;
    }

    private INode searchNode(Predicate<INode> nodePredicate, INode node, Set<INode> visitedNodes) {
        if (nodePredicate.test(node)) {
            return node;
        }
        if (node instanceof ICompositeNode) {
            ICompositeNode compositeNode = (ICompositeNode) node;
            for (INode innerNode : compositeNode.getNodes()) {
                if (visitedNodes.contains(innerNode)) {
                    break;
                }
                visitedNodes.add(innerNode);
                INode resultOfInnerSearch = searchNode(nodePredicate, innerNode, visitedNodes);
                if (resultOfInnerSearch != null) {
                    return resultOfInnerSearch;
                }
            }
        }
        return null;
    }
}
