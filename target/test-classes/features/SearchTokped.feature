Feature: Tokopedia Search
	
	#Declarative Programming
  Scenario: Test menu search on tokopedia and count result product displayed
    Given User has accessed tokopedia site 
    When User input product on search bar "beatles"
    And Scroll down page until all product displayed
    Then Validate total product displayed