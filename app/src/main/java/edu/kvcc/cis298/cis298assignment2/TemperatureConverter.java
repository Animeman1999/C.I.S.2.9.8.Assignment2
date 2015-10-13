package edu.kvcc.cis298.cis298assignment2;

import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TemperatureConverter extends AppCompatActivity {

    private Button mConvertButton;

    private RadioGroup mFromGroup;
    private RadioGroup mToGroup;

    private String INSTANCE_KEY = "instance_key";
    private String currentAnswer = "Flipping before you even start, enter a value and select Units to be converted from and to.";

    private TextView mTextView;

    private int [] [] mFindFormula = {// ARRAY TO HOLD THE FORMULA NUMBERS, ROWS ARE THE FROM TYPE AND COLUMNS ARE THE TO TYPE
            {12, 0,  1,  2},
            {3, 12,  4,  5},
            {6,  7, 12,  8},
            {9, 10, 11, 12}};

    private void convertTemperature(){//   METHOD TO DO DATA VALIDATION AND CHOOSE WHICH FORMULA TO USE
        String tempatureString = ((EditText) findViewById(R.id.editText)).getText().toString();

        int mFormulaNumberSelected = 0; // WILL HOLD WHICH FORMULUA NUMBER IS TO BE USED IN THE CONVERSION

        double temperature; //             HOLDS THE TEMPERATURE VALUE TO BE CONVERTED

        int mRoundingInt;//                HOLDS VALUE FOR ROUNDING USING INTEGER MATH

        double mRoundedDouble;//           HOLDS ROUNDED VALUE



        String mFromType = "-";//          STRING TO HOLD TEMPERATURE TYPE THAT IS BEING CONVERTED FROM

        String mToType = "-";//          STRING TO HOLD TEMPERATURE TYPE THAT IS BEING CONVERTED TO



        if (tempatureString != null && !tempatureString.isEmpty()){// MAKE SURE THAT A TEMPERATURE HAS BEEN ENTERED

            temperature = Double.parseDouble(tempatureString);//  NOW THAT THERE IS A NUMBER IN THE FIELD CONVERT IT INTO A DOUBLE

            int mSelectedFrom = mFromGroup.getCheckedRadioButtonId(); // PASS IN THE ID NUMBER OF RADIO BUTTON THAT HAS BEEN SELECTED OR -1 IF NOT SELECTED.


            if (mSelectedFrom > 0){ // MAKE SURE THAT A FROM TEMPERATURE SCALE HAS BEEN SELECTED

                mSelectedFrom = (Math.abs(mSelectedFrom) % 10); // NOW THAT WE KNOW A SELECTED FROM RADIO BUTTON HAS BEEN CHECKED FIND IT LAST DIGIT

                int mSelectedTo = mToGroup.getCheckedRadioButtonId(); // PASS IN THE ID NUMBER OF RADIO BUTTON THAT HAS BEEN SELECTED OR -1 IF NOT SELECTED.


                if (mSelectedTo > 0){ // MAKE SURE THAT A TO TEMPERATURE SCALE HAS BEEN SELECTED
                    mSelectedTo = (Math.abs(mSelectedTo) % 10);// NOW THAT WE KNOW A SELECTED TO RADIO BUTTON HAS BEEN CHECKED FIND IT LAST DIGIT

                    switch (mSelectedFrom){// PLACE "SELECTED FROM TEMPERATURE TYPE" INTO A STRING FOR OUTPUT
                        case 5:
                            mFromType = "°C";
                            break;
                        case 6:
                            mFromType = "°F";
                            break;
                        case 7:
                            mFromType = "K";
                            break;
                        default:
                            mFromType ="°Ra";
                    }

                    switch (mSelectedTo){// PLACE "SELECTED TO TEMPERATURE TYPE" INTO A STRING FOR OUTPUT
                        case 1:
                            mToType = "°C";
                            break;
                        case 2:
                            mToType =  "°F";
                            break;
                        case 3:
                            mToType = "K";
                            break;
                        default:
                            mToType = "°Ra";
                    }

                    mFormulaNumberSelected = mFindFormula [(mSelectedFrom - 5)] [(mSelectedTo -1)];  // SELECT WHICH FORMLUA IS USED

                    TempConversion AnotherTempConversion= new TempConversion(temperature, mFormulaNumberSelected);  // DO THE CONVERSION

                    mRoundingInt = (int)(AnotherTempConversion.getOutputTemperature()* 10);                         // ROUND TO THE NEAREST 10TH
                    mRoundedDouble = (double) (mRoundingInt) / 10;

                    mTextView = (TextView) findViewById(R.id.textView4);

                    // CONCATINATE THE TEMPERATURE ENTERED WITH THE TYPE OF TEMPERATURE ENTERED WITH EQUAL SIGN WITH CONVERTED TEMPERTATURE WITH THE CONVERTED TEMPERATURE TYPE WITH
                    // A SECOND LINE THAT HAS THE FORMULA
                    currentAnswer = String.valueOf(temperature) + mFromType + " = " + String.valueOf(mRoundedDouble) + mToType + System.getProperty("line.separator")+ AnotherTempConversion.getOutputData();
                    mTextView.setText(currentAnswer);
                } else {
                    //                                                      ERROR MESSAGE WHEN A to TERMPERATURE SCALE HAS NOT BEE SELECTED
                    Toast.makeText(TemperatureConverter.this,R.string.no_to_error_toast, Toast.LENGTH_SHORT).show();
                }
            }
            else {
                //                                                      ERROR MESSAGE WHEN A FROM TERMPERATURE SCALE HAS NOT BEE SELECTED
                Toast.makeText(TemperatureConverter.this,R.string.no_from_error_toast, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            //                                                          ERROR MESSAGE WHEN TEMPERATURE HAS NOT BEEN ENTERED
            Toast.makeText(TemperatureConverter.this,R.string.no_tempature_error_toast, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        mFromGroup = (RadioGroup) findViewById(R.id.radioGroup);    //      ASSIGN THE 2 RADIO GROUPS

        mToGroup = (RadioGroup)findViewById(R.id.radioGroup2);

        mConvertButton = (Button) findViewById(R.id.convertButton);//        ASSIGN THE BUTTON



        if (savedInstanceState != null) //                                   AN INSTANCE OF THE APP WAS MADE PREVOUS TO THIS ONE
        {
              currentAnswer = savedInstanceState.getString(INSTANCE_KEY);  //   GET THE currentAnswer THAT WAS SAVED WHEN THE PREVOUS INSTANCE STOPED

              mTextView = (TextView) findViewById(R.id.textView4);         //   MAKE NEW INSTANCE OF textView4

              mTextView.setText(currentAnswer);                            //   OUTPUT THE currentAnswer

        }

        mConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {// SIMPLE, WHEN CLICKED RUN convertTemperature() METHOD

                convertTemperature();

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(INSTANCE_KEY, currentAnswer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_temperature_converter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
