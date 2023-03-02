@City
Feature: City Module API Automation

  Scenario: verify user get citylist through api
    Given User add headers for to citylist
    When User add request body for citylist and enter stateId
    And User send "POST" request for citylist endpoint
    Then User verify the status code is 200
    Then User verify the citylist response message matches "Munnar" and saved the city id

    
