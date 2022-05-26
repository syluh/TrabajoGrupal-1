package trabajo_grupal.actores;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.List;

public class MultiplicarMatrices extends AbstractActor {
    private int c = 0;

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(DosGuardarMatrices.class, dm -> {
                    int legM1 = dm.m1().getValues().length;
                    int legM2 = dm.m2().getValues()[0].length;

                    List<TaskCalcElement> actors;
                    if (legM1 == legM2) {
                        int[][] output = new int[legM1][legM2];
                        actors = new ArrayList<>();
                        for (var i = 0; i < output.length; i++) {
                            for (var j = 0; j < output[0].length; j++) {
                                TaskCalcElement actor = new TaskCalcElement(dm.m1(), dm.m2(), i, j);

                            }
                        }
                    }
                }).build();
    }

    public static Props props(){
        return Props.create(MultiplicarMatrices.class);
    }
}