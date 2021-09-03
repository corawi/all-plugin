package Commands;

import All.All;
import Timer.Timer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TimerCommand implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(args.length == 0){
            sendUsage(sender);
            return true;
        }

        switch (args[0].toLowerCase()){
            case "resume": {
                Timer timer = All.getInstance().getTimer();

                if (timer.isRunning()) {
                    sender.sendMessage("§cDer Timer läuft bereits.");
                    break;
                }

                timer.setRunning(true);
                sender.sendMessage("§7Der Timer wurde gestartet.");
                break;
            }
            case "pause": {
                Timer timer = All.getInstance().getTimer();

                if (!timer.isRunning()) {
                    sender.sendMessage("§cDer Timer läuft nicht.");
                    break;
                }

                timer.setRunning(false);
                sender.sendMessage("§cDer Timer wurde pausiert.");
                break;
            }
            case "time": {
                if(args.length != 2){
                    sender.sendMessage("§7Verwendung§8: §9/timer time (Zeit)");
                    return true;
                }

                try {
                    Timer timer = All.getInstance().getTimer();

                    timer.setRunning(false);
                    timer.setTime(Integer.parseInt(args[1]));
                    sender.sendMessage("§cDie Zeit wurde auf " + args[1] + " gesetzt.");
                } catch (NumberFormatException e){
                    sender.sendMessage("§cDein Parameter 2 muss eine Zahl sein!");
                }
                break;
            }
            case "reset": {
                Timer timer = All.getInstance().getTimer();

                timer.setRunning(false);
                timer.setTime(0);
                sender.sendMessage("§cDer Timer wurde zurückgesetzt.");
                break;
            }
            default:
                sendUsage(sender);
                break;
        }

        return false;
    }

    private void sendUsage(CommandSender sender){
        sender.sendMessage("§7Verwendung§8: §9/timer resume, /timer pause, /timer time (Zeit), /timer reset");
    }
}
