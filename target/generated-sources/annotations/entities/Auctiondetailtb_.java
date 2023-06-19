package entities;

import entities.Auctioneermaster;
import entities.Playermaster;
import entities.Tournamenttb;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-19T16:34:17")
@StaticMetamodel(Auctiondetailtb.class)
public class Auctiondetailtb_ { 

    public static volatile SingularAttribute<Auctiondetailtb, Integer> auctionId;
    public static volatile SingularAttribute<Auctiondetailtb, Date> date;
    public static volatile SingularAttribute<Auctiondetailtb, Tournamenttb> tornamentId;
    public static volatile ListAttribute<Auctiondetailtb, Playermaster> playermasterList;
    public static volatile SingularAttribute<Auctiondetailtb, Long> totalPurse;
    public static volatile SingularAttribute<Auctiondetailtb, Auctioneermaster> auctioneerId;
    public static volatile SingularAttribute<Auctiondetailtb, Integer> minPlayer;
    public static volatile SingularAttribute<Auctiondetailtb, Integer> bidIncrementAmount;
    public static volatile SingularAttribute<Auctiondetailtb, Long> eachPlayerBasePrice;
    public static volatile SingularAttribute<Auctiondetailtb, String> status;

}