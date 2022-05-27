package trabajo_grupal.actores;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class MultiplicarMatrices extends AbstractActor {
    private int c = 0;
    private int[][] output;

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(DosGuardarMatrices.class, dm -> {
                    int legM1 = dm.m1().getValues().length;
                    int legM2 = dm.m2().getValues()[0].length;
                    if (legM1 == legM2) {
                        output = new int[legM1][legM2];
                        for (var i = 0; i < output.length; i++) {
                            for (var j = 0; j < output[0].length; j++) {
                                ActorSystem actorSystem = ActorSystem.create("MultiplicarMatrices");
                                ActorRef taskActor = actorSystem.actorOf(MultiplicarParte.props(), String.format("Multi-%d",c));
                                taskActor.tell(new TaskCalcElement(dm.m1(),dm.m2(),i,j), self());
                                c++;
                            }
                        }
                    }
                }).match(TaskCalcElement.class, t -> {
                    output[t.getRowIndex()][t.getCloIndex()] = t.getElement();
                    c--;
                    if (c == 0) {
                        ActorSystem actorSystem = ActorSystem.create("RunMatriz");
                        ActorRef taskActor = actorSystem.actorOf(RunMatriz.props(), "RunMatriz");
                        taskActor.tell(new Matriz(output), self());
                    }
                }).build();
    }

    public static Props props(){
        return Props.create(MultiplicarMatrices.class);
    }
}