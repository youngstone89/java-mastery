package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Box {
    private int boxId;
    private List<Item> items;

    public Box(int boxId, List<Item> items) {
        this.boxId = boxId;
        this.items = items;
    }

    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

class Item {
    private int id;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private List<Item> items;

    public Item() {
    }

    public Item(int id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

public class RecursiveStreamTest {

    public static void main(String[] args) {

        List<Item> items = new ArrayList();
        Item item1 = new Item();
        item1.setId(1);
        item1.setItems(List.of(new Item(11, null)));
        items.add(item1);

        Item item2 = new Item();
        item2.setId(2);
        item2.setItems(List.of(new Item(22, null)));
        items.add(item2);

        List<Box> boxes = new ArrayList<>();
        boxes.add(new Box(11111, items));

        boxes.stream().map(box -> setItemsPrice(box)).collect(Collectors.toList());

        Map<Integer, Item> map = boxes.stream()
                .flatMap(box -> box.getItems().stream())
                .collect(Collectors.toMap(Item::getId, item -> item));

        Map<Integer, Item> map2 = boxes.stream()
                .flatMap(box -> box.getItems().stream()) // Flatten top-level items
                .flatMap(item -> Stream.concat( // Flatten nested items
                        Stream.of(item),
                        item.getItems() == null ? Stream.empty() : item.getItems().stream()))
                .collect(Collectors.toMap(Item::getId, item -> item));
        System.out.println(map);
        System.out.println(map2);
    }

    private static Box setItemsPrice(Box box) {
        if (box.getItems() == null) {
            return box;
        }
        for (Item item : box.getItems()) {
            setPrice(item);
        }
        return box;
    }

    private static Item setPrice(Item item) {
        item.setPrice(10000);
        if (item.getItems() == null) {
            return item;
        }

        for (Item tem : item.getItems()) {
            setPrice(tem);
        }
        return item;

    }
}
