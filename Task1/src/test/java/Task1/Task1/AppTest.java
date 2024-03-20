package Task1.Task1;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class AppTest 
{
    WebDriver driver;
    WebDriverWait wait;
    int i=0;
    
    @Test(priority=1)
    public void open_browser() {
    	System.setProperty("webdriver.chrome.driver","G:\\worldline\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
    	driver = new ChromeDriver();
    	wait = new WebDriverWait(driver, 5);
    	driver.manage().window().maximize();
    	driver.get("https://www.edureka.co/");
    	System.out.println("Start Browser Executed");
    	Reporter.log("Start Browser Executed");
    }
    
    @Test(dependsOnMethods = {"open_browser"})
    public void login() {
    	WebElement login_page_button = driver.findElement(By.xpath("/html[1]/body[1]/header[1]/nav[1]/ul[1]/li[4]/span[1]"));
    	login_page_button.click();
    	WebElement email_input = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[1]/div[2]/div[3]/form[1]/div[1]/input[1]"));
    	wait.until(ExpectedConditions.visibilityOf(email_input));
    	email_input.sendKeys("worldlinetestingassignment@gmail.com");
    	WebElement password_input = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[1]/div[2]/div[3]/form[1]/div[2]/input[1]"));
    	password_input.sendKeys("Worldline@01");
    	WebElement login_button = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[1]/div[2]/div[3]/form[1]/button[1]"));
    	login_button.click();
    	System.out.println("Login Executed");
    	Reporter.log("Login Executed");
    }
    
    @Test(dependsOnMethods = {"login"})
    public void personal_profile() throws InterruptedException  {
    	Thread.sleep(2000);
    	WebElement profileopen = driver.findElement(By.xpath("/html[1]/body[1]/header[1]/nav[1]/div[4]/ul[1]/li[6]"));
    	profileopen.click();
    	WebElement profileclick = driver.findElement(By.xpath("/html[1]/body[1]/header[1]/nav[1]/div[4]/ul[1]/li[6]/ul[1]/li[4]/a[1]"));
    	profileclick.click();
    	
    	String expectedURL = "https://learning.edureka.co/my-profile";
    	String actualURL = driver.getCurrentUrl();
    	Assert.assertEquals(expectedURL,actualURL);
    	
    	System.out.println("Go to profile Executed");
    	Reporter.log("Go to profile Executed");
    }
    
    @Test(dependsOnMethods = {"personal_profile"},dataProvider="testingdata")
    public void personal_profile_change(String name,String years,String role,String designations) throws InterruptedException {
    	WebElement user_details_edit = driver.findElement(By.id("other_details"));
    	user_details_edit.click();
    	System.out.println("Click Edit Personal Profile Executed");
    	
    	
    	if(i==0) {
    		try {
	    	WebElement deny_updates = driver.findElement(By.id("wzrk-cancel"));
	    	wait.until(ExpectedConditions.visibilityOf(deny_updates));
	    	deny_updates.click();
	    	i=1;
    		}
    		catch(NoSuchElementException e) {
    			// Notification element not found or not visible
    		}
    	}
    	
    	String expectedURL = "https://learning.edureka.co/new-onboarding/userdetails?fromProfile=true&tab=userDetails";
    	String actualURL = driver.getCurrentUrl();
    	Assert.assertEquals(expectedURL,actualURL);
    	
    	WebElement change_name = driver.findElement(By.id("fullName"));
    	change_name.clear();
    	change_name.sendKeys(name);
    	
    	WebElement year_dropdown_icon_click = driver.findElement(By.xpath("(//button[@type='button'])[5]"));
    	year_dropdown_icon_click.click();
    	WebElement yearSelect = driver.findElement(By.xpath("//ul[@class='available-items']//li[contains(text(), '"+years+"')]"));
    	yearSelect.click();
    	
    	WebElement role_icon_click = driver.findElement(By.xpath("(//button[@type='button'])[6]"));
    	role_icon_click.click();
    	WebElement roleSelect = driver.findElement(By.xpath("//ul[@class='available-items']//li[contains(text(), '"+role+"')]"));
    	roleSelect.click();
    	
    	WebElement designation = driver.findElement(By.id("designation"));
    	designation.clear();
    	designation.sendKeys(designations);
    	    	
    	WebElement save_button = driver.findElement(By.xpath("(//button[normalize-space()='Save and Continue'])[1]"));
    	save_button.click();
    	
    	Thread.sleep(2000);
    	String expectedURL2 = "https://learning.edureka.co/my-profile";
    	String actualURL2 = driver.getCurrentUrl();
    	Assert.assertEquals(expectedURL2,actualURL2);
    	
    	System.out.println("Personal Profile details Change Executed");
    	Reporter.log("Personal Profile details Change Executed");
    }
    

    
    @Test(dependsOnMethods = {"personal_profile_change"},dataProvider="testingdata2")
    public void carrier_profile_change(String companyName, String currentJob, String currentIndustry, String userSkill, String interestedJob, String jobType, String lastDrawnSalary, String currentCity) throws InterruptedException {
    	Thread.sleep(2000);		
    	WebElement career_services_click = driver.findElement(By.xpath("(//a[normalize-space()='Career Services'])[1]"));
    	career_services_click.click();
    	
    	WebElement professional_details_edit = driver.findElement(By.xpath("(//a[@id='professional_details'])[1]"));
    	professional_details_edit.click();
    	System.out.println("Go to Carrier Profile Executed");
    	Reporter.log("Go to Carrier Profile Executed");
    	
    	Thread.sleep(2000);
    	String expectedURL = "https://learning.edureka.co/onboarding/professionaldetails";
    	String actualURL = driver.getCurrentUrl();
    	Assert.assertEquals(expectedURL,actualURL);
    	
    	WebElement company_details = driver.findElement(By.name("companyName"));
    	company_details.clear();
    	company_details.sendKeys(companyName);
    	
    	WebElement current_job = driver.findElement(By.name("currentjob"));
    	Select current_job_select = new Select(current_job);
    	current_job_select.selectByVisibleText(currentJob);
    	
    	WebElement industry = driver.findElement(By.name("currentIndustry"));
    	Select industry_select = new Select(industry);
    	industry_select.selectByVisibleText(currentIndustry);
    	
    	WebElement skill = driver.findElement(By.name("userSkill"));
    	skill.clear();
    	skill.sendKeys(userSkill);
    	
    	WebElement next_button = driver.findElement(By.xpath("(//button[normalize-space()='Next'])[1]"));
    	wait.until(ExpectedConditions.elementToBeClickable(next_button));
    	next_button.click();
    	
    	Thread.sleep(2000);
    	String expectedURL2 = "https://learning.edureka.co/onboarding/careerinterests";
    	String actualURL2 = driver.getCurrentUrl();
    	Assert.assertEquals(expectedURL2,actualURL2);
    	
    	WebElement interested_job = driver.findElement(By.name("interestedJob"));
    	Select interested_job_select = new Select(interested_job);
    	interested_job_select.selectByVisibleText(interestedJob);
    	
    	WebElement employment_type = driver.findElement(By.name("elementType"));
    	Select employment_type_select = new Select(employment_type);
    	employment_type_select.selectByVisibleText(jobType);
    	
    	WebElement current_salary = driver.findElement(By.name("lastDrawnSalary"));
    	Select current_salary_select = new Select(current_salary);
    	current_salary_select.selectByVisibleText(lastDrawnSalary);
    	
    	WebElement city = driver.findElement(By.name("currentCity"));
    	city.clear();
    	city.sendKeys(currentCity);
    	
    	if(i==0) {
    		try {
	    	WebElement deny_updates = driver.findElement(By.id("wzrk-cancel"));
	    	wait.until(ExpectedConditions.visibilityOf(deny_updates));
	    	deny_updates.click();
	    	i=1;
    		}
    		catch(NoSuchElementException e) {
    			// Notification element not found or not visible
    		}
    	}
    	
    	WebElement next_button_2 = driver.findElement(By.xpath("(//button[normalize-space()='Next'])[1]"));
    	wait.until(ExpectedConditions.elementToBeClickable(next_button_2));
    	next_button_2.click();
    	
    	Thread.sleep(2000);
    	WebElement save_button_2 = driver.findElement(By.cssSelector("button[class='btn pull-right onboarding-primary-button']"));
    	wait.until(ExpectedConditions.elementToBeClickable(save_button_2));
    	save_button_2.click();
    	
    	Thread.sleep(2000);
    	String expectedURL3 = "https://learning.edureka.co/my-profile";
    	String actualURL3 = driver.getCurrentUrl();
    	Assert.assertEquals(expectedURL3,actualURL3);
    	
    	System.out.println("Professional details Change Executed");
    	Reporter.log("Professional details Change Executed");
    }
    
    @Test(dependsOnMethods = {"carrier_profile_change"})
    public void goHome() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div[class='navbar-header edu_logo'] a")).click();
    	String expectedURL = "https://www.edureka.co/";
    	String actualURL = driver.getCurrentUrl();
    	Assert.assertEquals(expectedURL,actualURL);
    }
    
    @Test(dependsOnMethods = {"goHome"})
    public void exploreBlog() throws InterruptedException {
    	Thread.sleep(2000);
    	WebElement cloud_computing = driver.findElement(By.linkText("Cloud Computing"));
    	cloud_computing.click();
    	WebElement aws = driver.findElement(By.xpath("(//a[@aria-label='course_info'])[1]"));
    	aws.click();
    	
    	Thread.sleep(2000);
    	String expectedURL = "https://www.edureka.co/aws-certification-training";
    	String actualURL = driver.getCurrentUrl();
    	Assert.assertEquals(expectedURL,actualURL);
    	System.out.println("Exploring Blog");
    	driver.findElement(By.cssSelector("a[aria-label='Go to Edureka Home Page']")).click();
    }
    
    @Test(dependsOnMethods = {"exploreBlog"})
    public void logout() throws InterruptedException {
    	Thread.sleep(2000);
    	WebElement profileopen = driver.findElement(By.xpath("/html[1]/body[1]/header[1]/nav[1]/div[4]/ul[1]/li[6]"));
    	profileopen.click();
    	WebElement profileclick = driver.findElement(By.linkText("Log Out"));
    	profileclick.click();
    	System.out.println("Log Out Executed");
    	Reporter.log("Log Out Executed");
    }
    
    @DataProvider(name="testingdata")
    public Object[][] testingData(){
		ReadExcelFile config = new ReadExcelFile("G:\\worldline\\taskSample.xlsx");
		
		int rows = config.getRowCount(0);
		int col = config.getColCount(0);
		
		Object[][] credentials = new Object[rows][col];
		
		for(int i=0;i<rows;i++) {
			for(int j=0;j<col;j++) {
				credentials[i][j] = config.getData(0, i+1, j);
			}
		}
		
		return credentials;
	}
    
    @DataProvider(name="testingdata2")
    public Object[][] testingData2(){
		ReadExcelFile config = new ReadExcelFile("G:\\worldline\\taskSample.xlsx");
		
		int rows = config.getRowCount(1);
		int col = config.getColCount(1);
		
		Object[][] credentials = new Object[rows][col];
		
		for(int i=0;i<rows;i++) {
			for(int j=0;j<col;j++) {
				credentials[i][j] = config.getData(1, i+1, j);
			}
		}
		
		return credentials;
	}
    
}