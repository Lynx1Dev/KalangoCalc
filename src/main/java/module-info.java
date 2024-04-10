module sol.kalango.kalangocalc {
    requires javafx.fxml;
	requires com.google.gson;

    requires transitive javafx.controls;
	requires jdk.compiler;

	opens sol.kalango.kalangocalc to javafx.fxml;
    exports sol.kalango.kalangocalc;
}