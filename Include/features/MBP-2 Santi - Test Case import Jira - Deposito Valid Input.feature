@Deposito

Feature: Deposito Transaction Feature

As a user, I want to add amount to my account (deposito).

 

@Valid

Scenario Outline: Deposito with a valid input

When I click menu Make a Deposito

And I choose dropdown Account

And I set text total amount to deposito

Then my total amount should be increase

Examples:

| account | amount |

| BCA | 100000 |