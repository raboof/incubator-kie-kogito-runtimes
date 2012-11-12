package org.drools.command;

import static org.drools.persistence.util.PersistenceUtil.*;

import java.util.HashMap;

import org.drools.persistence.util.PersistenceUtil;
import org.junit.After;
import org.kie.KnowledgeBase;
import org.kie.KnowledgeBaseFactory;
import org.kie.persistence.jpa.JPAKnowledgeService;
import org.kie.runtime.KnowledgeSessionConfiguration;
import org.kie.runtime.StatefulKnowledgeSession;

public class KBuilderBatchExecutionPersistenceTest extends KBuilderBatchExecutionTest {

    private HashMap<String, Object> context;
    
    @After
    public void cleanUpPersistence() throws Exception {
        disposeKSession();
        cleanUp(context);
        context = null;
    }

    protected StatefulKnowledgeSession createKnowledgeSession(KnowledgeBase kbase) { 
        if( context == null ) { 
            context = PersistenceUtil.setupWithPoolingDataSource(DROOLS_PERSISTENCE_UNIT_NAME);
        }
        KnowledgeSessionConfiguration ksconf = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
        return JPAKnowledgeService.newStatefulKnowledgeSession(kbase, ksconf, createEnvironment(context));
    }  
    
}
