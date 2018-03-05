Feature: Login API

  Scenario Outline: Verify login with valid credentials
    Given The API's are up and running for Hathway
    And User wants to request <API>
    And User sets paramters for Login Info API <Response Language> <Search ID> <Search Type>
    When User perform GET request
    Then User should see the JSON response
    And The response code should be <Response Code>
    And Response schema should be as per expected schema <SchemaPath>
    And Login Info API response should match with database data
    Examples:
      | API         | Response Language | Search ID    | Search Type | Response Code | SchemaPath               |
      | "Login API" | "english"         | "1025198041" | "acc"       | 200           | "schemas/LoginInfo.json" |
#      | "Login API" | "english"         | "9980077556" | "rmn"       | 200           | "schemas/LoginInfo.json" |
#      | "Login API" | "english"         | "000122066186"      | "VC No"     | 200           | "schemas/LoginInfo.json" |
#      | "Login API" | "english"         | "38:C8:5C:77:DC:D4" | "MAC ID"    | 200           | "schemas/LoginInfo.json" |

