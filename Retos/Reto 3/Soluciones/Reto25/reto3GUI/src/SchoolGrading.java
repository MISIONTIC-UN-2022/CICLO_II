public class SchoolGrading {
	private final double[][] gradingScale = {
	    {0,1},
	    {1,2.5},
	    {2.5,3,5},
	    {3.5,4.5},
	    {4.5,5}
	};
    private final String[] subjects = {"biologia", "geografia", "matematicas"};
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

    public int stat2(double[][] data){
        int count = 0;
        for(int i = 0; i < data.length; i++){
            if(gradingScale[1][0] < data[i][3] && gradingScale[1][1] >= data[i][3])
                count++;
        }
        return count;
    }

    public String stat3( double[][] data){
        double[] subjectsSum = {0,0,0};
        int[] subjectsCount = {0,0,0};

        for(int j = 0; j < data.length; j++){
            subjectsSum[(int) (data[j][2]- 1)] = subjectsSum[(int) (data[j][2]- 1)] + data[j][3];
            subjectsCount[(int) (data[j][2]- 1)] = subjectsCount[(int) (data[j][2]- 1)] + 1;
        }
        
        double auxMax = 0;
        int auxIndex = -1;
        for(int i = 0; i < subjectsSum.length; i++){
            if(subjectsCount[i] != 0 && auxMax < subjectsSum[i]/subjectsCount[i]){
                auxMax = subjectsSum[i]/subjectsCount[i];
                auxIndex = i;
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