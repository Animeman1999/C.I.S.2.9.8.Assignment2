package edu.kvcc.cis298.cis298assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
        mToChoice6 = (RadioButton) findViewById(R.id.radioButton7);
        mToChoice7 = (RadioButton) findViewById(R.id.radioButton8);

        mConvertButton = (Button) findViewById(R.id.convertButton);
        mConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tempatureString = ((EditText) findViewById(R.id.editText)).getText().toString();
                String testString;

                if (tempatureString != null && !tempatureString.isEmpty()){// MAKE SURE THAT A TEMPERATURE HAS BEEN ENTERED
                    int mSelectedFrom = mFromGroup.getCheckedRadioButtonId();
                    testString = String.valueOf(mSelectedFrom);
                   // Toast.makeText(TemperatureConverter.this,testString, Toast.LENGTH_SHORT).show();
                    if (mSelectedFrom > 0){ // MAKE SURE THAT A FROM TEMPERATURE SCALE HAS BEEN SELECTED
                        int mSelectedTo = mToGroup.getCheckedRadioButtonId();

                        if (mSelectedTo > 0){ // MAKE SURE THAT A TO TEMPERATURE SCALE HAS BEEN SELECTED
                            
                        }
                        else {
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
