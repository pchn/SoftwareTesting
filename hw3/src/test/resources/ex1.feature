Feature: Ex1

  Background:
    When I open test site by URL
    Then Home page is opened
    When I log in as "Roman" - "Jdi1234"
    Then I logged in as "ROMAN IOVLEV"


  Scenario: Assert browser title
    Then Browser title is "Home Page"

  Scenario: Assert header section contents
    Then Header section contains expected items

  Scenario: Assert index page contents
    Then Index page has 4 images
    And Images are displayed
    Then There are 4 texts under images
    And They have proper text

  Scenario: Assert texts of the main headers
    Then Text is displayed and equals to expected result

  Scenario: Assert center iframe
    Then IFrame is displayed
    When I switch to the iframe
    Then There is epam-logo
    And I switch to original window back

  Scenario: Assert sub header
    Then Sub header text is "JDI GITHUB"
    And Sub header text is a link "https://github.com/epam/JDI"

  Scenario: Assert that there is a Left Section
    Then There is a Left Section

  Scenario: Assert that there is a Footer
    Then There is a Footer


