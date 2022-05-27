package trabajo_grupal.actores;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class MultiplicarParte extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TaskCalcElement.class, t -> {
                    t.calcValue();
                    sender().tell(t, self());
                })
                .build();
    }

    public static Props props(){
        return Props.create(MultiplicarParte.class);
    }
}
