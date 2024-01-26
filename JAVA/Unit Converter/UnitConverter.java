import javax.swing.*;
import java.awt.event.*;

public class UnitConverter {

    // functionalities

    private JFrame frame;
    private JTextField input, output;
    private JComboBox<String> fromUnit, toUnit;
    private JLabel inputLabel;
    private JButton convertButton;
    private JLabel outputLabel;

    protected void initComponents() {

        // frame setup

        frame = new JFrame("Unit Converter");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // button and other fields

        inputLabel = new JLabel("Input: ");
        input = new JTextField(10);
        outputLabel = new JLabel("Output: ");
        output = new JTextField(10);
        output.setEditable(false);
        fromUnit = new JComboBox<String>(new String[] { "Meters", "Feet", "Inches", "Centimeters", });
        toUnit = new JComboBox<String>(new String[] { "Meters", "Feet", "Inches", "Centimeters", });
        convertButton = new JButton("Convert");

        // position

        inputLabel.setBounds(20, 50, 100, 20);
        input.setBounds(150, 50, 100, 20);
        fromUnit.setBounds(270, 50, 100, 20);
        outputLabel.setBounds(20, 80, 100, 20);
        output.setBounds(150, 80, 100, 20);
        toUnit.setBounds(270, 80, 100, 20);
        convertButton.setBounds(150, 120, 100, 20);

        // perform action through button

        convertButton.addActionListener(new ConvertButtonListner());

        // ActionListner

        frame.add(inputLabel);
        frame.add(input);
        frame.add(fromUnit);
        frame.add(outputLabel);
        frame.add(output);
        frame.add(toUnit);
        frame.add(convertButton);

        // to display the layout

        frame.setVisible(true);
    }

    UnitConverter() {
        initComponents();
    }

    public static void main(String[] args) {
        new UnitConverter();
    }

    // perform actions: Event handling

    private class ConvertButtonListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String fromUnitType = fromUnit.getSelectedItem().toString();
            String toUnitType = toUnit.getSelectedItem().toString();
            double inputValue = Double.parseDouble(input.getText());
            double outputVaule = 0.0;

            if (fromUnitType.equals("Meters")) {
                if (toUnitType.equals("Meters")) {
                    outputVaule=UnitConverter.metersToMeters(inputValue);
                } else if (toUnitType.equals("Feet")) {
                    outputVaule=UnitConverter.metersToFeet(inputValue);
                } else if (toUnitType.equals("Inches")) {
                    outputVaule=UnitConverter.metersToInches(inputValue);
                } else if (toUnitType.equals("Centimeters")) {
                    outputVaule=UnitConverter.metersToCentimemeter(inputValue);
                }
            } else if (fromUnitType.equals("Feet")) {
                if (toUnitType.equals("Meters")) {
                    outputVaule=UnitConverter.feetToMeters(inputValue);
                } else if (toUnitType.equals("Feet")) {
                    outputVaule=UnitConverter.feetToFeet(inputValue);
                } else if (toUnitType.equals("Inches")) {
                    outputVaule=UnitConverter.feetToInches(inputValue);
                } else if (toUnitType.equals("Centimeters")) {
                    outputVaule=UnitConverter.feetToCentimeters(inputValue);
                }
            } else if (fromUnitType.equals("Inches")) {
                if (toUnitType.equals("Meters")) {
                    outputVaule=UnitConverter.inchesToMeters(inputValue);
                } else if (toUnitType.equals("Feet")) {
                    outputVaule=UnitConverter.inchesToFeet(inputValue);
                } else if (toUnitType.equals("Inches")) {
                    outputVaule=UnitConverter.inchesToInches(inputValue);
                } else if (toUnitType.equals("Centimeters")) {
                    outputVaule=UnitConverter.inchesToCentimeters(inputValue);
                }
            } else if (fromUnitType.equals("Centimeters")) {
                if (toUnitType.equals("Meters")) {
                    outputVaule=UnitConverter.centimetersToMeters(inputValue);
                } else if (toUnitType.equals("Feet")) {
                    outputVaule=UnitConverter.centimetersToFeet(inputValue);
                } else if (toUnitType.equals("Inches")) {
                    outputVaule=UnitConverter.centimetersToInches(inputValue);
                } else if (toUnitType.equals("Centimeters")) {
                    outputVaule=UnitConverter.centimetersToCentimeters(inputValue);
                }
            }

            //to display the output

            output.setText(""+outputVaule);
        }
    }

    //converting meters to other units

    public static double metersToMeters(double inputVal){
        double output;
        output=inputVal;
        return output;
    } 

    public static double metersToCentimemeter(double inputVal){
        double output;
        output=100*inputVal;
        return output;
    } 

    public static double metersToFeet(double inputVal){
        double output;
        output=3.28084*inputVal;
        return output;
    }
    
    public static double metersToInches(double inputVal){
        double output;
        output=12*3.28084*inputVal;
        return output;
    }

    //converting feet to other units

    public static double feetToCentimeters(double inputVal){
        double output;
        output=(inputVal/3.28084)*100;
        return output;
    }

    public static double feetToInches(double inputVal){
        double output;
        output=inputVal*12;
        return output;
    }

    public static double feetToFeet(double inputVal){
        double output;
        output=inputVal;
        return output;
    }

    public static double feetToMeters(double inputVal){
        double output;
        output=inputVal/3.28084;
        return output;
    }

    //converting inches to other units

    public static double inchesToMeters(double inputVal){
        double output;
        output=(inputVal/12)/3.28084;
        return output;
    }

    public static double inchesToCentimeters(double inputVal){
        double output;
        output=((inputVal/12)/3.28084)*100;
        return output;
    }

    public static double inchesToInches(double inputVal){
        double output;
        output=inputVal;
        return output;
    }

    public static double inchesToFeet(double inputVal){
        double output;
        output=inputVal/12;
        return output;
    }

    //converting centimeters to other units

    public static double centimetersToMeters(double inputVal){
        double output;
        output=inputVal/100;
        return output;
    }
    
    public static double centimetersToCentimeters(double inputVal){
        double output;
        output=inputVal;
        return output;
    }
    
    public static double centimetersToFeet(double inputVal){
        double output;
        output=(inputVal/100)*3.28084;
        return output;
    }
    
    public static double centimetersToInches(double inputVal){
        double output;
        output=((inputVal/100)*3.28084)*12;
        return output;
    }
}
