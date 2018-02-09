ValueInterface
==============

.. java:package:: com.eddmash.form.values
   :noindex:

.. java:type:: public interface ValueInterface<T>

   This class make it easy to deal with data passed into views like spinner.

   e.g. if you have a user records [{"gender":"male", "id":"1"},{"gender":"Female", "id":"2"}] and you would like to display the 'gender' on the spinner and whilst making it easy to get the id assigned to the gender when its selected.

   .. parsed-literal::

      List genders = new ArrayList<>();
      genders.add(new SimpleValue(" ", " "));
      genders.add(new SimpleValue("1", "Male"));
      genders.add(new SimpleValue("2", "Female"));

      Spinner genderSpinner = findViewById(R.id.gender);

      ArrayAdapter adapter = new ArrayAdapter<>(this,
                                              android.R.layout.simple_list_item_1, genders);
      genderSpinner.setAdapter(adapter);

   With this setup you can retrieve the id of the selected gender as follows

   .. parsed-literal::

       genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemClick(AdapterView> adapterView, View view, int i,  long l)
            {
                    if (adapterView.getSelectedItem() instanceof ValueInterface) {
                    ValueInterface val = (ValueInterface) adapterView.getSelectedItem();
                    val.getItem(); // get the data structure backing this value item
                    val.getLabel(); // the the label
                    val.getValue(); // get the actual value
                }
            }
      });

   :param <T>:

Methods
-------
getItem
^^^^^^^

.. java:method::  T getItem()
   :outertype: ValueInterface

   This should return the actual datastructure backing the ValueInterface instance. \ :java:ref:`MapValue.getItem()`\  } which returns a map from which the value and label were retrieved.

getLabel
^^^^^^^^

.. java:method::  String getLabel()
   :outertype: ValueInterface

   This is displayed to the use, e.g. in the examples above Male and Female will be displated.

getValue
^^^^^^^^

.. java:method::  String getValue()
   :outertype: ValueInterface

   The actual value that we need regardless of what is displayed to the user.

