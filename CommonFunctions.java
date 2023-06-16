package com.SolvablyAutoJava;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.security.SecureRandom;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunctions {
	public static GeneralElements generalElements;
	public Boolean createdClass = false;
	
	public CommonFunctions(WebDriver driver){
		this.driver = driver;
		generalElements = new GeneralElements(driver);
	}
	
	WebDriver driver;

	//logs into  with given user and pass
	public void loginWithUser(String user, String password) throws InterruptedException {
		generalElements.sendKeysToElement("name", "email_address", user);
		generalElements.clickElementByXpath("//button[.='Continue with email']");
		generalElements.clickElementByXpath("//button[.='Type password instead']");
		generalElements.sendKeysToElement("name", "password", password);
		generalElements.clickElementByXpath("//button[.='Log in']");
	}
	
	
	//create a team
	public void createTeam(String teamName, String teamPassword) throws InterruptedException {
		generalElements.clickElement("href", "#teams");
		generalElements.clickElementByXpath("//button[.='Add Team']");
		generalElements.sendKeysToElement("name", "name", teamName);
		generalElements.sendKeysToElement("name", "password", teamPassword);
		generalElements.clickElementByXpath("//button[.='Create Team']");
	}
	
	
	//create a challenge
	public void assignChallenge(String challengeElementType, String challengeElementTypeDefinition) throws InterruptedException {
		generalElements.clickElement("href", "#assignments");
		generalElements.clickElementByXpath("//button[.='New Assignment']");
		generalElements.clickElementByXpath("//button[.='Select Assignment...']");
		generalElements.clickElementByXpath("//button[.='Select Challenge']");
		generalElements.sendKeysToElement("placeholder", "Search by title...", "reg"+"\n");
		generalElements.clickElementByXpath("//"+challengeElementType+"[.='"+challengeElementTypeDefinition+"']");
		generalElements.collectElementsByXpath("//button[.='Select Challenge']").get(0).click();
		generalElements.clickElementByXpath("//button[.='Save']");
		generalElements.clickElementByXpath("//button[.='Next']");
		generalElements.clickElementByXpath("//button[.='Assign']");
		
		generalElements.clickElementByXpath("//button[@class='btn-auto-assign-teams btn btn-primary']");
		generalElements.clickElementByXpath("//button[.='Add Teams']");
		generalElements.clickElementByXpath("//button[.='Save changes']");

	}
	
	//create a challenge
		public void assignChallenge2Members(String challengeElementType, String challengeElementTypeDefinition) throws InterruptedException {
			generalElements.clickElement("href", "#assignments");
			generalElements.clickElementByXpath("//button[.='New Assignment']");
			generalElements.clickElementByXpath("//button[.='Select Assignment...']");
			generalElements.clickElementByXpath("//button[.='Select Challenge']");
			generalElements.sendKeysToElement("placeholder", "Search by title...", "reg"+"\n");
			generalElements.clickElementByXpath("//"+challengeElementType+"[.='"+challengeElementTypeDefinition+"']");
			generalElements.collectElementsByXpath("//button[.='Select Challenge']").get(0).click();
			generalElements.clickElementByXpath("//button[.='Save']");
			generalElements.clickElementByXpath("//button[.='Next']");
			generalElements.clickElementByXpath("//button[.='Assign']");
			
			generalElements.clickElementByXpath("//button[@class='btn-auto-assign-teams btn btn-primary']");
			generalElements.clickElementByXpath("//button[.='Add Teams']");
			generalElements.clickElementByXpath("//button[.='Save changes']");

		}
	
	//create a Course
		public void assignCourse(String challengeElementType, String challengeElementTypeDefinition) throws InterruptedException {
			generalElements.clickElement("href", "#assignments");
			generalElements.clickElementByXpath("//button[.='New Assignment']");
			generalElements.clickElementByXpath("//button[.='Select Assignment...']");
			generalElements.clickElementByXpath("//button[.='Select Course']");
			generalElements.clickElementByXpath("//"+challengeElementType+"[.='"+challengeElementTypeDefinition+"']");
			generalElements.collectElementsByXpath("//button[.='Select Course']").get(0).click();
			//assign team
			WebElement element = driver.findElement(By.xpath("//div[@class='modal-content']//div[@class='modal-footer']//button[@class='btn btn-primary']"));
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver; 
			js.executeScript("arguments[0].click();", element);
			Thread.sleep(2000);
			element = driver.findElement(By.xpath("//button[.='Next']"));
			js.executeScript("arguments[0].click();", element);
			Thread.sleep(2000);
			element = driver.findElement(By.xpath("//button[.='Assign']"));
			js.executeScript("arguments[0].click();", element);

		}
		
		//create a Course
				public void assignCourseAndChallenge(String challengeElementType, String challengeElementTypeDefinition) throws InterruptedException {
					generalElements.clickElement("href", "#assignments");
					generalElements.clickElementByXpath("//button[.='New Assignment']");
					//generalElements.clickElementByXpath("//button[.='Select Assignment...']");
					generalElements.clickElementByXpath("//button[.='Select Journey']");
					
					//select a journey
					generalElements.sendKeysToElement("id", "journeySearchInput", "Regression Journey");
					generalElements.clickElementByXpath("//button[@aria-label='Search journeys']");
					generalElements.clickElementByXpath("//h5[.='Regression Journey']");
					generalElements.clickElementByXpath("//button[.='Select Journey']");
					generalElements.clickElementByXpath("//button[.='Next']");
					
					//assign team
					WebElement element = driver.findElement(By.xpath("//div[@class='modal-content']//div[@class='modal-footer']//button[@class='btn btn-primary']"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
					Thread.sleep(2000);
					JavascriptExecutor js = (JavascriptExecutor) driver; 
					js.executeScript("arguments[0].click();", element);
					//generalElements.collectElementsByXpath("//button[.='Save']").get(0).click();
					Thread.sleep(2000);
					element = driver.findElement(By.xpath("//button[.='Next']"));
					js.executeScript("arguments[0].click();", element);
					//generalElements.clickElementByXpath("//button[.='Next']");
					//Thread.sleep(2000);
					//element = driver.findElement(By.xpath("//input[@value='1']"));
					//js.executeScript("arguments[0].click();", element);
					//generalElements.clickElementByXpath("//input[@value='1']");
					
					Thread.sleep(2000);
					element = driver.findElement(By.xpath("//button[.='Assign']"));
					js.executeScript("arguments[0].click();", element);
					//generalElements.clickElementByXpath("//button[.='Assign']");
					//Thread.sleep(2000);
					//element = driver.findElement(By.xpath("//button[@class='btn-auto-assign-teams btn btn-primary']"));
					//js.executeScript("arguments[0].click();", element);
					//generalElements.clickElementByXpath("//button[@class='btn-auto-assign-teams btn btn-primary']");
					//Thread.sleep(2000);
					//element = driver.findElement(By.xpath("//button[.='Add Teams']"));
					//js.executeScript("arguments[0].click();", element);
					//generalElements.clickElementByXpath("//button[.='Add Teams']");
					//Thread.sleep(2000);
					//element = driver.findElement(By.xpath("//button[.='Save changes']"));
					//js.executeScript("arguments[0].click();", element);
					//generalElements.clickElementByXpath("//button[.='Save changes']");

				}
	
	//create a class
	public void createClass(String className) throws InterruptedException {
		
		generalElements.clickElementByXpath("//a[@id='admin-tabs-tab-groups']");
		generalElements.clickElementByXpath("//button[.='Add group']");
		generalElements.sendKeysToElement("name", "name", className);
		
		generalElements.clickElementByXpath("//button[.='Save changes']");
		
		createdClass=true;

	}
	
	//create a sub organization
	public void createSubOrg(String OrgName) throws InterruptedException {
		generalElements.clickElementByXpath("//a[@id='admin-tabs-tab-organizations']");
		generalElements.clickElementByXpath("//button[.='Add Organization']");
		generalElements.sendKeysToElement("name", "name", OrgName);
			
		generalElements.clickElementByXpath("//button[.='Save changes']");
		
		//verify suborg created
		Thread.sleep(3000);
		generalElements.findText(OrgName);

	}
	
	//create a sub organization
	public void deleteSubOrg(String OrgName) throws InterruptedException {
		generalElements.clickElementByXpath("//a[@id='admin-tabs-tab-organizations']");
		generalElements.clickElementByXpath("//button[@class=' btn btn-link']");
		generalElements.clickElementByXpath("//a[.='Delete']");
		generalElements.clickElementByXpath("//button[.='Delete Organization']");
		Thread.sleep(2000);
		generalElements.findText("No organizations found.");

	}
	
	//generate a random string
	 public String generateRandomString(int length){
		 
		 String CHAR_LIST = 
		            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        StringBuffer randStr = new StringBuffer(length);
	        SecureRandom secureRandom = new SecureRandom();
	        for( int i = 0; i < length; i++ ) 
	            randStr.append( CHAR_LIST.charAt( secureRandom.nextInt(CHAR_LIST.length()) ) );
	        return randStr.toString();
	    }
	 
	 //complete discovery phase and activate submit
	 public void completeDiscoveryPhase(String randomText1, String randomText2, String randomText3, String randomText4, String randomText5,
			 String randomText6, String randomText7, String randomText8, String randomText9, String randomText10) throws InterruptedException{
			//complete discover phase test case steps
		 
		 	generalElements.collectElementsByXpath("//button[.='Next']").get(1).click();
		 	generalElements.collectElementsByXpath("//button[.='Next']").get(1).click();
		 	generalElements.collectElementsByXpath("//button[.='Next']").get(1).click();
			generalElements.clickElementByXpath("//h4[.='Discover']");
			generalElements.clickElementByXpath("//button[@aria-label='close side panel']");
				
			
			//add 10 words to word cloud and continue to next card
			generalElements.sendKeysToElement("id", "wordCloudList", randomText1);
			generalElements.clickElementByXpath("//button[.='Add Word']");
			generalElements.sendKeysToElement("id", "wordCloudList", randomText2);
			generalElements.clickElementByXpath("//button[.='Add Word']");
			generalElements.sendKeysToElement("id", "wordCloudList", randomText3);
			generalElements.clickElementByXpath("//button[.='Add Word']");
			generalElements.sendKeysToElement("id", "wordCloudList", randomText4);
			generalElements.clickElementByXpath("//button[.='Add Word']");
			generalElements.sendKeysToElement("id", "wordCloudList", randomText5);
			generalElements.clickElementByXpath("//button[.='Add Word']");
			generalElements.sendKeysToElement("id", "wordCloudList", randomText6);
			generalElements.clickElementByXpath("//button[.='Add Word']");
			generalElements.sendKeysToElement("id", "wordCloudList", randomText7);
			generalElements.clickElementByXpath("//button[.='Add Word']");
			generalElements.sendKeysToElement("id", "wordCloudList", randomText8);
			generalElements.clickElementByXpath("//button[.='Add Word']");
			generalElements.sendKeysToElement("id", "wordCloudList", randomText9);
			generalElements.clickElementByXpath("//button[.='Add Word']");
			generalElements.sendKeysToElement("id", "wordCloudList", randomText10);
			generalElements.clickElementByXpath("//button[.='Add Word']");
			//generalElements.clickElementByXpath("//button[.='Next']");
			//generalElements.sendKeysToElement("placeholder", "Enter word...", randomText10+"1");
			//generalElements.clickElementByXpath("//button[.='Add Word']");
			//generalElements.sendKeysToElement("placeholder", "Enter word...", randomText10+"2");
			//generalElements.clickElementByXpath("//button[.='Add Word']");
			
			//complete card 2 and go to next one
			generalElements.clickElementByXpath("//button[@aria-label='Next activity']");
			generalElements.sendKeysToElement("class", "ql-editor ql-blank", "testing");
			generalElements.sendKeysToElement("class", "ql-editor", " ");
			generalElements.sendKeysToElement("class", "ql-editor", "and");
			generalElements.sendKeysToElement("class", "ql-editor", " ");
			generalElements.sendKeysToElement("class", "ql-editor", "filling");
			generalElements.sendKeysToElement("class", "ql-editor", " ");
			generalElements.sendKeysToElement("class", "ql-editor", "this");
			generalElements.sendKeysToElement("class", "ql-editor", " ");
			generalElements.sendKeysToElement("class", "ql-editor", "out");
			generalElements.sendKeysToElement("class", "ql-editor", " ");
			generalElements.sendKeysToElement("class", "ql-editor", "to");
			generalElements.sendKeysToElement("class", "ql-editor", " ");
			generalElements.sendKeysToElement("class", "ql-editor", "check");
			generalElements.sendKeysToElement("class", "ql-editor", " ");
			generalElements.sendKeysToElement("class", "ql-editor", "that");
			generalElements.sendKeysToElement("class", "ql-editor", " ");
			generalElements.sendKeysToElement("class", "ql-editor", "it");
			generalElements.sendKeysToElement("class", "ql-editor", " ");
			generalElements.sendKeysToElement("class", "ql-editor", "is");
			generalElements.sendKeysToElement("class", "ql-editor", " ");
			generalElements.sendKeysToElement("class", "ql-editor", "working");
			generalElements.sendKeysToElement("class", "ql-editor", " ");
			generalElements.sendKeysToElement("class", "ql-editor", "and");
			generalElements.sendKeysToElement("class", "ql-editor", " ");
			generalElements.sendKeysToElement("class", "ql-editor", "is");
			generalElements.sendKeysToElement("class", "ql-editor", " ");
			generalElements.sendKeysToElement("class", "ql-editor", "long");
			generalElements.sendKeysToElement("class", "ql-editor", " ");
			generalElements.sendKeysToElement("class", "ql-editor", "enough");
			generalElements.clickElementByXpath("//div[.='Select your file']");
				
			String workingDir = System.getProperty("user.dir");
			//generalElements.sendKeysToElementByXpath("//input[@type='file']", workingDir + "/file uploads/lionImage.jpg");
			generalElements.collectElementsByXpath("//input[@type='file']").get(1).sendKeys(workingDir + "/file uploads/lionImage.jpg");
			generalElements.clickElementByXpath("//button[.='Upload']");
			
			Thread.sleep(2000);
			WebElement element = driver.findElement(By.xpath("//button[.='Next']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
			
			generalElements.clickElementByXpath("//button[.='Next']");
			
			//complete card 3 and go to next one
			Thread.sleep(2000);
			element = driver.findElement(By.xpath("//button[.='Next']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

			generalElements.collectElementsByXpath("//button[.='Pick constraint category']").get(0).click();
			generalElements.clickElementByXpath("//a[.='Time']");

			generalElements.collectElementsByXpath("//button[.='Pick constraint category']").get(1).click();
			generalElements.collectElementsByXpath("//a[.='Aesthetics']").get(2).click();

			generalElements.collectElementsByXpath("//button[.='Pick constraint category']").get(0).click();
			generalElements.collectElementsByXpath("//a[.='Reliability']").get(1).click();

			generalElements.collectElementsByXpath("//textarea[.='']").get(0).sendKeys("adding text to complete this card");
			Thread.sleep(4000);
			
			generalElements.collectElementsByXpath("//textarea[.='']").get(1).sendKeys("adding text to complete this card");
			Thread.sleep(4000);
			
			generalElements.collectElementsByXpath("//textarea[.='']").get(0).sendKeys("adding text to complete this card");
			Thread.sleep(4000);
			
			element = driver.findElement(By.xpath("//button[.='Next']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

			generalElements.clickElementByXpath("//button[.='Next']");
			
			//complete final card and verify submit button is active
			//submit what youve learned
			element = generalElements.collectElementsByXpath("//button[.='Add What You Know']").get(0);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
			Thread.sleep(2);
			generalElements.collectElementsByXpath("//button[.='Add What You Know']").get(0).click();
			Thread.sleep(2);
			generalElements.collectElementsByXpath("//textarea[@placeholder='Write at least 5 words...']").get(0).sendKeys("some text to complete this");
			generalElements.clickElementByXpath("//button[.='Submit']");
			
			element = generalElements.collectElementsByXpath("//button[.='Add What You Know']").get(1);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
			Thread.sleep(2);
			generalElements.collectElementsByXpath("//button[.='Add What You Know']").get(1).click();
			generalElements.collectElementsByXpath("//textarea[@placeholder='Write at least 5 words...']").get(0).sendKeys("some text to complete this");
			generalElements.clickElementByXpath("//button[.='Submit']");
			
			element = generalElements.collectElementsByXpath("//button[.='Add What You Know']").get(2);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
			Thread.sleep(2);
			generalElements.collectElementsByXpath("//button[.='Add What You Know']").get(2).click();
			generalElements.collectElementsByXpath("//textarea[@placeholder='Write at least 5 words...']").get(0).sendKeys("some text to complete this");
			generalElements.clickElementByXpath("//button[.='Submit']");
			
			
	 }
	 
	//submit discovery phase
	public void submitPhase(String phase, String nextPhase) throws InterruptedException {
		generalElements.clickElementByXpath("//button[.='Submit "+phase+"']");
		
		//submit the phase and verify you are taken to new phase
		generalElements.clickElementByXpath("//button[.='Submit']");
		generalElements.clickElementByXpath("//button[.='Cancel Submit']");
		
		generalElements.clickElementByXpath("//button[.='Submit "+phase+"']");
		generalElements.clickElementByXpath("//button[.='Submit']");
		Thread.sleep(12000);
		
		//verify you are on completion screen
		generalElements.verifyElementIsVisableOrNotVisable("id", "phase-celebration-content", true);
		generalElements.findText("Submitted by");
		generalElements.findText("Contributions");
		generalElements.collectElementsByXpath("//button[@class='btn btn-primary btn-lg' and contains(text(),'Continue')]").get(0).click();
		
		//continue to next phase
		generalElements.clickElementByXpath("//button[.='Continue to "+nextPhase+"']");
		
		
			 
	}
	
	//delete a class for a organization with a single class
	public void loginAndDeleteOrganizationClassWith1Class(String className) throws InterruptedException {
		//go back to organization and delete the class created
		generalElements.clickElement("href","#organizations");
		
		generalElements.clickElementByXpath("//b[.='"+className+"']");
		generalElements.clickElementByXpath("//a[@id='admin-tabs-tab-groups']");
		
		generalElements.clickElement("id","admin-tabs-tab-groups");
		
		if(createdClass == true) {
			generalElements.clickElementByXpath("//button[@class=' btn btn-link']");
			generalElements.clickElementByXpath("//a[.='Delete']");
			generalElements.clickElementByXpath("//button[.='Delete Group']");
			generalElements.findText("No groups found.");
		}
		
		
		
		//logout
		generalElements.collectElementsByXpath("//a[@id='nav-dropdown']").get(0).click();
		generalElements.clickElement("href","/logout");
	}
	
	public void completeCourse() throws InterruptedException {
		
		//introduction
		generalElements.clickElementByXpath("//button[.='Continue']");
		
		//session 1
		WebElement element = driver.findElement(By.xpath("//h5[.='Be Responsible']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//h5[.='Clarify Expectations']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//h5[.='Build Trust']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//h4[.='Taking Control of Your Work']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		generalElements.clickElementByXpath("//button[@class='close-button d-flex justify-content-center align-items-center text-dark btn btn-link']");
		Thread.sleep(2000);
		element = driver.findElement(By.xpath("//video[@controlslist='nodownload']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(2000);
		element.click();

		
		
		//wait for video
		Thread.sleep(70000);
		element.click();
		Thread.sleep(1000);
		element.click();
		Thread.sleep(70000);
		element.click();
		Thread.sleep(1000);
		element.click();
		Thread.sleep(70000);
		//input text areas
		generalElements.sendKeysToElementByXpath("//textarea[@id='103460058560']", "test 123");
		generalElements.sendKeysToElementByXpath("//textarea[@id='391600952104']", "test 123");
		generalElements.sendKeysToElementByXpath("//textarea[@id='554628640789']", "test 123");
		Thread.sleep(5000);
		
		
		//go and complete session 2
		element = driver.findElement(By.xpath("//h5[.='Share It']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(2000);
		generalElements.collectElementsByXpath("//span[.='Session 2']").get(1).click();
		
		
		element = driver.findElement(By.xpath("//h5[.='Choose Accountability']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//h5[.='Think Ahead']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//h5[.='Balance Responsibilities']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//h4[.='Being at Your Best']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		
		element = driver.findElement(By.xpath("//video[@controlslist='nodownload']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(2000);
		element.click();
		//wait for video
		Thread.sleep(70000);
		element.click();
		Thread.sleep(1000);
		element.click();
		Thread.sleep(70000);
		element.click();
		Thread.sleep(1000);
		element.click();
		Thread.sleep(70000);
		//input text areas
		generalElements.sendKeysToElementByXpath("//textarea[@id='90734664173']", "test 123");
		generalElements.sendKeysToElementByXpath("//textarea[@id='568077944425']", "test 123");
		generalElements.sendKeysToElementByXpath("//textarea[@id='462215426208']", "test 123");
		Thread.sleep(5000);
		
		//go and complete session 3
		element = driver.findElement(By.xpath("//h5[.='Share It']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(2000);
		generalElements.collectElementsByXpath("//span[.='Session 3']").get(1).click();
		
		element = driver.findElement(By.xpath("//h5[.='Making Mistakes']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//h5[.='Avoiding Blame']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//h5[.='Seeking Growth']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//h4[.='Owning Your Failures']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);						
		
		element = driver.findElement(By.xpath("//video[@controlslist='nodownload']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(2000);
		element.click();
		//wait for video
		Thread.sleep(60000);
		element.click();
		Thread.sleep(1000);
		element.click();
		Thread.sleep(60000);
		element.click();
		Thread.sleep(1000);
		element.click();
		Thread.sleep(60000);
		//input text areas
		generalElements.sendKeysToElementByXpath("//textarea[@id='262958941537']", "test 123");
		generalElements.sendKeysToElementByXpath("//textarea[@id='490382345726']", "test 123");
		generalElements.sendKeysToElementByXpath("//textarea[@id='787281142934']", "test 123");
		Thread.sleep(5000);
		
		//go and complete session 4
		element = driver.findElement(By.xpath("//h5[.='Share It']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(2000);
		generalElements.collectElementsByXpath("//span[.='Session 4']").get(1).click();

		
		element = driver.findElement(By.xpath("//h5[.='Defining Roles']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//h5[.='Keeping Promises']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//h5[.='Proactively Supporting Others']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//h4[.='Managing Your Commitments']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		
		element = driver.findElement(By.xpath("//video[@controlslist='nodownload']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(2000);
		element.click();
		//wait for video
		Thread.sleep(70000);
		element.click();
		Thread.sleep(1000);
		element.click();
		Thread.sleep(70000);
		element.click();
		Thread.sleep(1000);
		element.click();
		Thread.sleep(70000);
		//input text areas
		generalElements.sendKeysToElementByXpath("//textarea[@id='758844619348']", "test 123");
		generalElements.sendKeysToElementByXpath("//textarea[@id='733907587212']", "test 123");
		generalElements.sendKeysToElementByXpath("//textarea[@id='140068454472']", "test 123");
		generalElements.sendKeysToElementByXpath("//textarea[@id='451493870873']", "test 123");
		generalElements.sendKeysToElementByXpath("//textarea[@id='303913598985']", "test 123");
		generalElements.sendKeysToElementByXpath("//textarea[@id='423433697306']", "test 123");
		generalElements.sendKeysToElementByXpath("//textarea[@id='808526895565']", "test 123");
		generalElements.sendKeysToElementByXpath("//textarea[@id='475868465700']", "test 123");
		Thread.sleep(5000);
		
		//close course
		generalElements.clickElementByXpath("//button[@class='content-close-btn btn-icon me-2 text-decoration-none text-muted btn btn-link']");
	}
	
	 //complete discovery phase and activate submit
	 public void completeExplorePhase() throws InterruptedException {
			//complete card1
			String workingDir = System.getProperty("user.dir");
			generalElements.collectElementsByXpath("//button[.='Add Question']").get(0).click();
			Thread.sleep(2000);
			//generalElements.collectElementsByXpath("//a[@data-rb-event-key='text']").get(0).click();
			generalElements.collectElementsByXpath("//textarea[@placeholder='Write at least 5 words...']").get(0).sendKeys("some text to complete this");
			Thread.sleep(2000);
			generalElements.collectElementsByXpath("//button[.='Add Question']").get(1).click();
			
			Thread.sleep(2000);
			generalElements.collectElementsByXpath("//button[.='Add Question']").get(0).click();
			Thread.sleep(2000);
			//generalElements.collectElementsByXpath("//a[@data-rb-event-key='text']").get(0).click();
			generalElements.collectElementsByXpath("//textarea[@placeholder='Write at least 5 words...']").get(0).sendKeys("some text to complete this");
			Thread.sleep(2000);
			generalElements.collectElementsByXpath("//button[.='Add Question']").get(1).click();
			
			Thread.sleep(2000);
			generalElements.collectElementsByXpath("//button[.='Add Question']").get(0).click();
			Thread.sleep(2000);
			generalElements.collectElementsByXpath("//textarea[@placeholder='Write at least 5 words...']").get(0).sendKeys("some text to complete this");
			Thread.sleep(2000);
			generalElements.collectElementsByXpath("//button[.='Add Question']").get(1).click();
			Thread.sleep(2000);

			//go to next card
			WebElement element = driver.findElement(By.xpath("//button[.='Next']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);	
			generalElements.clickElementByXpath("//button[@aria-label='Next activity']");
			
			//add research cards
			element = generalElements.collectElementsByXpath("//h4[.='Activity']").get(0);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
			Thread.sleep(2000);
			generalElements.collectElementsByXpath("//span[.='Add Research']").get(0).click();
			generalElements.clickElementByXpath("//h5[.='Online']");
			generalElements.collectElementsByXpath("//input[@class='form-control']").get(0).sendKeys("test.com");
			generalElements.clickElementByXpath("//button[.='Next']");
			Thread.sleep(5000);
			generalElements.collectElementsByXpath("//textarea[@placeholder='Write at least 5 words...']").get(0).sendKeys("some text to complete this");
			generalElements.clickElementByXpath("//button[.='Submit']");
			
			Thread.sleep(2000);
			generalElements.collectElementsByXpath("//button[.='Add Research']").get(1).click();
			generalElements.clickElementByXpath("//h5[.='Online']");
			generalElements.collectElementsByXpath("//input[@class='form-control']").get(0).sendKeys("test.com");
			generalElements.clickElementByXpath("//button[.='Next']");
			Thread.sleep(5000);
			generalElements.collectElementsByXpath("//textarea[@placeholder='Write at least 5 words...']").get(0).sendKeys("some text to complete this");
			generalElements.clickElementByXpath("//button[.='Submit']");
			
			Thread.sleep(2000);
			generalElements.collectElementsByXpath("//button[.='Add Research']").get(2).click();
			generalElements.clickElementByXpath("//h5[.='Online']");
			generalElements.collectElementsByXpath("//input[@class='form-control']").get(0).sendKeys("test.com");
			generalElements.clickElementByXpath("//button[.='Next']");
			Thread.sleep(5000);
			generalElements.collectElementsByXpath("//textarea[@placeholder='Write at least 5 words...']").get(0).sendKeys("some text to complete this");
			generalElements.clickElementByXpath("//button[.='Submit']");
			
	 }
	 
	 public void completeImaginePhase() throws InterruptedException {
			//add all ideas and go to next card
			String workingDir = System.getProperty("user.dir");
			WebElement element = generalElements.collectElementsByXpath("//button[.='Add Idea']").get(0);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
			Thread.sleep(2);
			generalElements.collectElementsByXpath("//button[.='Add Idea']").get(0).click();
			generalElements.collectElementsByXpath("//textarea[@placeholder='Write at least 5 words...']").get(0).sendKeys("some text to complete this");
			generalElements.clickElementByXpath("//button[.='Submit']");
			
			//idea 2
			generalElements.collectElementsByXpath("//button[.='Add Idea']").get(0).click();
			generalElements.collectElementsByXpath("//textarea[@placeholder='Write at least 5 words...']").get(0).sendKeys("some text to complete this");
			generalElements.clickElementByXpath("//button[.='Submit']");
			
			//idea 3
			generalElements.collectElementsByXpath("//button[.='Add Idea']").get(0).click();
			generalElements.collectElementsByXpath("//textarea[@placeholder='Write at least 5 words...']").get(0).sendKeys("some text to complete this");
			generalElements.clickElementByXpath("//button[.='Submit']");
			
			//idea 4
			generalElements.collectElementsByXpath("//button[.='Add Idea']").get(0).click();
			generalElements.collectElementsByXpath("//textarea[@placeholder='Write at least 5 words...']").get(0).sendKeys("some text to complete this");
			generalElements.clickElementByXpath("//button[.='Submit']");
			
			//idea 5
			generalElements.collectElementsByXpath("//button[.='Add Idea']").get(0).click();
			generalElements.collectElementsByXpath("//textarea[@placeholder='Write at least 5 words...']").get(0).sendKeys("some text to complete this");
			generalElements.clickElementByXpath("//button[.='Submit']");
			Thread.sleep(2000);
			
			
			//go to next card
			element = driver.findElement(By.xpath("//button[.='Next']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
			
			generalElements.clickElementByXpath("//button[.='Next']");
			
			//select an idea and verify submit is enabled
			generalElements.collectElementsByXpath("//div[@class='card-body']").get(1).click();
			generalElements.collectElementsByXpath("//button[.='Submit Imagine']").get(0).isEnabled();
			
	 }
	 
	 public void completeCreatePhase() throws InterruptedException {
		//fill required info for card 1 and continue
			generalElements.collectElementsByXpath("//textarea[@class='form-control']").get(0).sendKeys("filling required words over here");
			
			generalElements.collectElementsByXpath("//textarea[@class='form-control']").get(1).sendKeys("filling required words over here");
			
			generalElements.collectElementsByXpath("//textarea[@class='form-control']").get(2).sendKeys("filling required words over here");
			
			
			//go to next card
			WebElement element = driver.findElement(By.xpath("//button[.='Next']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
			
			generalElements.clickElementByXpath("//button[.='Next']");
			
			//fill in required info for card 2
			//generalElements.collectElementsByXpath("//button[@aria-label='View deliverable details']").get(1).click();
			element = generalElements.collectElementsByXpath("//button[.='See Example']").get(0);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
			
			//generalElements.clickElementByXpath("//button[.='Next']");
			generalElements.collectElementsByXpath("//button[.='Create this Deliverable']").get(0).click();
			generalElements.sendKeysToElementByXpath("//input[@placeholder='Paste a link']", "https://docs.google.com/presentation/d/1EPJtu4cGXHg8tFgJB8xiXeg5GGlZC40W52JTAby1bqY/edit?usp=sharing");
			generalElements.clickElementByXpath("//div[@class='input-group']//button[@class='btn btn-type']");
			generalElements.clickElementByXpath("//button[.='Submit']");
			generalElements.collectElementsByXpath("//button[.='Submit Create']").get(0).isEnabled();
	 }
	 
	 public void Logout() throws InterruptedException {
		 Thread.sleep(2000);
		 	WebElement element = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"nav-dropdown\"]")));
			element = driver.findElement(By.xpath("//*[@id=\"nav-dropdown\"]"));
			
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver; 
				js.executeScript("arguments[0].click();", element);
				
				Thread.sleep(2000);
				element = driver.findElement(By.xpath("//*[@href='/logout']"));
				js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", element);
			}catch(Exception e) {
				System.out.print(e);
				
				List<WebElement> listelement = driver.findElements(By.xpath("//*[@id=\"nav-dropdown\"]"));
				JavascriptExecutor js = (JavascriptExecutor) driver; 
				js.executeScript("arguments[0].click();", listelement.get(1));
				generalElements.clickElementByXpath("//*[@href='/logout']");
			}
			
		 
	 }
	 
	 public void PassFailTest(String result) throws InterruptedException {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("sauce:job-result=" + result); 
	 }
	 
	 public void SetSauceTestName(String name) throws InterruptedException {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("sauce:job-name=" + name); 
	 }
	 
	 public void closeBrowser() throws InterruptedException {
		 driver.quit();
	 }

}
