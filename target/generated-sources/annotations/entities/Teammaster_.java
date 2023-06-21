package entities;

import entities.Soldplayertb;
import entities.Teamownermaster;
import entities.Tournamenttb;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-22T03:57:19")
@StaticMetamodel(Teammaster.class)
public class Teammaster_ { 

    public static volatile SingularAttribute<Teammaster, String> teamName;
    public static volatile SingularAttribute<Teammaster, byte[]> teamLogo;
    public static volatile ListAttribute<Teammaster, Soldplayertb> soldplayertbList;
    public static volatile SingularAttribute<Teammaster, Integer> teamId;
    public static volatile SingularAttribute<Teammaster, Tournamenttb> tournamentid;
    public static volatile SingularAttribute<Teammaster, Long> totalPurse;
    public static volatile SingularAttribute<Teammaster, Teamownermaster> ownerId;

}