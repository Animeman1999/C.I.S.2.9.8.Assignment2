package edu.kvcc.cis298.cis298assignment2;

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

//    private static final String KEY_INDEX ="index";/////////////////////
//    private int mCurrentIndex;

    private int [] [] mFindFormula = {
            {12, 0, 1, 2},
            {3, 12, 4, 5},
            {6,7,12,8},
            {9, 10, 11, 12}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        mFromGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mToGroup = (RadioGroup)findViewById(R.id.radioGroup2);

        mConvertButton = (Button) findViewById(R.id.convertButton);

        mConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tempatureString = ((EditText) findViewById(R.id.editText)).getText().toString();
                int mFormulaNumberSelected = 0;
                double temperature;
                int mRoundingInt;
                double mRoundedDouble;
                TextView mTextView;
                String mFromType = "-";
                String mToType = "-";

                if (tempatureString != null && !tempatureString.isEmpty()){// MAKE SURE THAT A TEMPERATURE HAS BEEN ENTERED
                    temperature = Double.parseDouble(tempatureString);
                    int mSelectedFrom = mFromGroup.getCheckedRadioButtonId();


                    if (mSelectedFrom > 0){ // MAKE SURE THAT A FROM TEMPERATURE SCALE HAS BEEN SELECTED
                        mSelectedFrom = (Math.abs(mSelectedFrom) % 10); // NOW THAT WE KNOW A SELECTED FROM RADIO BUTTON HAS BEEN CHECKED FIND IT LAST DIGIT
                        int mSelectedTo = mToGroup.getCheckedRadioButtonId();


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
                              mTextView.setText(String.valueOf(temperature) + mFromType + " = " + String.valueOf(mRoundedDouble) + mToType + System.getProperty("line.separator")+ AnotherTempConversion.getOutputData());
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
        });



/*    if (savedInstanceState != null){
        mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
    }
*/
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
