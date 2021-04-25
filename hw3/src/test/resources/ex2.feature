Feature: Ex2

  Background:
    When I open test site by URL
    Then Home page is opened
    When I log in as "Roman" - "Jdi1234"
    Then I logged in as "ROMAN IOVLEV"

  Scenario: Assert browser title
    Then Browser title is "Home Page"

  Scenario: Assert Header Service category contents
    When I click on Service subcategory in the Header
    Then There are 9 elements with proper texts in the Header

  Scenario: Assert Left Section Service category contents
    When I click on Service subcategory in the Left Section
    Then There are 9 elements with proper texts int the Left Section

  Scenario: Assert Different Elements page contents
    When I click on Different Elements page in Service subcategory
    Then "Different Elements" page is opened

    Then There are 4 checkboxes
    And There are 4 radiobuttons
    And There is a dropdown
    And There are 2 buttons
    And There is the Right Section
    And There is the Left Section

    When I select Water and Wind checkboxes
    Then Checkboxes are selected
    And For each checkbox there is an individual log row and value is corresponded to the status of checkbox

    When I select Selen radiobutton
    Then There is a log row and value is corresponded to the status of radiobutton

    When I select Yellow in dropdown
    Then There is a log row and value is corresponded to the selected value

    When I unselect checkboxes
    Then Checkboxes are unselected
    And For each checkbox there is an individual log row and value is corresponded to the unselected status of checkbox