import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class controller {

    @FXML
    private Label pacientLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTextField;

    @FXML
    private Label ageLabel;

    @FXML
    private TextField ageTextField;

    @FXML
    private Label cityLabel;

    @FXML
    private TextField cityTextField;

    @FXML
    private Label epsLabel;

    @FXML
    private TextField epsTextField;

    @FXML
    private Label diseaseLabel;

    @FXML
    private TextField diseaseTextField;

    @FXML
    private Button addPacientButton;

    @FXML
    private TextArea textArea1;

    @FXML
    private TextArea textArea2;

    @FXML
    private Label mNameLabel;

    @FXML
    private TextField mNameTextField;

    @FXML
    private Label mIDLabel;

    @FXML
    private TextField mIDTextField;

    @FXML
    private Label pIDLabel;

    @FXML
    private TextField pIDTextField;

    @FXML
    private ListView<Medico> medicList;

    @FXML
    private TextArea textArea3;

    @FXML
    private Button addMedicButton;

    @FXML
    private Button addPacientToMedicButton;

    @FXML
    private Button processDataButton;

    @FXML
    void addMedicButtonClick(ActionEvent event) {
        Medico md = new Medico(mNameTextField.getText(), mIDTextField.getText());
        medicList.getItems().add(md);
        mNameTextField.setText("");
        mIDTextField.setText("");
    }

    @FXML
    public void itemMouseClick(MouseEvent event) {
        Medico md = medicList.getSelectionModel().getSelectedItem();
        textArea3.setText("");
        for (String px : md.getPacientes()) {
            textArea3.setText(textArea3.getText() + px + "\n");
        }
    }

    @FXML
    void addPacientButtonClick(ActionEvent event) {
        String s = String.join("-", nameTextField.getText(),
                                    idTextField.getText(),
                                    ageTextField.getText(),
                                    cityTextField.getText(),
                                    epsTextField.getText(),
                                    diseaseTextField.getText());
        //System.out.println(s);
        textArea1.setText(textArea1.getText() + s + "\n");
        nameTextField.setText("");
        idTextField.setText("");
        ageTextField.setText("");
        cityTextField.setText("");
        epsTextField.setText("");
        diseaseTextField.setText("");
    }

    @FXML
    void addPacientToMedicButtonClick(ActionEvent event) {
        Medico md = medicList.getSelectionModel().getSelectedItem();
        md.addPaciente(pIDTextField.getText());
        pIDTextField.setText("");
    }

    public static boolean buscarElem(String[] vec, String elem) {
        boolean res = false;
        for (int  i = 0; i  < vec.length; i ++) {
            if (elem.equals(vec[i])) {
                return true;
            }
        }
        return res;
    }

    @FXML
    void contarCiudades(Paciente[] pxs) {
        String[] visitadas = new String[pxs.length];
        int pos = 0;
        for (int i = 0; i < pxs.length; i++) visitadas[i] = "";
        for (Paciente px : pxs) {
            if(buscarElem(visitadas, px.getCiudad()) == false) {
                visitadas[pos] = px.getCiudad();
                pos += 1;
            }
        }

        int[] cont = new int[pos];
        for (int i = 0; i < pos; i++) {
            for (Paciente px : pxs) {
                if (visitadas[i].equals(px.getCiudad())) {
                    cont[i] += 1;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < cont.length; i++) {
            if (cont[i] < min) min = cont[i];
            System.out.println(visitadas[i] + " " + cont[i]);
            textArea2.setText(textArea2.getText() + visitadas[i] + " " + cont[i] + "\n");
        }
        for (int i = 0; i < cont.length; i++) {
            if (cont[i] == min) {
                System.out.println(visitadas[i]);
                textArea2.setText(textArea2.getText() + visitadas[i] + "\n");
                break;
            }
        }
    }

    @FXML
    void processDataButtonClick(ActionEvent event) {
        String datitos = textArea1.getText().strip();
        String[] pacientes = datitos.split("\n");
        Paciente datos[] = new Paciente[pacientes.length];

        for (int i = 0; i < pacientes.length; i++) {
            String[] temp = pacientes[i].split("-");
            Paciente px = new Paciente(temp[0], temp[1], Integer.parseInt(temp[2]), temp[3], temp[4], temp[5]);
            datos[i] = px;
        }

        contarCiudades(datos);

        for (Paciente px : datos) {
            if(px.clasificarEdad().equalsIgnoreCase("tercera edad")) {
                textArea2.setText(textArea2.getText() + px.getNombre() + " " + px.getCedula() + "\n");
            }
        }
    }

}
