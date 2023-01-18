package Moodle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class MoodleActuator {
    private WebDriver driver;
    private WebDriverWait wait;


public void initSession(String webDriver, String path){
    // webDriver = "webdriver.chrome.driver"
    // path = "C:\\Users\\eylon\\Downloads\\chromedriver_win32\\chromedriver.exe";
    System.setProperty(webDriver, path);

    // new chrome driver object
    this.driver = new ChromeDriver();

    // new web driver wait -> waits until element are loaded (40 sec max)
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));


    // launch website -> localhost
    driver.get("http://localhost/");

    // maximize the window - some web apps look different in different sizes
    driver.manage().window().maximize();


    /*
        If we wanted to test the web application on different devices -
            1. Open the web app
            2. Right click -> click inspect
            3. Click on the phone icon at the top left corner of the inspect window (the app changes preview format at this point)
            4. Locate the dimensions drop-down list at the top of the web app and choose device
            5. Copy dimensions size (on the right side of the drop-down list)
               -> driver.manage().window().setSize(new Dimension(width, height));
     */

    System.out.println("Driver setup finished for - " + driver.getTitle());
}
    public void goToLogin(){
        // locate and click on web element -> login
        driver.findElement(By.linkText("Log in")).click();
    }

    public void enterLoginInfo(String username, String password) {
        // locate the username input box and enter username
        // $x("//*[@id='username']")
        // driver.findElement(By.xpath("//*[@id='username']")).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']"))).sendKeys(username);

        // locate the password input box and enter password
        // $x("//*[@name='password' and @type='password']")
        driver.findElement(By.xpath("//*[@name='password' and @type='password']")).sendKeys(password);

        // locate Log in button and press
        // $x("//*[@id='loginbtn']")
        driver.findElement(By.id("loginbtn")).click();
    }

    public void teacherWelcomeMessage(){
        // now to check if login process succeeded -> find the element which indicates it succeeded
        // $x("//*[contains(text(),'Welcome, Teacher!')]")
        driver.findElement(By.xpath("//*[contains(text(),'Welcome back, Teacher!')]"));
    }

    public void loginSequence(String username, String password){
        // locate and click on web element -> login
        goToLogin();

        // enter username and password -> press login
        enterLoginInfo(username, password);

        // check for welcome message
        teacherWelcomeMessage();
    }

    public void logout(){
        // find user menu -> click on it
        driver.findElement(By.id("user-menu-toggle")).click();

        // find log out button in drop down menu -> click on it
        driver.findElement(By.linkText("Log out")).click();

    }

    public void logoutAll() throws InterruptedException {
        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();

        //Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            driver.wait(5);
            logout();
        }
    }

    public void checkNotLoggedIn(){
        // to make sure we logged out -> find the login button again
        // $x("//*[contains(text(),'Log in')][1]")
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Log in')][1]")));

    }

    public void checkNotLoggedInAll() throws InterruptedException {
        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();
        System.out.println("num of windows" + driver.getWindowHandles().size());
        //Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            driver.wait(5);

            checkNotLoggedIn();
        }
    }

    public void logoutAndCheck(){
        logout();
        checkNotLoggedIn();
    }

    public void editModeOn(){
        // find edit mode switch -> click on it
        // $x("//*[@type='checkbox' and @name='setmode']")
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='checkbox' and @name='setmode']"))).click();
    }

    public void myCoursesTab(){
        // example of how we can save the element and then operate with it
        // find my courses -> click on it
        // $x("//*[contains(text(),'My courses') and @role='menuitem']")
        WebElement myCoursesTab = driver.findElement(By.xpath("//*[contains(text(),'My courses') and @role='menuitem']"));
        myCoursesTab.click();
    }

    public void goToCourse(String courseName){
        // find course -> click on it
        // $x("//*[@class='multiline' and contains(text(),'Demo course')]")[0].click()
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='multiline' and contains(text(),'" + courseName + "')]"))).click();
    }

    public void addAssignment(){
        editModeOn();
        // click on add an activity or resource
        // $x("//*[contains(text(),'Add an activity or resource')]")[0]
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(text(),'Add an activity or resource')]"))).get(0).click();

        // click on quiz option
        // $x("//*[contains(text(),'Quiz')]").at(0).click()
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(text(),'Assignment') and @class='optionname clamp-2']"))).get(0).click();
        System.out.println("done1");
        String AssName = createRandomString(8);
        // choose quiz name
        // $x("//*[@id='id_name']")
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='id_name']"))).sendKeys(AssName);
        System.out.println("done2");

        // finish process - click on save and return to course
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_submitbutton2"))).click();
        System.out.println("done3");

    }


    // Generates random strings according to desired length
    // Used when we want to initialize multiple objects and need unique names
    private String createRandomString(int stringlength){

        // case of empty string
        if(stringlength < 1)
            return "";
        Random rnd = new Random();
        StringBuilder newString = new StringBuilder(stringlength);

        // first letter is capitalized
        newString.append((char)('A' + rnd.nextInt(26)));

        // rest of letters are lower cased
        for(int i = 0; i < stringlength - 1; i++){
            char c = (char) ('a' + rnd.nextInt(26));
            newString.append(c);
        }
        return newString.toString();
    }

    private String createRandomSentence(int wordCount){
        if(wordCount < 1)
            return "";

        Random rnd = new Random();
        StringBuilder newString = new StringBuilder(wordCount);

        // rest of letters are lower cased
        for(int i = 0; i < wordCount; i++){
            String word = createRandomString(5);
            newString.append(word);
            newString.append(" ");
        }
        return newString.toString();
    }

    public void terminateDriver(){
        // close all the driver windows
        // another option - to close a browser window: driver.close();
        driver.quit();

    }

    // submits quiz and exits quiz review and goes back to course page
    public void finishQuiz(){

        // finish attempt -> click on submit button - opens a small window -> click on submit button in new page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(text(),'Submit all and finish')])[1]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(text(),'Submit all and finish')])[2]"))).click();

        // click on finish review
        // $x("//*[contains(text(),'Finish review')]")[1].click()
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(text(),'Finish review')])[1]"))).click();

        // click on course tab to go back to course page
        // $x("//a[@title='Demo course']")[0].click()
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@title='Demo course']))[0]"))).click();
    }


    public void generalWelcomeMessage(String welcomeWord) {
        // now to check if login process succeeded -> find the element which indicates it succeeded
        // $x("//*[contains(text(),'Welcome, <welcomeWord>!')]")
        driver.findElement(By.xpath("//*[contains(text(),'Welcome back, " + welcomeWord + "!')]"));
    }

    public void TeacherCreatsAssBox(){

    }
    public void goToQuestionBank(){
        // make sure page loaded
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("General")));

        // Got to drop-down menu and open it (click on it)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("More"))).click();

        // go to question bank (click on question bank in drop down menu)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Question bank"))).click();
    }
    private void addMultipleChoiceDetails(int questionNum) {
        // enter question name
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_name"))).sendKeys("Demo multiple choice question #" + questionNum);

        // enter question text
        driver.findElement(By.id("id_questiontexteditable")).sendKeys("Pick the correct value for " + createRandomString(5) + ":");

        // select how to number the answers
        Select numbering = new Select(driver.findElement(By.id("id_answernumbering")));
        numbering.selectByValue("123");

        // enter 4 optional answers
        for(int i = 0; i < 5; i++){
            driver.findElement(By.id("id_answer_" + i + "editable")).sendKeys(String.valueOf(10 * i));
        }

        // select in grade dropdown list (here we choose this as the only answer -> gives 100% of the grade)
        Select grade = new Select(driver.findElement(By.id("id_fraction_4")));
        grade.selectByValue("1.0");

    }

    public void addMultipleChoiceQuestions(int numOfQuestions){

        goToQuestionBank();

        for(int j = 0; j < numOfQuestions; j++){
            // click on - create a new question ...
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='submit' and @class='btn btn-secondary']"))).click();

            // select multiple choice question
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("item_qtype_multichoice"))).click();

            // click on add
            driver.findElement(By.xpath("//*[@class='submitbutton btn btn-primary' and @name='submitbutton']")).click();

            addMultipleChoiceDetails(j);

            // save changes
            driver.findElement(By.id("id_submitbutton")).click();
        }

        // go back to course
        driver.findElement(By.linkText("Course")).click();

    }
public void addQuiz(){
        editModeOn();
        // click on add an activity or resource
        // $x("//*[contains(text(),'Add an activity or resource')]")[0]
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(text(),'Add an activity or resource')]"))).get(0).click();

        // click on quiz option
        // $x("//*[contains(text(),'Quiz')]").at(0).click()
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(text(),'Quiz') and @class='optionname clamp-2']"))).get(0).click();

        String quizName = createRandomString(8);
        // choose quiz name
        // $x("//*[@id='id_name']")
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='id_name']"))).sendKeys(quizName);

        String quizDescription = createRandomSentence(8);
        // add quiz description
        // $x("//*[@id='id_introeditoreditable']")
        driver.findElement(By.xpath("//*[@id='id_introeditoreditable']")).sendKeys(quizDescription);

        // find Grade tab
        driver.findElement(By.linkText("Grade")).click();

        // add minimum passing grade (from 1 - 10)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_gradepass"))).sendKeys("10");

        // find attempts tab
        driver.findElement(By.id("id_attempts")).click();

        // add maximum number of attempts allowed
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[5]/div[5]/div/div[3]/div/section/div/form/fieldset[3]/div[2]/div[3]/div[2]/select/option[6]"))).click();

        // finish process - click on save and return to course
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_submitbutton2"))).click();

        // go to quiz page
        // $x("(//a[@class=' aalink stretched-link'])[2]")
        wait.until((ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@class=' aalink stretched-link'])[2]")))).click();

        addAllQuestionsToQuize();

        // go back to quiz page
        driver.findElement(By.linkText("Quiz")).click();

        // start quiz - click on - Preview quiz
        // $x("//*[contains(text(),'Preview quiz')]")
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Preview quiz')]"))).click();
    }
public void addAllQuestionsToQuize(){
        // add question to quiz
        wait.until((ExpectedConditions.presenceOfElementLocated(By.linkText("Add question")))).click();
        // open add menu
        wait.until((ExpectedConditions.presenceOfElementLocated(By.linkText("Add")))).click();
        // select "from question bank"
        wait.until((ExpectedConditions.presenceOfElementLocated(By.linkText("from question bank")))).click();

        // mark V in checkbox - select all
        wait.until((ExpectedConditions.presenceOfElementLocated(By.id("qbheadercheckbox")))).click();
        driver.findElement(By.xpath("//*[@type='submit' and @name='add']")).click();

        // $x("//*[@type='submit' and @value='Save']")
        // click on - Add selected questions to the quiz
        wait.until((ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='submit' and @value='Save']")))).click();

    }




}