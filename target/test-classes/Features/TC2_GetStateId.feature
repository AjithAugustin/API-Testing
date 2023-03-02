@State
Feature: State Module API Automation

Scenario: verify user get ststelist through api
Given User add headers for to statelist
When User send "GET" request for statelist endpoint
Then User verify the status code is 200
Then User verify the statelist response message matches "Kerala" and saved the state id
