Feature: Tracking functionalities

  Scenario Outline: tracking CONCILLIATION_REQUEST
    Given the provided shipment
    When shipment reference should be equal to tracking <trackingReference>
    When shipment parcel number should be equal to tracking parcel <trackingParcelNumber>
    When shipment total weight should be less than tracking weight <trackingTotalWeight>
    When tracking <trackingStatus> should be DELIVERED
    Then dispatch an application event reference <eventReference> and status <eventStatus>
    And print it into the console

    Examples:
      | trackingReference | trackingParcelNumber  | trackingTotalWeight | trackingStatus  | eventReference  | eventStatus           |
      | ABCD123456        | 2                     | 100                 | DELIVERED       | ABCD123456      | CONCILLIATION_REQUEST |


  Scenario Outline: tracking NOT_NEEDED
    Given the provided shipment
    When shipment reference should be equal to tracking <trackingReference>
    When shipment parcel number should be equal to tracking parcel <trackingParcelNumber>
    When shipment total weight should be greater or equal than tracking weight <trackingTotalWeight>
    When tracking <trackingStatus> should be DELIVERED
    Then dispatch an application event reference <eventReference> and status <eventStatus>
    And print it into the console

    Examples:
      | trackingReference | trackingParcelNumber  | trackingTotalWeight | trackingStatus  | eventReference  | eventStatus           |
      | ABCD123456        | 2                     | 1                   | DELIVERED       | ABCD123456      | NOT_NEEDED |


  Scenario Outline: tracking INCOMPLETE from DELIVERED
    Given the provided shipment
    When shipment reference should be equal to tracking <trackingReference>
    When tracking <trackingStatus> is not DELIVERED
    Then dispatch an application event reference <eventReference> and status <eventStatus>
    And print it into the console

    Examples:
      | trackingReference | trackingStatus  | eventReference  | eventStatus |
      | ABCD123456        | WAITING_IN_HUB  | ABCD123456      | INCOMPLETE  |


  Scenario Outline: tracking INCOMPLETE FROM OTHER
    Given the provided shipment
    When shipment reference should be equal to tracking <trackingReference>
    When any other tracking field is null
    Then dispatch an application event reference <eventReference> and status <eventStatus>
    And print it into the console

    Examples:
      | trackingReference | eventReference  | eventStatus |
      | ABCD123456        | ABCD123456      | INCOMPLETE  |


  Scenario Outline: tracking NOT_FOUND
    Given the provided shipment
    When tracking <trackingReference> is not equal to shipment reference
    Then dispatch an application event reference <eventReference> and status <eventStatus>
    And print it into the console

    Examples:
      | trackingReference | eventReference  | eventStatus |
      | EFGH123456        | EFGH123456      | NOT_FOUND   |