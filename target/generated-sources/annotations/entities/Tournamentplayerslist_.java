package entities;

import entities.Playermaster;
import entities.Tournamenttb;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-19T00:18:38")
@StaticMetamodel(Tournamentplayerslist.class)
public class Tournamentplayerslist_ { 

    public static volatile SingularAttribute<Tournamentplayerslist, Integer> tpid;
    public static volatile SingularAttribute<Tournamentplayerslist, Tournamenttb> tournamentId;
    public static volatile SingularAttribute<Tournamentplayerslist, String> playerStatus;
    public static volatile SingularAttribute<Tournamentplayerslist, Boolean> isApproved;
    public static volatile SingularAttribute<Tournamentplayerslist, Playermaster> playerId;

}