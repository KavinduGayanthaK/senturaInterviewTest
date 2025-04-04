package lk.ijse.senturaInterviewTest.senturaInterviewTest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.senturaInterviewTest.senturaInterviewTest.dto.UserDTO;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private OkHttpClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${api.url}")
    private String url;

    @Value("${api.key}")
    private String apikey;

    public UserDTO saveUser(UserDTO userDTO) {
        String endpoint = url + "/api/users";
        try {

            String jsonBody = objectMapper.writeValueAsString(userDTO);

            RequestBody body = okhttp3.RequestBody.create(
                    jsonBody,
                    MediaType.parse("application/json")
            );

            Request request = new Request.Builder()
                    .url(endpoint)
                    .post(body)
                    .addHeader("Authorization", "Bearer " + apikey)
                    .addHeader("Content-Type", "application/json")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();
                    return objectMapper.readValue(responseBody, UserDTO.class);
                } else {
                    System.out.println("Failed to save user: " + response.message());
                    throw new RuntimeException("Failed to save user: " + response);
                }
            }
        } catch (JsonProcessingException e) {
            System.out.println("Error converting user to JSON");
            e.printStackTrace();
            throw new RuntimeException("Error converting user to JSON", e);
        } catch (IOException e) {
            System.out.println("Error making HTTP request to  API");
            e.printStackTrace();
            throw new RuntimeException("Error making HTTP request to  API", e);
        }
    }

    public UserDTO getUser(String userId, Boolean trashed) {
        String endpoint = url + "/api/users/" + userId;
        if (trashed != null) {
            endpoint += "?trashed=" + trashed;
        }

        Request request = new Request.Builder()
                .url(endpoint)
                .get()
                .addHeader("Authorization", "Bearer " + apikey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                return objectMapper.readValue(responseBody, UserDTO.class);
            } else {
                throw new RuntimeException("Failed to get user: " + response.message());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UserDTO updateUser(String userId, UserDTO userDTO) throws Exception {
        String endpoint = url + "/api/users/" + userId;

        try {
            String jsonBody = objectMapper.writeValueAsString(userDTO);
            RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json"));
            Request request = new Request.Builder()
                    .url(endpoint)
                    .put(body)
                    .addHeader("Authorization", "Bearer " + apikey)
                    .addHeader("Content-Type", "application/json")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();
                    return objectMapper.readValue(responseBody, UserDTO.class);
                } else {
                    throw new RuntimeException("Failed to update user: " + response.message());
                }
            }
        } catch (Exception e) {
            throw new Exception("Error converting user to JSON", e);
        }
    }
}
