package trabajo_grupal.actores;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class TaskCalcElement{
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

    public int calcValue(){
        int[] row = m1.getRow(rowIndex);
        int[] col = m2.getColumn(cloIndex);

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


    public static Props props(){
        return Props.create(TaskCalcElement.class);
    }
}
