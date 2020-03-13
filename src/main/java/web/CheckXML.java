package web;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class CheckXML {
    private static final String error = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Error><ErrorCode>INTERNAL_ERROR</ErrorCode><ErrorDescription>An internal error has occurred, please contact Aconex for support on this matter</ErrorDescription><RequestID>XmtvT8NoTnUNqfyBOA5XZQAAA1c</RequestID><SystemTime>2020-03-13T11:32:31.307Z</SystemTime></Error>";
    private static final String xml ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><SendMailResult sendStatus=\"MailSent\"><NewMailId>298947432</NewMailId></SendMailResult>";
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(error)));

        Node root = document.getDocumentElement();
        NodeList list = root.getChildNodes();
        Node node = list.item(1);
        System.out.println(node.getTextContent());
    }


}


