Feature: Integer Arithmetic Expressions
  This feature provides a range of scenarios corresponding to the
  intended external behaviour of arithmetic expressions on integers.

  # This is just a comment.
  # You can start with a Background: that will be run before executing each scenario.

  Background:
    Given I initialise a calculator

  # Each scenario can be seen as a test that can be executed with JUnit,
  # provided that each of the steps (Given, When, And and Then) are
  # implemented in a Java mapping file (CalculatorSteps.Java)

  Scenario: Adding two integer numbers
    Given an integer operation '+'
    When I provide a first number 4
    And I provide a second number 5
    Then the operation evaluates to 9

  Scenario: Subtracting two integer numbers
    Given an integer operation '-'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 2

  Scenario: Multiplying two integer numbers
    Given an integer operation '*'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 35

  Scenario: Dividing two integer numbers
    Given an integer operation '/'
    When I provide a first number 7
    And I provide a second number 2
    Then the operation evaluates to 3.5

  Scenario: Modulus two integer numbers
    Given an integer operation '%'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 2

  Scenario: pgcd two integer numbers
    Given an integer operation 'pgcd'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 1

    Scenario: ppcm two integer numbers
    Given an integer operation 'ppcm'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 35

    Scenario: prime two integer numbers
    Given an integer operation 'prime'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 1

  Scenario: prime two integer numbers
    Given an integer operation 'prime'
    When I provide a first number 4
    And I provide a second number 2
    Then the operation evaluates to 0

    Scenario: pow two integer numbers
    Given an integer operation '^'
    When I provide a first number 4
    And I provide a second number 2
    Then the operation evaluates to 16

  Scenario: combinatorial two integer numbers
    Given an integer operation 'comb'
    When I provide a first number 4
    And I provide a second number 2
    Then the operation evaluates to 6

  Scenario: eucledian two integer numbers
    Given an integer operation 'gcd'
    When I provide a first number 4
    And I provide a second number 2
    Then the operation evaluates to 2

  Scenario: euclidian division two integer numbers
    Given an integer operation '//'
    When I provide a first number 4
    And I provide a second number 2
    Then the operation evaluates to 0



  Scenario: Adding two variables
    Given an integer operation '+'
    When I provide a first variable a containing the value 4
    And I provide a second variable b containing the value 5
    Then the operation evaluates to 9

  Scenario: Printing the modulus of two integer numbers
    Given the modulus of two numbers 8 and 6
    Then its INFIX notation is ( 8 % 6 )
    And its PREFIX notation is % (8, 6)
    And its POSTFIX notation is (8, 6) %

  Scenario: Printing the pgcd of two integer numbers
    Given the pgcd of two numbers 8 and 6
    Then its INFIX notation is ( 8 pgcd 6 )
    And its PREFIX notation is pgcd (8, 6)
    And its POSTFIX notation is (8, 6) pgcd

    Scenario: Printing the ppcm of two integer numbers
    Given the ppcm of two numbers 8 and 6
    Then its INFIX notation is ( 8 ppcm 6 )
    And its PREFIX notation is ppcm (8, 6)
    And its POSTFIX notation is (8, 6) ppcm

    Scenario: Printing the pow of two integer numbers
    Given the pow of two numbers 8 and 6
    Then its INFIX notation is ( 8 ^ 6 )
    And its PREFIX notation is ^ (8, 6)
    And its POSTFIX notation is (8, 6) ^

    Scenario: Printing the prime of two integer numbers
    Given the prime between two numbers 8 and 6
    Then its INFIX notation is ( 8 prime 6 )
    And its PREFIX notation is prime (8, 6)
    And its POSTFIX notation is (8, 6) prime

    Scenario: Printing the combinatorial of two integer numbers
    Given the combinatorial of two numbers 8 and 6
    Then its INFIX notation is ( 8 comb 6 )
    And its PREFIX notation is comb (8, 6)
    And its POSTFIX notation is (8, 6) comb

    Scenario: Printing the eucledian of two integer numbers
    Given the eucledian of two numbers 8 and 6
    Then its INFIX notation is ( 8 gcd 6 )
    And its PREFIX notation is gcd (8, 6)
    And its POSTFIX notation is (8, 6) gcd

    Scenario: Printing the euclidian division of two integer numbers
    Given the euclidian division of two numbers 8 and 6
    Then its INFIX notation is ( 8 // 6 )
    And its PREFIX notation is // (8, 6)
    And its POSTFIX notation is (8, 6) //

  # This is an example of a scenario in which we provide a list of numbers as input.
  # (In fact, this is not entirely true, since what is given as input is a table of
  # strings. In this case, the table is of dimension 1 * 3 (1 line and three columns).
  Scenario: Evaluation arithmetic operations over a list of integer numbers
    Given the following list of integer numbers
      | 8 | 2 | 2 |
    Then the sum is 12
    And the product is 32
    And the difference is 4
    And the quotient is 2


  Scenario: Evaluation arithmetic operations over a list of complex numbers
    Given the following list of complex numbers
      | 8+3i | 2-1i |
    Then the sum with complex number is 10+2i
    And the difference with complex number is 6+4i
    And the product with complex number is 19-2i
    #And the quotient with complex number is 2.60+2.80i TODO resolve this
  # A scenario outline (or template) is a scenario that is parameterised
  # with different values. The outline comes with a set of examples.
  # The scenario will be executed with each of the provided inputs.
  Scenario Outline: Adding two integer numbers
    Given an integer operation '+'
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the operation evaluates to <result>

    Examples:
      |n1|n2|result|
      |4|5|9|
      |5|3|8|

  Scenario Outline: Square root of one integer numbers
    Given an integer operation 'sqrt'
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the operation evaluates to <result>

    Examples:
      |n1  |n2 |result|
      |4   |5  |2     |
      |25  |3  |5     |

  Scenario Outline: Modulus of one integer numbers
    Given an integer operation 'modulus'
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the operation evaluates to <result>

    Examples:
      |n1  |n2 |result|
      |14  |5  |14    |
      |2   |3  |2     |

  Scenario Outline: Dividing two integer numbers
    Given an integer operation '/'
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the operation evaluates to <result>

    Examples:
      |n1|n2|result|
      |35|5|7.0|
      |5|5|1.0|
      |5|2|2.5|

  Scenario Outline: Evaluating arithmetic operations with two integer parameters
    Given an integer operation <op>
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the operation evaluates to <result>

    Examples:
      | op        |n1|n2|result|
      | "+"       | 4 | 5|     9|
      | "-"       | 8 | 5|     3|
      | "*"       | 7 | 2|    14|
      | "/"       | 6 | 2|     3|
      | "sqrt"    | 16| 5|     4|
      | "modulus" | 10| 7|    10|


