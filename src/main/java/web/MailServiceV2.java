package web;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class MailServiceV2 {

    private static final String USERNAME = "Ameir";
    private static final  String PASSWORD = "shuki914";
    private static final String KEY = "a9e0796b-d64d-4abc-b4c4-d80dd2344e2e";

    private final RestTemplate restTemplate;

    public MailServiceV2() {
        this.restTemplate = getRestTemplate();
    }

    private RestTemplate getRestTemplate() {

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add((request, body, execution) -> {

            String base64Credentials = Base64.encodeBase64String(String.join(":", USERNAME, PASSWORD).getBytes());

            HttpHeaders headers = request.getHeaders();
            headers.add("Authorization", "Basic " + base64Credentials);
            headers.add("X-Application-Key", KEY);

            return execution.execute(request, body);
        });

        return restTemplate;
    }

    public String createMail(String projectId, String body) {

        String url = "https://uk1.aconex.co.uk/api/projects/" + projectId + "/mail";

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_MIXED_VALUE);

        HttpHeaders xmlBodyHeaders = new HttpHeaders();
        xmlBodyHeaders.add(HttpHeaders.CONTENT_TYPE, "application/vnd.aconex.mail.v2+xml");

        MultiValueMap<String, HttpEntity<?>> multipartBody = new LinkedMultiValueMap<>();
        multipartBody.add("",  new HttpEntity<>(body, xmlBodyHeaders));

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, new HttpEntity<>(multipartBody, headers), String.class);

        return responseEntity.getBody();
    }
}
