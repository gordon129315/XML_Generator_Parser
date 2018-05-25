package yzr;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XML {
    //fromXml(node: XMLNode) -> String
    //node 视为根结点，根据结点的信息 输出 整个 xml 作为 String
    public String fromXml(XMLNode node) {
        String result = "";
        result += node.showNameAndAttributes();

        for (XMLNode subNode : node.getSubNodes()) {
            result += fromXml(subNode);
        }

        result += "</" + node.getName() + ">";

        return result;
    }


    public String fromXmlWithPrettyPrinting(XMLNode node) {

        return recursion(node, 0);
    }

    private String recursion(XMLNode node, int times) {
        String result = "";
        result += node.showNameAndAttributes();

        if (node.containsSubNodes()) {
            result += "\n";
        }

        for (XMLNode subNode : node.getSubNodes()) {
            result += tabTimes(times + 1);
            result += recursion(subNode,times + 1);
        }

        result += "</" + node.getName() + ">\n" + tabTimes(times - 1);

        return result;
    }

    private String tabTimes(int times) {
        String str = "";
        for (int i = 0; i < times; i++) {
            str += "\t";
        }
        return str;
    }


    //toXml(string: String) -> XMLNode
    public XMLNode toXml(String string) {

        int level = 0;
        Map<Integer, XMLNode> map = new HashMap<>();

        // 创建 Pattern 对象
        Pattern r = Pattern.compile("</?(\\w+).*?>([^<\\n]+)*");

        // 现在创建 matcher 对象
        Matcher m = r.matcher(string);

        while (m.find( )) {
            String element = m.group();
            if (element.startsWith("</")) {
                level --;
            }
            else {
                String nodeName = m.group(1);
                String text = m.group(2);
                findAttributeAndValue(element, nodeName, text, map, level);
                level ++;
            }

        }


        return map.get(0);
    }

    private void findAttributeAndValue(String element, String nodeName, String text, Map<Integer, XMLNode> map, int level) {
        XMLNode node = new XMLNode(nodeName);

        // 创建 Pattern 对象
        Pattern r = Pattern.compile("(\\w+)\\s*=\\s*\"([^\"]+)\"");

        // 现在创建 matcher 对象
        Matcher m = r.matcher(element);

        while (m.find( )) {
            String attributeName = m.group(1);
            String value = m.group(2);
            node.setAttribute(attributeName, value);
        }
        node.setText(text);

        map.put(level, node);
        if (level > 0) {
            map.get(level - 1).addNode(node);
        }

    }


}
