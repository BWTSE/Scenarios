# Shopping task

This system represents a shopping basket tracker, e.g. at the self scanning stations that are common in grocery stores.

Your task is to add a class that represents a receipt. It should make available information that is usually present on a receipt such as: the items purchased, the total cost, the portion that was VAT (assume it is the same, non-zero, rate for all items), the time and date of the purchase.

The class should have a method that returns a string that, when printed, somewhat resembles a physical receipt (it does not have to be particularly pretty). Ending it with a friendly message to the customer may ecourage repeat business.

The Tracker class should have a method that generates a receipt object based on what is currently "in the basket".

Finally, call your new receipt-generator method (and print the results of it) from Main. 

The 'ItemDatabase' class (crudely) mocks database access, pay little attention to it. Otherwise you are free to modify the existing system, if you feel it is appropriate to complete your task.

## Task checklist
* Added a receipt class.
* Added functionality to the receipt class that provides access to basic receipt information.
* Added a method to Receipt that returns a string representation of the receipt.
* Call the receipt generator method from main.
* Print the string representation of the receipt (from main).
* Program compiles.
