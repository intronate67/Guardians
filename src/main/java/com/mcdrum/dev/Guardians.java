package com.mcdrum.dev;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @Author Hunter Sharpe
 */
public class Guardians extends JavaPlugin implements Listener{

    private static Guardians instance;
    public static Guardians getInstance(){
        return instance;
    }

    @Override
    public void onEnable(){
        getCommand("guard").setExecutor(new GuardCommand());
    }
    @Override
    public void onDisable(){

    }


}
