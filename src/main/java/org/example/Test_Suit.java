package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Test_Suit {

    static String expectedRegistrationCompleteMsg = "Thank for registration";
    static String expectedAbleToVoteMessage = "Only registered users can vote.";
    static String expectedCompareProductMessage = "can not compare products";
static String expectedEmailToFriend="Only registed user can vote ";
static String expectedElectronics = "product not adding";
static String expectedCommunityPollVoteMessage = "Error message is disappearing.";
    //  @BeforeMethod
    @BeforeMethod
    public static void openBrowser() {
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public static void closeBrowser() {
        driver.close();
    }

    @Test
    public static void verifyUserShoudBeAbleToRegisterSuccessfully() {
//
        //click on registration button
        clickOnElement(By.className("ico-register"));
        //type firstname

        typeText(By.id("FirstName"), "TestFirstName");
        //type lastname

        typeText(By.id("LastName"), "TestLastName");
        //type email address

        typeText(By.name("Email"), "testEmail" + timestamp() + "@gmail.com");
        //type password

        typeText(By.id("Password"), "test123");
        //type confirm password

        typeText(By.id("ConfirmPassword"), "test123");
        //click on register button

        clickOnElement(By.id("register-button"));
        //type message
        String actualMessage = getTextFromElement(By.xpath("//div[@class='result']"));
        System.out.println("MY message:" + actualMessage);
        Assert.assertEquals(actualMessage, expectedRegistrationCompleteMsg, "registration is not working");

        //close browser
//        driver.close();


    }

    protected static WebDriver driver;

    public static void clickOnElement(By by) {
        driver.findElement(by).click();}

    public static void typeText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public static String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    public static Long timestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();}
    @Test
            public static  void verifyUserShouldBeAbleToVote  ()     {
        clickOnElement(By.className("ico-register"));
        //type first name
        typeText(By.id("FirstName"), "ram");
        //type last name
        typeText(By.id("LastName"), "patel");
        //type email address
        typeText(By.name("Email"), "ram12@gmail.com");
        //type password
        typeText(By.id("Password"), "ram12345");
        //type confirm password
        typeText(By.id("ConfirmPassword"), "ram12345");
        //click on register submit button
        clickOnElement(By.id("register-button"));
        //click on login button
        clickOnElement(By.className("ico-login"));
        //Type  email address
        //   typeText(By.xpath("//div[@class='form-fields']/div[1]/input"),"raml123@gmail.com");
      //  typeText(By.id("Email"), "raml1234@gmail.com");
        typeText(By.id("Email"), "ram12@gmail.com");
        // Enter Password
        typeText(By.id("Password"), "ram12345");
        clickOnElement(By.xpath("//div[@class=\"buttons\"]//button[@class=\"button-1 login-button\"]"));
                //click on good
        clickOnElement(By.id("pollanswers-2"));
        // Do Vote
        clickOnElement(By.id("vote-poll-1"));
        String actualMessage = getTextFromElement(By.xpath("//span[@class=\"poll-total-votes\"]"));
        System.out.println("My Message:"+actualMessage);
        // Expected message
        Assert.assertEquals(actualMessage,expectedAbleToVoteMessage, "Only registered users can vote.");}


    @Test
    public static void verifyUserShouldBeAbleToSendEmail(){
        //click on Apple MacBook
        clickOnElement(By.linkText("Apple MacBook Pro 13-inch"));
        //click email a friend
        clickOnElement((By.className("email-a-friend")));
        //type friend email
        typeText(By.className("friend-email"),"001+"+timestamp()+"@gmail.com");
        //type your email
        typeText(By.className("your-email"),"002+"+timestamp()+"@mail.com");

        typeText(By.id("PersonalMessage"),"here is your mac");
        //click on send mail
        clickOnElement(By.name("send-email"));
        String actualMessage = getTextFromElement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]"));
        System.out.println("my message: " +actualMessage);
        Assert.assertEquals(actualMessage,expectedEmailToFriend,"Only registed user can vote");
    }

            @Test
            public static void verifyUserBeAbleToCompareProduct(){

                clickOnElement(By.linkText("HTC One M8 Android L 5.0 Lollipop"));
                //click compare button
                clickOnElement(By.xpath("//div[@class='compare-products']/button"));
                //select product
                clickOnElement(By.linkText("Gift Cards"));
                clickOnElement(By.linkText("$25 Virtual Gift Card"));
                clickOnElement(By.xpath("//div[@class='compare-products']/button"));
                //product comparison
                clickOnElement(By.linkText("product comparison"));
                String actualMessage = getTextFromElement(By.linkText("HTC One M8 Android L 5.0 Lollipop"));
                System.out.println("My Message :" + actualMessage);
                String actualMessage1 = getTextFromElement(By.linkText("$25 Virtual Gift Card"));
                System.out.println("My Message :" + actualMessage1);

                clickOnElement(By.className("clear-list"));
                String actualMessage2 = getTextFromElement(By.className("no-data"));
                System.out.println("MyMessage :" + actualMessage2);
                Assert.assertEquals(actualMessage2, expectedCompareProductMessage, "Can not comparing product");
            }
    @Test
    public static void verifyUserBeAbleToAddElectronicsItems(){
        //click on electronics
        clickOnElement((By.linkText("Electronics")));
        //click on camera and photo
        clickOnElement(By.linkText("Camera & photo"));
        //add digital camera
        clickOnElement(By.linkText("Leica T Mirrorless Digital Camera"));
        clickOnElement(By.id("add-to-cart-button-16"));
        //click on shopping cart
        clickOnElement(By.linkText("Shopping cart"));
        String actualMessage=getTextFromElement(By.xpath("//*[@id=\"shopping-cart-form\"]/div[1]/table/tbody/tr/td[3]"));
        System.out.println("my message;"+actualMessage);
        Assert.assertEquals(actualMessage,expectedElectronics,"product not adding");
    }
    @Test
            public static void verifyUserShouldRefferTotheFriend(){
    clickOnElement(By.className("ico-register"));
    //type first name
    typeText(By.id("FirstName"), "ram");
    //type last name
    typeText(By.id("LastName"), "patel");
    //type email address
    typeText(By.name("Email"), "ram1234@gmail.com");
    //type password
    typeText(By.id("Password"), "ram1234");
    //type confirm password
    typeText(By.id("ConfirmPassword"), "ram1234");
    //click on register submit button
    clickOnElement(By.id("register-button"));
    //click on login button
    clickOnElement(By.className("ico-login"));
    //Type  email address
    //   typeText(By.xpath("//div[@class='form-fields']/div[1]/input"),"raml123@gmail.com");
    typeText(By.id("Email"), "ram1234@gmail.com");
    }
    @Test
            public static void VerifyUserShouldAbleToVote(){
    //click on good button
    clickOnElement(By.id("pollanswers-2"));
    //Click on vote button
    clickOnElement(By.id("vote-poll-1"));
    //Use the selenium wait
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
    //get the text message for web element
        wait.until (ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"poll-vote-error\"]")));
    String actualMessage = getTextFromElement(By.xpath("//div[@class=\"poll-vote-error\"]"));
        System.out.println("My message:" + actualMessage);
    //text message  is disappearing
        Assert.assertEquals(actualMessage, expectedCommunityPollVoteMessage, "Error message is disappearing.");




        }}





