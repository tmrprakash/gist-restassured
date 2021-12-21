# GIST API Test App
 - GitHub Gist: Instantly share code, notes, and snippets.
## About this Test
 - Test the Creation, Read and Delete the GIST using API Endpoints

## Setup and Execution
1. Pre-requisite
   ```
   Java 1.8 or above
   Maven installed 
   ```
2. Update Environment Config
    ```
    Navigate to src/test/resources/config.properties
    Update git_personal_auth_token
    ```
   - How to generate personal access token: https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token
3. Install and Test 
    ```
    >mvn clean install
    >mvn test
    ```
4. Reporting
    - Test Reports are available in
    ```
    /target/surefire-reports/index.html
    ```

