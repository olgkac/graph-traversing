package com.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class MyStructureTest {

    @Test
    public void shouldCountElementsInBasicTree() {
        //given
        IMyStructure tree = givenBasicTreeStructureWith4Elements();
        //when
        int result = tree.count();
        //then
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void shouldCountElementsAndIgnoreDuplicatedElements() {
        //given
        IMyStructure tree = givenStructureWithDuplicatedElements();
        //when
        int result = tree.count();
        //then
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void shouldCountElementsAndIgnoreCircularGraphs() {
        //given
        IMyStructure tree = givenCircularGraphs();
        //when
        int result = tree.count();
        //then
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void shouldFindElementInBasicTree() {
        //given
        IMyStructure tree = givenBasicTreeStructureWith4Elements();
        //when
        INode result = tree.findByCode("c");
        //then
        assertThat(result.getCode()).isEqualTo("c");
    }

    @Test
    public void shouldReturnNullIfElementNotPresent() {
        //given
        IMyStructure tree = givenBasicTreeStructureWith4Elements();
        //when
        INode result = tree.findByCode("0");
        //then
        assertThat(result).isNull();
    }

    @Test
    public void shouldFindElementInCirsularGraphs() {
        //given
        IMyStructure tree = givenCircularGraphs();
        //when
        INode result = tree.findByCode("c");
        //then
        assertThat(result.getCode()).isEqualTo("c");
    }

    private static IMyStructure givenBasicTreeStructureWith4Elements() {
        List<INode> nodes = Arrays.asList(new TestNode("c", "c"), new TestNode("d", "d"));
        List<INode> rootElements = Arrays.asList(new TestCompositeNode("a", "a", nodes), new TestNode("b", "b"));
        return new MyStructure(rootElements);
    }

    private static IMyStructure givenStructureWithDuplicatedElements() {
        INode basicNode = new TestNode("c", "c");
        List<INode> nodes = Arrays.asList(basicNode, new TestNode("d", "d"));
        List<INode> rootElements = Arrays.asList(new TestCompositeNode("a", "a", nodes), basicNode);
        return new MyStructure(rootElements);
    }

    private static IMyStructure givenCircularGraphs() {
        TestCompositeNode a = new TestCompositeNode("a", "a", new ArrayList<>());
        TestCompositeNode b = new TestCompositeNode("b", "b", new ArrayList<>());
        TestNode c = new TestNode("c", "c");
        a.getNodes().add(b);
        b.getNodes().add(a);
        List list = new ArrayList();
        list.add(a);
        list.add(c);
        return new MyStructure(list);
    }
}