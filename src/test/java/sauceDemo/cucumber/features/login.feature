Feature: Login Page Aplikasi Sauce Demo

  Scenario: Success Login
    Given Halaman login sauce Demo
    When input valid username
    And input valid password
    And click login button
    Then User in on home page

  Scenario: Failed Login
    Given Halaman login sauce Demo
    When input valid username
    And input Invalid password
    And click login button
    Then User get error message