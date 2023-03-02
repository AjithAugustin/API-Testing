@Address
Feature: Address Module API Automation

  Scenario Outline: Verify add user address to the application through api
    Given User add header and bearer autherization for accessing address endpoint
    When User add request body for add new address "<first_name>","<last_name>","<mobile>","<appartment>","<country>","<zipcode>","<address>" and "<addressType>"
    And User send "POST" request for addUserAddress endpoint
    Then User verify the status code is 200
    Then User verify the address response message matches "Address added successfully"

    Examples: 
      | first_name | last_name | mobile     | appartment | country | zipcode | address | addressType |
      | Ajith      | Augustin  | 8547062549 | KDHP       |     101 |  685612 | Munnar  | Home        |

  Scenario Outline: Verify update user address to the application through api
    Given User add header and bearer autherization for accessing updateUserAddress endpoint
    When User add request body for update new address "<first_name>","<last_name>","<mobile>","<appartment>","<country>","<zipcode>","<address>" and "<addressType>"
    And User send "PUT" request for updateUserAddress endpoint
    Then User verify the status code is 200
    Then User verify the updateUserAddress response message matches "Address updated successfully"

    Examples: 
      | first_name | last_name | mobile     | appartment | country | zipcode | address | addressType |
      | Ajith      | Augustin  | 8428965801 | KAP        |     101 |  685612 | Munnar  | Home        |

  Scenario: Verify get user address to the application through api
    Given User add header and bearer autherization for accessing getUserAddress endpoint
    When User send "GET" request for getUserAddress endpoint
    Then User verify the status code is 200
    Then User verify the getUserAddress response message matches "OK"

  Scenario: Verify delete user address to the application through api
    Given User add header and bearer autherization for accessing deleteUserAddress endpoint
    When User add request body for delete address
    And User send "DELETE" request for deleteUserAddress endpoint
    Then User verify the status code is 200
    Then User verify the deleteUserAddress response message matches "Address deleted successfully"
