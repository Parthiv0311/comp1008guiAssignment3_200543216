package SeatingArrangement;

import SeatingArrangement.model.StudentActivity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.*;

public class ControllerActivity {

    @FXML
    private TextField student_Name_Type;

    @FXML
    private ColorPicker color_Select_Type;

    @FXML
    private Text name1, name2, name3, name4, name5, name6, name7, name8, name9;

    @FXML
    private Text message_Text_Type;

    @FXML
    private Rectangle seat_1, seat_2, seat_3, seat_4, seat_5, seat_6, seat_7, seat_8, seat_9;

    @FXML
    private Button placeButton;

    private List<Text> names;

    private List<Rectangle> seats_Type;

    private List<StudentActivity> students = new ArrayList<>();

    private final int case_Base_Error = 1;
    private final int case_Base_Success = 2;

    private final String fail_Message_Color_Set = "RED";
    private final String success_Message_Color_Set = "BLUE";

    @FXML
    public void initialize(){

        // Call method to construct ArrayList for names

        constructNamesArrayList();

        // Call method to construct ArrayList for seats

        constructSeatsArrayList();

    }
    private void constructNamesArrayList(){

        // Initialize the ArrayList instance to store the seats

        names = new ArrayList<>();

        names.add(name1);
        names.add(name2);
        names.add(name3);
        names.add(name4);
        names.add(name5);
        names.add(name6);
        names.add(name7);
        names.add(name8);
        names.add(name9);

    }
    private void constructSeatsArrayList(){

        seats_Type = new ArrayList<>();

        seats_Type.add(seat_1);
        seats_Type.add(seat_2);
        seats_Type.add(seat_3);
        seats_Type.add(seat_4);
        seats_Type.add(seat_5);
        seats_Type.add(seat_6);
        seats_Type.add(seat_7);
        seats_Type.add(seat_8);
        seats_Type.add(seat_9);

    }
    @FXML
    private void onButtonAction(ActionEvent event){

        if(message_Text_Type.isVisible()){
            message_Text_Type.setVisible(false);
        }

        // Check if all the 9 seats are full

        if(students.size()>=9){

            // set error message

            setMessage("All the seats are taken", case_Base_Error);
            return;
        }

        // Get student's name from the TextField

        String studentName = student_Name_Type.getText();

        // Get the color from the ColorPicker

        Color seatColor = color_Select_Type.getValue();

        // Check the student name is valid or not

        if(!validateStudentName(studentName)){

            // Set the error message

            setMessage("Error message appears now", case_Base_Error);


            return;
        }

        // Check if the color is available or not

        if(!checkColorAvailability(seatColor)){

// Set the error message for the colour

            setMessage("This color has already been used", case_Base_Error);

            return;
        }


        designateSeat(studentName, seatColor);

    }

    // use method to set error message on the errorMessage text

    public void setMessage(String message, int caseTag){


        message_Text_Type.setVisible(true);

        if(caseTag== case_Base_Error){
            message_Text_Type.setFill(Paint.valueOf(fail_Message_Color_Set));
        }else{
            message_Text_Type.setFill(Paint.valueOf(success_Message_Color_Set));
        }


        message_Text_Type.setText(message);

    }

    // use method to check student name valid or not

    private boolean validateStudentName(String studentName){

        boolean availability = true;


        if(studentName.trim().isEmpty()){

            availability = false;

        }else{


// ArrayList of Student to find the whether the name is same


            for(int i = 0; i<students.size(); i++){


                // If the students name is same exists, then availability false

                if(students.get(i).getName_Type().equals(studentName)){
                    availability = false;
                }
            }
        }

        return availability;
    }

    private void designateSeat(String studentName, Color seatColor){

        Random random = new Random();

        int randomNumber;

        do{

            randomNumber = random.nextInt(seats_Type.size());

        }while(!checkSeatAvailability(randomNumber));

        StudentActivity student = new StudentActivity(studentName, randomNumber, seatColor);

        students.add(student);

        // Fill the seat with the chosen color

        seats_Type.get(student.getSeat_Index_Type()).setFill(student.getSeat_Color_Type());

// Set the student's name for the text

        names.get(student.getSeat_Index_Type()).setVisible(true);
        names.get(student.getSeat_Index_Type()).setText(student.getName_Type());

        setMessage(String.format("%s\'s seat is successfully assigned!", student.getName_Type()), case_Base_Success);

    }

    // ArrayList of students to check the seat is already taken or not

    private boolean checkSeatAvailability(int randomNumber) {

        boolean availability = true;

        //ArrayList of students to check the seat number is taken or not

        for (int i = 0; i < students.size(); i++) {


            if (students.get(i).getSeat_Index_Type() == randomNumber) {
                availability = false;
            }
        }

        return availability;
    }

    private boolean checkColorAvailability(Color color){

        boolean availability= true;

        for(int i = 0; i<students.size(); i++){

            if(students.get(i).getSeat_Color_Type().equals(color)){
                availability = false;
            }
        }
        return availability;

    }
    
}
