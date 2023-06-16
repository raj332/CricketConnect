package entities;

import entities.Soldplayertb;
import entities.Tournamentplayerslist;
import entities.Unsoldplayertb;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-16T11:09:24")
@StaticMetamodel(Playermaster.class)
public class Playermaster_ { 

    public static volatile SingularAttribute<Playermaster, Tournamentplayerslist> tournamentplayerslist;
    public static volatile SingularAttribute<Playermaster, Soldplayertb> soldplayertb;
    public static volatile SingularAttribute<Playermaster, String> bowlingType;
    public static volatile SingularAttribute<Playermaster, Boolean> isWicketkeeper;
    public static volatile SingularAttribute<Playermaster, Long> mobileNumber;
    public static volatile SingularAttribute<Playermaster, Unsoldplayertb> unsoldplayertb;
    public static volatile SingularAttribute<Playermaster, String> name;
    public static volatile SingularAttribute<Playermaster, byte[]> photo;
    public static volatile SingularAttribute<Playermaster, String> battingPosition;
    public static volatile SingularAttribute<Playermaster, String> battingType;
    public static volatile SingularAttribute<Playermaster, Integer> age;
    public static volatile SingularAttribute<Playermaster, String> playerId;

}