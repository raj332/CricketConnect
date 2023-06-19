package entities;

import entities.Auctiondetailtb;
import entities.Soldplayertb;
import entities.Tournamentplayerslist;
import entities.Tournamenttb;
import entities.Unsoldplayertb;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-19T16:34:17")
@StaticMetamodel(Playermaster.class)
public class Playermaster_ { 

    public static volatile SingularAttribute<Playermaster, Boolean> isWicketkeeper;
    public static volatile SingularAttribute<Playermaster, Long> mobileNumber;
    public static volatile ListAttribute<Playermaster, Auctiondetailtb> auctiondetailtbList;
    public static volatile SingularAttribute<Playermaster, Unsoldplayertb> unsoldplayertb;
    public static volatile ListAttribute<Playermaster, Tournamentplayerslist> tournamentplayerslistList;
    public static volatile SingularAttribute<Playermaster, byte[]> photo;
    public static volatile SingularAttribute<Playermaster, Soldplayertb> soldplayertb;
    public static volatile SingularAttribute<Playermaster, String> bowlingType;
    public static volatile ListAttribute<Playermaster, Tournamenttb> tournamenttbList;
    public static volatile SingularAttribute<Playermaster, String> name;
    public static volatile SingularAttribute<Playermaster, String> battingPosition;
    public static volatile SingularAttribute<Playermaster, String> battingType;
    public static volatile SingularAttribute<Playermaster, Integer> age;
    public static volatile SingularAttribute<Playermaster, String> playerId;

}