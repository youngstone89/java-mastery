package com.example.stream;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * NullFieldGroupByTest
 */
class ChildItem {

    private Integer id;

    public ChildItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}

class OriginalItem {
    private Integer originalId;
    private List<ChildItem> items;

    public OriginalItem(Integer originalId, List<ChildItem> items) {
        this.originalId = originalId;
        this.items = items;
    }

    public Integer getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Integer originalId) {
        this.originalId = originalId;
    }

    public List<ChildItem> getItems() {
        return items;
    }

    public void setItems(List<ChildItem> items) {
        this.items = items;
    }

}

public class NullFieldGroupByTest {
    private String name;
    private Integer id;
    private Integer parentId;

    public NullFieldGroupByTest(String name, Integer id, Integer parentId) {
        this.name = name;
        this.id = id;
        this.parentId = parentId;
    }

    static Integer NULL_FILLER = -1;

    public static void main(String[] args) {
        NullFieldGroupByTest one = new NullFieldGroupByTest("k", 1, null);
        NullFieldGroupByTest two = new NullFieldGroupByTest("y", 2, 1);
        NullFieldGroupByTest th = new NullFieldGroupByTest("y", 3, 1);
        NullFieldGroupByTest one2 = new NullFieldGroupByTest("k2", 4, null);
        NullFieldGroupByTest two2 = new NullFieldGroupByTest("y2", 5, 4);
        NullFieldGroupByTest th2 = new NullFieldGroupByTest("y2", 6, 4);
        NullFieldGroupByTest one3 = new NullFieldGroupByTest("k2", 7, null);
        NullFieldGroupByTest two3 = new NullFieldGroupByTest("y2", 8, 7);

        List<NullFieldGroupByTest> items = List.of(one, two, th, one2, two2, th2, one3, two3);

        // get map of <parentId, List<object>> to get child list
        Map<Integer, List<NullFieldGroupByTest>> itemsByParent = items.stream()
                .map(fillGroupingKey())
                .collect(Collectors.groupingBy(test -> test.getParentId()));

        // get map of <parentId, entity> to get parent item list
        Map<Integer, NullFieldGroupByTest> parentItemsById = itemsByParent.get(NULL_FILLER).stream()
                .collect(Collectors.toMap(NullFieldGroupByTest::getId, Function.identity()));

        List<OriginalItem> originalItems = parentItemsById.entrySet().stream()
                .map(entry -> generateOriginalItem(itemsByParent, entry)).collect(Collectors.toList());

        for (OriginalItem item : originalItems) {
            System.out.println("parentId:" + item.getOriginalId() + "\t items id="
                    + item.getItems().stream().map(child -> child.getId().toString()).collect(Collectors.joining(",")));
        }
    }

    private static OriginalItem generateOriginalItem(Map<Integer, List<NullFieldGroupByTest>> itemsByParent,
            Entry<Integer, NullFieldGroupByTest> entry) {
        Integer parentId = entry.getKey();
        List<ChildItem> childItems = getChildItems(itemsByParent, parentId);
        return getOriginalItem(parentId, childItems);
    }

    private static OriginalItem getOriginalItem(Integer parentId, List<ChildItem> childItems) {
        OriginalItem originalItem = new OriginalItem(parentId, childItems);
        return originalItem;
    }

    private static List<ChildItem> getChildItems(
            Map<Integer, List<NullFieldGroupByTest>> itemsByParent,
            Integer parentId) {
        List<ChildItem> childItems = itemsByParent.get(parentId).stream().map(item -> new ChildItem(item.getId()))
                .collect(Collectors.toList());

        return childItems;
    }

    private static Function<? super NullFieldGroupByTest, ? extends NullFieldGroupByTest> fillGroupingKey() {
        return test -> {
            if (test.getParentId() == null) {
                test.setParentId(NULL_FILLER);
            }
            return test;
        };
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}