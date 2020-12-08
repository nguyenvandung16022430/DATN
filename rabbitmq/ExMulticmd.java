public class ExMulticmd implements Runnable {
  String message;
  public ExMulticmd(String s){
  this.message = s;
  }
  public void run() {
    try{
    System.out.println(this.message);
    ExCommand cmd = new ExCommand();
    cmd.excuteCommand(this.message);
    if(message.equals("stop")){
      cmd.excuteCommand("deletePidFile");
    }
    }catch( Exception e){
      System.out.println(e);
    };
  }
}
