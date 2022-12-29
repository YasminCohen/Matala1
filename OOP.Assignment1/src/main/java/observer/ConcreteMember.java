package observer;

public class ConcreteMember implements Member{

    private UndoableStringBuilder un;

    /**
     *a default constructor to initialize the UndoableStringBuilder.
     */
    public ConcreteMember(){
        this.un = new UndoableStringBuilder();
    }
    /**
     * update the UndoableStringBuilder of Member to be like the UndoableStringBuilder of his GroupAdmin.
     * @param usb is The updated UndoableStringBuilder that comes from GroupAdmin.
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.un = usb;
    }
    public String toString() {
        if (un == null){
            return "null";
        }
      return un.toString();
    }


}
