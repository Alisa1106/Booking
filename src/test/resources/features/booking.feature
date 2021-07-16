Feature: Search on Booking.com

  Scenario: Search by city
    Given User is looking for hotels in 'Minsk' city
    When User does search
    Then Hotel 'Hostel Urban' should be on the search results page
    Then Hotel 'Hostel Urban' rating is '9,2'

  Scenario Outline: Search by city two
    Given User is looking for hotels in '<City>' city
    When User does search
    Then Hotel '<HotelName>' should be on the search results page
    Then Hotel '<HotelName>' rating is '<Rating>'

    Examples:
      | City  | HotelName               | Rating |
      | Minsk | Hostel Urban            | 9,2    |
      | Brest | Hampton by Hilton Brest | 9,3    |
      | Gomel | Villa Rosa              | 8,3    |