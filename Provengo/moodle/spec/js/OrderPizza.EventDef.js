/* @Provengo summon selenium */
/**
 *  The ComposeQuery event defines the selenium actions for writing the query that was given in the parameter e.
 */

defineEvent(SeleniumSession, "login", function(session, e){
  session.click("/html/body/div[2]/nav/div[2]/div/div/span/a");
  session.writeText("/html/body/div[2]/div[2]/div/div/section/div/div/div/div/form[1]/div[1]/input", e.user);
  session.writeText("/html/body/div[2]/div[2]/div/div/section/div/div/div/div/form[1]/div[2]/input", e.password);
  session.click("/html/body/div[2]/div[2]/div/div/section/div/div/div/div/form[1]/div[3]/button");
})

defineEvent(SeleniumSession, "deleteAssignment", function(session, e){
  // edit mode on
  session.click("//*[@type='checkbox' and @name='setmode']");
  session.click("/html/body/div[5]/div[5]/div/div[3]/div/section/div/div/div/ul/li[1]/div[2]/ul/li[2]/div/div[1]/div[2]/div/div/div/div/a/i");
  session.click("/html/body/div[5]/div[5]/div/div[3]/div/section/div/div/div/ul/li[1]/div[2]/ul/li[2]/div/div[1]/div[2]/div/div/div/div/div/a[6]");
  session.click("//button[@class='btn btn-primary'][@data-action='save']");
})

defineEvent(SeleniumSession, "accessCourse", function(session, e){
  session.click("/html/body/div[3]/nav/div[1]/nav/ul/li[3]/a");
  session.click("/html/body/div[3]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div[1]/div/div/a/span[3]");
})



defineEvent(SeleniumSession, "submitAssignment", function(session, e){
  //enter ass page
  session.click("/html/body/div[3]/div[4]/div/div[3]/div/section/div/div/div/ul/li[1]/div[2]/ul/li[2]/div/div[1]/div/div[1]/div/div[2]/div[2]/a");
  //add sub
  session.click("/html/body/div[3]/div[4]/div/div[2]/div/section/div[2]/div[1]/div/div/div/form/button");
  // open files dialog
  session.waitForVisibility("/html/body/div[3]/div[4]/div/div[2]/div/section/div[2]/div/form/fieldset/div[2]/div/div[2]/fieldset/div/div[2]/div[1]/div[1]/div[1]/a")
  session.click("/html/body/div[3]/div[4]/div/div[2]/div/section/div[2]/div/form/fieldset/div[2]/div/div[2]/fieldset/div/div[2]/div[1]/div[1]/div[1]/a")
  //select file apple
  session.click("/html/body/div[8]/div[3]/div/div[2]/div/div/div[1]/div[4]/a/span")
  session.writeText("/html/body/div[8]/div[3]/div/div[2]/div/div/div[2]/div/div[2]/div/div/form/div/div[1]/input", "apple");
  session.click("/html/body/div[8]/div[3]/div/div[2]/div/div/div[2]/div/div[2]/div/div/form/p/button");
  session.click("/html/body/div[8]/div[3]/div/div[2]/div/div/div[2]/div/div[2]/div[1]/a[1]/div[1]/div[3]");
  session.click("/html/body/div[9]/div[3]/div/div[2]/div/div[2]/form/div[4]/div/button[1]");

  // submit
  session.waitForVisibility("/html/body/div[4]/div[4]/div/div[2]/div/section/div[2]/div/form/div[2]/div[2]/fieldset/div/div[1]/span/input")
  session.click("/html/body/div[4]/div[4]/div/div[2]/div/section/div[2]/div/form/div[2]/div[2]/fieldset/div/div[1]/span/input")
})
