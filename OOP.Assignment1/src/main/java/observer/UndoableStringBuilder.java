package observer;
import java.util.Stack;

/**
 * a class for String Builder functions with an undo method.
 */
public class UndoableStringBuilder {
    private Stack<StringBuilder> st;

    /**
     *a default constructor to initialize the stack.
     */
    public UndoableStringBuilder(){
        this.st = new Stack<>();
    }

    /**
     *a constructor to initialize the stack with a parameter.
     * @param str a String for the String Builder.
     */
    public UndoableStringBuilder( String str){
        this.st = new Stack<>();
        this.st.push(new StringBuilder(str));

    }

    /**
     * appending new String to the end of the current String.
     * @param str The String for appending.
     * @return The appended String Builder.
     */
    public UndoableStringBuilder append(String str){
        StringBuilder help = new StringBuilder();;
        if(!this.st.isEmpty())
            help.append(this.st.peek().toString());
        try
        {
            help.append(str);
        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        this.st.push(help);
        return this;
    }

    /**
     * Delete all the chars between two indexes.
     * @param start The first index to delete.
     * @param end The last index to delete.
     * @return The current String Builder after delete.
     */
    public UndoableStringBuilder delete(int start, int end){
        StringBuilder help = new StringBuilder();;
        if(!this.st.isEmpty())
            help.append(this.st.peek().toString());
        try
        {
            help.delete(start,end);
        }
        catch (StringIndexOutOfBoundsException e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        this.st.push(help);
        return this;
    }

    /**
     * Insert a String to a specific place in the Current String.
     * @param offset the index to insert the String.
     * @param str The String to insert.
     * @return The current String Builder after insert.
     */
    public UndoableStringBuilder insert(int offset, String str){
        StringBuilder help = new StringBuilder();;
        if(!this.st.isEmpty())
            help.append(this.st.peek().toString());
        try
        {
            help.insert(offset,str);
        }
        catch (StringIndexOutOfBoundsException e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        this.st.push(help);
        return this;
    }

    /**
     * Replace some chars to another String.
     * @param start The first index for the replacing.
     * @param end The last index for the replacing.
     * @param str The new String for the replacing.
     * @return The current String Builder after replace.
     */
    public UndoableStringBuilder replace(int start, int end, String str) throws IllegalArgumentException{
        if (!(start>=0)) throw new IllegalArgumentException("rlfprfp");
        StringBuilder help = new StringBuilder();;
        if(!this.st.isEmpty())
            help.append(this.st.peek().toString());
        try
        {
            help.replace(start,end,str);
        }
        catch (StringIndexOutOfBoundsException e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        this.st.push(help);
        return this;
    }

    /**
     * Reverses the current string.
     * @return The reversed String Builder.
     */
    public UndoableStringBuilder reverse(){
        StringBuilder help = new StringBuilder();;
        if(!this.st.isEmpty())
            help.append(this.st.peek().toString());
        try
        {
            help.reverse();
        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        this.st.push(help);
        return this;

    }

    /**
     * Undo the last change in the String Builder by pop the Stack.
     */
    public void undo(){
        if (!this.st.isEmpty())
            st.pop();
    }

    /**
     * Returns the current String.
     */
    @Override
    public String toString() {
        if (this.st.isEmpty())
            return "";
        return this.st.peek().toString();
    }

}