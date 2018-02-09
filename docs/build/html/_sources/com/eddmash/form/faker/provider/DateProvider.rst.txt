.. java:import:: com.eddmash.form.faker PopulatorInterface

.. java:import:: java.text SimpleDateFormat

.. java:import:: java.util Calendar

.. java:import:: java.util Locale

DateProvider
============

.. java:package:: com.eddmash.form.faker.provider
   :noindex:

.. java:type:: public class DateProvider extends Provider

Fields
------
TIME_NOW
^^^^^^^^

.. java:field:: public static final String TIME_NOW
   :outertype: DateProvider

TODAY
^^^^^

.. java:field:: public static final String TODAY
   :outertype: DateProvider

dateFormat
^^^^^^^^^^

.. java:field:: protected String dateFormat
   :outertype: DateProvider

timeFormat
^^^^^^^^^^

.. java:field:: protected String timeFormat
   :outertype: DateProvider

Constructors
------------
DateProvider
^^^^^^^^^^^^

.. java:constructor:: public DateProvider(PopulatorInterface populator)
   :outertype: DateProvider

DateProvider
^^^^^^^^^^^^

.. java:constructor:: public DateProvider(PopulatorInterface populator, String format)
   :outertype: DateProvider

Methods
-------
generate
^^^^^^^^

.. java:method:: @Override public String generate()
   :outertype: DateProvider

getDate
^^^^^^^

.. java:method:: public DateProvider getDate()
   :outertype: DateProvider

getDate
^^^^^^^

.. java:method:: public DateProvider getDate(String timeFormat)
   :outertype: DateProvider

getTime
^^^^^^^

.. java:method:: public DateProvider getTime()
   :outertype: DateProvider

getTime
^^^^^^^

.. java:method:: public DateProvider getTime(String timeFormat)
   :outertype: DateProvider

