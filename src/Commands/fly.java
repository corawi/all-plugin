package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class fly implements CommandExecutor {
    public static ArrayList<Player> fly = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command is available for players only.");
            return true;
        }
        Player p = (Player) sender;
        if (p.hasPermission("advancedlobby.commands.fly")) {
            if (args.length == 0) {
                if (!fly.contains(p)) {
                    fly.add(p);
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.sendMessage("§cDer FliegeModus wurde aktiviert!");
                    return true;
                }
                fly.remove(p);
                p.setAllowFlight(false);
                p.setFlying(false);
                p.sendMessage("§cDer FliegeModus wurde deaktiviert!");
                return true;
            }
            if (args.length == 1) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    p.sendMessage("§cDer Spiler " + target.getDisplayName() + " wurde nicht gefunden!");
                    return true;
                }
                if (target == p) {
                    if (!fly.contains(p)) {
                        fly.add(p);
                        p.setAllowFlight(true);
                        p.setFlying(true);
                        p.sendMessage("§cDer FliegeModus wurde aktiviert!");
                        return true;
                    }
                    fly.remove(p);
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    p.sendMessage("§cDer FliegeModus wurde deaktiviert!");
                    return true;
                }
                if (!fly.contains(target)) {
                    fly.add(target);
                    target.setAllowFlight(true);
                    target.setFlying(true);
                    target.sendMessage("§cDer FliegeModus wurde aktiviert!");
                    p.sendMessage("§cDer Spieler" + target.getDisplayName() + " kann nun fliegen!");
                    return true;
                }
                fly.remove(target);
                target.setAllowFlight(false);
                target.setFlying(false);
                target.sendMessage("§cDer FliegeModus wurde deaktiviert!");
                p.sendMessage("§cDer Spieler" + target.getDisplayName() + " kann nun nicht mehr fliegen!");
                return true;
            }
            p.sendMessage("§cBenutze /fly");
            return true;
        }
        p.sendMessage("§cDu darfst dies nicht tun!");
        return true;
    }
}