import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility

    /**
     * update method test.
     * Test which checks if the changes made in GroupAdmin have been updated in ConcreteMember.
     */
    @Test
    public void UpdateOfConcreteMemberTest() {
        GroupAdmin ga = new GroupAdmin();
        ConcreteMember yasmin = new ConcreteMember();
        ga.register(yasmin);
        ga.append("abcdefg");
        assertEquals("abcdefg", ga.toString());
        assertEquals("abcdefg", yasmin.toString());
        ga.delete(2, 5);
        assertEquals("abfg", ga.toString());
        assertEquals("abfg", yasmin.toString());
        ga.undo();
        assertEquals("abcdefg", ga.toString());
        assertEquals("abcdefg", yasmin.toString());
        ga.insert(3, "123");
        assertEquals("abc123defg", ga.toString());
        assertEquals("abc123defg", yasmin.toString());
        logger.info(()->JvmUtilities.objectTotalSize(ga));
        logger.info(()->JvmUtilities.objectTotalSize(yasmin));
        GroupAdmin san = new GroupAdmin();
        ConcreteMember avi = new ConcreteMember();
        san.register(avi);
        san.append("alltrar");
        assertEquals("alltrar", avi.toString());
        san.delete(2, 5);
        assertEquals("alar", avi.toString());
        san.undo();
        assertEquals("alltrar", avi.toString());
        san.insert(3, "ya");
        assertEquals("allyatrar", avi.toString());
        logger.info(()->JvmUtilities.objectFootprint(ga,san));
        logger.info(() -> JvmUtilities.jvmInfo());
    }

    /**
     * register method test.
     * Check if the Member that we added to the list has really joined the recipients of the updates
     * check if his UndoableStringBuilder has been updated accordingly.
     */
    @Test
    public void test_register() {
        GroupAdmin ga = new GroupAdmin();
        ConcreteMember yasmin = new ConcreteMember();
        ConcreteMember avi = new ConcreteMember();
        assertEquals("",ga.toString());
        ga.register(yasmin);
        ga.append("start");
        assertEquals("start",yasmin.toString());
        ga.register(avi);
        ga.append("before");
        assertEquals("startbefore",avi.toString());
        assertEquals("startbefore",yasmin.toString());
        logger.info(()->JvmUtilities.objectTotalSize(ga));
        logger.info(()->JvmUtilities.objectTotalSize(yasmin));
        logger.info(()->JvmUtilities.objectTotalSize(avi));
    }
    /**
     * unregister method test.
     * Check if the Member that we removed from the list has really removed from GroupAdmin.
     */
    @Test
    public void test_unregister() {
        GroupAdmin ga = new GroupAdmin();
        ConcreteMember yasmin = new ConcreteMember();
        ConcreteMember avi = new ConcreteMember();
        ConcreteMember shlomi = new ConcreteMember();
        ga.register(yasmin);
        ga.register(avi);
        ga.register(shlomi);
        assertEquals("",ga.toString());
        ga.append("start");
        assertEquals("start", shlomi.toString());
        ga.unregister(shlomi);
        assertEquals("null",shlomi.toString());
        logger.info(()->JvmUtilities.objectFootprint(ga,shlomi));
    }
    /**
     * notifyObservers method test.
     * * check if the expected UndoableStringBuilder of member equals the actual UndoableStringBuilder after notifyObservers method.
     */
    @Test
    public void test_notifyObservers() {
        GroupAdmin ga = new GroupAdmin();
        ConcreteMember yasmin = new ConcreteMember();
        assertEquals("",ga.toString());
        ga.register(yasmin);
        ga.append("hello");
        assertEquals("hello",yasmin.toString());
        ga.undo();
        assertEquals("",yasmin.toString());
      logger.info(()->"this is objectFootprint in GroupAdmin"+JvmUtilities.objectFootprint(ga));

    }
    /**
     * append method test.
     * check if the expected string equals to the actual string after append method.
     * check if the expected string equals to the actual string after undo method.
     */

    @Test
    public void test_append() {
        GroupAdmin ga = new GroupAdmin();
        assertEquals("",ga.toString());
        ga.append("hello");
        assertEquals("hello",ga.toString());
        ga.append(" world!");
        assertEquals("hello world!",ga.toString());
        ga.undo();
        assertEquals("hello",ga.toString());
        ga.undo();
        assertEquals("",ga.toString());
        ga.undo();
        assertEquals("",ga.toString());
       logger.info(()->"this is objectFootprint in GroupAdmin"+JvmUtilities.objectFootprint(ga));
    }
    /**
     * delete method test.
     * check if the expected string equals to the actual string after delete method.
     * check if the expected string equals to the actual string after undo method.
     */
    @Test
    public void test_delete() {
        UndoableStringBuilder actual = new UndoableStringBuilder("hello world!");
        GroupAdmin ga = new GroupAdmin(actual);
        assertEquals("hello world!",ga.toString());
        ga.delete(5,11);
        assertEquals("hello!",ga.toString());
        ga.delete(8,11);
        assertEquals("hello!",ga.toString());
        ga.delete(4,3);
        assertEquals("hello!",ga.toString());
        ga.delete(1,6);
        assertEquals("h",ga.toString());
        ga.undo();
        assertEquals("hello!",ga.toString());
        ga.delete(0,6);
        assertEquals("",ga.toString());
        logger.info(()->"this is objectFootprint in GroupAdmin"+JvmUtilities.objectFootprint(ga));

    }
    /**
     * insert method test.
     * check if the expected string equals to the actual string after insert method.
     * check if the expected string equals to the actual string after undo method.
     */
    @Test
    public void test_insert() {
        logger.info(() -> JvmUtilities.jvmInfo());
        GroupAdmin ga = new GroupAdmin();
        ga.insert(1,"hello world!");
        assertEquals("",ga.toString());
        ga.insert(0,"hello world!");
        assertEquals("hello world!",ga.toString());
        ga.insert(6,"beautiful ");
        assertEquals("hello beautiful world!",ga.toString());
        ga.undo();
        assertEquals("hello world!",ga.toString());
        ga.undo();
        assertEquals("",ga.toString());
        logger.info(()->"this is objectFootprint in GroupAdmin"+JvmUtilities.objectFootprint(ga));
        logger.info(() -> JvmUtilities.jvmInfo());
    }

    /**
     * undo method test.
     * check if the expected string equals to the actual string after undo method.
     */
    @Test
    public void test_undo() {
        GroupAdmin ga = new GroupAdmin();
        ga.append("to be or not to be") ;
        assertEquals("to be or not to be",ga.toString());
        ga.append(" chose"); ;
        assertEquals("to be or not to be chose",ga.toString());
        ga.append(" dad"); ;
        assertEquals("to be or not to be chose dad",ga.toString());
        ga.undo();
        assertEquals("to be or not to be chose",ga.toString());
        ga.undo();
        assertEquals("to be or not to be",ga.toString());
        logger.info(()->"this is objectFootprint in GroupAdmin"+JvmUtilities.objectFootprint(ga));

    }

    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()->JvmUtilities.objectFootprint(s1));

        logger.info(()->JvmUtilities.objectFootprint(s1,s2));

        logger.info(()->JvmUtilities.objectTotalSize(s1));

        logger.info(() -> JvmUtilities.jvmInfo());
    }
}
