package web;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebRequest {

    private static final String USERNAME = "Ameir";
    private static final  String PASSWORD = "shuki914";
    private static final String KEY = "a9e0796b-d64d-4abc-b4c4-d80dd2344e2e";



    static String mailXML =
            "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>" +
                    "<Mail><TotalAttachmentCount>0</TotalAttachmentCount>" +
                    "<Attribute1>Systems</Attribute1>" +
                    "<Attribute2>Systems</Attribute2>" +
                    "<Attribute3>TTR - General</Attribute3>" +
                    "<CcUserId>268830196</CcUserId>" +
                    "<Confidential>false</Confidential>" +
                    "<ResponseRequired>2</ResponseRequired>" +
                    "<ResponseRequiredDate>2020-02-28T00:00:00.000Z</ResponseRequiredDate><RichMailText>false</RichMailText><MailSubject>fghjkl;'</MailSubject><ToUserId>268977889</ToUserId><MailTypeId>268436355</MailTypeId><MailFormFields><MailFormField identifier=\"NcrType_singleSelect\">Quality</MailFormField><MailFormField identifier=\"Drawingreference_singleLineText\">N/A</MailFormField><MailFormField identifier=\"SpeccontractReference_singleLineText\">N/A</MailFormField><MailFormField identifier=\"OpenedBy_singleSelect\">QC</MailFormField><MailFormField identifier=\"ResponsiblePerson_singleLineText\">N/A</MailFormField><MailFormField identifier=\"DateOpened_date\">2020-02-28</MailFormField><MailFormField identifier=\"NcrStatus_singleSelect\">Open</MailFormField><MailFormField identifier=\"ExpectedClosingDate_date\">2020-02-28</MailFormField><MailFormField identifier=\"NcrLevel_singleSelect\">1</MailFormField><MailFormField identifier=\"ElementNamecode_singleLineText\">N/A</MailFormField><MailFormField identifier=\"DescriptionOfTheDeficiency_multiLineText\">N/A</MailFormField><MailFormField identifier=\"CauseOfDeficiency_singleSelect\">N/A</MailFormField><MailFormField identifier=\"ProposedCorrectiveAction_multiLineText\">N/A</MailFormField><MailFormField identifier=\"ProposedDisposition_singleSelect\">N/A</MailFormField></MailFormFields></Mail>";



    public static HttpURLConnection buildRequest(String uri, String method) {
        try {
            URL url = new URL(uri);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            String cred = new String(Base64.encodeBase64(
                    (USERNAME + ":" + PASSWORD).getBytes("UTF-8")));

            request.addRequestProperty("Authorization", "Basic " + cred);
            request.addRequestProperty("X-Application-Key", KEY);
            request.setRequestMethod(method);
            return request;
        } catch (Exception e) {
            System.out.println("Exception impossible to build request!");
        }
        return null;
    }

    public static String getResponse(HttpURLConnection request) {
        try {
                String line;
                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        request.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
                return sb.toString();

        } catch (Exception e) {
            try {
                    String line = null;
                    BufferedReader rd = new BufferedReader(
                            new InputStreamReader(
                                    request.getErrorStream(), "UTF-8"));
                    StringBuilder sb = new StringBuilder();
                    while ((line = rd.readLine()) != null) {
                        sb.append(line + '\n');
                    }
                    rd.close();
                    return sb.toString();

            } catch (Exception ex) {
                System.out.println("Please check your Internet connection\n" +
                        e.getMessage());
            }
        }
        return null;
    }

    public static List<String> getDocumentsIdByType(String project_id, String type) throws ParserConfigurationException, IOException, SAXException {
        HttpURLConnection request = buildRequest("https://uk1.aconex.co.uk/api/projects/" + project_id + "/register?search_query=doctype:" + type, "GET");
        String response = getResponse(request);

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(new ByteArrayInputStream(response.getBytes()));

        NodeList nodeList = document.getElementsByTagName("Document");

        List<String> result = new ArrayList<>();

        for(int i = 0; i < nodeList.getLength(); i++) {
            result.add(nodeList.item(i).getAttributes().getNamedItem("DocumentId").getNodeValue());
        }

        return result;
    }

}
