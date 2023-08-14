package SeatingArrangement.model;

import javafx.scene.paint.Color;

public class StudentActivity {

    private String name_Type;

    private int seat_Index_Type;

    private Color seat_Color_Type;

    public StudentActivity(String name, int seatIndex, Color seatColor){

        this.name_Type = name;
        this.seat_Index_Type = seatIndex;
        this.seat_Color_Type = seatColor;

    }
        public String getName_Type() {
            return name_Type;
        }

        public int getSeat_Index_Type() {
            return seat_Index_Type;
        }

        public Color getSeat_Color_Type() {
            return seat_Color_Type;
        }
    }
