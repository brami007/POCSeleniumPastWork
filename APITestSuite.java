package com.AutoJava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.SolvablyAutoJava.JavaSolutions.APISolution;

public class APITestSuite {
	public static APISolution apiSolution;
	
	@Rule
    public TestName testName = new TestName();

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void starting(final Description description) {
            String methodName = description.getMethodName();
            String className = description.getClassName();
            className = className.substring(className.lastIndexOf('.') + 1);
            System.out.println("Starting JUnit-test: " + className + " " + methodName);
        }
    };
    
    @BeforeClass
	public static void beforeTestCase(){
    }
	
	
	@Test
	public void loginAndLogoutAPI() throws InterruptedException, IOException {
		assertEquals("loginAndLogoutAPI", testName.getMethodName());
		
		String userName = "xxx";
		String pass = "xxx";
		String loginURL = "https://dev.xxxx.com/api/users/auth/new?token=f0be4510-1df0-11eb-9569-cbae5e4af8d6";
		String checkLoginURL = "https://dev.xxxx.com/api/users/auth/check";
		String logoutURL = "https://dev.xxx.com/api/users/logout";
		String getCheckLogoutURL = "https://dev.xxx.com/api/users/profile/basics/get?token=";
		String loginParameters = "username="+userName+"&password="+pass;
		
		int count = 0;
		while (count < 100){
			count++;
			
			//login and retrieve token
			System.out.println("Loging in and retrieving token");
			String[] embeddedKeys = {"data"};
			String token = APISolution.sendPOSTWithParameters(loginURL, loginParameters, embeddedKeys, "access_token", false);
			assertNotEquals(token, null);
			System.out.println("Login token is: " + token);
			
			//verify user is logged in
			System.out.println("Verify user is logged in");
			String loginCheckStatusParameters = "token=" + token;
			embeddedKeys = null;
			String loginVerificationString = APISolution.sendPOSTWithParameters(checkLoginURL, loginCheckStatusParameters, embeddedKeys, "message", false);
			assertEquals(loginVerificationString, "Logged in.");
			System.out.println("Check login message is: " + loginVerificationString);
			
			//logout
			System.out.println("Verify user is logged out");
			loginVerificationString = APISolution.sendPOSTWithParameters(logoutURL, loginCheckStatusParameters, embeddedKeys, "message", false);
			assertEquals(loginVerificationString, "Logged out!");
			System.out.println("Check logout message is: " + loginVerificationString);
			
			//verify you are logged out and token is not usable
			System.out.println("Verify user is logged out");
			embeddedKeys = new String[] {"data"};
			String logoutVerificationString = APISolution.sendGET(getCheckLogoutURL, token, embeddedKeys, "reason", false);
			assertEquals(logoutVerificationString, "notloggedin");
			System.out.println("Check logout message is: " + logoutVerificationString);
		}
		
	}
	
	//@Test
	public void verifySearchResult() throws InterruptedException, IOException {
		assertEquals("verifySearchResult", testName.getMethodName());
		
		String userName = "xxx";
		String pass = "xxx";
		String loginURL = "https://dev.xxx.com/api/users/auth/new?token=f0be4510-1df0-11eb-9569-cbae5e4af8d6";
		int pageNumber = 1;
		int pageSize = 20;
		String pageOrder = "ASC";
		String searchURL = "https://dev.xxx.com/api/site/users/list/"+pageNumber+"/"+pageSize+"?search_term=&sort_token=first_name&sort_direction="+pageOrder+"&token=";
		String loginParameters = "username="+userName+"&password="+pass;
		
		
		//login and retrieve token
		System.out.println("Loging in and retrieving token");
		String[] embeddedKeys = {"data"};
		String token = APISolution.sendPOSTWithParameters(loginURL, loginParameters, embeddedKeys, "access_token", false);
		assertNotEquals(token, null);
		System.out.println("Login token is: " + token);
		
		//do a simple search
		System.out.println("Verify user is logged in");
		String searchResultsJSON = APISolution.sendPOSTNoParameters(searchURL, token, embeddedKeys, "users", true);
		System.out.println("JSONArray is: " + searchResultsJSON);
		
		//verify search returns 20 names
		JSONArray jsonArray = new JSONArray(searchResultsJSON);
	    List<String> list = new ArrayList<String>();
	    for(int i = 0 ; i < jsonArray.length(); i++) {
	       list.add(jsonArray.getJSONObject(i).getString("username"));
	       System.out.println(jsonArray.getJSONObject(i).getString("username")); // display usernames
	    }
	    assertEquals(list.size(), 20);
		
		
		
	}
}
