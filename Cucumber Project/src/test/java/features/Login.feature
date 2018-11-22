Feature: Application Login

Scenario: Login with valid credentials
Given User is on landing page
When User login with username <userid> and password <password>
Then Home page is dispalyed