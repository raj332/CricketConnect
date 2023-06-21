package entities;

import entities.Auctiondetailtb;
import entities.Organizermaster;
import entities.Playermaster;
import entities.Teammaster;
import entities.Tournamentplayerslist;
import entities.Unsoldplayertb;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-22T04:54:32")
@StaticMetamodel(Tournamenttb.class)
public class Tournamenttb_ { 

    public static volatile ListAttribute<Tournamenttb, Unsoldplayertb> unsoldplayertbList;
    public static volatile SingularAttribute<Tournamenttb, String> tournamentName;
    public static volatile SingularAttribute<Tournamenttb, Organizermaster> organizerId;
    public static volatile ListAttribute<Tournamenttb, Auctiondetailtb> auctiondetailtbList;
    public static volatile SingularAttribute<Tournamenttb, Integer> tournamentId;
    public static volatile ListAttribute<Tournamenttb, Tournamentplayerslist> tournamentplayerslistList;
    public static volatile ListAttribute<Tournamenttb, Playermaster> playermasterList;
    public static volatile SingularAttribute<Tournamenttb, Date> startingDate;
    public static volatile ListAttribute<Tournamenttb, Teammaster> teammasterList;

}