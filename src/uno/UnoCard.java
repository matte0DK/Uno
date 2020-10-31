package uno;

import java.awt.*;

public class UnoCard {

    // INSTANCE VARIABLES
    enum Color {
        Red, Blue, Green, Yellow, Wild;

        private static final Color[] colors = Color.values();

        public static Color getColor(int i) {
            return Color.colors[i];
        }
    }

    enum Value {
        Zero,One,Two,Three,Four,Five,Six,Seven,Eight,Nine,DrawTwo,Skip,Reverse,Wild,WildFour;

        private static final Value[] values = Value.values();

        public static Value getValue(int i) {
            return Value.values[i];
        }
    }

    private final Color color;
    private final Value value;

    // CONSTRUCTOR //
    public UnoCard (final Color color,final Value value) {
        this.color = color;
        this.value = value;
    }

    // GETTERS //
    public Color getColor(){
        return this.color;
    }
    public Value getValue(){
        return this.value;
    }

    // METHOD: MAKING COLOR AND VALUE VISIBLE TO US INSTEAD OF MEMORY-ADRES, !check-method! //
    public String toString() {
        return color + "-" + value;
    }
}
