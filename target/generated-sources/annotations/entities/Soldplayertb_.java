package entities;

import entities.Playermaster;
import entities.Teammaster;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-22T04:54:32")
@StaticMetamodel(Soldplayertb.class)
public class Soldplayertb_ { 

    public static volatile SingularAttribute<Soldplayertb, Date> date;
    public static volatile SingularAttribute<Soldplayertb, Teammaster> teamId;
    public static volatile SingularAttribute<Soldplayertb, Playermaster> playermaster;
    public static volatile SingularAttribute<Soldplayertb, Long> soldAmount;
    public static volatile SingularAttribute<Soldplayertb, String> playerId;

}