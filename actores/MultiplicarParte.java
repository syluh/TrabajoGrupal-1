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

                    ActorSystem actorSystem = ActorSystem.create();
                    ActorRef taskActor = actorSystem.actorOf(MultiplicarMatrices.props(), "MultiplicarMatrices");

                    sender().tell(t, taskActor);
                })

                .build();
    }

}
