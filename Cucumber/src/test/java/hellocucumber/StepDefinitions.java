package hellocucumber;

import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {


    private String TEACHER_USERNAME = "yakir";
    private String TEACHER_PASSWORD = "1234Yakir#";
    private String COURSE_NAME = "programing";
    private String ADMIN_USERNAME = "admin";
    private String ADMIN_PASSWORD = "Ls1997**";
    private String STUDENT_USERNAME = "student";
    private String STUDENT_PASSWORD = "1234Shira#";
    private String courseName = "programing";
    private int DEFAULT_LENGTH = 5;
    private static MoodleActuator moodle_student;
    private static MoodleActuator moodle_teacher;
    private String webDriver_student = "webdriver.chrome.driver";
    private String webDriver_teacher = "webdriver.chrome.driver";
    private String path = "C:\\Users\\ASUS\\Downloads\\sqe-hw3-main\\sqe-hw3-main\\Selenium\\chromedriver.exe";


    public void moodleInit(int type) {
        System.out.println("--------------- INITIALIZING MOODLE TEST - OPENING WEBPAGE ---------------");
        if (type==0){
            moodle_student = new MoodleActuator();
            moodle_student.initSession(webDriver_student, path);
        }

        if (type==1){
            moodle_teacher = new MoodleActuator();
            moodle_teacher.initSession(webDriver_teacher, path);
        }

    }

    //student submmitins Ass
    @Given("^Student is on Home Page$")
    public void init_s(){
        moodleInit(0);
    }

    @When("^Student Navigate to LogIn Page$")
    public void navigateToLogins(){
        moodle_student.goToLogin();
    }

    @When("^Student enters UserName and Password")
    public void entertStudentLoginInfo(){
        moodle_student.enterLoginInfo(STUDENT_USERNAME, STUDENT_PASSWORD);
    }

    @When("^Student navigates to assignment in course")
    public void go_to_subbmission(){
        moodle_student.myCoursesTab();
        moodle_student.goToCourse(courseName);
    }

    @When("^student submits ass \"([^\"]*)\"")
    public void submitFile(String filePath) {
        moodle_student.submitFile(filePath);
    }

    @Then("^ass added")
    public void isSubmmited(){
        moodle_student.fileSubmmited();
        moodle_student.terminateDriver();
    }

    //teacher deletes Ass/////////////

    @Given("^Teacher is on Home Page$")
    public void init_t(){
        moodleInit(1);
    }

    @When("^Teacher Navigate to LogIn Page$")
    public void navigateToLogint(){
        moodle_teacher.goToLogin();
    }

    @When("^Teacher enters UserName and Password")
    public void entertTeacherLoginInfo(){
        moodle_teacher.enterLoginInfo(TEACHER_USERNAME, TEACHER_PASSWORD);
    }

    @When("^deletes ass")
    public void delete_assignment(){
        moodle_teacher.myCoursesTab();
        moodle_teacher.goToCourse(courseName);
        moodle_teacher.DeleteAssignment();
    }

    @Then("^ass deleted")
    public void ass_deleted(){moodle_teacher.AssDeleted();
    moodle_teacher.terminateDriver();}

    /////////////////

    @Given("^Teacher and Student are on Home Page")
    public void TS_onHomePage(){
        moodleInit(0);
        moodleInit(1);
    }


    private void terminate(){
        System.out.println("in terminate");
        moodle_teacher.terminateDriver();
        moodle_student.terminateDriver();
    }

}
