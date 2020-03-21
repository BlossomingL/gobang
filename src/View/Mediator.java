package View;

/**
 * Created by Blossoming on 2016/12/5.
 */
public class Mediator {
    private PPMainBoard mb;
    private PPChessBoard cb;

    public Mediator(PPMainBoard mb,PPChessBoard cb)
    {
        this.cb=cb;
        this.mb=mb;
    }

    public void deliverMsg(int clickable)
    {
        //cb.setClickable(clickable);
    }
}
