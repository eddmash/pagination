Android validation library documentation!
#########################################

A lightweight and extensible android validation library.

It uses simple, straightforward validation methods with a focus on readable and concise syntax.

Installation
************

using Maven.

::

    <dependency>
      <groupId>com.eddmash</groupId>
      <artifactId>validation</artifactId>
      <version>1.0.16</version>
      <type>pom</type>
    </dependency>

using Gradle.

::

    compile 'com.eddmash:validation:1.0.16'

Usage
*****

Using this library boils down to this steps

- Create a validator object

.. code-block:: java

    Validator validator = new Validator();

- Add validation checks to the validator

.. code-block:: java

    // the view objects to validate
    EditText nameEditText = (EditText) view.findViewById(R.id.name);
    Spinner ageSpinner = (Spinner) view.findViewById(R.id.spinner);

    // ... using check objects
    validator.addCheck(new NotEmptyCheck(nameEditText, "name cannot be blank");
    validator.addCheck(new NotEmptyCheck(ageSpinner, "age cannot be blank");

:doc:`Learn more about available checks <checks>`

- Validate

To run the validations involve the validators
:java:ref:`validate() <ValidatorInterface.validate()>` method.

.. code-block:: java

    validator.validate()

This method returns ``true`` if the validation passed or ``false`` if the validations failed.

.. _handling_errors:

Handle the errors
*****************

Incase of validation failure, the validation errors can be accessed via the
:java:ref:`getErrors() <ValidatorInterface.getErrors()>` method.

This library comes with a convenience class :java:ref:`ErrorRenderer <ErrorRenderer>`, which can be
used to easily display the validation errors.

- Displaying errors.

.. code-block:: java

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

- Using ValidationListener to handle errors.

This version of :java:ref:`validate() <ValidatorInterface.validate()>`
accepts a :java:ref:`ValidationListener <ValidationListener>` which has
:java:ref:`onValidationSuccess <ValidationListener.onValidationSuccess()>` invoked when
validation is a success. :java:ref:`onValidationFailed <ValidationListener.onValidationFailed()>`
invoked when validation fails methods.

.. code-block:: java

    // the layout where we display any validation errors
    LinearLayout errorSpace = (LinearLayout) findViewById(R.id.error_base);
    errorSpace.removeAllViews();// clear space first

    validator.validate(new ValidationListener() {
        @Override
        public void onValidationSuccess(ValidatorInterface validatorInterface) {
            // on success code
        }

        @Override
        public void onValidationFailed(ValidatorInterface validatorInterface) {
            // show the errors if validation failed
            // we use the renderer class to handle the display
            ErrorRenderer errorRenderer = new ErrorRenderer(MainActivity.this,
                    validatorInterface);
            errorRenderer.render(errorSpace);
        }
    });

.. toctree::
    :titlesonly:
    :maxdepth: 1

    Usage <self>
    checks
    renderer
    example/fragments
    Api <packages>

