import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controlador {

    static double[][] gradingScale0 = {
	    {0,1},
	    {1,2.5},
	    {2.5,3,5},
	    {3.5,4.5},
	    {4.5,5}
	};
	static double[][] gradingScale1 = {
	    {0,3},
	    {3,6},
	    {6,8},
	    {8,9},
	    {9,10}
	};
	static double[][] gradingScale2 = {
	    {0,30},
	    {30,60},
	    {60,80},
	    {80,90},
	    {90,100}
	};
    static String[] subjects = {"informatica", "fisica", "quimica"};
	static double[][] gradingScale = gradingScale0;
    static String[] students = { "armando", "nicolas", "daniel", "maria", "marcela", "alexandra"};
    static String[] genders = {"m", "f"};
    static Estudiante[] data = new Estudiante[6];

    @FXML
    private Button ingresar;

    @FXML
    private TextField nombre;

    @FXML
    private TextField genero;

    @FXML
    private TextField materia;

    @FXML
    private TextField nota;

    @FXML
    private TextArea entrada;

    @FXML
    private Button procesar;

    @FXML
    private TextArea salida;

    @FXML
    void clicIngresar(ActionEvent event) {

        String s = String.join(" ", nombre.getText(),
        genero.getText(),
        materia.getText(),
        nota.getText());
        entrada.setText(entrada.getText() + s + "\n");

    }

    @FXML
    void clicProcesar(ActionEvent event) {
        readData(entrada.getText());
        print(getTestsPerformanceOverAvg(data));
        print(getExamsPercentajeOutstanding(data));
        print(subjects[getBestSubjectForFemales(data)]);
        print(students[getStudentBestGradeBySubject(data) -1]);
    }

    public static void print(Object a){
        if(a instanceof Double){
            System.out.printf("%.2f\n",a);
        }else{
            System.out.println(a);
        }
        
    }   
    
    public static void readData(String entrada){

        String[] datos = entrada.split("\n");
        int x = 0;
        for (int i = 0; i < datos.length; i=i+3) {
            String[] line1 = datos[i].split(" ");
            String[] line2 = datos[i+1].split(" ");
            String[] line3 = datos[i+2].split(" ");
            double id = Double.parseDouble(line1[0]);
            double gen = Double.parseDouble(line1[1]);
            double notaI = Double.parseDouble(line1[3]);
            double notaH = Double.parseDouble(line2[3]);
            double notaL = Double.parseDouble(line3[3]);
            data[x] = new Estudiante(id, gen, notaI, notaH, notaL);
            x++;
        }

    }

    // '??Cu??l es el desempe??o promedio de todo el grupo?'
    public static double getAverageAllGrades(Estudiante[] data){
        double sum = 0;
        for(int i = 0; i < data.length; i++){
            sum += data[i].getNotaFisica() + data[i].getNotaInformatica() + data[i].getNotaQuimica() ;
        }
        return (sum / (data.length*3));
    }


    public static boolean isAprobbed(double grade){
        return gradingScale[2][0] < grade;
    }

    public static int getTestsPerformanceOverAvg(Estudiante[] data){
        int count = 0;
        double avg = getAverageAllGrades(data);
        for(int i = 0; i < data.length; i++){
            if(data[i].getNotaFisica() > avg)
                count++;
            if(data[i].getNotaInformatica() > avg)
                count++;
            if(data[i].getNotaQuimica() > avg)
                count++;
        }
        return count;
    }

    public static int getExamsOutstanding(Estudiante[] data){
        int count = 0;
        for(int i = 0; i < data.length; i++){
            if(gradingScale[3][0] < data[i].getNotaFisica() && gradingScale[3][1] >= data[i].getNotaFisica())
                count++;
            if(gradingScale[3][0] < data[i].getNotaInformatica() && gradingScale[3][1] >= data[i].getNotaInformatica())
                count++;
            if(gradingScale[3][0] < data[i].getNotaFisica() && gradingScale[3][1] >= data[i].getNotaFisica())
                count++;
        }
        return count;
    }
    public static double getExamsPercentajeOutstanding(Estudiante[] data){
        return (double)(getExamsOutstanding(data)) / data.length;
    }

    public static int getBestSubjectForFemales(Estudiante[] data){
        double[] subjectsSum = {0,0,0};
        int[] subjectsCount = {0,0,0};

        for(int j = 0; j < data.length; j++){
            if(data[j].getGenero() == 1.0){
                subjectsSum[0] = subjectsSum[0] + data[j].getNotaInformatica();
                subjectsSum[1] = subjectsSum[0] + data[j].getNotaFisica();
                subjectsSum[2] = subjectsSum[0] + data[j].getNotaQuimica();
                subjectsCount[0] = subjectsCount[0] + 1;
                subjectsCount[1] = subjectsCount[1] + 1;
                subjectsCount[2] = subjectsCount[2] + 1;
            }
        }
        
        double auxMax = 0;
        int auxIndex = -1;
        for(int i = 0; i < subjectsSum.length; i++){
            if(subjectsCount[i] != 0 && auxMax < subjectsSum[i]/subjectsCount[i]){
                auxMax = subjectsSum[i]/subjectsCount[i];
                auxIndex = i;
            }
                
        }
        return auxIndex;
    }

    public static int getStudentBestGradeBySubject(Estudiante[] data){
        double max = data[0].getNotaInformatica();
        int indMax = (int)data[0].getId();
        for(int i = 0; i < data.length; i++){
            if(data[i].getNotaInformatica() > max){
                max = data[i].getNotaInformatica();
                indMax = (int)data[i].getId();
            }
        }
        return indMax;
    }

}
