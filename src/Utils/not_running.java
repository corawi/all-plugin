package Utils;

import All.All;
import Timer.Timer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class not_running implements Listener {



    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Timer timer = All.getInstance().getTimer();
        if (!timer.isRunning()){
            if (e.getEntity() instanceof Player) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onHunger (FoodLevelChangeEvent e) {
        Timer timer = All.getInstance().getTimer();
        if (!timer.isRunning()){
            if (e.getEntity() instanceof Player) {
                e.setCancelled(true);
                e.setFoodLevel(e.getFoodLevel());
            }
        }
    }

    @EventHandler
    public void onWalk (PlayerMoveEvent e) {
        Timer timer = All.getInstance().getTimer();
        if (!timer.isRunning()){
            e.setCancelled(true);
        }
    }
}
