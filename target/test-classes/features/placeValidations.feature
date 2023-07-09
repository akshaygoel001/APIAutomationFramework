#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Validating Place API's
  I want to check each functionality of Place API

  @AddPlace
  Scenario Outline: Verify if Add Place is working using Add Place API 
    Given Add Place Payload with "<name>" , "<language>" and "<address>"
    When User calls "AddPlaceAPI" with HTTP "POST" request
    Then API call got success with status code 200
    And "status" in response body is "OK" 
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "GetPlaceAPI"
  Examples:
		|name     |language  |address              |
		|Ayodhya	|English-IN|Ram Mandir				   | 
#		|Lucknow  |French-IN |22,side lane, Lucknow| 
# 	|Noida    |Spanish-IN|24,side lane, Noida  | 
   
  @DeletePlace  
  Scenario: Verify if Delete Place is working using Delete Place API  
		Given Delete Place Payload with place_id
    When User calls "DeletePlaceAPI" with HTTP "DELETE" request
    Then API call got success with status code 200
    And "status" in response body is "OK" 
    
    
    
    
    
    
    