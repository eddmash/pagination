
Install
*******

using Maven.

::

    <dependency>
      <groupId>com.eddmash</groupId>
      <artifactId>android-form</artifactId>
      <version>1.0.8</version>
      <type>pom</type>
    </dependency>

Using gradle

::

    compile 'com.eddmash:android-form:1.0.8'

Usage
*****

To use this library is very easy

.. _basic_form:

- Create :java:ref:`Form <FormInterface>` class


.. code-block:: java

     private class BasicForm extends Form {
            public BasicForm() {
                super();
            }

            public BasicForm(ValidatorInterface validator) {
                super(validator);
            }

            @Override
            public void save() throws FormException {
                // implement the saving logic, you have access to
                // getValues() returns a map of where key is the name of the field and the values
            }
    }

- Create form object

.. code-block:: java

    BasicForm form = new BasicForm();

- Add :java:ref:`Field <FieldInterface>` to :java:ref:`FormInterface <FormInterface>`

.. code-block:: java

    // get the views from the layout
    Spinner genderSpinner = (Spinner) editText.findViewById(R.id.gender);
    EditText fName = (EditText) editText.findViewById(R.id.firstname);
    EditText phone_number = (EditText) editText.findViewById(R.id.phone_number);

    // add views to the form
    form.addField("gender", genderSpinner);
    form.addField("firstname", fName);
    form.addField("phone_number", fName);

    // add validation check
    form.addCheck(new NotEmptyCheck(gender,"Gender cannot be blank"));
    form.addCheck(new NotEmptyCheck(fName,"Firstname cannot be blank"));

- Run :java:ref:`validation <FormInterface.isValid()>` and get the :java:ref:`values <FormInterface.getValues()>`.

.. code-block:: java

    if(form.isValid()){
        form.getValues()// returns a map of where key is the name of the field and the values

    }else{

         LinearLayout errorSpace = (LinearLayout) findViewById(R.id.error_base);
         errorSpace.removeAllViews();// clear space first

         ErrorRenderer errorRenderer = new ErrorRenderer(this, form.getValidator());
         errorRenderer.render(errorSpace);
    }

- :java:ref:`Save <FormInterface.save()>` form :java:ref:`values <FormInterface.getValues()>`

.. code-block:: java

    if(form.isValid()){
        try{
            form.save() // save
        } catch (FormException e) {
            e.printStackTrace();
        }

    }else{

        LinearLayout errorSpace = (LinearLayout) findViewById(R.id.error_base);
        errorSpace.removeAllViews();// clear space first

        ErrorRenderer errorRenderer = new ErrorRenderer(this, form.getValidator());
        errorRenderer.render(errorSpace);
    }
