package com.SolvablyAutoJava;
import static org.junit.Assert.assertEquals;
//test

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.time.Duration;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.junit5.SauceBaseTest;


public class CoreSmokeTestsUI extends SauceBaseTest {
	public static WebDriver myDriver;
	public static GeneralElements generalElements;
	public static CommonFunctions commonFunctions;
	public static BaseFunctions baseFunctions;
	DataCenter dataCenter = DataCenter.EU_CENTRAL;
	
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
        @Override
        protected void failed(Throwable e, Description description) {
        	try {
        	commonFunctions.Logout(); 
        	
        	//login with Organiztion admin single organization
  		  	commonFunctions.loginWithUser("bramirez@massiveu.com", "Neonboy44!!"); //go
  		  	//back to organization and delete the class created
  		  	commonFunctions.loginAndDeleteOrganizationClassWith1Class("Canvas1");
  		  	
  		  	commonFunctions.PassFailTest("failed"); 
  		  	commonFunctions.closeBrowser();
        	} catch (InterruptedException e1) {
        	
        	}
        }

        @Override
        protected void succeeded(Description description) {
        	try {
				commonFunctions.PassFailTest("passed");
				commonFunctions.closeBrowser();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				///e.printStackTrace();
			}
           }
		 
       
    };
    
    
    
 // Specify Sauce Specific Options Based on Browser
    public SauceOptions createSauceOptions() {
        return (SauceOptions) SauceOptions.chrome()
                .setExtendedDebugging()
                .setIdleTimeout(Duration.ofSeconds(45))
                .setTimeZone("Alaska")
                .build();
    }
	
	
	@Before
	public void beforeTestCase() throws MalformedURLException{
		//sauce labs set start
		ChromeOptions browserOptions = new ChromeOptions();
		Map<String, Object> sauceOptions = new HashMap<>();
		sauceOptions.put("build", "1");
		sauceOptions.put("name", "xxx smoke test");
		sauceOptions.put("screen-resolution", "1920x1080");
		browserOptions.setCapability("sauce:options", sauceOptions);

		URL url = new URL("xxx");
		RemoteWebDriver myDriver = new RemoteWebDriver(url, browserOptions);
		//sauce lab setup stop
		
		//uncomment me to run locally
		//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/myDriver/chromedriver_1.exe");
		//to run local change to MyDriver.
		//myDriver = new ChromeDriver();
		
		generalElements = new GeneralElements(myDriver);
		commonFunctions = new CommonFunctions(myDriver);
		myDriver.navigate().to("xxxxx.com");
		myDriver.manage().window().maximize();
	
	}
	
	//SOLV-TC-1 Login and logout 
	//@Test
	public void SOLVTC1LoginLogout() throws InterruptedException {
		
		//1. validate the admin site loads after logging in
		assertEquals("SOLVTC1LoginLogout", testName.getMethodName());
		//commonFunctions.SetSauceTestName("Login/Logout all users");
		
		//verify you get error with wrong password
		commonFunctions.loginWithUser("user", "pass");
		
		generalElements.findText("Invalid credentials provided");
		
		//clear email text
		generalElements.clearText("name","email_address","");
		
		//go to login and input credentials for admin
		commonFunctions.loginWithUser("user", "pass");
		
		//logout
		commonFunctions.Logout();
		
		//2. validate the organization admin site loads after logging in
				
		//verify you get error with wrong password
		commonFunctions.loginWithUser("user", "pass");
		
		generalElements.findText("Invalid credentials provided");
				
		//clear email text
		generalElements.clearText("name","email_address","");
				
				
		//go to login and input credentials for admin
		commonFunctions.loginWithUser("user", "pass");
				
		//logout
		commonFunctions.Logout();
		
		//3. validate the instructor loads after logging in
						
		//verify you get error with wrong password
		commonFunctions.loginWithUser("user", "pass");
		
		generalElements.findText("Invalid credentials provided");
		
		//clear email text
		generalElements.clearText("name","email_address","");
						
		//go to login and input credentials for admin
		commonFunctions.loginWithUser("user", "pass");					
						
		//logout
		commonFunctions.Logout();
		
		//5. validate the student loads after logging in
								
		//verify you get error with wrong password
		commonFunctions.loginWithUser("user", "pass");
		
		generalElements.findText("Invalid credentials provided");
								
		//clear email text
		generalElements.clearText("name","email_address","");
											
		//go to login and input credentials for admin
		commonFunctions.loginWithUser("user", "pass");
								
		//verify page loaded after login
		generalElements.findText("You do not belong to any active classes. Please contact your administrator if you believe this is an error.");
								
		//logout
		commonFunctions.Logout();

	}
	
	
	//SOLV-TC-5 Main dashboard users
	//@Test
	public void SOLVTC5UserFunctionalityAdmin() throws InterruptedException {
		
		//1. validate the admin site loads after logging in
		assertEquals("SOLVTC5UserFunctionalityAdmin", testName.getMethodName());
		//commonFunctions.SetSauceTestName("Admin dashboard main functionality");
					
		//login with admin
		commonFunctions.loginWithUser("user", "pass");
			
		//verify page loaded after login and go to users
		generalElements.findText("Site administration");
		generalElements.clickElement("href","#users");
		
		//search for user and click to view user
		generalElements.sendKeysToElement("class", "form-control", "AdminUsers");
		generalElements.clickElementByXpath("//button[.='View']");
		
		//Verify user details show and close
		generalElements.findText("Overview");
		generalElements.findText("Development");
		generalElements.findText("Memberships");
		generalElements.findText("AdminUsers");
		
		//change role to admin and save
		generalElements.clickElementByXpath("//button[@class='align-self-start d-flex align-items-center p-0 btn btn-link btn-sm']");
		generalElements.selectDropdown("name", "role", "Site Admin");		
		generalElements.clickElementByXpath("//button[.='Submit']");
		
		//verify user is admin
		
		//generalElements.findText("Site Admin");
		
		//Change role back to none
		generalElements.clickElementByXpath("//button[@class='align-self-start d-flex align-items-center p-0 btn btn-link btn-sm']");
		generalElements.selectDropdown("name", "role", "None");		
		generalElements.clickElementByXpath("//button[.='Submit']");
		
		
		//verify user is not admin
		
		generalElements.findText("None");
		
		//change the users password
		generalElements.clickElementByXpath("//button[@class='align-self-start d-flex align-items-center btn btn-primary btn-sm']");
		generalElements.clickElementByXpath("//a[@id='edit-user-tabs-tab-password']");
		int number = new Random().nextInt(1111) + 20;
		generalElements.sendKeysToElement("name", "password", "Password" + number);
		generalElements.sendKeysToElement("name", "password_confirm", "Password" + number);
		generalElements.clickElement("name", "disclaimer");
		generalElements.collectElementsByXpath("//button[@type='submit']").get(1).click();
		generalElements.collectElementsByXpath("//button[@class='btn-close']").get(2).click();
		generalElements.clickListElementByJavaScript("//button[@class='btn-close']",0);
		
		//logout
		commonFunctions.Logout();
		
		//login with admin
		commonFunctions.loginWithUser("user", "pass");
					
		//verify page loaded after login and go to users
		generalElements.findText("No groups found.");
		
		//logout
		commonFunctions.Logout();
			
	}
	
	
	//SOLV-TC-14  Footer Links
	//@Test
	public void SOLVTC14FooterLinks() throws InterruptedException {
		
		assertEquals("SOLVTC14FooterLinks", testName.getMethodName());
				
		//go to footer link "support" and verify content loads and return to homepage
		generalElements.clickElement("href","/support");
		generalElements.findText("Support Center");
		generalElements.findText("General / Getting Started");
		generalElements.findText("What is AIM?");
		generalElements.findText("Troubleshooting / Technical Support");
		generalElements.findText("How does AIM work?");
		generalElements.findText("General / Getting Started");
		generalElements.findText("Privacy Policy");
		
		generalElements.clickElement("alt","AIM logo");
				
		//go to footer link "Privacy Policy" and verify content loads and return to homepage
		generalElements.clickElementByXpath("//a[.='Privacy Policy']");
		
	    generalElements.findText("Talogy Privacy Policy");
	    generalElements.findText("Information We Collect");
	    generalElements.findText("Children’s Online Privacy Protection Act");
	    generalElements.findText("Purposes of Processing");

		
	}
	
	//SOLV-TC-84 Design lab create, copy, delete challenges
	//@Test
	public void SOLVTC84DesignLabCreateAndCopyChallenges() throws InterruptedException {
		//1. validate the admin site loads after logging in
		assertEquals("SOLVTC84DesignLabCreateAndCopyChallenges", testName.getMethodName());
					
		//login with instructor
		commonFunctions.loginWithUser("user", "pass");
		
		//verify page loaded after login and go to design lab
		generalElements.clickElement("href", "/admin/challenge-templates");
		
		//create challenge and verify it displays
		generalElements.collectElementsByXpath("//button[@class='dropdown-toggle btn btn-secondary']").get(0).click();
		generalElements.clickElementByXpath("//a[.=' Challenge']");
		int number = new Random().nextInt(1111) + 20;
		generalElements.sendKeysToElement("class", "form-control", "testChallenge" + number);
		generalElements.clickElement("id", "new-challenge-template-disclaimer");
		generalElements.clickElement("class", "btn btn-primary");
		
		//return to design lab and verify new challenge displays under new
		generalElements.findText("Challenge Editor");
		generalElements.findText("testChallenge"+number);
		generalElements.clickElement("href", "/admin/challenge-templates");
			
		//click and copy the challenge you created
		
		WebElement element = myDriver.findElement(By.xpath("//a[.='Support']"));
		((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
		generalElements.collectElementsByXpath("//h5[.='testChallenge" + number+"']").get(0).click();
		generalElements.findText("testChallenge"+number);
		
		
		element = myDriver.findElement(By.xpath("//button[.='Copy']"));
		((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
		generalElements.clickElementByXpath("//button[.='Copy']");
		generalElements.clickElementByXpath("//button[.='Copy Challenge']");
		
		//Verify challenge was copied
		generalElements.findText("Challenge copied successfully.");
		
		//Return to the design verify challenge is there and return
		generalElements.clickElement("href", "/admin/challenge-templates");
		
		element = myDriver.findElement(By.xpath("//a[.='Support']"));
		((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
		generalElements.collectElementsByXpath("//h5[.='testChallenge" + number+"']").get(0).click();
		generalElements.findText("testChallenge"+number);
		
		//delete challenge
		
		element = myDriver.findElement(By.xpath("//button[.='Delete']"));
		((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
		generalElements.clickElementByXpath("//button[.='Delete']");
		generalElements.clickElementByXpath("//button[.='Delete challenge']");
		generalElements.findText("Challenge deleted successfully.");
		
		//go back to design
		generalElements.clickElement("href", "/admin/challenge-templates");
		
		//delete copied challenge
		
		((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
		generalElements.collectElementsByXpath("//h5[.='testChallenge" + number+"']").get(0).click();
		generalElements.findText("testChallenge"+number);
		
		//delete challenge
		
		element = myDriver.findElement(By.xpath("//button[.='Delete']"));
		((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
		generalElements.clickElementByXpath("//button[.='Delete']");
		generalElements.clickElementByXpath("//button[.='Delete challenge']");
		generalElements.findText("Challenge deleted successfully.");
		
		//logout
		generalElements.collectElementsByXpath("//div[@class='user-icon-inner']").get(0).click();
		generalElements.clickElement("href","/logout");

		
	}
	
	//SOLV-TC-84 Design lab create, copy, delete journeys
		//@Test
		public void SOLVTC84DesignLabCreateAndCopyJourneys() throws InterruptedException {
			//1. validate the admin site loads after logging in
			assertEquals("SOLVTC84DesignLabCreateAndCopyJourneys", testName.getMethodName());
						
			//login with instructor
			commonFunctions.loginWithUser("user", "pass");
			
			//verify page loaded after login and go to design lab
			generalElements.clickElement("href", "/admin/challenge-templates");
			
			//create challenge and verify it displays
			generalElements.collectElementsByXpath("//button[@class='dropdown-toggle btn btn-secondary']").get(0).click();
			generalElements.clickElementByXpath("//a[.='Journey']");
			int number = new Random().nextInt(1111) + 20;
			generalElements.sendKeysToElement("class", "form-control", "testChallenge" + number);
			generalElements.clickElement("id", "new-challenge-template-disclaimer");
			generalElements.clickElement("class", "btn btn-primary");
			
			//return to design lab and verify new challenge displays under new
			generalElements.findText("Journey Editor");
			generalElements.findText("testChallenge"+number);
			generalElements.clickElement("href", "/admin/challenge-templates");
			
			//Return to the design verify challenge is there and return
			
			WebElement element = myDriver.findElement(By.xpath("//a[.='Support']"));
			((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
			generalElements.collectElementsByXpath("//h5[.='testChallenge" + number+"']").get(0).click();
			generalElements.findText("testChallenge"+number);
			
			//delete challenge
			generalElements.clickElementByXpath("//button[.='Delete']");
			generalElements.clickElementByXpath("//button[.='Delete journey']");
			generalElements.findText("Journey deleted successfully.");
			
			//go back to design
			generalElements.clickElement("href", "/admin/challenge-templates");
			
			//logout
			generalElements.collectElementsByXpath("//div[@class='user-icon-inner']").get(0).click();
			generalElements.clickElement("href","/logout");

			
		}
	
	
	//SOLV-TC-94 Joining a team with and without password
	//@Test
	public void SOLVTC94JoiningATeamWithAndWithoutPassword() throws InterruptedException {
		//1. validate the admin site loads after logging in
		assertEquals("SOLVTC94JoiningATeamWithAndWithoutPassword", testName.getMethodName());
						
		//login with Organiztion admin single organization
		commonFunctions.loginWithUser("user", "pass");
		
		//create a class for the organization
		generalElements.clickElement("href","#organizations");
		
		generalElements.clickElementByXpath("//*[@id=\"admin-tabs-tabpane-organizations\"]/div/div/table/tbody/tr[2]/td[1]/a");
		commonFunctions.createClass("AutomationTest");
		
		//go to add users
		generalElements.clickElement("id","admin-tabs-tab-users");
		generalElements.clickElementByXpath("//button[.=' Add User']");
		generalElements.clickElementByXpath("//a[.='Add user']");

		
		// add both users
		//generalElements.sendKeysToElement("placeholder", "Enter word...", randomText1);
		generalElements.sendKeysToElementByXpath("//input[@placeholder='Search for user by name or email...']", "bramirez+automationuser@massiveu.com");
		
		generalElements.clickElementByXpath("//input[@class='form-check-input']");
		generalElements.clickElementByXpath("//button[@class='btn btn-primary']");
		
		generalElements.clickElementByXpath("//button[.=' Add User']");
		generalElements.clickElementByXpath("//a[.='Add user']");
		generalElements.sendKeysToElementByXpath("//input[@placeholder='Search for user by name or email...']", "bramirez+automationuser2@massiveu.com");
		
		generalElements.clickElementByXpath("//input[@class='form-check-input']");
		generalElements.clickElementByXpath("//button[@class='btn btn-primary']");
		
		
		//Go to challenges and create a new one
		commonFunctions.assignChallenge2Members("h5", "challenge 2 8 reg");
		
		
		//make a password for 1 team
		generalElements.clickElementByXpath("//a[.='Teams']");
		generalElements.clickElementByXpath("//button[@class=' btn btn-link']");
		generalElements.clickElementByXpath("//button[.='Delete team']");
		generalElements.clickElementByXpath("//button[.='Delete']");
		
		generalElements.clickElementByXpath("//button[@class=' btn btn-link']");
		generalElements.clickElementByXpath("//button[.='Set team password']");
		generalElements.sendKeysToElementByXpath("//input[@aria-label='password']", "test123");
		generalElements.clickElementByXpath("//button[@type='submit']");
		
		//go to settings and allow teams to form on their own
		generalElements.clickElementByXpath("//a[.='Settings']");
		generalElements.selectDropdown("name", "can_learners_create_teams", "Yes");
		WebElement element = myDriver.findElement(By.xpath("//button[@type='submit']"));
		((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
		generalElements.clickElementByXpath("//button[@type='submit']");
		//generalElements.clickElementByXpath("//button[.='No']");
		
		//logout
		generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
		generalElements.clickElement("href","/logout");

								
		//login with Organiztion admin single organization
		commonFunctions.loginWithUser("user", "pass");
		
		//join the challenge without a team password
		generalElements.clickElementByXpath("//button[.='Start']");
		generalElements.clickElementByXpath("//button[.='Join a Team']");
		generalElements.clickElementByXpath("//button[.='Join']");
		generalElements.sendKeysToElementByXpath("//input[@class='form-control']", "test123");
		generalElements.clickElementByXpath("//button[.='Submit']");
		generalElements.clickElementByXpath("//button[.='Close']");
		generalElements.findText("Overview");
		
		//logout
		generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
		generalElements.clickElement("href","/logout");
		
		//login with Organiztion admin single organization
		commonFunctions.loginWithUser("user", "pass");
		
		//go back to organization and delete the class created
		commonFunctions.loginAndDeleteOrganizationClassWith1Class("Canvas1");
		
	}
	
	//SOLV-TC-39 Organization classes creating and deleting
	//@Test
	public void SOLVTC39OrganizationClassesCreatingAndDeleting() throws InterruptedException {
		//1. validate the admin site loads after logging in
		assertEquals("SOLVTC39OrganizationClassesCreatingAndDeleting", testName.getMethodName());
						
		//login with admin single organization
		commonFunctions.loginWithUser("user", "pass");
		
		//create a class for the organization
		generalElements.clickElement("href","#organizations");
		generalElements.clickElementByXpath("//b[.='Canvas1']");
		commonFunctions.createClass("AutomationTest");
		
		//go back to organization and delete the class created
		generalElements.clickElement("href","/admin/organization/25");
		
		generalElements.clickElement("id","admin-tabs-tab-groups");
		generalElements.clickElementByXpath("//button[@class=' btn btn-link']");
		generalElements.clickElementByXpath("//a[.='Delete']");
		generalElements.clickElementByXpath("//button[.='Delete Group']");
		generalElements.findText("No groups found.");
		
		//logout
		generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
		generalElements.clickElement("href","/logout");
		
		//login with admin single organization
		commonFunctions.loginWithUser("user", "pass");
		
		//create a class for the organization
		commonFunctions.createClass("AutomationTest");
				
		//go back to organization and delete the class created
		generalElements.clickElement("href","/admin/organization/25");
				
		generalElements.clickElement("id","admin-tabs-tab-groups");
		generalElements.clickElementByXpath("//button[@class=' btn btn-link']");
		generalElements.clickElementByXpath("//a[.='Delete']");
		generalElements.clickElementByXpath("//button[.='Delete Group']");
		generalElements.findText("No groups found.");
		
		//logout
		generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
		generalElements.clickElement("href","/logout");
		
	}
	
	//SOLV-TC-99 Student Challenge Discover Phase
	//@Test
	public void SOLVTC99StudentChallengeDiscoverPhase() throws InterruptedException {
			//1. validate the admin site loads after logging in
			assertEquals("SOLVTC99StudentChallengeDiscoverPhase", testName.getMethodName());
							
							
			//login with Organiztion admin single organization
			commonFunctions.loginWithUser("user", "pass");
			
			//create a class for the organization
			generalElements.clickElement("href","#organizations");
			generalElements.clickElementByXpath("//*[@id=\"admin-tabs-tabpane-organizations\"]/div/div/table/tbody/tr[2]/td[1]/a");
			commonFunctions.createClass("AutomationTest");
			
			//go to add users
			generalElements.clickElement("id","admin-tabs-tab-users");
			generalElements.clickElementByXpath("//button[.=' Add User']");
			generalElements.clickElementByXpath("//a[.='Add user']");

			
			// add both users
			//generalElements.sendKeysToElement("placeholder", "Enter word...", randomText1);
			generalElements.sendKeysToElementByXpath("//input[@placeholder='Search for user by name or email...']", "bramirez+automationuser@massiveu.com");
			
			generalElements.clickElementByXpath("//input[@class='form-check-input']");
			generalElements.clickElementByXpath("//button[@class='btn btn-primary']");
			
			
			//Go to challenges and create a new one
			commonFunctions.assignChallenge("h5", "challenge 2 8 reg");

			//logout
			generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
			generalElements.clickElement("href","/logout");			
									
			//login with Organiztion admin single organization
			commonFunctions.loginWithUser("user", "pass");
			
			//join the challenge without a team password
			generalElements.clickElementByXpath("//a[.='Start']");
			
			
			// join challenge and go to challenge hub
			generalElements.clickElementByXpath("//button[.='Close']");
			generalElements.clickElementByXpath("//button[.='Solve Now']");
			
			//go through discover phase
			String randomText1 = "adding";
			String randomText2 = "different";
			String randomText3 = "words";
			String randomText4 = "to";
			String randomText5 = "cloud";
			String randomText6 = "and";
			String randomText7 = "verify";
			String randomText8 = "it";
			String randomText9 = "works";
			String randomText10 = "perfect";
			commonFunctions.completeDiscoveryPhase(randomText1, randomText2, randomText3, randomText4, randomText5, 
					randomText6, randomText7, randomText8, randomText9, randomText10);
			
			//go back to define challenge and verify submit challenge is disabled with going below 10 words
			generalElements.clickElementByXpath("//div[.='Define Challenge']");
			
			WebElement element = myDriver.findElement(By.xpath("//button[.='See Example']"));
			((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
			generalElements.clickElementByXpath("//span[.='"+randomText9+"']");
			myDriver.switchTo().alert().accept();
			generalElements.verifyButtonDisabled("//button[.='Submit Discover']");
			generalElements.sendKeysToElement("placeholder", "Enter word...", randomText9);
			generalElements.clickElementByXpath("//button[.='Add Word']");
			
			//go to second card and verify submit challenge is disabled after taking out required value
			generalElements.clickElementByXpath("//div[.='Identify Audience']");
			
			element = myDriver.findElement(By.xpath("//button[.='See Example']"));
			((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
			myDriver.findElement(By.xpath("//div[@class='ql-editor']")).clear();
			generalElements.verifyButtonDisabled("//button[.='Submit Discover']");
			generalElements.sendKeysToElement("class", "ql-editor ql-blank", "testing and filling this out to check that it is working and is long enough");
			
			//go to third card and verify submit challenge is disabled after taking out required value
			generalElements.clickElementByXpath("//div[.='Establish Constraints']");
			int count = 33;
			while(count > 0) {
				count--;
				generalElements.collectElementsByXpath("//textarea[@aria-label='text input for Time constraint']").get(0).sendKeys(Keys.BACK_SPACE);
			}
			
			generalElements.verifyButtonDisabled("//button[.='Submit Discover']");
			
			generalElements.collectElementsByXpath("//textarea[.='']").get(0).sendKeys("adding text to complete this card");
			
			//go to fourth card and verify submit challenge is disabled after taking out required value
			generalElements.clickElementByXpath("//div[.='Share What You Know']");
			

			//submit the phase and verify you are taken to new phase
			commonFunctions.submitPhase("Discover", "Explore");
			generalElements.verifyButtonDisabled("//button[.='Submit Explore']");
			
			//logout
			generalElements.collectElementsByXpath("//button[@aria-label='back to challenge overview']").get(0).click();
			
			generalElements.collectElementsByXpath("//button[@aria-label='back to challenge overview']").get(0).click();
			generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
			generalElements.clickElement("href","/logout");
			
			//login with Organization admin single organization
			commonFunctions.loginWithUser("user", "pass");
			
			//go back to organization and delete the class created
			commonFunctions.loginAndDeleteOrganizationClassWith1Class("Canvas1");

	}
	
	//SOLV-TC-100 Student Challenge Explore Phase
	//@Test
	public void SOLVTC100StudentChallengeExplorePhase() throws InterruptedException {
			//1. validate the admin site loads after logging in
			assertEquals("SOLVTC100StudentChallengeExplorePhase", testName.getMethodName());
							
			//login with Organiztion admin single organization
			commonFunctions.loginWithUser("user", "pass");
			
			//create a class for the organization
			generalElements.clickElement("href","#organizations");
			generalElements.clickElementByXpath("//*[@id=\"admin-tabs-tabpane-organizations\"]/div/div/table/tbody/tr[2]/td[1]/a");
			commonFunctions.createClass("AutomationTest");
			
			//go to add users
			generalElements.clickElement("id","admin-tabs-tab-users");
			generalElements.clickElementByXpath("//button[.=' Add User']");
			generalElements.clickElementByXpath("//a[.='Add user']");

			
			// add both users
			//generalElements.sendKeysToElement("placeholder", "Enter word...", randomText1);
			generalElements.sendKeysToElementByXpath("//input[@placeholder='Search for user by name or email...']", "bramirez+automationuser@massiveu.com");
			
			generalElements.clickElementByXpath("//input[@class='form-check-input']");
			generalElements.clickElementByXpath("//button[@class='btn btn-primary']");
			
			
			//Go to challenges and create a new one
			commonFunctions.assignChallenge("h5", "challenge 2 8 reg");

			//logout
			generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
			generalElements.clickElement("href","/logout");
			
									
			//login with Organiztion admin single organization
			commonFunctions.loginWithUser("user", "pass");
			
			//join the challenge without a team password
			generalElements.clickElementByXpath("//a[.='Start']");
			
			
			
			// join challenge and go to challenge hub
			generalElements.clickElementByXpath("//button[.='Close']");
			generalElements.clickElementByXpath("//button[.='Solve Now']");
			
			//go through discover phase
			String randomText1 = "adding";
			String randomText2 = "different";
			String randomText3 = "words";
			String randomText4 = "to";
			String randomText5 = "cloud";
			String randomText6 = "and";
			String randomText7 = "verify";
			String randomText8 = "it";
			String randomText9 = "works";
			String randomText10 = "perfect";
			commonFunctions.completeDiscoveryPhase(randomText1, randomText2, randomText3, randomText4, randomText5, 
					randomText6, randomText7, randomText8, randomText9, randomText10);
			
			//submit the phase and verify you are taken to new phase
			commonFunctions.submitPhase("Discover", "Explore");
			
			generalElements.verifyButtonDisabled("//button[.='Submit Explore']");
			
			//complete explore phase card
			commonFunctions.completeExplorePhase();
			
			//remove an item and verify phase submission is disabled
			WebElement element = myDriver.findElement(By.xpath("//button[.='See Example']"));
			((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
			generalElements.collectElementsByXpath("//button[@title='Actions']").get(0).click();
			generalElements.collectElementsByXpath("//a[@class='dropdown-item']").get(1).click();
			generalElements.collectElementsByXpath("//button[.='Remove']").get(0).click();
			generalElements.verifyButtonDisabled("//button[.='Submit Explore']");
			
			//add item back and verify button is enabled
			//add research cards
			
			element = myDriver.findElement(By.xpath("//span[.='Review Guiding Resources']"));
			((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
			generalElements.collectElementsByXpath("//button[.='Add Research']").get(0).click();
			generalElements.clickElementByXpath("//h5[.='Online']");
			generalElements.collectElementsByXpath("//input[@class='form-control']").get(0).sendKeys("test.com");
			generalElements.clickElementByXpath("//button[.='Next']");
			
			generalElements.collectElementsByXpath("//textarea[@placeholder='Write at least 5 words...']").get(0).sendKeys("some text to complete this");
			generalElements.clickElementByXpath("//button[.='Submit']");
			generalElements.collectElementsByXpath("//button[.='Submit Explore']").get(0).isEnabled();
			
			//submit the phase and verify you are taken to new phase
			commonFunctions.submitPhase("Explore", "Imagine");
			
			generalElements.verifyButtonDisabled("//button[.='Submit Imagine']");
			
			//logout
			generalElements.collectElementsByXpath("//button[@aria-label='back to challenge overview']").get(0).click();
			
			generalElements.collectElementsByXpath("//button[@aria-label='back to challenge overview']").get(0).click();
			generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
			generalElements.clickElement("href","/logout");
			
			//login with Organiztion admin single organization
			commonFunctions.loginWithUser("user", "pass");
			
			//go back to organization and delete the class created
			commonFunctions.loginAndDeleteOrganizationClassWith1Class("Canvas1");

	}
	
	//SOLV-TC-101 Student Challenge Imagine Phase
	//@Test
	public void SOLVTC101StudentChallengeImaginePhase() throws InterruptedException {
		//1. validate the admin site loads after logging in
		assertEquals("SOLVTC101StudentChallengeImaginePhase", testName.getMethodName());
						
		//login with Organiztion admin single organization
		commonFunctions.loginWithUser("user", "pass");
		
		//create a class for the organization
		generalElements.clickElement("href","#organizations");
		generalElements.clickElementByXpath("//*[@id=\"admin-tabs-tabpane-organizations\"]/div/div/table/tbody/tr[2]/td[1]/a");
		commonFunctions.createClass("AutomationTest");
		
		//go to add users
		generalElements.clickElement("id","admin-tabs-tab-users");
		generalElements.clickElementByXpath("//button[.=' Add User']");
		generalElements.clickElementByXpath("//a[.='Add user']");

		
		// add both users
		//generalElements.sendKeysToElement("placeholder", "Enter word...", randomText1);
		generalElements.sendKeysToElementByXpath("//input[@placeholder='Search for user by name or email...']", "bramirez+automationuser@massiveu.com");
		
		generalElements.clickElementByXpath("//input[@class='form-check-input']");
		generalElements.clickElementByXpath("//button[@class='btn btn-primary']");
		
		
		//Go to challenges and create a new one
		commonFunctions.assignChallenge("h5", "challenge 2 8 reg");

		//logout
		generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
		generalElements.clickElement("href","/logout");
		
								
		//login with Organiztion admin single organization
		commonFunctions.loginWithUser("user", "pass");
		
		//join the challenge without a team password
		generalElements.clickElementByXpath("//a[.='Start']");
		
		
		// join challenge and go to challenge hub
		generalElements.clickElementByXpath("//button[.='Close']");
		generalElements.clickElementByXpath("//button[.='Solve Now']");
		
		//go through discover phase
		String randomText1 = "adding";
		String randomText2 = "different";
		String randomText3 = "words";
		String randomText4 = "to";
		String randomText5 = "cloud";
		String randomText6 = "and";
		String randomText7 = "verify";
		String randomText8 = "it";
		String randomText9 = "works";
		String randomText10 = "perfect";
		commonFunctions.completeDiscoveryPhase(randomText1, randomText2, randomText3, randomText4, randomText5, 
				randomText6, randomText7, randomText8, randomText9, randomText10);
		
		//submit the phase and verify you are taken to new phase
		commonFunctions.submitPhase("Discover", "Explore");
		
		generalElements.verifyButtonDisabled("//button[.='Submit Explore']");
		
		//complete explore phase card
		commonFunctions.completeExplorePhase();
		
		//submit the phase and verify you are taken to new phase
		commonFunctions.submitPhase("Explore", "Imagine");
		
		generalElements.verifyButtonDisabled("//button[.='Submit Imagine']");
		
		//complete all imagine phase cards
		commonFunctions.completeImaginePhase();
		
		//unselect an idea and verify complete phase is disabled
		generalElements.collectElementsByXpath("//div[@class='idea-card  white selected card']").get(0).click();
		generalElements.verifyButtonDisabled("//button[.='Submit Imagine']");
		
		
		//select an idea and verify submit is enabled
		generalElements.collectElementsByXpath("//div[@class='idea-card  white  card']").get(0).click();
		generalElements.collectElementsByXpath("//button[.='Submit Imagine']").get(0).isEnabled();
		
		//go back to first card
		generalElements.clickElementByXpath("//div[.='Brainstorm']");
		
		
		//submit the phase and verify you are taken to new phase
		commonFunctions.submitPhase("Imagine", "Create");
		
		generalElements.verifyButtonDisabled("//button[.='Submit Create']");
		
		//logout
		generalElements.collectElementsByXpath("//button[@aria-label='back to challenge overview']").get(0).click();
		
		generalElements.collectElementsByXpath("//button[@aria-label='back to challenge overview']").get(0).click();
		generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
		generalElements.clickElement("href","/logout");
		
		//login with Organiztion admin single organization
		commonFunctions.loginWithUser("user", "pass");
		
		//go back to organization and delete the class created
		commonFunctions.loginAndDeleteOrganizationClassWith1Class("Canvas1");
			
	}
	
	//SOLV-TC-102 Student Challenge Create Phase
	//@Test
		public void SOLVTC102StudentChallengeCreatePhase() throws InterruptedException {
			//1. validate the admin site loads after logging in
			assertEquals("SOLVTC102StudentChallengeCreatePhase", testName.getMethodName());
							
							
			//login with Organiztion admin single organization
			commonFunctions.loginWithUser("user", "pass");
			
			//create a class for the organization
			generalElements.clickElement("href","#organizations");
			generalElements.clickElementByXpath("//*[@id=\"admin-tabs-tabpane-organizations\"]/div/div/table/tbody/tr[2]/td[1]/a");
			commonFunctions.createClass("AutomationTest");
			
			//go to add users
			generalElements.clickElement("id","admin-tabs-tab-users");
			generalElements.clickElementByXpath("//button[.=' Add User']");
			generalElements.clickElementByXpath("//a[.='Add user']");

			
			// add both users
			generalElements.sendKeysToElementByXpath("//input[@placeholder='Search for user by name or email...']", "bramirez+automationuser@massiveu.com");
			
			generalElements.clickElementByXpath("//input[@class='form-check-input']");
			generalElements.clickElementByXpath("//button[@class='btn btn-primary']");
			
			
			//Go to challenges and create a new one
			commonFunctions.assignChallenge("h5", "challenge 2 8 reg");

			//logout
			generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
			generalElements.clickElement("href","/logout");
			
									
			//login with Organiztion admin single organization
			commonFunctions.loginWithUser("user", "pass");
			
			//join the challenge without a team password
			generalElements.clickElementByXpath("//a[.='Start']");
			
			
			// join challenge and go to challenge hub
			generalElements.clickElementByXpath("//button[.='Close']");
			generalElements.clickElementByXpath("//button[.='Solve Now']");
			
			//go through discover phase
			String randomText1 = "adding";
			String randomText2 = "different";
			String randomText3 = "words";
			String randomText4 = "to";
			String randomText5 = "cloud";
			String randomText6 = "and";
			String randomText7 = "verify";
			String randomText8 = "it";
			String randomText9 = "works";
			String randomText10 = "perfect";
			commonFunctions.completeDiscoveryPhase(randomText1, randomText2, randomText3, randomText4, randomText5, 
					randomText6, randomText7, randomText8, randomText9, randomText10);
			
			//submit the phase and verify you are taken to new phase
			commonFunctions.submitPhase("Discover", "Explore");
			
			generalElements.verifyButtonDisabled("//button[.='Submit Explore']");
			
			//complete explore phase card
			commonFunctions.completeExplorePhase();
			
			//submit the phase and verify you are taken to new phase
			commonFunctions.submitPhase("Explore", "Imagine");
			
			generalElements.verifyButtonDisabled("//button[.='Submit Imagine']");
			
			//complete all imagine phase cards
			commonFunctions.completeImaginePhase();
			
			//submit the phase and verify you are taken to new phase
			commonFunctions.submitPhase("Imagine", "Create");
			
			generalElements.verifyButtonDisabled("//button[.='Submit Create']");
			
			//input all required fields for imagine
			commonFunctions.completeCreatePhase();
			
			//go back to first card
			generalElements.clickElementByXpath("//div[.='Action Statement']");
			
			//return to card 1 remove words from one required field and verify submit phase is disabled
			generalElements.collectElementsByXpath("//textarea[@class='form-control']").get(0).clear();
			
			generalElements.collectElementsByXpath("//textarea[@class='form-control']").get(0).sendKeys("test");
			
			generalElements.verifyButtonDisabled("//button[.='Submit Create']");
			
			//input required field again and verify submit button is enabled
			generalElements.collectElementsByXpath("//textarea[@class='form-control']").get(0).sendKeys("filling required words over here");
			
			generalElements.collectElementsByXpath("//button[.='Submit Create']").get(0).isEnabled();
			
			//submit the phase and verify you are taken to new phase
			commonFunctions.submitPhase("Create", "Reflect");
			
			generalElements.verifyButtonDisabled("//button[.='Submit Reflect']");
			
			//logout
			generalElements.collectElementsByXpath("//button[@aria-label='back to challenge overview']").get(0).click();
			generalElements.collectElementsByXpath("//button[@aria-label='back to challenge overview']").get(0).click();
			generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
			generalElements.clickElement("href","/logout");
			
			//login with Organiztion admin single organization
			commonFunctions.loginWithUser("user", "pass");
			
			//go back to organization and delete the class created
			commonFunctions.loginAndDeleteOrganizationClassWith1Class("Canvas1");
			
		}
		
		//SOLV-TC-103 Student Challenge Reflect Phase
		//@Test
		public void SOLVTC103StudentChallengeReflectPhase() throws InterruptedException {
			//1. validate the admin site loads after logging in
			assertEquals("SOLVTC103StudentChallengeReflectPhase", testName.getMethodName());
							
							
			//login with Organiztion admin single organization
			commonFunctions.loginWithUser("user", "pass");
			
			//create a class for the organization
			generalElements.clickElement("href","#organizations");
			generalElements.clickElementByXpath("//*[@id=\"admin-tabs-tabpane-organizations\"]/div/div/table/tbody/tr[2]/td[1]/a");
			commonFunctions.createClass("AutomationTest");
			
			//go to add users
			generalElements.clickElement("id","admin-tabs-tab-users");
			generalElements.clickElementByXpath("//button[.=' Add User']");
			generalElements.clickElementByXpath("//a[.='Add user']");

			
			// add both users
			//generalElements.sendKeysToElement("placeholder", "Enter word...", randomText1);
			generalElements.sendKeysToElementByXpath("//input[@placeholder='Search for user by name or email...']", "bramirez+automationuser@massiveu.com");
			
			generalElements.clickElementByXpath("//input[@class='form-check-input']");
			generalElements.clickElementByXpath("//button[@class='btn btn-primary']");
			
			
			//Go to challenges and create a new one
			commonFunctions.assignChallenge("h5", "challenge 2 8 reg");

			//logout
			generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
			
			generalElements.clickElement("href","/logout");
			
									
			//login with Organiztion admin single organization
			commonFunctions.loginWithUser("user", "pass");
			
			//join the challenge without a team password
			generalElements.clickElementByXpath("//a[.='Start']");
			
			
			// join challenge and go to challenge hub
			generalElements.clickElementByXpath("//button[.='Close']");
			generalElements.clickElementByXpath("//button[.='Solve Now']");
			
			//go through discover phase
			String randomText1 = "adding";
			String randomText2 = "different";
			String randomText3 = "words";
			String randomText4 = "to";
			String randomText5 = "cloud";
			String randomText6 = "toverify";
			String randomText7 = "it";
			String randomText8 = "works";
			String randomText9 = "perfect";
			String randomText10 = "yay";
			commonFunctions.completeDiscoveryPhase(randomText1, randomText2, randomText3, randomText4, randomText5, 
					randomText6, randomText7, randomText8, randomText9, randomText10);
			
			//submit the phase and verify you are taken to new phase
			commonFunctions.submitPhase("Discover", "Explore");
			
			generalElements.verifyButtonDisabled("//button[.='Submit Explore']");
			
			//complete explore phase card
			commonFunctions.completeExplorePhase();
			
			//submit the phase and verify you are taken to new phase
			//commonFunctions.submitPhase("Explore", "Imagine");
			generalElements.clickElementByXpath("//button[.='Submit Explore']");
			
			//submit the phase and verify you are taken to new phase
			generalElements.clickElementByXpath("//button[.='Submit']");
			generalElements.clickElementByXpath("//button[.='Cancel Submit']");
			
			generalElements.clickElementByXpath("//button[.='Submit Explore']");
			generalElements.clickElementByXpath("//button[.='Submit']");
			Thread.sleep(12000);
			
			//verify you are on completion screen
			generalElements.verifyElementIsVisableOrNotVisable("id", "phase-celebration-content", true);
			generalElements.findText("Submitted by");
			generalElements.findText("Contributions");
			generalElements.collectElementsByXpath("//button[.='Continue']").get(0).click();
			
			//continue to next phase
			generalElements.clickElementByXpath("//button[.='Continue to Imagine']");
			
			generalElements.verifyButtonDisabled("//button[.='Submit Imagine']");
			
			//complete all imagine phase cards
			commonFunctions.completeImaginePhase();
			
			//submit the phase and verify you are taken to new phase
			commonFunctions.submitPhase("Imagine", "Create");
			
			generalElements.verifyButtonDisabled("//button[.='Submit Create']");
			
			//input all required fields for imagine
			commonFunctions.completeCreatePhase();
			generalElements.collectElementsByXpath("//button[.='Submit Create']").get(0).isEnabled();
			
			//submit the phase and verify you are taken to new phase
			commonFunctions.submitPhase("Create", "Reflect");
			
			generalElements.verifyButtonDisabled("//button[.='Submit Reflect']");
			
			//complete all 4 cards
			WebElement element = myDriver.findElement(By.xpath("//button[.='Next']"));
			((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
			generalElements.collectElementsByXpath("//input[@class='radio-button']").get(3).click();
			generalElements.clickElementByXpath("//button[.='Next']");
			
			
			element = myDriver.findElement(By.xpath("//h4[.='Activity']"));
			((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
			generalElements.collectElementsByXpath("//input[@class='radio-button']").get(3).click();
			generalElements.clickElementByXpath("//button[.='Next']");
			
			
			element = myDriver.findElement(By.xpath("//h4[.='Activity']"));
			((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
			generalElements.collectElementsByXpath("//input[@class='radio-button']").get(3).click();
			generalElements.clickElementByXpath("//button[.='Next']");
			
			
			element = myDriver.findElement(By.xpath("//input[@class='radio-button']"));
			((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
			generalElements.collectElementsByXpath("//input[@class='radio-button']").get(3).click();

			
			//logout
			generalElements.collectElementsByXpath("//button[@aria-label='back to challenge overview']").get(0).click();

			generalElements.collectElementsByXpath("//button[@aria-label='back to challenge overview']").get(0).click();
			generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
			generalElements.clickElement("href","/logout");
			
			//login with Organiztion admin single organization
			commonFunctions.loginWithUser("user", "pass");
			
			//go back to organization and delete the class created
			commonFunctions.loginAndDeleteOrganizationClassWith1Class("Canvas1");
			
			
		}
		
		//SOLV-TC-98 Student challenge navigation links
		@Test
		public void SOLVTC98StudentChallengeNavigationLinks() throws InterruptedException {
			//1. validate the admin site loads after logging in
			assertEquals("SOLVTC98StudentChallengeNavigationLinks", testName.getMethodName());

							
			//login with Organiztion admin single organization
			commonFunctions.loginWithUser("user", "pass");
			
			//create a class for the organization
			generalElements.clickElement("href","#organizations");
			generalElements.clickElementByXpath("//*[@id=\"admin-tabs-tabpane-organizations\"]/div/div/table/tbody/tr[2]/td[1]/a");
			commonFunctions.createClass("AutomationTest");
			
			//go to add users
			generalElements.clickElement("id","admin-tabs-tab-users");
			generalElements.clickElementByXpath("//button[.=' Add User']");
			generalElements.clickElementByXpath("//a[.='Add user']");

			
			// add both users
			generalElements.sendKeysToElementByXpath("//input[@placeholder='Search for user by name or email...']", "bramirez+automationuser@massiveu.com");
			
			generalElements.clickElementByXpath("//input[@class='form-check-input']");
			generalElements.clickElementByXpath("//button[@class='btn btn-primary']");
			
			
			//Go to challenges and create a new one
			commonFunctions.assignChallenge("h5", "challenge 2 8 reg");

			//logout
			generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
			generalElements.clickElement("href","/logout");
			
									
			//login with Organiztion admin single organization
			commonFunctions.loginWithUser("user", "pass");
			
			//join the challenge without a team password
			generalElements.clickElementByXpath("//a[.='Start']");
			
					
			// join challenge and go to challenge hub
			generalElements.clickElementByXpath("//button[.='Close']");
			generalElements.clickElementByXpath("//button[.='Solve Now']");
			generalElements.clickElementByXpath("//button[@class='btn-close']");
			
			//return to syllabus
			generalElements.clickElementByXpath("//button[@aria-label='back to challenge overview']");
			generalElements.findText("Overview");
			
			//return to challenge
			generalElements.clickElementByXpath("//h5[.='Solve Now']");
			generalElements.findText("My activity");
			
			//logout
			generalElements.clickElementByXpath("//button[@aria-label='back to challenge overview']");
			generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
			generalElements.clickElement("href","/logout");
			
			//login with Organiztion admin single organization
			commonFunctions.loginWithUser("user", "pass");
			
			//go back to organization and delete the class created
			commonFunctions.loginAndDeleteOrganizationClassWith1Class("Canvas1");
					
		}
		
		//SOLV-TC-76 Instructor grading
		//@Test
		public void SOLVTC76InstructorGrading() throws InterruptedException {
					//1. validate the admin site loads after logging in
					assertEquals("SOLVTC76InstructorGrading", testName.getMethodName());
									
									
					//login with Organiztion admin single organization
					commonFunctions.loginWithUser("user", "pass");
					
					//create a class for the organization
					generalElements.clickElement("href","#organizations");
					generalElements.clickElementByXpath("//*[@id=\"admin-tabs-tabpane-organizations\"]/div/div/table/tbody/tr[2]/td[1]/a");
					commonFunctions.createClass("AutomationTest");
					
					//go to add users
					generalElements.clickElement("id","admin-tabs-tab-users");
					generalElements.clickElementByXpath("//button[.=' Add User']");
					generalElements.clickElementByXpath("//a[.='Add user']");

					
					// add both users
					generalElements.sendKeysToElementByXpath("//input[@placeholder='Search for user by name or email...']", "bramirez+automationuser@massiveu.com");
					
					generalElements.clickElementByXpath("//input[@class='form-check-input']");
					generalElements.clickElementByXpath("//button[@class='btn btn-primary']");
					
					generalElements.clickElementByXpath("//button[.=' Add User']");
					generalElements.clickElementByXpath("//a[.='Add user']");

					generalElements.sendKeysToElementByXpath("//input[@placeholder='Search for user by name or email...']", "bramirez+inst0003@massiveu.com");
					
					generalElements.clickElementByXpath("//input[@class='form-check-input']");
					generalElements.clickElementByXpath("//button[@class='btn btn-primary']");
					
					
					//Go to challenges and create a new one
					commonFunctions.assignChallenge("h5", "challenge 2 8 reg");

					//logout
					generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
					generalElements.clickElement("href","/logout");
					
											
					//login with Organiztion admin single organization
					commonFunctions.loginWithUser("user", "pass");
					
					//join the challenge without a team password
					generalElements.clickElementByXpath("//a[.='Start']");
					
					
					// join challenge and go to challenge hub
					generalElements.clickElementByXpath("//button[.='Close']");
					generalElements.clickElementByXpath("//button[.='Solve Now']");
					
					//go through discover phase
					String randomText1 = "adding";
					String randomText2 = "different";
					String randomText3 = "words";
					String randomText4 = "to";
					String randomText5 = "cloud";
					String randomText6 = "and";
					String randomText7 = "verify";
					String randomText8 = "it";
					String randomText9 = "works";
					String randomText10 = "perfect";
					commonFunctions.completeDiscoveryPhase(randomText1, randomText2, randomText3, randomText4, randomText5, 
							randomText6, randomText7, randomText8, randomText9, randomText10);
					
					//submit the phase and verify you are taken to new phase
					commonFunctions.submitPhase("Discover", "Explore");
					
					generalElements.verifyButtonDisabled("//button[.='Submit Explore']");
					
					//complete explore phase card
					commonFunctions.completeExplorePhase();
					
					//submit the phase and verify you are taken to new phase
					commonFunctions.submitPhase("Explore", "Imagine");
					
					generalElements.verifyButtonDisabled("//button[.='Submit Imagine']");
					
					//complete all imagine phase cards
					commonFunctions.completeImaginePhase();
					
					//submit the phase and verify you are taken to new phase
					commonFunctions.submitPhase("Imagine", "Create");
					
					generalElements.verifyButtonDisabled("//button[.='Submit Create']");
					
					//input all required fields for imagine
					commonFunctions.completeCreatePhase();
					generalElements.collectElementsByXpath("//button[.='Submit Create']").get(0).isEnabled();
					
					//submit the phase and verify you are taken to new phase
					commonFunctions.submitPhase("Create", "Reflect");
					
					generalElements.verifyButtonDisabled("//button[.='Submit Reflect']");
					
					//complete all 4 cards
					WebElement element = myDriver.findElement(By.xpath("//button[.='Next']"));
					((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
					generalElements.collectElementsByXpath("//input[@class='radio-button']").get(3).click();
					generalElements.clickElementByXpath("//button[.='Next']");
					
					
					element = myDriver.findElement(By.xpath("//h4[.='Activity']"));
					((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
					generalElements.collectElementsByXpath("//input[@class='radio-button']").get(3).click();
					generalElements.clickElementByXpath("//button[.='Next']");
					
					
					element = myDriver.findElement(By.xpath("//h4[.='Activity']"));
					((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
					generalElements.collectElementsByXpath("//input[@class='radio-button']").get(3).click();
					generalElements.clickElementByXpath("//button[.='Next']");
					
					
					element = myDriver.findElement(By.xpath("//input[@class='radio-button']"));
					((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
					generalElements.collectElementsByXpath("//input[@class='radio-button']").get(3).click();
					
					//submit the challenge
					generalElements.clickElementByXpath("//button[@aria-label='Submit Reflect']");
					generalElements.clickElementByXpath("//button[.='Submit']");
					Thread.sleep(11000);
					
					element = myDriver.findElement(By.xpath("//div[.='Discover']"));
					((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
					generalElements.clickElementByXpath("//button[.='Continue']");
					
					//logout
					generalElements.clickElementByXpath("//button[@aria-label='back to challenge overview']");
					generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
					generalElements.clickElement("href","/logout");
					
					//login as an instructor
					commonFunctions.loginWithUser("user", "pass");
					
					//Go to grade challenge
					generalElements.collectElementsByXpath("//span[@class='count text-white badge bg-danger']").get(0).click();
					generalElements.clickElementByXpath("//h6[.='challenge 2 8 reg']");
					
					//input scores and continue
					generalElements.collectElementsByXpath("//input[@name='response_1']").clear();
					generalElements.sendKeysToElementByXpath("//input[@name='response_1']", "10");
					generalElements.collectElementsByXpath("//input[@name='response_2']").clear();
					generalElements.sendKeysToElementByXpath("//input[@name='response_2']", "20");
					generalElements.collectElementsByXpath("//input[@name='response_3']").clear();
					generalElements.sendKeysToElementByXpath("//input[@name='response_3']", "10");
					generalElements.collectElementsByXpath("//input[@name='response_4']").clear();
					generalElements.sendKeysToElementByXpath("//input[@name='response_4']", "20");
					generalElements.collectElementsByXpath("//input[@name='response_5']").clear();
					generalElements.sendKeysToElementByXpath("//input[@name='response_5']", "10");
					
					//input text and submit
					generalElements.sendKeysToElementByXpath("//textarea[@name='notes']", "Testing feedback comment section deliverable");
					generalElements.findText("70");
					generalElements.clickElementByXpath("//button[.='Submit']");
					
					//Input learners 4c's
					generalElements.collectElementsByXpath("//button[@class='dropdown-toggle btn btn-learner-4c-selector']").get(0).click();
					generalElements.clickElementByXpath("//strong[.='Exemplary']");
					generalElements.collectElementsByXpath("//button[@class='dropdown-toggle btn btn-learner-4c-selector']").get(1).click();
					element = myDriver.findElement(By.xpath("//button[.='Submit']"));
					((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
					generalElements.collectElementsByXpath("//strong[.='Strong']").get(1).click();
					
					generalElements.collectElementsByXpath("//button[@class='dropdown-toggle btn btn-learner-4c-selector']").get(2).click();
					generalElements.collectElementsByXpath("//strong[.='Strong']").get(2).click();
					generalElements.collectElementsByXpath("//button[@class='dropdown-toggle btn btn-learner-4c-selector']").get(3).click();
					generalElements.collectElementsByXpath("//strong[.='Exemplary']").get(3).click();
					
					//input text and submit
					generalElements.sendKeysToElementByXpath("//textarea[@name='notes']", "Testing feedback comment section 4c");
					generalElements.clickElementByXpath("//button[.='Submit']");
					
					//verify scores and comment
					
					generalElements.findText("Testing feedback comment section deliverable");
					generalElements.findText("94");
					generalElements.findText("8");
					
					//verify bottom table scores
					element = myDriver.findElement(By.xpath("//strong[.='View Team Challenge']"));
					((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
					generalElements.findText("Testing feedback comment section 4c");
					generalElements.findText("70");
					generalElements.findText("93.75");
					generalElements.collectElementsByXpath("//b[.='81.88%']").get(0).isDisplayed();
					generalElements.collectElementsByXpath("//input[@value='81.88']").get(0).isDisplayed();
					
					generalElements.clickElementByXpath("//button[.='Submit']");
					
					//verify scores have been submitted and logout
					generalElements.findText("Success");
					generalElements.findText("challenge 2 8 reg");
					
					generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(1).click();
					generalElements.clickElement("href","/logout");
					
					//login with admin
					commonFunctions.loginWithUser("user", "pass");
					
					//go back to organization and delete the class created
					commonFunctions.loginAndDeleteOrganizationClassWith1Class("Canvas1");
					
					
				}
				
				//validate all file upload types
				//@Test
					public void TestUploadFileTypes() throws InterruptedException, AWTException {
						//1. validate the admin site loads after logging in
						assertEquals("TestUploadFileTypes", testName.getMethodName());
										
						//login with Organiztion admin single organization
						commonFunctions.loginWithUser("user", "pass");
						
						//create a class for the organization
						generalElements.clickElement("href","#organizations");
						generalElements.clickElementByXpath("//*[@id=\"admin-tabs-tabpane-organizations\"]/div/div/table/tbody/tr[2]/td[1]/a");
						commonFunctions.createClass("AutomationTest");
						
						//go to add users
						generalElements.clickElement("id","admin-tabs-tab-users");
						generalElements.clickElementByXpath("//button[.=' Add User']");
						generalElements.clickElementByXpath("//a[.='Add user']");

						
						// add both users
						generalElements.sendKeysToElementByXpath("//input[@placeholder='Search for user by name or email...']", "bramirez+automationuser@massiveu.com");
						
						generalElements.clickElementByXpath("//input[@class='form-check-input']");
						generalElements.clickElementByXpath("//button[@class='btn btn-primary']");
						
						
						//Go to challenges and create a new one
						commonFunctions.assignChallenge("h5", "challenge 2 8 reg");

						//logout
						generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
						generalElements.clickElement("href","/logout");
						
												
						//login with Organiztion admin single organization
						commonFunctions.loginWithUser("user", "pass");
						
						//join the challenge without a team password
						generalElements.clickElementByXpath("//a[.='Start']");
						
						
						// join challenge and go to challenge hub
						generalElements.clickElementByXpath("//button[.='Close']");
						generalElements.clickElementByXpath("//button[.='Solve Now']");
						
						//go through discover phase
						String randomText1 = "adding";
						String randomText2 = "different";
						String randomText3 = "words";
						String randomText4 = "to";
						String randomText5 = "cloud";
						String randomText6 = "and";
						String randomText7 = "verify";
						String randomText8 = "it";
						String randomText9 = "works";
						String randomText10 = "perfect";
						commonFunctions.completeDiscoveryPhase(randomText1, randomText2, randomText3, randomText4, randomText5, 
								randomText6, randomText7, randomText8, randomText9, randomText10);
						
						//submit the phase and verify you are taken to new phase
						commonFunctions.submitPhase("Discover", "Explore");
						generalElements.verifyButtonDisabled("//button[.='Submit Explore']");
						
						//complete explore phase card
						commonFunctions.completeExplorePhase();
						
						//submit the phase and verify you are taken to new phase
						commonFunctions.submitPhase("Explore", "Imagine");
						generalElements.verifyButtonDisabled("//button[.='Submit Imagine']");
						
						//complete all imagine phase cards
						commonFunctions.completeImaginePhase();
						
						//submit the phase and verify you are taken to new phase
						commonFunctions.submitPhase("Imagine", "Create");
						generalElements.verifyButtonDisabled("//button[.='Submit Create']");
						
						//fill required info for card 1 and continue
						generalElements.collectElementsByXpath("//textarea[@class='form-control']").get(0).sendKeys("filling required words over here");	
						generalElements.collectElementsByXpath("//textarea[@class='form-control']").get(1).sendKeys("filling required words over here");						
						generalElements.collectElementsByXpath("//textarea[@class='form-control']").get(2).sendKeys("filling required words over here");
						
						
						//go to next card
						WebElement element = myDriver.findElement(By.xpath("//button[.='Next']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						
						generalElements.clickElementByXpath("//button[.='Next']");
						
						//fill in required info for card 2
						element = generalElements.collectElementsByXpath("//button[@aria-label='View deliverable details']").get(0);
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						
						//add google slides
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						generalElements.sendKeysToElementByXpath("//input[@placeholder='Paste a link']", "https://docs.google.com/presentation/d/1EPJtu4cGXHg8tFgJB8xiXeg5GGlZC40W52JTAby1bqY/edit?usp=sharing");
						generalElements.clickElementByXpath("//div[@class='input-group']//button[@class='btn btn-type']");
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add google docs
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.clickElementByXpath("//button[@aria-label='View deliverable details']");
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.sendKeysToElementByXpath("//input[@placeholder='Paste a link']", "https://docs.google.com/document/d/1BUVeQTPYlxv0MTpzPoe-qBB6ua_j-uHZDu_k1FaRNp4/edit?usp=sharing");
						generalElements.clickElementByXpath("//div[@class='input-group']//button[@class='btn btn-type']");
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add google sheets
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.sendKeysToElementByXpath("//input[@placeholder='Paste a link']", "https://docs.google.com/spreadsheets/d/1S-IK5cfcThc5LjNeSOawFAyp2c7MX4aRBfgoNgkYdYQ/edit?usp=sharing");
						generalElements.clickElementByXpath("//div[@class='input-group']//button[@class='btn btn-type']");
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add powtoon
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.sendKeysToElementByXpath("//input[@placeholder='Paste a link']", "<iframe width=\'480\' height=\'270\' src=\'https://www.powtoon.com/embed/g6dnaPM1aGm/\' frameborder=\'0\' allowfullscreen></iframe>");
						generalElements.clickElementByXpath("//div[@class='input-group']//button[@class='btn btn-type']");
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add canva
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.sendKeysToElementByXpath("//input[@placeholder='Paste a link']", "https://www.canva.com/design/DAEwHV4Q1rQ/view");
						generalElements.clickElementByXpath("//div[@class='input-group']//button[@class='btn btn-type']");
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add spark
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.sendKeysToElementByXpath("//input[@placeholder='Paste a link']", "https://express.adobe.com/page/qTUuLNVwwm5Zp/");
						generalElements.clickElementByXpath("//div[@class='input-group']//button[@class='btn btn-type']");
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add youtube
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.sendKeysToElementByXpath("//input[@placeholder='Paste a link']", "https://youtu.be/kOYS9lX2pgg");
						generalElements.clickElementByXpath("//div[@class='input-group']//button[@class='btn btn-type']");
						
						generalElements.clickElementByXpath("//button[.='Submit']");

						//add vimeo
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.sendKeysToElementByXpath("//input[@placeholder='Paste a link']", "https://vimeo.com/577635596");
						generalElements.clickElementByXpath("//div[@class='input-group']//button[@class='btn btn-type']");
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add bandcamp
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.sendKeysToElementByXpath("//input[@placeholder='Paste a link']", "<iframe style=\"border: 0; width: 350px; height: 470px;\" src=\"https://bandcamp.com/EmbeddedPlayer/album=92637734/size=large/bgcol=ffffff/linkcol=0687f5/tracklist=false/track=1845445054/transparent=true/\" seamless><a href=\"https://artpepper.bandcamp.com/album/patricia-free\">PATRICIA (FREE) by ART PEPPER</a></iframe>");
						generalElements.clickElementByXpath("//div[@class='input-group']//button[@class='btn btn-type']");
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add prezi
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.sendKeysToElementByXpath("//input[@placeholder='Paste a link']", "https://prezi.com/i/zdpbus0vze2m/test/");
						generalElements.clickElementByXpath("//div[@class='input-group']//button[@class='btn btn-type']");
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add sound cloud
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.sendKeysToElementByXpath("//input[@placeholder='Paste a link']", "<iframe width=\"100%\" height=\"300\" scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/255052488&color=%23ff5500&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true\"></iframe><div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"><a href=\"https://soundcloud.com/tammi-terrell\" title=\"Tammi Terrell\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">Tammi Terrell</a> ï¿½ <a href=\"https://soundcloud.com/tammi-terrell/aint-no-mountain-high-enough-1\" title=\"Ain&#x27;t No Mountain High Enough\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">Ain&#x27;t No Mountain High Enough</a></div>");
						generalElements.clickElementByXpath("//div[@class='input-group']//button[@class='btn btn-type']");
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						
						//add jpg
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.clickElementByXpath("//div[.='Select your file']");
						String workingDir = System.getProperty("user.dir") + "\\File Samples\\sample.jpg";
						
						
						StringSelection ss = new StringSelection(workingDir);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
										
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add jpeg
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.clickElementByXpath("//div[.='Select your file']");
						workingDir = System.getProperty("user.dir") + "\\File Samples\\sample.jpeg";
						
						
						ss = new StringSelection(workingDir);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

						robot = new Robot();
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
										
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add jfif
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.clickElementByXpath("//div[.='Select your file']");
						workingDir = System.getProperty("user.dir") + "\\File Samples\\sample.jfif";
						
						
						ss = new StringSelection(workingDir);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

						robot = new Robot();
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
										
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add mp3
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.clickElementByXpath("//div[.='Select your file']");
						workingDir = System.getProperty("user.dir") + "\\File Samples\\sample.mp3";
						
						
						ss = new StringSelection(workingDir);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

						robot = new Robot();
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
										
						
						generalElements.clickElementByXpath("//button[.='Submit']");

						//add mp4
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.clickElementByXpath("//div[.='Select your file']");
						workingDir = System.getProperty("user.dir") + "\\File Samples\\sample.mp4";
						
						
						ss = new StringSelection(workingDir);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

						robot = new Robot();
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
										
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add ogg
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.clickElementByXpath("//div[.='Select your file']");
						workingDir = System.getProperty("user.dir") + "\\File Samples\\sample.ogg";
						
						
						ss = new StringSelection(workingDir);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

						robot = new Robot();
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
										
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add pdf
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.clickElementByXpath("//div[.='Select your file']");
						workingDir = System.getProperty("user.dir") + "\\File Samples\\sample.pdf";
						
						
						ss = new StringSelection(workingDir);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

						robot = new Robot();
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
										
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add png
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						//generalElements.clickElementByXpath("//button[@aria-label='View deliverable details']");
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.clickElementByXpath("//div[.='Select your file']");
						workingDir = System.getProperty("user.dir") + "\\File Samples\\sample.png";
						
						
						ss = new StringSelection(workingDir);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

						robot = new Robot();
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
										
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add wav
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						//generalElements.clickElementByXpath("//button[@aria-label='View deliverable details']");
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.clickElementByXpath("//div[.='Select your file']");
						workingDir = System.getProperty("user.dir") + "\\File Samples\\sample.wav";
						
						
						ss = new StringSelection(workingDir);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

						robot = new Robot();
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
										
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//add webm
						
						element = myDriver.findElement(By.xpath("//h4[.='Instructions']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
						//accept popup to remove old deliverable
						myDriver.switchTo().alert().accept();
						generalElements.clickElementByXpath("//div[.='Select your file']");
						workingDir = System.getProperty("user.dir") + "\\File Samples\\sample.webm";
						
						
						ss = new StringSelection(workingDir);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

						robot = new Robot();
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
										
						
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						
						generalElements.collectElementsByXpath("//button[.='Submit Create']").get(0).isEnabled();
						
						//logout
						generalElements.collectElementsByXpath("//button[@aria-label='back to challenge overview']").get(0).click();
						
						generalElements.collectElementsByXpath("//button[@aria-label='back to challenge overview']").get(0).click();
						generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
						generalElements.clickElement("href","/logout");
						
						//login with Organiztion admin single organization
						commonFunctions.loginWithUser("user", "pass");
						
						//go back to organization and delete the class created
						commonFunctions.loginAndDeleteOrganizationClassWith1Class("Canvas1");
						
					}
					
					//Start to end complete a course
					//@Test
					public void SOLVTC131CompleteACourse() throws InterruptedException {
						//1. validate the admin site loads after logging in
						assertEquals("SOLVTC131CompleteACourse", testName.getMethodName());
										
										
						//login with Organiztion admin single organization
						commonFunctions.loginWithUser("user", "pass");
						
						//create a class for the organization
						generalElements.clickElement("href","#organizations");
						generalElements.clickElementByXpath("//*[@id=\"admin-tabs-tabpane-organizations\"]/div/div/table/tbody/tr[2]/td[1]/a");
						commonFunctions.createClass("AutomationTest");
						
						//go to add users
						generalElements.clickElement("id","admin-tabs-tab-users");
						generalElements.clickElementByXpath("//button[.=' Add User']");
						generalElements.clickElementByXpath("//a[.='Add user']");

						
						// add both users
						//generalElements.sendKeysToElement("placeholder", "Enter word...", randomText1);
						generalElements.sendKeysToElementByXpath("//input[@placeholder='Search for user by name or email...']", "bramirez+automationuser@massiveu.com");
						
						generalElements.clickElementByXpath("//input[@class='form-check-input']");
						generalElements.clickElementByXpath("//button[@class='btn btn-primary']");
						
						
						//Go to challenges and create a new one
						commonFunctions.assignCourse("h5", "Accountability");

						//logout
						
						commonFunctions.Logout();
						
												
						//login with Organiztion admin single organization
						commonFunctions.loginWithUser("user", "pass");
						
						//join the challenge without a team password
						generalElements.clickElementByXpath("//a[.='Start']");
						
						
						
						// join challenge and go to course
						generalElements.clickElementByXpath("//button[.='Start Course']");
						
						//complete course
						commonFunctions.completeCourse();
						
						//verify course is completed
						
						generalElements.findText("Course Complete");
						
						//logout
						
						commonFunctions.Logout();

						
						//login with Organiztion admin single organization
						commonFunctions.loginWithUser("user", "pass");
						
						//go back to organization and delete the class created
						commonFunctions.loginAndDeleteOrganizationClassWith1Class("Canvas1");
					}
					
					//@Test
					public void SOLVTC131CompleteAJourney() throws InterruptedException {
						//1. validate the admin site loads after logging in
						assertEquals("SOLVTC131CompleteAJourney", testName.getMethodName());
										
						//login with Organiztion admin single organization
						commonFunctions.loginWithUser("user", "pass");
						
						//create a class for the organization
						generalElements.clickElement("href","#organizations");
						generalElements.clickElementByXpath("//*[@id=\"admin-tabs-tabpane-organizations\"]/div/div/table/tbody/tr[2]/td[1]/a");
						commonFunctions.createClass("AutomationTest");
						
						//go to add users
						generalElements.clickElement("id","admin-tabs-tab-users");
						generalElements.clickElementByXpath("//button[.=' Add User']");
						generalElements.clickElementByXpath("//a[.='Add user']");

						
						// add both users
						//generalElements.sendKeysToElement("placeholder", "Enter word...", randomText1);
						generalElements.sendKeysToElementByXpath("//input[@placeholder='Search for user by name or email...']", "bramirez+automationuser@massiveu.com");
						
						generalElements.clickElementByXpath("//input[@class='form-check-input']");
						generalElements.clickElementByXpath("//button[@class='btn btn-primary']");
						
						
						//Go to challenges and create a new one
						commonFunctions.assignCourseAndChallenge("h5", "Accountability");

						//logout
						
						commonFunctions.Logout();
						
												
						//login with Organiztion admin single organization
						commonFunctions.loginWithUser("user", "pass");
						
						//join the challenge without a team password
						generalElements.clickElementByXpath("//a[.='Start']");
						
						
						
						// join journey and go to course
						generalElements.clickElementByXpath("//button[.='Start Journey']");
						generalElements.clickElementByXpath("//button[.='Start Course']");
						
						//complete course
						commonFunctions.completeCourse();
						
						//return to journey and go to challenge
						generalElements.clickElementByXpath("//span[.='Regression Journey']");
						generalElements.clickElementByXpath("//button[.='Resume Journey']");
						
						
						// join challenge and go to challenge hub
						generalElements.clickElementByXpath("//button[.='Close']");
						generalElements.clickElementByXpath("//button[.='Solve Now']");
						
						//complete the challenge
						//go through discover phase
						String randomText1 = "adding";
						String randomText2 = "different";
						String randomText3 = "words";
						String randomText4 = "to";
						String randomText5 = "cloud";
						String randomText6 = "toverify";
						String randomText7 = "it";
						String randomText8 = "works";
						String randomText9 = "perfect";
						String randomText10 = "yay";
						commonFunctions.completeDiscoveryPhase(randomText1, randomText2, randomText3, randomText4, randomText5, 
								randomText6, randomText7, randomText8, randomText9, randomText10);
						
						//submit the phase and verify you are taken to new phase
						commonFunctions.submitPhase("Discover", "Explore");
						
						generalElements.verifyButtonDisabled("//button[.='Submit Explore']");
						
						//complete explore phase card
						commonFunctions.completeExplorePhase();
						
						//submit the phase and verify you are taken to new phase
						generalElements.clickElementByXpath("//button[.='Submit Explore']");
						
						//submit the phase and verify you are taken to new phase
						generalElements.clickElementByXpath("//button[.='Submit']");
						generalElements.clickElementByXpath("//button[.='Cancel Submit']");
						
						generalElements.clickElementByXpath("//button[.='Submit Explore']");
						generalElements.clickElementByXpath("//button[.='Submit']");
						
						//verify you are on completion screen
						generalElements.verifyElementIsVisableOrNotVisable("id", "phase-celebration-content", true);
						generalElements.findText("Submitted by");
						generalElements.findText("Contributions");
						generalElements.collectElementsByXpath("//button[.='Continue']").get(0).click();
						
						//continue to next phase
						generalElements.clickElementByXpath("//button[.='Continue to Imagine']");
						
						generalElements.verifyButtonDisabled("//button[.='Submit Imagine']");
						
						//complete all imagine phase cards
						commonFunctions.completeImaginePhase();
						
						//submit the phase and verify you are taken to new phase
						commonFunctions.submitPhase("Imagine", "Create");
						
						generalElements.verifyButtonDisabled("//button[.='Submit Create']");
						
						//input all required fields for imagine
						commonFunctions.completeCreatePhase();
						generalElements.collectElementsByXpath("//button[.='Submit Create']").get(0).isEnabled();
						
						//submit the phase and verify you are taken to new phase
						commonFunctions.submitPhase("Create", "Reflect");
						
						generalElements.verifyButtonDisabled("//button[.='Submit Reflect']");
						
						//complete all 4 cards
						WebElement element = myDriver.findElement(By.xpath("//button[.='Next']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//input[@class='radio-button']").get(3).click();
						generalElements.clickElementByXpath("//button[.='Next']");
						
						
						element = myDriver.findElement(By.xpath("//h4[.='Activity']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//input[@class='radio-button']").get(3).click();
						generalElements.clickElementByXpath("//button[.='Next']");
						
						
						element = myDriver.findElement(By.xpath("//h4[.='Activity']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//input[@class='radio-button']").get(3).click();
						generalElements.clickElementByXpath("//button[.='Next']");
						
						
						element = myDriver.findElement(By.xpath("//input[@class='radio-button']"));
						((JavascriptExecutor)myDriver).executeScript("arguments[0].scrollIntoView();", element);
						generalElements.collectElementsByXpath("//input[@class='radio-button']").get(3).click();
						
						//commonFunctions.submitPhase("Reflect", "Reflect");
						
						//logout
						generalElements.collectElementsByXpath("//button[@aria-label='back to challenge overview']").get(0).click();
						
						generalElements.collectElementsByXpath("//button[@aria-label='back to challenge overview']").get(0).click();
						generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
						generalElements.clickElement("href","/logout");
						
						//login with Organiztion admin single organization
						commonFunctions.loginWithUser("user", "pass");
						
						//go back to organization and delete the class created
						commonFunctions.loginAndDeleteOrganizationClassWith1Class("Canvas1");
					}
					
					//@Test
					public void CreateAndDeleteSubOrg() throws InterruptedException {
						//1. validate the admin site loads after logging in
						assertEquals("CreateAndDeleteSubOrg", testName.getMethodName());
										
						//login with Organiztion admin single organization
						commonFunctions.loginWithUser("user", "pass");
						
						//create a sub org for the organization
						generalElements.clickElement("href","#organizations");
						generalElements.clickElementByXpath("//*[@id=\"admin-tabs-tabpane-organizations\"]/div/div/table/tbody/tr[2]/td[1]/a");
						commonFunctions.createSubOrg("AutomationTestSubOrg");
						
						//return and delete sub org
						generalElements.clickElement("href","/admin/organization/25");
						commonFunctions.deleteSubOrg("AutomationTestSubOrg");
						
						//logout
						generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
						generalElements.clickElement("href","/logout");
						
						//login as org admin
						commonFunctions.loginWithUser("user", "pass");
						
						
						//create a sub org for the organization
						commonFunctions.createSubOrg("AutomationTestSubOrg");
						
						//return and delete sub org
						generalElements.clickElement("href","/home");
						commonFunctions.deleteSubOrg("AutomationTestSubOrg");
						
						//logout
						generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
						generalElements.clickElement("href","/logout");
						
					}
					
					//@Test
					public void TestForgotPassword() throws InterruptedException {
						//1. validate the admin site loads after logging in
						assertEquals("TestForgotPassword", testName.getMethodName());
										
						//click forgot password
						generalElements.clickElementByXpath("//a[.='Forgot password?']");
						generalElements.sendKeysToElement("aria-label","email","bramirez+adminusers1@massiveu.com");
						generalElements.clickElementByXpath("//button[.='Reset Password']");
						
						//verify forgot password message generates
						
						generalElements.findText("If an account exists with that email, you will receive a reset password link. If after 5 minutes you do not, try again with a different possible email.");
						
						//close window
						generalElements.clickElementByXpath("//button[@class='btn-close']");
						
					}
					
					//instructor dashboard links
					//@Test
					public void DashboardLinksInstructor() throws InterruptedException {
						//1. validate the admin site loads after logging in
						assertEquals("DashboardLinksInstructor", testName.getMethodName());
										
						//login with Organiztion admin single organization
						commonFunctions.loginWithUser("user", "pass");
						
						//verify you are on dashboard
						generalElements.findText("Learner Groups");
						
						//go to design lab and verify design lab loads
						generalElements.clickElement("href","/admin/challenge-templates");
						generalElements.findText("Recent");
						generalElements.findText("Shared");
						generalElements.findText("Created");
						
						//go to content and verify it loads
						generalElements.clickElement("href","/admin/content");
						generalElements.findText("Journeys");
						generalElements.findText("Courses");
						generalElements.findText("Challenges");
						generalElements.findText("Third Party");
						
						//go back to home and verify it loads
						generalElements.clickElement("href","/home");
						generalElements.findText("Groups");
						generalElements.findText("Assignments");
						
						//go to assignments and verify it loads
						generalElements.clickElement("href","#assignments");
						generalElements.findText("Active Assignments");
						
						//logout
						generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(1).click();
						generalElements.clickElement("href","/logout");
					}
	
	@After
	public void quit() throws InterruptedException {

		


	}

}
