package observer;

import java.util.ArrayList;
/**
 *The GroupAdmin class contains the situation database (UdoableStringBuilder),
 * and a customer database that should receive updates on any changes made to the database.
 * The class describes the sender of the updates (Observable).
 */
public class GroupAdmin implements Sender {
    private UndoableStringBuilder un;
    private ArrayList<Member> myMember;

    /**
     *a default constructor to initialize the UndoableStringBuilder and also to initialize the ArrayList of member .
     */
    public GroupAdmin(){
        un = new UndoableStringBuilder();
        myMember = new ArrayList<>();
    }
    /**
     *a constructor to initialize the UndoableStringBuilder with a parameter.
     * and also to initialize the ArrayList of member .
     * @param s a UndoableStringBuilder for to initialize the UndoableStringBuilder of this class.
     */
    public GroupAdmin(UndoableStringBuilder s){
       this.un = s;
       this.myMember = new ArrayList<>();
    }

    /**
     * register is a function that adds a member to the list of the "updates of the UndoableStringBuilder".
     * @param obj This is a member who join the list of updates
     */
    @Override
    public void register(Member obj) {
        this.myMember.add(obj);
        obj.update(this.un);
    }
    /**
     * unregister is a function that removes a member from the list of the "updates of the UndoableStringBuilder".
     * @param obj This is a member that we remove from the list of updates.
     */
    @Override
    public void unregister(Member obj) {
        this.myMember.remove(obj);
        obj.update(null);
    }

    /**
     * Insert a String to a specific place in the Current String.
     * @param offset the index to insert the String.
     * @param obj The String to insert.
     */
    @Override
    public void insert(int offset, String obj) {
        un.insert(offset,obj);
        notifyObservers();
    }
    /**
     * appending new String to the end of the current String.
     * @param obj The String for appending.
     */
    @Override
    public void append(String obj) {
        un.append(obj);
        notifyObservers();
    }
    /**
     * Delete all the chars between two indexes.
     * @param start The first index to delete.
     * @param end The last index to delete.
     */
    @Override
    public void delete(int start, int end) {
        un.delete(start,end);
        notifyObservers();
    }
    /**
     * Undo the last change in the String Builder.
     */
    @Override
    public void undo() {
        un.undo();
        notifyObservers();
    }
    /**
     * notifyObservers is a function that updates everyone who is registered to GroupAdmin
     * with every change in UndoableStringBuilder.
     */
    public void notifyObservers() {
        for (Member o : this.myMember){
            o.update(un);
        }
    }
    /**
     * function that printing the UndoableStringBuilder of GroupAdmin
     */
    @Override
    public String toString() {
        if (un == null){
            return "null";
        }
        return un.toString();
    }
}