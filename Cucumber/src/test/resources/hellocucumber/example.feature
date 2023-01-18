Feature: Assignment Submission


  Scenario Outline: student submits assignment
    Given Student is on Home Page
    When Student Navigate to LogIn Page
    And Student enters UserName and Password
    And Student navigates to assignment in course
    And student submits ass <filepath>
    Then ass added
    Examples:
      | filepath |
      | "C:\\Users\\ASUS\\Desktop\\Demo_Test2\\Demo_Test2\\file.txt"  |

  Scenario Outline: teacher deletes assignment
    Given Teacher is on Home Page
    When Teacher Navigate to LogIn Page
    And Teacher enters UserName and Password
    And deletes ass
    Then ass deleted
    Examples:
      | UserName | Password    | WelcomeWord |
      | "shira"  | "Falafel2$" | "shira"       |

  Scenario Outline: student submits assignment and teacher deletes assignment
    Given Teacher and Student are on Home Page
    When Teacher Navigate to LogIn Page
    And Teacher enters UserName and Password
    And Student Navigate to LogIn Page
    And Student enters UserName and Password
    And Student navigates to assignment in course
    And student submits ass <filepath>
    Then ass added
    And deletes ass
    Then ass deleted
    Examples:
      | filepath |
      | "C:\\Users\\ASUS\\Desktop\\Demo_Test2\\Demo_Test2\\file.txt"  |

