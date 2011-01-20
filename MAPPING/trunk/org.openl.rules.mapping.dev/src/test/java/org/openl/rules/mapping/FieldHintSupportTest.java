package org.openl.rules.mapping;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
import org.openl.rules.mapping.to.A;
import org.openl.rules.mapping.to.ArrayContainer;
import org.openl.rules.mapping.to.C;
import org.openl.rules.mapping.to.inheritance.ChildA;

public class FieldHintSupportTest {

    @Test
    public void indexedListsWithoutGenericTypeTest() {

        File source = new File("src/test/resources/org/openl/rules/mapping/FieldHintsSupportTest.xlsx");
        RulesBeanMapper mapper = RulesBeanMapperFactory.createMapperInstance(source);

        C c = new C();
        c.setAString("root");

        List aList = new ArrayList();

        A a = new A();
        a.setAString("a-string");
        a.setAnInteger(100);
        a.setAStringArray(new String[] { "x", "y" });

        aList.add(a);

        ChildA childA = new ChildA();
        childA.setAString("child-a-string");
        childA.setAnInteger(50);

        List childList = new ArrayList();

        A innerA = new A();
        innerA.setAString("inner-a-string");

        ChildA innerChildA = new ChildA();
        innerChildA.setAString("10");

        childList.add(innerA);
        childList.add(innerChildA);

        childA.setAList(childList);

        aList.add(childA);

        c.setAList(aList);

        ArrayContainer acontainer = mapper.map(c, ArrayContainer.class);
        assertEquals(7, acontainer.getArray().length);
        assertEquals("root", acontainer.getArray()[0]);
        assertEquals("a-string", acontainer.getArray()[1]);
        assertEquals("child-a-string", acontainer.getArray()[2]);
        assertEquals("inner-a-string", acontainer.getArray()[3]);
        assertEquals("10", acontainer.getArray()[4]);
        assertEquals("inner-a-string", acontainer.getArray()[5]);
        assertEquals(Integer.valueOf(10), acontainer.getArray()[6]);

        C c1 = mapper.map(acontainer, C.class);
        
        assertEquals("root", c1.getAString());
        assertEquals(2, c1.getAList().size());
        assertEquals(A.class, c1.getAList().get(0).getClass());
        assertEquals("a-string", ((A)c1.getAList().get(0)).getAString());
        assertEquals(ChildA.class, c1.getAList().get(1).getClass());
        assertEquals("child-a-string", ((ChildA)c1.getAList().get(1)).getAString());
        assertEquals(2, ((ChildA)c1.getAList().get(1)).getAList().size());
        assertEquals(A.class, ((ChildA)c1.getAList().get(1)).getAList().get(0).getClass());
        assertEquals("inner-a-string", ((A)((ChildA)c1.getAList().get(1)).getAList().get(0)).getAString());
        assertEquals(A.class, ((ChildA)c1.getAList().get(1)).getAList().get(1).getClass());
        assertEquals("10", ((A)((ChildA)c1.getAList().get(1)).getAList().get(1)).getAString());
    }
}
