package com.xonro.vflow.dataview;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.QueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataviewApplicationTests {
    @PersistenceContext
    private EntityManager em;
	@Test
	public void contextLoads() {
		String sql = " select column_name from information_schema.columns where table_name = 'b_xr_wechat_message' ";
		String sql1 = " WHERE 1=1 and ??? ";
		Query query = em.createNativeQuery(sql1);
        query.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.getResultList();
        Map map = (Map) list.get(0);
        for (Object key: map.keySet()){
            System.err.println(key);
        }

		System.err.println();
	}

}
