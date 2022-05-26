package trabajo_grupal.Concurrente;

public class TaskCalcElement extends Thread{
    private Matriz m1;
    private Matriz m2;
    private int rowIndex;
    private int cloIndex;
    private int element;

    public TaskCalcElement(Matriz m1, Matriz m2, int rowIndex, int cloIndex) {
        this.m1 = m1;
        this.m2 = m2;
        this.rowIndex = rowIndex;
        this.cloIndex = cloIndex;
    }

    @Override
    public void run(){
        element = calcValue(m1.getRow(rowIndex), m2.getColumn(cloIndex));
    }

    private int calcValue(int[] row, int[] col){
        int aux = 0 ;
        for (var i=0; i<row.length;i++){
            aux += row[i] * col[i];
        }
        return aux;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getCloIndex() {
        return cloIndex;
    }

    public int getElement() {
        return element;
    }
}
