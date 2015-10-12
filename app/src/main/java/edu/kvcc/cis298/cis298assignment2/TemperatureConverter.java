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

    private RadioButton mFromChoice1;  // RADIO BUTTONS IN THE FROM GROUP
    private RadioButton mFromChoice2;
    private RadioButton mFromChoice3;
    private RadioButton mFromChoice4;

    private RadioButton mToChoice5;  // RADIO BUTTONS IN THE TO GROUP
    private RadioButton mToChoice6;
    private RadioButton mToChoice7;
    private RadioButton mToChoice8;

    private RadioGroup mFromGroup;
    private RadioGroup mToGroup;

    private static final String KEY_INDEX ="index";/////////////////////
    private int mCurrentIndex;

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


        mFromChoice1 = (RadioButton) findViewById(R.id.radioButton);
        mFromChoice2 = (RadioButton) findViewById(R.id.radioButton2);
        mFromChoice3 = (RadioButton) findViewById(R.id.radioButton3);
        mFromChoice4 = (RadioButton) findViewById(R.id.radioButton4);

        mToChoice5 = (RadioButton) findViewById(R.id.radioButton5);
        mToChoice6 = (RadioButton) findViewById(R.id.radioButton6);
        mToChoice7 = (RadioButton) findViewById(R.id.radioButton7);
        mToChoice8 = (RadioButton) findViewById(R.id.radioButton8);

        mConvertButton = (Button) findViewById(R.id.convertButton);

        mConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tempatureString = ((EditText) findViewById(R.id.editText)).getText().toString();
                int mFormulaNumberSelected = 0;
                String testString;
                double temperature;
                int mRoundingInt;
                double mRoundedDouble;
                TextView mTextView;
                String mFromType = "-";
                String mToType = "-";

                if (tempatureString != null && !tempatureString.isEmpty()){// MAKE SURE THAT A TEMPERATURE HAS BEEN ENTERED
                    temperature = Double.parseDouble(tempatureString);
                    int mSelectedFrom = mFromGroup.getCheckedRadioButtonId();
                    mSelectedFrom = (Math.abs(mSelectedFrom) % 10);

                    if (mSelectedFrom > 0){ // MAKE SURE THAT A FROM TEMPERATURE SCALE HAS BEEN SELECTED
                        int mSelectedTo = mToGroup.getCheckedRadioButtonId();
                        mSelectedTo = (Math.abs(mSelectedTo) % 10);

                        if (mSelectedTo > 0){ // MAKE SURE THAT A TO TEMPERATURE SCALE HAS BEEN SELECTED

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


/*
                            //**********************************FROM CELCIUS*********************************************
                            if (mFromChoice1.getId() == mSelectedFrom){
                                mFromType = "°C"; //                        FOR OUTPUT
                               if(mToChoice5.getId() == mSelectedTo) { //TO CELCIUS
                                   mFormulaNumberSelected = 12;
                                   mToType = "°C";
                                   Toast.makeText(TemperatureConverter.this,R.string.same_scale_error, Toast.LENGTH_SHORT).show();
                               }
                                else
                               {
                                   if(mToChoice6.getId() == mSelectedTo){ //TO FAHRENHEIT 0
                                       mFormulaNumberSelected = 0;
                                       mToType = "°F";
                                   }
                                   else
                                   {
                                       if (mToChoice7.getId() == mSelectedTo){// TO KELVIN 1
                                           mFormulaNumberSelected = 1;
                                           mToType = "K";
                                       }
                                       else
                                       {//TO RANKIN 2
                                           mFormulaNumberSelected = 2;
                                           mToType ="°Ra";
                                       }
                                   }
                               }
                               }
                            //********************************END FROM CELCIUS**********************************************

                            //************************************FROM FARHENHEIT*******************************************
                            if (mFromChoice2.getId() == mSelectedFrom){// FROM FARHENHEIT
                                mFromType = "°F";
                                if(mToChoice5.getId() == mSelectedTo) { //TO CELCIUS 3
                                    mFormulaNumberSelected = 3;
                                    mToType = "°C";
                                }
                                else
                                {
                                    if(mToChoice6.getId() == mSelectedTo){ //TO FAHRENHEIT
                                        mFormulaNumberSelected = 12;
                                        mToType  = "°F";
                                        Toast.makeText(TemperatureConverter.this,R.string.same_scale_error, Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        if (mToChoice7.getId() == mSelectedTo){// TO KELVIN 4
                                            mFormulaNumberSelected =4;
                                            mToType = "K";
                                        }
                                        else
                                        {//TO RANKIN 5
                                            mFormulaNumberSelected =5;
                                            mToType ="°Ra";
                                        }
                                    }
                                }
                            }
                            //********************************END FROM FARHENHEIT*******************************************

                            //***********************************FROM KELVIN***********************************************
                            if (mFromChoice3.getId() == mSelectedFrom){
                                mFromType = "K";
                                if(mToChoice5.getId() == mSelectedTo) { //TO CELCIUS 6
                                    mFormulaNumberSelected =6;
                                    mToType = "°C";
                                }
                                else
                                {
                                    if(mToChoice6.getId() == mSelectedTo){ //TO FAHRENHEIT 7
                                        mFormulaNumberSelected =7;
                                        mToType = "°F";
                                    }
                                    else
                                    {
                                        if (mToChoice7.getId() == mSelectedTo){// TO KELVIN
                                            mFormulaNumberSelected = 12;
                                            mToType  = "K";
                                            Toast.makeText(TemperatureConverter.this,R.string.same_scale_error, Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            //TO RANKIN 8
                                            mFormulaNumberSelected = 8;
                                            mToType ="°Ra";
                                        }
                                    }
                                }
                            }
                            //********************************END FROM KELVIN***********************************************

                            //**********************************FROM RANKIN*************************************************
                            if (mFromChoice4.getId() == mSelectedFrom){
                                mFromType = "°Ra";
                                if(mToChoice5.getId() == mSelectedTo) { //TO CELCIUS 9
                                    mFormulaNumberSelected = 9;
                                    mToType = "°C";
                                }
                                else
                                {
                                    if(mToChoice6.getId() == mSelectedTo){ //TO FAHRENHEIT 10
                                        mFormulaNumberSelected =10;
                                        mToType = "°F";
                                    }
                                    else
                                    {
                                        if (mToChoice7.getId() == mSelectedTo){// TO KELVIN 11
                                            mFormulaNumberSelected = 11;
                                            mToType = "K";
                                        }
                                        else
                                        {//TO RANKIN
                                            mFormulaNumberSelected = 12;
                                            mToType = "°Ra";
                                            Toast.makeText(TemperatureConverter.this,R.string.same_scale_error, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }

                            //********************************END FROM RANKIN*************************************************
 */

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



    if (savedInstanceState != null){
        mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
    }

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
