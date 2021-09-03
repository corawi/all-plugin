package All;

import Commands.TimerCommand;
import Commands.fly;
import Commands.gm;
import Timer.Timer;
import Utils.not_running;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class All extends JavaPlugin{

    private static All instance;

    private Timer timer;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable(){
        System.out.print("§aDas Challenge-System wurde aktiviert!");




        getCommand("timer").setExecutor(new TimerCommand());
        Bukkit.getPluginManager().registerEvents(new not_running(),this);

        getCommand("gamemode").setExecutor(new gm());
        getCommand("gm").setExecutor(new gm());

        getCommand("fly").setExecutor(new fly());

        timer = new Timer(false,0);
    }
    @Override
    public void onDisable(){
        System.out.print("§cDas Challenge-System wurde deaktiviert");
    }

    public static All getInstance() {
        return instance;
    }

    public Timer getTimer() {
        return timer;
    }
}