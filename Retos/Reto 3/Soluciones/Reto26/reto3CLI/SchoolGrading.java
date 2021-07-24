public class SchoolGrading {
	private final double[][] gradingScale = {
	    {0,3},
	    {3,6},
	    {6,8},
	    {8,9},
	    {9,10}
	};
    private final String[] subjects = {"idiomas", "historia", "literatura"};
    private final String[] students = { "armando", "nicolas", "daniel", "maria", "marcela", "alexandra"};
    private final String[] genders = {"m", "f"};


    public SchoolGrading(){}

    // '¿Cuál es el desempeño promedio de todo el grupo?'
    private double getAverageAllGrades(double[][] data){
        double sum = 0;
        for(int i = 0; i < data.length; i++){
            sum += data[i][3];
        }
        return (sum / data.length);
    }


    private boolean isAprobbed(double grade){
        return gradingScale[2][0] < grade;
    }

    public int stat1( double[][] data ){
        int count = 0;
        double avg = getAverageAllGrades();
        for(int i = 0; i < data.length; i++){
            if(data[i][3] < avg)
                count++;
        }
        return count;
    }

    private int getExamsExcellent(double[][] data){
        int count = 0;
        for(int i = 0; i < data.length; i++){
            if(gradingScale[4][0] < data[i][3] && gradingScale[4][1] >= data[i][3])
                count++;
        }
        return count;
    }
    public double stat2(double[][] data){
        return (double)(getExamsExcellent(data)) / data.length;
    }

public String stat3( double[][] data){
    int[] subjectsCount = {0,0,0};
    for(int i = 0; i < data.length; i++){
        if(!isAprobbed(data[i][3])){
            subjectsCount[(int)(data[i][2] - 1)] = subjectsCount[(int)(data[i][2] - 1)] + 1;
        }
    }
    int auxMax = 0;
    int auxIndex = -1;
    for(int i = 0; i < subjectsCount.length; i++){
        if(subjectsCount[i] != 0 && auxMax < subjectsCount[i]){
            auxIndex = i;
            auxMax = subjectsCount[i];
        }
    }
    return subjects[auxIndex];
}

    public String stat4( double[][] data){
        double[] subjectsMax = {0,0,0};
        int[] subjectsStudentIndex = {0,0,0};
        for(int i = 0; i < data.length; i++){
            if(subjectsMax[(int)(data[i][2] -1 ) ] < data[i][3]){
                subjectsMax[(int)(data[i][2] -1 ) ] = data[i][3];
                subjectsStudentIndex[(int)(data[i][2] -1 )] = (int)data[i][0];
            }
        }
        return students[subjectsStudentIndex[0] - 1];
    }

}