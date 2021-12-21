package com.gist.test;
import com.gist.helper.GistTestHelper;
import com.gist.util.EnvironmentConstants;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

public class GISTCRDOperationTest {
    private String gistId;
    private GistTestHelper gistTestHelper;
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = EnvironmentConstants.GIT_APP_URL;
        RestAssured.port = EnvironmentConstants.GIT_PORT_NUMBER;
        gistTestHelper = new GistTestHelper();
    }

    @Test
    public void testCreateGist() {
         gistId= given().header("Authorization", EnvironmentConstants.GIT_PERSONAL_AUTH_TOKEN).header("accept","application/vnd.github.v3+json").header("Content-Type", "application/json")
                .body(gistTestHelper.constructGistPayload())
                .when().post("/gists")
                .then()
                .assertThat().statusCode(HttpStatus.SC_CREATED).extract().
                        path("id");
        System.out.println("Gist ID:"+ gistId);
    }

    @Test(dependsOnMethods = {"testCreateGist"})
    public void validateCreatedGist() {
        given().header(EnvironmentConstants.GIT_AUTH_HEADER_KEY, EnvironmentConstants.GIT_PERSONAL_AUTH_TOKEN).header(EnvironmentConstants.GIT_ACCEPT_HEADER_KEY,EnvironmentConstants.GIT_ACCEPT_HEADER).
                when().get("/gists/"+gistId).
                then().assertThat()
                .body("files.size()", is(2))
                .body(gistTestHelper.constructFilePath(GistTestHelper.javaFileName,"filename"), equalTo(GistTestHelper.javaFileName))
                .body(gistTestHelper.constructFilePath(GistTestHelper.javaFileName,"content"), equalTo(GistTestHelper.fileJavaContent))
                .body(gistTestHelper.constructFilePath(GistTestHelper.textFileName,"content"), equalTo(GistTestHelper.textFileContent))
                .body("description",equalTo(GistTestHelper.gistDescription))
                .body("public", equalTo(GistTestHelper.isPublicGist));
    }

    @Test(dependsOnMethods = {"validateCreatedGist"})
    public void testDeleteGist() {
        given().header(EnvironmentConstants.GIT_AUTH_HEADER_KEY, EnvironmentConstants.GIT_PERSONAL_AUTH_TOKEN).header(EnvironmentConstants.GIT_ACCEPT_HEADER_KEY,EnvironmentConstants.GIT_ACCEPT_HEADER).
                when(). delete("/gists/"+gistId).
                then().assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test(dependsOnMethods = {"testDeleteGist"})
    public void validateDeletedGist() {
        given().header(EnvironmentConstants.GIT_AUTH_HEADER_KEY, EnvironmentConstants.GIT_PERSONAL_AUTH_TOKEN).header("accept","application/vnd.github.v3+json").
                when().get("/gists/"+gistId).
                then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
