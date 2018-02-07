Validating across fragments Example
###################################

Define an interface to be used to communicate between the activity and its fragments

.. code-block:: java

    public interface FormSaveListener {

        /**
         * Save the data saved by the user. this values are cleared once a save to the database is made
         * otherwise this are saved for use on restore.
         *
         */
        void save();

        /**
        * Return a validator object that will be shared to the fragments
        */
        ValidationListener getValidator();
    }

The activity that contains fragments should implement the `FormSaveListener` interface above.

The activity defines a validator object that this activity will use for validation.

.. code-block:: java

    public class DetailsActivity extends Activity implements FormSaveListener{
        ValidationListener mainValidator;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if (getResources().getConfiguration().orientation
                    == Configuration.ORIENTATION_LANDSCAPE) {
                // If the screen is now in landscape mode, we can show the
                // dialog in-line with the list so we don't need this activity.
                finish();
                return;
            }

            if (savedInstanceState == null) {
                FragmentA fragmentA = new FragmentA();
                fragmentA.setArguments(getIntent().getExtras());
                getFragmentManager().beginTransaction().add(R.id.fragment_location, fragmentA).commit();
            }

            mainValidator = new Validator(this);
        }

        public ValidationListener getValidator(){
            return mainValidator;
        }

        public void save(){
             if (mainValidator.validate()) {
                 // .. code to perform if validation passes
             } else {

                 // show the errors if validation failed
                 // we use the renderer class to handle the display
                 ErrorRenderer errorRenderer = new ErrorRenderer(this, validator);
                 errorRenderer.render(errorSpace);
             }
        }
    }

On each fragement we create a validation object and give it a tag id, so that the errors for each
fragment be grouped together.

On each fragement, onAttach ensure to cast the context to form FormSaveListener,

Add the fragments validation object to the activities validation object,
This makes it to be validated at the time when the activity validation is done.

We run the activities validation when the submit button is clicked.

.. code-block:: java

    public class FragmentA extends ListFragment {
        FormSaveListener formSaveListener;
        ValidationListener validator;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_a, container, false);

            validator = new Validator("Fragment_A", getActivity());
            formSaveListener.getValidator().addValidator(validator);

            setUpValidations()
            return view;
        }

        @Override
        public void onResume() {
            super.onResume();
            Button submitBtn = (Button) view.findViewById(R.id.form_submit_button);

            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    formSaveListener.save();
                }
            });

        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            try {
                formSaveListener = (FormSaveListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() + " must implement FormSaveListener");
            }
        }

        public void setUpValidations(){
            EditText nameEditText = (EditText) view.findViewById(R.id.name);
            validator.setValidation(nameEditText, RegexTemplate.NOT_EMPTY, "name cannot be empty");
        }
    }