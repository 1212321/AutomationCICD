Feature: Validiate Place API

@AddPlace
Scenario Outline: verify if place is being successfully addded using AddPlaceAPI

	Given Add Place Payload "<name>"  "<address>" "<language>"
	When user calls "AddPlaceAPI" using http "POST" method request
	Then user check if API call got the status code success as 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify if place_id maps to "<name>" with "getPlaceAPI"
	
	
Examples:
	|	name 					| address 													  | language |
	| Vaibhav Saini | Noida Sec - 52 / Arawali Apartments | English  |
#	| VIck Saini | Noida Sec - 52 / Arawali Apartments, 114-A | Portgeous  |

@DeletePlace
Scenario: Verify if Delete Place functionlity is working

	Given DeletePlace Payload
	When user calls "deletePlaceAPI" using http "POST" method request
	Then user check if API call got the status code success as 200
	And "status" in response body is "OK"
