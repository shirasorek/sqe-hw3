/* @provengo summon selenium */

/**
 * This story opens a new browser window, goes to google.com, and searches for a pizza.

*/

story('Student submits Assignment', function () {
  // the "with" statement makes it redundant to write "s." before each call to a defined event (like the story above)
  with (new SeleniumSession().start('http://localhost/')) {

     login({user: 'student', password: '1234Shira#'})
     accessCourse()
     submitAssignment()

  }
})

story('Teacher deletes Assignment', function(){
  with (new SeleniumSession().start('http://localhost/')){
  login({user: 'yakir', password: '1234Yakir#'})
  accessCourse()
  deleteAssignment()
  }
})
