.. java:import:: android.app Activity

.. java:import:: android.graphics Color

.. java:import:: android.graphics Typeface

.. java:import:: android.editText View

.. java:import:: android.editText ViewGroup

.. java:import:: android.widget GridLayout

.. java:import:: android.widget LinearLayout

.. java:import:: android.widget TextView

.. java:import:: com.eddmash.validation ValidatorInterface

.. java:import:: java.util List

.. java:import:: java.util Map

ErrorRenderer
=============

.. java:package:: com.eddmash.validation.renderer
   :noindex:

.. java:type:: public class ErrorRenderer implements RendererInterface

   Renders all errors found in the validator

Constructors
------------
ErrorRenderer
^^^^^^^^^^^^^

.. java:constructor:: public ErrorRenderer(Activity activity, ValidatorInterface validatorInterface)
   :outertype: ErrorRenderer

Methods
-------
render
^^^^^^

.. java:method:: @Override public void render(ViewGroup errorSpace)
   :outertype: ErrorRenderer

