import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class controller {

    @FXML
    private Label nameLabel;

    @FXML
    private Button addDataButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTextField;

    @FXML
    private Label genreLabel;

    @FXML
    private TextField genreTextField;

    @FXML
    private Label nauseaLabel;

    @FXML
    private TextField nauseasTextField;

    @FXML
    private Label vomitLabel;

    @FXML
    private TextField vomitTextField;

    @FXML
    private Label abPainLabel;

    @FXML
    private TextField abPainTextField;

    @FXML
    private Label diarrheaLabel;

    @FXML
    private TextField diarrheaTextField;

    @FXML
    private Label feverLabel;

    @FXML
    private TextField feverTextField;

    @FXML
    private Button processButton;

    @FXML
    private TextArea richTextField1;

    @FXML
    private TextArea richTextField2;

    @FXML
    void clickDataButton(ActionEvent event) {
        String s = String.join("-", nameTextField.getText(),
                                    idTextField.getText(),
                                    genreTextField.getText(),
                                    nauseasTextField.getText(),
                                    vomitTextField.getText(),
                                    abPainTextField.getText(),
                                    diarrheaTextField.getText(),
                                    feverTextField.getText());
        //System.out.println(s);
        richTextField1.setText(richTextField1.getText() + s + "\n");
        nameTextField.setText("");
        idTextField.setText("");
        genreTextField.setText("");
        nauseasTextField.setText("");
        vomitTextField.setText("");
        abPainTextField.setText("");
        diarrheaTextField.setText("");
        feverTextField.setText("");
    }

    public static float promedio(Paciente[] pxs) {
        float tot = 0;
        for (Paciente px : pxs) {
            tot += px.getEdad();
        }
        return (float) tot/pxs.length;
    }

    @FXML
    void arribaProm(Paciente[] pxs, float promedio) {
        for (Paciente px : pxs) {
            if (px.getEdad() > promedio) {
                System.out.println(px.getNombre() + " " + px.getCedula());
                richTextField2.setText(richTextField2.getText() + px.getNombre() + " " + px.getCedula() + "\n");
            }
        }
    }

    public static void contEnfermedades(Paciente[] pxs, int[] conts) {
        for (Paciente px : pxs) {
            switch (px.getEnfermedad()) {
                case "Cancer":
                    conts[0] += 1;
                    break;
                case "Cardiovasculares":
                    conts[1] += 1;
                    break;
                case "Respiratorias":
                    conts[2] += 1;
                    break;
                case "Cerebrovasculares":
                    conts[3] += 1;
                    break;
                case "Hipertension":
                    conts[4] += 1;
                    break;
                case "Diabetes":
                    conts[5] += 1;
                    break;
            }
        }
    }

    @FXML
    void clickProcessButton(ActionEvent event) {
        String datitos = richTextField1.getText().strip();
        String[] pacientes = datitos.split("\n");
        Paciente datos[] = new Paciente[pacientes.length];

        String[] enfermedades = {"Cancer", "Cardiovasculares", "Respiratorias", "Cerebrovasculares", "Hipertension", "Diabetes"};
        int[] contEnf = {0, 0, 0, 0, 0, 0};

        for (int i = 0; i < pacientes.length; i++) {
            String[] temp = pacientes[i].split("-");
            Paciente px = new Paciente(temp[0], temp[1], Integer.parseInt(temp[2]), temp[3], temp[4], temp[5]);
            datos[i] = px;
        }

        float prom = promedio(datos);
        System.out.println(String.format("%.2f", prom));
        richTextField2.setText(richTextField2.getText() + String.format("%.2f", prom) + "\n");

        arribaProm(datos, prom);
        contEnfermedades(datos, contEnf);

        for (int i = 0; i < 6; i++) {
            System.out.println(enfermedades[i] + " " + contEnf[i]);
            richTextField2.setText(richTextField2.getText() + enfermedades[i] + " " + contEnf[i] + "\n");
        }

        for (Paciente px : datos) {
            if (px.clasificarEdad().equalsIgnoreCase("joven adulto")) {
                System.out.println(px.getNombre() + " " + px.getCedula());
                richTextField2.setText(richTextField2.getText() + px.getNombre() + " " + px.getCedula() + "\n");
            }
        }

    }

}