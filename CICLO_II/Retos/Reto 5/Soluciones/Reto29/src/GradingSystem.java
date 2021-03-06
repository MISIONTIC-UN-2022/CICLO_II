public class GradingSystem {
	private final double[][] gradingScale = {
	    {0,3},
	    {3,6},
	    {6,8},
	    {8,9},
	    {9,10}
	};
    protected final String[] subjects = {"fisica", "quimica", "idiomas"};
    protected final String[] students = { "armando", "nicolas", "daniel", "maria", "marcela", "alexandra"};
    protected final String[] genders = {"m", "f"};


    public GradingSystem(){}

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
            if(data[i][3] > avg)
                count++;
        }
        return count;
    }

    public int stat2(double[][] data){
        int count = 0;
        for(int i = 0; i < data.length; i++){
            if(gradingScale[4][0] < data[i][3] && gradingScale[4][1] >= data[i][3])
                count++;
        }
        return count;
    }

    public String stat3( double[][] data){
        double[] gendersSum = {0,0};
        int[] gendersCount = {0,0};
        for(int i = 0; i < data.length; i++){
            gendersSum[(int)(data[i][1])] +=  data[i][3];
            gendersCount[(int)(data[i][1])] = gendersCount[(int)(data[i][1])] + 1;
        }
        double auxMax = 0;
        int auxIndex = -1;
        for(int i = 0; i < gendersSum.length; i++){
            if(gendersCount[i] != 0 && auxMax < gendersSum[i]/gendersCount[i]){
                auxMax = gendersSum[i]/gendersCount[i];
                auxIndex = i;
            }
        }
        return genders[auxIndex];
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