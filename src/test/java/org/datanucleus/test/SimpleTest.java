package org.datanucleus.test;

import java.util.*;
import java.util.stream.IntStream;

import org.junit.*;
import javax.jdo.*;

import static org.junit.Assert.*;
import mydomain.model.*;
import org.datanucleus.util.NucleusLogger;

public class SimpleTest
{

//    private ObjectPair<Query, Map<String, Object>> getPartQueryWithParams(Class clazz, String operation, String dbName,
//                                                                          String tblName, List<String> partNames) {
//        String queryStr = operation+ " FROM "+clazz.getName() +
//                " WHERE this.table.tableName == t1 && this.table.database.name == t2 && partitionList.contains(this.partitionName)" +
//                " PARAMETERS java.lang.String t1, java.lang.String t2, java.util.List partitionList";
//
//
//        Query query = pm.newQuery(queryStr);
//        Map<String, Object> params = new HashMap<>();
//        params.put("t1", HiveStringUtils.normalizeIdentifier(tblName));
//        params.put("t2", HiveStringUtils.normalizeIdentifier(dbName));
//        params.put("partitionList",partNames);
//        return new ObjectPair<Query, Map<String, Object>>(query, params);
//    }


    @Test
    public void testSimple()
    {
        NucleusLogger.GENERAL.info(">> test START");
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("MyTest");

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();
            Person john = new Person(1,"john", 30);
            john.getShoes().add(new Shoe("nike",6));
            john.getShoes().add(new Shoe("adidas",4));
            john.getShirts().add(new Shirt("nike","L"));
            pm.makePersistent(john);

            tx.commit();

            tx.begin();
            System.out.println("Bulk delete with single join");
            Query q = pm.newQuery("DELETE FROM " + Person.class.getName() +
                    " WHERE shoes.contains(shoe) && shoe.size == 5 ");
            System.out.println("number of delete people:" + q.execute());

            System.out.println("Bulk delete with multiple joins");
            q = pm.newQuery("DELETE FROM " + Person.class.getName() +
                    " WHERE shoes.contains(shoe) && shoe.size == 5 && shirts.contains(shirt) && shirt.size == 'L' ");
            System.out.println("number of delete people:" + q.execute());

            tx.commit();

        }
        catch (Throwable thr)
        {
            NucleusLogger.GENERAL.error(">> Exception in test", thr);
            fail("Failed test : " + thr.getMessage());
        }
        finally 
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }

        pmf.close();
        NucleusLogger.GENERAL.info(">> test END");
    }
}
