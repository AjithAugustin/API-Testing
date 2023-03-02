@SearchProduct
Feature: Search Product Module API Automation

  Scenario Outline: Verify search product to the application through api
    Given User search product for accessing searchProduct endpoint
    When User add request body for search product "<text>"
    And User send "POST" request for searchProduct endpoint
    Then User verify the status code is 200
    Then User verify the searchProduct response message matches "OK"

    Examples: 
      | text |
      | nuts |
