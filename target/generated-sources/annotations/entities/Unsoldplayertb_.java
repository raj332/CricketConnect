package entities;

import entities.Playermaster;
import entities.Tournamenttb;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-22T01:32:37")
@StaticMetamodel(Unsoldplayertb.class)
public class Unsoldplayertb_ { 

    public static volatile SingularAttribute<Unsoldplayertb, Date> date;
    public static volatile SingularAttribute<Unsoldplayertb, Tournamenttb> tournamentId;
    public static volatile SingularAttribute<Unsoldplayertb, Playermaster> playermaster;
    public static volatile SingularAttribute<Unsoldplayertb, String> playerId;

}