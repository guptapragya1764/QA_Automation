Feature: As a user, I want to use various apis

Scenario: I want to create user through api

 Given I have end point "/Account/v1/User" and request type is "POST"
# Given I have json body request in "src/test/java/read.json" and data file is "Book1.xlsx"
 Given I have json body request in "src/test/java/read.json"
 Then Send request to server
 Then expected response code should be 400
 Then expected response code should be follow:
 """
 {
    "code": "1200",
    "message": "UserName and Password required."
}
  """

# Given Create request from file "Book1.xlsx"

