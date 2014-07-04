package com.mcdrum.dev;

import de.kumpelblase2.remoteentities.EntityManager;
import de.kumpelblase2.remoteentities.RemoteEntities;
import de.kumpelblase2.remoteentities.api.RemoteEntity;
import de.kumpelblase2.remoteentities.api.RemoteEntityType;
import de.kumpelblase2.remoteentities.api.thinking.InteractBehavior;
import de.kumpelblase2.remoteentities.api.thinking.goals.DesireProtectOwner;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @Author Hunter Sharpe
 */
public class GuardCommand implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can spawn a guardian!");
            return true;
        }
        final Player p = (Player) sender;
        if(!p.hasPermission("guardians.spawn")){
            p.sendMessage("You do not have permission!");
            return true;
        }
        if(args.length != 0){
            p.sendMessage("Incorrect Command usage!");
        }
        EntityManager manager = RemoteEntities.createManager(Guardians.getInstance());
        Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 1, p.getLocation().getZ() + 1);
        final RemoteEntity entity = manager.createEntity(RemoteEntityType.Human, loc, false);
        entity.getMind().addTargetingDesire(new DesireProtectOwner(2.0F, true),1);
        /*TODO: List
        Give NPC's swords
        Different NPCs
        Economy Built In
        Advanced Permissions
         */
        entity.getMind().addBehaviour(new InteractBehavior(entity) {
            @Override
            public void onInteract(Player player) {
                player.sendMessage(ChatColor.GREEN + "Hello there " + player.getName() + "!");
            }
        });
        return true;
    }

}
