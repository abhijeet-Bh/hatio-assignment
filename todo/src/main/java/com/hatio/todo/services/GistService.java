package com.hatio.todo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class GistService {

    private static final String GITHUB_API_URL = "https://api.github.com/gists";
    private final OkHttpClient httpClient = new OkHttpClient();

    public String createSecretGist(String markdownContent, String fileName, String githubToken) throws IOException {
        // Create Gist request body
        Map<String, Object> gistBody = new HashMap<>();
        gistBody.put("description", "Project Summary Gist");
        gistBody.put("public", false); // Secret gist

        Map<String, Map<String, String>> files = new HashMap<>();
        Map<String, String> fileContent = new HashMap<>();
        fileContent.put("content", markdownContent);
        files.put(fileName + ".md", fileContent);

        gistBody.put("files", files);

        // Build request
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                new ObjectMapper().writeValueAsString(gistBody)
        );

        Request request = new Request.Builder()
                .url(GITHUB_API_URL)
                .post(requestBody)
                .addHeader("Authorization", "token " + githubToken)
                .build();

        // Execute the request
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Extract and return the Gist URL
            String responseBody = response.body().string();
            Map<String, Object> responseMap = new ObjectMapper().readValue(responseBody, HashMap.class);
            return (String) responseMap.get("html_url");
        }
    }
}
