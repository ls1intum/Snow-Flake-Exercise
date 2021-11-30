module KochSnowflake {
    requires javafx.controls;
    requires javafx.graphics;

    opens de.tum.in.ase to javafx.graphics, javafx.controls;

    exports de.tum.in.ase;
}
