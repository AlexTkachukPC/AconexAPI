package web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class MailService {

    public static void main(String[] args) throws IOException {

        byte[] xmlBytes = MailService.class.getClassLoader().getResourceAsStream("mail.xml").readAllBytes();

        //it works:)
        MailServiceV2 mailServiceV2 = new MailServiceV2();
        System.out.println(mailServiceV2.createMail("268440297", new String(xmlBytes)));

        //it works as well!
//        System.out.println(createMail("268440297", xmlBytes));
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

}
