@Login
Feature: Login Module API Atomation

Scenario: Get user logtoken from login endpoint
Given User add header
When User add basic authentication for login
And User send "POST" request for login endpoint
Then User verify the status code is 200
Then User verify the login response body firstname present as "Ajith" and get the logtoken saved
