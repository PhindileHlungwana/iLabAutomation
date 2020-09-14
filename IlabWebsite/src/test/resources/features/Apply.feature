Feature: Apply for a job

  Scenario: Populate the application form
    Given the browser is open
    And User navigates to Apply in Careers page South Africa
    When User enters <name> <email> and <phone>
    And user clicks on apply
    Then ErrorMessage text is displayed
    
    Examples: 
    |name|email|phone|
    |Phindi|someone@gmail.com|0731234567|
    |sarah|somemail@gmail.com|0731234565|
