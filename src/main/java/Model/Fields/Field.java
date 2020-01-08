package Model.Fields;

import Model.Player;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public abstract class Field {
    protected int id;
    protected String text;
    protected Color bg_color;
    protected Color fg_color;

    public Field(int id, String text){
        this.id= id;
        this.text = text;
    }
    public Field(int id, String text, Color C_bg, Color C_text){
        this.id= id;
        this.text = text;
        this.bg_color = C_bg;
        this.fg_color = C_text;
    }

    public int getID() {
        return this.id;
    }
    public abstract GUI_Field getGUIVersion();

    @Override
    public String toString() {
        return this.text;
    }
}
