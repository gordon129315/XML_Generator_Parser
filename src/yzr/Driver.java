package yzr;


public class Driver {
    public static void main(String[] args) {
        XMLNode point = new XMLNode("point");
        point.setAttribute("id", "100");
        point.setAttribute("id2", "200");

        XMLNode x = new XMLNode("x");
        x.setAttribute("id", "50");
        point.addNode(x);

        XMLNode z = new XMLNode("z");
        z.setAttribute("id", "25");
        z.setText("12345");
        x.addNode(z);

        XMLNode y = new XMLNode("y");
        y.setText("4");
        point.addNode(y);

        XML xml = new XML();
        System.out.println(xml.fromXml(point));
        String xmlContent = xml.fromXmlWithPrettyPrinting(point);
        System.out.println(xmlContent);
        System.out.println("---------------------");

        XMLNode xmlNode = xml.toXml(xmlContent);
        System.out.println();
        System.out.println(xml.fromXml(xmlNode));
        System.out.println(xml.fromXmlWithPrettyPrinting(xmlNode));
    }
}
