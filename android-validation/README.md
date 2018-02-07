<a href='https://bintray.com/eddmash/maven/validation/1.0.0/link'><img src='https://api.bintray.com/packages/eddmash/maven/validation/images/download.svg?version=1.0.0'></a>
<a href='https://bintray.com/eddmash/maven/validation?source=watch' alt='Get automatic notifications about new "validation" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>

Android validation library documentation!
-----------------------------------------

A lightweight and extensible android validation library.

It uses simple, straightforward validation methods with a focus on readable and concise syntax.

Visit the [Documentation](http://android-validation-library.readthedocs.io)

Installation
------------

using Maven.

```
    <dependency>
      <groupId>com.eddmash</groupId>
      <artifactId>validation</artifactId>
      <version>1.0.14</version>
      <type>pom</type>
    </dependency>
```

using Gradle.

```
    compile 'com.eddmash:validation:1.0.14'
```

Usage
-----
Using this library boils down to this steps

- Create a validator object

```
    Validator validator = new Validator();
```

- Add validation checks to the validator

```

    // the view objects to validate
    EditText nameEditText = (EditText) view.findViewById(R.id.name);
    Spinner ageSpinner = (Spinner) view.findViewById(R.id.spinner);

    // ... using check objects
    validator.addCheck(new NotEmptyCheck(nameEditText, "name cannot be blank");
    validator.addCheck(new NotEmptyCheck(ageSpinner, "age cannot be blank");
```
[Learn more about available checks](http://android-validation-library.readthedocs.io/en/latest/checks.html)

- Validate

The final step is telling the validator to validate againsts our checks

```
validator.validate().
```

This method returns ``true`` if the validation passed or ``false`` if the validations failed.

Handling Errors
---------------

Incase of validation failure, the validation errors can be accessed via the
`ValidatorInterface.getErrors()` method.

This library comes with a convenience class 
[ErrorRenderer](http://android-validation-library.readthedocs.io/en/latest/renderer.html), which 
can be used to easily display the validation errors.

```
     // the layout where we display any validation errors
     LinearLayout errorSpace = (LinearLayout) findViewById(R.id.error_base);
     
     errorSpace.removeAllViews();// clear space first
     
     if (validator.validate()) {
     
         // .. code to perform if validation passes
         
     } else {
     
         // show the errors if validation failed
         // we use the renderer class to handle the display
         ErrorRenderer errorRenderer = new ErrorRenderer(this, validator);
         
         errorRenderer.render(errorSpace);
     }
```


