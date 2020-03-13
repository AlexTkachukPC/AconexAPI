package web;

import web.util.XmlGenerator;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class MailService {

    public static void main(String[] args) throws IOException {


        byte[] xmlBytes = XmlGenerator.generateXmlMail(
                0,
                "Systems",
                "Systems",
                "TTR - General",
                268830196L,
                false,
                2,
                new Date(),
                false,
                "subject test",
                268977889L,
                268436355L);
        System.out.println(createMail("268440297", xmlBytes));
    }



    public static String createMail(String project_id, byte[] xmlBytes) throws IOException {

        HttpURLConnection request = WebRequest.buildRequest("https://uk1.aconex.co.uk/api/projects/" + project_id + "/mail", "POST");

        String boundary = "----------------------------" + Long.toHexString(System.currentTimeMillis());

        request.setRequestProperty("Content-Type", "multipart/mixed; boundary=\"" + boundary + "\"");
        request.setRequestProperty("Accept", "application/xml");
        request.setDoOutput(true);

        byte[] boundaryStart = ("--" + boundary + "\n").getBytes(StandardCharsets.UTF_8);
        byte[] boundaryEnd = ("\n\n--" + boundary + "--").getBytes(StandardCharsets.UTF_8);

        ByteArrayOutputStream postData = new ByteArrayOutputStream();

        postData.write(boundaryStart);
        postData.write("Content-Type: application/vnd.aconex.mail.v2+xml \n\n".getBytes(StandardCharsets.UTF_8));
        postData.write(xmlBytes);
        postData.write(boundaryEnd);

        postData.close();

        request.setRequestProperty("Content-Length", Integer.toString(postData.size()));

        try (OutputStream requestStream = request.getOutputStream()) {
            requestStream.write(postData.toByteArray());
            requestStream.flush();
        }

        return WebRequest.getResponse(request);
    }


    public static String updateMail(String project_id, byte[] xmlBytes) throws IOException {
        return null;
    }

}
