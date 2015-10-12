package edu.kvcc.cis298.cis298assignment2;

import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by jmartin5229 on 10/6/2015.
 */
public class TempConversion {
    private double mInputTemperature;
    private double mOutputTemperature;
    private int mFormulaNumb;
    private String[] mFormulaString = {
            "°F = °C × 1.8 + 32",
            " K = °C + 273.15",
            "Ra = °C × 1.8 + 491.67",
            "°C = (°F − 32) / 1.8",
            "K = (°F + 459.67) / 1.8",
            "Ra = °F + 459.67",
            "C = K − 273.15",
            "°F = (K  − 273.15) × 1.8 + 32",
            "°Ra = K × 1.8",
            "°C = °Ra / 1.8 -491.67",
            "°F = °Ra - 459.67",
            " K = °Ra / 1.8",
            "No conversion taking place!"
        };
    private String mOutputData;



    public TempConversion (double inputTemperature,int formulaNumb ){
        mInputTemperature = inputTemperature;
        mFormulaNumb = formulaNumb;


        switch (mFormulaNumb){
            case 0://°F = °C × 1.8 + 32
                mOutputTemperature = mInputTemperature * 1.8 + 32;
                break;
            case 1:// K = °C + 273.15
                mOutputTemperature = mInputTemperature + 273.15;
                break;
            case 2://Ra = °C × 1.8 + 491.67
                mOutputTemperature = mInputTemperature * 1.8 + 491.67;
                break;
            case 3://°C = (°F − 32) / 1.8
                mOutputTemperature = (mInputTemperature - 32) / 1.8;
                break;
            case 4://K = (°F + 459.67) / 1.8
                mOutputTemperature = (mInputTemperature + 459.67) / 1.8;
                break;
            case 5://Ra = °F + 459.67
                mOutputTemperature = (mInputTemperature + 459.67);
                break;
            case 6://C = K − 273.15
                mOutputTemperature = (mInputTemperature - 273.15);
                break;
            case 7://F = (K  − 273.15) × 1.8 + 32
                mOutputTemperature = (mInputTemperature  - 273.15) * 1.8 + 32;
                break;
            case 8://°Ra = K × 1.8
                mOutputTemperature = (mInputTemperature *1.8);
                break;
            case 9://°C = °Ra / 1.8 -491.67
                mOutputTemperature = (mInputTemperature / 1.8 -491.67);
                break;
            case 10://°F = °Ra - 459.67
                mOutputTemperature = (mInputTemperature -459.67);
                break;
            case 11:// K = °Ra / 1.8
                mOutputTemperature = (mInputTemperature / 1.8);
                break;
            default:// NO CONVERSION
                mOutputTemperature = mInputTemperature;
        }

        mOutputData = mFormulaString[mFormulaNumb];

    }

    public double getOutputTemperature() {
        return mOutputTemperature;
    }

    public void setOutputTemperature(double outputTemperature) {
        mOutputTemperature = outputTemperature;
    }

    public String getOutputData() {
        return mOutputData;
    }

    public void setOutputData(String outputData) {
        mOutputData = outputData;
    }
}
