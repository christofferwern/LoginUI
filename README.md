LoginUI
=======

User friendly login user interface for Android

This project contains two different custom components. The first one is a Registration component that creats a UI for creating a account. It is possible to create custom fields, change background color, text color and header color.

The second one is a Password analyzer that will check if a given password is strong enough. This component is integrated with the Registration component.

To be able to create a new user all the required fields has to be filled correctly, this will enable the create button.

There is three different types of fields:
        DEFAULT
        EMAIL
        PASSWORD

But also three different security levels of the password field:
        EASY
        MEDIUM
        HARD

How to use
==========

```
RC = (RegistrationComponent) findViewById(R.id.registration);

RC.addField("Firstname", Type.DEFAULT, false);
RC.addField("Lastname", Type.DEFAULT, false);
RC.addField("Username", Type.DEFAULT);
RC.addField("Password", Type.PASSWORD);
RC.addField("Email", Type.EMAIL, true);

RC.setHeaderColor(Color.argb(100,0, 0, 0)); // Sets the text color of all headers.
RC.setFieldBackgroundColor(Color.argb(100,0, 159, 137)); // Sets the background color of all fields.
RC.setTextColor(Color.argb(150, 255, 255, 255)); // Sets the text color of all fields.

RC.setPasswordBarColors(Color.rgb(23, 49, 46), Color.rgb(167, 241, 231));
RC.setPasswordLevel(SecurityType.MEDIUM);

```
Docs
====
The documentation is generated with doxygen and can be found in Docs/html/index.html
