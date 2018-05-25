package yzr;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class XMLNode {
    private String name;
    private String text;
    private List<XMLNode> subNodes = new LinkedList<>();
    private Map<String, String> attributes = new HashMap<>();

    public XMLNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<XMLNode> getSubNodes() {
        return subNodes;
    }

    //addNode(node: XMLNode)
    public void addNode(XMLNode node) {
        subNodes.add(node);
    }

    //getNode(index: int) -> XMLNode    返回第 index 个子结点
    public XMLNode getNode(int index) {
        return subNodes.get(index);
    }

    //containsSubNodes() -> boolean     是否含有子结点
    public boolean containsSubNodes() {
        return subNodes.size() != 0;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    //setAttribute(attributeName: String, value: String)    为该结点设置属性
    public void setAttribute(String attributeName, String value) {
        attributes.put(attributeName, value);
    }

    //getAttribute(attributeName: String) -> String        返回该节点属性名是 attributeName 的值
    public String getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }


    public String showNameAndAttributes() {
        String result = "";
        result += "<" + getName();
        if (!attributes.isEmpty()) {
            for (Map.Entry<String, String> entry : attributes.entrySet()) {
                result += " " + entry.getKey() + "=\"" + entry.getValue() + "\"";
            }
        }
        result += ">";

        if (!containsSubNodes() && getText() != null && !getText().equals("")) {
            result += getText();
        }

        return result;
    }
}
