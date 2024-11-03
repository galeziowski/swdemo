Feature: Schedule
  Scenario: Check presence of schedule slot
    When I get schedule slots
    Then I receive a correct response
    Then Schedule list contains entry for year 2024, month 11, day 1, hour 8, minute 30 and vetId "1"