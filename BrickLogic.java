public class BrickLogic {
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean isBroken;

    public BrickLogic (int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.height	= height;
        this.width = width;
        this.isBroken = false;
    }
}
