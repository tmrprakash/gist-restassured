package com.gist.test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

import java.io.IOException;
import java.util.*;

public class GitHubClient {
    //@BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.github.com";
        RestAssured.port = 443;
    }
    public void connectGitHub() throws IOException {
        //GitHub github = GitHub.connect();
       // GitHub github = new GitHubBuilder().withPassword("tmrprakash", "Kala$$12").build();
        //GHGist
    }

   // @Test
    public  void testGit() {
        String encodedAuthToken = Base64.getEncoder().encodeToString(("tmrprakash" + ":" + "Kala$$12").getBytes());

        given().log().all().header("authorization", encodedAuthToken).
                get("users/tmrprakash")
                .then().statusCode(200);

    }

   // @Test
    public  void testGetGist() {
        String encodedAuthToken = Base64.getEncoder().encodeToString(("tmrprakash" + ":" + "Kala$$12").getBytes());
        String oAuth = "token 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";

        Response response= (Response)
                given().header("authorization", oAuth).header("accept","application/vnd.github.v3+json").
                get("/gists/69c2364906fbb7b11795be6e4d54b675");
                        //then().assertThat()
                        //.body("files.size()", is(1))
               // .body("test.java.filename", equalTo("test.java"));
        //
        //
        System.out.println("Response:"+ response.body().asPrettyString());

    }

    //@Test
    public  void testAssertGetGist() {
        String encodedAuthToken = Base64.getEncoder().encodeToString(("tmrprakash" + ":" + "Kala$$12").getBytes());
        String oAuth = "token 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";

        // Response response= (Response)
                given().header("authorization", oAuth).header("accept","application/vnd.github.v3+json").
                        get("/gists/69c2364906fbb7b11795be6e4d54b675").
        then().assertThat()
        .body("files.size()", is(2))
        .body("files.\"test.java\".filename", equalTo("test.java"))
                        .body("files.\"test.java\".content", equalTo("test Java"));
        //
        //
        //System.out.println("Response:"+ response.body().asPrettyString());

    }
    // @Test
    public void testCreateGist() {
        String encodedAuthToken = Base64.getEncoder().encodeToString(("tmrprakash" + ":" + "Kala$$12").getBytes());
        Map<String, Object> formParamsV1 = new HashMap<>();
        formParamsV1.put("description", "TestDescription");
        formParamsV1.put("public", true);

        Map<String,Map<String,String>> fileValue = new HashMap<>();
        Map<String,String> fileContentMap = new HashMap<>();
        fileContentMap.put("content", "test Java");
        fileValue.put("test.java", fileContentMap);
        fileValue.put("test1.java", fileContentMap);

        List<Object> fileListValue = new ArrayList();
        fileListValue.add(fileValue);
        fileListValue.add(fileValue);
        formParamsV1.put("files", fileValue);
        String oAuth = "token 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";
        String gistId= given().log().all().header("Authorization", oAuth).header("accept","application/vnd.github.v3+json").header("Content-Type", "application/json")
                .body(formParamsV1)
                .when().post("/gists")
                .then()
                .log().all()
                .assertThat().statusCode(201).extract().
                        path("id");
        System.out.println("Gist ID:"+ gistId);
    }
}
