package trabajo_grupal.actores;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
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

        ActorSystem actorSystem = ActorSystem.create("system");
        ActorRef taskActor = actorSystem.actorOf(RunMatriz.props(), "RunMatriz");
        taskActor.tell(new DosGuardarMatrices(m1,m2), ActorRef.noSender());

    }

    private static void printArray(int[] arr, boolean vertical) {
        var escapeSquence = vertical ? "\n" : "\t";

        for(var i = 0; i < arr.length; i ++) {
            System.out.printf("%d%s", arr[i], escapeSquence);
        }
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Matriz.class, m -> System.out.println(m.toString()))
                .match(DosGuardarMatrices.class, dm -> {
                    ActorSystem actorSystem = ActorSystem.create("RunMatriz");
                    ActorRef taskActor = actorSystem.actorOf(MultiplicarMatrices.props(), "MultiplicarMatrices");
                    taskActor.tell(dm, self());
                })
                .build();
    }

    public static Props props(){
        return Props.create(RunMatriz.class);
    }
}
