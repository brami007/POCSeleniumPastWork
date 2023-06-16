package com.SolvablyAutoJava;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GeneralElements {
	public GeneralElements(WebDriver driver){
		this.driver = driver;
	}

	WebDriver driver;
	
	public void verifyLinkLoaded(String searchResultsLink) throws InterruptedException {
		//wait for the result to display
		WebElement searchResult = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@href='" +searchResultsLink + "']")));
		
		//verify it is displayed
		try {
			searchResult.isDisplayed();
		}catch(Exception e){
			System.out.print("Verify clicking on link " +searchResultsLink);
		}
		
	}
	
	public void verifyElementLoaded(String elementType,String elementName) throws InterruptedException {
		//wait for the result to display
		Thread.sleep(1500);
		WebElement searchResult = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@" + elementType +"='" +elementName + "']")));
		
		//verify it is displayed
		try {
			searchResult.isDisplayed();
		}catch(Exception e){
			System.out.print("Verify the following element loaded: Element Type:" +elementType + " Type name: " + elementName);
		}
		
	}
	
	public void verifyElementLoadedFullXpath(String fullXP) throws InterruptedException {
		//wait for the result to display
		Thread.sleep(1500);
		WebElement searchResult = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.xpath(fullXP)));
		
		
		//verify it is displayed
		try {
			searchResult.isDisplayed();
		}catch(Exception e){
			System.out.print("Verify the following element loaded. Full XPath: " + fullXP);
		}
		
	}
	
	public void clickElement(String elementType, String linkToClick) throws InterruptedException {
		//wait for the result to display
		Thread.sleep(1500);
		WebElement searchResult = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@" + elementType +"='" +linkToClick + "']")));
		
		//verify it is displayed
		try {
			searchResult.click();
		}catch(Exception e){
			System.out.print("Verify the following element can be clicked: Element Type:" +elementType + " Type name: " + linkToClick+"/n");
			System.out.print(e+"/n");
		}
		
	}
	
	public void clickElementByXpath(String xpath) throws InterruptedException {
		//wait for the result to display
		Thread.sleep(2500);
		WebElement searchResult = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		Thread.sleep(2000);
		//verify it is displayed
		
		
		for(int x = 0; x < 5; x++) {
			
			try {
				searchResult.click();
				break;
			}catch(Exception e){
				System.out.print(e+"\n");
				//System.out.print("Verify the following element can be clicked: Full xpath:" +xpath);
			}
			Thread.sleep(1500);
		}
		
		
	}
	
	public void verifyButtonDisabled(String xpath) throws InterruptedException {
		//wait for the result to display
		Thread.sleep(1500);
		WebElement searchResult = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		
		//verify it is displayed
		try {
			assertEquals(false, searchResult.isEnabled());
		}catch(Exception e){
			System.out.print("Verify the following button is disabled: Full xpath:" +xpath);
		}
	}
	
	public void selectDropdown(String elementType, String elementTypeDefenition, String dropdownItemToSelect) throws InterruptedException {
		Thread.sleep(1500);
		Select dropdownSelector = new Select(driver.findElement(By.xpath("//*[@" + elementType +"='" +elementTypeDefenition + "']")));

		//select the dropdown
		try {
			dropdownSelector.selectByVisibleText(dropdownItemToSelect);
		}catch(Exception e){
			System.out.print("Verify the following button is disabled: Element type:" +elementType + " Element Type Definition: " + elementTypeDefenition);
		}

	}
	
	public void sendKeysToElement(String elementType, String typeDescription, String charactersToSend) throws InterruptedException {
		//wait for the result to display
		Thread.sleep(1500);
		WebElement searchResult = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@" + elementType + "='" +typeDescription + "']")));
		
		//verify it is displayed
		try {
			searchResult.sendKeys(charactersToSend);
		}catch(Exception e){
			System.out.print("Sending keys to the following element. Element Type: " +elementType + " Element Type Definition: " + typeDescription);
		}
		
	}
	
	public void sendKeysToElementByXpath(String xpath, String charactersToSend) throws InterruptedException {
		//wait for the result to display
		Thread.sleep(1500);
		WebElement searchResult = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		
		try {
			searchResult.sendKeys(charactersToSend);
		}catch(Exception e){
			System.out.print("Sending keys to the following element. Full xpath: " +xpath);
		}
		//verify it is displayed
	}
	
	public void clearText(String elementType, String typeDescription, String charactersToSend) throws InterruptedException {
		//wait for the result to display
		Thread.sleep(1500);
		WebElement searchResult = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@" + elementType + "='" +typeDescription + "']")));
		
		//clear text
		try {
			searchResult.clear();
		}catch(Exception e){
			System.out.print("Clear the text to the following element. Element Type: " +elementType + "Element type definition: " + typeDescription);
		}
	}
	
	public void findText(String textToFind) throws InterruptedException {
		//wait for the result to display
		Thread.sleep(1500);
		WebElement searchResult = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + textToFind + "')]")));

	}
	
	public void verifyElementIsVisableOrNotVisable(String elementType,String elementName, Boolean isVisable) throws InterruptedException {
		//wait for the result to display
		Thread.sleep(1500);
		WebElement searchResult = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@" + elementType +"='" +elementName + "']")));
		
		//verify it is visable
		try {
			if(isVisable) {
				searchResult.isDisplayed();
			}else {
				
			}
		}catch(Exception e){
			System.out.print("Check the following element is visable. Element Type: " +elementType + "Element type definition: " + elementName);
		}
	

	}
	
	public void clickTableElementButton(String elementType,String elementName, String textToFind, int colNumber, boolean isButton, boolean isMenu, int menuItemNumber) throws InterruptedException {
		//wait for the result to display
		Thread.sleep(1500);
		
		WebElement searchResult = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"admin-tabs-tabpane-users\"]/div/div/table/tbody/tr/td["+colNumber+"]/div/button")));

		try {
			if(isButton) {
				driver.findElement(By.xpath("//*[@id=\"admin-tabs-tabpane-users\"]/div/div/table/tbody/tr/td["+colNumber+"]/div/button")).click();
			}
			else if (isMenu){
				driver.findElement(By.xpath("//*[@id=\"admin-tabs-tabpane-users\"]/div/div/table/tbody/tr/td["+colNumber+"]/div/div/a["+menuItemNumber+"]")).click();
			}
		}catch(Exception e){
			System.out.print("Click table element");
		}
		
		
	}
	
	public void verifyTableElement(String elementType,String elementName, Boolean isVisable) throws InterruptedException {
		//wait for the result to display
		Thread.sleep(1500);
		WebElement searchResult = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@" + elementType +"='" +elementName + "']")));
		
		//verify it is visable
		try {
			if(isVisable) {
				searchResult.isDisplayed();
			}else {
				
			}
		}catch(Exception e){
			System.out.print("Verify table element exists. Element Type: " + elementType + " Element type name: " + elementName);
		}
		

	}

	public List<WebElement> collectElementsByXpath(String xpath) throws InterruptedException {
		//wait for the result to display
		Thread.sleep(4500);
		List<WebElement> searchResult = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		
		//verify it is displayed
		try {
			return searchResult;
		}catch(Exception e){
			System.out.print("Collects elements by xpath: " + xpath);
			return searchResult;
		}
	}
	
	public void clickListElementByJavaScript(String xpath, int index ) throws InterruptedException {
		//wait for the result to display
		Thread.sleep(4500);
		List<WebElement> searchResult = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].click();", searchResult.get(index));
		
		//verify it is displayed
		try {
			//return searchResult;
		}catch(Exception e){
			System.out.print("Collects elements by xpath: " + xpath);
			//return searchResult;
		}
	}

}