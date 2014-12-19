LoginUI
=======

User friendly login user interface for Android

This project contains two different custom components. The first one is a Registration component that creats a UI for creating a account. It is possible to create custom fields, change background color, text color and header color.

The second one is a Password analyzer that will check if a given password is strong enough. This component is integrated with the Registration component.

To be able to create a new user all the required fields has to be filled correctly, this will enable the create button.

How to use
==========

```
RC = (RegistrationComponent) findViewById(R.id.registration); // find the custom component in XML
        
RC.setFieldBackgroundColor(Color.BLACK); // Sets the background color of all fields.
RC.setTextColor(Color.GREEN); // Sets the text color of all fields.
RC.setHeaderColor(Color.WHITE); // Sets the text color of all headers.
RC.addField("New field"); // Creates a new field with given String name. 
RC.addField("New field 2"); // Creates a new field with given String name. 

```

