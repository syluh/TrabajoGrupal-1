package trabajo_grupal.actores;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.japi.pf.ReceiveBuilder;

public class RunMatriz extends AbstractActor {
    public static void main(String[] args) {
        int[][] mat1Values = {
                {1, 2},
                {3, 4},
                {5, 6},
        };
        Matriz m1 = new Matriz(mat1Values);
        int[][] mat2Values = {
                {1, 2, 3},
                {3, 4, 5}
        };
        Matriz m2 = new Matriz(mat2Values);

        Matriz result = m1.multiply(m2);

        System.out.println(result);

        ActorSystem actorSystem = ActorSystem.create();
        ActorRef taskActor = actorSystem.actorOf(TaskCalcElement.props(), "TaskCalcElement");
        taskActor.tell(new DosGuardarMatrices(m1,m2));
    }

    private static void printArray(int[] arr, boolean vertical) {
        var escapeSquence = vertical ? "\n" : "\t";

        for(var i = 0; i < arr.length; i ++) {
            System.out.printf("%d%s", arr[i], escapeSquence);
        }
    }
}
