
public class GameLogic{

    static int frameWidth = 800;
    static int frameHeight = 600;
    private int score;
    private int playerX; //Player(tray) position (x,y)
    private final int playerY;
    private int playerWidth; //Tray rectangular structure
    private int playerHeight;
    private int ballX,ballY; //Ball position
    private int ballDiameter;

    GameLogic(){
        score = 0;
        playerX = frameWidth/2; //To appear in the middle of the screen (frame)
        playerY = frameHeight-playerHeight; 
        playerWidth = 80;
        playerHeight = 20;
        ballX = playerX;
        ballY = frameHeight - playerHeight-ballDiameter; 
        ballDiameter = 20;
    }
}