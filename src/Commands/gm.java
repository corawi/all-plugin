package Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gm implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§7Command is available for players only.");
            return true;
        }
        Player p = (Player)sender;
        if (p.hasPermission("gamemode")) {
            if (args.length == 0) {
                sendUsage(sender);
                return true;
            }
            if (args.length == 1) {
                GameMode gameMode;
                String mode = args[0].toLowerCase();
                switch (mode) {
                    case "0":
                    case "survival":
                        gameMode = GameMode.SURVIVAL;
                        break;
                    case "1":
                    case "creative":
                        gameMode = GameMode.CREATIVE;
                        break;
                    case "2":
                    case "adventure":
                        gameMode = GameMode.ADVENTURE;
                        break;
                    case "3":
                    case "spectator":
                        gameMode = GameMode.SPECTATOR;
                        break;
                    default:
                        gameMode = null;
                        break;
                }
                if (gameMode == null) {
                    sendUsage(sender);
                    return true;
                }
                p.setGameMode(gameMode);
                p.sendMessage("§7Dein gamemode wurde auf §9" + p.getGameMode() + "§7 gesetzt!");
                return true;
            }
            if (args.length == 2) {
                GameMode gameMode;
                Player target = Bukkit.getPlayerExact(args[1]);
                if (target == null) {
                    p.sendMessage("§7Der gamemode von §9" + target.getGameMode() + "§7 auf §9" + args[1] + "§7 gesetzt!");
                    return true;
                }
                String mode = args[0];
                switch (mode) {
                    case "0":
                        gameMode = GameMode.SURVIVAL;
                        break;
                    case "1":
                        gameMode = GameMode.CREATIVE;
                        break;
                    case "2":
                        gameMode = GameMode.ADVENTURE;
                        break;
                    case "3":
                        gameMode = GameMode.SPECTATOR;
                        break;
                    default:
                        gameMode = null;
                        break;
                }
                if (gameMode == null) {
                    sendUsage(sender);
                    return true;
                }
                if (target == p) {
                    p.setGameMode(gameMode);
                    p.sendMessage("§7Dein gamemode wurde auf §9" + p.getGameMode() + "§7 gesetzt!");
                    return true;
                }
                target.setGameMode(gameMode);
                target.sendMessage("§7Dein gamemode wurde auf §9" + p.getGameMode() + "§7 gesetzt!");
                p.sendMessage("§7Der gamemode von §9" + target.getGameMode() + "§7 auf §9" + args[1] + "§7 gesetzt!");
                return true;
            }
            sendUsage(sender);
            return true;
        }
        p.sendMessage("§7Du hast keine Berechtigung dies zu tun");
        return true;
    }
    private void sendUsage(CommandSender sender){
        sender.sendMessage("§7Verwendung§8: §9/gamemode <gamemode> <spieler>!");
        sender.sendMessage("§7<gamemode>§8: §90 / 1 / 2 / 3 ");
        sender.sendMessage("§7<gamemode 2 >§8: §9SURVIVAL / CREATIVE / ADVENTURE / SPECTATOR");
    }
}
